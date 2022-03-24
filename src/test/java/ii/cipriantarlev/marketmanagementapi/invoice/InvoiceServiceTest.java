package ii.cipriantarlev.marketmanagementapi.invoice;

import ii.cipriantarlev.marketmanagementapi.documenttype.DocumentType;
import ii.cipriantarlev.marketmanagementapi.documenttype.DocumentTypeDTO;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTOFoundWhenSaveException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTOListNotFoundException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTONotFoundException;
import ii.cipriantarlev.marketmanagementapi.history.EntitiesHistoryService;
import ii.cipriantarlev.marketmanagementapi.history.HistoryAction;
import ii.cipriantarlev.marketmanagementapi.utils.MarketManagementFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class InvoiceServiceTest {

    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    @Mock
    private InvoiceRepository repository;

    @Mock
    private InvoiceMapper mapper;

    @Mock
    private EntitiesHistoryService entitiesHistoryService;

    @Mock
    private MarketManagementFactory factory;

    private InvoiceDTO invoiceDTO;
    private Invoice invoice;

    private final long id = 1L;

    @BeforeEach
    void setUp() {
        openMocks(this);
        invoiceDTO = InvoiceDTO.builder().id(id).invoiceNumber("TEST123").build();
        invoice = new Invoice();
        invoice.setId(id);
        invoice.setInvoiceNumber("TEST123");
    }

    @Test
    void findAll() throws Exception {
        List<Invoice> invoices = Collections.singletonList(invoice);

        when(repository.findAll()).thenReturn(invoices);
        when(mapper.mapEntityToDTO(invoices.get(0))).thenReturn(invoiceDTO);

        var invoicesDTO = invoiceService.findAll();

        verify(repository).findAll();
        verify(mapper).mapEntityToDTO(invoices.get(0));
        assertFalse(invoicesDTO.isEmpty());
    }

    @Test
    void findAllWhenEmptyList() throws Exception {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(DTOListNotFoundException.class, () -> invoiceService.findAll());
        verify(repository).findAll();
    }

    @Test
    void findAllIncomeInvoices() throws Exception {
        var documentType = new DocumentType(1L);
        invoice.setDocumentType(documentType);
        invoiceDTO.setDocumentType(DocumentTypeDTO.builder().id(1L).build());
        List<Invoice> invoices = Collections.singletonList(invoice);

        when(repository.findAllByDocumentType(documentType)).thenReturn(invoices);
        when(mapper.mapEntityToDTO(invoices.get(0))).thenReturn(invoiceDTO);

        var invoicesDTO = invoiceService.findInvoicesByDocumentType(documentType);

        verify(repository).findAllByDocumentType(documentType);
        verify(mapper).mapEntityToDTO(invoices.get(0));
        assertFalse(invoicesDTO.isEmpty());
    }

    @Test
    void findAllIncomeInvoicesWhenEmptyList() throws Exception {
        var documentType = new DocumentType(1L);
        when(repository.findAllByDocumentType(documentType)).thenReturn(Collections.emptyList());

        assertThrows(DTOListNotFoundException.class, () -> invoiceService.findInvoicesByDocumentType(documentType));
        verify(repository).findAllByDocumentType(documentType);
    }

    @Test
    void findAllOutcomeInvoices() throws Exception {
        var documentType = new DocumentType(2L);
        invoice.setDocumentType(documentType);
        invoiceDTO.setDocumentType(DocumentTypeDTO.builder().id(1L).build());
        List<Invoice> invoices = Collections.singletonList(invoice);

        when(repository.findAllByDocumentType(documentType)).thenReturn(invoices);
        when(mapper.mapEntityToDTO(invoices.get(0))).thenReturn(invoiceDTO);

        var invoicesDTO = invoiceService.findInvoicesByDocumentType(documentType);

        verify(repository).findAllByDocumentType(documentType);
        verify(mapper).mapEntityToDTO(invoices.get(0));
        assertFalse(invoicesDTO.isEmpty());
    }

    @Test
    void findAllOutcomeInvoicesWhenEmpty() throws Exception {
        var documentType = new DocumentType(2L);
        when(repository.findAllByDocumentType(documentType)).thenReturn(Collections.emptyList());

        assertThrows(DTOListNotFoundException.class, () -> invoiceService.findInvoicesByDocumentType(documentType));
        verify(repository).findAllByDocumentType(documentType);
    }

    @Test
    void findById() throws Exception {
        var invoiceOptional = Optional.of(invoice);

        when(repository.findById(id)).thenReturn(invoiceOptional);
        when(mapper.mapEntityToDTO(invoiceOptional.get())).thenReturn(invoiceDTO);

        var invoiceDTO = invoiceService.findById(id);

        verify(repository).findById(id);
        verify(mapper).mapEntityToDTO(invoiceOptional.get());
        assertNotNull(invoiceDTO);
        assertEquals(id, invoiceDTO.getId());
    }

    @Test
    void findByIdWhenNotFound() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> invoiceService.findById(id));
        verify(repository).findById(id);
    }

    @Test
    void save() throws Exception {
        when(repository.findById(invoiceDTO.getId())).thenReturn(Optional.empty());
        when(mapper.mapDTOToEntity(invoiceDTO)).thenReturn(invoice);
        when(repository.save(invoice)).thenReturn(invoice);
        doNothing().when(entitiesHistoryService).createEntityHistoryRecord(invoice, null, HistoryAction.CREATE);
        when(mapper.mapEntityToDTO(invoice)).thenReturn(invoiceDTO);

        var savedInvoiceDTO = invoiceService.save(invoiceDTO);

        verify(repository).findById(invoiceDTO.getId());
        verify(mapper).mapDTOToEntity(invoiceDTO);
        verify(repository).save(invoice);
        verify(entitiesHistoryService).createEntityHistoryRecord(invoice, null, HistoryAction.CREATE);
        verify(mapper).mapEntityToDTO(invoice);
        assertNotNull(savedInvoiceDTO);
        assertEquals(id, savedInvoiceDTO.getId());
    }

    @Test
    void saveWhenAlreadyExist() throws Exception {
        when(repository.findById(invoiceDTO.getId())).thenReturn(Optional.of(invoice));

        assertThrows(DTOFoundWhenSaveException.class, () -> invoiceService.save(invoiceDTO));
        verify(repository).findById(invoiceDTO.getId());
    }

    @Test
    void update() throws Exception {
        var invoiceOptional = Optional.of(invoice);

        when(repository.findById(id)).thenReturn(invoiceOptional);
        when(mapper.mapEntityToDTO(invoiceOptional.get())).thenReturn(invoiceDTO);
        when(mapper.mapDTOToEntity(invoiceDTO)).thenReturn(invoice);
        when(repository.save(invoice)).thenReturn(invoice);
        doNothing().when(entitiesHistoryService).createEntityHistoryRecord(invoice, invoice, HistoryAction.UPDATE);

        var updateInvoiceDTO = invoiceService.update(invoiceDTO);

        verify(repository).findById(id);
        verify(mapper, times(2)).mapEntityToDTO(invoiceOptional.get());
        verify(mapper, times(3)).mapDTOToEntity(invoiceDTO);
        verify(repository).save(invoice);
        verify(entitiesHistoryService).createEntityHistoryRecord(invoice, invoice, HistoryAction.UPDATE);
        assertNotNull(updateInvoiceDTO);
        assertEquals(id, updateInvoiceDTO.getId());
    }

    @Test
    void updateWhenNotFound() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> invoiceService.update(invoiceDTO));
        verify(repository).findById(id);
    }

    @Test
    void deleteById() throws Exception {
        var invoiceOptional = Optional.of(invoice);

        when(repository.findById(id)).thenReturn(invoiceOptional);
        when(mapper.mapEntityToDTO(invoiceOptional.get())).thenReturn(invoiceDTO);
        when(mapper.mapDTOToEntity(invoiceDTO)).thenReturn(invoice);
        doNothing().when(entitiesHistoryService).createEntityHistoryRecord(invoice, null, HistoryAction.DELETE);
        doNothing().when(repository).deleteById(id);

        invoiceService.deleteById(id);

        verify(repository).findById(id);
        verify(mapper).mapEntityToDTO(invoiceOptional.get());
        verify(mapper).mapDTOToEntity(invoiceDTO);
        verify(repository).deleteById(id);
        verify(entitiesHistoryService).createEntityHistoryRecord(invoice, null, HistoryAction.DELETE);
    }

    @Test
    void deleteByIdWhenNotFound() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> invoiceService.deleteById(id));
        verify(repository).findById(id);
    }

    @Test
    void updateIsApprovedMarker() throws Exception {
        var invoiceOptional = Optional.of(invoice);
        Map<Boolean, List<Long>> invoicesToUpdate = Collections.singletonMap(true, Collections.singletonList(id));

        when(repository.findById(id)).thenReturn(invoiceOptional);
        when(mapper.mapEntityToDTO(invoiceOptional.get())).thenReturn(invoiceDTO);
        when(mapper.mapDTOToEntity(invoiceDTO)).thenReturn(invoice);
        when(factory.getClonedInvoice(invoice)).thenReturn(invoice);
        doNothing().when(entitiesHistoryService).createEntityHistoryRecord(invoice, invoice, HistoryAction.UPDATE);
        when(repository.updateIsApprovedMarker(true, id)).thenReturn(1);

        var updatedRows = invoiceService.updateIsApprovedMarker(invoicesToUpdate);

        verify(repository).findById(id);
        verify(mapper).mapEntityToDTO(invoiceOptional.get());
        verify(mapper).mapDTOToEntity(invoiceDTO);
        verify(entitiesHistoryService).createEntityHistoryRecord(invoice, invoice, HistoryAction.UPDATE);
        verify(repository).updateIsApprovedMarker(true, id);
        verify(factory).getClonedInvoice(invoice);
        assertEquals(1, updatedRows);
    }

    @Test
    void updateIsApprovedMarkerWhenNotFound() throws Exception {
        Map<Boolean, List<Long>> invoicesToUpdate = Collections.singletonMap(true, Collections.singletonList(id));

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> invoiceService.updateIsApprovedMarker(invoicesToUpdate));
        verify(repository).findById(id);
    }
}