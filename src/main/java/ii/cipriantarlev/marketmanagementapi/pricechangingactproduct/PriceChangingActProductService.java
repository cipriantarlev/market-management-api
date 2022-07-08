package ii.cipriantarlev.marketmanagementapi.pricechangingactproduct;

import java.util.List;
import java.util.UUID;

public interface PriceChangingActProductService {

    List<PriceChangingActProductDTO> findAllByPriceChangingActId(UUID priceChangingActId);

    PriceChangingActProductDTO findById(UUID id);

    PriceChangingActProductDTO save(PriceChangingActProductDTO priceChangingActProductDTO);

    PriceChangingActProductDTO update(PriceChangingActProductDTO priceChangingActProductDTO);

    void deleteById(UUID id);
}
