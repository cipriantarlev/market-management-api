package ii.cipriantarlev.marketmanagementapi.pricechangingactproduct;

import ii.cipriantarlev.marketmanagementapi.exceptions.DTOFoundWhenSaveException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTOListNotFoundException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTONotFoundException;
import ii.cipriantarlev.marketmanagementapi.pricechangingact.PriceChangingActDTO;
import ii.cipriantarlev.marketmanagementapi.product.Product;
import ii.cipriantarlev.marketmanagementapi.product.ProductDTOForList;
import ii.cipriantarlev.marketmanagementapi.product.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.mockito.Mockito.*;

class PriceChangingActProductServiceTest {

    @InjectMocks
    private PriceChangingActProductServiceImpl service;

    @Mock
    private PriceChangingActProductRepository repository;

    @Mock
    private PriceChangingActProductMapper mapper;

    @Mock
    private ProductService productService;

    private final PriceChangingActProduct priceChangingActProduct = new PriceChangingActProduct();
    private final PriceChangingActProductDTO priceChangingActProductDTO = new PriceChangingActProductDTO();

    private final UUID id = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        openMocks(this);
        priceChangingActProduct.setId(id);
        priceChangingActProductDTO.setId(id);
    }

    @Test
    void findAllByPriceChangingActId() throws Exception {
        List<PriceChangingActProduct> priceChangingActProducts = Collections.singletonList(priceChangingActProduct);

        when(repository.findAllByPriceChangingActId(id)).thenReturn(priceChangingActProducts);
        when(mapper.mapEntityToDTO(priceChangingActProducts.get(0))).thenReturn(priceChangingActProductDTO);

        var resultedPriceChangingActProducts = service.findAllByPriceChangingActId(id);

        verify(repository).findAllByPriceChangingActId(id);
        verify(mapper).mapEntityToDTO(priceChangingActProducts.get(0));
        assertFalse(resultedPriceChangingActProducts.isEmpty());
    }

    @Test
    void findAllByPriceChangingActIdWhenListIsEmpty() {
        when(repository.findAllByPriceChangingActId(id)).thenReturn(Collections.emptyList());

        List<PriceChangingActProductDTO> resultedPriceChangingActProducts = service.findAllByPriceChangingActId(id);

        assertTrue(resultedPriceChangingActProducts.isEmpty());
        verify(repository).findAllByPriceChangingActId(id);
    }

    @Test
    void findById() throws Exception {
        var priceChangingActProductOptional = Optional.of(priceChangingActProduct);

        when(repository.findById(id)).thenReturn(priceChangingActProductOptional);
        when(mapper.mapEntityToDTO(priceChangingActProductOptional.get())).thenReturn(priceChangingActProductDTO);

        var resultedPriceChangingActProductDTO = service.findById(id);

        verify(repository).findById(id);
        verify(mapper).mapEntityToDTO(priceChangingActProductOptional.get());
        assertEquals(priceChangingActProductDTO, resultedPriceChangingActProductDTO);
    }

    @Test
    void findByIdNotFound() {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> service.findById(id));
        verify(repository).findById(id);
    }

    @Test
    void save() throws Exception {
        ProductDTOForList product = ProductDTOForList.builder()
                .id(1L)
                .retailPrice(BigDecimal.TEN)
                .tradeMargin(BigDecimal.ONE)
                .oldRetailPrice(BigDecimal.ONE)
                .build();
        priceChangingActProductDTO.setProduct(product);
        PriceChangingActDTO priceChangingActDTO = PriceChangingActDTO.builder().id(id).build();
        priceChangingActProductDTO.setPriceChangingAct(priceChangingActDTO);

        when(repository.findById(id)).thenReturn(Optional.empty());
        when(productService.updateRetailPrice(product.getRetailPrice(), product.getTradeMargin(),
                product.getOldRetailPrice(), product.getId())).thenReturn(1);
        when(mapper.mapDTOToEntity(priceChangingActProductDTO)).thenReturn(priceChangingActProduct);
        when(repository.save(priceChangingActProduct)).thenReturn(priceChangingActProduct);
        when(mapper.mapEntityToDTO(priceChangingActProduct)).thenReturn(priceChangingActProductDTO);

        var resultedPriceChangingActProductDTO = service.save(priceChangingActProductDTO);

        verify(repository).findById(id);
        verify(productService).updateRetailPrice(product.getRetailPrice(), product.getTradeMargin(),
                product.getOldRetailPrice(), product.getId());
        verify(mapper).mapDTOToEntity(priceChangingActProductDTO);
        verify(repository).save(priceChangingActProduct);
        verify(mapper).mapEntityToDTO(priceChangingActProduct);
        assertEquals(priceChangingActProductDTO, resultedPriceChangingActProductDTO);
    }

    @Test
    void saveWhenAlreadyExist() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.of(priceChangingActProduct));

        assertThrows(DTOFoundWhenSaveException.class, () -> service.save(priceChangingActProductDTO));
        verify(repository).findById(id);
    }

    @Test
    void saveWhenProductNotFound() throws Exception {
        ProductDTOForList product = ProductDTOForList.builder()
                .id(1L)
                .retailPrice(BigDecimal.TEN)
                .tradeMargin(BigDecimal.ONE)
                .oldRetailPrice(BigDecimal.ONE)
                .build();
        priceChangingActProductDTO.setProduct(product);

        when(repository.findById(id)).thenReturn(Optional.empty());
        when(productService.updateRetailPrice(product.getRetailPrice(), product.getTradeMargin(),
                product.getOldRetailPrice(), product.getId())).thenReturn(0);

        assertThrows(DTONotFoundException.class, () -> service.save(priceChangingActProductDTO));
        verify(repository).findById(id);
        verify(productService).updateRetailPrice(product.getRetailPrice(), product.getTradeMargin(),
                product.getOldRetailPrice(), product.getId());
    }

    @Test
    void update() throws Exception {
        ProductDTOForList product = ProductDTOForList.builder()
                .id(1L)
                .retailPrice(BigDecimal.TEN)
                .tradeMargin(BigDecimal.ONE)
                .oldRetailPrice(BigDecimal.ONE)
                .build();
        priceChangingActProductDTO.setProduct(product);
        var priceChangingActProductOptional = Optional.of(priceChangingActProduct);

        when(repository.findById(id)).thenReturn(priceChangingActProductOptional);
        when(mapper.mapEntityToDTO(priceChangingActProductOptional.get())).thenReturn(priceChangingActProductDTO);
        when(productService.updateRetailPrice(product.getRetailPrice(), product.getTradeMargin(),
                product.getOldRetailPrice(), product.getId())).thenReturn(1);
        when(mapper.mapDTOToEntity(priceChangingActProductDTO)).thenReturn(priceChangingActProduct);
        when(repository.save(priceChangingActProduct)).thenReturn(priceChangingActProduct);
        when(mapper.mapEntityToDTO(priceChangingActProduct)).thenReturn(priceChangingActProductDTO);

        var resultedPriceChangingActProductDTO = service.update(priceChangingActProductDTO);

        verify(repository).findById(id);
        verify(productService).updateRetailPrice(product.getRetailPrice(), product.getTradeMargin(),
                product.getOldRetailPrice(), product.getId());
        verify(mapper).mapDTOToEntity(priceChangingActProductDTO);
        verify(repository).save(priceChangingActProduct);
        verify(mapper, times(2)).mapEntityToDTO(priceChangingActProduct);
        assertEquals(priceChangingActProductDTO, resultedPriceChangingActProductDTO);
        assertEquals(priceChangingActProductDTO, resultedPriceChangingActProductDTO);

    }

    @Test
    void updateWhenNotFound() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> service.update(priceChangingActProductDTO));
        verify(repository).findById(id);
    }

    @Test
    void updateWhenProductNotFound() throws Exception {
        ProductDTOForList product = ProductDTOForList.builder()
                .id(1L)
                .retailPrice(BigDecimal.TEN)
                .tradeMargin(BigDecimal.ONE)
                .oldRetailPrice(BigDecimal.ONE)
                .build();
        priceChangingActProductDTO.setProduct(product);
        var priceChangingActProductOptional = Optional.of(priceChangingActProduct);

        when(repository.findById(id)).thenReturn(priceChangingActProductOptional);
        when(mapper.mapEntityToDTO(priceChangingActProductOptional.get())).thenReturn(priceChangingActProductDTO);
        when(productService.updateRetailPrice(product.getRetailPrice(), product.getTradeMargin(),
                product.getOldRetailPrice(), product.getId())).thenReturn(0);

        assertThrows(DTONotFoundException.class, () -> service.update(priceChangingActProductDTO));

        verify(repository).findById(id);
        verify(productService).updateRetailPrice(product.getRetailPrice(), product.getTradeMargin(),
                product.getOldRetailPrice(), product.getId());
        verify(mapper).mapEntityToDTO(priceChangingActProductOptional.get());
    }

    @Test
    void deleteById() throws Exception {
        Product product = new Product();
        product.setOldRetailPrice(BigDecimal.TEN);
        product.setId(1L);
        priceChangingActProduct.setProduct(product);
        priceChangingActProductDTO.setProduct(ProductDTOForList.builder()
                .id(1L)
                .oldRetailPrice(BigDecimal.TEN)
                .tradeMargin(BigDecimal.ZERO)
                .build());
        var priceChangingActProductOptional = Optional.of(priceChangingActProduct);

        when(repository.findById(id)).thenReturn(priceChangingActProductOptional);
        when(mapper.mapEntityToDTO(priceChangingActProductOptional.get())).thenReturn(priceChangingActProductDTO);
        when(productService.updateRetailPrice(
                BigDecimal.TEN, BigDecimal.ZERO, BigDecimal.ZERO, product.getId())).thenReturn(1);
        doNothing().when(repository).deleteById(id);

        service.deleteById(id);

        verify(repository).findById(id);
        verify(mapper).mapEntityToDTO(priceChangingActProductOptional.get());
        verify(productService).updateRetailPrice(
                BigDecimal.TEN, BigDecimal.ZERO, BigDecimal.ZERO, product.getId());
        verify(repository).deleteById(id);
    }

    @Test
    void deleteByIdWhenNotFound() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> service.deleteById(id));
        verify(repository).findById(id);
    }
}