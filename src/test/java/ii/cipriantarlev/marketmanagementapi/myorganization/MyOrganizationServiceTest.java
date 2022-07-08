package ii.cipriantarlev.marketmanagementapi.myorganization;

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

class MyOrganizationServiceTest {

    @InjectMocks
    private MyOrganizationServiceImpl service;

    @Mock
    private MyOrganizationRepository repository;

    @Mock
    private MyOrganizationMapper mapper;

    @Mock
    private EntitiesHistoryService entitiesHistoryService;

    private MyOrganization myOrganization;
    private MyOrganizationDTO myOrganizationDTO;
    private final long id = 1L;

    @BeforeEach
    void setUp() {
        openMocks(this);
        myOrganizationDTO = MyOrganizationDTO.builder().id(id).isDefault(true).build();
        myOrganization = new MyOrganization();
        myOrganization.setId(id);
        myOrganization.setDefault(true);
    }

    @Test
    void findAll() throws Exception {
        List<MyOrganization> myOrganizationList = Collections.singletonList(myOrganization);

        when(repository.findAll()).thenReturn(myOrganizationList);
        when(mapper.mapEntityToDTO(myOrganizationList.get(0))).thenReturn(myOrganizationDTO);

        var resultedMyOrganizationDTOList = service.findAll();

        verify(repository).findAll();
        verify(mapper).mapEntityToDTO(myOrganizationList.get(0));
        assertFalse(resultedMyOrganizationDTOList.isEmpty());
        assertEquals(1, resultedMyOrganizationDTOList.size());
    }

    @Test
    void findAllMyOrganizationDTOOnlyName() throws Exception {
        List<MyOrganization> myOrganizationList = Collections.singletonList(myOrganization);

        when(repository.findAll()).thenReturn(myOrganizationList);
        when(mapper.mapEntityToMyOrganizationDTOOnlyName(myOrganizationList.get(0)))
                .thenReturn(new MyOrganizationDTOOnlyName());

        var resultedMyOrganizationDTOList = service.findAll();

        verify(repository).findAll();
        verify(mapper).mapEntityToDTO(myOrganizationList.get(0));
        assertFalse(resultedMyOrganizationDTOList.isEmpty());
        assertEquals(1, resultedMyOrganizationDTOList.size());
    }

    @Test
    void findById() throws Exception {
        var myOrganizationOptional = Optional.of(myOrganization);

        when(repository.findById(id)).thenReturn(myOrganizationOptional);
        when(mapper.mapEntityToDTO(myOrganizationOptional.get())).thenReturn(myOrganizationDTO);

        var resultedMyOrganizationDTO = service.findById(id);

        verify(repository).findById(id);
        verify(mapper).mapEntityToDTO(myOrganizationOptional.get());
        assertNotNull(resultedMyOrganizationDTO);
        assertEquals(id, resultedMyOrganizationDTO.getId());
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
        when(mapper.mapDTOToEntity(myOrganizationDTO)).thenReturn(myOrganization);
        when(repository.save(myOrganization)).thenReturn(myOrganization);
        doNothing().when(entitiesHistoryService)
                .createEntityHistoryRecord(myOrganization, null, HistoryAction.CREATE);
        when(mapper.mapEntityToDTO(myOrganization)).thenReturn(myOrganizationDTO);

        var savedMyOrganizationDTO = service.save(myOrganizationDTO);

        verify(repository).findById(id);
        verify(mapper).mapDTOToEntity(myOrganizationDTO);
        verify(repository).save(myOrganization);
        verify(entitiesHistoryService).createEntityHistoryRecord(myOrganization, null, HistoryAction.CREATE);
        verify(mapper).mapEntityToDTO(myOrganization);
        assertNotNull(savedMyOrganizationDTO);
        assertEquals(id, savedMyOrganizationDTO.getId());
    }

    @Test
    void saveWhenAlreadyExist() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.of(myOrganization));

        assertThrows(DTOFoundWhenSaveException.class, () -> service.save(myOrganizationDTO));
        verify(repository).findById(id);
    }

    @Test
    void update() throws Exception {
        var myOrganizationOptional = Optional.of(myOrganization);

        when(repository.findById(id)).thenReturn(myOrganizationOptional);
        when(mapper.mapEntityToDTO(myOrganizationOptional.get())).thenReturn(myOrganizationDTO);
        when(mapper.mapDTOToEntity(myOrganizationDTO)).thenReturn(myOrganization);
        when(repository.save(myOrganization)).thenReturn(myOrganization);
        doNothing().when(entitiesHistoryService)
                .createEntityHistoryRecord(myOrganization, myOrganization, HistoryAction.UPDATE);

        var resultedMyOrganizationDTO = service.update(myOrganizationDTO);

        verify(repository).findById(id);
        verify(mapper, times(2)).mapEntityToDTO(myOrganizationOptional.get());
        verify(mapper, times(2)).mapDTOToEntity(myOrganizationDTO);
        verify(repository).save(myOrganization);
        verify(entitiesHistoryService).createEntityHistoryRecord(myOrganization, myOrganization, HistoryAction.UPDATE);
        assertNotNull(resultedMyOrganizationDTO);
        assertEquals(id, resultedMyOrganizationDTO.getId());
    }

    @Test
    void updateWhenNotFound() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> service.update(myOrganizationDTO));
        verify(repository).findById(id);
    }

    @Test
    void deleteById() throws Exception {
        var myOrganizationOptional = Optional.of(myOrganization);

        when(repository.findById(id)).thenReturn(myOrganizationOptional);
        when(mapper.mapEntityToDTO(myOrganizationOptional.get())).thenReturn(myOrganizationDTO);
        when(mapper.mapDTOToEntity(myOrganizationDTO)).thenReturn(myOrganization);
        doNothing().when(entitiesHistoryService)
                        .createEntityHistoryRecord(myOrganization, null, HistoryAction.DELETE);
        doNothing().when(repository).deleteById(id);

        service.deleteById(id);

        verify(repository).findById(id);
        verify(mapper).mapEntityToDTO(myOrganizationOptional.get());
        verify(mapper).mapDTOToEntity(myOrganizationDTO);
        verify(entitiesHistoryService).createEntityHistoryRecord(myOrganization, null, HistoryAction.DELETE);
        verify(repository).deleteById(id);
    }

    @Test
    void deleteByIdWhenNotFound() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> service.deleteById(id));
        verify(repository).findById(id);
    }

    @Test
    void findByIsDefaultTrue() throws Exception {
        var myOrganizationOptional = Optional.of(myOrganization);
        MyOrganizationDTOOnlyName myOrganizationDTOOnlyName = new MyOrganizationDTOOnlyName();

        when(repository.findByIsDefaultTrue()).thenReturn(myOrganizationOptional);
        when(mapper.mapEntityToMyOrganizationDTOOnlyName(myOrganizationOptional.get())).thenReturn(myOrganizationDTOOnlyName);

        var resultedMyOrganizationDTO = service.findByIsDefaultTrue();

        verify(repository).findByIsDefaultTrue();
        verify(mapper).mapEntityToMyOrganizationDTOOnlyName(myOrganizationOptional.get());
        assertNotNull(resultedMyOrganizationDTO);
    }

    @Test
    void findByIsDefaultTrueWhenNotFound() throws Exception {
        when(repository.findByIsDefaultTrue()).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> service.findByIsDefaultTrue());
        verify(repository).findByIsDefaultTrue();
    }
}