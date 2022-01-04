package ii.cipriantarlev.marketmanagementapi.vat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class VatMapperTest {

    @InjectMocks
    private VatMapper mapper;

    @Mock
    private ModelMapper modelMapper;

    private Vat vat;
    private VatDTO vatDTO;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void mapVatToVatDTO() throws Exception {
        when(modelMapper.map(vat, VatDTO.class)).thenReturn(vatDTO);

        var resultedValue = mapper.mapVatToVatDTO(vat);

        verify(modelMapper).map(vat, VatDTO.class);
        assertEquals(vatDTO, resultedValue);
    }

    @Test
    void mapVatDTOToVat() throws Exception {
        when(modelMapper.map(vatDTO, Vat.class)).thenReturn(vat);

        var resultedValue = mapper.mapVatDTOToVat(vatDTO);

        verify(modelMapper).map(vatDTO, Vat.class);
        assertEquals(vat, resultedValue);
    }
}