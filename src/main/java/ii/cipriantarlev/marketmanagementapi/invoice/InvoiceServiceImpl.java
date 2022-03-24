/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.invoice;

import java.util.List;
import java.util.stream.Collectors;

import ii.cipriantarlev.marketmanagementapi.history.EntitiesHistoryService;
import ii.cipriantarlev.marketmanagementapi.history.HistoryAction;
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

	@Autowired
	private EntitiesHistoryService entitiesHistoryService;

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
		entitiesHistoryService.createEntityHistoryRecord(savedInvoice, null, HistoryAction.CREATE);
		return invoiceMapper.mapEntityToDTO(savedInvoice);
	}

	@Override
	public InvoiceDTO update(InvoiceDTO invoiceDTO) {
		var foundInvoice = invoiceMapper.mapDTOToEntity(this.findById(invoiceDTO.getId()));
		var savedInvoice = invoiceRepository.save(invoiceMapper.mapDTOToEntity(invoiceDTO));
		entitiesHistoryService.createEntityHistoryRecord(invoiceMapper.mapDTOToEntity(invoiceDTO), foundInvoice, HistoryAction.UPDATE);
		return invoiceMapper.mapEntityToDTO(savedInvoice);
	}

	@Override
	public void deleteById(Long id) {
		entitiesHistoryService.createEntityHistoryRecord(invoiceMapper.mapDTOToEntity(this.findById(id)), null, HistoryAction.DELETE);
		invoiceRepository.deleteById(id);
	}

	@Override
	public int updateIsApprovedMarker(boolean isApproved, Long id) {
		var foundInvoice = invoiceMapper.mapDTOToEntity(this.findById(id));
		foundInvoice.setApproved(isApproved);
		entitiesHistoryService.createEntityHistoryRecord(
				foundInvoice, invoiceMapper.mapDTOToEntity(this.findById(id)), HistoryAction.UPDATE);
		return invoiceRepository.updateIsApprovedMarker(isApproved, id);
	}

	@Override
	public List<InvoiceDTO> findInvoicesByDocumentType(DocumentType documentType) {
		List<InvoiceDTO> invoices = invoiceRepository
				.findAllByDocumentType(documentType).stream()
				.map(invoice -> invoiceMapper.mapEntityToDTO(invoice)).collect(Collectors.toList());

		if (invoices == null || invoices.isEmpty()) {
			throw new DTOListNotFoundException("Invoice list not found");
		}

		return invoices;
	}
}