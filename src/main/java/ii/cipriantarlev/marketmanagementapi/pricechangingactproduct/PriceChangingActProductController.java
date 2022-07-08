package ii.cipriantarlev.marketmanagementapi.pricechangingactproduct;

import ii.cipriantarlev.marketmanagementapi.utils.RestControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static ii.cipriantarlev.marketmanagementapi.utils.Constants.*;

@CrossOrigin(LOCAL_HOST)
@RestController
@RequestMapping(PRICE_CHANGING_ACT_PRODUCTS_ROOT_PATH)
class PriceChangingActProductController {

    @Autowired
    private PriceChangingActProductService service;

    @Autowired
    private RestControllerUtil restControllerUtil;

    @GetMapping(PRICE_CHANGING_ACT_ID)
    public ResponseEntity<List<PriceChangingActProductDTO>> getPriceChangingActProducts(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findAllByPriceChangingActId(id));
    }

    @GetMapping(ID_PATH)
    public ResponseEntity<PriceChangingActProductDTO> getPriceChangingActProduct(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<PriceChangingActProductDTO> createPriceChangingActProduct(
            @Valid @RequestBody PriceChangingActProductDTO priceChangingActProductDTO) {
        var priceChangingActProduct = service.save(priceChangingActProductDTO);
        var headers = restControllerUtil.setHttpsHeaderLocation(
                PRICE_CHANGING_ACT_PRODUCTS_ROOT_PATH, priceChangingActProduct.getId());

        return new ResponseEntity<>(priceChangingActProduct, headers, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PriceChangingActProductDTO> updatePriceChangingActProduct(
            @Valid @RequestBody PriceChangingActProductDTO priceChangingActProductDTO) {
        return ResponseEntity.ok(service.update(priceChangingActProductDTO));
    }

    @DeleteMapping(ID_PATH)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletePriceChangingActProduct(@PathVariable UUID id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}