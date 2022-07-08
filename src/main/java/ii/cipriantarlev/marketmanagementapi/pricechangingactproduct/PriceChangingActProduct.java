package ii.cipriantarlev.marketmanagementapi.pricechangingactproduct;

import ii.cipriantarlev.marketmanagementapi.pricechangingact.PriceChangingAct;
import ii.cipriantarlev.marketmanagementapi.product.Product;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "price_changing_act_products")
@NoArgsConstructor
@Getter
@Setter
public class PriceChangingActProduct {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH, CascadeType.MERGE })
    @JoinColumn(name = "price_changing_act_id")
    @ToString.Exclude
    private PriceChangingAct priceChangingAct;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "product_id")
    @ToString.Exclude
    private Product product;

    @Column(name = "old_price")
    private BigDecimal oldPrice;

    @Column(name = "price_difference")
    private BigDecimal priceDifference;
}
