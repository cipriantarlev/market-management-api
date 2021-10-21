/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.documenttype;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ii.cipriantarlev.marketmanagementapi.exceptions.DTOFoundWhenSaveException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTOListNotFoundException;
import ii.cipriantarlev.marketmanagementapi.exceptions.DTONotFoundException;

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {

	@Autowired
	private DocumentTypeRepository documentTypeRepository;

	@Autowired
	private DocumentTypeMapper documentTypeMapper;

	@Override
	public List<DocumentTypeDTO> findAll() {
		List<DocumentTypeDTO> documentTypeList = documentTypeRepository.findAll().stream()
				.map(documentType -> documentTypeMapper.mapEntityToDTO(documentType))
				.collect(Collectors.toList());

		if (documentTypeList == null || documentTypeList.isEmpty()) {
			throw new DTOListNotFoundException("Document Type list not found");
		}

		return documentTypeList;
	}

	@Override
	public DocumentTypeDTO findById(Integer id) {
		var documentType = documentTypeRepository.findById(id);

		if (documentType.isPresent()) {
			return documentTypeMapper.mapEntityToDTO(documentType.get());
		}

		throw new DTONotFoundException(String.format("Document Type with %d not found", id), id);
	}

	@Override
	public DocumentTypeDTO save(DocumentTypeDTO documentTypeDTO) {
		if (documentTypeDTO.getId() != null && documentTypeRepository.findById(documentTypeDTO.getId()).isPresent()) {
			throw new DTOFoundWhenSaveException(
					String.format(
							"Document Type with id: '%d' already exists in database. "
									+ "Please use update in order to save the changes in database",
							documentTypeDTO.getId()),
					documentTypeDTO.getId());
		}

		var documentType = documentTypeRepository.save(documentTypeMapper.mapDTOToEntity(documentTypeDTO));
		return documentTypeMapper.mapEntityToDTO(documentType);
	}

	@Override
	public DocumentTypeDTO update(DocumentTypeDTO documentTypeDTO) {
		this.findById(documentTypeDTO.getId());
		var documentType = documentTypeRepository.save(documentTypeMapper.mapDTOToEntity(documentTypeDTO));
		return documentTypeMapper.mapEntityToDTO(documentType);
	}

	@Override
	public void deleteById(Integer id) {
		this.findById(id);
		documentTypeRepository.deleteById(id);
	}
}
