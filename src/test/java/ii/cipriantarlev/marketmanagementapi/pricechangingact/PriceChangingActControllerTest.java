package ii.cipriantarlev.marketmanagementapi.pricechangingact;

import ii.cipriantarlev.marketmanagementapi.utils.RestControllerUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpHeaders;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static ii.cipriantarlev.marketmanagementapi.utils.Constants.PRICE_CHANGING_ACTS_ROOT_PATH;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class PriceChangingActControllerTest {

    @InjectMocks
    private PriceChangingActController controller;

    @Mock
    private PriceChangingActService service;

    @Mock
    private RestControllerUtil restControllerUtil;

    private final int ok = 200;
    private final UUID id = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void getPriceChangingActs() throws Exception {
        List<PriceChangingActDTO> priceChangingActDTOS = Collections.singletonList(new PriceChangingActDTO());

        when(service.findAll()).thenReturn(priceChangingActDTOS);

        var response = controller.getPriceChangingActs();

        verify(service).findAll();
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(priceChangingActDTOS, response.getBody());
    }

    @Test
    void getPriceChangingAct() throws Exception {
        PriceChangingActDTO priceChangingActDTO = PriceChangingActDTO.builder().id(id).build();

        when(service.findById(id)).thenReturn(priceChangingActDTO);

        var response = controller.getPriceChangingAct(id);

        verify(service).findById(id);
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(priceChangingActDTO, response.getBody());
    }

    @Test
    void createPriceChangingAct() throws Exception {
        PriceChangingActDTO priceChangingActDTO = PriceChangingActDTO.builder().build();
        PriceChangingActDTO savedPriceChangingActDTO = PriceChangingActDTO.builder().id(id).build();

        when(service.save(priceChangingActDTO)).thenReturn(savedPriceChangingActDTO);
        when(restControllerUtil.setHttpsHeaderLocation(PRICE_CHANGING_ACTS_ROOT_PATH, savedPriceChangingActDTO.getId()))
                .thenReturn(new HttpHeaders());

        var response = controller.createPriceChangingAct(priceChangingActDTO);

        verify(service).save(priceChangingActDTO);
        verify(restControllerUtil).setHttpsHeaderLocation(PRICE_CHANGING_ACTS_ROOT_PATH, savedPriceChangingActDTO.getId());
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(savedPriceChangingActDTO, response.getBody());
    }

    @Test
    void updatePriceChangingAct() throws Exception {
        PriceChangingActDTO priceChangingActDTO = PriceChangingActDTO.builder().id(id).build();

        when(service.update(priceChangingActDTO)).thenReturn(priceChangingActDTO);

        var response = controller.updatePriceChangingAct(priceChangingActDTO);

        verify(service).update(priceChangingActDTO);
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(priceChangingActDTO, response.getBody());
    }

    @Test
    void updateApprovedMarker() throws Exception {
        Map<Boolean, List<UUID>> priceChangingActs = Collections.singletonMap(true, Collections.singletonList(id));
        when(service.updateIsApprovedMarker(priceChangingActs)).thenReturn(1);

        var response = controller.updateApprovedMarker(priceChangingActs);

        assertEquals(1, response.getBody());
        assertEquals(ok, response.getStatusCodeValue());
        verify(service).updateIsApprovedMarker(priceChangingActs);
    }

    @Test
    void deletePriceActChanging() throws Exception {
        doNothing().when(service).deleteById(id);

        var response = controller.deletePriceActChanging(id);

        assertEquals(ok, response.getStatusCodeValue());
        verify(service).deleteById(id);
    }
}