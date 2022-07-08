package ii.cipriantarlev.marketmanagementapi.pricechangingact;

import ii.cipriantarlev.marketmanagementapi.myorganization.MyOrganization;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "price_changing_acts")
@NoArgsConstructor
@Getter
@Setter
public class PriceChangingAct {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "date_created")
    private LocalDate dateCreated;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "my_organization_id")
    private MyOrganization myOrganization;

    @Column(name = "old_prices")
    private BigDecimal oldPrices;

    @Column(name = "new_prices")
    private BigDecimal newPrices;

    @Column(name = "prices_difference")
    private BigDecimal pricesDifference;

    @Column(name = "note")
    private String note;

    @Column(name = "is_approved")
    private boolean isApproved;

    @Column(name = "is_sent")
    private boolean isSent;

    @Column(name = "invoice_id")
    private Long invoiceId;
}
