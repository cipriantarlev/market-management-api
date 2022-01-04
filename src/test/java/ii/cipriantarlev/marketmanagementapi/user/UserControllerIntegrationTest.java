package ii.cipriantarlev.marketmanagementapi.user;

import ii.cipriantarlev.marketmanagementapi.config.IntegrationTestConfiguration;
import ii.cipriantarlev.marketmanagementapi.role.RoleDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientException;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static ii.cipriantarlev.marketmanagementapi.utils.Constants.*;

class UserControllerIntegrationTest extends IntegrationTestConfiguration {

    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        userDTO = UserDTO.builder()
                .username("test")
                .password("test")
                .roles(Collections.singletonList(RoleDTO.builder().id(1).role("ROLE_USER").build()))
                .email("email@email.com")
                .build();
    }

    @Test
    void getUsers() throws Exception {
        HttpEntity<List<UserDTO>> entity = new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(USERS_ROOT_PATH),
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<List<UserDTO>>() {
                        });

        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void getUserWhenOk() throws Exception {
        var response = getRestTemplateWithAuth()
                .getForEntity(USERS_ROOT_PATH.concat(GOOD_ID_PATH),
                        UserDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(GOOD_ID, Objects.requireNonNull(response.getBody()).getId());
        assertEquals("ciprian", response.getBody().getUsername());
    }

    @Test
    void getUserWhenNotFound() throws Exception {
        assertThrows(RestClientException.class, throwExceptionWhenGet(MY_ORGANIZATIONS_ROOT_PATH.concat(BAD_ID_PATH)));
    }

    @Test
    void createUserWhenOk() throws Exception {
        var response = getRestTemplateWithAuth()
                .postForEntity(createUri(USERS_ROOT_PATH),
                        userDTO,
                        UserDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(2L, Objects.requireNonNull(response.getBody()).getId());
        assertEquals("test", response.getBody().getUsername());
    }

    @Test
    void createUserWhenAlreadyExist() throws Exception {
        userDTO.setId(GOOD_ID);

        HttpEntity<UserDTO> entity = new HttpEntity<>(userDTO, new HttpHeaders());

        assertThrows(RestClientException.class, throwException(entity, USERS_ROOT_PATH, HttpMethod.POST));
    }

    @Test
    void updateUserWhenOk() throws Exception {
        userDTO.setId(GOOD_ID);

        HttpEntity<UserDTO> entity = new HttpEntity<>(userDTO, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(USERS_ROOT_PATH),
                        HttpMethod.PUT,
                        entity,
                        UserDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(GOOD_ID, Objects.requireNonNull(response.getBody()).getId());
        assertEquals("test", response.getBody().getUsername());
    }

    @Test
    void updateUserWhenNotFound() throws Exception {
        userDTO.setId(BAD_ID);

        HttpEntity<UserDTO> entity = new HttpEntity<>(userDTO, new HttpHeaders());

        assertThrows(RestClientException.class, throwException(entity, USERS_ROOT_PATH, HttpMethod.PUT));
    }

    @Test
    void deleteUserWhenOk() throws Exception {
        HttpEntity<UserDTO> entity = new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(USERS_ROOT_PATH.concat(GOOD_ID_PATH)),
                        HttpMethod.DELETE,
                        entity,
                        UserDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void deleteUserWhenNotFound() throws Exception {
        HttpEntity<UserDTO> entity = new HttpEntity<>(null, new HttpHeaders());

        assertThrows(RestClientException.class,
                throwException(entity, USERS_ROOT_PATH.concat(BAD_ID_PATH), HttpMethod.DELETE));
    }
}