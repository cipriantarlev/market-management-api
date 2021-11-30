/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.barcode;

import static ii.cipriantarlev.marketmanagementapi.utils.Constants.*;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(LOCAL_HOST)
@RestController
@RequestMapping(BARCODES_ROOT_PATH)
public class BarcodeController {

	@Autowired
	private BarcodeService barcodeService;

	@GetMapping
	public ResponseEntity<List<BarcodeDTO>> getAllBarcodes() {
		var findAll = barcodeService.findAll();
		return new ResponseEntity<>(findAll, HttpStatus.OK);
	}

//	@GetMapping("/product/{id}")
//	public ResponseEntity<List<BarcodeDTO>> getAllBarcodesByProductId(@PathVariable Long id) {
//		List<BarcodeDTO> barcodes = barcodeService.findAllByProductId(id);
//
//		if (barcodes == null || barcodes.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
//
//		return new ResponseEntity<>(barcodes, HttpStatus.OK);
//	}

	@GetMapping(ID_PATH)
	public ResponseEntity<BarcodeDTO> getBarcode(@PathVariable Long id) {
		var findById = barcodeService.findById(id);
		return new ResponseEntity<>(findById, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<BarcodeDTO> saveOrGenerateBarcode(@Valid @RequestBody BarcodeDTO barcodeDTO) {
		var barcode = barcodeService.generateNewBarcode(barcodeDTO);
		return new ResponseEntity<>(barcode, HttpStatus.OK);
	}

	@DeleteMapping(ID_PATH)
	public ResponseEntity<Void> deleteBarcode(@PathVariable Long id) {
		barcodeService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
