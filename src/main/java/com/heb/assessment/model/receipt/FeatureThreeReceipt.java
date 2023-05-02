package com.heb.assessment.model.receipt;

public class FeatureThreeReceipt {
    private float subtotal;
    private float taxableSubtotal;
    private float taxTotal;
    private float grandTotal;

    public FeatureThreeReceipt(
        float subtotal,
        float taxableSubtotal,
        float taxTotal,
        float grandTotal
    ) {
        this.subtotal = subtotal;
        this.taxableSubtotal = taxableSubtotal;
        this.taxTotal = taxTotal;
        this.grandTotal = grandTotal;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public float getTaxableSubtotal() {
        return taxableSubtotal;
    }

    public void setTaxableSubtotal(float taxableSubtotal) {
        this.taxableSubtotal = taxableSubtotal;
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
