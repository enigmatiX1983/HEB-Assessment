package com.heb.assessment.exception.ExceptionBody;

import java.util.List;

public class CartExceptionBody {
    private List<ErrorTypeAndMessage> errorTypesAndMessagesList;

    public CartExceptionBody(List<ErrorTypeAndMessage> errorTypesAndMessagesList) {
        this.errorTypesAndMessagesList = errorTypesAndMessagesList;
    }

    public List<ErrorTypeAndMessage> getErrorTypesAndMessagesList() {
        return errorTypesAndMessagesList;
    }

    public void setErrorTypesAndMessagesList(List<ErrorTypeAndMessage> errorTypesAndMessagesList) {
        this.errorTypesAndMessagesList = errorTypesAndMessagesList;
    }
}
