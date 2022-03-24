package ii.cipriantarlev.marketmanagementapi.subcategory;

import ii.cipriantarlev.marketmanagementapi.category.CategoryDTO;
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

class SubcategoryControllerIntegrationTest extends IntegrationTestConfiguration {

    private SubcategoryDTO subcategoryDTO;

    @BeforeEach
    void setUp() {
        subcategoryDTO = SubcategoryDTO.builder()
                .name("Test")
                .category(CategoryDTO.builder().id(GOOD_ID).name("Test").build())
                .build();
    }

    @Test
    void getSubcategories() throws Exception {
        HttpEntity<List<SubcategoryDTO>> entity = new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(SUBCATEGORIES_ROOT_PATH),
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<List<SubcategoryDTO>>() {
                        });

        assertEquals(3, Objects.requireNonNull(response.getBody()).size());
        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void getSubcategoriesByCategoryId() throws Exception {
        HttpEntity<List<SubcategoryDTONoCategory>> entity = new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(SUBCATEGORIES_ROOT_PATH.concat("/category").concat(GOOD_ID_PATH)),
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<List<SubcategoryDTONoCategory>>() {
                        });

        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void getSubcategoryWhenOk() throws Exception {
        var response = getRestTemplateWithAuth()
                .getForEntity(createUri(SUBCATEGORIES_ROOT_PATH.concat(GOOD_ID_PATH)),
                        SubcategoryDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(GOOD_ID, Objects.requireNonNull(response.getBody()).getId());
        assertEquals("Detergent de rufe", response.getBody().getName());
    }

    @Test
    void getSubcategoryWhenNotFound() throws Exception {
        assertThrows(RestClientException.class, throwExceptionWhenGet(SUBCATEGORIES_ROOT_PATH.concat(BAD_ID_PATH)));
    }

    @Test
    void createSubcategoryWhenOk() throws Exception {
        var response = getRestTemplateWithAuth()
                .postForEntity(createUri(SUBCATEGORIES_ROOT_PATH),
                        subcategoryDTO,
                        SubcategoryDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(4L, Objects.requireNonNull(response.getBody()).getId());
        assertEquals("Test", response.getBody().getName());
    }

    @Test
    void createSubcategoryWhenAlreadyExist() throws Exception {
        subcategoryDTO.setId(GOOD_ID);

        HttpEntity<SubcategoryDTO> entity = new HttpEntity<>(subcategoryDTO, new HttpHeaders());

        assertThrows(RestClientException.class, throwException(entity, SUBCATEGORIES_ROOT_PATH, HttpMethod.POST));
    }

    @Test
    void updateSubcategoryWhenOk() throws Exception {
        subcategoryDTO.setId(GOOD_ID);

        HttpEntity<SubcategoryDTO> entity = new HttpEntity<>(subcategoryDTO, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(SUBCATEGORIES_ROOT_PATH),
                        HttpMethod.PUT,
                        entity,
                        SubcategoryDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(GOOD_ID, Objects.requireNonNull(response.getBody()).getId());
        assertEquals("Test", response.getBody().getName());
    }

    @Test
    void updateSubcategoryWhenNotFound() throws Exception {
        subcategoryDTO.setId(BAD_ID);

        HttpEntity<SubcategoryDTO> entity = new HttpEntity<>(subcategoryDTO, new HttpHeaders());

        assertThrows(RestClientException.class, throwException(entity, SUBCATEGORIES_ROOT_PATH, HttpMethod.PUT));
    }

    @Test
    void deleteSubcategoryWhenOk() throws Exception {
        HttpEntity<SubcategoryDTO> entity = new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(SUBCATEGORIES_ROOT_PATH.concat(GOOD_ID_PATH)),
                        HttpMethod.DELETE,
                        entity,
                        SubcategoryDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void deleteSubcategoryWhenNotFound() throws Exception {
        HttpEntity<SubcategoryDTO> entity = new HttpEntity<>(null, new HttpHeaders());

        assertThrows(RestClientException.class,
                throwException(entity, SUBCATEGORIES_ROOT_PATH.concat(BAD_ID_PATH), HttpMethod.DELETE));
    }
}