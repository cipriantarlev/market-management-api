/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.barcode;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
  Class used to map {@link Barcode} to {@link BarcodeDTO}.
 */
@Component
public class BarcodeMapper {

	/**
	 * {@link ModelMapper} is used to map entity to dto.
	 */
	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Method used to map {@link Barcode} to {@link BarcodeDTO}.
	 *
	 * @param barcode entity to be mapped.
	 * @return resulted dto.
	 */
	public BarcodeDTO mapEntityToDTO(Barcode barcode) {
		return modelMapper.map(barcode, BarcodeDTO.class);
	}

	/**
	 * Method used to map {@link BarcodeDTO} to {@link Barcode}.
	 *
	 * @param barcodeDTO DTO to be mapped.
	 * @return resulted entity.
	 */
	public Barcode mapDTOToEntity(BarcodeDTO barcodeDTO) {
		return modelMapper.map(barcodeDTO, Barcode.class);
	}
}
