package ii.cipriantarlev.marketmanagementapi.category;

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

class CategoryDTOTest {

	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Test
	void testWhenValueIsNull() throws Exception {

		Set<ConstraintViolation<CategoryDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getNullValueCategoryDTO());

		assertEquals("Category name should not be blank or null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenValueIsBlank() throws Exception {

		Set<ConstraintViolation<CategoryDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getBlankValueCategoryDTO());

		assertEquals("Category name should not be blank or null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenValueIsEmpty() throws Exception {

		Set<ConstraintViolation<CategoryDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getEmptyValueCategoryDTO());

		List<ConstraintViolation<CategoryDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		finalSet.add(constraintViolationList.get(0).getMessage());
		finalSet.add(constraintViolationList.get(1).getMessage());

		assertTrue(finalSet.contains("Category name should not be blank or null"));
		assertTrue(finalSet.contains("Category name length should be between 1 and 150"));
		assertEquals(2, constraintViolationSet.size());
	}

	@Test
	void testWhenValueIsInvalid() throws Exception {

		Set<ConstraintViolation<CategoryDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getInvalidCharactersValueCategoryDTO());

		assertEquals("Category name should contain only letters",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenValueIsToLong() throws Exception {

		Set<ConstraintViolation<CategoryDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getToManyCharactersValueCategoryDTO());

		assertEquals("Category name length should be between 1 and 150",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
}
