/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.region;

import static ii.cipriantarlev.marketmanagementapi.utils.Constants.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(LOCAL_HOST)
@RestController
@RequestMapping(REGIONS_ROOT_PATH)
public class RegionController {

	@Autowired
	private RegionService regionService;

	@GetMapping
	public ResponseEntity<List<RegionDTO>> getRegions() {
		List<RegionDTO> regions = regionService.findAll();
		return new ResponseEntity<>(regions, HttpStatus.OK);
	}
}
