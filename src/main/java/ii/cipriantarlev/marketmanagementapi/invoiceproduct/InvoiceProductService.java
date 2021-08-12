package ii.cipriantarlev.marketmanagementapi.invoiceproduct;

import java.util.List;

public interface InvoiceProductService {

	List<InvoiceProductDTO> findAllByInvoiceId(Long invoiceId);

	InvoiceProductDTO findById(Long id);

	InvoiceProductDTO save(InvoiceProductDTO invoiceProductDTO);

	void deleteById(Long id);
}
