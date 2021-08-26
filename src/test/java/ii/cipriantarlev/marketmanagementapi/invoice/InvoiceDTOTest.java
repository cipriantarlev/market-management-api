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
		finalSet.add(constraintViolationList.get(0).getMessage());
		finalSet.add(constraintViolationList.get(1).getMessage());

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
}
