/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.documenttype;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link DocumentType} entity.
 */
public interface DocumentTypeRepository extends JpaRepository<DocumentType, Long> {

}
