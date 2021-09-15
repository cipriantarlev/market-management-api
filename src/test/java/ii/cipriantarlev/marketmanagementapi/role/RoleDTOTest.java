package ii.cipriantarlev.marketmanagementapi.role;

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

class RoleDTOTest {

	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Test
	void testWhenRoleIsNull() throws Exception {

		Set<ConstraintViolation<RoleDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getNullRoleRoleDTO());

		assertEquals("Role name should not be blank or null", constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenRoleIsBlank() throws Exception {

		Set<ConstraintViolation<RoleDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getBlankRoleRoleDTO());

		List<ConstraintViolation<RoleDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Role name should contain only letters, numbers and underline"));
		assertTrue(finalSet.contains("Role name should not be blank or null"));
		assertEquals(2, constraintViolationSet.size());
	}

	@Test
	void testWhenRoleIsEmpty() throws Exception {

		Set<ConstraintViolation<RoleDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getEmptyRoleRoleDTO());

		List<ConstraintViolation<RoleDTO>> constraintViolationList = new ArrayList<>();
		CollectionUtils.addAll(constraintViolationList, constraintViolationSet);

		Set<String> finalSet = new HashSet<>();
		constraintViolationList.forEach(constraintViolation -> finalSet.add(constraintViolation.getMessage()));

		assertTrue(finalSet.contains("Role name should not be blank or null"));
		assertTrue(finalSet.contains("Role name length should be between 1 and 50"));
		assertTrue(finalSet.contains("Role name should contain only letters, numbers and underline"));
		assertEquals(3, constraintViolationSet.size());
	}

	@Test
	void testWhenRoleIsInvalid() throws Exception {

		Set<ConstraintViolation<RoleDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getInvalidCharactersRoleRoleDTO());

		assertEquals("Role name should contain only letters, numbers and underline",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}

	@Test
	void testWhenRoleIsToLong() throws Exception {

		Set<ConstraintViolation<RoleDTO>> constraintViolationSet = validator
				.validate(TestDataBuilder.getToManyCharactersRoleRoleDTO());

		assertEquals("Role name length should be between 1 and 50",
				constraintViolationSet.iterator().next().getMessage());
		assertEquals(1, constraintViolationSet.size());
	}
}
