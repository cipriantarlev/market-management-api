/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.measuringunit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/measuring-units")
public class MeasuringUnitController {

	@Autowired
	private MeasuringUnitService measuringUnitService;

	@GetMapping
	public ResponseEntity<List<MeasuringUnitDTO>> getMeasuringUnits() {
		List<MeasuringUnitDTO> measuringUnits = measuringUnitService.findAll();

		if (measuringUnits == null || measuringUnits.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(measuringUnits, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<MeasuringUnitDTO> getMeasuringUnit(@PathVariable Integer id) {
		var measuringUnit = measuringUnitService.findById(id);

		if (measuringUnit == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(measuringUnit, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<MeasuringUnitDTO> createMeasuringUnit(@RequestBody MeasuringUnitDTO measuringUnitDTO) {
		if (measuringUnitDTO.getId() != null && measuringUnitService.findById(measuringUnitDTO.getId()) != null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		var measuringUnit = measuringUnitService.save(measuringUnitDTO);
		var headers = new HttpHeaders();
		headers.setLocation(
				UriComponentsBuilder.fromPath("/measuring-units/{id}").buildAndExpand(measuringUnit.getId()).toUri());
		return new ResponseEntity<>(measuringUnit, headers, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<MeasuringUnitDTO> updateMeasuringUnit(@RequestBody MeasuringUnitDTO measuringUnitDTO) {
		var measuringUnit = measuringUnitService.findById(measuringUnitDTO.getId());

		if (measuringUnit == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		MeasuringUnitDTO savedMeasuringUnit = measuringUnitService.save(measuringUnitDTO);
		return new ResponseEntity<>(savedMeasuringUnit, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteVat(@PathVariable Integer id) {
		var measuringUnit = measuringUnitService.findById(id);

		if (measuringUnit == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		measuringUnitService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
