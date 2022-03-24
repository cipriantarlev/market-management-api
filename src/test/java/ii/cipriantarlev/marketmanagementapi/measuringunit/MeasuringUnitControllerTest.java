package ii.cipriantarlev.marketmanagementapi.measuringunit;

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

class MeasuringUnitControllerTest {

    @InjectMocks
    private MeasuringUnitController controller;

    @Mock
    private MeasuringUnitService service;

    @Mock
    private RestControllerUtil restControllerUtil;

    private final long id = 1L;
    private final int ok = 200;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void getMeasuringUnits() throws Exception {
        List<MeasuringUnitDTO> measuringUnitDTOList = Collections.singletonList(new MeasuringUnitDTO());

        when(service.findAll()).thenReturn(measuringUnitDTOList);

        var response = controller.getMeasuringUnits();

        verify(service).findAll();
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(measuringUnitDTOList, response.getBody());
    }

    @Test
    void getMeasuringUnit() throws Exception {
        MeasuringUnitDTO measuringUnitDTO = MeasuringUnitDTO.builder().id(id).build();

        when(service.findById(id)).thenReturn(measuringUnitDTO);

        var response = controller.getMeasuringUnit(id);

        verify(service).findById(id);
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(measuringUnitDTO, response.getBody());
    }

    @Test
    void createMeasuringUnit() throws Exception {
        MeasuringUnitDTO measuringUnitDTO = MeasuringUnitDTO.builder().name("test").build();
        MeasuringUnitDTO savedMeasuringUnitDTO = MeasuringUnitDTO.builder().id(id).name("test").build();

        when(service.save(measuringUnitDTO)).thenReturn(savedMeasuringUnitDTO);
        when(restControllerUtil.setHttpsHeaderLocation(MEASURING_UNITS_ROOT_PATH, savedMeasuringUnitDTO.getId()))
                .thenReturn(new HttpHeaders());

        var response = controller.createMeasuringUnit(measuringUnitDTO);

        verify(service).save(measuringUnitDTO);
        verify(restControllerUtil).setHttpsHeaderLocation(MEASURING_UNITS_ROOT_PATH.concat(ID_PATH), savedMeasuringUnitDTO.getId());
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(savedMeasuringUnitDTO, response.getBody());
    }

    @Test
    void updateMeasuringUnit() throws Exception {
        MeasuringUnitDTO measuringUnitDTO = MeasuringUnitDTO.builder().id(id).name("test").build();

        when(service.update(measuringUnitDTO)).thenReturn(measuringUnitDTO);

        var response = controller.updateMeasuringUnit(measuringUnitDTO);

        verify(service).update(measuringUnitDTO);
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(measuringUnitDTO, response.getBody());
    }

    @Test
    void deleteMeasuringUnit() throws Exception {
        doNothing().when(service).deleteById(id);

        var response = controller.deleteMeasuringUnit(id);

        verify(service).deleteById(id);
        assertEquals(ok, response.getStatusCodeValue());
    }
}