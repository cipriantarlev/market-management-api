/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(value = "select p.id, name_rom, name_rus, category_id, subcategory_id, discount_price, retail_price, trade_margin, measuring_unit_id, vat_id, plu_id, product_code_id, stock from public.products as p left join barcodes as b on b.product_id = p.id where b.value = ?1", nativeQuery = true)
	Product findByBarcodeValue(String barcodeValue);
}
