package ii.cipriantarlev.marketmanagementapi.measuringunit;

import java.util.List;

public interface MeasuringUnitService {

	List<MeasuringUnitDTO> findAll();

	MeasuringUnitDTO findById(Integer id);

	MeasuringUnitDTO save(MeasuringUnitDTO measuringUnitDTO);

	void deleteById(Integer id);
}
