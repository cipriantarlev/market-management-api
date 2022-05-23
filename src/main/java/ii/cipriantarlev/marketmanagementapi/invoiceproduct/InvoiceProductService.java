/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.invoiceproduct;

import java.util.List;
import java.util.Map;

public interface InvoiceProductService {

	List<InvoiceProductDTO> findAllByInvoiceId(Long invoiceId);

	InvoiceProductDTO findById(Long id);

	InvoiceProductDTO save(InvoiceProductDTO invoiceProductDTO);

	InvoiceProductDTO update(InvoiceProductDTO invoiceProductDTO);

	int updateIsProductChecked(Map<Boolean, List<Long>> productsToUpdate);

	void deleteById(Long id);
}
