package ii.cipriantarlev.marketmanagementapi.product;

import ii.cipriantarlev.marketmanagementapi.product.history.ProductHistory;
import ii.cipriantarlev.marketmanagementapi.product.history.ProductHistoryService;
import ii.cipriantarlev.marketmanagementapi.utils.RestControllerUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpHeaders;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static ii.cipriantarlev.marketmanagementapi.utils.Constants.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class ProductControllerTest {

    @InjectMocks
    private ProductController controller;

    @Mock
    private ProductService service;

    @Mock
    private RestControllerUtil restControllerUtil;

    @Mock
    private ProductHistoryService productHistoryService;

    private final long id = 1L;
    private final int ok = 200;
    private final String nameRom = "Test";

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void getProducts() throws Exception {
        List<ProductDTOForList> productDTOList = Collections.singletonList(new ProductDTOForList());

        when(service.findAll()).thenReturn(productDTOList);

        var response = controller.getProducts();

        verify(service).findAll();
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(productDTOList, response.getBody());
    }

    @Test
    void getProduct() throws Exception {
        ProductDTO productDTO = ProductDTO.builder().id(id).build();

        when(service.findById(id)).thenReturn(productDTO);

        var response = controller.getProduct(id);

        verify(service).findById(id);
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(productDTO, response.getBody());
    }

    @Test
    void getProductByBarcodeValue() throws Exception {
        final String barcodeValue = "222039444";
        ProductDTO productDTO = ProductDTO.builder().id(id).build();

        when(service.findByBarcodeValue(barcodeValue)).thenReturn(productDTO);

        var response = controller.getProductByBarcodeValue(barcodeValue);

        verify(service).findByBarcodeValue(barcodeValue);
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(productDTO, response.getBody());
    }

    @Test
    void createProduct() throws Exception {
        ProductDTO productDTO = ProductDTO.builder().nameRom(nameRom).build();
        ProductDTO savedProductDTO = ProductDTO.builder().id(id).nameRom(nameRom).build();

        when(service.save(productDTO)).thenReturn(savedProductDTO);
        when(restControllerUtil
                .setHttpsHeaderLocation(PRODUCTS_ROOT_PATH.concat(ID_PATH), savedProductDTO.getId()))
                .thenReturn(new HttpHeaders());

        var response = controller.createProduct(productDTO);

        verify(service).save(productDTO);
        verify(restControllerUtil).setHttpsHeaderLocation(PRODUCTS_ROOT_PATH.concat(ID_PATH), savedProductDTO.getId());
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(savedProductDTO, response.getBody());
    }

    @Test
    void updateProduct() throws Exception {
        ProductDTO productDTO = ProductDTO.builder().id(id).nameRom(nameRom).build();

        when(service.update(productDTO)).thenReturn(productDTO);

        var response = controller.updateProduct(productDTO);

        verify(service).update(productDTO);
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(productDTO, response.getBody());
    }

    @Test
    void deleteProduct() throws Exception {
        doNothing().when(service).deleteById(id);

        var response = controller.deleteProduct(id);

        verify(service).deleteById(id);
        assertEquals(ok, response.getStatusCodeValue());
    }

    @Test
    void checkIfNameRomExists() throws Exception {
        when(service.checkIfNameRomExists(nameRom)).thenReturn(true);

        var response = controller.checkIfNameRomExists(nameRom);

        verify(service).checkIfNameRomExists(nameRom);
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(Boolean.TRUE, response.getBody());
    }

    @Test
    void checkIfNameRusExists() throws Exception {
        final String nameRus = "Тест";
        when(service.checkIfNameRusExists(nameRus)).thenReturn(true);

        var response = controller.checkIfNameRusExists(nameRus);

        verify(service).checkIfNameRusExists(nameRus);
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(Boolean.TRUE, response.getBody());
    }

    @Test
    void getProductHistory() throws Exception {
        Set<ProductHistory> productHistorySet = Collections.singleton(new ProductHistory());

        when(productHistoryService.findProductPriceHistory(id)).thenReturn(productHistorySet);

        var response = controller.getProductHistory(id);

        verify(productHistoryService).findProductPriceHistory(id);
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(productHistorySet, response.getBody());
    }

    @Test
    void updateIsCheckedMarker() throws Exception {
        Map<Boolean, List<Long>> productsToUpdate = Collections.singletonMap(true, Collections.singletonList(id));

        when(service.updateIsCheckedMarker(productsToUpdate)).thenReturn(1);

        var response = controller.updateIsCheckedMarker(productsToUpdate);

        verify(service).updateIsCheckedMarker(productsToUpdate);
        assertEquals(1, response.getBody());
        assertEquals(ok, response.getStatusCodeValue());
    }
}