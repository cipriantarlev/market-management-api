package ii.cipriantarlev.marketmanagementapi.pricechangingactproduct;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PriceChangingActProductRepository extends JpaRepository<PriceChangingActProduct, UUID> {

    List<PriceChangingActProduct> findAllByPriceChangingActId(UUID priceChangingActId);
}