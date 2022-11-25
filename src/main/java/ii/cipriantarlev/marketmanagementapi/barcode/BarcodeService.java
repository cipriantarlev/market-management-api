/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.barcode;

import ii.cipriantarlev.marketmanagementapi.exceptions.DTOListNotFoundException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTONotFoundException;
import ii.cipriantarlev.marketmanagementapi.product.Product;

import java.util.List;

/**
 * {@link Barcode} service used to manage it.
 */
public interface BarcodeService {

	/**
	 * Method to find all {@link Barcode} in database.
	 *
	 * @return the list of found barcodes.
	 * If the list is empty the {@link DTOListNotFoundException}
	 * will be thrown.
	 */
	List<BarcodeDTO> findAll();

	/**
	 * Method to find {@link Barcode} by provided id.
	 *
	 * @param id of barcode to found.
	 * @return the found {@link Barcode}.
	 * If the barcode is not found the {@link DTONotFoundException}
	 * will be thrown.
	 */
	BarcodeDTO findById(Long id);

	/**
	 * Generate new barcode based on value of the last found.
	 * If the value is 21 or 22 the new barcode will be generated
	 * based on last barcode value found in database, otherwise the current
	 * value will be linked to {@link Product}
	 * and persisted to database.
	 *
	 * @param barcodeDTO sent from ui.
	 * @return the generated or sent barcodeDTO.
	 */
	BarcodeDTO generateNewBarcode(BarcodeDTO barcodeDTO);

	/**
	 * Method to delete a barcode based on provided id.
	 * If the barcode is not found the {@link DTONotFoundException}
	 * will be thrown.
	 *
	 * @param id of barcode to be deleted.
	 */
	void deleteById(Long id);

	/**
	 * Method to delete all barcode that are not linked to any product.
	 */
	void deleteBarcodeWithNullProductId();

	/**
	 * Method to check if the provided value of {@link Barcode}
	 * exists in database.
	 *
	 * @param value to be checked in database.
	 * @return true if value exist and false if not.
	 */
	boolean checkIfValueExists(String value);
}
