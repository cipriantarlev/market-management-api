package ii.cipriantarlev.marketmanagementapi.myorganization;

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

class MyOrganizationControllerIntegrationTest extends IntegrationTestConfiguration {

    private MyOrganizationDTO myOrganizationDTO;

    @BeforeEach
    void setUp() {
        myOrganizationDTO = MyOrganizationDTO.builder()
                .name("Test")
                .bank("Test")
                .fiscalCode("123345")
                .bankAccount("1234")
                .vatCode("12335")
                .city("Test")
                .phoneNumber("222-222")
                .email("test@test.com")
                .build();
    }

    @Test
    void getMyOrganizations() throws Exception {
        HttpEntity<List<MyOrganizationDTO>> entity = new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(MY_ORGANIZATIONS_ROOT_PATH),
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<List<MyOrganizationDTO>>() {
                        });

        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void getMyOrganizationWhenOk() throws Exception {
        var response = getRestTemplateWithAuth()
                .getForEntity(createUri(MY_ORGANIZATIONS_ROOT_PATH.concat(GOOD_ID_PATH)),
                        MyOrganizationDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals("II Ciprian Tarlev", Objects.requireNonNull(response.getBody()).getName());
        assertEquals(GOOD_ID, response.getBody().getId());
    }

    @Test
    void getMyOrganizationWhenNotFound() throws Exception {
        assertThrows(RestClientException.class, throwExceptionWhenGet(MY_ORGANIZATIONS_ROOT_PATH.concat(BAD_ID_PATH)));
    }

    @Test
    void createMyOrganizationWhenOk() throws Exception {
        var response = getRestTemplateWithAuth()
                .postForEntity(createUri(MY_ORGANIZATIONS_ROOT_PATH),
                        myOrganizationDTO,
                        MyOrganizationDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(3L, Objects.requireNonNull(response.getBody()).getId());
        assertEquals("Test", response.getBody().getName());
    }

    @Test
    void createMyOrganizationWhenAlreadyExist() throws Exception {
        myOrganizationDTO.setId(GOOD_ID);

        HttpEntity<MyOrganizationDTO> entity = new HttpEntity<>(myOrganizationDTO, new HttpHeaders());

        assertThrows(RestClientException.class, throwException(entity, MY_ORGANIZATIONS_ROOT_PATH, HttpMethod.POST));
    }

    @Test
    void updateMyOrganizationWhenOk() throws Exception {
        myOrganizationDTO.setId(GOOD_ID);

        HttpEntity<MyOrganizationDTO> entity = new HttpEntity<>(myOrganizationDTO, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(MY_ORGANIZATIONS_ROOT_PATH),
                        HttpMethod.PUT,
                        entity,
                        MyOrganizationDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(GOOD_ID, Objects.requireNonNull(response.getBody()).getId());
        assertEquals("Test", response.getBody().getName());
    }

    @Test
    void updateMyOrganizationWhenNotFound() throws Exception {
        myOrganizationDTO.setId(BAD_ID);

        HttpEntity<MyOrganizationDTO> entity = new HttpEntity<>(myOrganizationDTO, new HttpHeaders());

        assertThrows(RestClientException.class, throwException(entity, MY_ORGANIZATIONS_ROOT_PATH, HttpMethod.PUT));
    }

    @Test
    void deletedMyOrganizationWhenOK() throws Exception {
        HttpEntity<MyOrganizationDTO> entity = new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(MY_ORGANIZATIONS_ROOT_PATH.concat(GOOD_ID_PATH)),
                        HttpMethod.DELETE,
                        entity,
                        MyOrganizationDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void deletedMyOrganizationWhenNotFound() throws Exception {
        HttpEntity<MyOrganizationDTO> entity = new HttpEntity<>(null, new HttpHeaders());

        assertThrows(RestClientException.class,
                throwException(entity, MY_ORGANIZATIONS_ROOT_PATH.concat(BAD_ID_PATH), HttpMethod.DELETE));
    }
}