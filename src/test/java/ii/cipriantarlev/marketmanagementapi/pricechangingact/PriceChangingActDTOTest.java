package ii.cipriantarlev.marketmanagementapi.pricechangingact;

import ii.cipriantarlev.marketmanagementapi.data.TestDataDTOBuilder;
import org.junit.jupiter.api.Test;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PriceChangingActDTOTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void whenDateCreatedIsNull() throws Exception {
        List<String> finalList = validator
                .validate(TestDataDTOBuilder.getNullDateCreatedPriceChangingActDTO())
                .stream()
                .map(ConstraintViolation::getMessage)
                .toList();

        assertTrue(finalList.contains("PriceChangingAct date should not be null"));
        assertTrue(finalList.contains("PriceChangingAct date should be in the following format: yyyy-MM-dd"));
        assertEquals(2, finalList.size());
    }

    @Test
    void whenDateCreatedWrongFormat() throws Exception {
        List<String> finalList = validator
                .validate(TestDataDTOBuilder.getMyOrganizationNullPriceChangingActDTO())
                .stream()
                .map(ConstraintViolation::getMessage)
                .toList();

        assertTrue(finalList.contains("My Organization DTO should not be null"));
        assertEquals(1, finalList.size());
    }

    @Test
    void whenNegativeOldPrice() throws Exception {
        List<String> finalList = validator
                .validate(TestDataDTOBuilder.getNegativeOldPricePriceChangingActDTO())
                .stream()
                .map(ConstraintViolation::getMessage)
                .toList();

        assertTrue(finalList.contains("Old Price min value should be 0.0"));
        assertEquals(1, finalList.size());
    }

    @Test
    void whenWrongFormatOldPrice() throws Exception {
        List<String> finalList = validator
                .validate(TestDataDTOBuilder.getWrongFormatOldPricePriceChangingActDTO())
                .stream()
                .map(ConstraintViolation::getMessage)
                .toList();

        assertTrue(finalList.contains("Old Price format should have 6 integer digits and 2 digits"));
        assertEquals(1, finalList.size());
    }

    @Test
    void whenNegativeNewPrice() throws Exception {
        List<String> finalList = validator
                .validate(TestDataDTOBuilder.getNegativeNewPricePriceChangingActDTO())
                .stream()
                .map(ConstraintViolation::getMessage)
                .toList();

        assertTrue(finalList.contains("New Price min value should be 0.0"));
        assertEquals(1, finalList.size());
    }

    @Test
    void whenWrongFormatNewPrice() throws Exception {
        List<String> finalList = validator
                .validate(TestDataDTOBuilder.getWrongFormatNewPricePriceChangingActDTO())
                .stream()
                .map(ConstraintViolation::getMessage)
                .toList();

        assertTrue(finalList.contains("New Price format should have 6 integer digits and 2 digits"));
        assertEquals(1, finalList.size());
    }

    @Test
    void whenWrongFormatPriceDifference() throws Exception {
        List<String> finalList = validator
                .validate(TestDataDTOBuilder.getWrongFormatPriceDifferencePriceChangingActDTO())
                .stream()
                .map(ConstraintViolation::getMessage)
                .toList();

        assertTrue(finalList.contains("Price difference format should have 6 integer digits and 2 digits"));
        assertEquals(1, finalList.size());
    }
}