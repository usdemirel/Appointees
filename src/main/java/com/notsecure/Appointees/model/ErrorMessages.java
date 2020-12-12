package com.notsecure.Appointees.model;

public enum ErrorMessages {
   MISSING_REQUIRED_FIELD("Missing Required Field. Please see the documentation for required fields"),
   RECORD_ALREADY_EXISTS("Record already exists"),
   INTERNAL_SERVICE_ERROR("Internal Service error"),
   NO_RECORD_FOUND("Record with provided ID is not found"),
   AUTHENTICATION_FAILED("Authentication failed"),
   COULD_NOT_UPDATE_RECORD("Could not update record"),
   COULD_NOT_DELETE_RECORD("Could not delete record"),
   EMAIL_ADDRESS_NOT_VERIFIED("Email address could not be verified");


private String errorMessage;

ErrorMessages(String errorMessage) {
   this.errorMessage = errorMessage;
}

public String getErrorMessage() {
   return errorMessage;
}

public void setErrorMessage(String errorMessage) {
   this.errorMessage = errorMessage;
}
}
