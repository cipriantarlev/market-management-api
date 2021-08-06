package ii.cipriantarlev.marketmanagementapi.invoice;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InvoiceMapper {

	@Autowired
	private ModelMapper modelMapper;

	public InvoiceDTO mapEntityToDTO(Invoice invoice) {
		return modelMapper.map(invoice, InvoiceDTO.class);
	}

	public Invoice mapDTOToEntity(InvoiceDTO invoiceDTO) {
		return modelMapper.map(invoiceDTO, Invoice.class);
	}
}
