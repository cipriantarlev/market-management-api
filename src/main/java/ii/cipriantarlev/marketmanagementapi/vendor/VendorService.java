/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.vendor;

import java.util.List;

public interface VendorService {

	List<VendorDTONoRegions> findAll();

	List<VendorDTOOnlyName> findAllVendorDTOOnlyName();

	VendorDTO findById(Long id);

	VendorDTO save(VendorDTO vendorDTO);

	VendorDTO update(VendorDTO vendorDTO);

	void deleteById(Long id);
}
