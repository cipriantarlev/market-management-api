package ii.cipriantarlev.marketmanagementapi.vat;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VatMapper {

	@Autowired
	private ModelMapper modelMapper;

	public VatDTO mapVatToVatDTO(Vat vat) {
		return modelMapper.map(vat, VatDTO.class);
	}

	public Vat mapVatDTOToVat(VatDTO vatDTO) {
		return modelMapper.map(vatDTO, Vat.class);
	}
}
