package ii.cipriantarlev.marketmanagementapi.product;

import java.util.List;

public interface ProductService {

	List<ProductDTO> findAll();

	ProductDTO findById(Long id);

	ProductDTO save(ProductDTO productDTO);

	void deleteById(Long id);
}
