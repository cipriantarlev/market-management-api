package ii.cipriantarlev.marketmanagementapi.category;

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

class CategoryServiceTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @Mock
    private EntitiesHistoryService entitiesHistoryService;

    private final long id = 1L;
    private final Category category = new Category();
    private CategoryDTO categoryDTO;

    @BeforeEach
    void setUp() {
        openMocks(this);
        categoryDTO = CategoryDTO.builder().id(1L).name("test").build();
    }

    @Test
    void findAll() throws Exception {
        List<Category> categoryList = Collections.singletonList(category);

        when(categoryRepository.findAll()).thenReturn(categoryList);
        when(categoryMapper.mapEntityToDTO(categoryList.get(0))).thenReturn(new CategoryDTO());

        var categoryDTOList = categoryService.findAll();

        verify(categoryRepository).findAll();
        verify(categoryMapper).mapEntityToDTO(categoryList.get(0));
        assertFalse(categoryDTOList.isEmpty());
    }

    @Test
    void findAllWhenEmptyList() throws Exception {
        when(categoryRepository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(DTOListNotFoundException.class, () -> categoryService.findAll());
        verify(categoryRepository).findAll();
    }

    @Test
    void findById() throws Exception {
        var categoryOptional = Optional.of(category);

        when(categoryRepository.findById(id)).thenReturn(categoryOptional);
        when(categoryMapper.mapEntityToDTO(categoryOptional.get())).thenReturn(new CategoryDTO());

        var categoryDTO = categoryService.findById(id);

        verify(categoryRepository).findById(id);
        verify(categoryMapper).mapEntityToDTO(categoryOptional.get());
        assertNotNull(categoryDTO);
    }

    @Test
    void findByIdWhenNotFound() throws Exception {
        when(categoryRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> categoryService.findById(id));
        verify(categoryRepository).findById(id);
    }

    @Test
    void update() throws Exception {
        var categoryOptional = Optional.of(category);

        when(categoryRepository.findById(id)).thenReturn(categoryOptional);
        when(categoryMapper.mapEntityToDTO(categoryOptional.get())).thenReturn(categoryDTO);
        when(categoryMapper.mapDTOToEntity(categoryDTO)).thenReturn(category);
        when(categoryRepository.save(category)).thenReturn(category);
        doNothing().when(entitiesHistoryService).createEntityHistoryRecord(category, category, HistoryAction.UPDATE);

        var updatedCategoryDTO = categoryService.update(categoryDTO);

        verify(categoryRepository).findById(id);
        verify(categoryMapper, times(2)).mapEntityToDTO(categoryOptional.get());
        verify(categoryMapper, times(2)).mapDTOToEntity(categoryDTO);
        verify(entitiesHistoryService).createEntityHistoryRecord(category, category, HistoryAction.UPDATE);
        verify(categoryRepository).save(category);
        assertNotNull(updatedCategoryDTO);
    }

    @Test
    void updateWhenNotFound() throws Exception {
        when(categoryRepository.findById(categoryDTO.getId())).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> categoryService.update(categoryDTO));
        verify(categoryRepository).findById(categoryDTO.getId());
    }

    @Test
    void save() throws Exception {
        when(categoryRepository.findById(categoryDTO.getId())).thenReturn(Optional.empty());
        when(categoryMapper.mapDTOToEntity(categoryDTO)).thenReturn(category);
        when(categoryRepository.save(category)).thenReturn(category);
        doNothing().when(entitiesHistoryService).createEntityHistoryRecord(category, null, HistoryAction.CREATE);
        when(categoryMapper.mapEntityToDTO(category)).thenReturn(categoryDTO);

        var savedCategoryDTO = categoryService.save(categoryDTO);

        verify(categoryRepository).findById(categoryDTO.getId());
        verify(categoryMapper).mapEntityToDTO(category);
        verify(categoryMapper).mapDTOToEntity(categoryDTO);
        verify(entitiesHistoryService).createEntityHistoryRecord(category, null, HistoryAction.CREATE);
        verify(categoryRepository).save(category);
        assertNotNull(savedCategoryDTO);
    }

    @Test
    void saveWhenEntityAlreadyExists() throws Exception {
        when(categoryRepository.findById(categoryDTO.getId())).thenReturn(Optional.of(category));

        assertThrows(DTOFoundWhenSaveException.class, () -> categoryService.save(categoryDTO));

        verify(categoryRepository).findById(categoryDTO.getId());
    }

    @Test
    void deleteById() throws Exception {
        var categoryOptional = Optional.of(category);

        when(categoryRepository.findById(id)).thenReturn(categoryOptional);
        when(categoryMapper.mapEntityToDTO(categoryOptional.get())).thenReturn(categoryDTO);
        when(categoryMapper.mapDTOToEntity(categoryDTO)).thenReturn(category);
        doNothing().when(entitiesHistoryService).createEntityHistoryRecord(category, null, HistoryAction.DELETE);
        doNothing().when(categoryRepository).deleteById(id);

        categoryService.deleteById(id);

        verify(categoryRepository).findById(id);
        verify(categoryMapper).mapEntityToDTO(categoryOptional.get());
        verify(categoryMapper).mapDTOToEntity(categoryDTO);
        verify(entitiesHistoryService).createEntityHistoryRecord(category, null, HistoryAction.DELETE);
        verify(categoryRepository).deleteById(id);
    }

    @Test
    void deleteByIdWhenNotFound() throws Exception {
        when(categoryRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> categoryService.deleteById(id));
        verify(categoryRepository).findById(id);
    }
}