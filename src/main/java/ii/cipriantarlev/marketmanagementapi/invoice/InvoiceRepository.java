/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.invoice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ii.cipriantarlev.marketmanagementapi.documenttype.DocumentType;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

	List<Invoice> findAllByDocumentType(DocumentType documentType);
}
