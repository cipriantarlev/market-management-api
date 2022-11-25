/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ii.cipriantarlev.marketmanagementapi.exceptions.error.response.DTOFoundErrorResponse;
import ii.cipriantarlev.marketmanagementapi.exceptions.error.response.ErrorResponse;
import ii.cipriantarlev.marketmanagementapi.exceptions.error.response.NotFoundErrorResponse;
import ii.cipriantarlev.marketmanagementapi.exceptions.error.response.ValidationErrorResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * A Global exception handler class.
 * 
 * @author ciprian.tarlev
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * A method to catch MethodArgumentNotValidException when the request body
	 * object doesn't respect the validation constraints added to that object.
	 * 
	 * The message example: { "statusCode": 400, "message": "Document type name
	 * should contain only letters", "timeStamp": "2021-10-08T11:47:34.2024948",
	 * "field": "name" }
	 * 
	 * @param exception to be intercepted
	 * @return Response entity with a list of violated constraints.
	 */
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

	/**
	 * A method to catch DataIntegrityViolationException when it is an attempt to
	 * insert in database and new record that violate unique constraint.
	 * 
	 * The message example: { "statusCode": 400, "message": " Key (name)=(Income
	 * Invoice) already exists", "timeStamp": "2021-10-08T11:42:57.8501775" }
	 * 
	 * @param exception to be intercepted
	 * @return Response entity with a list of violated constraints.
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<List<ErrorResponse>> handleUniqueConstraintViolation(
			DataIntegrityViolationException exception) {

		var rootCauseMessage = exception.getRootCause() != null
				? StringUtils.substringAfterLast(exception.getRootCause().getMessage(), "Detail:").replace(".", "")
				: exception.getMessage();

		var errorResponse = ErrorResponse.builder()
				.statusCode(HttpStatus.BAD_REQUEST.value())
				.message(rootCauseMessage)
				.timeStamp(LocalDateTime.now()).build();

		log.error("Unique constraint violation error: {}", errorResponse);

		return new ResponseEntity<>(Collections.singletonList(errorResponse), HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * A method to catch DTONotFoundException when DTO with provided id is not found
	 * in the database
	 * 
	 * The message example: { "statusCode": 404, "message": "Barcode with 188888 not
	 * found", "timeStamp": "2021-10-08T12:35:21.4593327", "id": 188888 }
	 * 
	 * @param exception to be intercepted
	 * @return Response entity with a list of object that are not found.
	 */
	@ExceptionHandler(DTONotFoundException.class)
	public ResponseEntity<List<ErrorResponse>> handleDtoNotFoundException(
			DTONotFoundException exception) {

		var errorResponse = new NotFoundErrorResponse();
		errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorResponse.setId(exception.getLongId() > 0 ? String.valueOf(exception.getLongId()) : exception.getUUIDId().toString());
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setTimeStamp(LocalDateTime.now());

		log.error("DTO not found: {}", errorResponse);

		return new ResponseEntity<>(Collections.singletonList(errorResponse), HttpStatus.NOT_FOUND);
	}
	
	/**
	 * A method to catch DTOListNotFoundException when DTO list is not found
	 * in the database.
	 * 
	 * The message example: { "statusCode": 404, "message": "Barcode list not found",
	 *  "timeStamp": "2021-10-08T11:42:57.8501775" }
	 * 
	 * @param exception to be intercepted
	 * @return Response entity with a list of object that are not found.
	 */
	@ExceptionHandler(DTOListNotFoundException.class)
	public ResponseEntity<List<ErrorResponse>> handleDtoListNotFoundException(
			DTOListNotFoundException exception) {

		var errorResponse = ErrorResponse.builder()
				.statusCode(HttpStatus.NOT_FOUND.value())
				.message(exception.getMessage())
				.timeStamp(LocalDateTime.now()).build();

		log.error("DTO not found: {}", errorResponse);

		return new ResponseEntity<>(Collections.singletonList(errorResponse), HttpStatus.NOT_FOUND);
	}
	
	/**
	 * A method to catch DTOFoundWhenSaveException when DTO with provided id is found
	 * in the database during saving.
	 * 
	 * The message example: { "statusCode": 400, "message": "Category with id: '23'
	 * already exists in database. Please use update in order to save the changes in database", 
	 * "timeStamp": "2021-10-08T12:35:21.4593327", "id": 188888 }
	 * 
	 * @param exception to be intercepted
	 * @return Response entity with a list of object that are not found.
	 */
	@ExceptionHandler(DTOFoundWhenSaveException.class)
	public ResponseEntity<List<ErrorResponse>> handleDtoFoundException(
			DTOFoundWhenSaveException exception) {

		var errorResponse = new DTOFoundErrorResponse();
		errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
		errorResponse.setId(exception.getLongId() > 0 ? String.valueOf(exception.getLongId()) : exception.getUUIDId().toString());
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setTimeStamp(LocalDateTime.now());

		log.error("Found DTO during saving: {}", errorResponse);

		return new ResponseEntity<>(Collections.singletonList(errorResponse), HttpStatus.NOT_FOUND);
	}

	/**
	 * A method to catch PriceLabelGenerationException when a price label generation
	 * has thrown and IOException.
	 *
	 * The message example: { "statusCode": 400, "message": "Unable to generate price label. Please try again.",
	 * "timeStamp": "2021-10-08T11:42:57.8501775" }
	 *
	 * @param exception to be intercepted
	 * @return Response entity with a list of violated constraints.
	 */
	@ExceptionHandler(PriceLabelGenerationException.class)
	public ResponseEntity<List<ErrorResponse>> handlePriceLabelGenerationException(
			PriceLabelGenerationException exception) {

		var errorResponse = new ErrorResponse();
		errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setTimeStamp(LocalDateTime.now());

		log.error("Unable to generate price label: {}", errorResponse);

		return new ResponseEntity<>(Collections.singletonList(errorResponse), HttpStatus.BAD_REQUEST);
	}
}