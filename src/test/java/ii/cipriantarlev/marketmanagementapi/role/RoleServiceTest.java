package ii.cipriantarlev.marketmanagementapi.role;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class RoleServiceTest {

    @InjectMocks
    private RoleServiceImpl service;

    @Mock
    private RoleRepository repository;

    @Mock
    private RoleMapper mapper;

    private Role role;
    private RoleDTO roleDTO;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void findAll() throws Exception {
        List<Role> roleList = Collections.singletonList(role);

        when(repository.findAll()).thenReturn(roleList);
        when(mapper.mapRoleToRoleDTO(roleList.get(0))).thenReturn(roleDTO);

        var resultedRoleList = service.findAll();

        verify(repository).findAll();
        verify(mapper).mapRoleToRoleDTO(roleList.get(0));
        assertFalse(resultedRoleList.isEmpty());
        assertEquals(1, resultedRoleList.size());
    }
}