/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.myorganization;

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

class MyOrganizationDTOTest {

	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Test
	void testWhenNameIsNull() throws Exception {

		Set<ConstraintViolation<MyOrganizationDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullNameMyOrganizationDTO());

		assertEquals("My Organization name should not be blank or null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenNameIsBlank() throws Exception {

		Set<ConstraintViolation<MyOrganizationDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getBlankNameMyOrganizationDTO());

		assertEquals("My Organization name should not be blank or null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenNameIsEmpty() throws Exception {

		Set<ConstraintViolation<MyOrganizationDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getEmptyNameMyOrganizationDTO());

		List<ConstraintViolation<MyOrganizationDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("My Organization name should not be blank or null"));
		assertTrue(finalSet.contains("My Organization name length should be between 1 and 150"));
		assertTrue(finalSet.contains("My Organization name should contain only letters and numbers"));
		assertEquals(3, constraintViolationSet.size());
	}

	@Test
	void testWhenNameIsInvalid() throws Exception {

		Set<ConstraintViolation<MyOrganizationDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getInvalidCharactersNameMyOrganizationDTO());

		assertEquals("My Organization name should contain only letters and numbers",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenNameIsToLong() throws Exception {

		Set<ConstraintViolation<MyOrganizationDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getToManyCharactersNameMyOrganizationDTO());

		assertEquals("My Organization name length should be between 1 and 150",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
	
	@Test
	void testWhenBankIsNull() throws Exception {

		Set<ConstraintViolation<MyOrganizationDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullBankMyOrganizationDTO());

		assertEquals("Bank should not be blank or null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenBankIsBlank() throws Exception {

		Set<ConstraintViolation<MyOrganizationDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getBlankBankMyOrganizationDTO());

		assertEquals("Bank should not be blank or null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenBankIsEmpty() throws Exception {

		Set<ConstraintViolation<MyOrganizationDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getEmptyBankMyOrganizationDTO());

		List<ConstraintViolation<MyOrganizationDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Bank should not be blank or null"));
		assertTrue(finalSet.contains("Bank length should be between 1 and 200"));
		assertTrue(finalSet.contains("Bank should contain only letters and numbers"));
		assertEquals(3, constraintViolationSet.size());
	}

	@Test
	void testWhenBankIsInvalid() throws Exception {

		Set<ConstraintViolation<MyOrganizationDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getInvalidCharactersBankMyOrganizationDTO());

		assertEquals("Bank should contain only letters and numbers",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenBankIsToLong() throws Exception {

		Set<ConstraintViolation<MyOrganizationDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getToManyCharactersBankMyOrganizationDTO());

		assertEquals("Bank length should be between 1 and 200",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
	
	@Test
	void testWhenFiscalCodeIsNull() throws Exception {

		Set<ConstraintViolation<MyOrganizationDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullFiscalCodeMyOrganizationDTO());

		assertEquals("Fiscal Code should not be blank or null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenFiscalCodeIsBlank() throws Exception {

		Set<ConstraintViolation<MyOrganizationDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getBlankFiscalCodeMyOrganizationDTO());

		List<ConstraintViolation<MyOrganizationDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Fiscal Code should not be blank or null"));
		assertTrue(finalSet.contains("Fiscal Code should contain only numbers"));
		assertEquals(2, constraintViolationSet.size());
	}

	@Test
	void testWhenFiscalCodeIsEmpty() throws Exception {

		Set<ConstraintViolation<MyOrganizationDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getEmptyFiscalCodeMyOrganizationDTO());

		List<ConstraintViolation<MyOrganizationDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Fiscal Code should not be blank or null"));
		assertTrue(finalSet.contains("Fiscal Code length should be between 1 and 20"));
		assertTrue(finalSet.contains("Fiscal Code should contain only numbers"));
		assertEquals(3, constraintViolationSet.size());
	}

	@Test
	void testWhenFiscalCodeIsInvalid() throws Exception {

		Set<ConstraintViolation<MyOrganizationDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getInvalidCharactersFiscalCodeMyOrganizationDTO());

		assertEquals("Fiscal Code should contain only numbers",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenFiscalCodeIsToLong() throws Exception {

		Set<ConstraintViolation<MyOrganizationDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getToManyCharactersFiscalCodeMyOrganizationDTO());

		assertEquals("Fiscal Code length should be between 1 and 20",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
	
	@Test
	void testWhenBankAccountIsNull() throws Exception {

		Set<ConstraintViolation<MyOrganizationDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullBankAccountMyOrganizationDTO());

		assertEquals("Bank Account should not be blank or null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenBankAccountIsBlank() throws Exception {

		Set<ConstraintViolation<MyOrganizationDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getBlankBankAccountMyOrganizationDTO());

		List<ConstraintViolation<MyOrganizationDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Bank Account should not be blank or null"));
		assertTrue(finalSet.contains("Bank Account should contain only numbers"));
		assertEquals(2, constraintViolationSet.size());
	}

	@Test
	void testWhenBankAccountIsEmpty() throws Exception {

		Set<ConstraintViolation<MyOrganizationDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getEmptyBankAccountMyOrganizationDTO());

		List<ConstraintViolation<MyOrganizationDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Bank Account should not be blank or null"));
		assertTrue(finalSet.contains("Bank Account length should be between 1 and 50"));
		assertTrue(finalSet.contains("Bank Account should contain only numbers"));
		assertEquals(3, constraintViolationSet.size());
	}

	@Test
	void testWhenBankAccountIsInvalid() throws Exception {

		Set<ConstraintViolation<MyOrganizationDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getInvalidCharactersBankAccountMyOrganizationDTO());

		assertEquals("Bank Account should contain only numbers",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenBankAccountIsToLong() throws Exception {

		Set<ConstraintViolation<MyOrganizationDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getToManyCharactersBankAccountMyOrganizationDTO());

		assertEquals("Bank Account length should be between 1 and 50",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
	
	@Test
	void testWhenVatCodeIsNull() throws Exception {

		Set<ConstraintViolation<MyOrganizationDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullVatCodeMyOrganizationDTO());

		assertEquals("Vat Code should not be blank or null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenVatCodeIsBlank() throws Exception {

		Set<ConstraintViolation<MyOrganizationDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getBlankVatCodeMyOrganizationDTO());

		List<ConstraintViolation<MyOrganizationDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Vat Code should not be blank or null"));
		assertTrue(finalSet.contains("Vat Code should contain only numbers"));
		assertEquals(2, constraintViolationSet.size());
	}

	@Test
	void testWhenVatCodeIsEmpty() throws Exception {

		Set<ConstraintViolation<MyOrganizationDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getEmptyVatCodeMyOrganizationDTO());

		List<ConstraintViolation<MyOrganizationDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Vat Code should not be blank or null"));
		assertTrue(finalSet.contains("Vat Code length should be between 1 and 50"));
		assertTrue(finalSet.contains("Vat Code should contain only numbers"));
		assertEquals(3, constraintViolationSet.size());
	}

	@Test
	void testWhenVatCodeIsInvalid() throws Exception {

		Set<ConstraintViolation<MyOrganizationDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getInvalidCharactersVatCodeMyOrganizationDTO());

		assertEquals("Vat Code should contain only numbers",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenVatCodeIsToLong() throws Exception {

		Set<ConstraintViolation<MyOrganizationDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getToManyCharactersVatCodeMyOrganizationDTO());

		assertEquals("Vat Code length should be between 1 and 50",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
	
	@Test
	void testWhenCityIsNull() throws Exception {

		Set<ConstraintViolation<MyOrganizationDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getNullCityMyOrganizationDTO());

		assertEquals("City should not be blank or null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenCityIsBlank() throws Exception {

		Set<ConstraintViolation<MyOrganizationDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getBlankCityMyOrganizationDTO());

		assertEquals("City should not be blank or null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenCityIsEmpty() throws Exception {

		Set<ConstraintViolation<MyOrganizationDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getEmptyCityMyOrganizationDTO());

		List<ConstraintViolation<MyOrganizationDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("City should not be blank or null"));
		assertTrue(finalSet.contains("City length should be between 1 and 150"));
		assertTrue(finalSet.contains("City should contain only letters and numbers"));
		assertEquals(3, constraintViolationSet.size());
	}

	@Test
	void testWhenCityIsInvalid() throws Exception {

		Set<ConstraintViolation<MyOrganizationDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getInvalidCharactersCityMyOrganizationDTO());

		assertEquals("City should contain only letters and numbers",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenCityIsToLong() throws Exception {

		Set<ConstraintViolation<MyOrganizationDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getToManyCharactersCityMyOrganizationDTO());

		assertEquals("City length should be between 1 and 150",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
	
	@Test
	void testWhenPhoneNumberIsInvalid() throws Exception {

		Set<ConstraintViolation<MyOrganizationDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getInvalidCharactersPhoneNumberMyOrganizationDTO());

		assertEquals("Phone number should contain only dash and numbers",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenPhoneNumberIsToLong() throws Exception {

		Set<ConstraintViolation<MyOrganizationDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getToManyCharactersPhoneNumberMyOrganizationDTO());

		assertEquals("Phone number length should be between 1 and 100",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenEmailIsInvalid() throws Exception {

		Set<ConstraintViolation<MyOrganizationDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getInvalidCharactersEmailMyOrganizationDTO());

		assertEquals("Email should respect the patter: email@email.com",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenEmailIsToLong() throws Exception {

		Set<ConstraintViolation<MyOrganizationDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getToManyCharactersEmailMyOrganizationDTO());

		List<ConstraintViolation<MyOrganizationDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Email should be valid"));
		assertTrue(finalSet.contains("Email length should be between 5 and 100"));
		assertEquals(2, constraintViolationSet.size());
	}
	
	@Test
	void testWhenNoteIsInvalid() throws Exception {

		Set<ConstraintViolation<MyOrganizationDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getInvalidCharactersNoteMyOrganizationDTO());

		assertEquals("Note should contain alphanumeric character, space, dot, comma, colons, semicolons and dash",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenNoteIsToLong() throws Exception {

		Set<ConstraintViolation<MyOrganizationDTO>> constraintViolationSet = validator
				.validate(TestDataDTOBuilder.getToManyCharactersNoteMyOrganizationDTO());

		assertEquals("Note length should be between 1 and 500",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
}
