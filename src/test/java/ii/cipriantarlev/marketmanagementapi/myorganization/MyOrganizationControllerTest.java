package ii.cipriantarlev.marketmanagementapi.myorganization;

import ii.cipriantarlev.marketmanagementapi.utils.RestControllerUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpHeaders;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static ii.cipriantarlev.marketmanagementapi.utils.Constants.*;

class MyOrganizationControllerTest {

    @InjectMocks
    private MyOrganizationController controller;

    @Mock
    private MyOrganizationService service;

    @Mock
    private RestControllerUtil restControllerUtil;

    private final long id = 1L;
    private final int ok = 200;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void getMyOrganizations() throws Exception {
        List<MyOrganizationDTO> myOrganizationDTOList = Collections.singletonList(new MyOrganizationDTO());

        when(service.findAll()).thenReturn(myOrganizationDTOList);

        var response = controller.getMyOrganizations();

        verify(service).findAll();
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(myOrganizationDTOList, response.getBody());
    }

    @Test
    void getMyOrganization() throws Exception {
        MyOrganizationDTO myOrganizationDTO = MyOrganizationDTO.builder().id(id).build();

        when(service.findById(id)).thenReturn(myOrganizationDTO);

        var response = controller.getMyOrganization(id);

        verify(service).findById(id);
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(myOrganizationDTO, response.getBody());
    }

    @Test
    void createMyOrganization() throws Exception {
        MyOrganizationDTO myOrganizationDTO = MyOrganizationDTO.builder().name("Test").build();
        MyOrganizationDTO savedMyOrganizationDTO = MyOrganizationDTO.builder().id(id).name("Test").build();

        when(service.save(myOrganizationDTO)).thenReturn(savedMyOrganizationDTO);
        when(restControllerUtil
                .setHttpsHeaderLocation(MY_ORGANIZATIONS_ROOT_PATH.concat(ID_PATH), savedMyOrganizationDTO.getId()))
                .thenReturn(new HttpHeaders());

        var response = controller.createMyOrganization(myOrganizationDTO);

        verify(service).save(myOrganizationDTO);
        verify(restControllerUtil)
                .setHttpsHeaderLocation(MY_ORGANIZATIONS_ROOT_PATH.concat(ID_PATH), savedMyOrganizationDTO.getId());
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(savedMyOrganizationDTO, response.getBody());
    }

    @Test
    void updateMyOrganization() throws Exception {
        MyOrganizationDTO myOrganizationDTO = MyOrganizationDTO.builder().id(id).name("Test").build();

        when(service.update(myOrganizationDTO)).thenReturn(myOrganizationDTO);

        var response = controller.updateMyOrganization(myOrganizationDTO);

        verify(service).update(myOrganizationDTO);
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(myOrganizationDTO, response.getBody());
    }

    @Test
    void deletedMyOrganization() throws Exception {
        doNothing().when(service).deleteById(id);

        var response = controller.deletedMyOrganization(id);

        verify(service).deleteById(id);
        assertEquals(ok, response.getStatusCodeValue());
    }
}