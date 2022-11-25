/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.documenttype;

import ii.cipriantarlev.marketmanagementapi.exceptions.DTOFoundWhenSaveException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTOListNotFoundException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTONotFoundException;

import java.util.List;

/**
 * {@link DocumentType} service used to manage it.
 */
public interface DocumentTypeService {

	/**
	 * Method to find all {@link DocumentTypeDTO} in database.
	 *
	 * @return the list of found document types.
	 * If the list is empty the {@link DTOListNotFoundException}
	 * will be thrown.
	 */
	List<DocumentTypeDTO> findAll();

	/**
	 * Method to find {@link DocumentTypeDTO} by provided id.
	 *
	 * @param id of document type to found.
	 * @return the found {@link DocumentTypeDTO}.
	 * If the document type is not found the {@link DTONotFoundException}
	 * will be thrown.
	 */
	DocumentTypeDTO findById(Long id);

	/**
	 * Method to save the provided {@link DocumentTypeDTO} as {@link DocumentType}
	 * in the database.
	 *
	 * @param documentTypeDTO that should be saved in the database.
	 * @return the new created document type. If the document type is not found the
	 * {@link DTOFoundWhenSaveException} will be thrown.
	 */
	DocumentTypeDTO save(DocumentTypeDTO documentTypeDTO);

	/**
	 * Method to update the provided {@link DocumentTypeDTO} as {@link DocumentType}
	 * in the database.
	 *
	 * @param documentTypeDTO that should be updated.
	 * @return the updated document type. If the document type is not found the
	 * {@link DTONotFoundException} will be thrown.
	 */
	DocumentTypeDTO update(DocumentTypeDTO documentTypeDTO);

	/**
	 * Method to delete a {@link DocumentType} based on provided id.
	 * If the document type is not found the {@link DTONotFoundException}
	 * will be thrown.
	 *
	 * @param id of the {@link DocumentType} to be deleted.
	 */
	void deleteById(Long id);
}
