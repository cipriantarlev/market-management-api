package ii.cipriantarlev.marketmanagementapi.role;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class RoleControllerTest {

    @InjectMocks
    private RoleController controller;

    @Mock
    private RoleService service;

    private Role role;
    private RoleDTO roleDTO;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void getRoles() throws Exception {
        List<RoleDTO> roleDTOList = Collections.singletonList(roleDTO);

        when(service.findAll()).thenReturn(roleDTOList);

        var response = controller.getRoles();

        verify(service).findAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(roleDTOList, response.getBody());
    }
}