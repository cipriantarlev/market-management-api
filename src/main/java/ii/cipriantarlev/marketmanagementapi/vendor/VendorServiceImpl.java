package ii.cipriantarlev.marketmanagementapi.vendor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendorServiceImpl implements VendorService {

	@Autowired
	private VendorRepository vendorRepository;

	@Autowired
	private VendorMapper vendorMapper;

	@Override
	public List<VendorDTONoRegions> findAll() {
		return vendorRepository.findAllByOrderByIdAsc().stream()
				.map(vendor -> vendorMapper.mapVendorToVendorDTONoRegions(vendor))
				.collect(Collectors.toList());
	}

	@Override
	public VendorDTO findById(Integer id) {
		Optional<Vendor> vendor = vendorRepository.findById(id);

		if (vendor.isPresent()) {
			return vendorMapper.mapVendorToVendorDTO(vendor.get());
		}

		return null;
	}

	@Override
	public Vendor save(VendorDTO vendorDTO) {
		var vendor = vendorMapper.mapVendorDTOToVendor(vendorDTO);
		return vendorRepository.save(vendor);
	}

	@Override
	public void deleteById(Integer id) {
		vendorRepository.deleteById(id);
	}
}
