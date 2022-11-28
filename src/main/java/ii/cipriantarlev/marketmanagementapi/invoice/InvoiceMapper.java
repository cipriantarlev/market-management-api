/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.invoice;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Class used to map {@link Invoice} to {@link InvoiceDTO} and vice-versa.
 */
@Component
public class InvoiceMapper {

	/**
	 * {@link ModelMapper} is used to map entity to dto.
	 */
	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Method used to map {@link Invoice} to {@link InvoiceDTO}.
	 *
	 * @param invoice entity to be mapped.
	 * @return resulted dto.
	 */
	public InvoiceDTO mapEntityToDTO(Invoice invoice) {
		return modelMapper.map(invoice, InvoiceDTO.class);
	}

	/**
	 * Method used to map {@link InvoiceDTO} to {@link Invoice}.
	 *
	 * @param invoiceDTO DTO to be mapped.
	 * @return resulted entity.
	 */
	public Invoice mapDTOToEntity(InvoiceDTO invoiceDTO) {
		return modelMapper.map(invoiceDTO, Invoice.class);
	}
}
