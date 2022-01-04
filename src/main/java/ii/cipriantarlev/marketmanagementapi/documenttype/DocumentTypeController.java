/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.documenttype;

import static ii.cipriantarlev.marketmanagementapi.utils.Constants.*;

import java.util.List;

import javax.validation.Valid;

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

@CrossOrigin(LOCAL_HOST)
@RestController
@RequestMapping(DOCUMENT_TYPE_ROOT_PATH)
public class DocumentTypeController {

	@Autowired
	private DocumentTypeService documentTypeService;

	@Autowired
	private RestControllerUtil restControllerUtil;

	@GetMapping
	public ResponseEntity<List<DocumentTypeDTO>> getDocumentTypeList() {
		var documentTypeList = documentTypeService.findAll();
		return new ResponseEntity<>(documentTypeList, HttpStatus.OK);
	}

	@GetMapping(ID_PATH)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<DocumentTypeDTO> getDocumentType(@PathVariable Long id) {
		var documentType = documentTypeService.findById(id);
		return new ResponseEntity<>(documentType, HttpStatus.OK);
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<DocumentTypeDTO> createDocumentType(@Valid @RequestBody DocumentTypeDTO documentTypeDTO) {
		var documentType = documentTypeService.save(documentTypeDTO);
		var headers = restControllerUtil.setHttpsHeaderLocation(DOCUMENT_TYPE_ROOT_PATH,
				documentType.getId());
		return new ResponseEntity<>(documentType, headers, HttpStatus.OK);
	}

	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<DocumentTypeDTO> updateDocumentType(@Valid @RequestBody DocumentTypeDTO documentTypeDTO) {
		var savedDocumentType = documentTypeService.update(documentTypeDTO);
		return new ResponseEntity<>(savedDocumentType, HttpStatus.OK);
	}

	@DeleteMapping(ID_PATH)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deleteDocumentType(@PathVariable Long id) {
		documentTypeService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
