package ii.cipriantarlev.marketmanagementapi.productscode;

import ii.cipriantarlev.marketmanagementapi.config.IntegrationTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientException;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static ii.cipriantarlev.marketmanagementapi.utils.Constants.*;

class ProductCodeControllerIntegrationTest extends IntegrationTestConfiguration {

    @Test
    void getProductsCode() throws Exception {
        HttpEntity<List<ProductCodeDTO>> entity = new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(PRODUCTS_CODE_ROOT_PATH),
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<List<ProductCodeDTO>>() {
                        });

        assertEquals(3, Objects.requireNonNull(response.getBody()).size());
        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void getProductCodeWhenOk() throws Exception {
        var response = getRestTemplateWithAuth()
                .getForEntity(createUri(PRODUCTS_CODE_ROOT_PATH.concat(GOOD_ID_PATH)),
                        ProductCodeDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(GOOD_ID, Objects.requireNonNull(response.getBody()).getId());
        assertEquals("MD00000000", response.getBody().getValue());
    }

    @Test
    void getProductCodeWhenNotFound() throws Exception {
        assertThrows(RestClientException.class, throwExceptionWhenGet(PRODUCTS_CODE_ROOT_PATH.concat(BAD_ID_PATH)));
    }

    @Test
    void generateNewProductCode() throws Exception {
        var response = getRestTemplateWithAuth()
                .postForEntity(createUri(PRODUCTS_CODE_ROOT_PATH),
                        null,
                        ProductCodeDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(4L, Objects.requireNonNull(response.getBody()).getId());
        assertEquals("MD00000003", response.getBody().getValue());
    }

    @Test
    void deleteProductCodeWhenOk() throws Exception {
        HttpEntity<ProductCodeDTO> entity = new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(PRODUCTS_CODE_ROOT_PATH.concat(GOOD_ID_PATH)),
                        HttpMethod.DELETE,
                        entity,
                        ProductCodeDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void deleteProductCodeWhenNotFound() throws Exception {
        HttpEntity<ProductCodeDTO> entity = new HttpEntity<>(null, new HttpHeaders());

        assertThrows(RestClientException.class,
                throwException(entity, PRODUCTS_CODE_ROOT_PATH.concat(BAD_ID_PATH), HttpMethod.DELETE));
    }
}