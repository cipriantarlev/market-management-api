package ii.cipriantarlev.marketmanagementapi.pricechangingact;

import ii.cipriantarlev.marketmanagementapi.config.IntegrationTestConfiguration;
import ii.cipriantarlev.marketmanagementapi.myorganization.MyOrganizationDTOOnlyName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static ii.cipriantarlev.marketmanagementapi.utils.Constants.*;

class PriceChangingActControllerIntegrationTest extends IntegrationTestConfiguration {

    private PriceChangingActDTO priceChangingActDTO;

    @BeforeEach
    void setUp() {
        priceChangingActDTO = PriceChangingActDTO.builder()
                .dateCreated(LocalDate.now())
                .myOrganization(MyOrganizationDTOOnlyName.builder().id(GOOD_ID).build())
                .oldPrices(BigDecimal.valueOf(12.00))
                .newPrices(BigDecimal.valueOf(12.00))
                .pricesDifference(BigDecimal.valueOf(12.00))
                .build();
    }

    @Test
    void getPriceChangingActs() throws Exception {
        HttpEntity<List<PriceChangingActDTO>> entity = new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(PRICE_CHANGING_ACTS_ROOT_PATH),
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<List<PriceChangingActDTO>>() {
                        });

        assertEquals(3, Objects.requireNonNull(response.getBody()).size());
        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void getPriceChangingActWhenOk() throws Exception {
        var response = getRestTemplateWithAuth()
                .getForEntity(createUri(PRICE_CHANGING_ACTS_ROOT_PATH.concat(GOOD_UUID_PATH)), PriceChangingActDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(GOOD_UUID, Objects.requireNonNull(response.getBody()).getId());
        assertEquals(BigDecimal.valueOf(65.96), response.getBody().getNewPrices());
    }

    @Test
    void getPriceChangingActWhenNotFound() throws Exception {
        assertThrows(RestClientException.class, throwExceptionWhenGet(PRICE_CHANGING_ACTS_ROOT_PATH.concat(BAD_UUID_PATH)));
    }

    @Test
    void createPriceChangingAct() throws Exception {
        var response = getRestTemplateWithAuth()
                .postForEntity(createUri(PRICE_CHANGING_ACTS_ROOT_PATH),
                        priceChangingActDTO,
                        PriceChangingActDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(BigDecimal.valueOf(12.00), Objects.requireNonNull(response.getBody()).getOldPrices());
    }

    @Test
    void createPriceChangingActWhenAlreadyExist() throws Exception {
        priceChangingActDTO.setId(GOOD_UUID);

        HttpEntity<PriceChangingActDTO> entity = new HttpEntity<>(priceChangingActDTO, new HttpHeaders());

        assertThrows(RestClientException.class, throwException(entity, PRICE_CHANGING_ACTS_ROOT_PATH, HttpMethod.POST));
    }

    @Test
    void updatePriceChangingAct() throws Exception {
        priceChangingActDTO.setId(GOOD_UUID);
        HttpEntity<PriceChangingActDTO> entity = new HttpEntity<>(priceChangingActDTO, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(PRICE_CHANGING_ACTS_ROOT_PATH),
                        HttpMethod.PUT,
                        entity,
                        PriceChangingActDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(GOOD_UUID, Objects.requireNonNull(response.getBody()).getId());
        assertEquals(BigDecimal.valueOf(12.00), Objects.requireNonNull(response.getBody()).getOldPrices());
    }

    @Test
    void updatePriceChangingActWhenNotFound() throws Exception {
        priceChangingActDTO.setId(BAD_UUID);

        HttpEntity<PriceChangingActDTO> entity = new HttpEntity<>(priceChangingActDTO, new HttpHeaders());

        assertThrows(RestClientException.class, throwException(entity, PRICE_CHANGING_ACTS_ROOT_PATH, HttpMethod.PUT));
    }

    @Test
    void updateApprovedMarker() throws Exception {
        Map<Boolean, List<UUID>> priceChangingActs = Collections.singletonMap(true, Collections.singletonList(GOOD_UUID));
        HttpEntity<Map<Boolean, List<UUID>>> entity = new HttpEntity<>(priceChangingActs, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(PRICE_CHANGING_ACTS_ROOT_PATH.concat(IS_APPROVED)),
                        HttpMethod.PUT,
                        entity,
                        Integer.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(1, response.getBody());
    }

    @Test
    void updateApprovedMarkerWhenNotFound() throws Exception {
        Map<Boolean, List<UUID>> priceChangingActs = Collections.singletonMap(true, Collections.singletonList(GOOD_UUID));
        HttpEntity<Map<Boolean, List<UUID>>> entity = new HttpEntity<>(priceChangingActs, new HttpHeaders());

        assertThrows(RestClientException.class,
                throwException(
                        entity,
                        PRICE_CHANGING_ACTS_ROOT_PATH.concat(IS_APPROVED),
                        HttpMethod.PUT));
    }

    @Test
    void deletePriceActChanging() throws Exception {
        HttpEntity<PriceChangingActDTO> entity = new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(PRICE_CHANGING_ACTS_ROOT_PATH.concat(GOOD_UUID_PATH)),
                        HttpMethod.DELETE,
                        entity,
                        PriceChangingActDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void deletePriceActChangingWhenNotFound() throws Exception {
        HttpEntity<PriceChangingActDTO> entity = new HttpEntity<>(null, new HttpHeaders());

        assertThrows(RestClientException.class,
                throwException(entity, PRICE_CHANGING_ACTS_ROOT_PATH.concat(BAD_UUID_PATH),HttpMethod.DELETE));
    }
}