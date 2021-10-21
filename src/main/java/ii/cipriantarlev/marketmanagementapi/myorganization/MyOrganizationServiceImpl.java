/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.myorganization;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ii.cipriantarlev.marketmanagementapi.exceptions.DTOFoundWhenSaveException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTOListNotFoundException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTONotFoundException;

@Service
public class MyOrganizationServiceImpl implements MyOrganizationService {

	@Autowired
	private MyOrganizationRepository myOrganizationRepository;

	@Autowired
	private MyOrganizationMapper myOrganizationMapper;

	@Override
	public List<MyOrganizationDTO> findAll() {
		List<MyOrganizationDTO> myOrganizations = myOrganizationRepository.findAll().stream()
				.map(myOrganization -> myOrganizationMapper.mapEntityToDTO(myOrganization))
				.collect(Collectors.toList());

		if (myOrganizations == null || myOrganizations.isEmpty()) {
			throw new DTOListNotFoundException("My Organization list not found");
		}

		return myOrganizations;
	}

	@Override
	public MyOrganizationDTO findById(Integer id) {
		Optional<MyOrganization> myOrganization = myOrganizationRepository.findById(id);

		if (myOrganization.isPresent()) {
			return myOrganizationMapper.mapEntityToDTO(myOrganization.get());
		}

		throw new DTONotFoundException(String.format("My Organization with %d not found", id), id);
	}

	@Override
	public MyOrganizationDTO save(MyOrganizationDTO myOrganizationDTO) {
		if (myOrganizationDTO.getId() != null
				&& myOrganizationRepository.findById(myOrganizationDTO.getId()).isPresent()) {
			throw new DTOFoundWhenSaveException(
					String.format(
							"Measuring Unit with id: '%d' already exists in database. "
									+ "Please use update in order to save the changes in database",
							myOrganizationDTO.getId()),
					myOrganizationDTO.getId());
		}

		var myOrganization = myOrganizationRepository
				.save(myOrganizationMapper.mapDTOToEntity(myOrganizationDTO));
		return myOrganizationMapper.mapEntityToDTO(myOrganization);
	}

	@Override
	public MyOrganizationDTO update(MyOrganizationDTO myOrganizationDTO) {
		this.findById(myOrganizationDTO.getId());
		var myOrganization = myOrganizationRepository
				.save(myOrganizationMapper.mapDTOToEntity(myOrganizationDTO));
		return myOrganizationMapper.mapEntityToDTO(myOrganization);
	}

	@Override
	public void deleteById(Integer id) {
		this.findById(id);
		myOrganizationRepository.deleteById(id);
	}

	@Override
	public List<MyOrganizationDTOOnlyName> findAllMyOrganizationDTOOnlyName() {
		List<MyOrganizationDTOOnlyName> myOrganizationList = myOrganizationRepository.findAll().stream()
				.map(myOrganization -> myOrganizationMapper.mapEntityToMyOrganizationDTOOnlyName(myOrganization))
				.collect(Collectors.toList());

		if (myOrganizationList == null || myOrganizationList.isEmpty()) {
			throw new DTOListNotFoundException("Measuring unit list not found");
		}

		return myOrganizationList;
	}
}
