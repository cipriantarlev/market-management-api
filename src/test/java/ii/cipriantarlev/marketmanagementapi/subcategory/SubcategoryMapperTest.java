package ii.cipriantarlev.marketmanagementapi.subcategory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class SubcategoryMapperTest {

    @InjectMocks
    private SubcategoryMapper mapper;

    @Mock
    private ModelMapper modelMapper;

    private Subcategory subcategory;
    private SubcategoryDTO subcategoryDTO;
    private SubcategoryDTONoCategory subcategoryDTONoCategory;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void mapEntityToDTO() throws Exception {
        when(modelMapper.map(subcategory, SubcategoryDTO.class)).thenReturn(subcategoryDTO);

        var returnedValue = mapper.mapEntityToDTO(subcategory);

        verify(modelMapper).map(subcategory, SubcategoryDTO.class);
        assertEquals(subcategoryDTO, returnedValue);
    }

    @Test
    void mapDTOToEntity() throws Exception {
        when(modelMapper.map(subcategoryDTO, Subcategory.class)).thenReturn(subcategory);

        var returnedValue = mapper.mapDTOToEntity(subcategoryDTO);

        verify(modelMapper).map(subcategoryDTO, Subcategory.class);
        assertEquals(subcategory, returnedValue);
    }

    @Test
    void mapEntityToNoCategoryDTO() throws Exception {
        when(modelMapper.map(subcategory, SubcategoryDTONoCategory.class)).thenReturn(subcategoryDTONoCategory);

        var returnedValue = mapper.mapEntityToNoCategoryDTO(subcategory);

        verify(modelMapper).map(subcategory, SubcategoryDTONoCategory.class);
        assertEquals(subcategoryDTONoCategory, returnedValue);
    }
}