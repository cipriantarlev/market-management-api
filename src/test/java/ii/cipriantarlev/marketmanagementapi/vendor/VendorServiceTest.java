package ii.cipriantarlev.marketmanagementapi.vendor;

import ii.cipriantarlev.marketmanagementapi.exceptions.DTOFoundWhenSaveException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTONotFoundException;
import ii.cipriantarlev.marketmanagementapi.history.EntitiesHistoryService;
import ii.cipriantarlev.marketmanagementapi.history.HistoryAction;
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

class VendorServiceTest {

    @InjectMocks
    private VendorServiceImpl service;

    @Mock
    private VendorRepository repository;

    @Mock
    private VendorMapper mapper;

    @Mock
    private EntitiesHistoryService entitiesHistoryService;

    private Vendor vendor;
    private VendorDTO vendorDTO;
    private final long id = 1L;

    @BeforeEach
    void setUp() {
        openMocks(this);
        vendor = new Vendor();
        vendor.setId(id);
        vendorDTO = VendorDTO.builder().id(id).build();
    }

    @Test
    void findAll() throws Exception {
        List<Vendor> vendorList = Collections.singletonList(vendor);

        when(repository.findAllByOrderByIdAsc()).thenReturn(vendorList);
        when(mapper.mapVendorToVendorDTONoRegions(vendorList.get(0))).thenReturn(new VendorDTONoRegions());

        var resultedVendorDTOList = service.findAll();

        verify(repository).findAllByOrderByIdAsc();
        verify(mapper).mapVendorToVendorDTONoRegions(vendorList.get(0));
        assertFalse(resultedVendorDTOList.isEmpty());
        assertEquals(1, resultedVendorDTOList.size());
    }

    @Test
    void findAllVendorDTOOnlyName() throws Exception {
        List<Vendor> vendorList = Collections.singletonList(vendor);

        when(repository.findAllByOrderByIdAsc()).thenReturn(vendorList);
        when(mapper.mapVendorToVendorDTOOnlyName(vendorList.get(0))).thenReturn(new VendorDTOOnlyName());

        var resultedVendorDTOList = service.findAllVendorDTOOnlyName();

        verify(repository).findAllByOrderByIdAsc();
        verify(mapper).mapVendorToVendorDTOOnlyName(vendorList.get(0));
        assertFalse(resultedVendorDTOList.isEmpty());
        assertEquals(1, resultedVendorDTOList.size());
    }

    @Test
    void findById() throws Exception {
        var vendorOptional = Optional.of(vendor);

        when(repository.findById(id)).thenReturn(vendorOptional);
        when(mapper.mapVendorToVendorDTO(vendorOptional.get())).thenReturn(vendorDTO);

        var resultedVendorDTO = service.findById(id);

        verify(repository).findById(id);
        verify(mapper).mapVendorToVendorDTO(vendorOptional.get());
        assertNotNull(resultedVendorDTO);
        assertEquals(id, resultedVendorDTO.getId());
    }

    @Test
    void findByIdWhenNotFound() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> service.findById(id));
        verify(repository).findById(id);
    }

    @Test
    void save() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.empty());
        when(mapper.mapVendorToVendorDTO(vendor)).thenReturn(vendorDTO);
        when(mapper.mapVendorDTOToVendor(vendorDTO)).thenReturn(vendor);
        when(repository.save(vendor)).thenReturn(vendor);
        doNothing().when(entitiesHistoryService)
                .createEntityHistoryRecord(vendor, null, HistoryAction.CREATE);

        var savedVendorDTO = service.save(vendorDTO);

        verify(repository).findById(id);
        verify(mapper).mapVendorToVendorDTO(vendor);
        verify(mapper).mapVendorDTOToVendor(vendorDTO);
        verify(repository).save(vendor);
        verify(entitiesHistoryService).createEntityHistoryRecord(vendor, null, HistoryAction.CREATE);
        assertNotNull(savedVendorDTO);
        assertEquals(id, savedVendorDTO.getId());
    }

    @Test
    void saveWhenAlreadyExist() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.of(vendor));

        assertThrows(DTOFoundWhenSaveException.class, () -> service.save(vendorDTO));
        verify(repository).findById(id);
    }

    @Test
    void update() throws Exception {
        var vendorOptional = Optional.of(vendor);

        when(repository.findById(id)).thenReturn(vendorOptional);
        when(mapper.mapVendorToVendorDTO(vendor)).thenReturn(vendorDTO);
        when(mapper.mapVendorDTOToVendor(vendorDTO)).thenReturn(vendor);
        when(repository.save(vendor)).thenReturn(vendor);
        doNothing().when(entitiesHistoryService)
                .createEntityHistoryRecord(vendor, vendor, HistoryAction.UPDATE);

        var updatedVendorDTO = service.update(vendorDTO);

        verify(repository).findById(id);
        verify(mapper, times(2)).mapVendorToVendorDTO(vendor);
        verify(mapper, times(2)).mapVendorDTOToVendor(vendorDTO);
        verify(repository).save(vendor);
        verify(entitiesHistoryService).createEntityHistoryRecord(vendor, vendor, HistoryAction.UPDATE);
        assertNotNull(updatedVendorDTO);
        assertEquals(id, updatedVendorDTO.getId());
    }

    @Test
    void updateWhenNotFound() throws Exception {
       when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> service.update(vendorDTO));
        verify(repository).findById(id);
    }

    @Test
    void deleteById() throws Exception {
        var vendorOptional = Optional.of(vendor);

        when(repository.findById(id)).thenReturn(vendorOptional);
        when(mapper.mapVendorToVendorDTO(vendor)).thenReturn(vendorDTO);
        when(mapper.mapVendorDTOToVendor(vendorDTO)).thenReturn(vendor);
        doNothing().when(repository).deleteById(id);
        doNothing().when(entitiesHistoryService)
                .createEntityHistoryRecord(vendor, null, HistoryAction.DELETE);

        service.deleteById(id);

        verify(repository).findById(id);
        verify(mapper).mapVendorToVendorDTO(vendor);
        verify(mapper).mapVendorDTOToVendor(vendorDTO);
        verify(repository).deleteById(id);
        verify(entitiesHistoryService).createEntityHistoryRecord(vendor, null, HistoryAction.DELETE);
    }

    @Test
    void deleteByIdWhenNotFound() throws Exception {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DTONotFoundException.class, () -> service.deleteById(id));

        verify(repository).findById(id);
    }
}