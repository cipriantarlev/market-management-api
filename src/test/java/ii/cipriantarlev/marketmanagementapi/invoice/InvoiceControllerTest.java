package ii.cipriantarlev.marketmanagementapi.invoice;

import ii.cipriantarlev.marketmanagementapi.documenttype.DocumentType;
import ii.cipriantarlev.marketmanagementapi.documenttype.DocumentTypeDTO;
import ii.cipriantarlev.marketmanagementapi.myorganization.MyOrganizationDTOOnlyName;
import ii.cipriantarlev.marketmanagementapi.myorganization.MyOrganizationService;
import ii.cipriantarlev.marketmanagementapi.utils.MarketManagementFactory;
import ii.cipriantarlev.marketmanagementapi.utils.RestControllerUtil;
import ii.cipriantarlev.marketmanagementapi.vendor.VendorDTOOnlyName;
import ii.cipriantarlev.marketmanagementapi.vendor.VendorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpHeaders;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static ii.cipriantarlev.marketmanagementapi.utils.Constants.*;

class InvoiceControllerTest {

    @InjectMocks
    private InvoiceController controller;

    @Mock
    private InvoiceService invoiceService;

    @Mock
    private MyOrganizationService myOrganizationService;

    @Mock
    private VendorService vendorService;

    @Mock
    private RestControllerUtil restControllerUtil;

    @Mock
    private MarketManagementFactory factory;

    private final long id = 1L;
    private final int ok = 200;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void getInvoices() throws Exception {
        List<InvoiceDTO> invoiceDTOList = Collections.singletonList(new InvoiceDTO());

        when(invoiceService.findAll()).thenReturn(invoiceDTOList);

        var response = controller.getInvoices();

        assertEquals(invoiceDTOList, response.getBody());
        assertEquals(ok, response.getStatusCodeValue());
        verify(invoiceService).findAll();
    }

    @Test
    void getIncomeInvoices() throws Exception {
        List<InvoiceDTO> invoiceDTOList = Collections.singletonList(
                InvoiceDTO.builder().documentType(DocumentTypeDTO.builder().id(id).build()).build());
        final var documentType = new DocumentType(id);

        when(factory.getNewDocumentType(id)).thenReturn(documentType);
        when(invoiceService.findInvoicesByDocumentType(documentType)).thenReturn(invoiceDTOList);

        var response = controller.getIncomeInvoices();

        assertEquals(invoiceDTOList, response.getBody());
        assertEquals(ok, response.getStatusCodeValue());
        verify(invoiceService).findInvoicesByDocumentType(documentType);
        verify(factory).getNewDocumentType(id);
    }

    @Test
    void getOutcomeInvoices() throws Exception {
        final long id = 2L;
        List<InvoiceDTO> invoiceDTOList = Collections.singletonList(
                InvoiceDTO.builder().documentType(DocumentTypeDTO.builder().id(id).build()).build());
        final var documentType = new DocumentType(id);

        when(factory.getNewDocumentType(id)).thenReturn(documentType);
        when(invoiceService.findInvoicesByDocumentType(documentType)).thenReturn(invoiceDTOList);

        var response = controller.getOutcomeInvoices();

        assertEquals(invoiceDTOList, response.getBody());
        assertEquals(ok, response.getStatusCodeValue());
        verify(invoiceService).findInvoicesByDocumentType(documentType);
    }

    @Test
    void getInvoice() throws Exception {
        InvoiceDTO invoiceDTO = InvoiceDTO.builder().id(id).build();

        when(invoiceService.findById(id)).thenReturn(invoiceDTO);

        var response = controller.getInvoice(id);

        assertEquals(invoiceDTO, response.getBody());
        assertEquals(ok, response.getStatusCodeValue());
        verify(invoiceService).findById(id);
    }

    @Test
    void getMyOrganizations() throws Exception {
        List<MyOrganizationDTOOnlyName> myOrganizations = Collections.singletonList(new MyOrganizationDTOOnlyName());

        when(myOrganizationService.findAllMyOrganizationDTOOnlyName()).thenReturn(myOrganizations);

        var response = controller.getMyOrganizations();

        assertEquals(myOrganizations, response.getBody());
        assertEquals(ok, response.getStatusCodeValue());
        verify(myOrganizationService).findAllMyOrganizationDTOOnlyName();
    }

    @Test
    void getVendors() throws Exception {
        List<VendorDTOOnlyName> vendors = Collections.singletonList(new VendorDTOOnlyName());

        when(vendorService.findAllVendorDTOOnlyName()).thenReturn(vendors);

        var response = controller.getVendors();

        assertEquals(vendors, response.getBody());
        assertEquals(ok, response.getStatusCodeValue());
        verify(vendorService).findAllVendorDTOOnlyName();
    }

    @Test
    void createInvoice() throws Exception {
        InvoiceDTO invoiceDTO = InvoiceDTO.builder().invoiceNumber("TEST123").build();
        InvoiceDTO savedInvoiceDTO = InvoiceDTO.builder().id(id).invoiceNumber("TEST123").build();

        when(invoiceService.save(invoiceDTO)).thenReturn(savedInvoiceDTO);
        when(restControllerUtil.setHttpsHeaderLocation(INVOICES_ROOT_PATH, savedInvoiceDTO.getId()))
                .thenReturn(new HttpHeaders());

        var response = controller.createInvoice(invoiceDTO);

        assertEquals(savedInvoiceDTO, response.getBody());
        assertEquals(ok, response.getStatusCodeValue());
        verify(invoiceService).save(invoiceDTO);
        verify(restControllerUtil).setHttpsHeaderLocation(INVOICES_ROOT_PATH, savedInvoiceDTO.getId());
    }

    @Test
    void updateInvoice() throws Exception {
        InvoiceDTO invoiceDTO = InvoiceDTO.builder().id(id).invoiceNumber("TEST123").build();

        when(invoiceService.update(invoiceDTO)).thenReturn(invoiceDTO);

        var response = controller.updateInvoice(invoiceDTO);

        assertEquals(invoiceDTO, response.getBody());
        assertEquals(ok, response.getStatusCodeValue());
        verify(invoiceService).update(invoiceDTO);
    }

    @Test
    void updateInvoiceIsApprovedMarker() throws Exception {
        Map<Boolean, List<Long>> invoicesToUpdate = Collections.singletonMap(true, Collections.singletonList(id));
        when(invoiceService.updateIsApprovedMarker(invoicesToUpdate)).thenReturn(1);

        var response = controller.updateInvoiceIsApprovedMarker(invoicesToUpdate);

        assertEquals(1, response.getBody());
        assertEquals(ok, response.getStatusCodeValue());
        verify(invoiceService).updateIsApprovedMarker(invoicesToUpdate);
    }

    @Test
    void deleteInvoice() throws Exception {
        doNothing().when(invoiceService).deleteById(id);

        var response = controller.deleteInvoice(id);

        assertEquals(ok, response.getStatusCodeValue());
        verify(invoiceService).deleteById(id);
    }
}