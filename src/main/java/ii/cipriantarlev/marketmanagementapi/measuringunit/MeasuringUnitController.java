/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.measuringunit;

import static ii.cipriantarlev.marketmanagementapi.utils.Constants.*;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ii.cipriantarlev.marketmanagementapi.utils.RestControllerUtil;

@CrossOrigin(LOCAL_HOST)
@RestController
@RequestMapping(MEASURING_UNITS_ROOT_PATH)
public class MeasuringUnitController {

	@Autowired
	private MeasuringUnitService measuringUnitService;

	@Autowired
	private RestControllerUtil restControllerUtil;

	@GetMapping
	public ResponseEntity<List<MeasuringUnitDTO>> getMeasuringUnits() {
		List<MeasuringUnitDTO> measuringUnits = measuringUnitService.findAll();
		return new ResponseEntity<>(measuringUnits, HttpStatus.OK);
	}

	@GetMapping(ID_PATH)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MeasuringUnitDTO> getMeasuringUnit(@PathVariable Integer id) {
		var measuringUnit = measuringUnitService.findById(id);
		return new ResponseEntity<>(measuringUnit, HttpStatus.OK);
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MeasuringUnitDTO> createMeasuringUnit(@Valid @RequestBody MeasuringUnitDTO measuringUnitDTO) {
		var measuringUnit = measuringUnitService.save(measuringUnitDTO);
		var headers = restControllerUtil.setHttpsHeaderLocation(MEASURING_UNITS_ROOT_PATH.concat(ID_PATH),
				measuringUnit.getId().longValue());
		return new ResponseEntity<>(measuringUnit, headers, HttpStatus.OK);
	}

	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MeasuringUnitDTO> updateMeasuringUnit(@Valid @RequestBody MeasuringUnitDTO measuringUnitDTO) {
		var savedMeasuringUnit = measuringUnitService.update(measuringUnitDTO);
		return new ResponseEntity<>(savedMeasuringUnit, HttpStatus.OK);
	}

	@DeleteMapping(ID_PATH)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deleteVat(@PathVariable Integer id) {
		measuringUnitService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
