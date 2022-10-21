package ii.cipriantarlev.marketmanagementapi.pricechangingactproduct;

import ii.cipriantarlev.marketmanagementapi.data.TestDataDTOBuilder;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PriceChangingActProductDTOTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void whenPriceChangingActDTOIsNull() {
        List<String> finalList = validator
                .validate(TestDataDTOBuilder.getNullProductPriceChangingActProduct())
                .stream()
                .map(ConstraintViolation::getMessage)
                .toList();

        assertTrue(finalList.contains("Price Changing Act should not be null"));
        assertEquals(1, finalList.size());
    }

    @Test
    void whenProductDTOForListIsNull() {
        List<String> finalList = validator
                .validate(TestDataDTOBuilder.getNullProductDTOPriceChangingActProductDTO())
                .stream()
                .map(ConstraintViolation::getMessage)
                .toList();

        assertTrue(finalList.contains("Product should not be null"));
        assertEquals(1, finalList.size());
    }

    @Test
    void whenNegativeOldPrice() throws Exception {
        List<String> finalList = validator
                .validate(TestDataDTOBuilder.getNegativeOldPriceDTOPriceChangingActProductDTO())
                .stream()
                .map(ConstraintViolation::getMessage)
                .toList();

        assertTrue(finalList.contains("Old Price min value should be 0.0"));
        assertEquals(1, finalList.size());
    }

    @Test
    void whenWrongFormatOldPrice() throws Exception {
        List<String> finalList = validator
                .validate(TestDataDTOBuilder.getWrongFormatOldPriceDTOPriceChangingActProductDTO())
                .stream()
                .map(ConstraintViolation::getMessage)
                .toList();

        assertTrue(finalList.contains("Old Price format should have 6 integer digits and 2 digits"));
        assertEquals(1, finalList.size());
    }

    @Test
    void whenWrongFormatPriceDifference() throws Exception {
        List<String> finalList = validator
                .validate(TestDataDTOBuilder.getWrongFormatPriceDifferenceDTOPriceChangingActProductDTO())
                .stream()
                .map(ConstraintViolation::getMessage)
                .toList();

        assertTrue(finalList.contains("Price difference format should have 6 integer digits and 2 digits"));
        assertEquals(1, finalList.size());
    }
}