/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.plu;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;

import ii.cipriantarlev.marketmanagementapi.data.TestDataDTOBuilder;

class PluDTOTest {

	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	
	@Test
	void testWhenValueIsNull() throws Exception {
		Set<ConstraintViolation<PluDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullValuePluDTO());

		assertEquals("PLU value should not be null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
	
	@Test
	void testWhenValueIsNegative() throws Exception {

		Set<ConstraintViolation<PluDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNegativeValuePluDTO());

		assertEquals("PLU value should be positive",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenValueWrongRange() throws Exception {

		Set<ConstraintViolation<PluDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getWorngRangeValuePluDTO());

		assertEquals("PLU value max value is 9",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenValueIsZero() throws Exception {

		Set<ConstraintViolation<PluDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getZeroValuePluDTO());

		assertEquals("PLU value should be positive",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
}
