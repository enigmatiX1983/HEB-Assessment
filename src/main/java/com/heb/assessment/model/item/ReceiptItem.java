package com.heb.assessment.model.item;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReceiptItem {
    private String itemName;
    private Float price;
    private Float discount;
    private Float discountedPrice;

    public ReceiptItem(
        String itemName,
        Float price,
        Float discount,
        Float discountedPrice
    ) {
        this.itemName = itemName;
        this.price = price;
        this.discount = discount;
        this.discountedPrice = discountedPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Float getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(Float discountedPrice) {
        this.discountedPrice = discountedPrice;
    }
}
