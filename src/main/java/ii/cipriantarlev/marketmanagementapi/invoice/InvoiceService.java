/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.invoice;

import ii.cipriantarlev.marketmanagementapi.documenttype.DocumentType;

import java.util.List;

public interface InvoiceService {

	List<InvoiceDTO> findAll();

	List<InvoiceDTO> findInvoicesByDocumentType(DocumentType documentType);

	InvoiceDTO findById(Long id);

	InvoiceDTO save(InvoiceDTO invoiceDTO);

	InvoiceDTO update(InvoiceDTO invoiceDTO);

	void deleteById(Long id);

	int updateIsApprovedMarker(boolean isApproved, Long id);
}
