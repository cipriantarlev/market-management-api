package ii.cipriantarlev.marketmanagementapi.pricechangingact;

import ii.cipriantarlev.marketmanagementapi.utils.RestControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static ii.cipriantarlev.marketmanagementapi.utils.Constants.*;

@CrossOrigin(LOCAL_HOST)
@RestController
@RequestMapping(PRICE_CHANGING_ACTS_ROOT_PATH)
public class PriceChangingActController {

    @Autowired
    private PriceChangingActService service;

    @Autowired
    private RestControllerUtil restControllerUtil;

    @GetMapping
    public ResponseEntity<List<PriceChangingActDTO>> getPriceChangingActs() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(ID_PATH)
    public ResponseEntity<PriceChangingActDTO> getPriceChangingAct(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<PriceChangingActDTO> createPriceChangingAct(
            @Valid @RequestBody PriceChangingActDTO priceChangingActDTO) {
        var priceChangingAct = service.save(priceChangingActDTO);
        var headers = restControllerUtil.setHttpsHeaderLocation(
                PRICE_CHANGING_ACTS_ROOT_PATH, priceChangingAct.getId());

        return new ResponseEntity<>(priceChangingAct, headers, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PriceChangingActDTO> updatePriceChangingAct(
            @Valid @RequestBody PriceChangingActDTO priceChangingActDTO) {
        return ResponseEntity.ok(service.update(priceChangingActDTO));
    }

    @PutMapping(IS_APPROVED)
    public ResponseEntity<Integer> updateApprovedMarker(@RequestBody Map<Boolean, List<UUID>> priceChangingActsToUpdate) {
        return ResponseEntity.ok(service.updateIsApprovedMarker(priceChangingActsToUpdate));
    }

    @DeleteMapping(ID_PATH)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletePriceActChanging(@PathVariable UUID id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
