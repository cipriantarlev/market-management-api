/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.vat;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import ii.cipriantarlev.marketmanagementapi.util.RestControllerUtil;

import static ii.cipriantarlev.marketmanagementapi.util.Constants.*;

@CrossOrigin(LOCAL_HOST)
@RestController
@RequestMapping(VAT_ROOT_PATH)
public class VatController {

	@Autowired
	private VatService vatService;

	@Autowired
	private RestControllerUtil restControllerUtil;

	@GetMapping
	public ResponseEntity<List<VatDTO>> getVatList() {
		List<VatDTO> vatList = vatService.findAll();
		return new ResponseEntity<>(vatList, HttpStatus.OK);
	}

	@GetMapping(ID_PATH)
	public ResponseEntity<VatDTO> getVatById(@PathVariable Integer id) {
		VatDTO vat = vatService.findById(id);
		return new ResponseEntity<>(vat, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<VatDTO> createVat(@Valid @RequestBody VatDTO vatDTO) {
		var vat = vatService.save(vatDTO);
		var headers = restControllerUtil.setHttpsHeaderLocation(VAT_ROOT_PATH.concat(ID_PATH), vat.getId().longValue());
		return new ResponseEntity<>(vat, headers, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<VatDTO> updateDTO(@Valid @RequestBody VatDTO vatDTO) {
		var savedVat = vatService.save(vatDTO);
		return new ResponseEntity<>(savedVat, HttpStatus.OK);
	}

	@DeleteMapping(ID_PATH)
	public ResponseEntity<Void> deleteVat(@PathVariable Integer id) {
		vatService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
