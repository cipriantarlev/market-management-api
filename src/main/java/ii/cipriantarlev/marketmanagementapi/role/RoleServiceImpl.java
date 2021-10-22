/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.role;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ii.cipriantarlev.marketmanagementapi.exceptions.DTOListNotFoundException;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public List<RoleDTO> findAll() {
		List<RoleDTO> roles = roleRepository.findAll()
							 .stream()
							 .map(role -> roleMapper.mapRoleToRoleDTO(role))
							 .collect(Collectors.toList());

		if (roles == null || roles.isEmpty()) {
			throw new DTOListNotFoundException("Role list not found");
		}

		return roles;
	}
}
