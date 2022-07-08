package ii.cipriantarlev.marketmanagementapi.pricechangingact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.UUID;

public interface PriceChangingActRepository extends JpaRepository<PriceChangingAct, UUID> {

    @Transactional
    @Modifying
    @Query("update PriceChangingAct u set u.isApproved = ?1 where u.id = ?2")
    int updateIsApprovedMarker(boolean isApproved, UUID invoiceId);
}
