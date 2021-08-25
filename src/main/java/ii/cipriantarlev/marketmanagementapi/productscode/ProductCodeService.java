/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.productscode;

import java.util.List;

public interface ProductCodeService {

	List<ProductCodeDTO> findAll();

	ProductCodeDTO findById(Long id);

	ProductCodeDTO generateNewProductCode();

	void deleteById(Long id);
}
