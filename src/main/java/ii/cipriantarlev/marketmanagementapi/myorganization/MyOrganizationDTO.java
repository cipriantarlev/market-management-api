package ii.cipriantarlev.marketmanagementapi.myorganization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class MyOrganizationDTO {

	private Integer id;

	private String name;

	private String bank;

	private String fiscalCode;

	private String bankAccount;

	private String vatCode;

	private String city;

	private String phoneNumber;

	private String email;

	private String note;
}
