/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.barcode;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ii.cipriantarlev.marketmanagementapi.history.EntitiesHistory;
import ii.cipriantarlev.marketmanagementapi.history.EntitiesHistoryService;
import ii.cipriantarlev.marketmanagementapi.history.HistoryAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ii.cipriantarlev.marketmanagementapi.exceptions.DTOListNotFoundException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTONotFoundException;

/**
 * Class to implement {@link BarcodeService} interface.
 */
@Service
public class BarcodeServiceImpl implements BarcodeService {

	/**
	 * {@link BarcodeRepository} used to connect with database.
	 */
	@Autowired
	private BarcodeRepository barcodeRepository;

	/**
	 * {@link BarcodeMapper} used to map entity to dto and vice-versa.
	 */
	@Autowired
	private BarcodeMapper barcodeMapper;

	/**
	 * {@link EntitiesHistoryService} used to create {@link EntitiesHistory}
	 * records in database based on action performed on {@link Barcode}.
	 */
	@Autowired
	private EntitiesHistoryService entitiesHistoryService;

	@Override
	public List<BarcodeDTO> findAll() {
		List<BarcodeDTO> barcodes = barcodeRepository.findAll().stream()
				.map(barcode -> barcodeMapper.mapEntityToDTO(barcode)).collect(Collectors.toList());

		if (barcodes.isEmpty()) {
			throw new DTOListNotFoundException("Barcode list not found");
		}

		return barcodes;
	}

	@Override
	public BarcodeDTO findById(Long id) {
		Optional<Barcode> barcode = barcodeRepository.findById(id);

		if (barcode.isPresent()) {
			return barcodeMapper.mapEntityToDTO(barcode.get());
		}

		throw new DTONotFoundException(String.format("Barcode with %d not found", id), id);
	}

	@Override
	public BarcodeDTO generateNewBarcode(BarcodeDTO barcodeDTO) {
		if (barcodeDTO.getValue().equalsIgnoreCase("21")) {
			return getGeneratedBarcode(barcodeDTO, "2100000");
		}

		if (barcodeDTO.getValue().equalsIgnoreCase("22")) {
			return getGeneratedBarcode(barcodeDTO, "2200000000000");
		}

		return barcodeDTO;
	}

	@Override
	public void deleteById(Long id) {
		var barcode = barcodeMapper.mapDTOToEntity(findById(id));
		entitiesHistoryService.createEntityHistoryRecord(barcode, null, HistoryAction.DELETE);
		barcodeRepository.deleteById(id);
	}

	@Override
	public void deleteBarcodeWithNullProductId() {
		barcodeRepository.deleteAll(barcodeRepository.findAllByProductIdNull());
	}

	@Override
	public boolean checkIfValueExists(String value) {
		return barcodeRepository.findByValue(value) != null;
	}

	/**
	 * Method used to generated new barcode value based on the last value in the database.
	 *
	 * @param barcodeDTO sent from ui.
	 * @param generatedBarcodeValue value on which will be based the generated value.
	 * @return {@link BarcodeDTO} with generated value.
	 */
	private BarcodeDTO getGeneratedBarcode(BarcodeDTO barcodeDTO, String generatedBarcodeValue) {
		var lastBarcode = barcodeRepository.findFirst1ByValueStartingWithOrderByValueDesc(barcodeDTO.getValue());
		if (lastBarcode == null) {
			return new BarcodeDTO(generatedBarcodeValue);
		} else {
			long generatedValue = Long.parseLong(lastBarcode.getValue()) + 1;
			return new BarcodeDTO(Long.toString(generatedValue));
		}
	}
}
