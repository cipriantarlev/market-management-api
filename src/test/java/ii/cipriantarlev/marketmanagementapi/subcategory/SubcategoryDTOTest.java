package ii.cipriantarlev.marketmanagementapi.subcategory;

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

class SubcategoryDTOTest {

	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Test
	void testWhenNameIsNull() throws Exception {

		Set<ConstraintViolation<SubcategoryDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getNullNameSubcategoryDTO());

		assertEquals("Subcategory name should not be blank or null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenNameIsBlank() throws Exception {

		Set<ConstraintViolation<SubcategoryDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getBlankNameSubcategoryDTO());

		assertEquals("Subcategory name should not be blank or null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenNameIsEmpty() throws Exception {

		Set<ConstraintViolation<SubcategoryDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getEmptyNameSubcategoryDTO());

		List<ConstraintViolation<SubcategoryDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Subcategory name should not be blank or null"));
		assertTrue(finalSet.contains("Subcategory name length should be between 1 and 50"));
		assertTrue(finalSet.contains("Subcategory name should contain only letters and numbers"));
		assertEquals(3, constraintViolationSet.size());
	}

	@Test
	void testWhenNameIsInvalid() throws Exception {

		Set<ConstraintViolation<SubcategoryDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getInvalidCharactersNameSubcategoryDTO());

		assertEquals("Subcategory name should contain only letters and numbers",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenNameIsToLong() throws Exception {

		Set<ConstraintViolation<SubcategoryDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getToManyCharactersNameSubcategoryDTO());

		assertEquals("Subcategory name length should be between 1 and 50",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
	
	@Test
	void testWhenCategoryIsNull() throws Exception {

		Set<ConstraintViolation<SubcategoryDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getNullCategorySubcategoryDTO());

		assertEquals("Category DTO should not be null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
}
