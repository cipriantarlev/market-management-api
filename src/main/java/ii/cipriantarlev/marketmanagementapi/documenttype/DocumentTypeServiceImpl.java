/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.documenttype;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {

	@Autowired
	private DocumentTypeRepository documentTypeRepository;

	@Autowired
	private DocumentTypeMapper documentTypeMapper;

	@Override
	public List<DocumentTypeDTO> findAll() {
		return documentTypeRepository.findAll().stream()
				.map(documentType -> documentTypeMapper.mapEntityToDTO(documentType))
				.collect(Collectors.toList());
	}

	@Override
	public DocumentTypeDTO findById(Integer id) {
		var documentType = documentTypeRepository.findById(id);

		if (documentType.isPresent()) {
			return documentTypeMapper.mapEntityToDTO(documentType.get());
		}

		return null;
	}

	@Override
	public DocumentTypeDTO save(DocumentTypeDTO documentTypeDTO) {
		var documentType = documentTypeRepository.save(documentTypeMapper.mapDTOToEntity(documentTypeDTO));
		return documentTypeMapper.mapEntityToDTO(documentType);
	}

	@Override
	public void deleteById(Integer id) {
		documentTypeRepository.deleteById(id);
	}
}
