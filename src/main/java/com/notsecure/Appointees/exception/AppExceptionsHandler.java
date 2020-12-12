package com.notsecure.Appointees.exception;

import com.notsecure.Appointees.model.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class AppExceptionsHandler {

@ExceptionHandler(value= {Exception.class})
public ResponseEntity<Object> handleOtherExceptions(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
   System.out.println("a");
   ErrorMessage errorMessage = new ErrorMessage(LocalDateTime.now(), status, ex.getMessage());
   return ResponseEntity.status(status).body(errorMessage);
}

//@Override
//protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//      ErrorMessage errorMessage = new ErrorMessage(LocalDateTime.now(),status,ex.getMessage());
//   System.out.println("b");
//      return ResponseEntity.status(status).body(errorMessage);
//}

//@Override
//protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//   return super.handleHttpMediaTypeNotSupported(ex, headers, status, request);
//}
//
//@Override
//protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//   return super.handleMissingPathVariable(ex, headers, status, request);
//}
//
//@Override
//protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//   return super.handleTypeMismatch(ex, headers, status, request);
//}
//
//@Override
//protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//   return super.handleHttpMessageNotReadable(ex, headers, status, request);
//}
//
//@Override
//protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//   return super.handleMissingServletRequestPart(ex, headers, status, request);
//}
}
