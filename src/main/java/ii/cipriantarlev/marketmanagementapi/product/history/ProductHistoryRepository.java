package ii.cipriantarlev.marketmanagementapi.product.history;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductHistoryRepository extends JpaRepository<ProductHistory, UUID> {

    List<ProductHistory> findDistinctByProductIdAndDiscountPriceNotNullAndRetailPriceNotNullOrderByCreatedDesc(Long productId);
}
