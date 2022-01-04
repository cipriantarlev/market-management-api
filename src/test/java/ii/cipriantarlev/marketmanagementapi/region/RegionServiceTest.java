package ii.cipriantarlev.marketmanagementapi.region;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class RegionServiceTest {

    @InjectMocks
    private RegionServiceImpl service;

    @Mock
    private RegionRepository repository;

    @Mock
    private RegionMapper mapper;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void findAll() throws Exception {
        List<Region> regionList = Collections.singletonList(new Region());

        when(repository.findAll()).thenReturn(regionList);
        when(mapper.mapRegionToRegionDTO(regionList.get(0))).thenReturn(new RegionDTO());

        var resultedRegionList = service.findAll();

        verify(repository).findAll();
        verify(mapper).mapRegionToRegionDTO(regionList.get(0));
        assertFalse(resultedRegionList.isEmpty());
        assertEquals(1, resultedRegionList.size());
    }
}