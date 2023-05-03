package com.heb.assessment.exception;

import com.heb.assessment.exception.ExceptionBody.CartExceptionBody;
import com.heb.assessment.exception.ExceptionBody.ErrorTypeAndMessage;

import java.util.List;
import java.util.Objects;

public class CartException extends Exception {
    private CartExceptionBody cartExceptionBody;

    public CartException() {
    }

    public CartException(List<ErrorTypeAndMessage> errorTypesAndMessagesList)  {
        CartExceptionBody cartExceptionBody = new CartExceptionBody(errorTypesAndMessagesList);

        this.setCartExceptionBody(cartExceptionBody);
    }

    public CartException(CartExceptionBody cartExceptionBody)  {
        this.setCartExceptionBody(cartExceptionBody);
    }

    public void setCartExceptionBody(CartExceptionBody cartExceptionBody) {
        this.cartExceptionBody = cartExceptionBody;
    }

    public CartExceptionBody getCartExceptionBody() {
        return cartExceptionBody;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartException)) return false;
        CartException that = (CartException) o;
        return Objects.equals(cartExceptionBody, that.cartExceptionBody);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartExceptionBody);
    }
}
