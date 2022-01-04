package ii.cipriantarlev.marketmanagementapi.invoiceproduct;

import ii.cipriantarlev.marketmanagementapi.barcode.BarcodeDTO;
import ii.cipriantarlev.marketmanagementapi.category.CategoryDTO;
import ii.cipriantarlev.marketmanagementapi.config.IntegrationTestConfiguration;
import ii.cipriantarlev.marketmanagementapi.documenttype.DocumentTypeDTO;
import ii.cipriantarlev.marketmanagementapi.invoice.InvoiceDTO;
import ii.cipriantarlev.marketmanagementapi.measuringunit.MeasuringUnitDTO;
import ii.cipriantarlev.marketmanagementapi.myorganization.MyOrganizationDTOOnlyName;
import ii.cipriantarlev.marketmanagementapi.product.ProductDTO;
import ii.cipriantarlev.marketmanagementapi.productscode.ProductCodeDTO;
import ii.cipriantarlev.marketmanagementapi.subcategory.SubcategoryDTONoCategory;
import ii.cipriantarlev.marketmanagementapi.vat.VatDTO;
import ii.cipriantarlev.marketmanagementapi.vendor.VendorDTOOnlyName;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static ii.cipriantarlev.marketmanagementapi.utils.Constants.*;

class InvoiceProductControllerIntegrationTest extends IntegrationTestConfiguration {

    @Test
    void getInvoiceProductsByInvoiceId() throws Exception {
        HttpEntity<List<InvoiceProductDTO>> entity= new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(INVOICE_PRODUCT_ROOT_PATH.concat(GOOD_ID_PATH)),
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<List<InvoiceProductDTO>>() {
                        });

        assertEquals(3, Objects.requireNonNull(response.getBody()).size());
        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void getInvoiceProductByIdWhenOk() throws Exception {
        var response = getRestTemplateWithAuth()
                .getForEntity(createUri(INVOICE_PRODUCT_ROOT_PATH.concat(PRODUCT_PATH).concat(GOOD_ID_PATH)),
                        InvoiceProductDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(new BigDecimal("3.0000"), Objects.requireNonNull(response.getBody()).getQuantity());
        assertEquals(GOOD_ID, response.getBody().getId());
    }

    @Test
    void getInvoiceProductByIdWhenNotFound() throws Exception {
        assertThrows(RestClientException.class,
                throwExceptionWhenGet(INVOICE_PRODUCT_ROOT_PATH.concat(PRODUCT_PATH).concat(BAD_ID_PATH)));
    }

    @Test
    void createInvoiceProductWhenOk() throws Exception {
        InvoiceProductDTO invoiceProductDTO = getInvoiceProductDTO();

        var response = getRestTemplateWithAuth()
                .postForEntity(createUri(INVOICE_PRODUCT_ROOT_PATH.concat(PRODUCT_PATH)),
                        invoiceProductDTO,
                        InvoiceProductDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(4L, Objects.requireNonNull(response.getBody()).getId());
        assertEquals(BigDecimal.valueOf(3), response.getBody().getQuantity());
    }

    @Test
    void createInvoiceProductWhenAlreadyExist() throws Exception {
        InvoiceProductDTO invoiceProductDTO = getInvoiceProductDTO();
        invoiceProductDTO.setId(GOOD_ID);

        HttpEntity<InvoiceProductDTO> entity = new HttpEntity<>(invoiceProductDTO, new HttpHeaders());

        assertThrows(RestClientException.class, throwException(entity, INVOICE_PRODUCT_ROOT_PATH.concat(PRODUCT_PATH), HttpMethod.POST));
    }

    @Test
    void updateInvoiceProductWhenOk() throws Exception {
        InvoiceProductDTO invoiceProductDTO = getInvoiceProductDTO();
        invoiceProductDTO.setId(GOOD_ID);

        HttpEntity<InvoiceProductDTO> entity = new HttpEntity<>(invoiceProductDTO, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(INVOICE_PRODUCT_ROOT_PATH.concat(PRODUCT_PATH)),
                        HttpMethod.PUT,
                        entity,
                        InvoiceProductDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(GOOD_ID, Objects.requireNonNull(response.getBody()).getId());
        assertEquals(BigDecimal.valueOf(3), response.getBody().getQuantity());
    }

    @Test
    void updateInvoiceProductWhenNotFound() throws Exception {
        InvoiceProductDTO invoiceProductDTO = getInvoiceProductDTO();
        invoiceProductDTO.setId(BAD_ID);

        HttpEntity<InvoiceProductDTO> entity = new HttpEntity<>(invoiceProductDTO, new HttpHeaders());

        assertThrows(RestClientException.class,
                throwException(entity, INVOICE_PRODUCT_ROOT_PATH.concat(PRODUCT_PATH), HttpMethod.PUT));
    }

    @Test
    void deleteInvoiceProductByIdWhenOk() throws Exception {
        HttpEntity<InvoiceProductDTO> entity = new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(INVOICE_PRODUCT_ROOT_PATH.concat(PRODUCT_PATH).concat(GOOD_ID_PATH)),
                        HttpMethod.DELETE,
                        entity,
                        InvoiceProductDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void deleteInvoiceProductByIdWhenNotFound() throws Exception {
        HttpEntity<InvoiceProductDTO> entity = new HttpEntity<>(null, new HttpHeaders());

        assertThrows(RestClientException.class, throwException(
                entity,
                INVOICE_PRODUCT_ROOT_PATH.concat(PRODUCT_PATH).concat(BAD_ID_PATH),
                HttpMethod.DELETE));
    }

    private InvoiceProductDTO getInvoiceProductDTO() {
        return InvoiceProductDTO.builder()
                .invoice(getInvoiceDTO())
                .product(getProductDTO())
                .quantity(BigDecimal.valueOf(3))
                .vatSum(BigDecimal.valueOf(1.3))
                .totalDiscountPrice(BigDecimal.valueOf(34))
                .totalRetailPrice(BigDecimal.valueOf(55))
                .build();
    }

    private InvoiceDTO getInvoiceDTO() {
        return InvoiceDTO.builder()
                .id(2L)
                .documentType(DocumentTypeDTO.builder().id(1L).build())
                .myOrganization(MyOrganizationDTOOnlyName.builder().id(1L).build())
                .vendor(VendorDTOOnlyName.builder().id(1L).build())
                .dateCreated(LocalDate.now())
                .invoiceNumber("Test")
                .invoiceDate(LocalDate.now())
                .build();
    }

    private ProductDTO getProductDTO() {
        return ProductDTO.builder()
                .id(1L)
                .nameRom("Test")
                .nameRus("Test")
                .vat(VatDTO.builder().id(1L).name("test").value(4).build())
                .barcodes(Collections.singletonList(BarcodeDTO.builder().id(1L).value("999904").build()))
                .measuringUnit(MeasuringUnitDTO.builder().id(1L).name("test").build())
                .category(CategoryDTO.builder().id(1L).name("test").build())
                .subcategory(SubcategoryDTONoCategory.builder().id(1L).name("Test").build())
                .productCode(ProductCodeDTO.builder().id(1L).value("Test").build())
                .vendors(new ArrayList<>())
                .build();
    }
}