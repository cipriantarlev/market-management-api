/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.subcategory;

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
public class SubcategoryServiceImpl implements SubcategoryService {

	@Autowired
	private SubcategoryRepository subcategoryRepository;

	@Autowired
	private SubcategoryMapper subcategoryMapper;

	@Autowired
	private EntitiesHistoryService entitiesHistoryService;

	@Override
	public List<SubcategoryDTO> findAll() {
		List<SubcategoryDTO> subcategories = subcategoryRepository.findAll().stream()
				.map(subcategory -> subcategoryMapper.mapEntityToDTO(subcategory))
				.collect(Collectors.toList());

		if (subcategories == null || subcategories.isEmpty()) {
			throw new DTOListNotFoundException("Subcategory list not found");
		}

		return subcategories;
	}

	@Override
	public List<SubcategoryDTONoCategory> findAllByCategoryId(Long id) {
		List<SubcategoryDTONoCategory> subcategories = subcategoryRepository.findAllByCategoryId(id).stream()
				.map(subcategory -> subcategoryMapper.mapEntityToNoCategoryDTO(subcategory))
				.collect(Collectors.toList());

		if (subcategories.isEmpty()) {
			throw new DTOListNotFoundException("Subcategory list not found");
		}

		return subcategories;
	}

	@Override
	public SubcategoryDTO findById(Long id) {
		Optional<Subcategory> subcategory = subcategoryRepository.findById(id);

		if (subcategory.isPresent()) {
			return subcategoryMapper.mapEntityToDTO(subcategory.get());
		}

		throw new DTONotFoundException(String.format("Subcategory with %d not found", id), id);
	}

	@Override
	public SubcategoryDTO save(SubcategoryDTO subcategoryDTO) {
		if (subcategoryDTO.getId() != null && subcategoryRepository.findById(subcategoryDTO.getId()).isPresent()) {
			throw new DTOFoundWhenSaveException(
					String.format(
							"Subcategory with id: '%d' already exists in database. "
									+ "Please use update in order to save the changes in database",
							subcategoryDTO.getId()),
					subcategoryDTO.getId());
		}

		var subcategory = subcategoryRepository.save(subcategoryMapper.mapDTOToEntity(subcategoryDTO));
		entitiesHistoryService.createEntityHistoryRecord(subcategory, null, HistoryAction.CREATE);
		return subcategoryMapper.mapEntityToDTO(subcategory);
	}

	@Override
	public SubcategoryDTO update(SubcategoryDTO subcategoryDTO) {
		var foundSubcategory = subcategoryMapper.mapDTOToEntity(this.findById(subcategoryDTO.getId()));
		var subcategory = subcategoryRepository.save(subcategoryMapper.mapDTOToEntity(subcategoryDTO));
		entitiesHistoryService.createEntityHistoryRecord(subcategory, foundSubcategory, HistoryAction.UPDATE);
		return subcategoryMapper.mapEntityToDTO(subcategory);
	}

	@Override
	public void deleteById(Long id) {
		entitiesHistoryService.createEntityHistoryRecord(subcategoryMapper.mapDTOToEntity(this.findById(id)), null, HistoryAction.DELETE);
		subcategoryRepository.deleteById(id);
	}

}
