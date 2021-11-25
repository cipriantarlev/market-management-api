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
		return modelMapper.map(region, RegionDTO.class);
	}

	public Region mapRegionDTOToRegion(RegionDTO region) {
		return modelMapper.map(region, Region.class);
	}
}
