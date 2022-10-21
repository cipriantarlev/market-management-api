package ii.cipriantarlev.marketmanagementapi.pricechangingact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public interface PriceChangingActRepository extends JpaRepository<PriceChangingAct, UUID> {

    List<PriceChangingAct> findByIsApprovedAndIsSent(boolean isApproved, boolean isSent);

    List<PriceChangingAct> findByInvoiceId(long invoiceId);

    @Transactional
    @Modifying
    @Query("update PriceChangingAct u set u.isApproved = ?1 where u.id = ?2")
    int updateIsApprovedMarker(boolean isApproved, UUID priceChangingActId);

    @Transactional
    @Modifying
    @Query("update PriceChangingAct u set u.isSent = ?1 where u.id = ?2")
    int updateIsSentMarker(boolean isSent, UUID priceChangingActId);
}
