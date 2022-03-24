package ii.cipriantarlev.marketmanagementapi.invoice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class InvoiceMapperTest {

    @InjectMocks
    private InvoiceMapper invoiceMapper;

    @Mock
    private ModelMapper modelMapper;

    private Invoice invoice;
    private InvoiceDTO invoiceDTO;

    @BeforeEach
    void setUp() {
        openMocks(this);
        invoiceDTO = InvoiceDTO.builder().id(1L).invoiceNumber("TEST123").build();
        invoice = new Invoice();
        invoice.setId(1L);
        invoice.setInvoiceNumber("TEST123");
    }

    @Test
    void mapEntityToDTO() throws Exception {
        when(modelMapper.map(invoice, InvoiceDTO.class)).thenReturn(invoiceDTO);

        var resultedInvoiceDTO = invoiceMapper.mapEntityToDTO(invoice);

        verify(modelMapper).map(invoice, InvoiceDTO.class);
        assertEquals(invoiceDTO, resultedInvoiceDTO);
    }

    @Test
    void mapDTOToEntity() throws Exception {
        when(modelMapper.map(invoiceDTO, Invoice.class)).thenReturn(invoice);

        var resultedInvoice = invoiceMapper.mapDTOToEntity(invoiceDTO);

        verify(modelMapper).map(invoiceDTO, Invoice.class);
        assertEquals(invoice, resultedInvoice);
    }
}