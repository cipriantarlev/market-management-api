package ii.cipriantarlev.marketmanagementapi.region;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class RegionMapperTest {

    @InjectMocks
    private RegionMapper mapper;

    @Mock
    private ModelMapper modelMapper;

    private Region region;
    private RegionDTO regionDTO;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void mapRegionToRegionDTO() throws Exception {
        when(modelMapper.map(region, RegionDTO.class)).thenReturn(regionDTO);

        var resultedValue = mapper.mapRegionToRegionDTO(region);

        verify(modelMapper).map(region, RegionDTO.class);
        assertEquals(regionDTO, resultedValue);
    }

    @Test
    void mapRegionDTOToRegion() throws Exception {
        when(modelMapper.map(regionDTO, Region.class)).thenReturn(region);

        var resultedValue = mapper.mapRegionDTOToRegion(regionDTO);

        verify(modelMapper).map(regionDTO, Region.class);
        assertEquals(region, resultedValue);
    }
}