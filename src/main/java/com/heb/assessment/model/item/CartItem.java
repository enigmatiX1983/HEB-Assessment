package com.heb.assessment.model.item;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartItem {
    private String itemName;
    private long sku;
    private boolean isTaxable;
    private boolean ownBrand;
    private float price;

    public CartItem(
        String itemName,
        long sku, boolean isTaxable,
        boolean ownBrand,
        float price
    ) {
        this.itemName = itemName;
        this.sku = sku;
        this.isTaxable = isTaxable;
        this.ownBrand = ownBrand;
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public long getSku() {
        return sku;
    }

    public void setSku(long sku) {
        this.sku = sku;
    }

    public boolean getIsTaxable() {
        return isTaxable;
    }

    public void setIsTaxable(boolean taxable) {
        isTaxable = taxable;
    }

    public boolean getIsOwnBrand() {
        return ownBrand;
    }

    public void setIsOwnBrand(boolean ownBrand) {
        this.ownBrand = ownBrand;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
