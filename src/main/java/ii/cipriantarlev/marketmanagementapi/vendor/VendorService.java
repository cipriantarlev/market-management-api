package ii.cipriantarlev.marketmanagementapi.vendor;

import java.util.List;

public interface VendorService {

	List<VendorDTO> findAll();

	VendorDTO findById(Integer id);

	Vendor save(VendorDTO vendorDTO);

	void deleteById(Integer id);
}
