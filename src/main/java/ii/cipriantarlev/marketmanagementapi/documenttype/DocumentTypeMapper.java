/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.documenttype;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Class used to map {@link DocumentType} to {@link DocumentTypeDTO} and vice-versa.
 */
@Component
public class DocumentTypeMapper {

	/**
	 * {@link ModelMapper} is used to map entity to dto.
	 */
	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Method used to map {@link DocumentType} to {@link DocumentTypeDTO}.
	 *
	 * @param docymentType entity to be mapped.
	 * @return resulted dto.
	 */
	public DocumentTypeDTO mapEntityToDTO(DocumentType docymentType) {
		return modelMapper.map(docymentType, DocumentTypeDTO.class);
	}

	/**
	 * Method used to map {@link DocumentTypeDTO} to {@link DocumentType}.
	 *
	 * @param docymentTypeDTO DTO to be mapped.
	 * @return resulted entity.
	 */
	public DocumentType mapDTOToEntity(DocumentTypeDTO docymentTypeDTO) {
		return modelMapper.map(docymentTypeDTO, DocumentType.class);
	}
}
