package ii.cipriantarlev.marketmanagementapi.barcode;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BarcodeMapper {

	@Autowired
	private ModelMapper modelMapper;

	public BarcodeDTO mapEntityToDTO(Barcode barcode) {
		return modelMapper.map(barcode, BarcodeDTO.class);
	}

	public Barcode mapDTOToEntity(BarcodeDTO barcodeDTO) {
		return modelMapper.map(barcodeDTO, Barcode.class);
	}
}
