package ii.cipriantarlev.marketmanagementapi.plu;

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

class PluControllerIntegrationTest extends IntegrationTestConfiguration {

    @Test
    void getPluList() throws Exception {
        HttpEntity<List<PluDTO>> entity = new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(PLU_ROOT_PATH),
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<List<PluDTO>>() {
                        });

        assertEquals(3, Objects.requireNonNull(response.getBody()).size());
        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void getPluWhenOk() throws Exception {
        var response = getRestTemplateWithAuth()
                .getForEntity(createUri(PLU_ROOT_PATH.concat(GOOD_ID_PATH)),
                        Plu.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(1, Objects.requireNonNull(response.getBody()).getValue());
        assertEquals(GOOD_ID, response.getBody().getId());
    }

    @Test
    void getPluWhenNotFound() throws Exception {
        assertThrows(RestClientException.class, throwExceptionWhenGet(PLU_ROOT_PATH.concat(BAD_ID_PATH)));
    }

    @Test
    void generateNewPlu() throws Exception {
        var response = getRestTemplateWithAuth()
                .postForEntity(createUri(PLU_ROOT_PATH),
                        null,
                        PluDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(4L, Objects.requireNonNull(response.getBody()).getId());
        assertEquals(4, response.getBody().getValue());
    }

    @Test
    void deletePluWhenOk() throws Exception {
        HttpEntity<PluDTO> entity = new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(PLU_ROOT_PATH.concat(GOOD_ID_PATH)),
                        HttpMethod.DELETE,
                        entity,
                        PluDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void deletePluWhenNotFound() throws Exception {
        HttpEntity<PluDTO> entity = new HttpEntity<>(null, new HttpHeaders());

        assertThrows(RestClientException.class,
                throwException(entity, PLU_ROOT_PATH.concat(BAD_ID_PATH), HttpMethod.DELETE));
    }
}