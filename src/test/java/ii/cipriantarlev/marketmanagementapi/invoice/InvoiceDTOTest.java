/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.invoice;

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

class InvoiceDTOTest {

	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Test
	void testWhenDocumentTypeIsNull() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullDocumentTypeInvoiceDTO());

		assertEquals("Document Type DTO should not be null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenMyOrganizationIsNull() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullMyOrganizationDTO());

		assertEquals("My Organization DTO should not be null", constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenVendorIsNull() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullVendorDTO());

		assertEquals("Vendor DTO should not be null", constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenDateCreatedIsNull() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullDateCreated());

		List<ConstraintViolation<InvoiceDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraint -> finalSet.add(constraint.getMessage()));

		assertTrue(finalSet.contains("Created date should not be null"));
		assertTrue(finalSet.contains("Created date should be in the following format: yyyy-MM-dd"));
		assertEquals(2, constraintViolationSet.size());
	}

	@Test
	void testWhenInvoiceNumberIsNull() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullInVoiceNumber());

		assertEquals("Invoice number should not be blank or null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenInvoiceNumberIsBlank() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getBlankInvoiceNumber());

		List<ConstraintViolation<InvoiceDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraint -> finalSet.add(constraint.getMessage()));

		assertTrue(finalSet.contains("Invoice number should not be blank or null"));
		assertTrue(finalSet.contains("Invoice number should contain only letters and numbers"));
		assertEquals(2, constraintViolationSet.size());
	}

	@Test
	void testWhenInvoiceNumberIsEmpty() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getEmptyInvoiceNumber());

		List<ConstraintViolation<InvoiceDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraint -> finalSet.add(constraint.getMessage()));

		assertTrue(finalSet.contains("Invoice number should contain only letters and numbers"));
		assertTrue(finalSet.contains("Invoice number length should be between 1 and 50"));
		assertTrue(finalSet.contains("Invoice number should not be blank or null"));
		assertEquals(3, constraintViolationSet.size());
	}

	@Test
	void testWhenInvoiceNumberIsInvalid() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getInvalidInvoiceNumber());

		assertEquals("Invoice number should contain only letters and numbers",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenInvoiceNumberIsToLong() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getTooLongInvoiceNumber());

		assertEquals("Invoice number length should be between 1 and 50",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenInvoiceDateIsNull() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullInvoiceDate());

		List<ConstraintViolation<InvoiceDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraint -> finalSet.add(constraint.getMessage()));

		assertTrue(finalSet.contains("Invoice date should not be null"));
		assertTrue(finalSet.contains("Invoice date should be in the following format: yyyy-MM-dd"));
		assertEquals(2, constraintViolationSet.size());
	}

	@Test
	void testWhenNoteIsEmpty() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getEmptyNote());

		assertEquals("Note should contain alphanumeric character, space, dot, comma, colons, semicolons and dash",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenNoteIsTooLong() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getTooLongNote());

		assertEquals("Note length should be between 1 and 250",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
	
	@Test
	void testWhenTotalDiscountPriceIsNegative() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNegativeTotalDiscountPrice());

		assertEquals("Total Discount Price min value should be 0.0",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenTotalDiscountPriceWrongRange() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getWorngRangeTotalDiscountPrice());

		assertEquals("Total Discount Price format should have 6 integer digits and 2 digits",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenTotalRetailPriceIsNegative() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNegativeTotalRetailPrice());

		assertEquals("Total Retail Price min value should be 0.0",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenTotalRetailPriceWrongRange() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getWorngRangeTotalRetailPrice());

		assertEquals("Total Retail Price format should have 6 integer digits and 2 digits",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenTotalTradeMarginIsNegative() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNegativeTotalTradeMargin());

		assertEquals("Total Trade Margin min value should be 0.0",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenTotalTradeMarginWrongRange() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getWorngRangeTotalTradeMargin());

		assertEquals("Total Trade Margin format should have 6 integer digits and 2 digits",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenTradeMarginIsNegative() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNegativeTradeMargin());

		assertEquals("Trade Margin min value should be 0.0",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenTradeMarginWrongRange() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getWorngRangeTradeMargin());

		assertEquals("Trade Margin format should have 4 integer digits and 2 digits",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
	
	@Test
	void testWhenVatSumIsNegative() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNegativeVatSum());

		assertEquals("Vat sum value should be 0.0",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenVatSumWrongRange() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getWorngRangeVatSum());

		assertEquals("Vat sum format should have 6 integer digits and 2 digits",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
}
