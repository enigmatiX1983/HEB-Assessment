package com.heb.assessment.exception;

import com.heb.assessment.exception.ExceptionBody.CartExceptionBody;
import com.heb.assessment.exception.ExceptionBody.ErrorTypeAndMessage;

import java.util.List;

public class CartException extends Exception {
    private CartExceptionBody cartExceptionBody;

    public CartException(List<ErrorTypeAndMessage> errorTypesAndMessagesList)  {
        CartExceptionBody cartExceptionBody = new CartExceptionBody(errorTypesAndMessagesList);

        this.setCartExceptionBody(cartExceptionBody);
    }

    public void setCartExceptionBody(CartExceptionBody cartExceptionBody) {
        this.cartExceptionBody = cartExceptionBody;
    }

    public CartExceptionBody getCartExceptionBody() {
        return cartExceptionBody;
    }
}
