package ii.cipriantarlev.marketmanagementapi.product;

import ii.cipriantarlev.marketmanagementapi.barcode.BarcodeDTO;
import ii.cipriantarlev.marketmanagementapi.category.CategoryDTO;
import ii.cipriantarlev.marketmanagementapi.config.IntegrationTestConfiguration;
import ii.cipriantarlev.marketmanagementapi.measuringunit.MeasuringUnitDTO;
import ii.cipriantarlev.marketmanagementapi.product.history.ProductHistory;
import ii.cipriantarlev.marketmanagementapi.productscode.ProductCodeDTO;
import ii.cipriantarlev.marketmanagementapi.subcategory.SubcategoryDTONoCategory;
import ii.cipriantarlev.marketmanagementapi.vat.VatDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientException;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static ii.cipriantarlev.marketmanagementapi.utils.Constants.*;

class ProductControllerIntegrationTest extends IntegrationTestConfiguration {

    private ProductDTO productDTO;

    @BeforeEach
    void setUp() {
        productDTO = ProductDTO.builder()
                .nameRom("Test")
                .nameRus("Fdrффв")
                .category(CategoryDTO.builder().id(1L).name("dsds").build())
                .subcategory(SubcategoryDTONoCategory.builder().id(1L).name("dds").build())
                .measuringUnit(MeasuringUnitDTO.builder().id(1L).name("kg").build())
                .vat(VatDTO.builder().id(1L).value(2).name("2%").build())
                .barcodes(Collections.singletonList(BarcodeDTO.builder().value("222").build()))
                .productCode(ProductCodeDTO.builder().id(1L).value("MD000").build())
                .retailPrice(BigDecimal.TEN)
                .oldRetailPrice(BigDecimal.ONE)
                .build();
    }

    @Test
    void getProducts() throws Exception {
        HttpEntity<List<ProductDTO>> entity = new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(PRODUCTS_ROOT_PATH),
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<List<ProductDTO>>() {
                        });

