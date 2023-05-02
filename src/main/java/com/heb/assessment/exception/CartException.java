package com.heb.assessment.exception;

import com.heb.assessment.exception.ExceptionBody.CartExceptionBody;

public class CartException extends Exception {
    private CartExceptionBody cartExceptionBody;

    public CartException(String errorType, String errorMessage)  {
        CartExceptionBody cartExceptionBody = new CartExceptionBody();

        cartExceptionBody.setErrorType(errorType);
        cartExceptionBody.setErrorMessage(errorMessage);

        this.setCartExceptionBody(cartExceptionBody);
    }

    public void setCartExceptionBody(CartExceptionBody cartExceptionBody) {
        this.cartExceptionBody = cartExceptionBody;
    }

    public CartExceptionBody getCartExceptionBody() {
        return cartExceptionBody;
    }
}
