package ii.cipriantarlev.marketmanagementapi.category;

import ii.cipriantarlev.marketmanagementapi.utils.RestControllerUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpHeaders;

import java.util.Collections;
import java.util.List;

import static ii.cipriantarlev.marketmanagementapi.utils.Constants.CATEGORIES_ROOT_PATH;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    @Mock
    private RestControllerUtil restControllerUtil;

    private final long id = 1L;
    private final int ok = 200;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void getCategoriesWhenOk() throws Exception {
        List<CategoryDTO> categories = Collections.singletonList(new CategoryDTO());

        when(categoryService.findAll()).thenReturn(categories);

        var response = categoryController.getCategories();

        assertEquals(categories, response.getBody());
        assertEquals(ok, response.getStatusCodeValue());
        verify(categoryService).findAll();
    }

    @Test
    void getCategory() throws Exception {
        CategoryDTO categoryDTO = CategoryDTO.builder().id(id).build();

        when(categoryService.findById(id)).thenReturn(categoryDTO);

        var response = categoryController.getCategory(id);

        assertEquals(categoryDTO, response.getBody());
        assertEquals(ok, response.getStatusCodeValue());
        verify(categoryService).findById(id);
    }

    @Test
    void createCategory() throws Exception {
        CategoryDTO categoryDTO = CategoryDTO.builder().name("Test").build();
        CategoryDTO savedCategoryDTO = CategoryDTO.builder().id(id).name("Test").build();

        when(categoryService.save(categoryDTO)).thenReturn(savedCategoryDTO);
        when(restControllerUtil
                .setHttpsHeaderLocation(CATEGORIES_ROOT_PATH, savedCategoryDTO.getId())).thenReturn(new HttpHeaders());

        var response = categoryController.createCategory(categoryDTO);

        assertEquals(savedCategoryDTO, response.getBody());
        assertEquals(ok, response.getStatusCodeValue());
        verify(categoryService).save(categoryDTO);
        verify(restControllerUtil).setHttpsHeaderLocation(CATEGORIES_ROOT_PATH, savedCategoryDTO.getId());
    }

    @Test
    void updateCategory() throws Exception {
        CategoryDTO categoryDTO = CategoryDTO.builder().id(id).name("Test").build();

        when(categoryService.update(categoryDTO)).thenReturn(categoryDTO);

        var response = categoryController.updateCategory(categoryDTO);

        assertEquals(categoryDTO, response.getBody());
        assertEquals(ok, response.getStatusCodeValue());
        verify(categoryService).update(categoryDTO);
    }

    @Test
    void deleteCategory() throws Exception {
        doNothing().when(categoryService).deleteById(id);

        var response = categoryController.deleteCategory(id);

        assertEquals(ok, response.getStatusCodeValue());
        verify(categoryService).deleteById(id);
    }
}