package com.heb.assessment.exception.ExceptionBody;

import java.util.Objects;

public class ErrorTypeAndMessage {
    private String errorCd;
    private String errorMessage;

    public ErrorTypeAndMessage() {
    }

    public ErrorTypeAndMessage(String errorCd, String errorMessage) {
        this.errorCd = errorCd;
        this.errorMessage = errorMessage;
    }

    public String getErrorCd() {
        return errorCd;
    }

    public void setErrorCd(String errorType) {
        this.errorCd = errorType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ErrorTypeAndMessage)) return false;
        ErrorTypeAndMessage that = (ErrorTypeAndMessage) o;
        return Objects.equals(errorCd, that.errorCd) &&
                Objects.equals(errorMessage, that.errorMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorCd, errorMessage);
    }
}
