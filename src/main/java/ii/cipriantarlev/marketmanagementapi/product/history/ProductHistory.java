package ii.cipriantarlev.marketmanagementapi.product.history;

import com.vladmihalcea.hibernate.type.json.JsonType;
import ii.cipriantarlev.marketmanagementapi.history.History;
import ii.cipriantarlev.marketmanagementapi.product.ProductDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "product_history")
@NoArgsConstructor
@Getter
@Setter
@TypeDef(name = "json", typeClass = JsonType.class)
public class ProductHistory extends History {

    @Type(type = "json")
    @Column(name = "product", columnDefinition = "jsonb")
    private ProductDTO productDTO;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "discount_price")
    private BigDecimal discountPrice;

    @Column(name = "retail_price")
    private BigDecimal retailPrice;

    @Override
    public int hashCode() {
        return Objects.hash(discountPrice, retailPrice);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductHistory that = (ProductHistory) o;
        return Objects.equals(discountPrice, that.discountPrice) && Objects.equals(retailPrice, that.retailPrice);
    }
}
