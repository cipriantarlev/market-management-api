package ii.cipriantarlev.marketmanagementapi.user;

import java.util.List;

import ii.cipriantarlev.marketmanagementapi.role.RoleDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserDTO {

	private Integer id;

	private String username;

	private String password;

	private String email;

	private List<RoleDTO> roles;

}
