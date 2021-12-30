/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.vat;

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
public class VatServiceImpl implements VatService {

	@Autowired
	private VatRepository vatRepository;

	@Autowired
	private VatMapper vatMapper;

	@Autowired
	private EntitiesHistoryService entitiesHistoryService;

	@Override
	public List<VatDTO> findAll() {
		List<VatDTO> vatList = vatRepository.findAll().stream()
				.map(vat -> vatMapper.mapVatToVatDTO(vat))
				.collect(Collectors.toList());

		if (vatList.isEmpty()) {
			throw new DTOListNotFoundException("VAT list not found");
		}

		return vatList;
	}

	@Override
	public VatDTO findById(Long id) {
		Optional<Vat> vat = vatRepository.findById(id);

		if (vat.isPresent()) {
			return vatMapper.mapVatToVatDTO(vat.get());
		}

		throw new DTONotFoundException(String.format("VAT with %d not found", id), id);
	}

	@Override
	public VatDTO save(VatDTO vatDTO) {
		if (vatDTO.getId() != null && vatRepository.findById(vatDTO.getId()).isPresent()) {
			throw new DTOFoundWhenSaveException(
					String.format("VAT with id: '%d' already exists in database. "
							+ "Please use update in order to save the changes in database", vatDTO.getId()),
					vatDTO.getId());
		}

		var vat = vatRepository.save(vatMapper.mapVatDTOToVat(vatDTO));
		entitiesHistoryService.createEntityHistoryRecord(vat, null, HistoryAction.CREATE);
		return vatMapper.mapVatToVatDTO(vat);
	}

	@Override
	public VatDTO update(VatDTO vatDTO) {
		var foundVat = vatMapper.mapVatDTOToVat(this.findById(vatDTO.getId()));
		var vat = vatRepository.save(vatMapper.mapVatDTOToVat(vatDTO));
		entitiesHistoryService.createEntityHistoryRecord(vat, foundVat, HistoryAction.UPDATE);
		return vatMapper.mapVatToVatDTO(vat);
	}

	@Override
	public void deleteById(Long id) {
		entitiesHistoryService.createEntityHistoryRecord(vatMapper.mapVatDTOToVat(this.findById(id)), null, HistoryAction.DELETE);
		vatRepository.deleteById(id);
	}
}
