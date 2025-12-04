package br.com.aegis.stock.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(
			ResourceNotFoundException resourceException, 
			WebRequest request) {
		
		ErrorDetails errorDetails = new ErrorDetails(
					LocalDateTime.now(),
					resourceException.getMessage(),
					request.getDescription(false));
		
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<ErrorDetails> handleGlobalException(
			Exception exception, 
			WebRequest request) {
		
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(),
				exception.getMessage(),
				request.getDescription(false));
		
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<ErrorDetails> handleInsufficientStockExceptionHandler(
			InsufficientStockException stockException,
			WebRequest request) {
		
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(),
				stockException.getMessage(),
				request.getDescription(false));
	
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
}