package com.heb.assessment.model.receipt;

import com.heb.assessment.model.item.Item;

import java.util.List;

public class FeatureOneReceipt {
    private float grandTotal;
    private List<Item> itemsList;

    public FeatureOneReceipt(float grandTotal) {
        this.grandTotal = grandTotal;
    }

    public float getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(float grandTotal) {
        this.grandTotal = grandTotal;
    }
}
