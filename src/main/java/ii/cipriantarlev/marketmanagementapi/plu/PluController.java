/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.plu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static ii.cipriantarlev.marketmanagementapi.util.Constants.*;

@CrossOrigin(LOCAL_HOST)
@RestController
@RequestMapping(PLU_ROOT_PATH)
public class PluController {

	@Autowired
	private PluService pluService;

	@GetMapping
	public ResponseEntity<List<PluDTO>> getPluList() {
		List<PluDTO> pluList = pluService.findAll();
		return new ResponseEntity<>(pluList, HttpStatus.OK);
	}

	@GetMapping(ID_PATH)
	public ResponseEntity<PluDTO> getPlu(@PathVariable Integer id) {
		var plu = pluService.findById(id);
		return new ResponseEntity<>(plu, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<PluDTO> generateNewPlu() {
		var generatedPlu = pluService.generateNewPlu();
		return new ResponseEntity<>(generatedPlu, HttpStatus.OK);
	}

	@DeleteMapping(ID_PATH)
	public ResponseEntity<Void> deletePlu(@PathVariable Integer id) {
		pluService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
