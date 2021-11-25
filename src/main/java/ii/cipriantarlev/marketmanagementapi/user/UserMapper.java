/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

	@Autowired
	private ModelMapper modelMapper;

	public UserDTO mapUserToUserDTO(User user) {
		return modelMapper.map(user, UserDTO.class);
	}
	
	public User mapUserDTOToUser(UserDTO user) {
		return modelMapper.map(user, User.class);
	}
}
