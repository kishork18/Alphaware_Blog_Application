package com.blog.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorDetails> handleuserexception(UserException exc, WebRequest request) {
    	ErrorDetails errordetails= new ErrorDetails(LocalDateTime.now(), exc.getMessage(), request.getDescription(false));
    	return new ResponseEntity<ErrorDetails>(errordetails, HttpStatus.BAD_REQUEST);

	}
    @ExceptionHandler(CategoryException.class)
	public ResponseEntity<ErrorDetails> handleuserexception(CategoryException exc, WebRequest request) {
    	ErrorDetails errordetails= new ErrorDetails(LocalDateTime.now(), exc.getMessage(), request.getDescription(false));
    	return new ResponseEntity<ErrorDetails>(errordetails, HttpStatus.BAD_REQUEST);

	}
    @ExceptionHandler(PostException.class)
	public ResponseEntity<ErrorDetails> handleuserexception(PostException exc, WebRequest request) {
    	ErrorDetails errordetails= new ErrorDetails(LocalDateTime.now(), exc.getMessage(), request.getDescription(false));
    	return new ResponseEntity<ErrorDetails>(errordetails, HttpStatus.BAD_REQUEST);

	}
    @ExceptionHandler(CommentException.class)
	public ResponseEntity<ErrorDetails> handleuserexception(CommentException exc, WebRequest request) {
    	ErrorDetails errordetails= new ErrorDetails(LocalDateTime.now(), exc.getMessage(), request.getDescription(false));
    	return new ResponseEntity<ErrorDetails>(errordetails, HttpStatus.BAD_REQUEST);

	}
}
