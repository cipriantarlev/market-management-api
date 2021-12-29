/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.user;

import java.util.List;

public interface UserService {

	List<UserDTO> findAll();

	UserDTO findById(Long id);

	UserDTO findByUsername(String username);

	UserDTO save(UserDTO userDTO);

	UserDTO update(UserDTO userDTO);

	void deleteById(Long id);
}
