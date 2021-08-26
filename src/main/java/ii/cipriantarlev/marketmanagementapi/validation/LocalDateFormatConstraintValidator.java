package ii.cipriantarlev.marketmanagementapi.validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LocalDateFormatConstraintValidator implements ConstraintValidator<LocalDateFormat, LocalDate> {

	private DateTimeFormatter dateFormatter;

	@Override
	public void initialize(LocalDateFormat localDateFormat) {
		dateFormatter = DateTimeFormatter.ofPattern(localDateFormat.pattern());
	}

	@Override
	public boolean isValid(LocalDate localDateValue, ConstraintValidatorContext context) {

		if (localDateValue != null) {
			try {
				LocalDate.parse(localDateValue.toString(), dateFormatter);
				return true;

			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}
}
