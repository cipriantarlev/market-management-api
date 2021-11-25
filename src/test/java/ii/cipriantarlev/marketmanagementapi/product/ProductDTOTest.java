/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.product;

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

class ProductDTOTest {

	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Test
	void testWhenNameRomIsNull() throws Exception {

		Set<ConstraintViolation<ProductDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullNameRomProductDTO());

		assertEquals("Romanian Name should not be blank or null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenNameRomIsBlank() throws Exception {

		Set<ConstraintViolation<ProductDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getBlankNameRomProductDTO());

		assertEquals("Romanian Name should not be blank or null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenNameRomIsEmpty() throws Exception {

		Set<ConstraintViolation<ProductDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getEmptyNameRomProductDTO());

		List<ConstraintViolation<ProductDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Romanian Name should not be blank or null"));
		assertTrue(finalSet.contains("Romanian Name length should be between 1 and 300"));
		assertTrue(finalSet.contains("Romanian Name should contain only letters, numbers and dash"));
		assertEquals(3, constraintViolationSet.size());
	}

	@Test
	void testWhenNameRomIsInvalid() throws Exception {

		Set<ConstraintViolation<ProductDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getInvalidCharactersNameRomProductDTO());

		assertEquals("Romanian Name should contain only letters, numbers and dash",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenNameRomIsToLong() throws Exception {

		Set<ConstraintViolation<ProductDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getToManyCharactersNameRomProductDTO());

		assertEquals("Romanian Name length should be between 1 and 300",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
	
	@Test
	void testWhenNameRusIsNull() throws Exception {

		Set<ConstraintViolation<ProductDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullNameRusProductDTO());

		assertEquals("Russian Name should not be blank or null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenNameRusIsBlank() throws Exception {

		Set<ConstraintViolation<ProductDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getBlankNameRusProductDTO());

		assertEquals("Russian Name should not be blank or null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenNameRusIsEmpty() throws Exception {

		Set<ConstraintViolation<ProductDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getEmptyNameRusProductDTO());

		List<ConstraintViolation<ProductDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Russian Name should not be blank or null"));
		assertTrue(finalSet.contains("Russian Name length should be between 1 and 300"));
		assertTrue(finalSet.contains("Russian Name should contain only letters, numbers and dash"));
		assertEquals(3, constraintViolationSet.size());
	}

	@Test
	void testWhenNameRusIsInvalid() throws Exception {

		Set<ConstraintViolation<ProductDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getInvalidCharactersNameRusProductDTO());

		assertEquals("Russian Name should contain only letters, numbers and dash",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenNameRusIsToLong() throws Exception {

		Set<ConstraintViolation<ProductDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getToManyCharactersNameRusProductDTO());

		assertEquals("Russian Name length should be between 1 and 300",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenCategoryDTOIsNull() throws Exception {

		Set<ConstraintViolation<ProductDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullCategoryProductDTO());

		assertEquals("CategoryDTO should not be null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenSubcategoryDTOIsNull() throws Exception {

		Set<ConstraintViolation<ProductDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullSubcategoryProductDTO());

		assertEquals("SubcategoryDTO should not be null", constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenDiscountPriceIsNegative() throws Exception {

		Set<ConstraintViolation<ProductDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNegativeDiscountPriceProductDTO());

		assertEquals("Discount Price min value should be 0.0", constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenDiscountPriceWrongRange() throws Exception {

		Set<ConstraintViolation<ProductDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getWrongRangeDiscountPriceProductDTO());

		assertEquals("Discount Price fromat should have 5 integer digits and 2 digits",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenRetailPriceIsNegative() throws Exception {

		Set<ConstraintViolation<ProductDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNegativeRetailPriceProductDTO());

		assertEquals("Retail Price min value should be 0.0", constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenRetailPriceWrongRange() throws Exception {

		Set<ConstraintViolation<ProductDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getWrongRangeRetailPriceProductDTO());

		assertEquals("Retail Price fromat should have 5 integer digits and 2 digits",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenTradeMarginIsNegative() throws Exception {

		Set<ConstraintViolation<ProductDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNegativeTradeMarginProductDTO());

		assertEquals("Trade margin min value should be 0.0", constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenTradeMarginWrongRange() throws Exception {

		Set<ConstraintViolation<ProductDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getWrongRangeTradeMarginProductDTO());

		assertEquals("Trade margin fromat should have 3 integer digits and 2 digits",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenMeasuringUnitDTOIsNull() throws Exception {

		Set<ConstraintViolation<ProductDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullMeasuringUnitProductDTO());

		assertEquals("MeasuringUnitDTO should not be null", constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenVatDTOIsNull() throws Exception {

		Set<ConstraintViolation<ProductDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullVatProductDTO());

		assertEquals("VatDTO should not be null", constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenBarcodesIsNull() throws Exception {

		Set<ConstraintViolation<ProductDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullBarcodesProductDTO());

		assertEquals("Barcode list should not be null", constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenProductCodeIsNull() throws Exception {

		Set<ConstraintViolation<ProductDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullProductCodeProductDTO());

		assertEquals("ProductCodeDTO should not be null", constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenStockIsNegative() throws Exception {

		Set<ConstraintViolation<ProductDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNegativeStockProductDTO());

		assertEquals("Stock min value should be 0.0", constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenStockWrongRange() throws Exception {

		Set<ConstraintViolation<ProductDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getWrongRangeStockProductDTO());

		assertEquals("Stock fromat should have 6 integer digits and 4 digits",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
}
