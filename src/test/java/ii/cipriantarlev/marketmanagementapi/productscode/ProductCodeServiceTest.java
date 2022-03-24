package ii.cipriantarlev.marketmanagementapi.productscode;

import ii.cipriantarlev.marketmanagementapi.exceptions.DTONotFoundException;
import ii.cipriantarlev.marketmanagementapi.history.EntitiesHistoryService;
import ii.cipriantarlev.marketmanagementapi.history.HistoryAction;
import ii.cipriantarlev.marketmanagementapi.utils.MarketManagementFactory;
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

class ProductCodeServiceTest {

    @InjectMocks
    private ProductCodeServiceImpl service;

    @Mock
    private ProductCodeRepository repository;

    @Mock
    private ProductCodeMapper mapper;

    @Mock
    private EntitiesHistoryService entitiesHistoryService;

    @Mock
    private MarketManagementFactory factory;

    private ProductCode productCode;
    private ProductCodeDTO productCodeDTO;
    private final long id = 1L;
    private final String value = "MD00000000";

    @BeforeEach
    void setUp() {
        openMocks(this);
        productCode = new ProductCode();
        productCode.setId(id);
        productCodeDTO = ProductCodeDTO.builder().id(id).build();
        productCode.setValue(value);
        productCodeDTO.setValue(value);
    }

    @Test
    void findAll() throws Exception {
        List<ProductCode> productCodeList = Collections.singletonList(productCode);

        when(repository.findAll()).thenReturn(productCodeList);
        when(mapper.mapEntityToDTO(productCode)).thenReturn(productCodeDTO);

        var resultedProductCodeDTOList = service.findAll();

        verify(repository).findAll();
        verify(mapper).mapEntityToDTO(productCode);
        assertFalse(resultedProductCodeDTOList.isEmpty());
        assertEquals(1, resultedProductCodeDTOList.size());
    }

    @Test
    void findById() throws Exception {
        var productCodeOptional = Optional.of(productCode);

        when(repository.findById(id)).thenReturn(productCodeOptional);
        when(mapper.mapEntityToDTO(productCodeOptional.get())).thenReturn(productCodeDTO);

        var resultedProductCodeDTO = service.findById(id);

        verify(repository).findById(id);
        verify(mapper).mapEntityToDTO(productCodeOptional.get());
        assertNotNull(resultedProductCodeDTO);
        assertEquals(id, resultedProductCodeDTO.getId());
    }

    @Test
    void findByIdWhenNotFound() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> service.findById(id));
        verify(repository).findById(id);
    }

    @Test
    void generateNewProductCodeWhenGenerateFirstProductCode() throws Exception {
        when(repository.findFirst1ByOrderByValueDesc()).thenReturn(null);
        when(factory.getProductCode(value)).thenReturn(productCode);
        when(repository.save(productCode)).thenReturn(productCode);
        when(mapper.mapEntityToDTO(productCode)).thenReturn(productCodeDTO);

        var resultedProductCode = service.generateNewProductCode();

        verify(repository).findFirst1ByOrderByValueDesc();
        verify(factory).getProductCode(value);
        verify(repository).save(productCode);
        verify(mapper).mapEntityToDTO(productCode);
        assertEquals(productCodeDTO, resultedProductCode);
    }

    @Test
    void generateNewProductCodeWhenGenerateNewProductCode() throws Exception {
        final var newValue = "MD00000001";
        productCodeDTO.setValue(newValue);
        ProductCode savedProductCode = new ProductCode(newValue);

        when(repository.findFirst1ByOrderByValueDesc()).thenReturn(productCode);
        when(factory.getProductCode(newValue)).thenReturn(savedProductCode);
        when(repository.save(savedProductCode)).thenReturn(savedProductCode);
        when(mapper.mapEntityToDTO(savedProductCode)).thenReturn(productCodeDTO);
        doNothing().when(entitiesHistoryService)
                .createEntityHistoryRecord(savedProductCode, null, HistoryAction.CREATE);

        var resultedProductCode = service.generateNewProductCode();

        verify(repository).findFirst1ByOrderByValueDesc();
        verify(factory).getProductCode(newValue);
        verify(repository).save(savedProductCode);
        verify(mapper).mapEntityToDTO(savedProductCode);
        verify(entitiesHistoryService).createEntityHistoryRecord(savedProductCode, null, HistoryAction.CREATE);
        assertEquals(productCodeDTO, resultedProductCode);
    }

    @Test
    void deleteById() throws Exception {
        var productCodeOptional = Optional.of(productCode);

        when(repository.findById(id)).thenReturn(productCodeOptional);
        when(mapper.mapEntityToDTO(productCodeOptional.get())).thenReturn(productCodeDTO);
        when(mapper.mapDTOToEntity(productCodeDTO)).thenReturn(productCode);
        doNothing().when(entitiesHistoryService)
                .createEntityHistoryRecord(productCode, null, HistoryAction.DELETE);
        doNothing().when(repository).deleteById(id);

        service.deleteById(id);

        verify(repository).findById(id);
        verify(mapper).mapEntityToDTO(productCodeOptional.get());
        verify(mapper).mapDTOToEntity(productCodeDTO);
        verify(entitiesHistoryService).createEntityHistoryRecord(productCode, null, HistoryAction.DELETE);
        verify(repository).deleteById(id);
    }

    @Test
    void deleteByIdWhenNotFound() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> service.deleteById(id));
        verify(repository).findById(id);
    }
}