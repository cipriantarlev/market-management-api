package ii.cipriantarlev.marketmanagementapi.barcode;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BarcodeServiceImpl implements BarcodeService {

	@Autowired
	private BarcodeRepository barcodeRepository;

	@Autowired
	private BarcodeMapper barcodeMapper;

	@Override
	public List<BarcodeDTO> findAll() {
		return barcodeRepository.findAll().stream()
				.map(barcode -> barcodeMapper.mapEntityToDTO(barcode))
				.collect(Collectors.toList());
	}

	@Override
	public BarcodeDTO findById(Long id) {
		Optional<Barcode> barcode = barcodeRepository.findById(id);

		if (barcode.isPresent()) {
			return barcodeMapper.mapEntityToDTO(barcode.get());
		}

		return null;
	}

	@Override
	public BarcodeDTO generateNewBarcode(BarcodeDTO barcodeDTO) {
		if(barcodeDTO.getValue().equalsIgnoreCase("21")) {
			return getGeneratedBarcode(barcodeDTO, "2100000");
		}

		if (barcodeDTO.getValue().equalsIgnoreCase("22")) {
			return getGeneratedBarcode(barcodeDTO, "2200000000000");
		}

		return barcodeDTO;
	}

	@Override
	public void deleteById(Long id) {
		barcodeRepository.deleteById(id);
	}

	private BarcodeDTO getGeneratedBarcode(BarcodeDTO barcodeDTO, String generatedBarcodeValue) {
		var lastBarcode = barcodeRepository.findFirst1ByValueStartingWithOrderByValueDesc(barcodeDTO.getValue());
		if (lastBarcode == null) {
			return new BarcodeDTO(generatedBarcodeValue);
		} else {
			Long generatedValue = Long.parseLong(lastBarcode.getValue()) + 1;
			return new BarcodeDTO(generatedValue.toString());
		}
	}

	@Override
	public void deleteBarcodeWithNullProductId() {
		barcodeRepository.deleteAll(barcodeRepository.findAllByProductIdNull());
	}

}
