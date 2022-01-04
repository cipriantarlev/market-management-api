package ii.cipriantarlev.marketmanagementapi.category;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class CategoryMapperTest {

    @InjectMocks
    private CategoryMapper categoryMapper;

    @Mock
    private ModelMapper modelMapper;

    private Category category;
    private CategoryDTO categoryDTO;

    @BeforeEach
    void setUp() {
        openMocks(this);
        categoryDTO = CategoryDTO.builder().id(1L).name("test").build();
        category = new Category();
        category.setId(1L);
        category.setName("test");
    }

    @Test
    void mapEntityToDTO() throws Exception {
        when(modelMapper.map(category, CategoryDTO.class)).thenReturn(categoryDTO);

        var resultedBarcodeDTO = categoryMapper.mapEntityToDTO(category);

        verify(modelMapper).map(category, CategoryDTO.class);
        assertEquals(categoryDTO, resultedBarcodeDTO);
    }

    @Test
    void mapDTOToEntity() throws Exception {
        when(modelMapper.map(categoryDTO, Category.class)).thenReturn(category);

        var resultedBarcode = categoryMapper.mapDTOToEntity(categoryDTO);

        verify(modelMapper).map(categoryDTO, Category.class);
        assertEquals(category, resultedBarcode);
    }
}