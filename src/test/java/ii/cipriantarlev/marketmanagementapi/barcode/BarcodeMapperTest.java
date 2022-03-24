package ii.cipriantarlev.marketmanagementapi.barcode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class BarcodeMapperTest {

    @InjectMocks
    private BarcodeMapper barcodeMapper;

    @Mock
    private ModelMapper modelMapper;

    private BarcodeDTO barcodeDTO;
    private Barcode barcode;

    @BeforeEach
    void setUp() {
        openMocks(this);
        barcodeDTO = BarcodeDTO.builder().id(1L).value("2000").build();
        barcode = new Barcode();
        barcode.setId(1L);
        barcode.setValue("2000");
    }

    @Test
    void mapEntityToDTO() {
        when(modelMapper.map(barcode, BarcodeDTO.class)).thenReturn(barcodeDTO);

        var resultedBarcodeDTO = barcodeMapper.mapEntityToDTO(barcode);

        verify(modelMapper).map(barcode, BarcodeDTO.class);
        assertEquals(barcodeDTO, resultedBarcodeDTO);
    }

    @Test
    void mapDTOToEntity() {
        when(modelMapper.map(barcodeDTO, Barcode.class)).thenReturn(barcode);

        var resultedBarcode = barcodeMapper.mapDTOToEntity(barcodeDTO);

        verify(modelMapper).map(barcodeDTO, Barcode.class);
        assertEquals(barcode, resultedBarcode);
    }
}