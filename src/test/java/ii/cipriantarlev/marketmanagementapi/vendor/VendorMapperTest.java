package ii.cipriantarlev.marketmanagementapi.vendor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class VendorMapperTest {

    @InjectMocks
    private VendorMapper mapper;

    @Mock
    private ModelMapper modelMapper;

    private Vendor vendor;
    private VendorDTO vendorDTO;
    private VendorDTONoRegions vendorDTONoRegions;
    private VendorDTOOnlyName vendorDTOOnlyName;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void mapVendorToVendorDTO() throws Exception {
        when(modelMapper.map(vendor, VendorDTO.class)).thenReturn(vendorDTO);

        var returnedValue = mapper.mapVendorToVendorDTO(vendor);

        verify(modelMapper).map(vendor, VendorDTO.class);
        assertEquals(vendorDTO, returnedValue);
    }

    @Test
    void mapVendorDTOToVendor() throws Exception {
        when(modelMapper.map(vendorDTO, Vendor.class)).thenReturn(vendor);

        var returnedValue = mapper.mapVendorDTOToVendor(vendorDTO);

        verify(modelMapper).map(vendorDTO, Vendor.class);
        assertEquals(vendor, returnedValue);
    }

    @Test
    void mapVendorToVendorDTONoRegions() throws Exception {
        when(modelMapper.map(vendor, VendorDTONoRegions.class)).thenReturn(vendorDTONoRegions);

        var returnedValue = mapper.mapVendorToVendorDTONoRegions(vendor);

        verify(modelMapper).map(vendor, VendorDTONoRegions.class);
        assertEquals(vendorDTONoRegions, returnedValue);
    }

    @Test
    void mapEntityToVendorDTOOnlyName() throws Exception {
        when(modelMapper.map(vendor, VendorDTOOnlyName.class)).thenReturn(vendorDTOOnlyName);

        var returnedValue = mapper.mapVendorToVendorDTOOnlyName(vendor);

        verify(modelMapper).map(vendor, VendorDTOOnlyName.class);
        assertEquals(vendorDTOOnlyName, returnedValue);
    }
}