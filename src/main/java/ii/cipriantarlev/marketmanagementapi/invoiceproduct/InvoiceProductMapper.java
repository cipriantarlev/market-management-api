/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.invoiceproduct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Class used to map {@link InvoiceProduct} to {@link InvoiceProductDTO} and vice-versa.
 */
@Component
public class InvoiceProductMapper {

	/**
	 * {@link ModelMapper} is used to map entity to dto.
	 */
	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Method used to map {@link InvoiceProduct} to {@link InvoiceProductDTO}.
	 *
	 * @param invoiceProduct entity to be mapped.
	 * @return resulted dto.
	 */
	public InvoiceProductDTO mapEntityToDTO(InvoiceProduct invoiceProduct) {
		return modelMapper.map(invoiceProduct, InvoiceProductDTO.class);
	}

	/**
	 * Method used to map {@link InvoiceProductDTO} to {@link InvoiceProduct}.
	 *
	 * @param invoiceProductDTO DTO to be mapped.
	 * @return resulted entity.
	 */
	public InvoiceProduct mapDTOToEntity(InvoiceProductDTO invoiceProductDTO) {
		return modelMapper.map(invoiceProductDTO, InvoiceProduct.class);
	}
}
