/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.documenttype;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;

import ii.cipriantarlev.marketmanagementapi.data.TestDataDTOBuilder;

class DocumentTypeDTOTest {

	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Test
	void testWhenValueIsNull() throws Exception {

		Set<ConstraintViolation<DocumentTypeDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullValueDocumentTypeDTO());

		assertEquals("Document type name should not be blank or null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenValueIsBlank() throws Exception {

		Set<ConstraintViolation<DocumentTypeDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getBlankValueDocumentTypeDTO());

		assertEquals("Document type name should not be blank or null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenValueIsEmpty() throws Exception {

		Set<ConstraintViolation<DocumentTypeDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getEmptyValueDocumentTypeDTO());

		List<ConstraintViolation<DocumentTypeDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		finalSet.add(constraintViolationList.get(0).getMessage());
		finalSet.add(constraintViolationList.get(1).getMessage());

		assertTrue(finalSet.contains("Document type name should not be blank or null"));
		assertTrue(finalSet.contains("Document type name length should be between 1 and 250"));
		assertEquals(2, constraintViolationSet.size());
	}

	@Test
	void testWhenValueIsInvalid() throws Exception {

		Set<ConstraintViolation<DocumentTypeDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getInvalidCharactersValueDocumentTypeDTO());

		assertEquals("Document type name should contain only letters",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenValueIsToLong() throws Exception {

		Set<ConstraintViolation<DocumentTypeDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getToManyCharactersValueDocumentTypeDTO());

		assertEquals("Document type name length should be between 1 and 250",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
}
