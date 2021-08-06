package ii.cipriantarlev.marketmanagementapi.invoice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private InvoiceMapper invoiceMapper;

	@Override
	public List<InvoiceDTO> findAll() {
		return invoiceRepository.findAll().stream()
				.map(invoice -> invoiceMapper.mapEntityToDTO(invoice))
				.collect(Collectors.toList());
	}

	@Override
	public InvoiceDTO findById(Long id) {
		var invoice = invoiceRepository.findById(id);

		if (invoice.isPresent()) {
			return invoiceMapper.mapEntityToDTO(invoice.get());
		}

		return null;
	}

	@Override
	public InvoiceDTO save(InvoiceDTO invoiceDTO) {
		var savedInvoice = invoiceRepository.save(invoiceMapper.mapDTOToEntity(invoiceDTO));
		return invoiceMapper.mapEntityToDTO(savedInvoice);
	}

	@Override
	public void deleteById(Long id) {
		invoiceRepository.deleteById(id);
	}
}
