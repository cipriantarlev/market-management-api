/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.invoice;

import ii.cipriantarlev.marketmanagementapi.documenttype.DocumentType;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTOFoundWhenSaveException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTOListNotFoundException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTONotFoundException;

import java.util.List;
import java.util.Map;

/**
 * {@link Invoice} service used to manage it.
 */
public interface InvoiceService {

	/**
	 * Method used to find all {@link Invoice} in the database.
	 *
	 * @return the list of found invoices.
	 * If the list is empty the {@link DTOListNotFoundException}
	 * will be thrown.
	 */
	List<InvoiceDTO> findAll();

	/**
	 * Method used to find all {@link Invoice} in the database.
	 * based on their {@link DocumentType}.
	 *
	 * @param documentType invoice {@link DocumentType}
	 * @return the list of found invoices based on theri {@link DocumentType}.
	 * If the list is empty the {@link DTOListNotFoundException}
	 * will be thrown.
	 */
	List<InvoiceDTO> findInvoicesByDocumentType(DocumentType documentType);

	/**
	 * Method used to find {@link Invoice} by provided id.
	 *
	 * @param id the id of invoice to be found.
	 * @return the found {@link Invoice}.
	 * If the document type is not found the {@link DTONotFoundException}
	 * will be thrown.
	 */
	InvoiceDTO findById(Long id);

	/**
	 * Method to save the provided {@link InvoiceDTO} as {@link Invoice}
	 * in the database.
	 *
	 * @param invoiceDTO invoice that should be saved in the database.
	 * @return the new created {@link InvoiceDTO}. If the invoice is not found the
	 * {@link DTOFoundWhenSaveException} will be thrown.
	 */
	InvoiceDTO save(InvoiceDTO invoiceDTO);

	/**
	 * Method to update the provided {@link InvoiceDTO} as {@link Invoice}
	 * in the database.
	 *
	 * @param invoiceDTO invoice that should be updated in the database.
	 * @return the updated {@link InvoiceDTO}. If the invoice is not found the
	 * {@link DTONotFoundException} will be thrown.
	 */
	InvoiceDTO update(InvoiceDTO invoiceDTO);

	/**
	 * Method to delete an {@link InvoiceDTO} based on provided id.
	 * If the document type is not found the {@link DTONotFoundException}
	 * will be thrown.
	 *
	 * @param id the id of the {@link InvoiceDTO} to be deteled.
	 */
	void deleteById(Long id);

	/**
	 * Method to update {@link Invoice#isApproved()} marker.
	 *
	 * @param invoicesToUpdate {@link Map} that consists of keys that hold that value for
	 * {@link Invoice#isApproved()} marker and values that hold the id of {@link Invoice}
	 * that should be updated.
	 * @return the number of updated rows.
	 */
	int updateIsApprovedMarker(Map<Boolean, List<Long>> invoicesToUpdate);
}
