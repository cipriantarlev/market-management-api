package ii.cipriantarlev.marketmanagementapi.documenttype;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class DocumentTypeMapperTest {

    @InjectMocks
    private DocumentTypeMapper documentTypeMapper;

    @Mock
    private ModelMapper modelMapper;

    private DocumentType documentType;
    private DocumentTypeDTO documentTypeDTO;

    @BeforeEach
    void setUp() {
        openMocks(this);
        documentTypeDTO = DocumentTypeDTO.builder().id(1L).name("Test").build();
        documentType = new DocumentType();
        documentType.setId(1L);
        documentType.setName("Test");
    }

    @Test
    void mapEntityToDTO() throws Exception {
        when(modelMapper.map(documentType, DocumentTypeDTO.class)).thenReturn(documentTypeDTO);

        var resultedDocumentTypeDTO = documentTypeMapper.mapEntityToDTO(documentType);

        verify(modelMapper).map(documentType, DocumentTypeDTO.class);
        assertEquals(documentTypeDTO, resultedDocumentTypeDTO);
    }

    @Test
    void mapDTOToEntity() throws Exception {
        when(modelMapper.map(documentTypeDTO, DocumentType.class)).thenReturn(documentType);

        var resultedDocumentType = documentTypeMapper.mapDTOToEntity(documentTypeDTO);

        verify(modelMapper).map(documentTypeDTO, DocumentType.class);
        assertEquals(documentType, resultedDocumentType);
    }
}