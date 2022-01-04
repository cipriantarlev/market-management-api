package ii.cipriantarlev.marketmanagementapi.plu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class PluMapperTest {

    @InjectMocks
    private PluMapper mapper;

    @Mock
    private ModelMapper modelMapper;

    private Plu plu;
    private PluDTO pluDTO;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void mapEntityToDTO() throws Exception {
        when(modelMapper.map(plu, PluDTO.class)).thenReturn(pluDTO);

        var returnedValue = mapper.mapEntityToDTO(plu);

        verify(modelMapper).map(plu, PluDTO.class);
        assertEquals(pluDTO, returnedValue);
    }

    @Test
    void mapDTOToEntity() throws Exception {
        when(modelMapper.map(pluDTO, Plu.class)).thenReturn(plu);

        var returnedValue = mapper.mapDTOToEntity(pluDTO);

        verify(modelMapper).map(pluDTO, Plu.class);
        assertEquals(plu, returnedValue);
    }
}