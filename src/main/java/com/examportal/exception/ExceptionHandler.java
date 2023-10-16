package com.examportal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
@RestController
@ControllerAdvice
public class ExceptionHandler {
	private static final int PRECONDITION_FAILED = 412;
	 private static final int SERVER_ERROR = 500;

	@org.springframework.web.bind.annotation.ExceptionHandler(value=DataValidationException.class)
	public ResponseEntity<ApiError> handleDataValidationException(DataValidationException dve){

		String message = dve.getMessage();
		
		ApiError error = new ApiError(PRECONDITION_FAILED,message);
		
		return new ResponseEntity<ApiError>(error ,HttpStatus.PRECONDITION_FAILED);
		
	}
}

/*@ExceptionHandler(UserAuthenticationException.class)
   public ResponseEntity<ErrorResponseEntity> handleUserAuthenticationException(UserAuthenticationException uae) {

       String message = uae.getMessage();
       ErrorResponseEntity responseEntity = new ErrorResponseEntity(PRECONDITION_FAILED, message);
       responseEntity.setAdditionalData(uae.getAdditionalInfo());
       return new ResponseEntity<>(responseEntity, HttpStatus.PRECONDITION_FAILED);
   }*/