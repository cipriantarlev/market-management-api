package ii.cipriantarlev.marketmanagementapi.documenttype;

import ii.cipriantarlev.marketmanagementapi.exceptions.DTOFoundWhenSaveException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTOListNotFoundException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTONotFoundException;
import ii.cipriantarlev.marketmanagementapi.history.EntitiesHistoryService;
import ii.cipriantarlev.marketmanagementapi.history.HistoryAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class DocumentTypeServiceTest {

    @InjectMocks
    private DocumentTypeServiceImpl documentTypeService;

    @Mock
    private DocumentTypeRepository documentTypeRepository;

    @Mock
    private DocumentTypeMapper documentTypeMapper;

    @Mock
    private EntitiesHistoryService entitiesHistoryService;

    private DocumentTypeDTO documentTypeDTO;
    private final long id = 1L;
    private final DocumentType documentType = new DocumentType();

    @BeforeEach
    void setUp() {
        openMocks(this);
        documentTypeDTO = DocumentTypeDTO.builder().id(id).name("Test").build();
    }

    @Test
    void findAll() throws Exception {
        List<DocumentType> documentTypeList = Collections.singletonList(documentType);

        when(documentTypeRepository.findAll()).thenReturn(documentTypeList);
        when(documentTypeMapper.mapEntityToDTO(documentTypeList.get(0))).thenReturn(new DocumentTypeDTO());

        var documentTypeDTOList = documentTypeService.findAll();

        verify(documentTypeRepository).findAll();
        verify(documentTypeMapper).mapEntityToDTO(documentTypeList.get(0));
        assertFalse(documentTypeDTOList.isEmpty());
    }

    @Test
    void findAllWhenEmptyList() throws Exception {
        when(documentTypeRepository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(DTOListNotFoundException.class, () -> documentTypeService.findAll());
        verify(documentTypeRepository).findAll();
    }

    @Test
    void findById() throws Exception {
        var documentTypeOptional = Optional.of(documentType);

        when(documentTypeRepository.findById(id)).thenReturn(documentTypeOptional);
        when(documentTypeMapper.mapEntityToDTO(documentTypeOptional.get())).thenReturn(documentTypeDTO);

        var documentTypeDTO = documentTypeService.findById(id);

        verify(documentTypeRepository).findById(id);
        verify(documentTypeMapper).mapEntityToDTO(documentTypeOptional.get());
        assertNotNull(documentTypeDTO);
        assertEquals(id, documentTypeDTO.getId());
    }

    @Test
    void findByIdWhenNotFound() throws Exception {
        when(documentTypeRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> documentTypeService.findById(id));
        verify(documentTypeRepository).findById(id);
    }

    @Test
    void save() throws Exception {
        when(documentTypeRepository.findById(documentTypeDTO.getId())).thenReturn(Optional.empty());
        when(documentTypeMapper.mapDTOToEntity(documentTypeDTO)).thenReturn(documentType);
        when(documentTypeRepository.save(documentType)).thenReturn(documentType);
        doNothing().when(entitiesHistoryService).createEntityHistoryRecord(documentType, null, HistoryAction.CREATE);
        when(documentTypeMapper.mapEntityToDTO(documentType)).thenReturn(documentTypeDTO);

        var savedDocumentTypeDTO = documentTypeService.save(documentTypeDTO);

        verify(documentTypeRepository).findById(documentTypeDTO.getId());
        verify(documentTypeMapper).mapDTOToEntity(documentTypeDTO);
        verify(documentTypeRepository).save(documentType);
        verify(entitiesHistoryService).createEntityHistoryRecord(documentType, null, HistoryAction.CREATE);
        verify(documentTypeMapper).mapEntityToDTO(documentType);
        assertNotNull(savedDocumentTypeDTO);
        assertEquals(id, savedDocumentTypeDTO.getId());
    }

    @Test
    void saveWhenEntityAlreadyExists() throws Exception {
        when(documentTypeRepository.findById(documentTypeDTO.getId())).thenReturn(Optional.of(documentType));

        assertThrows(DTOFoundWhenSaveException.class, () -> documentTypeService.save(documentTypeDTO));
        verify(documentTypeRepository).findById(documentTypeDTO.getId());
    }

    @Test
    void update() throws Exception {
        var documentTypeOptional = Optional.of(documentType);

        when(documentTypeRepository.findById(id)).thenReturn(documentTypeOptional);
        when(documentTypeMapper.mapEntityToDTO(documentTypeOptional.get())).thenReturn(documentTypeDTO);
        when(documentTypeMapper.mapDTOToEntity(documentTypeDTO)).thenReturn(documentType);
        when(documentTypeRepository.save(documentType)).thenReturn(documentType);
        doNothing().when(entitiesHistoryService).createEntityHistoryRecord(documentType, documentType, HistoryAction.UPDATE);

        var updateDocumentTypeDTO = documentTypeService.update(documentTypeDTO);

        verify(documentTypeRepository).findById(id);
        verify(documentTypeMapper, times(2)).mapEntityToDTO(documentTypeOptional.get());
        verify(documentTypeMapper, times(2)).mapDTOToEntity(documentTypeDTO);
        verify(documentTypeRepository).save(documentType);
        verify(entitiesHistoryService).createEntityHistoryRecord(documentType, documentType, HistoryAction.UPDATE);
        assertNotNull(updateDocumentTypeDTO);
        assertEquals(id, updateDocumentTypeDTO.getId());
    }

    @Test
    void updateWhenNotFound() throws Exception {
        when(documentTypeRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> documentTypeService.update(documentTypeDTO));
        verify(documentTypeRepository).findById(id);
    }

    @Test
    void deleteById() throws Exception {
        var documentTypeOptional = Optional.of(documentType);

        when(documentTypeRepository.findById(id)).thenReturn(documentTypeOptional);
        when(documentTypeMapper.mapEntityToDTO(documentTypeOptional.get())).thenReturn(documentTypeDTO);
        when(documentTypeMapper.mapDTOToEntity(documentTypeDTO)).thenReturn(documentType);
        doNothing().when(entitiesHistoryService).createEntityHistoryRecord(documentType, null, HistoryAction.DELETE);
        doNothing().when(documentTypeRepository).deleteById(id);

        documentTypeService.deleteById(id);

        verify(documentTypeRepository).findById(id);
        verify(documentTypeMapper).mapEntityToDTO(documentTypeOptional.get());
        verify(documentTypeMapper).mapDTOToEntity(documentTypeDTO);
        verify(entitiesHistoryService).createEntityHistoryRecord(documentType, null, HistoryAction.DELETE);
        verify(documentTypeRepository).deleteById(id);
    }

    @Test
    void deleteByIdWhenNotFound() throws Exception {
        when(documentTypeRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> documentTypeService.deleteById(id));
        verify(documentTypeRepository).findById(id);
    }
}