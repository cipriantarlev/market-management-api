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

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/plu")
public class PluController {

	@Autowired
	private PluService pluService;

	@GetMapping
	public ResponseEntity<List<PluDTO>> getPluList() {
		List<PluDTO> pluList = pluService.findAll();

		if (pluList == null || pluList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(pluList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PluDTO> getPlu(@PathVariable Integer id) {
		var plu = pluService.findById(id);

		if (plu == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(plu, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<PluDTO> generateNewPlu() {
		var generatedPlu = pluService.generateNewPlu();
		return new ResponseEntity<>(generatedPlu, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePlu(@PathVariable Integer id) {
		var plu = pluService.findById(id);

		if (plu == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		pluService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}