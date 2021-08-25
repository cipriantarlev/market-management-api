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

		modelMapper.typeMap(Role.class, RoleDTO.class).addMappings(mapper -> {
			mapper.map(Role::getId, RoleDTO::setId);
			mapper.map(Role::getRoleName, RoleDTO::setRole);
		});

		return modelMapper.map(role, RoleDTO.class);
	}

	public Role mapRoleDTOToRole(RoleDTO role) {

		modelMapper.typeMap(RoleDTO.class, Role.class).addMappings(mapper -> {
			mapper.map(RoleDTO::getId, Role::setId);
			mapper.map(RoleDTO::getRole, Role::setRoleName);
		});

		return modelMapper.map(role, Role.class);
	}
}
