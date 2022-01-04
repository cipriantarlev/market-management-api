package ii.cipriantarlev.marketmanagementapi.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class UserMapperTest {

    @InjectMocks
    private UserMapper mapper;

    @Mock
    private ModelMapper modelMapper;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void mapUserToUserDTO() throws Exception {
        when(modelMapper.map(user, UserDTO.class)).thenReturn(userDTO);

        var returnedValue = mapper.mapUserToUserDTO(user);

        verify(modelMapper).map(user, UserDTO.class);
        assertEquals(userDTO, returnedValue);
    }

    @Test
    void mapUserDTOToUser() throws Exception {
        when(modelMapper.map(userDTO, User.class)).thenReturn(user);

        var returnedValue = mapper.mapUserDTOToUser(userDTO);

        verify(modelMapper).map(userDTO, User.class);
        assertEquals(user, returnedValue);
    }
}