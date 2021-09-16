/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.measuringunit;

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

class MeasuringUnitDTOTest {

	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Test
	void testWhenNameIsNull() throws Exception {

		Set<ConstraintViolation<MeasuringUnitDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullValueMeasuringUnitDTO());

		assertEquals("Measuring Unit name should not be blank or null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenNameIsBlank() throws Exception {

		Set<ConstraintViolation<MeasuringUnitDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getBlankValueMeasuringUnitDTO());

		List<ConstraintViolation<MeasuringUnitDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Measuring Unit name should contain only letters and numbers"));
		assertTrue(finalSet.contains("Measuring Unit name should not be blank or null"));
		assertEquals(2, constraintViolationSet.size());
	}

	@Test
	void testWhenNameIsEmpty() throws Exception {

		Set<ConstraintViolation<MeasuringUnitDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getEmptyValueMeasuringUnitDTO());

		List<ConstraintViolation<MeasuringUnitDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Measuring Unit name should not be blank or null"));
		assertTrue(finalSet.contains("Measuring Unit name length should be between 1 and 50"));
		assertTrue(finalSet.contains("Measuring Unit name should contain only letters and numbers"));
		assertEquals(3, constraintViolationSet.size());
	}

	@Test
	void testWhenNameIsInvalid() throws Exception {

		Set<ConstraintViolation<MeasuringUnitDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getInvalidCharactersValueMeasuringUnitDTO());

		assertEquals("Measuring Unit name should contain only letters and numbers",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenNameIsToLong() throws Exception {

		Set<ConstraintViolation<MeasuringUnitDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getToManyCharactersValueMeasuringUnitDTO());

		assertEquals("Measuring Unit name length should be between 1 and 50",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
}
