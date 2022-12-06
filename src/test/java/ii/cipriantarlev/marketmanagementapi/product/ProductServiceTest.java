package ii.cipriantarlev.marketmanagementapi.product;

import ii.cipriantarlev.marketmanagementapi.barcode.BarcodeService;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTOFoundWhenSaveException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTONotFoundException;
import ii.cipriantarlev.marketmanagementapi.exceptions.PriceLabelGenerationException;
import ii.cipriantarlev.marketmanagementapi.history.HistoryAction;
import ii.cipriantarlev.marketmanagementapi.product.history.ProductHistoryService;
import ii.cipriantarlev.marketmanagementapi.utils.CreateLabel;
import ii.cipriantarlev.marketmanagementapi.utils.MarketManagementFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl service;

    @Mock
    private ProductRepository repository;

    @Mock
    private ProductMapper mapper;

    @Mock
    private BarcodeService barcodeService;

    @Mock
    private ProductHistoryService productHistoryService;

    @Mock
    private CreateLabel createLabel;

    @Mock
    private MarketManagementFactory marketManagementFactory;

    private Product product;
    private ProductDTO productDTO;
    private final long id = 1L;
    private ProductDTOForList productDTOForList;
    List<ProductDTOForList> markedProductsForPrint = new ArrayList<>();

    @BeforeEach
    void setUp() {
        openMocks(this);
        product = new Product();
        product.setId(id);
        product.setRetailPrice(BigDecimal.TEN);
        product.setOldRetailPrice(BigDecimal.ONE);
        productDTO = ProductDTO.builder()
                .id(id)
                .retailPrice(BigDecimal.TEN)
                .oldRetailPrice(BigDecimal.ONE)
                .build();
    }

    @Test
    void findAll() throws Exception {
        List<Product> products = Collections.singletonList(product);

        when(repository.findAll()).thenReturn(products);
        when(mapper.mapEntityToDTOForList(products.get(0))).thenReturn(productDTOForList);

        var resultedProductList = service.findAll();

        verify(repository).findAll();
        verify(mapper).mapEntityToDTOForList(products.get(0));
        assertFalse(resultedProductList.isEmpty());
        assertEquals(1, resultedProductList.size());
    }

    @Test
    void findByBarcodeValue() throws Exception {
        final String barcodeValue = "22000230043555";

        when(repository.findByBarcodeValue(barcodeValue)).thenReturn(product);
        when(mapper.mapEntityToDTO(product)).thenReturn(productDTO);

        var resultedProductDTO = service.findByBarcodeValue(barcodeValue);

        verify(repository).findByBarcodeValue(barcodeValue);
        verify(mapper).mapEntityToDTO(product);
        assertNotNull(resultedProductDTO);
        assertEquals(productDTO, resultedProductDTO);
    }

    @Test
    void findByBarcodeValueWhenNotFound() throws Exception {
        final String barcodeValue = "22000230043555";

        when(repository.findByBarcodeValue(barcodeValue)).thenReturn(null);

        assertThrows(DTONotFoundException.class, () -> service.findByBarcodeValue(barcodeValue));
        verify(repository).findByBarcodeValue(barcodeValue);
    }

    @Test
    void findById() throws Exception {
        var productOptional = Optional.of(product);

        when(repository.findById(id)).thenReturn(productOptional);
        when(mapper.mapEntityToDTO(productOptional.get())).thenReturn(productDTO);

        var resultedProductDTO = service.findById(id);

        verify(repository).findById(id);
        verify(mapper).mapEntityToDTO(productOptional.get());
        assertNotNull(resultedProductDTO);
        assertEquals(productDTO, resultedProductDTO);
    }

    @Test
    void findByIdWhenNotFound() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> service.findById(id));
        verify(repository).findById(id);
    }

    @Test
    void save() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.empty());
        when(mapper.mapDTOToEntity(productDTO)).thenReturn(product);
        when(repository.save(product)).thenReturn(product);
        doNothing().when(barcodeService).deleteBarcodeWithNullProductId();
        when(mapper.mapEntityToDTO(product)).thenReturn(productDTO);
        doNothing().when(productHistoryService).createProductHistoryRecord(productDTO, HistoryAction.CREATE);

        var resultedProductDTO = service.save(productDTO);

        verify(repository).findById(id);
        verify(mapper).mapDTOToEntity(productDTO);
        verify(repository).save(product);
        verify(barcodeService).deleteBarcodeWithNullProductId();
        verify(mapper).mapEntityToDTO(product);
        verify(productHistoryService).createProductHistoryRecord(productDTO, HistoryAction.CREATE);
        assertNotNull(resultedProductDTO);
        assertEquals(id, resultedProductDTO.getId());
    }

    @Test
    void saveWhenAlreadyExist() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.of(product));

        assertThrows(DTOFoundWhenSaveException.class, () -> service.save(productDTO));
        verify(repository).findById(id);
    }

    @Test
    void update() throws Exception {
        var productOptional = Optional.of(product);

        when(repository.findById(id)).thenReturn(productOptional);
        when(mapper.mapEntityToDTO(productOptional.get())).thenReturn(productDTO);
        when(mapper.mapDTOToEntity(productDTO)).thenReturn(product);
        when(repository.save(product)).thenReturn(product);
        doNothing().when(barcodeService).deleteBarcodeWithNullProductId();
        doNothing().when(productHistoryService).createProductHistoryRecord(productDTO, HistoryAction.UPDATE);

        var resultedProductDTO = service.update(productDTO);

        verify(repository).findById(id);
        verify(mapper, times(2)).mapEntityToDTO(productOptional.get());
        verify(mapper).mapDTOToEntity(productDTO);
        verify(repository).save(product);
        verify(barcodeService).deleteBarcodeWithNullProductId();
        verify(productHistoryService).createProductHistoryRecord(productDTO, HistoryAction.UPDATE);
        assertNotNull(resultedProductDTO);
        assertEquals(id, resultedProductDTO.getId());
    }

    @Test
    void updateWhenNotFound() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> service.update(productDTO));
        verify(repository).findById(id);
    }

    @Test
    void deleteById() throws Exception {
        var productOptional = Optional.of(product);

        when(repository.findById(id)).thenReturn(productOptional);
        when(mapper.mapEntityToDTO(productOptional.get())).thenReturn(productDTO);
        doNothing().when(productHistoryService).createProductHistoryRecord(productDTO, HistoryAction.DELETE);
        doNothing().when(repository).deleteById(id);

        service.deleteById(id);

        verify(repository).findById(id);
        verify(mapper).mapEntityToDTO(productOptional.get());
        verify(productHistoryService).createProductHistoryRecord(productDTO, HistoryAction.DELETE);
        verify(repository).deleteById(id);
    }

    @Test
    void deleteByIdWhenNotFound() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> service.deleteById(id));
        verify(repository).findById(id);
    }

    @Test
    void checkIfNameRomExistsWhenTrue() throws Exception {
        when(repository.findByNameRom("Test")).thenReturn(product);

        assertTrue(service.checkIfNameRomExists("Test"));
    }
    @Test
    void checkIfNameRomExistsWhenFalse() throws Exception {
        when(repository.findByNameRom("Test")).thenReturn(null);

        assertFalse(service.checkIfNameRomExists("Test"));
    }

    @Test
    void checkIfNameRusExistsWhenTrue() throws Exception {
        when(repository.findByNameRus("Тест")).thenReturn(product);

        assertTrue(service.checkIfNameRusExists("Тест"));
    }

    @Test
    void checkIfNameRusExistsWhenFalse() throws Exception {
        when(repository.findByNameRus("Тест")).thenReturn(product);

        assertTrue(service.checkIfNameRusExists("Тест"));
    }

    @Test
    void updateIsCheckedMarker() throws Exception {
        var productOptional = Optional.of(product);
        Map<Boolean, List<Long>> productsToUpdate = Collections.singletonMap(true, Collections.singletonList(id));

        when(repository.findById(id)).thenReturn(productOptional);
        when(mapper.mapDTOToEntity(productDTO)).thenReturn(product);
        when(mapper.mapEntityToDTO(productOptional.get())).thenReturn(productDTO);
        doNothing().when(productHistoryService).createProductHistoryRecord(productDTO, HistoryAction.UPDATE);
        when(repository.updateIsCheckedMarker(true, id)).thenReturn(1);

        var updatedRows = service.updateIsCheckedMarker(productsToUpdate);

        verify(repository).findById(id);
        verify(mapper, times(2)).mapEntityToDTO(productOptional.get());
        verify(mapper).mapDTOToEntity(productDTO);
        verify(productHistoryService).createProductHistoryRecord(productDTO, HistoryAction.UPDATE);
        verify(repository).updateIsCheckedMarker(true, id);
        assertEquals(1, updatedRows);
    }

    @Test
    void updateIsCheckedMarkerWhenNotFound() throws Exception {
        Map<Boolean, List<Long>> productsToUpdate = Collections.singletonMap(true, Collections.singletonList(id));

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> service.updateIsCheckedMarker(productsToUpdate));
        verify(repository).findById(id);
    }

    @Test
    void findAllMarkedProduct() throws Exception {
        List<Product> products = Collections.singletonList(product);
        productDTOForList = new ProductDTOForList();

        when(repository.findByIsCheckedTrue()).thenReturn(products);
        when(mapper.mapEntityToDTOForList(products.get(0))).thenReturn(productDTOForList);

        var resultedProductList = service.findAllMarkedProduct();

        verify(repository).findByIsCheckedTrue();
        verify(mapper).mapEntityToDTOForList(products.get(0));
        assertFalse(resultedProductList.isEmpty());
        assertEquals(1, resultedProductList.size());
    }

    @Test
    void printMarkedProducts() throws Exception {
        Map<Long, Integer> productsToPrint = Collections.singletonMap(id, 1);
        productDTOForList = ProductDTOForList.builder().id(id).build();
        Optional<Product> productOptional = Optional.of(this.product);
        Optional<byte[]> priceLabelBytes = Optional.of(new byte[8]);
        markedProductsForPrint.add(productDTOForList);

        when(repository.findById(id)).thenReturn(productOptional);
        when(mapper.mapEntityToDTOForList(productOptional.get())).thenReturn(productDTOForList);
        when(createLabel.generatePriceLabel(markedProductsForPrint)).thenReturn(priceLabelBytes);

        byte[] bytes = service.printMarkedProducts(productsToPrint);

        verify(repository).findById(id);
        verify(mapper).mapEntityToDTOForList(productOptional.get());
        verify(createLabel).generatePriceLabel(markedProductsForPrint);
        assertEquals(8, bytes.length);
    }

    @Test
    void printMarkedProductsWhenIdNotFound() throws Exception {
        Map<Long, Integer> productsToPrint = Collections.singletonMap(id, 1);

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> service.printMarkedProducts(productsToPrint));
        verify(repository).findById(id);
    }

    @Test
    void printMarkedProductsWhenErrorDuringGeneration() throws Exception {
        Map<Long, Integer> productsToPrint = Collections.singletonMap(id, 1);
        productDTOForList = ProductDTOForList.builder().id(id).build();
        Optional<Product> productOptional = Optional.of(this.product);
        Optional<byte[]> priceLabelBytes = Optional.empty();
        markedProductsForPrint.add(productDTOForList);

        when(repository.findById(id)).thenReturn(productOptional);
        when(mapper.mapEntityToDTOForList(productOptional.get())).thenReturn(productDTOForList);
        when(createLabel.generatePriceLabel(markedProductsForPrint)).thenReturn(priceLabelBytes);

        assertThrows(PriceLabelGenerationException.class, () -> service.printMarkedProducts(productsToPrint));
        verify(repository).findById(id);
        verify(mapper).mapEntityToDTOForList(productOptional.get());
        verify(createLabel).generatePriceLabel(markedProductsForPrint);
    }

    @Test
    void updateRetailPrice() throws Exception {
        BigDecimal retailPrice = BigDecimal.TEN;
        BigDecimal tradeMargin = BigDecimal.ONE;
        BigDecimal oldRetailPrice = BigDecimal.ONE;
        int updatedRows = 1;

        when(repository.updateRetailPrice(retailPrice, tradeMargin, oldRetailPrice, id)).thenReturn(updatedRows);

        int resultedUpdatedRows = service.updateRetailPrice(retailPrice, tradeMargin, oldRetailPrice, id);

        assertEquals(updatedRows, resultedUpdatedRows);
    }
}