package ii.cipriantarlev.marketmanagementapi.myorganization;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyOrganizationServiceImpl implements MyOrganizationService {

	@Autowired
	private MyOrganizationRepository myOrganizationRepository;

	@Autowired
	private MyOrganizationMapper myOrganizationMapper;

	@Override
	public List<MyOrganizationDTO> findAll() {
		return myOrganizationRepository.findAll().stream()
				.map(myOrganization -> myOrganizationMapper.mapEntityToDTO(myOrganization))
				.collect(Collectors.toList());
	}

	@Override
	public MyOrganizationDTO findById(Integer id) {
		Optional<MyOrganization> myOrganization = myOrganizationRepository.findById(id);

		if (myOrganization.isPresent()) {
			return myOrganizationMapper.mapEntityToDTO(myOrganization.get());
		}

		return null;
	}

	@Override
	public MyOrganization save(MyOrganizationDTO userDTO) {
		var myOrganization = myOrganizationMapper.mapDTOToEntity(userDTO);
		return myOrganizationRepository.save(myOrganization);
	}

	@Override
	public void deleteById(Integer id) {
		myOrganizationRepository.deleteById(id);
	}

	@Override
	public List<MyOrganizationDTOOnlyName> findAllMyOrganizationDTOOnlyName() {
		return myOrganizationRepository.findAll().stream()
				.map(myOrganization -> myOrganizationMapper.mapEntityToMyOrganizationDTOOnlyName(myOrganization))
				.collect(Collectors.toList());
	}
}
