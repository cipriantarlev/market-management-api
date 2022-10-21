package ii.cipriantarlev.marketmanagementapi.pricechangingactproduct;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.mockito.Mockito.*;

class PriceChangingActProductMapperTest {

    @InjectMocks
    private PriceChangingActProductMapper mapper;

    @Mock
    private ModelMapper modelMapper;

    private PriceChangingActProduct priceChangingActProduct;
    private PriceChangingActProductDTO priceChangingActProductDTO;

    @BeforeEach
    void setUp() {
        openMocks(this);
        UUID id = UUID.randomUUID();
        priceChangingActProductDTO = PriceChangingActProductDTO.builder().id(id).build();
        priceChangingActProduct = new PriceChangingActProduct();
        priceChangingActProduct.setId(id);
    }

    @Test
    void mapEntityToDTO() throws Exception {
        when(modelMapper.map(priceChangingActProduct, PriceChangingActProductDTO.class)).thenReturn(priceChangingActProductDTO);

        var resultedPriceChangingActProductDTO = mapper.mapEntityToDTO(priceChangingActProduct);

        verify(modelMapper).map(priceChangingActProduct, PriceChangingActProductDTO.class);
        assertEquals(priceChangingActProductDTO, resultedPriceChangingActProductDTO);
    }

    @Test
    void mapDTOToEntity() throws Exception {
        when(modelMapper.map(priceChangingActProductDTO, PriceChangingActProduct.class)).thenReturn(priceChangingActProduct);

        var resultedPriceChangingActProduct = mapper.mapDTOToEntity(priceChangingActProductDTO);

        verify(modelMapper).map(priceChangingActProductDTO, PriceChangingActProduct.class);
        assertEquals(priceChangingActProduct, resultedPriceChangingActProduct);
    }
}