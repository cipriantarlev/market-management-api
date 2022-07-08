package ii.cipriantarlev.marketmanagementapi.pricechangingact;

import ii.cipriantarlev.marketmanagementapi.exceptions.DTOFoundWhenSaveException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTOListNotFoundException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTONotFoundException;
import ii.cipriantarlev.marketmanagementapi.pricechangingactproduct.PriceChangingActProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class PriceChangingActServiceTest {

    @InjectMocks
    private PriceChangingActServiceImpl service;

    @Mock
    private PriceChangingActRepository repository;

    @Mock
    private PriceChangingActMapper mapper;

    @Mock
    private PriceChangingActProductService priceChangingActProductService;

    private final PriceChangingAct priceChangingAct = new PriceChangingAct();
    private final PriceChangingActDTO priceChangingActDTO = new PriceChangingActDTO();

    private final UUID id = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        openMocks(this);
        priceChangingAct.setId(id);
        priceChangingActDTO.setId(id);
    }

    @Test
    void findAll() throws Exception {
        List<PriceChangingAct> priceChangingActs = Collections.singletonList(priceChangingAct);

        when(repository.findAll()).thenReturn(priceChangingActs);
        when(mapper.mapEntityToDTO(priceChangingActs.get(0))).thenReturn(new PriceChangingActDTO());

        var priceChangingActsDTO = service.findAll();

        verify(repository).findAll();
        verify(mapper).mapEntityToDTO(priceChangingActs.get(0));
        assertFalse(priceChangingActsDTO.isEmpty());
    }

    @Test
    void findAllWhenListIsEmpty() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(DTOListNotFoundException.class, () -> service.findAll());
        verify(repository).findAll();
    }

    @Test
    void findById() throws Exception {
        var priceChangingActOptional = Optional.of(priceChangingAct);

        when(repository.findById(id)).thenReturn(priceChangingActOptional);
        when(mapper.mapEntityToDTO(priceChangingActOptional.get())).thenReturn(priceChangingActDTO);

        var resultedPriceChangingActDTO = service.findById(id);

        verify(repository).findById(id);
        verify(mapper).mapEntityToDTO(priceChangingActOptional.get());
        assertEquals(priceChangingActDTO, resultedPriceChangingActDTO);
    }

    @Test
    void findByIdNotFound() {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> service.findById(id));
        verify(repository).findById(id);
    }

    @Test
    void save() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.empty());
        when(mapper.mapDTOToEntity(priceChangingActDTO)).thenReturn(priceChangingAct);
        when(repository.save(priceChangingAct)).thenReturn(priceChangingAct);
        when(mapper.mapEntityToDTO(priceChangingAct)).thenReturn(priceChangingActDTO);

        var resultedPriceChangingActDTO = service.save(priceChangingActDTO);

        verify(repository).findById(id);
        verify(repository).save(priceChangingAct);
        verify(mapper).mapDTOToEntity(priceChangingActDTO);
        verify(mapper).mapEntityToDTO(priceChangingAct);
        assertEquals(priceChangingActDTO, resultedPriceChangingActDTO);
    }

    @Test
    void saveWhenAlreadyExist() throws Exception {
        when(repository.findById(priceChangingActDTO.getId())).thenReturn(Optional.of(priceChangingAct));

        assertThrows(DTOFoundWhenSaveException.class, () -> service.save(priceChangingActDTO));
        verify(repository).findById(priceChangingActDTO.getId());
    }

    @Test
    void update() throws Exception {
        var priceChangingActOptional = Optional.of(priceChangingAct);

        when(repository.findById(id)).thenReturn(priceChangingActOptional);
        when(mapper.mapEntityToDTO(priceChangingActOptional.get())).thenReturn(priceChangingActDTO);
        when(mapper.mapDTOToEntity(priceChangingActDTO)).thenReturn(priceChangingAct);
        when(repository.save(priceChangingAct)).thenReturn(priceChangingAct);

        var resultedPriceChangingActDTO = service.update(priceChangingActDTO);

        verify(repository).findById(id);
        verify(mapper, times(2)).mapEntityToDTO(priceChangingActOptional.get());
        verify(mapper).mapDTOToEntity(priceChangingActDTO);
        verify(repository).save(priceChangingAct);
        assertEquals(priceChangingActDTO, resultedPriceChangingActDTO);
    }

    @Test
    void updateWhenNotFound() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> service.update(priceChangingActDTO));
        verify(repository).findById(id);
    }

    @Test
    void deleteById() throws Exception {
        var priceChangingActOptional = Optional.of(priceChangingAct);

        when(repository.findById(id)).thenReturn(priceChangingActOptional);
        when(priceChangingActProductService.findAllByPriceChangingActId(id)).thenReturn(new ArrayList<>());
        when(mapper.mapEntityToDTO(priceChangingActOptional.get())).thenReturn(priceChangingActDTO);
        doNothing().when(repository).deleteById(id);

        service.deleteById(id);

        verify(repository).findById(id);
        verify(priceChangingActProductService).findAllByPriceChangingActId(id);
        verify(mapper).mapEntityToDTO(priceChangingActOptional.get());
        verify(repository).deleteById(id);
    }

    @Test
    void deleteByIdWhenNotFound() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> service.deleteById(id));
        verify(repository).findById(id);
    }

    @Test
    void updateIsApprovedMarker() throws Exception {
        var priceChangingActOptional = Optional.of(priceChangingAct);
        Map<Boolean, List<UUID>> priceChangingActsToUpdate = Collections.singletonMap(true, Collections.singletonList(id));

        when(repository.findById(id)).thenReturn(priceChangingActOptional);
        when(mapper.mapEntityToDTO(priceChangingActOptional.get())).thenReturn(priceChangingActDTO);
        when(repository.updateIsApprovedMarker(true, id)).thenReturn(1);

        var updatedRows = service.updateIsApprovedMarker(priceChangingActsToUpdate);

        verify(repository).findById(id);
        verify(mapper).mapEntityToDTO(priceChangingActOptional.get());
        verify(repository).updateIsApprovedMarker(true, id);
        assertEquals(1, updatedRows);
    }

    @Test
    void updateIsApprovedMarkerWhenNotFound() throws Exception {
        Map<Boolean, List<UUID>> priceChangingActsToUpdate = Collections.singletonMap(true, Collections.singletonList(id));

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> service.updateIsApprovedMarker(priceChangingActsToUpdate));
        verify(repository).findById(id);
    }
}