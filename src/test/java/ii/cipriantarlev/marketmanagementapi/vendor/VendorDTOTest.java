/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.vendor;

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

class VendorDTOTest {

	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Test
	void testWhenNameIsNull() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullNameVendorDTO());

		assertEquals("Vendor name should not be blank or null", constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenNameIsBlank() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getBlankNameVendorDTO());

		assertEquals("Vendor name should not be blank or null", constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenNameIsEmpty() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getEmptyNameVendorDTO());

		List<ConstraintViolation<VendorDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Vendor name should not be blank or null"));
		assertTrue(finalSet.contains("Vendor name length should be between 1 and 200"));
		assertTrue(finalSet.contains("Vendor name should contain only letters and numbers"));
		assertEquals(3, constraintViolationSet.size());
	}

	@Test
	void testWhenNameIsInvalid() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getInvalidCharactersNameVendorDTO());

		assertEquals("Vendor name should contain only letters and numbers",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenNameIsToLong() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getToManyCharactersNameVendorDTO());

		assertEquals("Vendor name length should be between 1 and 200",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
	
	@Test
	void testWhenBankIsNull() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullBankVendorDTO());

		assertEquals("Bank should not be blank or null", constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenBankIsBlank() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getBlankBankVendorDTO());

		assertEquals("Bank should not be blank or null", constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenBankIsEmpty() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getEmptyBankVendorDTO());

		List<ConstraintViolation<VendorDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Bank should not be blank or null"));
		assertTrue(finalSet.contains("Bank length should be between 1 and 250"));
		assertTrue(finalSet.contains("Bank should contain only letters and numbers"));
		assertEquals(3, constraintViolationSet.size());
	}

	@Test
	void testWhenBankIsInvalid() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getInvalidCharactersBankVendorDTO());

		assertEquals("Bank should contain only letters and numbers",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenBankIsToLong() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getToManyCharactersBankVendorDTO());

		assertEquals("Bank length should be between 1 and 250",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenFiscalCodeIsNull() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullFiscalCodeVendorDTO());

		assertEquals("Fiscal code should not be blank or null", constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenFiscalCodeIsBlank() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getBlankFiscalCodeVendorDTO());

		List<ConstraintViolation<VendorDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Fiscal code should not be blank or null"));
		assertTrue(finalSet.contains("Fiscal code should contain only numbers"));
		assertEquals(2, constraintViolationSet.size());
	}

	@Test
	void testWhenFiscalCodeIsEmpty() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getEmptyFiscalCodeVendorDTO());

		List<ConstraintViolation<VendorDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Fiscal code should not be blank or null"));
		assertTrue(finalSet.contains("Fiscal code length should be between 1 and 100"));
		assertTrue(finalSet.contains("Fiscal code should contain only numbers"));
		assertEquals(3, constraintViolationSet.size());
	}

	@Test
	void testWhenFiscalCodeIsInvalid() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getInvalidCharactersFiscalCodeVendorDTO());

		assertEquals("Fiscal code should contain only numbers", constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenFiscalCodeIsToLong() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getToManyCharactersFiscalCodeVendorDTO());

		assertEquals("Fiscal code length should be between 1 and 100",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenBankAccountIsNull() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullBankAccountVendorDTO());

		assertEquals("Bank account should not be blank or null", constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenBankAccountIsBlank() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getBlankBankAccountVendorDTO());

		List<ConstraintViolation<VendorDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Bank account should not be blank or null"));
		assertTrue(finalSet.contains("Bank account should contain only numbers"));
		assertEquals(2, constraintViolationSet.size());
	}

	@Test
	void testWhenBankAccountIsEmpty() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getEmptyBankAccountVendorDTO());

		List<ConstraintViolation<VendorDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Bank account should not be blank or null"));
		assertTrue(finalSet.contains("Bank account length should be between 1 and 100"));
		assertTrue(finalSet.contains("Bank account should contain only numbers"));
		assertEquals(3, constraintViolationSet.size());
	}

	@Test
	void testWhenBankAccountIsInvalid() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getInvalidCharactersBankAccountVendorDTO());

		assertEquals("Bank account should contain only numbers", constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenBankAccountIsToLong() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getToManyCharactersBankAccountVendorDTO());

		assertEquals("Bank account length should be between 1 and 100",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
	
	@Test
	void testWhenCurrencyIsNull() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullCurrencyVendorDTO());

		assertEquals("Currency should not be blank or null", constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenCurrencyIsBlank() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getBlankCurrencyVendorDTO());

		List<ConstraintViolation<VendorDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Currency should not be blank or null"));
		assertTrue(finalSet.contains("Currency should contain only letters"));
		assertEquals(2, constraintViolationSet.size());
	}

	@Test
	void testWhenCurrencyIsEmpty() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getEmptyCurrencyVendorDTO());

		List<ConstraintViolation<VendorDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Currency should not be blank or null"));
		assertTrue(finalSet.contains("Currency length should be between 1 and 10"));
		assertTrue(finalSet.contains("Currency should contain only letters"));
		assertEquals(3, constraintViolationSet.size());
	}

	@Test
	void testWhenCurrencyIsInvalid() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getInvalidCharactersCurrencyVendorDTO());

		assertEquals("Currency should contain only letters",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenCurrencyIsToLong() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getToManyCharactersCurrencyVendorDTO());

		assertEquals("Currency length should be between 1 and 10",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenVatCodeIsNull() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullVatCodeVendorDTO());

		assertEquals("Vat code should not be blank or null", constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenVatCodeIsBlank() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getBlankVatCodeVendorDTO());

		List<ConstraintViolation<VendorDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Vat code should not be blank or null"));
		assertTrue(finalSet.contains("Vat code should contain only numbers"));
		assertEquals(2, constraintViolationSet.size());
	}

	@Test
	void testWhenVatCodeIsEmpty() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getEmptyVatCodeVendorDTO());

		List<ConstraintViolation<VendorDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Vat code should not be blank or null"));
		assertTrue(finalSet.contains("Vat code length should be between 1 and 50"));
		assertTrue(finalSet.contains("Vat code should contain only numbers"));
		assertEquals(3, constraintViolationSet.size());
	}

	@Test
	void testWhenVatCodeIsInvalid() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getInvalidCharactersVatCodeVendorDTO());

		assertEquals("Vat code should contain only numbers", constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenVatCodeIsToLong() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getToManyCharactersVatCodeVendorDTO());

		assertEquals("Vat code length should be between 1 and 50",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
	
	@Test
	void testWhenCityIsNull() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullCityVendorDTO());

		assertEquals("City should not be blank or null", constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenCityIsBlank() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getBlankCityVendorDTO());

		assertEquals("City should not be blank or null", constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenCityIsEmpty() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getEmptyCityVendorDTO());

		List<ConstraintViolation<VendorDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("City should not be blank or null"));
		assertTrue(finalSet.contains("City length should be between 1 and 100"));
		assertTrue(finalSet.contains("City should contain only letters and numbers"));
		assertEquals(3, constraintViolationSet.size());
	}

	@Test
	void testWhenCityIsInvalid() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getInvalidCharactersCityVendorDTO());

		assertEquals("City should contain only letters and numbers",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenCityIsToLong() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getToManyCharactersCityVendorDTO());

		assertEquals("City length should be between 1 and 100",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenRegionIsNull() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullRegionVendorDTO());

		assertEquals("Region DTO should not be null", constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenPhoneNumberIsNull() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullPhoneNumberVendorDTO());

		assertEquals("Phone number should not be blank or null", constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenPhoneNumberIsBlank() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getBlankPhoneNumberVendorDTO());

		List<ConstraintViolation<VendorDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Phone number should not be blank or null"));
		assertTrue(finalSet.contains("Phone number should contain only numbers and dash"));
		assertEquals(2, constraintViolationSet.size());
	}

	@Test
	void testWhenPhoneNumberIsEmpty() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getEmptyPhoneNumberVendorDTO());

		List<ConstraintViolation<VendorDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Phone number should not be blank or null"));
		assertTrue(finalSet.contains("Phone number length should be between 1 and 50"));
		assertTrue(finalSet.contains("Phone number should contain only numbers and dash"));
		assertEquals(3, constraintViolationSet.size());
	}

	@Test
	void testWhenPhoneNumberIsInvalid() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getInvalidCharactersPhoneNumberVendorDTO());

		assertEquals("Phone number should contain only numbers and dash", constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenPhoneNumberIsToLong() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getToManyCharactersPhoneNumberVendorDTO());

		assertEquals("Phone number length should be between 1 and 50",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenPostalCodeIsNull() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullPostalCodeVendorDTO());

		assertEquals("Postal code should not be blank or null", constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenPostalCodeIsBlank() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getBlankPostalCodeVendorDTO());

		List<ConstraintViolation<VendorDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Postal code should not be blank or null"));
		assertTrue(finalSet.contains("Postal code should contain only letters and numbers"));
		assertEquals(2, constraintViolationSet.size());
	}

	@Test
	void testWhenPostalCodeIsEmpty() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getEmptyPostalCodeVendorDTO());

		List<ConstraintViolation<VendorDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Postal code should not be blank or null"));
		assertTrue(finalSet.contains("Postal code length should be between 1 and 10"));
		assertTrue(finalSet.contains("Postal code should contain only letters and numbers"));
		assertEquals(3, constraintViolationSet.size());
	}

	@Test
	void testWhenPostalCodeIsInvalid() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getInvalidCharactersPostalCodeVendorDTO());

		assertEquals("Postal code should contain only letters and numbers",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenPostalCodeIsToLong() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getToManyCharactersPostalCodeVendorDTO());

		assertEquals("Postal code length should be between 1 and 10",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenBusinessAddressIsNull() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullBusinessAddressVendorDTO());

		assertEquals("Business address should not be blank or null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenBusinessAddressIsBlank() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getBlankBusinessAddressVendorDTO());

		assertEquals("Business address should not be blank or null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenBusinessAddressIsEmpty() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getEmptyBusinessAddressVendorDTO());

		List<ConstraintViolation<VendorDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Business address should not be blank or null"));
		assertTrue(finalSet.contains("Business address length should be between 1 and 250"));
		assertTrue(finalSet.contains("Business address should contain only letters, numbers, ', \", -, :"));
		assertEquals(3, constraintViolationSet.size());
	}

	@Test
	void testWhenBusinessAddressIsInvalid() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getInvalidCharactersBusinessAddressVendorDTO());

		assertEquals("Business address should contain only letters, numbers, ', \", -, :",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenBusinessAddressIsToLong() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getToManyCharactersBusinessAddressVendorDTO());

		assertEquals("Business address length should be between 1 and 250",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
	
	@Test
	void testWhenVendorTypeIsNull() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullVendorTypeVendorDTO());

		assertEquals("Vendor Type should not be blank or null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenVendorTypeIsBlank() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getBlankVendorTypeVendorDTO());

		assertEquals("Vendor Type should not be blank or null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenVendorTypeIsEmpty() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getEmptyVendorTypeVendorDTO());

		List<ConstraintViolation<VendorDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Vendor Type should not be blank or null"));
		assertTrue(finalSet.contains("Vendor Type length should be between 1 and 100"));
		assertTrue(finalSet.contains("Vendor Type should contain only letters and numbers"));
		assertEquals(3, constraintViolationSet.size());
	}

	@Test
	void testWhenVendorTypeIsInvalid() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getInvalidCharactersVendorTypeVendorDTO());

		assertEquals("Vendor Type should contain only letters and numbers",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenVendorTypeIsToLong() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getToManyCharactersVendorTypeVendorDTO());

		assertEquals("Vendor Type length should be between 1 and 100",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenVendorLegalTypeIsNull() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullVendorLegalTypeVendorDTO());

		assertEquals("Vendor Legal Type should not be blank or null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenVendorLegalTypeIsBlank() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getBlankVendorLegalTypeVendorDTO());

		assertEquals("Vendor Legal Type should not be blank or null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenVendorLegalTypeIsEmpty() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getEmptyVendorLegalTypeVendorDTO());

		List<ConstraintViolation<VendorDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Vendor Legal Type should not be blank or null"));
		assertTrue(finalSet.contains("Vendor Legal Type length should be between 1 and 150"));
		assertTrue(finalSet.contains("Vendor Legal Type should contain only letters and numbers"));
		assertEquals(3, constraintViolationSet.size());
	}

	@Test
	void testWhenVendorLegalTypeIsInvalid() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getInvalidCharactersVendorLegalTypeVendorDTO());

		assertEquals("Vendor Legal Type should contain only letters and numbers",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenVendorLegalTypeIsToLong() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getToManyCharactersVendorLegalTypeVendorDTO());

		assertEquals("Vendor Legal Type length should be between 1 and 150",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
	
	@Test
	void testWhenNoteIsInvalid() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getInvalidCharactersNoteVendorDTO());

		assertEquals("Note should contain alphanumeric character, space, dot, comma, colons, semicolons and dash",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenNoteIsToLong() throws Exception {

		Set<ConstraintViolation<VendorDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getToManyCharactersNoteVendorDTO());

		assertEquals("Note length should be between 1 and 250",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
}
