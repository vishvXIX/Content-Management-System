package com.example.cms.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.cms.util.ErrorStructure;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	private ErrorStructure structure;
	
	public ApplicationExceptionHandler(ErrorStructure structure) {
		super();
		this.structure = structure;
	}
	
	private ResponseEntity<Object> structure (HttpStatus status,String message,Object rootCause){
		return new ResponseEntity<Object> (Map.of(
				"status",status.value(),
				"message",message,
				"rootCause",rootCause),status);		
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		List<ObjectError> allErrors = ex.getAllErrors();
		Map<String, String> messages = new HashMap<>();
		
		allErrors.forEach(error->{
			FieldError fieldError = (FieldError)error;
			messages.put(fieldError.getField(),error.getDefaultMessage() );
		});
		
		return ResponseEntity.badRequest().body(
				structure.setStatus(HttpStatus.BAD_REQUEST.value())
				.setMessage("Invalid Input")
				.setRootCause(messages));
		
	}
	
	@ExceptionHandler (EmailAlreadyExistsException.class)
	public ResponseEntity<Object> handlerusrreNotFound(EmailAlreadyExistsException ex){
		return structure(HttpStatus.FOUND,ex.getMessage(),"Email id already exists");
	}
	
	@ExceptionHandler (InvalidEmailException.class)
	public ResponseEntity<Object> handlerusrreNotFound(InvalidEmailException ex){
		return structure(HttpStatus.FOUND,ex.getMessage(),"please enter a valid email.");
	}
	
	@ExceptionHandler (InvalidPasswordException.class)
	public ResponseEntity<Object> handlerusrreNotFound(InvalidPasswordException ex){
		return structure(HttpStatus.FOUND,ex.getMessage(),"please enter a valid password.");
	}
	
	@ExceptionHandler (UserNotFoundByIdException.class)
	public ResponseEntity<Object> handlerusrreNotFound(UserNotFoundByIdException ex){
		return structure(HttpStatus.FOUND,ex.getMessage(),"please enter a valid UserId.");
	}
	
	
	
}

