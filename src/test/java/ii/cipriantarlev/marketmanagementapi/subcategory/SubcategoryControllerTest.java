package ii.cipriantarlev.marketmanagementapi.subcategory;

import ii.cipriantarlev.marketmanagementapi.utils.RestControllerUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpHeaders;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static ii.cipriantarlev.marketmanagementapi.utils.Constants.*;

class SubcategoryControllerTest {

    @InjectMocks
    private SubcategoryController controller;

    @Mock
    private SubcategoryService service;

    @Mock
    private RestControllerUtil restControllerUtil;

    private final long id = 1L;
    private final int ok = 200;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void getSubcategories() throws Exception {
        List<SubcategoryDTO> subcategoryDTOList = Collections.singletonList(new SubcategoryDTO());

        when(service.findAll()).thenReturn(subcategoryDTOList);

        var response = controller.getSubcategories();

        verify(service).findAll();
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(subcategoryDTOList, response.getBody());
    }

    @Test
    void getSubcategoriesByCategoryId() throws Exception {
        List<SubcategoryDTONoCategory> subcategoryDTOList = Collections.singletonList(new SubcategoryDTONoCategory());

        when(service.findAllByCategoryId(id)).thenReturn(subcategoryDTOList);

        var response = controller.getSubcategoriesByCategoryId(id);

        verify(service).findAllByCategoryId(id);
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(subcategoryDTOList, response.getBody());
    }

    @Test
    void getSubcategory() throws Exception {
        SubcategoryDTO subcategoryDTO = SubcategoryDTO.builder().id(id).build();

        when(service.findById(id)).thenReturn(subcategoryDTO);

        var response = controller.getSubcategory(id);

        verify(service).findById(id);
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(subcategoryDTO, response.getBody());
    }

    @Test
    void createSubcategory() throws Exception {
        SubcategoryDTO subcategoryDTO = SubcategoryDTO.builder().name("Test").build();
        SubcategoryDTO savedSubcategoryDTO = SubcategoryDTO.builder().id(id).name("Test").build();

        when(service.save(subcategoryDTO)).thenReturn(savedSubcategoryDTO);
        when(restControllerUtil
                .setHttpsHeaderLocation(SUBCATEGORIES_ROOT_PATH.concat(ID_PATH), savedSubcategoryDTO.getId()))
                .thenReturn(new HttpHeaders());

        var response = controller.createSubcategory(subcategoryDTO);

        verify(service).save(subcategoryDTO);
        verify(restControllerUtil)
                .setHttpsHeaderLocation(SUBCATEGORIES_ROOT_PATH.concat(ID_PATH), savedSubcategoryDTO.getId());
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(savedSubcategoryDTO, response.getBody());
    }

    @Test
    void updateSubcategory() throws Exception {
        SubcategoryDTO subcategoryDTO = SubcategoryDTO.builder().id(id).name("Test").build();

        when(service.update(subcategoryDTO)).thenReturn(subcategoryDTO);

        var response = controller.updateSubcategory(subcategoryDTO);

        verify(service).update(subcategoryDTO);
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(subcategoryDTO, response.getBody());
    }

    @Test
    void deleteSubcategory() throws Exception {
        doNothing().when(service).deleteById(id);

        var response = controller.deleteSubcategory(id);

        verify(service).deleteById(id);
        assertEquals(ok, response.getStatusCodeValue());
    }
}