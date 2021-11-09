/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.invoice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ii.cipriantarlev.marketmanagementapi.documenttype.DocumentType;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTOFoundWhenSaveException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTOListNotFoundException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTONotFoundException;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private InvoiceMapper invoiceMapper;

	@Override
	public List<InvoiceDTO> findAll() {
		List<InvoiceDTO> invoices = invoiceRepository.findAll().stream()
				.map(invoice -> invoiceMapper.mapEntityToDTO(invoice))
				.collect(Collectors.toList());
		
		if (invoices == null || invoices.isEmpty()) {
			throw new DTOListNotFoundException("Invoice list not found");
		}

		return invoices;
	}

	@Override
	public InvoiceDTO findById(Long id) {
		var invoice = invoiceRepository.findById(id);

		if (invoice.isPresent()) {
			return invoiceMapper.mapEntityToDTO(invoice.get());
		}

		throw new DTONotFoundException(String.format("Invoice with %d not found", id), id);
	}

	@Override
	public InvoiceDTO save(InvoiceDTO invoiceDTO) {
		if (invoiceDTO.getId() != null && invoiceRepository.findById(invoiceDTO.getId()).isPresent()) {
			throw new DTOFoundWhenSaveException(
					String.format(
							"Invoice with id: '%d' already exists in database. "
									+ "Please use update in order to save the changes in database",
									invoiceDTO.getId()),
					invoiceDTO.getId());
		}
		
		var savedInvoice = invoiceRepository.save(invoiceMapper.mapDTOToEntity(invoiceDTO));
		return invoiceMapper.mapEntityToDTO(savedInvoice);
	}

	@Override
	public InvoiceDTO update(InvoiceDTO invoiceDTO) {
		this.findById(invoiceDTO.getId());
		var savedInvoice = invoiceRepository.save(invoiceMapper.mapDTOToEntity(invoiceDTO));
		return invoiceMapper.mapEntityToDTO(savedInvoice);
	}

	@Override
	public void deleteById(Long id) {
		this.findById(id);
		invoiceRepository.deleteById(id);
	}

	@Override
	public List<InvoiceDTO> findAllIncomeInvoices() {
		List<InvoiceDTO> invoices = invoiceRepository
				.findAllByDocumentType(DocumentType.builder().id(1).build()).stream()
				.map(invoice -> invoiceMapper.mapEntityToDTO(invoice)).collect(Collectors.toList());

		if (invoices == null || invoices.isEmpty()) {
			throw new DTOListNotFoundException("Invoice list not found");
		}

		return invoices;
	}

	@Override
	public List<InvoiceDTO> findAllOutcomeInvoices() {
		List<InvoiceDTO> invoices = invoiceRepository
				.findAllByDocumentType(DocumentType.builder().id(2).build()).stream()
				.map(invoice -> invoiceMapper.mapEntityToDTO(invoice)).collect(Collectors.toList());

		if (invoices == null || invoices.isEmpty()) {
			throw new DTOListNotFoundException("Invoice list not found");
		}

		return invoices;
	}
}
