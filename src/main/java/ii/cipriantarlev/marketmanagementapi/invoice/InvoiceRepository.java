/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.invoice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ii.cipriantarlev.marketmanagementapi.documenttype.DocumentType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * Repository interface for {@link Invoice} entity.
 */
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

	/**
	 * Method used to find all {@link Invoice} by {@link DocumentType}.
	 * This method is implemented by Spring container.
	 *
	 * @param documentType document type used to find the invoices.
	 * @return the list of all invoices that has the provided document type.
	 */
	List<Invoice> findAllByDocumentType(DocumentType documentType);

	/**
	 * Method to update {@link Invoice#isApproved()} marker.
	 * This method is implemented by Spring container.
	 *
	 * @param isApproved value used to update {@link Invoice#isApproved()} marker.
	 * @param invoiceId id used to identify the {@link Invoice}.
	 * @return the number of updated rows.
	 */
	@Transactional
	@Modifying
	@Query("update Invoice u set u.isApproved = ?1 where u.id = ?2")
	int updateIsApprovedMarker(boolean isApproved, Long invoiceId);
}