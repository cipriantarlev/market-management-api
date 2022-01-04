package ii.cipriantarlev.marketmanagementapi.role;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class RoleMapperTest {

    @InjectMocks
    private RoleMapper mapper;

    @Mock
    private ModelMapper modelMapper;

    private Role role;
    private RoleDTO roleDTO;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void mapRoleToRoleDTO() throws Exception {
        when(modelMapper.map(role, RoleDTO.class)).thenReturn(roleDTO);

        var returnedValue = mapper.mapRoleToRoleDTO(role);

        verify(modelMapper).map(role, RoleDTO.class);
        assertEquals(roleDTO, returnedValue);
    }

    @Test
    void mapRoleDTOToRole() throws Exception {
        when(modelMapper.map(roleDTO, Role.class)).thenReturn(role);

        var returnedValue = mapper.mapRoleDTOToRole(roleDTO);

        verify(modelMapper).map(roleDTO, Role.class);
        assertEquals(role, returnedValue);
    }
}