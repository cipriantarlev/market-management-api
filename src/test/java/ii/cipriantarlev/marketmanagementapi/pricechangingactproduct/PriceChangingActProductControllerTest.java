package ii.cipriantarlev.marketmanagementapi.pricechangingactproduct;

import ii.cipriantarlev.marketmanagementapi.utils.RestControllerUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpHeaders;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static ii.cipriantarlev.marketmanagementapi.utils.Constants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class PriceChangingActProductControllerTest {

    @InjectMocks
    private PriceChangingActProductController controller;

    @Mock
    private PriceChangingActProductService service;

    @Mock
    private RestControllerUtil restControllerUtil;

    private final int ok = 200;
    private final UUID id = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void getPriceChangingActProducts() throws Exception {
        List<PriceChangingActProductDTO> priceChangingActProductDTOList = Collections.singletonList(new PriceChangingActProductDTO());

        when(service.findAllByPriceChangingActId(id)).thenReturn(priceChangingActProductDTOList);

        var response = controller.getPriceChangingActProducts(id);

        verify(service).findAllByPriceChangingActId(id);
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(priceChangingActProductDTOList, response.getBody());
    }

    @Test
    void getPriceChangingActProduct() throws Exception {
        PriceChangingActProductDTO priceChangingActProductDTO = PriceChangingActProductDTO.builder().id(id).build();

        when(service.findById(id)).thenReturn(priceChangingActProductDTO);

        var response = controller.getPriceChangingActProduct(id);

        verify(service).findById(id);
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(priceChangingActProductDTO, response.getBody());
    }

    @Test
    void createPriceChangingActProduct() throws Exception {
        PriceChangingActProductDTO priceChangingActProductDTO = PriceChangingActProductDTO.builder().build();
        PriceChangingActProductDTO savedPriceChangingActProductDTO = PriceChangingActProductDTO.builder().id(id).build();

        when(service.save(priceChangingActProductDTO)).thenReturn(savedPriceChangingActProductDTO);
        when(restControllerUtil.setHttpsHeaderLocation(PRICE_CHANGING_ACT_PRODUCTS_ROOT_PATH, savedPriceChangingActProductDTO.getId()))
                .thenReturn(new HttpHeaders());

        var response = controller.createPriceChangingActProduct(priceChangingActProductDTO);

        verify(service).save(priceChangingActProductDTO);
        verify(restControllerUtil).setHttpsHeaderLocation(PRICE_CHANGING_ACT_PRODUCTS_ROOT_PATH, savedPriceChangingActProductDTO.getId());
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(savedPriceChangingActProductDTO, response.getBody());
    }

    @Test
    void updatePriceChangingActProduct() throws Exception {
        PriceChangingActProductDTO priceChangingActProductDTO = PriceChangingActProductDTO.builder().id(id).build();

        when(service.update(priceChangingActProductDTO)).thenReturn(priceChangingActProductDTO);

        var response = controller.updatePriceChangingActProduct(priceChangingActProductDTO);

        verify(service).update(priceChangingActProductDTO);
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(priceChangingActProductDTO, response.getBody());
    }

    @Test
    void deletePriceChangingActProduct() throws Exception {
        doNothing().when(service).deleteById(id);

        var response = controller.deletePriceChangingActProduct(id);

        assertEquals(ok, response.getStatusCodeValue());
        verify(service).deleteById(id);
    }
}