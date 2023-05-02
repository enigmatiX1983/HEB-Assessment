package com.heb.assessment.model.receipt;

import com.heb.assessment.model.item.Item;

import java.util.List;

public class FeatureTwoReceipt {
    private float subtotal;
    private float taxTotal;
    private float grandTotal;
    private List<Item> itemsList;

    public FeatureTwoReceipt(
        float subtotal,
        float taxTotal,
        float grandTotal
    ) {
        this.subtotal = subtotal;
        this.taxTotal = taxTotal;
        this.grandTotal = grandTotal;
    }

    public float getSubTotal() {
        return subtotal;
    }

    public void setSubTotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public float getTaxTotal() {
        return taxTotal;
    }

    public void setTaxTotal(float taxTotal) {
        this.taxTotal = taxTotal;
    }

    public float getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(float grandTotal) {
        this.grandTotal = grandTotal;
    }
}
