/**
 * 
 */
package ii.cipriantarlev.marketmanagementapi.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = LocalDateFormatConstraintValidator.class)
@Documented
@Retention(RUNTIME)
@Target(FIELD)
/**
 * This annotation should check if the provided LocalDate object respects the
 * given pattern
 * 
 * @author ciprian.tarlev
 */
public @interface LocalDateFormat {

	public String pattern() default "yyyy-MM-dd";

	public String message() default "Don't respect the pattern";

	public Class<?>[] groups() default {};

	public Class<? extends Payload>[] payload() default {};
}
