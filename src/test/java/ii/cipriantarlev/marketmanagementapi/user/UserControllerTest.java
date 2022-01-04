package ii.cipriantarlev.marketmanagementapi.user;

import ii.cipriantarlev.marketmanagementapi.utils.RestControllerUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpHeaders;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ii.cipriantarlev.marketmanagementapi.utils.Constants.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class UserControllerTest {

    @InjectMocks
    private UserController controller;

    @Mock
    private UserService service;

    @Mock
    private RestControllerUtil restControllerUtil;

    private final long id = 1L;
    private final int ok = 200;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void getUsers() throws Exception {
        List<UserDTO> userDTOList = Collections.singletonList(new UserDTO());

        when(service.findAll()).thenReturn(userDTOList);

        var response = controller.getUsers();

        verify(service).findAll();
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(userDTOList, response.getBody());
    }

    @Test
    void getUser() throws Exception {
        UserDTO userDTO = UserDTO.builder().id(id).build();

        when(service.findById(id)).thenReturn(userDTO);

        var response = controller.getUser(id);

        verify(service).findById(id);
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(userDTO, response.getBody());
    }

    @Test
    void createUser() throws Exception {
        UserDTO userDTO = UserDTO.builder().username("cip").build();
        UserDTO savedUserDTO = UserDTO.builder().id(id).username("cip").build();

        when(service.save(userDTO)).thenReturn(savedUserDTO);
        when(restControllerUtil
                .setHttpsHeaderLocation(USERS_ROOT_PATH.concat(ID_PATH), savedUserDTO.getId()))
                .thenReturn(new HttpHeaders());

        var response = controller.createUser(userDTO);

        verify(service).save(userDTO);
        verify(restControllerUtil).setHttpsHeaderLocation(USERS_ROOT_PATH.concat(ID_PATH), savedUserDTO.getId());
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(savedUserDTO, response.getBody());
    }

    @Test
    void updateUser() throws Exception {
        UserDTO userDTO = UserDTO.builder().id(id).username("cip").build();

        when(service.update(userDTO)).thenReturn(userDTO);

        var response = controller.updateUser(userDTO);

        verify(service).update(userDTO);
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(userDTO, response.getBody());
    }

    @Test
    void deleteUser() throws Exception {
        doNothing().when(service).deleteById(id);

        var response = controller.deleteUser(id);

        verify(service).deleteById(id);
        assertEquals(ok, response.getStatusCodeValue());
    }
}