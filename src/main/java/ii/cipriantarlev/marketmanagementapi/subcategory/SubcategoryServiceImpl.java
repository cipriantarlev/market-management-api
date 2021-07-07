package ii.cipriantarlev.marketmanagementapi.subcategory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubcategoryServiceImpl implements SubcategoryService {

	@Autowired
	private SubcategoryRepository subcategoryRepository;

	@Autowired
	private SubcategoryMapper subcategoryMapper;

	@Override
	public List<SubcategoryDTO> findAll() {
		return subcategoryRepository.findAll().stream()
				.map(subcategory -> subcategoryMapper.mapEntityToDTO(subcategory))
				.collect(Collectors.toList());
	}

	@Override
	public List<SubcategoryDTO> findAllByCategoryId(Integer id) {
		return subcategoryRepository.findAllByCategoryId(id).stream()
				.map(subcategory -> subcategoryMapper.mapEntityToDTO(subcategory))
				.collect(Collectors.toList());
	}

	@Override
	public SubcategoryDTO findById(Integer id) {
		Optional<Subcategory> subcategory = subcategoryRepository.findById(id);

		if (subcategory.isPresent()) {
			return subcategoryMapper.mapEntityToDTO(subcategory.get());
		}

		return null;
	}

	@Override
	public SubcategoryDTO save(SubcategoryDTO subcategoryDTO) {
		var category = subcategoryRepository.save(subcategoryMapper.mapDTOToEntity(subcategoryDTO));
		return subcategoryMapper.mapEntityToDTO(category);
	}

	@Override
	public void deleteById(Integer id) {
		subcategoryRepository.deleteById(id);
	}

}
