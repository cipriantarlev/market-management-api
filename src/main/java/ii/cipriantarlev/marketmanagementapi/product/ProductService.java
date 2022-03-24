/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.product;

import java.util.List;
import java.util.Map;

public interface ProductService {

	List<ProductDTOForList> findAll();

	ProductDTO findByBarcodeValue(String barcodeValue);

	ProductDTO findById(Long id);

	ProductDTO save(ProductDTO productDTO);

	ProductDTO update(ProductDTO productDTO);

	void deleteById(Long id);
	
	boolean checkIfNameRomExists(String nameRom);
	
	boolean checkIfNameRusExists(String nameRus);

	int updateIsCheckedMarker(Map<Boolean, List<Long>> productsToUpdate);
}
