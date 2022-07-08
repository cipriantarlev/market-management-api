package ii.cipriantarlev.marketmanagementapi.pricechangingact;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface PriceChangingActService {

    List<PriceChangingActDTO> findAll();

    PriceChangingActDTO findById(UUID id);

    PriceChangingActDTO save(PriceChangingActDTO priceChangingActDTO);

    PriceChangingActDTO update(PriceChangingActDTO priceChangingActDTO);

    void deleteById(UUID id);

    int updateIsApprovedMarker(Map<Boolean, List<UUID>> priceChangingActsToUpdate);

    List<PriceChangingAct> findByInvoiceId(long invoiceId);
}
