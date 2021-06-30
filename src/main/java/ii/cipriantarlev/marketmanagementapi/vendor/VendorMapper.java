package ii.cipriantarlev.marketmanagementapi.vendor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VendorMapper {

	@Autowired
	private ModelMapper modelMapper;

	public VendorDTO mapVendorToVendorDTO(Vendor vendor) {

		modelMapper.typeMap(Vendor.class, VendorDTO.class).addMappings(mapper -> {
			mapper.map(Vendor::getId, VendorDTO::setId);
			mapper.map(Vendor::getName, VendorDTO::setName);
			mapper.map(Vendor::getBank, VendorDTO::setBank);
			mapper.map(Vendor::getFiscalCode, VendorDTO::setFiscalCode);
			mapper.map(Vendor::getBankAccount, VendorDTO::setBankAccount);
			mapper.map(Vendor::getCurrency, VendorDTO::setCurrency);
			mapper.map(Vendor::getVatCode, VendorDTO::setVatCode);
			mapper.map(Vendor::getCity, VendorDTO::setCity);
			mapper.map(Vendor::getRegion, VendorDTO::setRegion);
			mapper.map(Vendor::getPhoneNumber, VendorDTO::setPhoneNumber);
			mapper.map(Vendor::getPostalCode, VendorDTO::setPostalCode);
			mapper.map(Vendor::getBusinessAddress, VendorDTO::setBusinessAddress);
			mapper.map(Vendor::getVendorType, VendorDTO::setVendorType);
			mapper.map(Vendor::getVendorLegalType, VendorDTO::setVendorLegalType);
			mapper.map(Vendor::getNote, VendorDTO::setNote);
		});

		return modelMapper.map(vendor, VendorDTO.class);
	}

	public Vendor mapVendorDTOToVendor(VendorDTO vendorDTO) {

		modelMapper.typeMap(VendorDTO.class, Vendor.class).addMappings(mapper -> {
			mapper.map(VendorDTO::getId, Vendor::setId);
			mapper.map(VendorDTO::getName, Vendor::setName);
			mapper.map(VendorDTO::getBank, Vendor::setBank);
			mapper.map(VendorDTO::getFiscalCode, Vendor::setFiscalCode);
			mapper.map(VendorDTO::getBankAccount, Vendor::setBankAccount);
			mapper.map(VendorDTO::getCurrency, Vendor::setCurrency);
			mapper.map(VendorDTO::getVatCode, Vendor::setVatCode);
			mapper.map(VendorDTO::getCity, Vendor::setCity);
			mapper.map(VendorDTO::getRegion, Vendor::setRegion);
			mapper.map(VendorDTO::getPhoneNumber, Vendor::setPhoneNumber);
			mapper.map(VendorDTO::getPostalCode, Vendor::setPostalCode);
			mapper.map(VendorDTO::getBusinessAddress, Vendor::setBusinessAddress);
			mapper.map(VendorDTO::getVendorType, Vendor::setVendorType);
			mapper.map(VendorDTO::getVendorLegalType, Vendor::setVendorLegalType);
			mapper.map(VendorDTO::getNote, Vendor::setNote);
		});

		return modelMapper.map(vendorDTO, Vendor.class);
	}

}
