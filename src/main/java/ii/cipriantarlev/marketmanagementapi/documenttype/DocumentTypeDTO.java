/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.documenttype;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.*;

/**
 * DTO class of {@link DocumentType}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class DocumentTypeDTO {

	/**
	 * Document Type id.
	 */
	@Positive
	private Long id;

	/**
	 * Document Type name.
	 */
	@NotBlank(message = "Document type name should not be blank or null")
	@Size(min = 1, max = 250, message = "Document type name length should be between {min} and {max}")
	@Pattern(regexp = "^[A-Za-z\\s]*$", message = "Document type name should contain only letters")
	private String name;
}
