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

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

	List<Invoice> findAllByDocumentType(DocumentType documentType);

	@Transactional
	@Modifying
	@Query("update Invoice u set u.isApproved = ?1 where u.id = ?2")
	int updateIsApprovedMarker(boolean isApproved, Long invoiceId);
}
