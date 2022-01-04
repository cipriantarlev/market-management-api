package ii.cipriantarlev.marketmanagementapi.barcode;

import ii.cipriantarlev.marketmanagementapi.exceptions.DTOListNotFoundException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTONotFoundException;
import ii.cipriantarlev.marketmanagementapi.history.EntitiesHistoryService;
import ii.cipriantarlev.marketmanagementapi.history.HistoryAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class BarcodeServiceTest {

    @InjectMocks
    private BarcodeServiceImpl barcodeService;

    @Mock
    private BarcodeRepository barcodeRepository;

    @Mock
    private BarcodeMapper barcodeMapper;

    @Mock
    private EntitiesHistoryService entitiesHistoryService;

    private final Barcode barcode = new Barcode();

    private final long id = 1L;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void findAllWhenListIsNotEmpty() {
        List<Barcode> barcodes = Collections.singletonList(barcode);

        when(barcodeRepository.findAll()).thenReturn(barcodes);
        when(barcodeMapper.mapEntityToDTO(barcodes.get(0))).thenReturn(new BarcodeDTO());

        var barcodeDTOList = barcodeService.findAll();

        verify(barcodeRepository).findAll();
        verify(barcodeMapper).mapEntityToDTO(barcodes.get(0));
        assertFalse(barcodeDTOList.isEmpty());
    }

    @Test
    void findAllWhenListIsEmpty() {
        when(barcodeRepository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(DTOListNotFoundException.class, () -> barcodeService.findAll());
        verify(barcodeRepository).findAll();
    }

    @Test
    void findById() {
        var barcodeOptional = Optional.of(barcode);

        when(barcodeRepository.findById(id)).thenReturn(barcodeOptional);
        when(barcodeMapper.mapEntityToDTO(barcodeOptional.get())).thenReturn(new BarcodeDTO());

        var barcodeDTO = barcodeService.findById(id);

        verify(barcodeRepository).findById(id);
        verify(barcodeMapper).mapEntityToDTO(barcodeOptional.get());
        assertNotNull(barcodeDTO);
    }

    @Test
    void findByIdNotFound() {
        when(barcodeRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> barcodeService.findById(id));
        verify(barcodeRepository).findById(id);
    }

    @Test
    void generateNewBarcodeWhenSave() {
        BarcodeDTO barcodeDTO = BarcodeDTO.builder().id(1L).value("4859054").build();

        assertEquals(barcodeDTO, barcodeService.generateNewBarcode(barcodeDTO));
    }

    @Test
    void generateNewBarcodeWhenValueIs22AndLastBarcodeIsNull() {
        BarcodeDTO barcodeDTO = BarcodeDTO.builder().id(1L).value("22").build();

        when(barcodeRepository.findFirst1ByValueStartingWithOrderByValueDesc(barcodeDTO.getValue())).thenReturn(null);

        var newBarcode = barcodeService.generateNewBarcode(barcodeDTO);

        verify(barcodeRepository).findFirst1ByValueStartingWithOrderByValueDesc(barcodeDTO.getValue());
        assertEquals("2200000000000", newBarcode.getValue());
    }

    @Test
    void generateNewBarcodeWhenValueIs21() {
        BarcodeDTO barcodeDTO = BarcodeDTO.builder().id(1L).value("21").build();
        barcode.setValue("2100000");

        when(barcodeRepository.findFirst1ByValueStartingWithOrderByValueDesc(barcodeDTO.getValue())).thenReturn(barcode);

        var newBarcode = barcodeService.generateNewBarcode(barcodeDTO);

        verify(barcodeRepository).findFirst1ByValueStartingWithOrderByValueDesc(barcodeDTO.getValue());
        assertEquals("2100001", newBarcode.getValue());
    }

    @Test
    void deleteById() {
        var barcodeOptional = Optional.of(barcode);
        BarcodeDTO barcodeDTO = BarcodeDTO.builder().id(1L).value("21").build();

        when(barcodeRepository.findById(id)).thenReturn(barcodeOptional);
        when(barcodeMapper.mapEntityToDTO(barcodeOptional.get())).thenReturn(barcodeDTO);
        when(barcodeMapper.mapDTOToEntity(barcodeDTO)).thenReturn(barcode);
        doNothing().when(entitiesHistoryService).createEntityHistoryRecord(barcode, null, HistoryAction.DELETE);
        doNothing().when(barcodeRepository).deleteById(id);

        barcodeService.deleteById(id);

        verify(barcodeRepository).findById(id);
        verify(barcodeMapper).mapEntityToDTO(barcodeOptional.get());
        verify(barcodeMapper).mapDTOToEntity(barcodeDTO);
        verify(entitiesHistoryService).createEntityHistoryRecord(barcode, null, HistoryAction.DELETE);
        verify(barcodeRepository).deleteById(id);
    }

    @Test
    void deleteByIdWhenNotFound() {
        when(barcodeRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> barcodeService.deleteById(id));
        verify(barcodeRepository).findById(id);
    }

    @Test
    void deleteBarcodeWithNullProductId() {
        List<Barcode> barcodes = Collections.singletonList(barcode);

        when(barcodeRepository.findAllByProductIdNull()).thenReturn(barcodes);
        doNothing().when(barcodeRepository).deleteAll(barcodes);

        barcodeService.deleteBarcodeWithNullProductId();

        verify(barcodeRepository).findAllByProductIdNull();
        verify(barcodeRepository).deleteAll(barcodes);
    }

    @Test
    void checkIfValueExistsWhenTrue() {
        String value = "2100000";

        when(barcodeRepository.findByValue(value)).thenReturn(barcode);

        boolean doesValueExist = barcodeService.checkIfValueExists(value);

        assertTrue(doesValueExist);
        verify(barcodeRepository).findByValue(value);
    }

    @Test
    void checkIfValueExistsWhenFalse() {
        String value = "2100000";

        when(barcodeRepository.findByValue(value)).thenReturn(null);

        boolean doesValueExist = barcodeService.checkIfValueExists(value);

        assertFalse(doesValueExist);
        verify(barcodeRepository).findByValue(value);
    }
}