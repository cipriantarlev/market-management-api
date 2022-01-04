package ii.cipriantarlev.marketmanagementapi.region;

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

class RegionControllerTest {

    @InjectMocks
    private RegionController controller;

    @Mock
    private RegionService service;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void getRegions() throws Exception {
        List<RegionDTO> regionDTOList = Collections.singletonList(new RegionDTO());

        when(service.findAll()).thenReturn(regionDTOList);

        var response = controller.getRegions();

        verify(service).findAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(regionDTOList, response.getBody());
    }
}