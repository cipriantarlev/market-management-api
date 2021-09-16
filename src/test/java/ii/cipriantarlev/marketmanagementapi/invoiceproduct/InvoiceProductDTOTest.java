/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.invoiceproduct;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;

import ii.cipriantarlev.marketmanagementapi.data.TestDataDTOBuilder;

class InvoiceProductDTOTest {

	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Test
	void testWhenInvoiceIsNull() throws Exception {

		Set<ConstraintViolation<InvoiceProductDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullInvoiceDTO());

		assertEquals("Invoice DTO should not be null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
	
	@Test
	void testWhenProductIsNull() throws Exception {

		Set<ConstraintViolation<InvoiceProductDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullProductDTO());

		assertEquals("Product DTO should not be null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
	
	@Test
	void testWhenQuantityIsNull() throws Exception {

		Set<ConstraintViolation<InvoiceProductDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullQunatity());

		assertEquals("Quantity should not be null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
	
	@Test
	void testWhenQuantityIsNegative() throws Exception {

		Set<ConstraintViolation<InvoiceProductDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNegativeQunatity());

		assertEquals("Quantity min value should be 0.0",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenQuantityWrongRange() throws Exception {

		Set<ConstraintViolation<InvoiceProductDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getWorngRangeQunatity());

		assertEquals("Quantity fromat should have 6 integer digits and 4 digits",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
	
	@Test
	void testWhenVatSumIsNull() throws Exception {

		Set<ConstraintViolation<InvoiceProductDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullVatSum());

		assertEquals("Vat sum should not be null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
	
	@Test
	void testWhenVatSumIsNegative() throws Exception {

		Set<ConstraintViolation<InvoiceProductDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNegativeVatSumInvoiceProduct());

		assertEquals("Vat sum min value should be 0.0",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenVatSumWrongRange() throws Exception {

		Set<ConstraintViolation<InvoiceProductDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getWorngRangeVatSumInvoiceProduct());

		assertEquals("Vat sum fromat should have 6 integer digits and 2 digits",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
	
	@Test
	void testWhenTotalDiscountPriceIsNull() throws Exception {

		Set<ConstraintViolation<InvoiceProductDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullTotalDiscountPricem());

		assertEquals("Total Discount Price should not be null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
	
	@Test
	void testWhenTotalDiscountPriceIsNegative() throws Exception {

		Set<ConstraintViolation<InvoiceProductDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNegativeTotalDiscountPricemInvoiceProduct());

		assertEquals("Total Discount Price min value should be 0.0",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenTotalDiscountPriceWrongRange() throws Exception {

		Set<ConstraintViolation<InvoiceProductDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getWorngRangeTotalDiscountPricemInvoiceProduct());

		assertEquals("Total Discount Price fromat should have 6 integer digits and 2 digits",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
	
	@Test
	void testWhenTotalRetailPriceIsNull() throws Exception {

		Set<ConstraintViolation<InvoiceProductDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullTotalRetailPricem());

		assertEquals("Total Retail Price should not be null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
	
	@Test
	void testWhenTotalRetailPriceIsNegative() throws Exception {

		Set<ConstraintViolation<InvoiceProductDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNegativeTotalRetailPricemInvoiceProduct());

		assertEquals("Total Retail Price min value should be 0.0",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenTotalRetailPriceWrongRange() throws Exception {

		Set<ConstraintViolation<InvoiceProductDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getWorngRangeTotalRetailPricemInvoiceProduct());

		assertEquals("Total Retail Price fromat should have 6 integer digits and 2 digits",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
}
