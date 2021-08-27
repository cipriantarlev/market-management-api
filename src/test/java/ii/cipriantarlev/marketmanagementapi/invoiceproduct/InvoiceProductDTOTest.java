package ii.cipriantarlev.marketmanagementapi.invoiceproduct;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;

import ii.cipriantarlev.marketmanagementapi.data.TestDataBuilder;

class InvoiceProductDTOTest {

	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Test
	void testWhenInvoiceIsNull() throws Exception {

		Set<ConstraintViolation<InvoiceProductDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getNullInvoiceDTO());

		assertEquals("Invoice DTO should not be null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
	
	@Test
	void testWhenProductIsNull() throws Exception {

		Set<ConstraintViolation<InvoiceProductDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getNullProductDTO());

		assertEquals("Product DTO should not be null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
	
	@Test
	void testWhenQuantityIsNull() throws Exception {

		Set<ConstraintViolation<InvoiceProductDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getNullQunatity());

		assertEquals("Quantity should not be null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
	
	@Test
	void testWhenQuantityIsNegative() throws Exception {

		Set<ConstraintViolation<InvoiceProductDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getNegativeQunatity());

		assertEquals("Quantity min value should be 0.0",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenQuantityWrongRange() throws Exception {

		Set<ConstraintViolation<InvoiceProductDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getWorngRangeQunatity());

		assertEquals("Quantity fromat should have 6 integer digits and 4 digits",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
	
	@Test
	void testWhenVatSumIsNull() throws Exception {

		Set<ConstraintViolation<InvoiceProductDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getNullVatSum());

		assertEquals("Vat sum should not be null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
	
	@Test
	void testWhenVatSumIsNegative() throws Exception {

		Set<ConstraintViolation<InvoiceProductDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getNegativeVatSumInvoiceProduct());

		assertEquals("Vat sum min value should be 0.0",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenVatSumWrongRange() throws Exception {

		Set<ConstraintViolation<InvoiceProductDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getWorngRangeVatSumInvoiceProduct());

		assertEquals("Vat sum fromat should have 6 integer digits and 2 digits",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
	
	@Test
	void testWhenTotalDiscountPriceIsNull() throws Exception {

		Set<ConstraintViolation<InvoiceProductDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getNullTotalDiscountPricem());

		assertEquals("Total Discount Price should not be null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
	
	@Test
	void testWhenTotalDiscountPriceIsNegative() throws Exception {

		Set<ConstraintViolation<InvoiceProductDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getNegativeTotalDiscountPricemInvoiceProduct());

		assertEquals("Total Discount Price min value should be 0.0",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenTotalDiscountPriceWrongRange() throws Exception {

		Set<ConstraintViolation<InvoiceProductDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getWorngRangeTotalDiscountPricemInvoiceProduct());

		assertEquals("Total Discount Price fromat should have 6 integer digits and 2 digits",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
	
	@Test
	void testWhenTotalRetailPriceIsNull() throws Exception {

		Set<ConstraintViolation<InvoiceProductDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getNullTotalRetailPricem());

		assertEquals("Total Retail Price should not be null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
	
	@Test
	void testWhenTotalRetailPriceIsNegative() throws Exception {

		Set<ConstraintViolation<InvoiceProductDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getNegativeTotalRetailPricemInvoiceProduct());

		assertEquals("Total Retail Price min value should be 0.0",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenTotalRetailPriceWrongRange() throws Exception {

		Set<ConstraintViolation<InvoiceProductDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getWorngRangeTotalRetailPricemInvoiceProduct());

		assertEquals("Total Retail Price fromat should have 6 integer digits and 2 digits",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
}
