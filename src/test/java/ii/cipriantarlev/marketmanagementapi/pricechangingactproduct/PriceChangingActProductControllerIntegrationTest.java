package ii.cipriantarlev.marketmanagementapi.pricechangingactproduct;

import ii.cipriantarlev.marketmanagementapi.config.IntegrationTestConfiguration;
import ii.cipriantarlev.marketmanagementapi.myorganization.MyOrganizationDTOOnlyName;
import ii.cipriantarlev.marketmanagementapi.pricechangingact.PriceChangingActDTO;
import ii.cipriantarlev.marketmanagementapi.product.ProductDTOForList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static ii.cipriantarlev.marketmanagementapi.utils.Constants.*;

class PriceChangingActProductControllerIntegrationTest extends IntegrationTestConfiguration  {

    private PriceChangingActProductDTO priceChangingActProductDTO;

    private final UUID expectedId = UUID.fromString("5fd843b1-f782-4de4-bf94-112b3790ec7f");

    @BeforeEach
    void setUp() {
        priceChangingActProductDTO = PriceChangingActProductDTO.builder()
                .priceChangingAct(PriceChangingActDTO.builder()
                        .id(GOOD_UUID)
                        .dateCreated(LocalDate.now())
                        .myOrganization(MyOrganizationDTOOnlyName.builder().id(GOOD_ID).build())
                        .build())
                .product(ProductDTOForList.builder().id(GOOD_ID).build())
                .oldPrice(BigDecimal.TEN)
                .priceDifference(BigDecimal.ONE)
                .build();
    }

    @Test
    void getPriceChangingActProducts() throws Exception {
        HttpEntity<List<PriceChangingActProductDTO>> entity = new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(PRICE_CHANGING_ACT_PRODUCTS_ROOT_PATH.concat("/price-changing-act/5fd843b1-f782-4134-bf94-112b3790ec7f")),
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<List<PriceChangingActProductDTO>>() {
                        });

        assertEquals(3, Objects.requireNonNull(response.getBody()).size());
        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void getPriceChangingActProductWhenOk() throws Exception {
        var response = getRestTemplateWithAuth()
                .getForEntity(createUri(PRICE_CHANGING_ACT_PRODUCTS_ROOT_PATH.concat("/"+ expectedId)),
                        PriceChangingActProductDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(expectedId, Objects.requireNonNull(response.getBody()).getId());
        assertEquals(BigDecimal.valueOf(65.96), response.getBody().getOldPrice());
    }

    @Test
    void getPriceChangingActProductWhenNotFound() throws Exception {
        assertThrows(RestClientException.class, throwExceptionWhenGet(PRICE_CHANGING_ACT_PRODUCTS_ROOT_PATH.concat(BAD_UUID_PATH)));
    }

    @Test
    void createPriceChangingActProduct() throws Exception {
        var response = getRestTemplateWithAuth()
                .postForEntity(createUri(PRICE_CHANGING_ACT_PRODUCTS_ROOT_PATH),
                        priceChangingActProductDTO,
                        PriceChangingActProductDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(BigDecimal.TEN, Objects.requireNonNull(response.getBody()).getOldPrice());
    }

    @Test
    void createPriceChangingActProductWhenAlreadyExist() throws Exception {
        priceChangingActProductDTO.setId(expectedId);

        HttpEntity<PriceChangingActProductDTO> entity = new HttpEntity<>(priceChangingActProductDTO, new HttpHeaders());

        assertThrows(RestClientException.class, throwException(entity, PRICE_CHANGING_ACT_PRODUCTS_ROOT_PATH, HttpMethod.POST));
    }

    @Test
    void updatePriceChangingActProductWhenOk() throws Exception {
        priceChangingActProductDTO.setId(expectedId);

        HttpEntity<PriceChangingActProductDTO> entity = new HttpEntity<>(priceChangingActProductDTO, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(PRICE_CHANGING_ACT_PRODUCTS_ROOT_PATH),
                        HttpMethod.PUT,
                        entity,
                        PriceChangingActProductDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(expectedId, Objects.requireNonNull(response.getBody()).getId());
        assertEquals(BigDecimal.TEN, response.getBody().getOldPrice());
    }

    @Test
    void updatePriceChangingActProductWhenNotFound() throws Exception {
        priceChangingActProductDTO.setId(BAD_UUID);

        HttpEntity<PriceChangingActProductDTO> entity = new HttpEntity<>(priceChangingActProductDTO, new HttpHeaders());

        assertThrows(RestClientException.class, throwException(entity, PRICE_CHANGING_ACT_PRODUCTS_ROOT_PATH, HttpMethod.PUT));
    }

    @Test
    void deletePriceChangingActProductWhenOk() throws Exception {
        HttpEntity<PriceChangingActProductDTO> entity = new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(PRICE_CHANGING_ACT_PRODUCTS_ROOT_PATH.concat("/"+ expectedId)),
                        HttpMethod.DELETE,
                        entity,
                        PriceChangingActProductDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void deletePriceChangingActProductWhenNotFound() throws Exception {
        HttpEntity<PriceChangingActProductDTO> entity = new HttpEntity<>(null, new HttpHeaders());

        assertThrows(RestClientException.class,
                throwException(entity, PRICE_CHANGING_ACT_PRODUCTS_ROOT_PATH.concat(BAD_UUID_PATH),HttpMethod.DELETE));
    }
}