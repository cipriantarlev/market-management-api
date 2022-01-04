package ii.cipriantarlev.marketmanagementapi.vat;

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

class VatServiceTest {

    @InjectMocks
    private VatServiceImpl service;

    @Mock
    private VatRepository repository;

    @Mock
    private VatMapper mapper;

    @Mock
    private EntitiesHistoryService entitiesHistoryService;

    private Vat vat;
    private VatDTO vatDTO;
    private final long id = 1L;

    @BeforeEach
    void setUp() {
        openMocks(this);
        vat = new Vat();
        vat.setId(id);
        vatDTO = VatDTO.builder().id(id).build();
    }

    @Test
    void findAll() throws Exception {
        List<Vat> vatList = Collections.singletonList(vat);

        when(repository.findAll()).thenReturn(vatList);
        when(mapper.mapVatToVatDTO(vatList.get(0))).thenReturn(vatDTO);

        var resultedVatDTOList = service.findAll();

        verify(repository).findAll();
        verify(mapper).mapVatToVatDTO(vatList.get(0));
        assertFalse(resultedVatDTOList.isEmpty());
        assertEquals(1, resultedVatDTOList.size());
    }

    @Test
    void findById() throws Exception {
        var vatOptional = Optional.of(vat);

        when(repository.findById(id)).thenReturn(vatOptional);
        when(mapper.mapVatToVatDTO(vatOptional.get())).thenReturn(vatDTO);

        var resultedVatDTO = service.findById(id);

        verify(repository).findById(id);
        verify(mapper).mapVatToVatDTO(vatOptional.get());
        assertNotNull(resultedVatDTO);
        assertEquals(id, resultedVatDTO.getId());
    }

    @Test
    void findByIdWhenNotFound() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> service.findById(id));
        verify(repository).findById(id);
    }

    @Test
    void save() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.empty());
        when(mapper.mapVatDTOToVat(vatDTO)).thenReturn(vat);
        when(repository.save(vat)).thenReturn(vat);
        doNothing().when(entitiesHistoryService)
                        .createEntityHistoryRecord(vat, null, HistoryAction.CREATE);
        when(mapper.mapVatToVatDTO(vat)).thenReturn(vatDTO);

        var savedVatDTO = service.save(vatDTO);

        verify(repository).findById(id);
        verify(mapper).mapVatToVatDTO(vat);
        verify(mapper).mapVatDTOToVat(vatDTO);
        verify(repository).save(vat);
        verify(entitiesHistoryService).createEntityHistoryRecord(vat, null, HistoryAction.CREATE);
        assertNotNull(savedVatDTO);
        assertEquals(id, savedVatDTO.getId());
    }

    @Test
    void saveWhenAlreadyExist() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.of(vat));

        assertThrows(DTOFoundWhenSaveException.class, () -> service.save(vatDTO));
        verify(repository).findById(id);
    }

    @Test
    void update() throws Exception {
        var vatOptional = Optional.of(vat);

        when(repository.findById(id)).thenReturn(vatOptional);
        when(mapper.mapVatDTOToVat(vatDTO)).thenReturn(vat);
        when(repository.save(vat)).thenReturn(vat);
        doNothing().when(entitiesHistoryService)
                .createEntityHistoryRecord(vat, vat, HistoryAction.UPDATE);
        when(mapper.mapVatToVatDTO(vat)).thenReturn(vatDTO);

        var updatedVatDTO = service.update(vatDTO);

        verify(repository).findById(id);
        verify(mapper, times(2)).mapVatToVatDTO(vat);
        verify(mapper, times(2)).mapVatDTOToVat(vatDTO);
        verify(repository).save(vat);
        verify(entitiesHistoryService).createEntityHistoryRecord(vat, vat, HistoryAction.UPDATE);
        assertNotNull(updatedVatDTO);
        assertEquals(id, updatedVatDTO.getId());
    }

    @Test
    void updateWhenNotFound() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> service.update(vatDTO));
        verify(repository).findById(id);
    }

    @Test
    void deleteById() throws Exception {
        var vatOptional = Optional.of(vat);

        when(repository.findById(id)).thenReturn(vatOptional);
        when(mapper.mapVatDTOToVat(vatDTO)).thenReturn(vat);
        doNothing().when(entitiesHistoryService)
                .createEntityHistoryRecord(vat, null, HistoryAction.DELETE);
        when(mapper.mapVatToVatDTO(vat)).thenReturn(vatDTO);
        doNothing().when(repository).deleteById(id);

        service.deleteById(id);

        verify(repository).findById(id);
        verify(mapper).mapVatToVatDTO(vat);
        verify(mapper).mapVatDTOToVat(vatDTO);
        verify(repository).deleteById(id);
        verify(entitiesHistoryService).createEntityHistoryRecord(vat, null, HistoryAction.DELETE);
    }

    @Test
    void deleteByIdWhenNotFound() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> service.deleteById(id));
        verify(repository).findById(id);
    }
}