package ii.cipriantarlev.marketmanagementapi.vendor;

import ii.cipriantarlev.marketmanagementapi.config.IntegrationTestConfiguration;
import ii.cipriantarlev.marketmanagementapi.region.RegionDTO;
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

class VendorControllerIntegrationTest extends IntegrationTestConfiguration {

    private VendorDTO vendorDTO;

    @BeforeEach
    void setUp() {
        vendorDTO = VendorDTO.builder()
                .name("Name")
                .bank("Bank")
                .fiscalCode("1205")
                .bankAccount("0051")
                .currency("MDL")
                .vatCode("0545")
                .city("BS")
                .region(RegionDTO.builder().id(1L).name("A3 s").build())
                .phoneNumber("00-55-")
                .postalCode("15d")
                .businessAddress("Strada")
                .vendorType("FD df")
                .vendorLegalType("dddd d")
                .build();
    }

    @Test
    void getVendors() throws Exception {
        HttpEntity<List<VendorDTONoRegions>> entity = new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(VENDORS_ROOT_PATH),
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<List<VendorDTONoRegions>>() {
                        });

        assertEquals(3, Objects.requireNonNull(response.getBody()).size());
        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void getVendorWhenOk() throws Exception {
        var response = getRestTemplateWithAuth()
                .getForEntity(createUri(VENDORS_ROOT_PATH.concat(GOOD_ID_PATH)),
                        VendorDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(GOOD_ID, Objects.requireNonNull(response.getBody()).getId());
        assertEquals("SRL Customagic", response.getBody().getName());
    }

    @Test
    void getVendorWhenNotFound() throws Exception {
        assertThrows(RestClientException.class, throwExceptionWhenGet(VENDORS_ROOT_PATH.concat(BAD_ID_PATH)));
    }

    @Test
    void createVendorWhenOk() throws Exception {
        var response = getRestTemplateWithAuth()
                .postForEntity(createUri(VENDORS_ROOT_PATH),
                        vendorDTO,
                        VendorDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(4L, Objects.requireNonNull(response.getBody()).getId());
        assertEquals("Name", response.getBody().getName());
    }

    @Test
    void createVendorWhenNotFound() throws Exception {
        vendorDTO.setId(GOOD_ID);

        HttpEntity<VendorDTO> entity = new HttpEntity<>(vendorDTO, new HttpHeaders());

        assertThrows(RestClientException.class, throwException(entity, VENDORS_ROOT_PATH, HttpMethod.POST));
    }

    @Test
    void updateVendorWhenOk() throws Exception {
        vendorDTO.setId(GOOD_ID);

        HttpEntity<VendorDTO> entity = new HttpEntity<>(vendorDTO, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(VENDORS_ROOT_PATH),
                        HttpMethod.PUT,
                        entity,
                        VendorDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(GOOD_ID, Objects.requireNonNull(response.getBody()).getId());
        assertEquals("Name", response.getBody().getName());
    }

    @Test
    void updateVendorWhenNotFound() throws Exception {
        vendorDTO.setId(BAD_ID);

        HttpEntity<VendorDTO> entity = new HttpEntity<>(vendorDTO, new HttpHeaders());

        assertThrows(RestClientException.class, throwException(entity, VENDORS_ROOT_PATH, HttpMethod.PUT));
    }

    @Test
    void deleteVendorWhenOk() throws Exception {
        HttpEntity<VendorDTO> entity = new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(VENDORS_ROOT_PATH.concat(GOOD_ID_PATH)),
                        HttpMethod.DELETE,
                        entity,
                        VendorDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void deleteVendorWhenNotFound() throws Exception {
        HttpEntity<VendorDTO> entity = new HttpEntity<>(null, new HttpHeaders());

        assertThrows(RestClientException.class,
                throwException(entity, VENDORS_ROOT_PATH.concat(BAD_ID_PATH), HttpMethod.DELETE));
    }
}