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

import ii.cipriantarlev.marketmanagementapi.data.TestDataBuilder;

class InvoiceDTOTest {

	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Test
	void testWhenDocumentTypeIsNull() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getNullDocumentTypeInvoiceDTO());

		assertEquals("Document Type DTO should not be null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenMyOrganizationIsNull() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getNullMyOrganizationDTO());

		assertEquals("My Organization DTO should not be null", constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenVendorIsNull() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getNullVendorDTO());

		assertEquals("Vendor DTO should not be null", constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenDateCreatedIsNull() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getNullDateCreated());

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
				.validate(TestDataBuilder.getNullInVoiceNumber());

		assertEquals("Invoice number should not be blank or null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenInvoiceNumberIsBlank() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getBlankInvoiceNumber());

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
				.validate(TestDataBuilder.getEmptyInvoiceNumber());

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
				.validate(TestDataBuilder.getInvalidInvoiceNumber());

		assertEquals("Invoice number should contain only letters and numbers",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenInvoiceNumberIsToLong() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getTooLongInvoiceNumber());

		assertEquals("Invoice number length should be between 1 and 50",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenInvoiceDateIsNull() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getNullInvoiceDate());

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
				.validate(TestDataBuilder.getEmptyNote());

		assertEquals("Note should contain alphanumeric character, space, dot, comma, colons, semicolons and dash",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenNoteIsTooLong() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getTooLongNote());

		assertEquals("Note length should be between 1 and 250",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
	
	@Test
	void testWhenTotalDiscountPriceIsNegative() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getNegativeTotalDiscountPrice());

		assertEquals("Total Discount Price min value should be 0.0",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenTotalDiscountPriceWrongRange() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getWorngRangeTotalDiscountPrice());

		assertEquals("Total Discount Price fromat should have 6 integer digits and 2 digits",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenTotalRetailPriceIsNegative() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getNegativeTotalRetailPrice());

		assertEquals("Total Retail Price min value should be 0.0",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenTotalRetailPriceWrongRange() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getWorngRangeTotalRetailPrice());

		assertEquals("Total Retail Price fromat should have 6 integer digits and 2 digits",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenTotalTradeMarginIsNegative() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getNegativeTotalTradeMargin());

		assertEquals("Total Trade Margin min value should be 0.0",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenTotalTradeMarginWrongRange() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getWorngRangeTotalTradeMargin());

		assertEquals("Total Trade Margin fromat should have 6 integer digits and 2 digits",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenTradeMarginIsNegative() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getNegativeTradeMargin());

		assertEquals("Trade Margin min value should be 0.0",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenTradeMarginWrongRange() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getWorngRangeTradeMargin());

		assertEquals("Trade Margin fromat should have 4 integer digits and 2 digits",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
	
	@Test
	void testWhenVatSumIsNegative() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getNegativeVatSum());

		assertEquals("Vat sum value should be 0.0",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenVatSumWrongRange() throws Exception {

		Set<ConstraintViolation<InvoiceDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getWorngRangeVatSum());

		assertEquals("Vat sum fromat should have 6 integer digits and 2 digits",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
}
