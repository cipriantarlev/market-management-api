package ii.cipriantarlev.marketmanagementapi.user;

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

class UserDTOTest {

	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Test
	void testWhenUsernameIsNull() throws Exception {

		Set<ConstraintViolation<UserDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getNullUsernameUserDTO());

		assertEquals("Username should not be blank or null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenUsernameIsBlank() throws Exception {

		Set<ConstraintViolation<UserDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getBlankUsernameUserDTO());

		List<ConstraintViolation<UserDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Username should not be blank or null"));
		assertTrue(finalSet.contains("Username should contain only letters and numbers"));
		assertEquals(2, constraintViolationSet.size());
	}

	@Test
	void testWhenUsernameIsEmpty() throws Exception {

		Set<ConstraintViolation<UserDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getEmptyUsernameUserDTO());

		List<ConstraintViolation<UserDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Username should not be blank or null"));
		assertTrue(finalSet.contains("Username length should be between 1 and 100"));
		assertTrue(finalSet.contains("Username should contain only letters and numbers"));
		assertEquals(3, constraintViolationSet.size());
	}

	@Test
	void testWhenUsernameIsInvalid() throws Exception {

		Set<ConstraintViolation<UserDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getInvalidCharactersUsernameUserDTO());

		assertEquals("Username should contain only letters and numbers",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenUsernameIsToLong() throws Exception {

		Set<ConstraintViolation<UserDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getToManyCharactersUsernameUserDTO());

		assertEquals("Username length should be between 1 and 100",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
	
	@Test
	void testWhenPasswordIsNull() throws Exception {

		Set<ConstraintViolation<UserDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getNullPasswordUserDTO());

		assertEquals("Password should not be blank or null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenPasswordIsBlank() throws Exception {

		Set<ConstraintViolation<UserDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getBlankPasswordUserDTO());


		assertEquals("Password should not be blank or null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenPasswordIsEmpty() throws Exception {

		Set<ConstraintViolation<UserDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getEmptyPasswordUserDTO());

		List<ConstraintViolation<UserDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Password should not be blank or null"));
		assertTrue(finalSet.contains("Password length should be between 1 and 100"));
		assertEquals(2, constraintViolationSet.size());
	}

	@Test
	void testWhenPasswordIsToLong() throws Exception {

		Set<ConstraintViolation<UserDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getToManyCharactersPasswordUserDTO());

		assertEquals("Password length should be between 1 and 100",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
	
	@Test
	void testWhenRolesIsNull() throws Exception {

		Set<ConstraintViolation<UserDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getNullRolesUserDTO());

		assertEquals("Roles should not be null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
	
	@Test
	void testWhenEmailIsNull() throws Exception {

		Set<ConstraintViolation<UserDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getNullEmailUserDTO());

		assertEquals("Email should not be blank or null",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenEmailIsBlank() throws Exception {

		Set<ConstraintViolation<UserDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getBlankEmailUserDTO());

		List<ConstraintViolation<UserDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Email should be valid"));
		assertTrue(finalSet.contains("Email length should be between 5 and 100"));
		assertTrue(finalSet.contains("Email should respect the patter: email@email.com"));
		assertTrue(finalSet.contains("Email should not be blank or null"));
		assertEquals(4, constraintViolationSet.size());
	}

	@Test
	void testWhenEmailIsEmpty() throws Exception {

		Set<ConstraintViolation<UserDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getEmptyEmailUserDTO());

		List<ConstraintViolation<UserDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Email should not be blank or null"));
		assertTrue(finalSet.contains("Email length should be between 5 and 100"));
		assertTrue(finalSet.contains("Email should respect the patter: email@email.com"));
		assertEquals(3, constraintViolationSet.size());
	}
	
	@Test
	void testWhenEmailIsInvalid() throws Exception {

		Set<ConstraintViolation<UserDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getInvalidCharactersEmailUserDTO());

		assertEquals("Email should respect the patter: email@email.com",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenEmailIsToLong() throws Exception {

		Set<ConstraintViolation<UserDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getToManyCharactersEmailUserDTO());

		List<ConstraintViolation<UserDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Email should be valid"));
		assertTrue(finalSet.contains("Email length should be between 5 and 100"));
		assertTrue(finalSet.contains("Email should respect the patter: email@email.com"));
		assertEquals(3, constraintViolationSet.size());
	}
}
