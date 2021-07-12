package ii.cipriantarlev.marketmanagementapi.barcode;

import java.util.List;

public interface BarcodeService {

	List<BarcodeDTO> findAll();

	List<BarcodeDTO> findAllByProductId(Long productId);

	BarcodeDTO findById(Long id);

	BarcodeDTO generateNewBarcode(BarcodeDTO barcodeDTO);

	void deleteById(Long id);
}
