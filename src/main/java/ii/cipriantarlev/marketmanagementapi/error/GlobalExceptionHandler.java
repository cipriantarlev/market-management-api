/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.error;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

			var errorResponse = new ValidationErrorResponse();
			errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
			errorResponse.setField(((FieldError) error).getField());
			errorResponse.setMessage(error.getDefaultMessage());
			errorResponse.setTimeStamp(LocalDateTime.now());
			
			log.error("Validation not passed for '{}' for field '{}' with message: {}", error.getObjectName(),
					errorResponse.getField(), errorResponse.getMessage());
			validationErrorList.add(errorResponse);
		});
		return new ResponseEntity<>(validationErrorList, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorResponse> handleUniqueConstraintViolation(
			DataIntegrityViolationException exception) {		
		
		var rootCauseMessage = exception.getRootCause() != null
				? exception.getRootCause().getMessage().replace("\"", "'")
				: exception.getMessage();
		
		var errorResponse = ErrorResponse.builder()
					.statusCode(HttpStatus.BAD_REQUEST.value())
					.message(rootCauseMessage)
					.timeStamp(LocalDateTime.now())
					.build();
			log.error("Unique constraint violation error: {}", errorResponse);

		return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
}
}
