/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(value = "select * from public.products as p left join barcodes as b on b.product_id = p.id where b.value = ?1", nativeQuery = true)
	Product findByBarcodeValue(String barcodeValue);
	
	Product findByNameRom(String nameRom);
	
	Product findByNameRus(String nameRus);

	@Transactional
	@Modifying
	@Query("update Product u set u.isChecked = ?1 where u.id = ?2")
	int updateIsCheckedMarker(boolean isChecked, Long productId);

	@Transactional
	@Modifying
	@Query("update Product u set u.retailPrice = ?1, u.tradeMargin = ?2, u.oldRetailPrice = ?3 where u.id = ?4")
	int updateRetailPrice(BigDecimal retailPrice, BigDecimal tradeMargin, BigDecimal oldRetailPrice, Long productId);

	List<Product> findByIsCheckedTrue();
}
