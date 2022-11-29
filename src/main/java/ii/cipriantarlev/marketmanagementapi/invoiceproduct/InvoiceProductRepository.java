/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.invoiceproduct;

import java.util.List;

import ii.cipriantarlev.marketmanagementapi.invoice.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link InvoiceProduct} entity.
 */
public interface InvoiceProductRepository extends JpaRepository<InvoiceProduct, Long> {

	/**
	 * Method used to find all {@link InvoiceProduct} by {@link Invoice} id.
	 * This method is implemented by Spring container.
	 *
	 * @param invoiceId {@link Invoice} id related to {@link InvoiceProduct}
	 * @return the list of all {@link InvoiceProduct} related to provided {@link Invoice} id.
	 */
	List<InvoiceProduct> findAllByInvoiceId(Long invoiceId);
}
