/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.barcode;

import java.util.List;

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

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/barcodes")
public class BarcodeController {

	@Autowired
	private BarcodeService barcodeService;

	@GetMapping
	public ResponseEntity<List<BarcodeDTO>> getAllBarcodes() {
		List<BarcodeDTO> barcodes = barcodeService.findAll();

		if (barcodes == null || barcodes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(barcodes, HttpStatus.OK);
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

	@GetMapping("/{id}")
	public ResponseEntity<BarcodeDTO> getBarcode(@PathVariable Long id) {
		var barcode = barcodeService.findById(id);

		if (barcode == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(barcode, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<BarcodeDTO> saveOrGenerateBarcode(@RequestBody BarcodeDTO barcodeDTO) {
		var barcode = barcodeService.generateNewBarcode(barcodeDTO);
		return new ResponseEntity<>(barcode, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBarcode(@PathVariable Long id) {
		var barcode = barcodeService.findById(id);

		if (barcode == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		barcodeService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
