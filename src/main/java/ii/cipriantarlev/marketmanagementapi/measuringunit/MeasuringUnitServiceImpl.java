package ii.cipriantarlev.marketmanagementapi.measuringunit;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasuringUnitServiceImpl implements MeasuringUnitService {

	@Autowired
	private MeasuringUnitRepository measuringUnitRepository;

	@Autowired
	private MeasuringUnitMapper measuringUnitMapper;

	@Override
	public List<MeasuringUnitDTO> findAll() {
		return measuringUnitRepository.findAll().stream()
				.map(measuringUnit -> measuringUnitMapper.mapEntityToDTO(measuringUnit))
				.collect(Collectors.toList());
	}

	@Override
	public MeasuringUnitDTO findById(Integer id) {
		Optional<MeasuringUnit> measuringUnit = measuringUnitRepository.findById(id);

		if (measuringUnit.isPresent()) {
			return measuringUnitMapper.mapEntityToDTO(measuringUnit.get());
		}

		return null;
	}

	@Override
	public MeasuringUnitDTO save(MeasuringUnitDTO measuringUnitDTO) {
		var measuringUnit = measuringUnitRepository.save(measuringUnitMapper.mapDTOToEntity(measuringUnitDTO));
		return measuringUnitMapper.mapEntityToDTO(measuringUnit);
	}

	@Override
	public void deleteById(Integer id) {
		measuringUnitRepository.deleteById(id);
	}
}
