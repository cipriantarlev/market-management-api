package ii.cipriantarlev.marketmanagementapi.vat;

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

import ii.cipriantarlev.marketmanagementapi.data.TestDataBuilder;

class VatDTOTest {

	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Test
	void testWhenValueIsNull() throws Exception {

		Set<ConstraintViolation<VatDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getNullValueVatDTO());

		assertEquals("Vat value should not be null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenValueIsNegative() throws Exception {

		Set<ConstraintViolation<VatDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getNegativeValueVatDTO());

		assertEquals("Vat value min value is 0",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenValueWrongRange() throws Exception {

		Set<ConstraintViolation<VatDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getWorngRangeValueVatDTO());

		assertEquals("Vat value max value is 99",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenNameIsNull() throws Exception {

		Set<ConstraintViolation<VatDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getNullNameVatDTO());

		assertEquals("Vat name should not be blank or null", constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenNameIsBlank() throws Exception {

		Set<ConstraintViolation<VatDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getBlankNameVatDTO());

		List<ConstraintViolation<VatDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Vat name should not be blank or null"));
		assertTrue(finalSet.contains("Vat name should contain only letters, numbers, () or %"));
		assertEquals(2, constraintViolationSet.size());
	}

	@Test
	void testWhenNameIsEmpty() throws Exception {

		Set<ConstraintViolation<VatDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getEmptyNameVatDTO());

		List<ConstraintViolation<VatDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Vat name should not be blank or null"));
		assertTrue(finalSet.contains("Vat name length should be between 1 and 100"));
		assertTrue(finalSet.contains("Vat name should contain only letters, numbers, () or %"));
		assertEquals(3, constraintViolationSet.size());
	}

	@Test
	void testWhenNameIsInvalid() throws Exception {

		Set<ConstraintViolation<VatDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getInvalidCharactersNameVatDTO());

		assertEquals("Vat name should contain only letters, numbers, () or %",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenNameIsToLong() throws Exception {

		Set<ConstraintViolation<VatDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getToManyCharactersNameVatDTO());

		assertEquals("Vat name length should be between 1 and 100",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
}
