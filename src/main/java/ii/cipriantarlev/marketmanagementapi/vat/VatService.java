package ii.cipriantarlev.marketmanagementapi.vat;

import java.util.List;

public interface VatService {

	List<VatDTO> findAll();

	VatDTO findById(Integer id);

	VatDTO save(VatDTO vatDTO);

	void deleteById(Integer id);
}
