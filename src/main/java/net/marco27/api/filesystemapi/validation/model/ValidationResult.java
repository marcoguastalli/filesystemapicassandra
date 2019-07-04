package net.marco27.api.filesystemapi.validation.model;

import org.springframework.http.HttpStatus;

import lombok.Setter;

/** Model object for input validation
 * <p>
 * If valid returns a ValidationResult object with isValid==true, errorMessage=="", HttpStatus=OK
 * <p>
 * If NO valid returns a ValidationResult object with isValid==false, errorMessage with the error, HttpStatus accordingly to the specs */
@Setter
public class ValidationResult {

    private boolean isValid;
    private String errorMessage;
    private HttpStatus httpStatus;

    public ValidationResult(boolean isValid, String errorMessage, HttpStatus httpStatus) {
        this.isValid = isValid;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }

    public boolean isValid() {
        return isValid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String toString() {
        return "{ isValid: " + isValid +
                ", errorMessage: \"" + errorMessage + "\"" +
                ", httpStatus: " + httpStatus + "}";
    }
}
