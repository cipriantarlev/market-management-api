package ii.cipriantarlev.marketmanagementapi.vendor;

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

class VendorControllerTest {

    @InjectMocks
    private VendorController controller;

    @Mock
    private VendorService service;

    @Mock
    private RestControllerUtil restControllerUtil;

    private final long id = 1L;
    private final int ok = 200;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void getVendors() throws Exception {
        List<VendorDTONoRegions> vendorDTONoRegionsList = Collections.singletonList(new VendorDTONoRegions());

        when(service.findAll()).thenReturn(vendorDTONoRegionsList);

        var response = controller.getVendors();

        verify(service).findAll();
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(vendorDTONoRegionsList, response.getBody());
    }

    @Test
    void getVendor() throws Exception {
        VendorDTO vendorDTO = VendorDTO.builder().id(id).build();

        when(service.findById(id)).thenReturn(vendorDTO);

        var response = controller.getVendor(id);

        verify(service).findById(id);
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(vendorDTO, response.getBody());
    }

    @Test
    void createVendor() throws Exception {
        VendorDTO vendorDTO = VendorDTO.builder().name("test").build();
        VendorDTO savedVendorDTO = VendorDTO.builder().id(id).name("test").build();

        when(service.save(vendorDTO)).thenReturn(savedVendorDTO);
        when(restControllerUtil
                .setHttpsHeaderLocation(VENDORS_ROOT_PATH.concat(ID_PATH), savedVendorDTO.getId()))
                .thenReturn(new HttpHeaders());

        var response = controller.createVendor(vendorDTO);

        verify(service).save(vendorDTO);
        verify(restControllerUtil).setHttpsHeaderLocation(VENDORS_ROOT_PATH.concat(ID_PATH), savedVendorDTO.getId());
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(savedVendorDTO, response.getBody());
    }

    @Test
    void updateVendor() throws Exception {
        VendorDTO vendorDTO = VendorDTO.builder().id(id).name("test").build();

        when(service.update(vendorDTO)).thenReturn(vendorDTO);

        var response = controller.updateVendor(vendorDTO);

        verify(service).update(vendorDTO);
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(vendorDTO, response.getBody());
    }

    @Test
    void deleteVendor() throws Exception {
        doNothing().when(service).deleteById(id);

        var response = controller.deleteVendor(id);

        verify(service).deleteById(id);
        assertEquals(ok, response.getStatusCodeValue());
    }
}