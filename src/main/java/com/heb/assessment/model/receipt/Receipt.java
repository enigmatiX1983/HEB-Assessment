package com.heb.assessment.model.receipt;

public class Receipt {
    private float subtotalBeforeDiscounts;
    private float discountTotal;
    private float subtotalAfterDiscounts;
    private float taxableSubtotalAfterDiscounts;
    private float taxTotal;
    private float grandTotal;

    public float getSubtotalBeforeDiscounts() {
        return subtotalBeforeDiscounts;
    }

    public void setSubtotalBeforeDiscounts(float subtotalBeforeDiscounts) {
        this.subtotalBeforeDiscounts = subtotalBeforeDiscounts;
    }

    public float getDiscountTotal() {
        return discountTotal;
    }

    public void setDiscountTotal(float discountTotal) {
        this.discountTotal = discountTotal;
    }

    public float getSubtotalAfterDiscounts() {
        return subtotalAfterDiscounts;
    }

    public void setSubtotalAfterDiscounts(float subtotalAfterDiscounts) {
        this.subtotalAfterDiscounts = subtotalAfterDiscounts;
    }

    public float getTaxableSubtotalAfterDiscounts() {
        return taxableSubtotalAfterDiscounts;
    }

    public void setTaxableSubtotalAfterDiscounts(float taxableSubtotalAfterDiscounts) {
        this.taxableSubtotalAfterDiscounts = taxableSubtotalAfterDiscounts;
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
