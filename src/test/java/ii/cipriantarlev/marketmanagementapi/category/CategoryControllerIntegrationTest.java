package ii.cipriantarlev.marketmanagementapi.category;

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

class CategoryControllerIntegrationTest extends IntegrationTestConfiguration {

    private CategoryDTO categoryDTO;

    @BeforeEach
    void setUp() {
        categoryDTO = CategoryDTO.builder().name("Test").build();
    }

    @Test
    void getCategories() throws Exception {
        HttpEntity<List<CategoryDTO>> entity = new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(CATEGORIES_ROOT_PATH),
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<List<CategoryDTO>>() {
                        });

        assertEquals(3, Objects.requireNonNull(response.getBody()).size());
        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void getCategoryWhenStatusOk() throws Exception {
        var response = getRestTemplateWithAuth()
                .getForEntity(createUri(CATEGORIES_ROOT_PATH.concat(GOOD_ID_PATH)), CategoryDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals("Chimie", Objects.requireNonNull(response.getBody()).getName());
        assertEquals(GOOD_ID, response.getBody().getId());
    }

    @Test
    void getCategoryWhenStatusNotFound() throws Exception {
        assertThrows(RestClientException.class, throwExceptionWhenGet(CATEGORIES_ROOT_PATH.concat(BAD_ID_PATH)));
    }

    @Test
    void createCategoryWhenOk() throws Exception {
        var response = getRestTemplateWithAuth()
                .postForEntity(createUri(CATEGORIES_ROOT_PATH), categoryDTO, CategoryDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(4L, Objects.requireNonNull(response.getBody()).getId());
        assertEquals("Test", response.getBody().getName());
    }

    @Test
    void createCategoryWhenAlreadyExist() throws Exception {
        categoryDTO.setId(GOOD_ID);

        HttpEntity<CategoryDTO> entity = new HttpEntity<>(categoryDTO, new HttpHeaders());

        assertThrows(RestClientException.class, throwException(entity, CATEGORIES_ROOT_PATH, HttpMethod.POST));
    }

    @Test
    void updateCategoryWhenOk() throws Exception {
        categoryDTO.setId(GOOD_ID);;

        HttpEntity<CategoryDTO> entity = new HttpEntity<>(categoryDTO, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(CATEGORIES_ROOT_PATH),
                        HttpMethod.PUT,
                        entity,
                        CategoryDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(GOOD_ID, Objects.requireNonNull(response.getBody()).getId());
        assertEquals("Test", response.getBody().getName());
    }

    @Test
    void updateCategoryWhenNotFound() throws Exception {
        categoryDTO.setId(BAD_ID);

        HttpEntity<CategoryDTO> entity = new HttpEntity<>(categoryDTO, new HttpHeaders());

        assertThrows(RestClientException.class, throwException(entity, CATEGORIES_ROOT_PATH, HttpMethod.PUT));
    }

    @Test
    void deleteCategoryWhenOk() throws Exception {
        HttpEntity<CategoryDTO> entity = new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(CATEGORIES_ROOT_PATH.concat(GOOD_ID_PATH)),
                        HttpMethod.DELETE,
                        entity,
                        CategoryDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void deleteCategoryWhenNotFound() throws Exception {
        HttpEntity<CategoryDTO> entity = new HttpEntity<>(null, new HttpHeaders());

        assertThrows(RestClientException.class, throwException(entity, CATEGORIES_ROOT_PATH.concat(BAD_ID_PATH), HttpMethod.DELETE));
    }
}