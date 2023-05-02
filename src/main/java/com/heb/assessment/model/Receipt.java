package com.heb.assessment.model;

public class Receipt {
    private long subtotalBeforeDiscounts;
    private long discountTotal;
    private long subtotalAfterDiscounts;
    private long taxableSubtotalAfterDiscounts;
    private long taxTotal;
    private long grandTotal;

    public long getSubtotalBeforeDiscounts() {
        return subtotalBeforeDiscounts;
    }

    public void setSubtotalBeforeDiscounts(long subtotalBeforeDiscounts) {
        this.subtotalBeforeDiscounts = subtotalBeforeDiscounts;
    }

    public long getDiscountTotal() {
        return discountTotal;
    }

    public void setDiscountTotal(long discountTotal) {
        this.discountTotal = discountTotal;
    }

    public long getSubtotalAfterDiscounts() {
        return subtotalAfterDiscounts;
    }

    public void setSubtotalAfterDiscounts(long subtotalAfterDiscounts) {
        this.subtotalAfterDiscounts = subtotalAfterDiscounts;
    }

    public long getTaxableSubtotalAfterDiscounts() {
        return taxableSubtotalAfterDiscounts;
    }

    public void setTaxableSubtotalAfterDiscounts(long taxableSubtotalAfterDiscounts) {
        this.taxableSubtotalAfterDiscounts = taxableSubtotalAfterDiscounts;
    }

    public long getTaxTotal() {
        return taxTotal;
    }

    public void setTaxTotal(long taxTotal) {
        this.taxTotal = taxTotal;
    }

    public long getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(long grandTotal) {
        this.grandTotal = grandTotal;
    }
}
