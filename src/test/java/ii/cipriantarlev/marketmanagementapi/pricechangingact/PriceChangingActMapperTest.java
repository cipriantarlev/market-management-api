package ii.cipriantarlev.marketmanagementapi.pricechangingact;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class PriceChangingActMapperTest {

    @InjectMocks
    private PriceChangingActMapper mapper;

    @Mock
    private ModelMapper modelMapper;

    private PriceChangingActDTO priceChangingActDTO;
    private PriceChangingAct priceChangingAct;

    @BeforeEach
    void setUp() {
        openMocks(this);
        UUID id = UUID.randomUUID();
        priceChangingActDTO = PriceChangingActDTO.builder().id(id).build();
        priceChangingAct = new PriceChangingAct();
        priceChangingAct.setId(id);
    }

    @Test
    void mapEntityToDTO() throws Exception {
        when(modelMapper.map(priceChangingAct, PriceChangingActDTO.class)).thenReturn(priceChangingActDTO);

        var resultedPriceChangingActDTO = mapper.mapEntityToDTO(priceChangingAct);

        verify(modelMapper).map(priceChangingAct, PriceChangingActDTO.class);
        assertEquals(priceChangingActDTO, resultedPriceChangingActDTO);
    }

    @Test
    void mapDTOToEntity() throws Exception {
        when(modelMapper.map(priceChangingActDTO, PriceChangingAct.class)).thenReturn(priceChangingAct);

        var resultedPriceChangingAct = mapper.mapDTOToEntity(priceChangingActDTO);

        verify(modelMapper).map(priceChangingActDTO, PriceChangingAct.class);
        assertEquals(priceChangingAct, resultedPriceChangingAct);
    }
}