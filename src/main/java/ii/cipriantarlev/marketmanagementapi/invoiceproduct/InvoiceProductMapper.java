/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.invoiceproduct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InvoiceProductMapper {

	@Autowired
	private ModelMapper modelMapper;

	public InvoiceProductDTO mapEntityToDTO(InvoiceProduct invoiceProduct) {
		return modelMapper.map(invoiceProduct, InvoiceProductDTO.class);
	}

	public InvoiceProduct mapDTOToEntity(InvoiceProductDTO invoiceProductDTO) {
		return modelMapper.map(invoiceProductDTO, InvoiceProduct.class);
	}
}
