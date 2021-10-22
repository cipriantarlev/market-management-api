/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.region;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ii.cipriantarlev.marketmanagementapi.exceptions.DTOListNotFoundException;

@Service
public class RegionServiceImpl implements RegionService {

	@Autowired
	private RegionRepository regionRepository;

	@Autowired
	private RegionMapper regionMapper;

	@Override
	public List<RegionDTO> findAll() {
		List<RegionDTO> regions = regionRepository.findAll().stream()
				.map(region -> regionMapper.mapRegionToRegionDTO(region))
				.collect(Collectors.toList());

		if (regions == null || regions.isEmpty()) {
			throw new DTOListNotFoundException("Region list not found");
		}

		return regions;
	}
}
