/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.invoice;

import java.util.List;

public interface InvoiceService {

	List<InvoiceDTO> findAll();

	InvoiceDTO findById(Long id);

	InvoiceDTO save(InvoiceDTO invoiceDTO);

	void deleteById(Long id);
}
