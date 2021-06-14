package ii.cipriantarlev.marketmanagementapi.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import ii.cipriantarlev.marketmanagementapi.role.RoleDTO;

@Component
public class DetailsService implements UserDetailsService {

	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDTO user = userService.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username + "was not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				AuthorityUtils.createAuthorityList(mapRoleDTOToStringArray(user.getRoles())));
	}

	private String[] mapRoleDTOToStringArray(List<RoleDTO> roles) {
		List<String> roleList = roles.stream()
									 .map(RoleDTO::getRoleName)
									 .collect(Collectors.toList());

		var array = new String[roleList.size()];
		return roleList.toArray(array);
	}

}
