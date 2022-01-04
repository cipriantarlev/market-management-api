/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.plu;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ii.cipriantarlev.marketmanagementapi.history.EntitiesHistoryService;
import ii.cipriantarlev.marketmanagementapi.history.HistoryAction;
import ii.cipriantarlev.marketmanagementapi.utils.MarketManagementFactory;
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

	@Autowired
	private MarketManagementFactory factory;

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
	public PluDTO findById(Long id) {
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
			var firstPlu = pluRepository.save(factory.getNewPlu(0));
			return pluMapper.mapEntityToDTO(firstPlu);
		}

		var generatedPlu = pluRepository.save(factory.getNewPlu(plu.getValue()));
		entitiesHistoryService.createEntityHistoryRecord(generatedPlu, null, HistoryAction.CREATE);
		return pluMapper.mapEntityToDTO(generatedPlu);
	}

	@Override
	public void deleteById(Long id) {
		entitiesHistoryService.createEntityHistoryRecord(pluMapper.mapDTOToEntity(this.findById(id)), null, HistoryAction.DELETE);
		pluRepository.deleteById(id);
	}

}
