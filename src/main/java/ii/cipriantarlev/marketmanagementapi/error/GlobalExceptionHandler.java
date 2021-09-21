/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.error;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
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
					.timeStamp(LocalDateTime.now()).build();
			
			log.error("Validation not passed for '{}' for field '{}' with message: {}", error.getObjectName(),
					errorResponse.getField(), errorResponse.getMessage());
			validationErrorList.add(errorResponse);
		});
		return new ResponseEntity<>(validationErrorList, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ValidationErrorResponse> handleUniqueConstraintViolation(
			DataIntegrityViolationException exception) {		
		
		var rootCauseMessage = exception.getRootCause() != null ? exception.getRootCause().getMessage() : exception.getMessage();
		
		var fieldMessage = StringUtils.substringAfterLast(rootCauseMessage, '\n').trim();
		var errorMessage = StringUtils.substringBeforeLast(rootCauseMessage, "\n").replace("\"", "'");
		
			ValidationErrorResponse errorResponse = ValidationErrorResponse.builder()
					.status(HttpStatus.BAD_REQUEST.value())
					.field(fieldMessage)
					.message(errorMessage)
					.timeStamp(LocalDateTime.now())
					.build();
			log.error("Unique constraint violation error: {}", errorResponse);

		return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
}
}