        assertEquals(3, Objects.requireNonNull(response.getBody()).size());
        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void getProductWhenOk() throws Exception {
        var response = getRestTemplateWithAuth()
                .getForEntity(createUri(PRODUCTS_ROOT_PATH.concat(GOOD_ID_PATH)),
                        ProductDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals("Naturalis 1L", Objects.requireNonNull(response.getBody()).getNameRom());
        assertEquals(GOOD_ID, response.getBody().getId());
    }

    @Test
    void getProductWhenNotFound() throws Exception {
        assertThrows(RestClientException.class, throwExceptionWhenGet(PRODUCTS_ROOT_PATH.concat(BAD_ID_PATH)));
    }

    @Test
    void getProductByBarcodeValueWhenOk() throws Exception {
        final String barcodeValue = "484000384004";
        String path = PRODUCTS_ROOT_PATH + BARCODES_ROOT_PATH +
                "/" + barcodeValue;

        var response = getRestTemplateWithAuth()
                .getForEntity(createUri(path), ProductDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(GOOD_ID, Objects.requireNonNull(response.getBody()).getId());
        assertEquals(barcodeValue, response.getBody().getBarcodes().get(0).getValue());
    }

    @Test
    void getProductByBarcodeValueWhenNotFound() throws Exception {
        final String barcodeValue = "48433322384004";
        String path = PRODUCTS_ROOT_PATH + BARCODES_ROOT_PATH +
                "/" + barcodeValue;

        assertThrows(RestClientException.class, throwExceptionWhenGet(path));
    }

    @Test
    void createProductWhenOk() throws Exception {
        var response = getRestTemplateWithAuth()
                .postForEntity(createUri(PRODUCTS_ROOT_PATH),
                        productDTO,
                        ProductDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(4L, Objects.requireNonNull(response.getBody()).getId());
        assertEquals("Test", response.getBody().getNameRom());
    }

    @Test
    void createProductWhenAlreadyExist() throws Exception {
        productDTO.setId(GOOD_ID);

        HttpEntity<ProductDTO> entity = new HttpEntity<>(productDTO, new HttpHeaders());

        assertThrows(RestClientException.class, throwException(entity, PRODUCTS_ROOT_PATH, HttpMethod.POST));
    }

    @Test
    void updateProductWhenOk() throws Exception {
        productDTO.setId(GOOD_ID);

        HttpEntity<ProductDTO> entity = new HttpEntity<>(productDTO, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(PRODUCTS_ROOT_PATH),
                        HttpMethod.PUT,
                        entity,
                        ProductDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(GOOD_ID, Objects.requireNonNull(response.getBody()).getId());
        assertEquals("Test", response.getBody().getNameRom());
    }

    @Test
    void updateProductWhenNotFound() throws Exception {
        productDTO.setId(BAD_ID);

        HttpEntity<ProductDTO> entity = new HttpEntity<>(productDTO, new HttpHeaders());

        assertThrows(RestClientException.class, throwException(entity, PRODUCTS_ROOT_PATH, HttpMethod.PUT));
    }

    @Test
    void deleteProductWhenOk() throws Exception {
        HttpEntity<ProductDTO> entity = new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(PRODUCTS_ROOT_PATH.concat(GOOD_ID_PATH)),
                        HttpMethod.DELETE,
                        entity,
                        ProductDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void deleteProductWhenNotFound() throws Exception {
        HttpEntity<ProductDTO> entity = new HttpEntity<>(null, new HttpHeaders());

        assertThrows(RestClientException.class,
                throwException(entity, PRODUCTS_ROOT_PATH.concat(BAD_ID_PATH), HttpMethod.DELETE));
    }

    @Test
    void checkIfNameRomExistsWhenTrue() throws Exception {
        checkIfNameExist("Naturalis 1L", Boolean.TRUE, "/product/name-rom/");
    }

    @Test
    void checkIfNameRomExistsWhenFalse() throws Exception {
        checkIfNameExist("Naturalid", Boolean.FALSE, "/product/name-rom/");
    }

    @Test
    void checkIfNameRusExistsWhenTrue() throws Exception {
        checkIfNameExist("Paste Barilla 400gr", Boolean.TRUE, "/product/name-rus/");
    }

    @Test
    void checkIfNameRusExistsWhenFalse() throws Exception {
        checkIfNameExist("Natural1L", Boolean.FALSE, "/product/name-rus/");
    }

    @Test
    void getProductHistoryWhenExist() throws Exception {
        HttpEntity<Set<ProductHistory>> entity = new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(PRODUCTS_ROOT_PATH.concat("/product-history").concat(GOOD_ID_PATH)),
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<Set<ProductHistory>>() {
                        });

        assertEquals(3, Objects.requireNonNull(response.getBody()).size());
        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void getProductHistoryWhenNotFound() throws Exception {
        HttpEntity<Set<ProductHistory>> entity = new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(PRODUCTS_ROOT_PATH.concat("/product-history").concat(BAD_ID_PATH)),
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<Set<ProductHistory>>() {
                        });

        assertEquals(0, Objects.requireNonNull(response.getBody()).size());
        assertEquals(OK, response.getStatusCodeValue());
    }

    private void checkIfNameExist(String name, Boolean returnValue, String path) {
        var response = getRestTemplateWithAuth()
                .getForEntity(createUri(PRODUCTS_ROOT_PATH.concat(path).concat(name)),
                        Boolean.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(returnValue, response.getBody());
    }

    @Test
    void updateIsCheckedMarkerWhenOk() throws Exception {
        Map<Boolean, List<Long>> productsToUpdate = Collections.singletonMap(true, Collections.singletonList(GOOD_ID));
        HttpEntity<Map<Boolean, List<Long>>> entity = new HttpEntity<>(productsToUpdate, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(PRODUCTS_ROOT_PATH.concat(IS_CHECKED)),
                        HttpMethod.PUT,
                        entity,
                        Integer.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(1, response.getBody());
    }

    @Test
    void updateIsCheckedMarkerWhenNotFound() throws Exception {
        Map<Boolean, List<Long>> productsToUpdate = Collections.singletonMap(true, Collections.singletonList(BAD_ID));
        HttpEntity<Map<Boolean, List<Long>>> entity = new HttpEntity<>(productsToUpdate, new HttpHeaders());

        assertThrows(RestClientException.class,
                throwException(
                        entity,
                        PRODUCTS_ROOT_PATH.concat(IS_CHECKED),
                        HttpMethod.PUT));
    }

    @Test
    void getMarkedProducts() {
        HttpEntity<List<ProductDTOForList>> entity = new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(PRODUCTS_ROOT_PATH.concat(IS_CHECKED)),
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<List<ProductDTOForList>>() {
                        });

        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void printMarkedProducts() {
        Map<Long, Integer> productsToPrint = Collections.singletonMap(GOOD_ID, 1);
        HttpEntity<Map<Long, Integer>> entity = new HttpEntity<>(productsToPrint, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(PRODUCTS_ROOT_PATH.concat(PRINT_PRODUCTS)),
                        HttpMethod.POST,
                        entity,
                        new ParameterizedTypeReference<byte[]>() {
                        });

        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void printMarkedProductsWhenIdNotFound() throws Exception {
        Map<Long, Integer> productsToPrint = Collections.singletonMap(BAD_ID, 1);
        HttpEntity<Map<Long, Integer>> entity = new HttpEntity<>(productsToPrint, new HttpHeaders());

        assertThrows(RestClientException.class,
                throwException(
                        entity,
                        PRODUCTS_ROOT_PATH.concat(PRINT_PRODUCTS),
                        HttpMethod.POST));
    }
}