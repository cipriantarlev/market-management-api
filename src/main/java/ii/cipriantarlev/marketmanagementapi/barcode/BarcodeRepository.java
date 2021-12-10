/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.barcode;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BarcodeRepository extends JpaRepository<Barcode, Long> {

	Barcode findFirst1ByValueStartingWithOrderByValueDesc(String value);

	@Query(value = "SELECT * FROM BARCODES WHERE PRODUCT_ID IS NULL", nativeQuery = true)
	List<Barcode> findAllByProductIdNull();
	
	Barcode findByValue(String value);
}
