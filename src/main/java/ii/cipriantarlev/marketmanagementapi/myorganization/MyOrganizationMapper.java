package ii.cipriantarlev.marketmanagementapi.myorganization;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyOrganizationMapper {

	@Autowired
	private ModelMapper modelMapper;

	public MyOrganizationDTO mapEntityToDTO(MyOrganization myOrganization) {

		modelMapper.typeMap(MyOrganization.class, MyOrganizationDTO.class).addMappings(mapper -> {
			mapper.map(MyOrganization::getId, MyOrganizationDTO::setId);
			mapper.map(MyOrganization::getName, MyOrganizationDTO::setName);
			mapper.map(MyOrganization::getBank, MyOrganizationDTO::setBank);
			mapper.map(MyOrganization::getFiscalCode, MyOrganizationDTO::setFiscalCode);
			mapper.map(MyOrganization::getBankAccount, MyOrganizationDTO::setBankAccount);
			mapper.map(MyOrganization::getVatCode, MyOrganizationDTO::setVatCode);
			mapper.map(MyOrganization::getCity, MyOrganizationDTO::setCity);
			mapper.map(MyOrganization::getPhoneNumber, MyOrganizationDTO::setPhoneNumber);
			mapper.map(MyOrganization::getEmail, MyOrganizationDTO::setEmail);
			mapper.map(MyOrganization::getNote, MyOrganizationDTO::setNote);
		});

		return modelMapper.map(myOrganization, MyOrganizationDTO.class);
	}

	public MyOrganization mapDTOToEntity(MyOrganizationDTO myOrganization) {

		modelMapper.typeMap(MyOrganizationDTO.class, MyOrganization.class).addMappings(mapper -> {
			mapper.map(MyOrganizationDTO::getId, MyOrganization::setId);
			mapper.map(MyOrganizationDTO::getName, MyOrganization::setName);
			mapper.map(MyOrganizationDTO::getBank, MyOrganization::setBank);
			mapper.map(MyOrganizationDTO::getFiscalCode, MyOrganization::setFiscalCode);
			mapper.map(MyOrganizationDTO::getBankAccount, MyOrganization::setBankAccount);
			mapper.map(MyOrganizationDTO::getVatCode, MyOrganization::setVatCode);
			mapper.map(MyOrganizationDTO::getCity, MyOrganization::setCity);
			mapper.map(MyOrganizationDTO::getPhoneNumber, MyOrganization::setPhoneNumber);
			mapper.map(MyOrganizationDTO::getEmail, MyOrganization::setEmail);
			mapper.map(MyOrganizationDTO::getNote, MyOrganization::setNote);
		});

		return modelMapper.map(myOrganization, MyOrganization.class);
	}
}
