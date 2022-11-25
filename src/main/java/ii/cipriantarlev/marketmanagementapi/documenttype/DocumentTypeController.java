/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.documenttype;

import static ii.cipriantarlev.marketmanagementapi.utils.Constants.*;

import java.util.List;

import javax.validation.Valid;

import ii.cipriantarlev.marketmanagementapi.exceptions.DTOFoundWhenSaveException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTONotFoundException;
import ii.cipriantarlev.marketmanagementapi.role.Role;
import ii.cipriantarlev.marketmanagementapi.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ii.cipriantarlev.marketmanagementapi.utils.RestControllerUtil;

/**
 * RestController class for {@link DocumentType}
 */
@CrossOrigin(LOCAL_HOST)
@RestController
@RequestMapping(DOCUMENT_TYPE_ROOT_PATH)
public class DocumentTypeController {

	/**
	 * {@link DocumentTypeService} used to manage {@link DocumentType}
	 */
	@Autowired
	private DocumentTypeService documentTypeService;

	/**
	 * Utility class used to create headers for Post method
	 */
	@Autowired
	private RestControllerUtil restControllerUtil;

	/**
	 * Method to expose all document types found in the database.
	 *
	 * @return list of found document types and 200 status code.
	 * If the list is empty the 404 status code will be sent.
	 */
	@GetMapping
	public ResponseEntity<List<DocumentTypeDTO>> getDocumentTypeList() {
		var documentTypeList = documentTypeService.findAll();
		return new ResponseEntity<>(documentTypeList, HttpStatus.OK);
	}

	/**
	 * Method to expose the {@link DocumentType} for provided id.
	 * Only {@link User}
	 * with ADMIN {@link Role}
	 * has access to this method.
	 *
	 * @param id of document type to be found.
	 * @return the found document type and 200 status code.
	 * If document type was not found the 404 status code will be sent.
	 * If user doesn't have ADMIN {@link Role}
	 * he will receive Http code 401 Unauthorized request.
	 */
	@GetMapping(ID_PATH)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<DocumentTypeDTO> getDocumentType(@PathVariable Long id) {
		var documentType = documentTypeService.findById(id);
		return new ResponseEntity<>(documentType, HttpStatus.OK);
	}

	/**
	 * Method to save the provided {@link DocumentTypeDTO} in database.
	 * Only {@link User} with ADMIN {@link Role} has access to this method.
	 *
	 * @param documentTypeDTO to be saved in database.
	 * @return the saved {@link DocumentType} and 200 status code.
	 * If in database already exists a document type with the same id, the {@link DTOFoundWhenSaveException}
	 * will be thrown and http code 400 bad request will be sent.
	 * If user doesn't have ADMIN {@link Role}
	 * he will receive Http code 401 Unauthorized request.
	 */
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<DocumentTypeDTO> createDocumentType(@Valid @RequestBody DocumentTypeDTO documentTypeDTO) {
		var documentType = documentTypeService.save(documentTypeDTO);
		var headers = restControllerUtil.setHttpsHeaderLocation(DOCUMENT_TYPE_ROOT_PATH,
				documentType.getId());
		return new ResponseEntity<>(documentType, headers, HttpStatus.OK);
	}

	/**
	 * Method to update the provided {@link DocumentTypeDTO} in database.
	 * Only {@link User} with ADMIN {@link Role} has access to this method.
	 *
	 * @param documentTypeDTO to be updated.
	 * @return the updated {@link DocumentType} and 200 status code.
	 * If the provided document type was not found in the database, the {@link DTONotFoundException}
	 * will be thrown and http code 404 not found will be sent.
	 * If user doesn't have ADMIN {@link Role}
	 * he will receive Http code 401 Unauthorized request.
	 */
	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<DocumentTypeDTO> updateDocumentType(@Valid @RequestBody DocumentTypeDTO documentTypeDTO) {
		var savedDocumentType = documentTypeService.update(documentTypeDTO);
		return new ResponseEntity<>(savedDocumentType, HttpStatus.OK);
	}

	/**
	 * Method to delete the {@link DocumentType} with provided id.
	 *
	 * @param id of document type to be deleted.
	 * @return 200 status code if the {@link DocumentType} was successfully deleted.
	 * If the provided category was not found in the database, the {@link DTONotFoundException}
	 * will be thrown and http code 404 not found will be sent.
	 * If user doesn't have ADMIN {@link Role}
	 * he will receive Http code 401 Unauthorized request.
	 */
	@DeleteMapping(ID_PATH)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deleteDocumentType(@PathVariable Long id) {
		documentTypeService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
