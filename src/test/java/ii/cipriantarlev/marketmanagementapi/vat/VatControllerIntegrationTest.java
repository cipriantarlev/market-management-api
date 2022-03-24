package ii.cipriantarlev.marketmanagementapi.vat;

import ii.cipriantarlev.marketmanagementapi.config.IntegrationTestConfiguration;
import org.junit.jupiter.api.BeforeEach;
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

class VatControllerIntegrationTest extends IntegrationTestConfiguration {

    private VatDTO vatDTO;

    @BeforeEach
    void setUp() {
        vatDTO = VatDTO.builder()
                .value(13)
                .name("13%")
                .build();
    }

    @Test
    void getVatList() throws Exception {
        HttpEntity<List<VatDTO>> entity = new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(VAT_ROOT_PATH),
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<List<VatDTO>>() {
                        });

        assertEquals(3, Objects.requireNonNull(response.getBody()).size());
        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void getVatByIdWhenOk() throws Exception {
        var response = getRestTemplateWithAuth()
                .getForEntity(createUri(VAT_ROOT_PATH.concat(GOOD_ID_PATH)),
                        VatDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(GOOD_ID, Objects.requireNonNull(response.getBody()).getId());
        assertEquals(20, response.getBody().getValue());
    }

    @Test
    void getVatByIdWhenNotFound() throws Exception {
        assertThrows(RestClientException.class, throwExceptionWhenGet(VAT_ROOT_PATH.concat(BAD_ID_PATH)));
    }

    @Test
    void createVatWhenOk() throws Exception {
        var response = getRestTemplateWithAuth()
                .postForEntity(createUri(VAT_ROOT_PATH),
                        vatDTO,
                        VatDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(4L, Objects.requireNonNull(response.getBody()).getId());
        assertEquals(13, response.getBody().getValue());
    }

    @Test
    void createVatWhenNotFound() throws Exception {
        vatDTO.setId(GOOD_ID);

        HttpEntity<VatDTO> entity = new HttpEntity<>(vatDTO, new HttpHeaders());

        assertThrows(RestClientException.class, throwException(entity, VAT_ROOT_PATH, HttpMethod.POST));
    }

    @Test
    void updateDTOWhenOk() throws Exception {
        vatDTO.setId(GOOD_ID);

        HttpEntity<VatDTO> entity = new HttpEntity<>(vatDTO, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(VAT_ROOT_PATH),
                        HttpMethod.PUT,
                        entity,
                        VatDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(GOOD_ID, Objects.requireNonNull(response.getBody()).getId());
        assertEquals(13, response.getBody().getValue());
    }

    @Test
    void updateDTOWhenNotFound() throws Exception {
        vatDTO.setId(BAD_ID);

        HttpEntity<VatDTO> entity = new HttpEntity<>(vatDTO, new HttpHeaders());

        assertThrows(RestClientException.class, throwException(entity, VAT_ROOT_PATH, HttpMethod.PUT));
    }

    @Test
    void deleteVatWhenOk() throws Exception {
        HttpEntity<VatDTO> entity = new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(VAT_ROOT_PATH.concat(GOOD_ID_PATH)),
                        HttpMethod.DELETE,
                        entity,
                        VatDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void deleteVatWhenNotFound() throws Exception {
        HttpEntity<VatDTO> entity = new HttpEntity<>(null, new HttpHeaders());

        assertThrows(RestClientException.class,
                throwException(entity, VAT_ROOT_PATH.concat(BAD_ID_PATH), HttpMethod.DELETE));
    }
}