package ii.cipriantarlev.marketmanagementapi.measuringunit;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MeasuringUnitMapper {

	@Autowired
	private ModelMapper modelMapper;

	public MeasuringUnitDTO mapEntityToDTO(MeasuringUnit measuringUnit) {
		return modelMapper.map(measuringUnit, MeasuringUnitDTO.class);
	}

	public MeasuringUnit mapDTOToEntity(MeasuringUnitDTO measuringUnitDTO) {
		return modelMapper.map(measuringUnitDTO, MeasuringUnit.class);
	}
}
