package ii.cipriantarlev.marketmanagementapi.documenttype;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/document-types")
public class DocumentTypeController {

	@Autowired
	private DocumentTypeService documentTypeService;

	@GetMapping
	public ResponseEntity<List<DocumentTypeDTO>> getDocumentTypeList() {
		var documentTypeList = documentTypeService.findAll();

		if (documentTypeList == null || documentTypeList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(documentTypeList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DocumentTypeDTO> getDocumentType(@PathVariable Integer id) {
		var documentType = documentTypeService.findById(id);

		if (documentType == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(documentType, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<DocumentTypeDTO> createDocumentType(@RequestBody DocumentTypeDTO documentTypeDTO) {
		if (documentTypeDTO.getId() != null && documentTypeService.findById(documentTypeDTO.getId()) != null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		var documentType = documentTypeService.save(documentTypeDTO);
		var headers = new HttpHeaders();
		headers.setLocation(
				UriComponentsBuilder.fromPath("/document-types/{id}").buildAndExpand(documentType.getId()).toUri());
		return new ResponseEntity<>(documentType, headers, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<DocumentTypeDTO> updateDocumentType(@RequestBody DocumentTypeDTO documentTypeDTO) {
		var productDto = documentTypeService.findById(documentTypeDTO.getId());

		if (productDto == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		var savedDocumentType = documentTypeService.save(documentTypeDTO);
		return new ResponseEntity<>(savedDocumentType, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDocumentType(@PathVariable Integer id) {
		var documentType = documentTypeService.findById(id);

		if (documentType == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		documentTypeService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
