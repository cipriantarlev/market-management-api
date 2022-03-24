package ii.cipriantarlev.marketmanagementapi.measuringunit;

import ii.cipriantarlev.marketmanagementapi.exceptions.DTOFoundWhenSaveException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTONotFoundException;
import ii.cipriantarlev.marketmanagementapi.history.EntitiesHistoryService;
import ii.cipriantarlev.marketmanagementapi.history.HistoryAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class MeasuringUnitServiceTest {

    @InjectMocks
    private MeasuringUnitServiceImpl service;

    @Mock
    private MeasuringUnitRepository repository;

    @Mock
    private MeasuringUnitMapper mapper;

    @Mock
    private EntitiesHistoryService entitiesHistoryService;

    private MeasuringUnit measuringUnit;
    private MeasuringUnitDTO measuringUnitDTO;

    private final long id = 1L;

    @BeforeEach
    void setUp() {
        openMocks(this);
        measuringUnitDTO = MeasuringUnitDTO.builder().id(id).build();
        measuringUnit = new MeasuringUnit();
        measuringUnit.setId(id);
    }

    @Test
    void findAll() throws Exception {
        List<MeasuringUnit> measuringUnitList = Collections.singletonList(measuringUnit);

        when(repository.findAll()).thenReturn(measuringUnitList);
        when(mapper.mapEntityToDTO(measuringUnitList.get(0))).thenReturn(measuringUnitDTO);

        var resultedMeasuringUnitDTOList = service.findAll();

        verify(repository).findAll();
        verify(mapper).mapEntityToDTO(measuringUnitList.get(0));
        assertFalse(resultedMeasuringUnitDTOList.isEmpty());
        assertEquals(1, resultedMeasuringUnitDTOList.size());
    }

    @Test
    void findById() throws Exception {
        var measuringUnitOptional = Optional.of(measuringUnit);

        when(repository.findById(id)).thenReturn(measuringUnitOptional);
        when(mapper.mapEntityToDTO(measuringUnitOptional.get())).thenReturn(measuringUnitDTO);

        var resultedMeasuringUnitDTO = service.findById(id);

        verify(repository).findById(id);
        verify(mapper).mapEntityToDTO(measuringUnitOptional.get());
        assertNotNull(resultedMeasuringUnitDTO);
        assertEquals(id, resultedMeasuringUnitDTO.getId());
    }

    @Test
    void findByIdWhenNotFound() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> service.findById(id));
        verify(repository).findById(id);
    }

    @Test
    void save() throws Exception {
        when(repository.findById(measuringUnitDTO.getId())).thenReturn(Optional.empty());
        when(mapper.mapDTOToEntity(measuringUnitDTO)).thenReturn(measuringUnit);
        when(repository.save(measuringUnit)).thenReturn(measuringUnit);
        doNothing().when(entitiesHistoryService).createEntityHistoryRecord(measuringUnit, null, HistoryAction.CREATE);
        when(mapper.mapEntityToDTO(measuringUnit)).thenReturn(measuringUnitDTO);

        var savedMeasuringUnitDTO = service.save(measuringUnitDTO);

        verify(repository).findById(measuringUnitDTO.getId());
        verify(mapper).mapDTOToEntity(measuringUnitDTO);
        verify(repository).save(measuringUnit);
        verify(entitiesHistoryService).createEntityHistoryRecord(measuringUnit, null, HistoryAction.CREATE);
        verify(mapper).mapEntityToDTO(measuringUnit);
        assertNotNull(savedMeasuringUnitDTO);
        assertEquals(id, savedMeasuringUnitDTO.getId());
    }

    @Test
    void saveWhenAlreadyExist() throws Exception {
        when(repository.findById(measuringUnitDTO.getId())).thenReturn(Optional.of(measuringUnit));

        assertThrows(DTOFoundWhenSaveException.class, () -> service.save(measuringUnitDTO));
        verify(repository).findById(measuringUnitDTO.getId());
    }

    @Test
    void update() throws Exception {
        var measuringUnitOptional = Optional.of(measuringUnit);

        when(repository.findById(id)).thenReturn(measuringUnitOptional);
        when(mapper.mapEntityToDTO(measuringUnitOptional.get())).thenReturn(measuringUnitDTO);
        when(mapper.mapDTOToEntity(measuringUnitDTO)).thenReturn(measuringUnit);
        when(repository.save(measuringUnit)).thenReturn(measuringUnit);
        doNothing().when(entitiesHistoryService).createEntityHistoryRecord(measuringUnit, measuringUnit, HistoryAction.UPDATE);

        var resultedMeasuringUnitDTO = service.update(measuringUnitDTO);

        verify(repository).findById(id);
        verify(mapper, times(2)).mapEntityToDTO(measuringUnitOptional.get());
        verify(mapper, times(2)).mapDTOToEntity(measuringUnitDTO);
        verify(repository).save(measuringUnit);
        verify(entitiesHistoryService).createEntityHistoryRecord(measuringUnit, measuringUnit, HistoryAction.UPDATE);
        assertNotNull(resultedMeasuringUnitDTO);
        assertEquals(id, resultedMeasuringUnitDTO.getId());
    }

    @Test
    void updateWhenNotFound() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> service.update(measuringUnitDTO));
        verify(repository).findById(id);
    }

    @Test
    void deleteById() throws Exception {
        var measuringUnitOptional = Optional.of(measuringUnit);

        when(repository.findById(id)).thenReturn(measuringUnitOptional);
        when(mapper.mapEntityToDTO(measuringUnitOptional.get())).thenReturn(measuringUnitDTO);
        when(mapper.mapDTOToEntity(measuringUnitDTO)).thenReturn(measuringUnit);
        doNothing().when(entitiesHistoryService).createEntityHistoryRecord(measuringUnit, null, HistoryAction.DELETE);
        doNothing().when(repository).deleteById(id);

        service.deleteById(id);

        verify(repository).findById(id);
        verify(mapper).mapEntityToDTO(measuringUnitOptional.get());
        verify(mapper).mapDTOToEntity(measuringUnitDTO);
        verify(entitiesHistoryService).createEntityHistoryRecord(measuringUnit, null, HistoryAction.DELETE);
        verify(repository).deleteById(id);
    }

    @Test
    void deleteByIdWhenNotFound() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> service.deleteById(id));
        verify(repository).findById(id);
    }
}