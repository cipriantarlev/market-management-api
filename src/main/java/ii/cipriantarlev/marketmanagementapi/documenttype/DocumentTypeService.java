/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.documenttype;

import java.util.List;

public interface DocumentTypeService {

	List<DocumentTypeDTO> findAll();

	DocumentTypeDTO findById(Long id);

	DocumentTypeDTO save(DocumentTypeDTO documentTypeDTO);

	DocumentTypeDTO update(DocumentTypeDTO documentTypeDTO);

	void deleteById(Long id);
}
