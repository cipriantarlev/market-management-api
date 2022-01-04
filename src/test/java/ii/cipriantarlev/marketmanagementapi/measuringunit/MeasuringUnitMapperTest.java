package ii.cipriantarlev.marketmanagementapi.measuringunit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class MeasuringUnitMapperTest {

    @InjectMocks
    private MeasuringUnitMapper mapper;

    @Mock
    private ModelMapper modelMapper;

    private MeasuringUnitDTO measuringUnitDTO;
    private MeasuringUnit measuringUnit;

    @BeforeEach
    void setUp() {
        openMocks(this);
        measuringUnitDTO = MeasuringUnitDTO.builder().id(1L).build();
        measuringUnit = new MeasuringUnit();
        measuringUnit.setId(1L);
    }

    @Test
    void mapEntityToDTO() throws Exception {
        when(modelMapper.map(measuringUnit, MeasuringUnitDTO.class)).thenReturn(measuringUnitDTO);

        var resultedMeasuringUnitDTO = mapper.mapEntityToDTO(measuringUnit);

        verify(modelMapper).map(measuringUnit, MeasuringUnitDTO.class);
        assertEquals(measuringUnitDTO, resultedMeasuringUnitDTO);
    }

    @Test
    void mapDTOToEntity() throws Exception {
        when(modelMapper.map(measuringUnitDTO, MeasuringUnit.class)).thenReturn(measuringUnit);

        var resultedMeasuringUnit = mapper.mapDTOToEntity(measuringUnitDTO);

        verify(modelMapper).map(measuringUnitDTO, MeasuringUnit.class);
        assertEquals(measuringUnit, resultedMeasuringUnit);
    }
}