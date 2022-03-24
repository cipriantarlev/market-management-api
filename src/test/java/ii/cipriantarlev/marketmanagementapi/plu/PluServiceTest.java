package ii.cipriantarlev.marketmanagementapi.plu;

import ii.cipriantarlev.marketmanagementapi.exceptions.DTONotFoundException;
import ii.cipriantarlev.marketmanagementapi.history.EntitiesHistoryService;
import ii.cipriantarlev.marketmanagementapi.history.HistoryAction;
import ii.cipriantarlev.marketmanagementapi.utils.MarketManagementFactory;
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

class PluServiceTest {

    @InjectMocks
    private PluServiceImpl service;

    @Mock
    private PluRepository repository;

    @Mock
    private PluMapper mapper;

    @Mock
    private EntitiesHistoryService entitiesHistoryService;

    @Mock
    private MarketManagementFactory factory;

    private Plu plu;
    private PluDTO pluDTO;
    private final long id = 1L;

    @BeforeEach
    void setUp() {
        openMocks(this);
        pluDTO = PluDTO.builder().id(id).value(1).build();
        plu = new Plu();
        plu.setId(id);
        plu.setValue(1);
    }

    @Test
    void findAll() throws Exception {
        List<Plu> pluList = Collections.singletonList(plu);

        when(repository.findAll()).thenReturn(pluList);
        when(mapper.mapEntityToDTO(plu)).thenReturn(pluDTO);

        var resultedPluList = service.findAll();

        verify(repository).findAll();
        verify(mapper).mapEntityToDTO(plu);
        assertFalse(resultedPluList.isEmpty());
        assertEquals(1, resultedPluList.size());
    }

    @Test
    void findById() throws Exception {
        var pluOptional = Optional.of(plu);

        when(repository.findById(id)).thenReturn(pluOptional);
        when(mapper.mapEntityToDTO(plu)).thenReturn(pluDTO);

        var resultedPluDTO = service.findById(id);

        verify(repository).findById(id);
        verify(mapper).mapEntityToDTO(plu);
        assertNotNull(resultedPluDTO);
        assertEquals(id, resultedPluDTO.getId());
    }

    @Test
    void findByIdWhenNotFound() throws Exception {
         when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> service.findById(id));
        verify(repository).findById(id);
    }

    @Test
    void generateNewPluWhenFirstPluIsNull() throws Exception {
        when(repository.findFirst1ByOrderByValueDesc()).thenReturn(null);
        when(factory.getNewPlu(0)).thenReturn(plu);
        when(repository.save(plu)).thenReturn(plu);
        when(mapper.mapEntityToDTO(plu)).thenReturn(pluDTO);

        var resultedPluDTO = service.generateNewPlu();

        verify(repository).findFirst1ByOrderByValueDesc();
        verify(repository).save(plu);
        verify(mapper).mapEntityToDTO(plu);
        verify(factory).getNewPlu(0);
        assertEquals(id, resultedPluDTO.getId());
        assertEquals(1, resultedPluDTO.getValue());
    }

    @Test
    void generateNewPluWhenAlreadyExist() throws Exception {
        Plu savedPlu = new Plu(2);
        savedPlu.setId(2L);

        when(repository.findFirst1ByOrderByValueDesc()).thenReturn(plu);
        when(factory.getNewPlu(plu.getValue())).thenReturn(savedPlu);
        when(repository.save(savedPlu)).thenReturn(savedPlu);
        when(mapper.mapEntityToDTO(savedPlu)).thenReturn(pluDTO);
        doNothing().when(entitiesHistoryService).createEntityHistoryRecord(savedPlu, null, HistoryAction.CREATE);

        var resultedPluDTO = service.generateNewPlu();

        verify(repository).findFirst1ByOrderByValueDesc();
        verify(repository).save(savedPlu);
        verify(mapper).mapEntityToDTO(savedPlu);
        verify(factory).getNewPlu(plu.getValue());
        verify(entitiesHistoryService).createEntityHistoryRecord(savedPlu, null, HistoryAction.CREATE);
        assertEquals(id, resultedPluDTO.getId());
        assertEquals(1, resultedPluDTO.getValue());
    }

    @Test
    void deleteById() throws Exception {
        var pluOptional = Optional.of(plu);

        when(repository.findById(id)).thenReturn(pluOptional);
        when(mapper.mapEntityToDTO(plu)).thenReturn(pluDTO);
        when(mapper.mapDTOToEntity(pluDTO)).thenReturn(plu);
        doNothing().when(entitiesHistoryService).createEntityHistoryRecord(plu, null, HistoryAction.DELETE);
        doNothing().when(repository).deleteById(id);

        service.deleteById(id);

        verify(repository).findById(id);
        verify(mapper).mapEntityToDTO(plu);
        verify(mapper).mapDTOToEntity(pluDTO);
        verify(entitiesHistoryService).createEntityHistoryRecord(plu, null, HistoryAction.DELETE);
        verify(repository).deleteById(id);
    }

    @Test
    void deleteByIdWhenNotFound() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> service.deleteById(id));
        verify(repository).findById(id);
    }
}