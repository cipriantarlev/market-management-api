/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.myorganization;

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
public class MyOrganizationServiceImpl implements MyOrganizationService {

	@Autowired
	private MyOrganizationRepository myOrganizationRepository;

	@Autowired
	private MyOrganizationMapper myOrganizationMapper;

	@Autowired
	private EntitiesHistoryService entitiesHistoryService;

	@Override
	public List<MyOrganizationDTO> findAll() {
		List<MyOrganizationDTO> myOrganizations = myOrganizationRepository.findAll().stream()
				.map(myOrganization -> myOrganizationMapper.mapEntityToDTO(myOrganization))
				.collect(Collectors.toList());

		if (myOrganizations.isEmpty()) {
			throw new DTOListNotFoundException("My Organization list not found");
		}

		return myOrganizations;
	}

	@Override
	public MyOrganizationDTO findById(Long id) {
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
							"My Organization with id: '%d' already exists in database. "
									+ "Please use update in order to save the changes in database",
							myOrganizationDTO.getId()),
					myOrganizationDTO.getId());
		}

		var myOrganization = myOrganizationRepository
				.save(myOrganizationMapper.mapDTOToEntity(myOrganizationDTO));
		entitiesHistoryService.createEntityHistoryRecord(myOrganization, null, HistoryAction.CREATE);
		return myOrganizationMapper.mapEntityToDTO(myOrganization);
	}

	@Override
	public MyOrganizationDTO update(MyOrganizationDTO myOrganizationDTO) {
		var foundMyOrganization = myOrganizationMapper.mapDTOToEntity(this.findById(myOrganizationDTO.getId()));
		var myOrganization = myOrganizationRepository
				.save(myOrganizationMapper.mapDTOToEntity(myOrganizationDTO));
		entitiesHistoryService.createEntityHistoryRecord(myOrganization, foundMyOrganization, HistoryAction.UPDATE);
		return myOrganizationMapper.mapEntityToDTO(myOrganization);
	}

	@Override
	public void deleteById(Long id) {
		entitiesHistoryService.createEntityHistoryRecord(myOrganizationMapper.mapDTOToEntity(this.findById(id)), null, HistoryAction.DELETE);
		myOrganizationRepository.deleteById(id);
	}

	@Override
	public List<MyOrganizationDTOOnlyName> findAllMyOrganizationDTOOnlyName() {
		List<MyOrganizationDTOOnlyName> myOrganizationList = myOrganizationRepository.findAll().stream()
				.map(myOrganization -> myOrganizationMapper.mapEntityToMyOrganizationDTOOnlyName(myOrganization))
				.collect(Collectors.toList());

		if (myOrganizationList.isEmpty()) {
			throw new DTOListNotFoundException("Measuring unit list not found");
		}

		return myOrganizationList;
	}
}
