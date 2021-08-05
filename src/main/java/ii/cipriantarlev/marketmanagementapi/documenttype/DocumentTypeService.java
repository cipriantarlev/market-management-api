package ii.cipriantarlev.marketmanagementapi.documenttype;

import java.util.List;

public interface DocumentTypeService {

	List<DocumentTypeDTO> findAll();

	DocumentTypeDTO findById(Integer id);

	DocumentTypeDTO save(DocumentTypeDTO documentTypeDTO);

	void deleteById(Integer id);
}
