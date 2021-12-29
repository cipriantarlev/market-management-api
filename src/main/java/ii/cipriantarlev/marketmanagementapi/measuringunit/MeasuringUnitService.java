/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.measuringunit;

import java.util.List;

public interface MeasuringUnitService {

	List<MeasuringUnitDTO> findAll();

	MeasuringUnitDTO findById(Long id);

	MeasuringUnitDTO save(MeasuringUnitDTO measuringUnitDTO);

	MeasuringUnitDTO update(MeasuringUnitDTO measuringUnitDTO);

	void deleteById(Long id);
}
