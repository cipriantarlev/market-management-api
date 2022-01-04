package ii.cipriantarlev.marketmanagementapi.productscode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class ProductCodeMapperTest {

    @InjectMocks
    private ProductCodeMapper mapper;

    @Mock
    private ModelMapper modelMapper;

    private ProductCode productCode;
    private ProductCodeDTO productCodeDTO;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void mapEntityToDTO() throws Exception {
        when(modelMapper.map(productCode, ProductCodeDTO.class)).thenReturn(productCodeDTO);

        var returnedValue = mapper.mapEntityToDTO(productCode);

        verify(modelMapper).map(productCode, ProductCodeDTO.class);
        assertEquals(productCodeDTO, returnedValue);
    }

    @Test
    void mapDTOToEntity() throws Exception {
        when(modelMapper.map(productCodeDTO, ProductCode.class)).thenReturn(productCode);

        var returnedValue = mapper.mapDTOToEntity(productCodeDTO);

        verify(modelMapper).map(productCodeDTO, ProductCode.class);
        assertEquals(productCode, returnedValue);
    }
}