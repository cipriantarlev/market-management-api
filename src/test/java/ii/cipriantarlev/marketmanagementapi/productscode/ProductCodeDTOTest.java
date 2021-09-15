package ii.cipriantarlev.marketmanagementapi.productscode;

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

class ProductCodeDTOTest {

	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Test
	void testWhenValueIsNull() throws Exception {

		Set<ConstraintViolation<ProductCodeDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getNullValueProductCodeDTO());

		assertEquals("Product Code value should not be blank or null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenValueIsBlank() throws Exception {

		Set<ConstraintViolation<ProductCodeDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getBlankValueProductCodeDTO());

		List<ConstraintViolation<ProductCodeDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Product Code value should contain only letters and numbers"));
		assertTrue(finalSet.contains("Product Code value should not be blank or null"));
		assertEquals(2, constraintViolationSet.size());
	}

	@Test
	void testWhenValueIsEmpty() throws Exception {

		Set<ConstraintViolation<ProductCodeDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getEmptyValueProductCodeDTO());

		List<ConstraintViolation<ProductCodeDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Product Code value should not be blank or null"));
		assertTrue(finalSet.contains("Product Code value length should be between 1 and 50"));
		assertTrue(finalSet.contains("Product Code value should contain only letters and numbers"));
		assertEquals(3, constraintViolationSet.size());
	}

	@Test
	void testWhenValueIsInvalid() throws Exception {

		Set<ConstraintViolation<ProductCodeDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getInvalidCharactersValueProductCodeDTO());

		assertEquals("Product Code value should contain only letters and numbers",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenValueIsToLong() throws Exception {

		Set<ConstraintViolation<ProductCodeDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getToManyCharactersValueProductCodeDTO());

		assertEquals("Product Code value length should be between 1 and 50",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
}
