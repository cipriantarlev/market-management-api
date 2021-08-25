/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.region;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegionMapper {

	@Autowired
	private ModelMapper modelMapper;

	public RegionDTO mapRegionToRegionDTO(Region region) {

		modelMapper.typeMap(Region.class, RegionDTO.class).addMappings(mapper -> {
			mapper.map(Region::getId, RegionDTO::setId);
			mapper.map(Region::getName, RegionDTO::setName);
		});

		return modelMapper.map(region, RegionDTO.class);
	}

	public Region mapRegionDTOToRegion(RegionDTO region) {

		modelMapper.typeMap(RegionDTO.class, Region.class).addMappings(mapper -> {
			mapper.map(RegionDTO::getId, Region::setId);
			mapper.map(RegionDTO::getName, Region::setName);
		});

		return modelMapper.map(region, Region.class);
	}
}
