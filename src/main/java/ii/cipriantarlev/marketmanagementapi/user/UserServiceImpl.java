package ii.cipriantarlev.marketmanagementapi.user;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;

	@Override
	public List<UserDTO> findAll() {
		return userRepository.findAll().stream().map(user -> userMapper.mapUserToUserDTO(user))
				.collect(Collectors.toList());
	}

	@Override
	public UserDTO findById(Integer id) {
		Optional<User> user = userRepository.findById(id);

		if (user.isPresent()) {
			return userMapper.mapUserToUserDTO(user.get());
		}
		return null;
	}

	@Override
	public UserDTO findByUsername(String username) {
		var user = userRepository.findByUsername(username);

		if (user != null) {
			return userMapper.mapUserToUserDTO(user);
		}

		return null;
	}

	@Override
	public User save(UserDTO userDTO) {
		var user = userMapper.mapUserDTOToUser(userDTO);
		return userRepository.save(user);
	}

	@Override
	public void deleteById(Integer id) {
		userRepository.deleteById(id);
	}
}
