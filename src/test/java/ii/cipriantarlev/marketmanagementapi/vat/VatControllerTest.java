package ii.cipriantarlev.marketmanagementapi.vat;

import ii.cipriantarlev.marketmanagementapi.utils.RestControllerUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpHeaders;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ii.cipriantarlev.marketmanagementapi.utils.Constants.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class VatControllerTest {

    @InjectMocks
    private VatController controller;

    @Mock
    private VatService service;

    @Mock
    private RestControllerUtil restControllerUtil;

    private final long id = 1L;
    private final int ok = 200;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void getVatList() throws Exception {
        List<VatDTO> vatDTOList = Collections.singletonList(new VatDTO());

        when(service.findAll()).thenReturn(vatDTOList);

        var response = controller.getVatList();

        verify(service).findAll();
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(vatDTOList, response.getBody());
    }

    @Test
    void getVatById() throws Exception {
        VatDTO vatDTO = VatDTO.builder().id(id).build();

        when(service.findById(id)).thenReturn(vatDTO);

        var response = controller.getVatById(id);

        verify(service).findById(id);
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(vatDTO, response.getBody());
    }

    @Test
    void createVat() throws Exception {
        VatDTO vatDTO = VatDTO.builder().value(1).build();
        VatDTO savedVatDTO = VatDTO.builder().id(id).value(1).build();

        when(service.save(vatDTO)).thenReturn(savedVatDTO);
        when(restControllerUtil
                .setHttpsHeaderLocation(VAT_ROOT_PATH.concat(ID_PATH), savedVatDTO.getId()))
                .thenReturn(new HttpHeaders());

        var response = controller.createVat(vatDTO);

        verify(service).save(vatDTO);
        verify(restControllerUtil).setHttpsHeaderLocation(VAT_ROOT_PATH.concat(ID_PATH), savedVatDTO.getId());
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(savedVatDTO, response.getBody());
    }

    @Test
    void updateDTO() throws Exception {
        VatDTO vatDTO = VatDTO.builder().id(id).value(1).build();

        when(service.update(vatDTO)).thenReturn(vatDTO);

        var response = controller.updateDTO(vatDTO);

        verify(service).update(vatDTO);
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(vatDTO, response.getBody());
    }

    @Test
    void deleteVat() throws Exception {
        doNothing().when(service).deleteById(id);

        var response = controller.deleteVat(id);

        verify(service).deleteById(id);
        assertEquals(ok, response.getStatusCodeValue());
    }
}