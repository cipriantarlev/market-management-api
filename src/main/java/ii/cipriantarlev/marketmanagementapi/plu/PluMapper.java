/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.plu;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PluMapper {

	@Autowired
	private ModelMapper modelMapper;

	public PluDTO mapEntityToDTO(Plu plu) {
		return modelMapper.map(plu, PluDTO.class);
	}

	public Plu mapDTOToEntity(PluDTO pluDTO) {
		return modelMapper.map(pluDTO, Plu.class);
	}
}
