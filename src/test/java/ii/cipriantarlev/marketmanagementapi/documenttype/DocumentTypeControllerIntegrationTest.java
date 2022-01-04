package ii.cipriantarlev.marketmanagementapi.documenttype;

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

class DocumentTypeControllerIntegrationTest extends IntegrationTestConfiguration {

    private DocumentTypeDTO documentTypeDTO;

    @BeforeEach
    void setUp() {
        documentTypeDTO = DocumentTypeDTO.builder().name("Test").build();
    }

    @Test
    void getDocumentTypeList() throws Exception {
        HttpEntity<List<DocumentTypeDTO>> entity = new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(DOCUMENT_TYPE_ROOT_PATH),
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<List<DocumentTypeDTO>>() {
                        });

        assertEquals(3, Objects.requireNonNull(response.getBody()).size());
        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void getDocumentTypeWhenOk() throws Exception {
        var response = getRestTemplateWithAuth()
                .getForEntity(createUri(DOCUMENT_TYPE_ROOT_PATH.concat(GOOD_ID_PATH)), DocumentTypeDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals("Income Invoice", Objects.requireNonNull(response.getBody()).getName());
        assertEquals(GOOD_ID, response.getBody().getId());
    }

    @Test
    void getDocumentTypeWhenNotFound() throws Exception {
        assertThrows(RestClientException.class, throwExceptionWhenGet(DOCUMENT_TYPE_ROOT_PATH.concat(BAD_ID_PATH)));
    }

    @Test
    void createDocumentTypeWhenOk() throws Exception {
        var response = getRestTemplateWithAuth()
                .postForEntity(createUri(DOCUMENT_TYPE_ROOT_PATH), documentTypeDTO, DocumentTypeDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(4L, Objects.requireNonNull(response.getBody()).getId());
        assertEquals("Test", response.getBody().getName());
    }

    @Test
    void createDocumentTypeWhenAlreadyExist() throws Exception {
        documentTypeDTO.setId(GOOD_ID);

        HttpEntity<DocumentTypeDTO> entity = new HttpEntity<>(documentTypeDTO, new HttpHeaders());

        assertThrows(RestClientException.class, throwException(entity, DOCUMENT_TYPE_ROOT_PATH, HttpMethod.POST));
    }

    @Test
    void updateDocumentTypeWhenOk() throws Exception {
        documentTypeDTO.setId(GOOD_ID);

        HttpEntity<DocumentTypeDTO> entity = new HttpEntity<>(documentTypeDTO, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(DOCUMENT_TYPE_ROOT_PATH),
                        HttpMethod.PUT,
                        entity,
                        DocumentTypeDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(GOOD_ID, Objects.requireNonNull(response.getBody()).getId());
        assertEquals("Test", response.getBody().getName());
    }

    @Test
    void updateDocumentTypeWhenNotFound() throws Exception {
        documentTypeDTO.setId(BAD_ID);

        HttpEntity<DocumentTypeDTO> entity = new HttpEntity<>(documentTypeDTO, new HttpHeaders());

        assertThrows(RestClientException.class, throwException(entity, DOCUMENT_TYPE_ROOT_PATH, HttpMethod.PUT));
    }

    @Test
    void deleteDocumentTypeWhenOk() throws Exception {
        HttpEntity<DocumentTypeDTO> entity = new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(DOCUMENT_TYPE_ROOT_PATH.concat(GOOD_ID_PATH)),
                        HttpMethod.DELETE,
                        entity,
                        DocumentTypeDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void deleteDocumentTypeWhenNotFound() throws Exception {
        HttpEntity<DocumentTypeDTO> entity = new HttpEntity<>(null, new HttpHeaders());

        assertThrows(RestClientException.class, throwException(entity, DOCUMENT_TYPE_ROOT_PATH.concat(BAD_ID_PATH), HttpMethod.DELETE));
    }
}