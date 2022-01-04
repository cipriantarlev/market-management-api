package ii.cipriantarlev.marketmanagementapi.plu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class PluControllerTest {

    @InjectMocks
    private PluController controller;

    @Mock
    private PluService service;

    private final long id = 1L;
    private final int ok = 200;
    private PluDTO pluDTO;

    @BeforeEach
    void setUp() {
        openMocks(this);
        pluDTO = PluDTO.builder().id(id).build();
    }

    @Test
    void getPluList() throws Exception {
        List<PluDTO> pluList = Collections.singletonList(pluDTO);

        when(service.findAll()).thenReturn(pluList);

        var response = controller.getPluList();

        verify(service).findAll();
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(pluList, response.getBody());
    }

    @Test
    void getPlu() throws Exception {
        when(service.findById(id)).thenReturn(pluDTO);

        var response = controller.getPlu(id);

        verify(service).findById(id);
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(pluDTO, response.getBody());
    }

    @Test
    void generateNewPlu() throws Exception {
        when(service.generateNewPlu()).thenReturn(pluDTO);

        var response = controller.generateNewPlu();

        verify(service).generateNewPlu();
        assertEquals(ok, response.getStatusCodeValue());
        assertEquals(pluDTO, response.getBody());
    }

    @Test
    void deletePlu() throws Exception {
        doNothing().when(service).deleteById(id);

        var response = controller.deletePlu(id);

        verify(service).deleteById(id);
        assertEquals(ok, response.getStatusCodeValue());
    }
}