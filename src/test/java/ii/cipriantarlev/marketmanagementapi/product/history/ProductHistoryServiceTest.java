package ii.cipriantarlev.marketmanagementapi.product.history;

import ii.cipriantarlev.marketmanagementapi.barcode.BarcodeDTO;
import ii.cipriantarlev.marketmanagementapi.category.CategoryDTO;
import ii.cipriantarlev.marketmanagementapi.core.AuthenticationInformation;
import ii.cipriantarlev.marketmanagementapi.history.HistoryAction;
import ii.cipriantarlev.marketmanagementapi.measuringunit.MeasuringUnitDTO;
import ii.cipriantarlev.marketmanagementapi.product.ProductDTO;
import ii.cipriantarlev.marketmanagementapi.productscode.ProductCodeDTO;
import ii.cipriantarlev.marketmanagementapi.subcategory.SubcategoryDTONoCategory;
import ii.cipriantarlev.marketmanagementapi.utils.MarketManagementFactory;
import ii.cipriantarlev.marketmanagementapi.vat.VatDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class ProductHistoryServiceTest {

    @InjectMocks
    private ProductHistoryServiceImpl service;

    @Mock
    private ProductHistoryRepository repository;

    @Mock
    private AuthenticationInformation authenticationInformation;

    @Mock
    private MarketManagementFactory factory;

    @Mock
    private Authentication authentication;

    private final long id = 1L;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void createProductHistoryRecord() throws Exception {
        ProductDTO productDTO = ProductDTO.builder()
                .id(id)
                .nameRom("Test")
                .nameRus("Fdrффв")
                .category(CategoryDTO.builder().id(id).name("dsds").build())
                .subcategory(SubcategoryDTONoCategory.builder().id(id).name("dds").build())
                .measuringUnit(MeasuringUnitDTO.builder().id(id).name("kg").build())
                .vat(VatDTO.builder().id(id).value(2).name("2%").build())
                .barcodes(Collections.singletonList(BarcodeDTO.builder().value("222").build()))
                .productCode(ProductCodeDTO.builder().id(id).value("MD000").build())
                .build();
        ProductHistory productHistory = new ProductHistory();

        when(factory.getProductHistory()).thenReturn(productHistory);
        when(authenticationInformation.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("ciprian");
        when(repository.save(productHistory)).thenReturn(productHistory);

        service.createProductHistoryRecord(productDTO, HistoryAction.DELETE);

        verify(factory).getProductHistory();
        verify(authenticationInformation).getAuthentication();
        verify(authentication).getName();
        verify(repository).save(productHistory);
    }

    @Test
    void findProductPriceHistory() throws Exception {
        var productHistory = new ProductHistory();
        Set<ProductHistory> productHistorySet = Collections.singleton(productHistory);
        List<ProductHistory> productHistoryList = Collections.singletonList(productHistory);
        when(repository.findDistinctByProductIdAndDiscountPriceNotNullAndRetailPriceNotNullOrderByCreatedDesc(id))
                .thenReturn(productHistoryList);

        var productPriceHistory = service.findProductPriceHistory(id);

        assertEquals(productHistorySet, productPriceHistory);
    }
}