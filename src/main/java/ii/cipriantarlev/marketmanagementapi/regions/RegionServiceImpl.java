package ii.cipriantarlev.marketmanagementapi.regions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionServiceImpl implements RegionService {

	@Autowired
	private RegionRepository regionRepository;

	@Autowired
	private RegionMapper regionMapper;

	@Override
	public List<RegionDTO> findAll() {
		return regionRepository.findAll().stream()
				.map(region -> regionMapper.mapRegionToRegionDTO(region))
				.collect(Collectors.toList());
	}

}
