/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.invoice;

import java.util.List;
import java.util.Map;

import ii.cipriantarlev.marketmanagementapi.history.EntitiesHistoryService;
import ii.cipriantarlev.marketmanagementapi.history.HistoryAction;
import ii.cipriantarlev.marketmanagementapi.invoiceproduct.InvoiceProduct;
import ii.cipriantarlev.marketmanagementapi.invoiceproduct.InvoiceProductRepository;
import ii.cipriantarlev.marketmanagementapi.pricechangingact.PriceChangingActDTO;
import ii.cipriantarlev.marketmanagementapi.pricechangingact.PriceChangingActService;
import ii.cipriantarlev.marketmanagementapi.product.Product;
import ii.cipriantarlev.marketmanagementapi.utils.MarketManagementFactory;
import lombok.Setter;
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

	@Autowired
	private MarketManagementFactory factory;

    @Autowired
    private InvoiceProductRepository invoiceProductRepository;

	@Autowired
	private PriceChangingActService priceChangingActService;

	@Setter
	private int updatedRows;

	@Override
	public List<InvoiceDTO> findAll() {
		List<InvoiceDTO> invoices = invoiceRepository.findAll().stream()
				.map(invoice -> invoiceMapper.mapEntityToDTO(invoice))
				.toList();

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
		deleteRelatedPriceChangingAct(id);
		invoiceRepository.deleteById(id);
	}

	@Override
	public int updateIsApprovedMarker(Map<Boolean, List<Long>> invoicesToUpdate) {
		setUpdatedRows(0);
		invoicesToUpdate.forEach((isApproved, idList) -> idList.forEach(id -> {
			var foundInvoice = invoiceMapper.mapDTOToEntity(this.findById(id));
			var copyOfFoundInvoice = factory.getClonedInvoice(foundInvoice);
			foundInvoice.setApproved(isApproved);
			entitiesHistoryService.createEntityHistoryRecord(
					foundInvoice, copyOfFoundInvoice, HistoryAction.UPDATE);
			invoiceRepository.updateIsApprovedMarker(isApproved, id);
			createPriceChangingActForApprovedInvoice(isApproved, id);
			setUpdatedRows(idList.size());
		}));

		return updatedRows;
	}

	@Override
	public List<InvoiceDTO> findInvoicesByDocumentType(DocumentType documentType) {
		List<InvoiceDTO> invoices = invoiceRepository
				.findAllByDocumentType(documentType).stream()
				.map(invoice -> invoiceMapper.mapEntityToDTO(invoice)).toList();

		if (invoices == null || invoices.isEmpty()) {
			throw new DTOListNotFoundException("Invoice list not found");
		}

		return invoices;
	}

	private void createPriceChangingActForApprovedInvoice(boolean isApproved, long id) {
		if(isApproved) {
            List<InvoiceProduct> invoiceProducts = invoiceProductRepository.findAllByInvoiceId(id);
            if(!invoiceProducts.isEmpty()) {
                List<Product> productsWithRetailPriceChanged = invoiceProducts.stream()
                        .map(InvoiceProduct::getProduct)
                        .filter(Product::isRetailPriceChanged)
                        .toList();

				PriceChangingActDTO priceChangingAct =
						factory.createPriceChangingActWhenProductPriceHasChanged(
								invoiceProducts.get(0).getInvoice(), productsWithRetailPriceChanged);

				factory.createPriceChangingActProductWhenProductPriceHasChanged(priceChangingAct, productsWithRetailPriceChanged);
            }
        }
	}

	private void deleteRelatedPriceChangingAct(long invoiceId) {
		priceChangingActService.findByInvoiceId(invoiceId)
				.forEach(priceChangingAct -> priceChangingActService.deleteById(priceChangingAct.getId()));
	}
}