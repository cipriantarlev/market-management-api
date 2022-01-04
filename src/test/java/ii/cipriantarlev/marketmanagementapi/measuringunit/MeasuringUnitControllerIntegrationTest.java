package ii.cipriantarlev.marketmanagementapi.measuringunit;

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

class MeasuringUnitControllerIntegrationTest extends IntegrationTestConfiguration {

    private MeasuringUnitDTO measuringUnitDTO;

    @BeforeEach
    void setUp() {
        measuringUnitDTO = MeasuringUnitDTO.builder().name("test").build();
    }

    @Test
    void getMeasuringUnits() throws Exception {
        HttpEntity<List<MeasuringUnitDTO>> entity = new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(MEASURING_UNITS_ROOT_PATH),
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<List<MeasuringUnitDTO>>() {
                        });

        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void getMeasuringUnitWhenOk() throws Exception {
        var response = getRestTemplateWithAuth()
                .getForEntity(createUri(MEASURING_UNITS_ROOT_PATH.concat(GOOD_ID_PATH)),
                        MeasuringUnitDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals("kg", Objects.requireNonNull(response.getBody()).getName());
        assertEquals(GOOD_ID, response.getBody().getId());
    }

    @Test
    void getMeasuringUnitWhenNotFound() throws Exception {
        assertThrows(RestClientException.class, throwExceptionWhenGet(MEASURING_UNITS_ROOT_PATH.concat(BAD_ID_PATH)));
    }

    @Test
    void createMeasuringUnitWhenOk() throws Exception {
        var response = getRestTemplateWithAuth()
                .postForEntity(createUri(MEASURING_UNITS_ROOT_PATH),
                        measuringUnitDTO,
                        MeasuringUnitDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(3L, Objects.requireNonNull(response.getBody()).getId());
        assertEquals("test", response.getBody().getName());
    }

    @Test
    void createMeasuringUnitWhenAlreadyExist() throws Exception {
        measuringUnitDTO.setId(GOOD_ID);

        HttpEntity<MeasuringUnitDTO> entity = new HttpEntity<>(measuringUnitDTO, new HttpHeaders());

        assertThrows(RestClientException.class, throwException(entity, MEASURING_UNITS_ROOT_PATH, HttpMethod.POST));
    }

    @Test
    void updateMeasuringUnitWhenOk() throws Exception {
        measuringUnitDTO.setId(GOOD_ID);

        HttpEntity<MeasuringUnitDTO> entity = new HttpEntity<>(measuringUnitDTO, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(MEASURING_UNITS_ROOT_PATH),
                        HttpMethod.PUT,
                        entity,
                        MeasuringUnitDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(GOOD_ID, Objects.requireNonNull(response.getBody()).getId());
        assertEquals("test", response.getBody().getName());
    }

    @Test
    void updateMeasuringUnitWhenNotFound() throws Exception {
        measuringUnitDTO.setId(BAD_ID);

        HttpEntity<MeasuringUnitDTO> entity = new HttpEntity<>(measuringUnitDTO, new HttpHeaders());

        assertThrows(RestClientException.class, throwException(entity, MEASURING_UNITS_ROOT_PATH, HttpMethod.PUT));
    }

    @Test
    void deleteMeasuringUnitWhenOk() throws Exception {
        HttpEntity<MeasuringUnitDTO> entity = new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(MEASURING_UNITS_ROOT_PATH.concat(GOOD_ID_PATH)),
                        HttpMethod.DELETE,
                        entity,
                        MeasuringUnitDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void deleteMeasuringUnitWhenNotFound() throws Exception {
        HttpEntity<MeasuringUnitDTO> entity = new HttpEntity<>(null, new HttpHeaders());

        assertThrows(RestClientException.class,
                throwException(entity, MEASURING_UNITS_ROOT_PATH.concat(BAD_ID_PATH), HttpMethod.DELETE));
    }
}