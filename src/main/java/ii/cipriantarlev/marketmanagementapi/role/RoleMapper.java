/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.role;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

	@Autowired
	private ModelMapper modelMapper;

	public RoleDTO mapRoleToRoleDTO(Role role) {
		return modelMapper.map(role, RoleDTO.class);
	}

	public Role mapRoleDTOToRole(RoleDTO role) {
		return modelMapper.map(role, Role.class);
	}
}
