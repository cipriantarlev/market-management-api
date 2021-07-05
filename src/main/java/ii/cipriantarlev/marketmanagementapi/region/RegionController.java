package ii.cipriantarlev.marketmanagementapi.region;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/regions")
public class RegionController {

	@Autowired
	private RegionService regionService;

	@GetMapping
	public ResponseEntity<List<RegionDTO>> getRegions() {

		List<RegionDTO> regions = regionService.findAll();

		if (regions == null || regions.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(regions, HttpStatus.OK);
	}
}
