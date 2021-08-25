/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.documenttype;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DocumentTypeMapper {

	@Autowired
	private ModelMapper modelMapper;

	public DocumentTypeDTO mapEntityToDTO(DocumentType docymentType) {
		return modelMapper.map(docymentType, DocumentTypeDTO.class);
	}

	public DocumentType mapDTOToEntity(DocumentTypeDTO docymentTypeDTO) {
		return modelMapper.map(docymentTypeDTO, DocumentType.class);
	}
}
