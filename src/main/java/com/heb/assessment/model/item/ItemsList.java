package com.heb.assessment.model.item;

import java.util.List;

public class ItemsList {
    private List<CartItem> cartItems;
    private List<ReceiptItem> receiptItems;

    public ItemsList() {
    }

    public ItemsList(
        List<CartItem> cartItems,
        List<ReceiptItem> receiptItems
    ) {
        this.cartItems = cartItems;
        this.receiptItems = receiptItems;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> items) {
        this.cartItems = items;
    }

    public List<ReceiptItem> getReceiptItems() {
        return receiptItems;
    }

    public void setReceiptItems(List<ReceiptItem> receiptItems) {
        this.receiptItems = receiptItems;
    }
}
