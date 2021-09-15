package ii.cipriantarlev.marketmanagementapi.region;

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

class RegionDTOTest {

	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Test
	void testWhenNameIsNull() throws Exception {

		Set<ConstraintViolation<RegionDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getNullNameRegionDTO());

		assertEquals("Region name should not be blank or null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenNameIsBlank() throws Exception {

		Set<ConstraintViolation<RegionDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getBlankNameRegionDTO());

		assertEquals("Region name should not be blank or null", constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenNameIsEmpty() throws Exception {

		Set<ConstraintViolation<RegionDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getEmptyNameRegionDTO());

		List<ConstraintViolation<RegionDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Region name should not be blank or null"));
		assertTrue(finalSet.contains("Region name length should be between 1 and 100"));
		assertTrue(finalSet.contains("Region name should contain only letters and numbers"));
		assertEquals(3, constraintViolationSet.size());
	}

	@Test
	void testWhenNameIsInvalid() throws Exception {

		Set<ConstraintViolation<RegionDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getInvalidCharactersNameRegionDTO());

		assertEquals("Region name should contain only letters and numbers",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenNameIsToLong() throws Exception {

		Set<ConstraintViolation<RegionDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getToManyCharactersNameRegionDTO());

		assertEquals("Region name length should be between 1 and 100",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
}
