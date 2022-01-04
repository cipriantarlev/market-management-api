package ii.cipriantarlev.marketmanagementapi.barcode;

import ii.cipriantarlev.marketmanagementapi.config.IntegrationTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientException;

import java.util.List;
import java.util.Objects;

import static ii.cipriantarlev.marketmanagementapi.utils.Constants.BARCODES_ROOT_PATH;
import static org.junit.jupiter.api.Assertions.*;

class BarcodeControllerIntegrationTest extends IntegrationTestConfiguration {

    private final String BARCODE = "/barcode";

    @Test
    void getAllBarcodes() throws Exception {
        HttpEntity<List<BarcodeDTO>> entity = new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(BARCODES_ROOT_PATH),
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<BarcodeDTO>>() {
                });

        assertEquals(3, Objects.requireNonNull(response.getBody()).size());
        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void getBarcodeWhenStatusOk() throws Exception {
        var response = getRestTemplateWithAuth()
                .getForEntity(createUri(BARCODES_ROOT_PATH.concat(GOOD_ID_PATH)), BarcodeDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals("2100002", Objects.requireNonNull(response.getBody()).getValue());
        assertEquals(GOOD_ID, response.getBody().getId());
    }

    @Test
    void getBarcodeWhenStatusNotFound() throws Exception {
        assertThrows(RestClientException.class, throwExceptionWhenGet(BARCODES_ROOT_PATH.concat(BAD_ID_PATH)));
    }

    @Test
    void saveOrGenerateBarcode() throws Exception {
        String barcodeValue = "484008374840";
        BarcodeDTO barcode = BarcodeDTO.builder().value(barcodeValue).build();
        var response = getRestTemplateWithAuth()
                .postForEntity(createUri(BARCODES_ROOT_PATH), barcode, BarcodeDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(barcodeValue, Objects.requireNonNull(response.getBody()).getValue());
    }

    @Test
    void saveOrGenerateBarcodeWith21() throws Exception {
        BarcodeDTO barcode = BarcodeDTO.builder().value("21").build();
        var response = getRestTemplateWithAuth()
                .postForEntity(createUri(BARCODES_ROOT_PATH), barcode, BarcodeDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals("2100003", Objects.requireNonNull(response.getBody()).getValue());
    }

    @Test
    void saveOrGenerateBarcodeWith22() throws Exception {
        BarcodeDTO barcode = BarcodeDTO.builder().value("22").build();
        var response = getRestTemplateWithAuth()
                .postForEntity(createUri(BARCODES_ROOT_PATH), barcode, BarcodeDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals("220000285995", Objects.requireNonNull(response.getBody()).getValue());
    }

    @Test
    void deleteBarcode() throws Exception {
        var response = getRestTemplateWithAuth()
                .exchange(createUri(BARCODES_ROOT_PATH.concat(GOOD_ID_PATH)),
                        HttpMethod.DELETE,
                        null,
                        Void.class);

        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void checkIfValueExistsWhenTrue() throws Exception {
        var response = getRestTemplateWithAuth()
                .getForEntity(createUri(BARCODES_ROOT_PATH.concat(BARCODE.concat("/220000285994"))), Boolean.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(Boolean.TRUE, response.getBody());
    }

    @Test
    void checkIfValueExistsWhenFalse() throws Exception {
        var response = getRestTemplateWithAuth()
                .getForEntity(createUri(BARCODES_ROOT_PATH.concat(BARCODE.concat(BAD_ID_PATH))), Boolean.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(Boolean.FALSE, response.getBody());
    }
}