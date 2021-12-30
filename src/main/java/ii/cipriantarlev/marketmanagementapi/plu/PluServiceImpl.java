/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.plu;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ii.cipriantarlev.marketmanagementapi.history.EntitiesHistoryService;
import ii.cipriantarlev.marketmanagementapi.history.HistoryAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ii.cipriantarlev.marketmanagementapi.exceptions.DTOListNotFoundException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTONotFoundException;

@Service
public class PluServiceImpl implements PluService {

	@Autowired
	private PluRepository pluRepository;

	@Autowired
	private PluMapper pluMapper;

	@Autowired
	private EntitiesHistoryService entitiesHistoryService;

	@Override
	public List<PluDTO> findAll() {
		List<PluDTO> pluList = pluRepository.findAll().stream()
				.map(plu -> pluMapper.mapEntityToDTO(plu))
				.collect(Collectors.toList());

		if (pluList == null || pluList.isEmpty()) {
			throw new DTOListNotFoundException("PLU list not found");
		}

		return pluList;
	}

	@Override
	public PluDTO findById(Integer id) {
		Optional<Plu> plu = pluRepository.findById(id);

		if (plu.isPresent()) {
			return pluMapper.mapEntityToDTO(plu.get());
		}

		throw new DTONotFoundException(String.format("PLU with %d not found", id), id);
	}

	@Override
	public PluDTO generateNewPlu() {
		var plu = pluRepository.findFirst1ByOrderByValueDesc();

		if (plu == null) {
			var firstPlu = pluRepository.save(new Plu(1));
			return pluMapper.mapEntityToDTO(firstPlu);
		}

		var generatedPlu = pluRepository.save(new Plu(plu.getValue() + 1));
		entitiesHistoryService.createEntityHistoryRecord(generatedPlu, null, HistoryAction.CREATE);
		return pluMapper.mapEntityToDTO(generatedPlu);
	}

	@Override
	public void deleteById(Integer id) {
		entitiesHistoryService.createEntityHistoryRecord(pluMapper.mapDTOToEntity(this.findById(id)), null, HistoryAction.DELETE);
		pluRepository.deleteById(id);
	}

}
