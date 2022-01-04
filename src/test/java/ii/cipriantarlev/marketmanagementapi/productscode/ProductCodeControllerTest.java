package ii.cipriantarlev.marketmanagementapi.productscode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class ProductCodeControllerTest {

    @InjectMocks
    private ProductCodeController controller;

    @Mock
    private ProductCodeService service;

    private ProductCodeDTO productCodeDTO;
    private final long id = 1L;
    private final int ok = 200;

    @BeforeEach
    void setUp() {
        openMocks(this);
        productCodeDTO = ProductCodeDTO.builder().id(id).value("TEST").build();
    }

    @Test
    void getProductsCode() throws Exception {
        List<ProductCodeDTO> productCodeDTOList = Collections.singletonList(productCodeDTO);

        when(service.findAll()).thenReturn(productCodeDTOList);

        var response = controller.getProductsCode();

        verify(service).findAll();
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(productCodeDTOList, response.getBody());
    }

    @Test
    void getProductCode() throws Exception {
        when(service.findById(id)).thenReturn(productCodeDTO);

        var response = controller.getProductCode(id);

        verify(service).findById(id);
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(productCodeDTO, response.getBody());
    }

    @Test
    void generateNewProductCode() throws Exception {
        when(service.generateNewProductCode()).thenReturn(productCodeDTO);

        var response = controller.generateNewProductCode();

        verify(service).generateNewProductCode();
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(productCodeDTO, response.getBody());
    }

    @Test
    void deleteProductCode() throws Exception {
        doNothing().when(service).deleteById(id);

        var response = controller.deleteProductCode(id);

        verify(service).deleteById(id);
        assertEquals(ok, response.getStatusCodeValue());
    }
}