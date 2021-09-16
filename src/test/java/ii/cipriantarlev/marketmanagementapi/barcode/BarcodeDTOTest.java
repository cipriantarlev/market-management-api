/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.barcode;

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

class BarcodeDTOTest {

	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Test
	void testWhenValueIsNull() throws Exception {

		Set<ConstraintViolation<BarcodeDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullValueBarcodeDTO());

		assertEquals("Barcode value should not be blank or null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenValueIsBlank() throws Exception {

		Set<ConstraintViolation<BarcodeDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getBlankValueBarcodeDTO());

		List<ConstraintViolation<BarcodeDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		finalSet.add(constraintViolationList.get(0).getMessage());
		finalSet.add(constraintViolationList.get(1).getMessage());

		assertTrue(finalSet.contains("Barcode value should not be blank or null"));
		assertTrue(finalSet.contains("Barcode value should contain only numbers"));
		assertEquals(2, constraintViolationSet.size());
	}

	@Test
	void testWhenValueIsEmpty() throws Exception {

		Set<ConstraintViolation<BarcodeDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getEmptyValueBarcodeDTO());

		List<ConstraintViolation<BarcodeDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		finalSet.add(constraintViolationList.get(0).getMessage());
		finalSet.add(constraintViolationList.get(1).getMessage());

		assertTrue(finalSet.contains("Barcode value should not be blank or null"));
		assertTrue(finalSet.contains("Barcode value length should be between 1 and 13"));
		assertEquals(2, constraintViolationSet.size());
	}

	@Test
	void testWhenValueIsInvalid() throws Exception {

		Set<ConstraintViolation<BarcodeDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getInvalidCharactersValueBarcodeDTO());

		assertEquals("Barcode value should contain only numbers",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenValueIsToLong() throws Exception {

		Set<ConstraintViolation<BarcodeDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getToManyCharactersValueBarcodeDTO());

		assertEquals("Barcode value length should be between 1 and 13",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
}
