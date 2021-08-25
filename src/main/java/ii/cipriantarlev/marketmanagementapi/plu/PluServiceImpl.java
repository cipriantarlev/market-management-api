/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.plu;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PluServiceImpl implements PluService {

	@Autowired
	private PluRepository pluRepository;

	@Autowired
	private PluMapper pluMapper;

	@Override
	public List<PluDTO> findAll() {
		return pluRepository.findAll().stream()
				.map(plu -> pluMapper.mapEntityToDTO(plu))
				.collect(Collectors.toList());
	}

	@Override
	public PluDTO findById(Integer id) {
		Optional<Plu> plu = pluRepository.findById(id);

		if (plu.isPresent()) {
			return pluMapper.mapEntityToDTO(plu.get());
		}

		return null;
	}

	@Override
	public PluDTO generateNewPlu() {
		var plu = pluRepository.findFirst1ByOrderByValueDesc();

		if (plu == null) {
			var firstPlu = pluRepository.save(new Plu(1));
			return pluMapper.mapEntityToDTO(firstPlu);
		}

		var generatedPlu = pluRepository.save(new Plu(plu.getValue() + 1));
		return pluMapper.mapEntityToDTO(generatedPlu);
	}

	@Override
	public void deleteById(Integer id) {
		pluRepository.deleteById(id);
	}

}
