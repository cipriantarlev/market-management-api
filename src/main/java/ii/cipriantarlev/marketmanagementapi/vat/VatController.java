/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.vat;

import java.util.List;

import javax.validation.Valid;

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
@RequestMapping("/vat")
public class VatController {

	@Autowired
	private VatService vatService;

	@GetMapping
	public ResponseEntity<List<VatDTO>> getVatList() {
		List<VatDTO> vatList = vatService.findAll();

		if (vatList == null || vatList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(vatList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<VatDTO> getVatById(@PathVariable Integer id) {
		VatDTO vat = vatService.findById(id);

		if (vat == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(vat, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<VatDTO> createVat(@Valid @RequestBody VatDTO vatDTO) {
		if (vatDTO.getId() != null && vatService.findById(vatDTO.getId()) != null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		var vat = vatService.save(vatDTO);
		var headers = new HttpHeaders();
		headers.setLocation(UriComponentsBuilder.fromPath("/vat/{id}").buildAndExpand(vat.getId()).toUri());
		return new ResponseEntity<>(vat, headers, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<VatDTO> updateDTO(@Valid @RequestBody VatDTO vatDTO) {
		VatDTO vat = vatService.findById(vatDTO.getId());

		if (vat == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		var savedVat = vatService.save(vatDTO);
		return new ResponseEntity<>(savedVat, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteVat(@PathVariable Integer id) {
		VatDTO vat = vatService.findById(id);

		if (vat == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		vatService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
