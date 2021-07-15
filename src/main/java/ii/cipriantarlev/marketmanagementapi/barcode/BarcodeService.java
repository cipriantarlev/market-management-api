package ii.cipriantarlev.marketmanagementapi.barcode;

import java.util.List;

public interface BarcodeService {

	List<BarcodeDTO> findAll();

	BarcodeDTO findById(Long id);

	BarcodeDTO generateNewBarcode(BarcodeDTO barcodeDTO);

	void deleteById(Long id);
}
