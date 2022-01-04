package ii.cipriantarlev.marketmanagementapi.invoiceproduct;

import ii.cipriantarlev.marketmanagementapi.exceptions.DTOFoundWhenSaveException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTOListNotFoundException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTONotFoundException;
import ii.cipriantarlev.marketmanagementapi.invoice.InvoiceDTO;
import ii.cipriantarlev.marketmanagementapi.invoice.InvoiceService;
import ii.cipriantarlev.marketmanagementapi.product.ProductDTO;
import ii.cipriantarlev.marketmanagementapi.product.ProductService;
import ii.cipriantarlev.marketmanagementapi.vendor.VendorDTOOnlyName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class InvoiceProductServiceTest {

    @InjectMocks
    private InvoiceProductServiceImpl service;

    @Mock
    private InvoiceProductRepository repository;

    @Mock
    private InvoiceProductMapper mapper;

    @Mock
    private ProductService productService;

    @Mock
    private InvoiceService invoiceService;

    private InvoiceProductDTO invoiceProductDTO;
    private InvoiceProduct invoiceProduct;

    private final long id = 1L;

    @BeforeEach
    void setUp() {
        openMocks(this);
        invoiceProductDTO = InvoiceProductDTO.builder().id(id).build();
        invoiceProduct = new InvoiceProduct();
        invoiceProduct.setId(id);
    }

    @Test
    void findAllByInvoiceId() throws Exception {
        List<InvoiceProduct> invoiceProductList = Collections.singletonList(invoiceProduct);

        when(repository.findAllByInvoiceId(id)).thenReturn(invoiceProductList);
        when(mapper.mapEntityToDTO(invoiceProductList.get(0))).thenReturn(invoiceProductDTO);

        var resultedInvoiceProductDTOList = service.findAllByInvoiceId(id);

        verify(repository).findAllByInvoiceId(id);
        verify(mapper).mapEntityToDTO(invoiceProductList.get(0));
        assertFalse(resultedInvoiceProductDTOList.isEmpty());
    }

    @Test
    void findAllByInvoiceIdWhenEmptyList() throws Exception {
        when(repository.findAllByInvoiceId(id)).thenReturn(Collections.emptyList());

        assertThrows(DTOListNotFoundException.class, () -> service.findAllByInvoiceId(id));
        verify(repository).findAllByInvoiceId(id);
    }

    @Test
    void findById() throws Exception {
        var invoiceProductOptional = Optional.of(invoiceProduct);

        when(repository.findById(id)).thenReturn(invoiceProductOptional);
        when(mapper.mapEntityToDTO(invoiceProductOptional.get())).thenReturn(invoiceProductDTO);

        var invoiceProductDTO = service.findById(id);

        verify(repository).findById(id);
        verify(mapper).mapEntityToDTO(invoiceProductOptional.get());
        assertNotNull(invoiceProductDTO);
        assertEquals(id, invoiceProductDTO.getId());
    }

    @Test
    void findByIdWhenNotFound() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> service.findById(id));
        verify(repository).findById(id);
    }

    @Test
    void save() throws Exception {
        InvoiceDTO invoiceDTO = InvoiceDTO.builder()
                .id(id)
                .vendor(VendorDTOOnlyName.builder().id(id).build())
                .build();
        ProductDTO productDTO = ProductDTO.builder().id(id).vendors(new ArrayList<>()).build();
        invoiceProductDTO.setInvoice(invoiceDTO);
        invoiceProductDTO.setProduct(productDTO);

        when(repository.findById(invoiceProductDTO.getId())).thenReturn(Optional.empty());
        when(mapper.mapDTOToEntity(invoiceProductDTO)).thenReturn(invoiceProduct);
        when(repository.save(invoiceProduct)).thenReturn(invoiceProduct);
        when(invoiceService.findById(id)).thenReturn(invoiceDTO);
        when(productService.update(productDTO)).thenReturn(productDTO);
        when(mapper.mapEntityToDTO(invoiceProduct)).thenReturn(invoiceProductDTO);

        var resultedInvoiceProductDTO = service.save(invoiceProductDTO);

        verify(repository).findById(invoiceProductDTO.getId());
        verify(mapper).mapDTOToEntity(invoiceProductDTO);
        verify(repository).save(invoiceProduct);
        verify(invoiceService).findById(id);
        verify(productService).update(productDTO);
        verify(mapper).mapEntityToDTO(invoiceProduct);
        assertNotNull(resultedInvoiceProductDTO);
        assertEquals(id, resultedInvoiceProductDTO.getId());
    }

    @Test
    void saveWhenAlreadyExist() throws Exception {
        when(repository.findById(invoiceProductDTO.getId())).thenReturn(Optional.of(invoiceProduct));

        assertThrows(DTOFoundWhenSaveException.class, () -> service.save(invoiceProductDTO));
        verify(repository).findById(invoiceProductDTO.getId());
    }

    @Test
    void update() throws Exception {
        var invoiceProductOptional = Optional.of(invoiceProduct);

        when(repository.findById(id)).thenReturn(invoiceProductOptional);
        when(mapper.mapEntityToDTO(invoiceProductOptional.get())).thenReturn(invoiceProductDTO);
        when(mapper.mapDTOToEntity(invoiceProductDTO)).thenReturn(invoiceProduct);
        when(repository.save(invoiceProduct)).thenReturn(invoiceProduct);

        var resultedInvoiceProductDTO = service.update(invoiceProductDTO);

        verify(repository).findById(id);
        verify(mapper, times(2)).mapEntityToDTO(invoiceProductOptional.get());
        verify(mapper).mapDTOToEntity(invoiceProductDTO);
        verify(repository).save(invoiceProduct);
        assertNotNull(resultedInvoiceProductDTO);
        assertEquals(id, resultedInvoiceProductDTO.getId());
    }

    @Test
    void updateWhenNotFound() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> service.update(invoiceProductDTO));
        verify(repository).findById(id);
    }

    @Test
    void deleteById() throws Exception {
        var invoiceProductOptional = Optional.of(invoiceProduct);
        ProductDTO productDTO = ProductDTO.builder().id(id).stock(BigDecimal.valueOf(10L)).build();
        invoiceProductDTO.setQuantity(BigDecimal.valueOf(5L));
        invoiceProductDTO.setProduct(productDTO);

        when(repository.findById(id)).thenReturn(invoiceProductOptional);
        when(mapper.mapEntityToDTO(invoiceProductOptional.get())).thenReturn(invoiceProductDTO);
        when(productService.update(productDTO)).thenReturn(productDTO);
        doNothing().when(repository).deleteById(id);

        service.deleteById(id);

        verify(repository).findById(id);
        verify(mapper).mapEntityToDTO(invoiceProductOptional.get());
        verify(productService).update(productDTO);
        verify(repository).deleteById(id);
    }

    @Test
    void deleteByIdWhenNotFound() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> service.deleteById(id));
        verify(repository).findById(id);
    }
}