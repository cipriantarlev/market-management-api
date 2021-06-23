package ii.cipriantarlev.marketmanagementapi.user;

import java.util.List;

public interface UserService {

	List<UserDTO> findAll();

	UserDTO findById(Integer id);

	UserDTO findByUsername(String username);

	User save(UserDTO userDTO);

	void deleteById(Integer id);
}
