/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.productscode;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCodeRepository extends JpaRepository<ProductCode, Long> {

	ProductCode findFirst1ByOrderByValueDesc();
}
