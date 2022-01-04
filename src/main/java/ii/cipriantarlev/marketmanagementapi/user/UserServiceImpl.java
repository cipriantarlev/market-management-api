/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.user;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ii.cipriantarlev.marketmanagementapi.history.EntitiesHistoryService;
import ii.cipriantarlev.marketmanagementapi.history.HistoryAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ii.cipriantarlev.marketmanagementapi.exceptions.DTOFoundWhenSaveException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTOListNotFoundException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTONotFoundException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private EntitiesHistoryService entitiesHistoryService;

	@Override
	public List<UserDTO> findAll() {
		List<UserDTO> users = userRepository.findAll().stream().map(this::hideUserPassword)
				.collect(Collectors.toList());

		if (users == null || users.isEmpty()) {
			throw new DTOListNotFoundException("User list not found");
		}

		return users;
	}

	@Override
	public UserDTO findById(Long id) {
		Optional<User> user = userRepository.findById(id);

		if (user.isPresent()) {
			return hideUserPassword(user.get());
		}

		throw new DTONotFoundException(String.format("User with %d not found", id), id);
	}

	@Override
	public UserDTO save(UserDTO userDTO) {
		if (userDTO.getId() != null && userRepository.findById(userDTO.getId()).isPresent()) {
			throw new DTOFoundWhenSaveException(
					String.format("User with id: '%d' already exists in database. "
							+ "Please use update in order to save the changes in database", userDTO.getId()),
					userDTO.getId());
		}

		var user = userRepository.save(userMapper.mapUserDTOToUser(userDTO));
		entitiesHistoryService.createEntityHistoryRecord(user, null, HistoryAction.CREATE);
		return userMapper.mapUserToUserDTO(user);
	}

	@Override
	public UserDTO update(UserDTO userDTO) {
		var foundUser = userMapper.mapUserDTOToUser(this.findById(userDTO.getId()));
		if (userDTO.getUsername() != null && userRepository.findByUsername(userDTO.getUsername()) != null
				&& !userDTO.getUsername().equalsIgnoreCase(foundUser.getUsername())) {

			throw new DTOFoundWhenSaveException(
					String.format("User with username %s already exists in database.", userDTO.getUsername()), 0);
		}
		var user = userRepository.save(userMapper.mapUserDTOToUser(userDTO));
		entitiesHistoryService.createEntityHistoryRecord(user, foundUser, HistoryAction.UPDATE);
		return userMapper.mapUserToUserDTO(user);
	}

	@Override
	public void deleteById(Long id) {
		entitiesHistoryService.createEntityHistoryRecord(userMapper.mapUserDTOToUser(this.findById(id)), null, HistoryAction.DELETE);
		userRepository.deleteById(id);
	}

	@Override
	public UserDTO findByUsername(String username) {
		var user = userRepository.findByUsername(username);

		if (user != null) {
			return userMapper.mapUserToUserDTO(user);
		}

		throw new DTONotFoundException(String.format("User with username %s not found", username));
	}

	private UserDTO hideUserPassword(User user) {
		user.setPassword("");
		return userMapper.mapUserToUserDTO(user);
	}
}
