package ii.cipriantarlev.marketmanagementapi.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class ProductMapperTest {

    @InjectMocks
    private ProductMapper mapper;

    @Mock
    private ModelMapper modelMapper;

    private ProductDTO productDTO;
    private Product product;
    private ProductDTOForList productDTOForList;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void mapEntityToDTO() throws Exception {
        when(modelMapper.map(product, ProductDTO.class)).thenReturn(productDTO);

        var returnedValue = mapper.mapEntityToDTO(product);

        verify(modelMapper).map(product, ProductDTO.class);
        assertEquals(productDTO, returnedValue);
    }

    @Test
    void mapDTOToEntity() throws Exception {
        when(modelMapper.map(productDTO, Product.class)).thenReturn(product);

        var resultedValue = mapper.mapDTOToEntity(productDTO);

        verify(modelMapper).map(productDTO, Product.class);
        assertEquals(product, resultedValue);
    }

    @Test
    void mapEntityToDTOForList() throws Exception {
        when(modelMapper.map(product, ProductDTOForList.class)).thenReturn(productDTOForList);

        var returnedValue = mapper.mapEntityToDTOForList(product);

        verify(modelMapper).map(product, ProductDTOForList.class);
        assertEquals(productDTOForList, returnedValue);
    }
}