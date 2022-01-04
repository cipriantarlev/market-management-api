package ii.cipriantarlev.marketmanagementapi.invoiceproduct;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class InvoiceProductMapperTest {

    @InjectMocks
    private InvoiceProductMapper mapper;

    @Mock
    private ModelMapper modelMapper;

    private InvoiceProduct invoiceProduct;
    private InvoiceProductDTO invoiceProductDTO;

    @BeforeEach
    void setUp() {
        openMocks(this);
        invoiceProductDTO = InvoiceProductDTO.builder().id(1L).build();
        invoiceProduct = new InvoiceProduct();
        invoiceProduct.setId(1L);
    }

    @Test
    void mapEntityToDTO() throws Exception {
        when(modelMapper.map(invoiceProduct, InvoiceProductDTO.class)).thenReturn(invoiceProductDTO);

        var resultedInvoiceProductDTO = mapper.mapEntityToDTO(invoiceProduct);

        verify(modelMapper).map(invoiceProduct, InvoiceProductDTO.class);
        assertEquals(invoiceProductDTO, resultedInvoiceProductDTO);
    }

    @Test
    void mapDTOToEntity() throws Exception {
        when(modelMapper.map(invoiceProductDTO, InvoiceProduct.class)).thenReturn(invoiceProduct);

        var resultedInvoiceProduct = mapper.mapDTOToEntity(invoiceProductDTO);

        verify(modelMapper).map(invoiceProductDTO, InvoiceProduct.class);
        assertEquals(invoiceProduct, resultedInvoiceProduct);
    }
}