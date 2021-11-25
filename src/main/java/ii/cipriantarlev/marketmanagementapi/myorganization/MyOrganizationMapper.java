/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.myorganization;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyOrganizationMapper {

	@Autowired
	private ModelMapper modelMapper;

	public MyOrganizationDTO mapEntityToDTO(MyOrganization myOrganization) {
		return modelMapper.map(myOrganization, MyOrganizationDTO.class);
	}

	public MyOrganization mapDTOToEntity(MyOrganizationDTO myOrganization) {
		return modelMapper.map(myOrganization, MyOrganization.class);
	}

	public MyOrganizationDTOOnlyName mapEntityToMyOrganizationDTOOnlyName(MyOrganization myOrganization) {
		return modelMapper.map(myOrganization, MyOrganizationDTOOnlyName.class);
	}
}
