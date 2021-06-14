package ii.cipriantarlev.marketmanagementapi.user;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ii.cipriantarlev.marketmanagementapi.role.Role;
import ii.cipriantarlev.marketmanagementapi.role.RoleDTO;
import ii.cipriantarlev.marketmanagementapi.role.RoleMapper;

@Component
public class UserMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RoleMapper roleMapper;

	public UserDTO mapUserToUserDTO(User user) {

		modelMapper.typeMap(User.class, UserDTO.class).addMappings(mapper -> {
			mapper.map(User::getId, UserDTO::setId);
			mapper.map(User::getUsername, UserDTO::setUsername);
			mapper.map(User::getPassword, UserDTO::setPassword);
			mapper.map(User::getEmail, UserDTO::setEmail);
			mapper.map(userMapper -> mapRoleToRoleDTO(user.getRoles()), UserDTO::setRoles);
		});

		return modelMapper.map(user, UserDTO.class);
	}
	
	public User mapUserDTOToUser(UserDTO user) {
		
		modelMapper.typeMap(UserDTO.class, User.class).addMappings(mapper -> {
			mapper.map(UserDTO::getId, User::setId);
			mapper.map(UserDTO::getUsername, User::setUsername);
			mapper.map(UserDTO::getPassword, User::setPassword);
			mapper.map(UserDTO::getEmail, User::setEmail);
			mapper.map(userMapper -> mapRoleDTOToRole(user.getRoles()), User::setRoles);
		});

		return modelMapper.map(user, User.class);
		
	}

	private List<RoleDTO> mapRoleToRoleDTO(List<Role> roles) {
		return roles.stream()
				.map(role -> roleMapper.mapRoleToRoleDTO(role))
					.collect(Collectors.toList());
	}
	
	private List<Role> mapRoleDTOToRole(List<RoleDTO> roles) {
		return roles.stream()
				.map(role -> roleMapper.mapRoleDTOToRole(role))
					.collect(Collectors.toList());
	}
}
