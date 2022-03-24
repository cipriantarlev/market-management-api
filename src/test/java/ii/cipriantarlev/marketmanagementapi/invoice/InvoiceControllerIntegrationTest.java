package ii.cipriantarlev.marketmanagementapi.invoice;

import ii.cipriantarlev.marketmanagementapi.config.IntegrationTestConfiguration;
import ii.cipriantarlev.marketmanagementapi.documenttype.DocumentTypeDTO;
import ii.cipriantarlev.marketmanagementapi.myorganization.MyOrganizationDTOOnlyName;
import ii.cipriantarlev.marketmanagementapi.vendor.VendorDTOOnlyName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientException;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static ii.cipriantarlev.marketmanagementapi.utils.Constants.*;

class InvoiceControllerIntegrationTest extends IntegrationTestConfiguration {

    private InvoiceDTO invoiceDTO;

    @BeforeEach
    void setUp() {
        invoiceDTO = InvoiceDTO.builder()
                .documentType(DocumentTypeDTO.builder().id(GOOD_ID).name("test").build())
                .myOrganization(MyOrganizationDTOOnlyName.builder().id(GOOD_ID).name("test").build())
                .vendor(VendorDTOOnlyName.builder().id(GOOD_ID).name("test").build())
                .dateCreated(LocalDate.now())
                .invoiceNumber("TEST9947")
                .invoiceDate(LocalDate.now())
                .build();
    }

    @Test
    void getInvoices() throws Exception {
        HttpEntity<List<InvoiceDTO>> entity = new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(INVOICES_ROOT_PATH),
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<List<InvoiceDTO>>() {
                        });

        assertEquals(3, Objects.requireNonNull(response.getBody()).size());
        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void getIncomeInvoices() throws Exception {
        HttpEntity<List<InvoiceDTO>> entity = new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(INVOICES_ROOT_PATH.concat(INCOME_INVOICES)),
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<List<InvoiceDTO>>() {
                        });

        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void getOutcomeInvoices() throws Exception {
        HttpEntity<List<InvoiceDTO>> entity = new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(INVOICES_ROOT_PATH.concat(OUTCOME_INVOICES)),
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<List<InvoiceDTO>>() {
                        });

        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void getInvoiceWhenOk() throws Exception {
        var response = getRestTemplateWithAuth()
                .getForEntity(createUri(INVOICES_ROOT_PATH.concat(GOOD_ID_PATH)), InvoiceDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals("F3243255", Objects.requireNonNull(response.getBody()).getInvoiceNumber());
        assertEquals(GOOD_ID, response.getBody().getId());
    }

    @Test
    void getInvoiceWhenNotFound() throws Exception {
        assertThrows(RestClientException.class, throwExceptionWhenGet(INVOICES_ROOT_PATH.concat(BAD_ID_PATH)));
    }

    @Test
    void getMyOrganizationsWhenOk() throws Exception {
        HttpEntity<List<MyOrganizationDTOOnlyName>> entity = new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(INVOICES_ROOT_PATH.concat(MY_ORGANIZATIONS_ROOT_PATH)),
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<List<MyOrganizationDTOOnlyName>>() {
                        });

        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void getVendorsWhenOk() throws Exception {
        HttpEntity<List<VendorDTOOnlyName>> entity = new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(INVOICES_ROOT_PATH.concat(VENDORS_ROOT_PATH)),
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<List<VendorDTOOnlyName>>() {
                        });

        assertEquals(3, Objects.requireNonNull(response.getBody()).size());
        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void createInvoiceWhenOk() throws Exception {
        var response = getRestTemplateWithAuth()
                .postForEntity(createUri(INVOICES_ROOT_PATH),
                        invoiceDTO,
                        InvoiceDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(4L, Objects.requireNonNull(response.getBody()).getId());
        assertEquals("TEST9947", response.getBody().getInvoiceNumber());
    }

    @Test
    void createInvoiceWhenAlreadyExist() throws Exception {
        invoiceDTO.setId(GOOD_ID);

        HttpEntity<InvoiceDTO> entity = new HttpEntity<>(invoiceDTO, new HttpHeaders());

        assertThrows(RestClientException.class, throwException(entity, INVOICES_ROOT_PATH, HttpMethod.POST));
    }

    @Test
    void updateInvoiceWhenOk() throws Exception {
        invoiceDTO.setId(GOOD_ID);

        HttpEntity<InvoiceDTO> entity = new HttpEntity<>(invoiceDTO, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(INVOICES_ROOT_PATH),
                        HttpMethod.PUT,
                        entity,
                        InvoiceDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(GOOD_ID, Objects.requireNonNull(response.getBody()).getId());
        assertEquals("TEST9947", response.getBody().getInvoiceNumber());
    }

    @Test
    void updateInvoiceWhenNotFound() throws Exception {
        invoiceDTO.setId(BAD_ID);

        HttpEntity<InvoiceDTO> entity = new HttpEntity<>(invoiceDTO, new HttpHeaders());

        assertThrows(RestClientException.class, throwException(entity, INVOICES_ROOT_PATH, HttpMethod.PUT));
    }

    @Test
    void updateInvoiceIsApprovedMarkerWhenOk() throws Exception {
        Map<Boolean, List<Long>> invoicesToUpdate = Collections.singletonMap(true, Collections.singletonList(GOOD_ID));
        HttpEntity<Map<Boolean, List<Long>>> entity = new HttpEntity<>(invoicesToUpdate, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(INVOICES_ROOT_PATH.concat(IS_APPROVED_INVOICE)),
                        HttpMethod.PUT,
                        entity,
                        Integer.class);

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(1, response.getBody());
    }

    @Test
    void updateInvoiceIsApprovedMarkerWhenNotFound() throws Exception {
        Map<Boolean, List<Long>> invoicesToUpdate = Collections.singletonMap(true, Collections.singletonList(GOOD_ID));
        HttpEntity<Map<Boolean, List<Long>>> entity = new HttpEntity<>(invoicesToUpdate, new HttpHeaders());

        assertThrows(RestClientException.class,
                throwException(
                        entity,
                        INVOICES_ROOT_PATH.concat(IS_APPROVED_INVOICE),
                        HttpMethod.PUT));
    }

    @Test
    void deleteInvoiceWhenOk() throws Exception {
        HttpEntity<InvoiceDTO> entity = new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(INVOICES_ROOT_PATH.concat(GOOD_ID_PATH)),
                        HttpMethod.DELETE,
                        entity,
                        InvoiceDTO.class);

        assertEquals(OK, response.getStatusCodeValue());
    }

    @Test
    void deleteInvoiceWhenNotFound() throws Exception {
        HttpEntity<InvoiceDTO> entity = new HttpEntity<>(null, new HttpHeaders());

        assertThrows(RestClientException.class,
                throwException(entity, INVOICES_ROOT_PATH.concat(BAD_ID_PATH),HttpMethod.DELETE));
    }
}