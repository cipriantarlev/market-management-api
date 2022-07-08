package ii.cipriantarlev.marketmanagementapi.pricechangingact;

import ii.cipriantarlev.marketmanagementapi.myorganization.MyOrganization;
import ii.cipriantarlev.marketmanagementapi.myorganization.MyOrganizationDTOOnlyName;
import ii.cipriantarlev.marketmanagementapi.validation.LocalDateFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class PriceChangingActDTO {

    private UUID id;

    @NotNull(message = "PriceChangingAct date should not be null")
    @LocalDateFormat(pattern = "yyyy-MM-dd", message = "PriceChangingAct date should be in the following format: {pattern}")
    private LocalDate dateCreated;

    @NotNull(message = "My Organization DTO should not be null")
    private MyOrganizationDTOOnlyName myOrganization;

    @DecimalMin(value = "0.0", message = "Old Price min value should be {value}")
    @Digits(integer = 6, fraction = 2, message = "Old Price format should have {integer} integer digits and {fraction} digits")
    private BigDecimal oldPrices;

    @DecimalMin(value = "0.0", message = "New Price min value should be {value}")
    @Digits(integer = 6, fraction = 2, message = "New Price format should have {integer} integer digits and {fraction} digits")
    private BigDecimal newPrices;

    @Digits(integer = 6, fraction = 2, message = "Price difference format should have {integer} integer digits and {fraction} digits")
    private BigDecimal pricesDifference;

    @Size(min = 1, max = 250, message = "Note length should be between {min} and {max}")
    private String note;

    private boolean isApproved;

    private boolean isSent;

    private Long invoiceId;
}
