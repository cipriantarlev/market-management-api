package ii.cipriantarlev.marketmanagementapi.invoiceproduct;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceProductRepository extends JpaRepository<InvoiceProduct, Long> {

	List<InvoiceProduct> findAllByInvoiceId(Long invoiceId);
}
