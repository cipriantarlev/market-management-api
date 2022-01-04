/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.barcode;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Repository interface for {@link Barcode} entity.
 */
public interface BarcodeRepository extends JpaRepository<Barcode, Long> {

	/**
	 * Method used to find last barcode for provided value.
	 *
	 * @param value provided to find last barcode.
	 * @return the last barcode with provided value.
	 */
	Barcode findFirst1ByValueStartingWithOrderByValueDesc(String value);

	/**
	 * Method to find all barcodes without relation to any product.
	 *
	 * @return list of found barcodes.
	 */
	@Query(value = "SELECT * FROM BARCODES WHERE PRODUCT_ID IS NULL", nativeQuery = true)
	List<Barcode> findAllByProductIdNull();

	/**
	 * Method to find {@link Barcode} using its value.
	 *
	 * @param value of barcode to be found.
	 * @return the found {@link Barcode}.
	 */
	Barcode findByValue(String value);
}
