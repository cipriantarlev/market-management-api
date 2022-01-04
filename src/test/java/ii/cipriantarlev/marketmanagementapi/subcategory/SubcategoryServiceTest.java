package ii.cipriantarlev.marketmanagementapi.subcategory;

import ii.cipriantarlev.marketmanagementapi.exceptions.DTOFoundWhenSaveException;
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

class SubcategoryServiceTest {

    @InjectMocks
    private SubcategoryServiceImpl service;

    @Mock
    private SubcategoryRepository repository;

    @Mock
    private SubcategoryMapper mapper;

    @Mock
    private EntitiesHistoryService entitiesHistoryService;

    private Subcategory subcategory;
    private SubcategoryDTO subcategoryDTO;
    private final long id = 1L;

    @BeforeEach
    void setUp() {
        openMocks(this);
        subcategory = new Subcategory();
        subcategory.setId(id);
        subcategoryDTO = SubcategoryDTO.builder().id(id).build();
    }

    @Test
    void findAll() throws Exception {
        List<Subcategory> subcategoryList = Collections.singletonList(subcategory);

        when(repository.findAll()).thenReturn(subcategoryList);
        when(mapper.mapEntityToDTO(subcategoryList.get(0))).thenReturn(subcategoryDTO);

        var resultedSubcategoryList = service.findAll();

        verify(repository).findAll();
        verify(mapper).mapEntityToDTO(subcategoryList.get(0));
        assertFalse(resultedSubcategoryList.isEmpty());
        assertEquals(1, resultedSubcategoryList.size());
    }

    @Test
    void findAllByCategoryId() throws Exception {
        List<Subcategory> subcategoryList = Collections.singletonList(subcategory);

        when(repository.findAllByCategoryId(id)).thenReturn(subcategoryList);
        when(mapper.mapEntityToNoCategoryDTO(subcategoryList.get(0))).thenReturn(new SubcategoryDTONoCategory());

        var resultedSubcategoryList = service.findAllByCategoryId(id);

        verify(repository).findAllByCategoryId(id);
        verify(mapper).mapEntityToNoCategoryDTO(subcategoryList.get(0));
        assertFalse(resultedSubcategoryList.isEmpty());
        assertEquals(1, resultedSubcategoryList.size());
    }

    @Test
    void findById() throws Exception {
        var subcategoryOptional = Optional.of(subcategory);

        when(repository.findById(id)).thenReturn(subcategoryOptional);
        when(mapper.mapEntityToDTO(subcategoryOptional.get())).thenReturn(subcategoryDTO);

        var resultedSubcategoryDTO = service.findById(id);

        verify(repository).findById(id);
        verify(mapper).mapEntityToDTO(subcategoryOptional.get());
        assertNotNull(resultedSubcategoryDTO);
        assertEquals(id, resultedSubcategoryDTO.getId());
    }

    @Test
    void findByIdWhenNotFound() throws Exception {
       when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> service.findById(id));
        verify(repository).findById(id);
    }

    @Test
    void save() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.empty());
        when(mapper.mapDTOToEntity(subcategoryDTO)).thenReturn(subcategory);
        when(repository.save(subcategory)).thenReturn(subcategory);
        doNothing().when(entitiesHistoryService)
                .createEntityHistoryRecord(subcategory, null, HistoryAction.CREATE);
        when(mapper.mapEntityToDTO(subcategory)).thenReturn(subcategoryDTO);

        var savedSubcategory = service.save(subcategoryDTO);

        verify(repository).findById(id);
        verify(mapper).mapDTOToEntity(subcategoryDTO);
        verify(repository).save(subcategory);
        verify(entitiesHistoryService).createEntityHistoryRecord(subcategory, null, HistoryAction.CREATE);
        verify(mapper).mapEntityToDTO(subcategory);
        assertNotNull(savedSubcategory);
        assertEquals(id, savedSubcategory.getId());
    }

    @Test
    void saveWhenAlreadyExist() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.of(subcategory));

        assertThrows(DTOFoundWhenSaveException.class, () -> service.save(subcategoryDTO));
        verify(repository).findById(id);
    }

    @Test
    void update() throws Exception {
        var subcategoryOptional = Optional.of(subcategory);

        when(repository.findById(id)).thenReturn(subcategoryOptional);
        when(mapper.mapEntityToDTO(subcategoryOptional.get())).thenReturn(subcategoryDTO);
        when(mapper.mapDTOToEntity(subcategoryDTO)).thenReturn(subcategory);
        when(repository.save(subcategory)).thenReturn(subcategory);
        doNothing().when(entitiesHistoryService)
                .createEntityHistoryRecord(subcategory, subcategory, HistoryAction.UPDATE);

        var updatedSubcategoryDTO = service.update(subcategoryDTO);

        verify(repository).findById(id);
        verify(mapper, times(2)).mapEntityToDTO(subcategoryOptional.get());
        verify(mapper, times(2)).mapDTOToEntity(subcategoryDTO);
        verify(repository).save(subcategory);
        verify(entitiesHistoryService).createEntityHistoryRecord(subcategory, subcategory, HistoryAction.UPDATE);
        assertNotNull(updatedSubcategoryDTO);
        assertEquals(id, updatedSubcategoryDTO.getId());
    }

    @Test
    void updateWhenNotFound() throws Exception {
       when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> service.update(subcategoryDTO));
        verify(repository).findById(id);
    }

    @Test
    void deleteById() throws Exception {
        var subcategoryOptional = Optional.of(subcategory);

        when(repository.findById(id)).thenReturn(subcategoryOptional);
        when(mapper.mapEntityToDTO(subcategoryOptional.get())).thenReturn(subcategoryDTO);
        when(mapper.mapDTOToEntity(subcategoryDTO)).thenReturn(subcategory);
        doNothing().when(entitiesHistoryService)
                        .createEntityHistoryRecord(subcategory, null, HistoryAction.DELETE);
        doNothing().when(repository).deleteById(id);

        service.deleteById(id);

        verify(repository).findById(id);
        verify(mapper).mapEntityToDTO(subcategoryOptional.get());
        verify(mapper).mapDTOToEntity(subcategoryDTO);
        verify(entitiesHistoryService).createEntityHistoryRecord(subcategory, null, HistoryAction.DELETE);
        verify(repository).deleteById(id);
    }

    @Test
    void deleteByIdWhenNotFound() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> service.deleteById(id));
        verify(repository).findById(id);
    }
}