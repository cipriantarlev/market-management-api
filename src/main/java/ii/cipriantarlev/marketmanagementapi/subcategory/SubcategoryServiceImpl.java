/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.subcategory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	public List<SubcategoryDTONoCategory> findAllByCategoryId(Integer id) {
		List<SubcategoryDTONoCategory> subcategories = subcategoryRepository.findAllByCategoryId(id).stream()
				.map(subcategory -> subcategoryMapper.mapEntityToNoCategoryDTO(subcategory))
				.collect(Collectors.toList());

		if (subcategories == null || subcategories.isEmpty()) {
			throw new DTOListNotFoundException("Subcategory list not found");
		}

		return subcategories;
	}

	@Override
	public SubcategoryDTO findById(Integer id) {
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

		var category = subcategoryRepository.save(subcategoryMapper.mapDTOToEntity(subcategoryDTO));
		return subcategoryMapper.mapEntityToDTO(category);
	}

	@Override
	public SubcategoryDTO update(SubcategoryDTO subcategoryDTO) {
		this.findById(subcategoryDTO.getId());
		var category = subcategoryRepository.save(subcategoryMapper.mapDTOToEntity(subcategoryDTO));
		return subcategoryMapper.mapEntityToDTO(category);
	}

	@Override
	public void deleteById(Integer id) {
		this.findById(id);
		subcategoryRepository.deleteById(id);
	}

}
