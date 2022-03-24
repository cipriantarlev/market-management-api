package ii.cipriantarlev.marketmanagementapi.invoiceproduct;

import ii.cipriantarlev.marketmanagementapi.utils.RestControllerUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpHeaders;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static ii.cipriantarlev.marketmanagementapi.utils.Constants.*;

class InvoiceProductControllerTest {

    @InjectMocks
    private InvoiceProductController controller;

    @Mock
    private InvoiceProductService service;

    @Mock
    private RestControllerUtil restControllerUtil;

    private final long id = 1L;
    private final int ok = 200;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void getInvoiceProductsByInvoiceId() throws Exception {
        List<InvoiceProductDTO> invoiceProductDTOList = Collections.singletonList(new InvoiceProductDTO());

        when(service.findAllByInvoiceId(id)).thenReturn(invoiceProductDTOList);

        var response = controller.getInvoiceProductsByInvoiceId(id);

        assertEquals(invoiceProductDTOList, response.getBody());
        assertEquals(ok, response.getStatusCodeValue());
        verify(service).findAllByInvoiceId(id);
    }

    @Test
    void getInvoiceProductById() throws Exception {
        InvoiceProductDTO invoiceProductDTO = InvoiceProductDTO.builder().id(id).build();

        when(service.findById(id)).thenReturn(invoiceProductDTO);

        var response = controller.getInvoiceProductById(id);

        assertEquals(invoiceProductDTO, response.getBody());
        assertEquals(ok, response.getStatusCodeValue());
        verify(service).findById(id);
    }

    @Test
    void createInvoiceProduct() throws Exception {
        InvoiceProductDTO invoiceProductDTO = InvoiceProductDTO.builder().build();
        InvoiceProductDTO savedInvoiceProductDTO = InvoiceProductDTO.builder().id(id).build();

        when(service.save(invoiceProductDTO)).thenReturn(savedInvoiceProductDTO);
        when(restControllerUtil.setHttpsHeaderLocation(
                INVOICE_PRODUCT_ROOT_PATH.concat(PRODUCT_PATH).concat(PRODUCT_ID_PATH),
                savedInvoiceProductDTO.getId()))
                .thenReturn(new HttpHeaders());

        var response = controller.createInvoiceProduct(invoiceProductDTO);

        assertEquals(savedInvoiceProductDTO, response.getBody());
        assertEquals(ok, response.getStatusCodeValue());
        verify(service).save(invoiceProductDTO);
        verify(restControllerUtil).setHttpsHeaderLocation(
                INVOICE_PRODUCT_ROOT_PATH.concat(PRODUCT_PATH).concat(PRODUCT_ID_PATH),
                savedInvoiceProductDTO.getId());
    }

    @Test
    void updateInvoiceProduct() throws Exception {
        InvoiceProductDTO invoiceProductDTO = InvoiceProductDTO.builder().id(id).build();

        when(service.update(invoiceProductDTO)).thenReturn(invoiceProductDTO);

        var response = controller.updateInvoiceProduct(invoiceProductDTO);

        assertEquals(invoiceProductDTO, response.getBody());
        assertEquals(ok, response.getStatusCodeValue());
        verify(service).update(invoiceProductDTO);
    }

    @Test
    void deleteInvoiceProductById() throws Exception {
        doNothing().when(service).deleteById(id);

        var response = controller.deleteInvoiceProductById(id);

        assertEquals(ok, response.getStatusCodeValue());
        verify(service).deleteById(id);
    }
}