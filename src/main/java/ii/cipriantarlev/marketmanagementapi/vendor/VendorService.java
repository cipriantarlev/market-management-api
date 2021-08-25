/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.vendor;

import java.util.List;

public interface VendorService {

	List<VendorDTONoRegions> findAll();

	List<VendorDTOOnlyName> findAllVendorDTOOnlyName();

	VendorDTO findById(Integer id);

	Vendor save(VendorDTO vendorDTO);

	void deleteById(Integer id);
}
