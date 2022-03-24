/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.vendor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VendorMapper {

	@Autowired
	private ModelMapper modelMapper;

	public VendorDTO mapVendorToVendorDTO(Vendor vendor) {
		return modelMapper.map(vendor, VendorDTO.class);
	}

	public Vendor mapVendorDTOToVendor(VendorDTO vendorDTO) {
		return modelMapper.map(vendorDTO, Vendor.class);
	}

	public VendorDTONoRegions mapVendorToVendorDTONoRegions(Vendor vendor) {
		return modelMapper.map(vendor, VendorDTONoRegions.class);
	}

	public VendorDTOOnlyName mapVendorToVendorDTOOnlyName(Vendor vendor) {
		return modelMapper.map(vendor, VendorDTOOnlyName.class);
	}
}
