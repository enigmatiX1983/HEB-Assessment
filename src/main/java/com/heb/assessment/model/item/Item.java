package com.heb.assessment.model.item;

public class Item {
    private String itemName;
    private long sku;

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

    public boolean isOwnBrand() {
        return ownBrand;
    }

    public void setOwnBrand(boolean ownBrand) {
        this.ownBrand = ownBrand;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    private boolean isTaxable;
    private boolean ownBrand;
    private float price;


}
