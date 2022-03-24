package ii.cipriantarlev.marketmanagementapi.user;

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

class UserServiceTest {

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper mapper;

    @Mock
    private EntitiesHistoryService entitiesHistoryService;

    private User user;
    private UserDTO userDTO;
    private final long id = 1L;

    @BeforeEach
    void setUp() {
        openMocks(this);
        user = new User();
        user.setId(id);
        userDTO = UserDTO.builder().id(id).build();
    }

    @Test
    void findAll() throws Exception {
        List<User> userList = Collections.singletonList(user);

        when(repository.findAll()).thenReturn(userList);
        when(mapper.mapUserToUserDTO(user)).thenReturn(userDTO);

        var resultedUserList = service.findAll();

        verify(repository).findAll();
        verify(mapper).mapUserToUserDTO(user);
        assertFalse(resultedUserList.isEmpty());
        assertEquals(1, resultedUserList.size());
    }

    @Test
    void findById() throws Exception {
        var userOptional = Optional.of(user);

        when(repository.findById(id)).thenReturn(userOptional);
        when(mapper.mapUserToUserDTO(userOptional.get())).thenReturn(userDTO);

        var resultedUserDTO = service.findById(id);

        verify(repository).findById(id);
        verify(mapper).mapUserToUserDTO(userOptional.get());
        assertNotNull(resultedUserDTO);
        assertEquals(id, resultedUserDTO.getId());
    }

    @Test
    void findByIdWhenNotFound() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> service.findById(id));
        verify(repository).findById(id);
    }

    @Test
    void findByUsername() throws Exception {
        final String username = "test";
        user.setUsername(username);
        userDTO.setUsername(username);

        when(repository.findByUsername(username)).thenReturn(user);
        when(mapper.mapUserToUserDTO(user)).thenReturn(userDTO);

        var resultedUserDTO = service.findByUsername(username);

        verify(repository).findByUsername(username);
        verify(mapper).mapUserToUserDTO(user);
        assertNotNull(resultedUserDTO);
        assertEquals(userDTO, resultedUserDTO);
    }

    @Test
    void findByUsernameWhenNotFound() throws Exception {
        final String username = "test";

        when(repository.findByUsername(username)).thenReturn(null);

        assertThrows(DTONotFoundException.class, () -> service.findByUsername(username));
        verify(repository).findByUsername(username);
    }

    @Test
    void save() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.empty());
        when(mapper.mapUserDTOToUser(userDTO)).thenReturn(user);
        when(repository.save(user)).thenReturn(user);
        doNothing().when(entitiesHistoryService)
                .createEntityHistoryRecord(user, null, HistoryAction.CREATE);
        when(mapper.mapUserToUserDTO(user)).thenReturn(userDTO);

        var resultedUserDTO = service.save(userDTO);

        verify(repository).findById(id);
        verify(mapper).mapUserDTOToUser(userDTO);
        verify(repository).save(user);
        verify(entitiesHistoryService).createEntityHistoryRecord(user, null, HistoryAction.CREATE);
        verify(mapper).mapUserToUserDTO(user);
        assertNotNull(resultedUserDTO);
        assertEquals(userDTO, resultedUserDTO);
    }

    @Test
    void saveWhenAlreadyExist() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.of(user));;

        assertThrows(DTOFoundWhenSaveException.class, () -> service.save(userDTO));
        verify(repository).findById(id);
    }

    @Test
    void update() throws Exception {
        var userOptional = Optional.of(user);

        when(repository.findById(id)).thenReturn(userOptional);
        when(mapper.mapUserToUserDTO(userOptional.get())).thenReturn(userDTO);
        when(mapper.mapUserDTOToUser(userDTO)).thenReturn(user);
        when(repository.save(user)).thenReturn(user);
        doNothing().when(entitiesHistoryService)
                .createEntityHistoryRecord(user, user, HistoryAction.UPDATE);

        var resultedUserDTO = service.update(userDTO);

        verify(repository).findById(id);
        verify(mapper, times(2)).mapUserToUserDTO(userOptional.get());
        verify(mapper, times(2)).mapUserDTOToUser(userDTO);
        verify(repository).save(user);
        verify(entitiesHistoryService).createEntityHistoryRecord(user, user, HistoryAction.UPDATE);
        assertNotNull(resultedUserDTO);
        assertEquals(id, resultedUserDTO.getId());
    }

    @Test
    void updateWhenNotFound() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> service.update(userDTO));
        verify(repository).findById(id);
    }

    @Test
    void deleteById() throws Exception {
        var userOptional = Optional.of(user);

        when(repository.findById(id)).thenReturn(userOptional);
        when(mapper.mapUserToUserDTO(userOptional.get())).thenReturn(userDTO);
        when(mapper.mapUserDTOToUser(userDTO)).thenReturn(user);
        doNothing().when(entitiesHistoryService)
                .createEntityHistoryRecord(user, null, HistoryAction.DELETE);
        doNothing().when(repository).deleteById(id);

        service.deleteById(id);

        verify(repository).findById(id);
        verify(mapper).mapUserToUserDTO(userOptional.get());
        verify(mapper).mapUserDTOToUser(userDTO);
        verify(entitiesHistoryService).createEntityHistoryRecord(user, null, HistoryAction.DELETE);
        verify(repository).deleteById(id);
    }

    @Test
    void deleteByIdWhenNotFound() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> service.deleteById(id));
        verify(repository).findById(id);
    }
}