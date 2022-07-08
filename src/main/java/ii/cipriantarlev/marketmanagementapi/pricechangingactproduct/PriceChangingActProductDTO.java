package ii.cipriantarlev.marketmanagementapi.pricechangingactproduct;

import ii.cipriantarlev.marketmanagementapi.pricechangingact.PriceChangingActDTO;
import ii.cipriantarlev.marketmanagementapi.product.ProductDTOForList;
import lombok.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class PriceChangingActProductDTO {

    private UUID id;

    @NotNull(message = "Price Changing Act should not be null")
    private PriceChangingActDTO priceChangingAct;

    @NotNull(message = "Product should not be null")
    private ProductDTOForList product;

    @DecimalMin(value = "0.0", message = "Old Price min value should be {value}")
    @Digits(integer = 6, fraction = 2, message = "Old Price format should have {integer} integer digits and {fraction} digits")
    private BigDecimal oldPrice;

    @Digits(integer = 6, fraction = 2, message = "Price difference format should have {integer} integer digits and {fraction} digits")
    private BigDecimal priceDifference;
}
