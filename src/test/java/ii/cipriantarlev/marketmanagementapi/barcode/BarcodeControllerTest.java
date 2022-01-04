package ii.cipriantarlev.marketmanagementapi.barcode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class BarcodeControllerTest {

    @InjectMocks
    private BarcodeController barcodeController;

    @Mock
    private BarcodeService barcodeService;

    private final long id = 1L;
    private final int ok = 200;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void getAllBarcodes() {
        List<BarcodeDTO> barcodes = Collections.singletonList(new BarcodeDTO());

        when(barcodeService.findAll()).thenReturn(barcodes);

        var response = barcodeController.getAllBarcodes();

        assertEquals(barcodes, response.getBody());
        assertEquals(ok, response.getStatusCodeValue());
    }

    @Test
    void getBarcode() {
        BarcodeDTO barcodeDTO = BarcodeDTO.builder().id(id).build();

        when(barcodeService.findById(id)).thenReturn(barcodeDTO);

        var response = barcodeController.getBarcode(id);

        assertEquals(barcodeDTO, response.getBody());
        assertEquals(ok, response.getStatusCodeValue());
    }

    @Test
    void saveOrGenerateBarcode() {
        BarcodeDTO barcodeDTO = BarcodeDTO.builder().id(id).value("48400035793").build();

        when(barcodeService.generateNewBarcode(barcodeDTO)).thenReturn(barcodeDTO);

        var response = barcodeController.saveOrGenerateBarcode(barcodeDTO);

        assertEquals(barcodeDTO, response.getBody());
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(barcodeDTO.getValue(), response.getBody().getValue());
    }

    @Test
    void deleteBarcode() {
        doNothing().when(barcodeService).deleteById(id);

        var response = barcodeController.deleteBarcode(id);

        assertEquals(ok, response.getStatusCodeValue());
    }

    @Test
    void checkIfValueExists() {
        String value = "2200000000001";

        when(barcodeService.checkIfValueExists(value)).thenReturn(true);

        var response = barcodeController.checkIfValueExists(value);

        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(Boolean.TRUE, response.getBody());
    }
}