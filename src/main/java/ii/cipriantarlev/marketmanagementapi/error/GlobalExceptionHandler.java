/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.error;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<ValidationErrorResponse>> handleValidationExceptions(
			MethodArgumentNotValidException exception) {
		List<ValidationErrorResponse> validationErrorList = new ArrayList<>();

		exception.getBindingResult().getAllErrors().forEach(error -> {

			ValidationErrorResponse errorResponse = ValidationErrorResponse.builder()
					.status(HttpStatus.BAD_REQUEST.value())
					.field(((FieldError) error).getField())
					.message(error.getDefaultMessage())
					.timeStamp(LocalDateTime.now())
					.build();
			log.error("Validation not passed for '{}' for field '{}' with message: {}", error.getObjectName(),
					errorResponse.getField(), errorResponse.getMessage());
			validationErrorList.add(errorResponse);
		});
		return new ResponseEntity<>(validationErrorList, HttpStatus.BAD_REQUEST);
	}
}
