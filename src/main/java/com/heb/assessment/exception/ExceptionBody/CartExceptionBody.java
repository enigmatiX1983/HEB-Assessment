package com.heb.assessment.exception.ExceptionBody;

import java.util.List;
import java.util.Objects;

public class CartExceptionBody {
    private List<ErrorTypeAndMessage> errorTypesAndMessagesList;

    public CartExceptionBody() {
    }

    public CartExceptionBody(List<ErrorTypeAndMessage> errorTypesAndMessagesList) {
        this.errorTypesAndMessagesList = errorTypesAndMessagesList;
    }

    public List<ErrorTypeAndMessage> getErrorTypesAndMessagesList() {
        return errorTypesAndMessagesList;
    }

    public void setErrorTypesAndMessagesList(List<ErrorTypeAndMessage> errorTypesAndMessagesList) {
        this.errorTypesAndMessagesList = errorTypesAndMessagesList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartExceptionBody)) return false;
        CartExceptionBody that = (CartExceptionBody) o;
        return Objects.equals(errorTypesAndMessagesList, that.errorTypesAndMessagesList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorTypesAndMessagesList);
    }
}
