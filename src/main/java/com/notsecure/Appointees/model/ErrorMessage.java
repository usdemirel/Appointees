package com.notsecure.Appointees.model;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorMessage {
   private HttpStatus status;
   private LocalDateTime timestamp;
   private String message;

public ErrorMessage() {
}

public ErrorMessage(LocalDateTime timestamp, HttpStatus status, String message) {
   this.status = status;
   this.timestamp = timestamp;
   this.message = message;
}

public HttpStatus getStatus() {
   return status;
}

public void setStatus(HttpStatus status) {
   this.status = status;
}

public LocalDateTime getTimestamp() {
   return timestamp;
}

public void setTimestamp(LocalDateTime timestamp) {
   this.timestamp = timestamp;
}

public String getMessage() {
   return message;
}

public void setMessage(String message) {
   this.message = message;
}
}
