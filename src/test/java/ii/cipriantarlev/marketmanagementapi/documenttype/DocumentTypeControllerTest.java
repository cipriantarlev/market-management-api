package ii.cipriantarlev.marketmanagementapi.documenttype;

import ii.cipriantarlev.marketmanagementapi.utils.RestControllerUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpHeaders;

import java.util.Collections;
import java.util.List;

import static ii.cipriantarlev.marketmanagementapi.utils.Constants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class DocumentTypeControllerTest {

    @InjectMocks
    private DocumentTypeController controller;

    @Mock
    private DocumentTypeService service;

    @Mock
    private RestControllerUtil restControllerUtil;

    private final long id = 1L;
    private final int ok = 200;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void getDocumentTypeList() throws Exception {
        List<DocumentTypeDTO> documentTypeList = Collections.singletonList(new DocumentTypeDTO());

        when(service.findAll()).thenReturn(documentTypeList);

        var response = controller.getDocumentTypeList();

        assertEquals(documentTypeList, response.getBody());
        assertEquals(ok, response.getStatusCodeValue());
        verify(service).findAll();
    }

    @Test
    void getDocumentType() throws Exception {
        DocumentTypeDTO documentTypeDTO = DocumentTypeDTO.builder().id(id).build();

        when(service.findById(id)).thenReturn(documentTypeDTO);

        var response = controller.getDocumentType(id);

        assertEquals(documentTypeDTO, response.getBody());
        assertEquals(ok, response.getStatusCodeValue());
        verify(service).findById(id);
    }

    @Test
    void createDocumentType() throws Exception {
        DocumentTypeDTO documentTypeDTO = DocumentTypeDTO.builder().name("Test").build();
        DocumentTypeDTO savedDocumentTypeDTO = DocumentTypeDTO.builder().id(id).name("Test").build();

        when(service.save(documentTypeDTO)).thenReturn(savedDocumentTypeDTO);
        when(restControllerUtil.setHttpsHeaderLocation(DOCUMENT_TYPE_ROOT_PATH, savedDocumentTypeDTO.getId()))
                .thenReturn(new HttpHeaders());

        var response = controller.createDocumentType(documentTypeDTO);

        assertEquals(savedDocumentTypeDTO, response.getBody());
        assertEquals(ok, response.getStatusCodeValue());
        verify(service).save(documentTypeDTO);
        verify(restControllerUtil).setHttpsHeaderLocation(DOCUMENT_TYPE_ROOT_PATH, savedDocumentTypeDTO.getId());
    }

    @Test
    void updateDocumentType() throws Exception {
        DocumentTypeDTO documentTypeDTO = DocumentTypeDTO.builder().id(id).name("Test").build();

        when(service.update(documentTypeDTO)).thenReturn(documentTypeDTO);

        var response = controller.updateDocumentType(documentTypeDTO);

        assertEquals(documentTypeDTO, response.getBody());
        assertEquals(ok, response.getStatusCodeValue());
        verify(service).update(documentTypeDTO);
    }

    @Test
    void deleteDocumentType() throws Exception {
        doNothing().when(service).deleteById(id);

        var response = controller.deleteDocumentType(id);

        assertEquals(ok, response.getStatusCodeValue());
        verify(service).deleteById(id);
    }
}