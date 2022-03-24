package ii.cipriantarlev.marketmanagementapi.myorganization;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class MyOrganizationMapperTest {

    @InjectMocks
    private MyOrganizationMapper mapper;

    @Mock
    private ModelMapper modelMapper;

    private MyOrganizationDTO myOrganizationDTO;
    private MyOrganization myOrganization;
    private MyOrganizationDTOOnlyName myOrganizationDTOOnlyName;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void mapEntityToDTO() throws Exception {
        when(modelMapper.map(myOrganization, MyOrganizationDTO.class)).thenReturn(myOrganizationDTO);

        var returnedValue = mapper.mapEntityToDTO(myOrganization);

        verify(modelMapper).map(myOrganization, MyOrganizationDTO.class);
        assertEquals(myOrganizationDTO, returnedValue);
    }

    @Test
    void mapDTOToEntity() throws Exception {
        when(modelMapper.map(myOrganizationDTO, MyOrganization.class)).thenReturn(myOrganization);

        var returnedValue = mapper.mapDTOToEntity(myOrganizationDTO);

        verify(modelMapper).map(myOrganizationDTO, MyOrganization.class);
        assertEquals(myOrganization, returnedValue);
    }

    @Test
    void mapEntityToMyOrganizationDTOOnlyName() throws Exception {
        when(modelMapper.map(myOrganization, MyOrganizationDTOOnlyName.class)).thenReturn(myOrganizationDTOOnlyName);

        var returnedValue = mapper.mapEntityToMyOrganizationDTOOnlyName(myOrganization);

        verify(modelMapper).map(myOrganization, MyOrganizationDTOOnlyName.class);
        assertEquals(myOrganizationDTOOnlyName, returnedValue);
    }
}