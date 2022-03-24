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

/**
 * RestController class for {@link Barcode}.
 */
@CrossOrigin(LOCAL_HOST)
@RestController
@RequestMapping(BARCODES_ROOT_PATH)
public class BarcodeController {

	/**
	 * {@link BarcodeService} used to manage {@link Barcode}.
	 */
	@Autowired
	private BarcodeService barcodeService;

	/**
	 * Method to expose all barcodes found in the database.
	 *
	 * @return list of found barcodes and 200 status code.
	 * If the list is empty the 404 status code will be sent.
	 */
	@GetMapping
	public ResponseEntity<List<BarcodeDTO>> getAllBarcodes() {
		var findAll = barcodeService.findAll();
		return new ResponseEntity<>(findAll, HttpStatus.OK);
	}

	/**
	 * Method to expose the {@link Barcode} for provided id.
	 *
	 * @param id of barcode to be found.
	 * @return the found barcode and 200 status code.
	 * If barcode was not found the 404 status code will be sent.
	 */
	@GetMapping(ID_PATH)
	public ResponseEntity<BarcodeDTO> getBarcode(@PathVariable Long id) {
		var findById = barcodeService.findById(id);
		return new ResponseEntity<>(findById, HttpStatus.OK);
	}

	/**
	 * Method to save the provided barcode or to generate a generic one based on provided value.
	 *
	 * @param barcodeDTO to checked and/or saved.
	 * @return the saved or generated barcode and 200 status code.
	 */
	@PostMapping
	public ResponseEntity<BarcodeDTO> saveOrGenerateBarcode(@Valid @RequestBody BarcodeDTO barcodeDTO) {
		var barcode = barcodeService.generateNewBarcode(barcodeDTO);
		return new ResponseEntity<>(barcode, HttpStatus.OK);
	}

	/**
	 * Method to delete barcode with provided id
	 *
	 * @param id for barcode to be deleted.
	 * @return 200 status code if barcode has been deleted.
	 * If barcode was not found the 404 status code will be sent.
	 */
	@DeleteMapping(ID_PATH)
	public ResponseEntity<Void> deleteBarcode(@PathVariable Long id) {
		barcodeService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Method to check if the provided barcode value already exists in database.
	 *
	 * @param value value to be checked.
	 * @return true if value exists, false if not and 200 status code.
	 */
	@GetMapping(BARCODE_BY_VALUE)
	public ResponseEntity<Boolean> checkIfValueExists(@PathVariable String value) {
		return new ResponseEntity<>(barcodeService.checkIfValueExists(value), HttpStatus.OK);
	}
}
