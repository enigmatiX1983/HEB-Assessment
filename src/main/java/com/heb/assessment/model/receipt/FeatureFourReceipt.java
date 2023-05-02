package com.heb.assessment.model.receipt;

import com.heb.assessment.model.item.Item;

import java.util.List;

public class FeatureFourReceipt {
    private float subtotalBeforeDiscounts;
    private float discountTotal;
    private float subtotalAfterDiscounts;
    private float taxableSubtotalAfterDiscounts;
    private float taxTotal;
    private float grandTotal;
    private List<Item> itemsList;

    public FeatureFourReceipt(
        float subtotalBeforeDiscounts,
        float discountTotal,
        float subtotalAfterDiscounts,
        float taxableSubtotalAfterDiscounts,
        float taxTotal, float grandTotal
    ) {
        this.subtotalBeforeDiscounts = subtotalBeforeDiscounts;
        this.discountTotal = discountTotal;
        this.subtotalAfterDiscounts = subtotalAfterDiscounts;
        this.taxableSubtotalAfterDiscounts = taxableSubtotalAfterDiscounts;
        this.taxTotal = taxTotal;
        this.grandTotal = grandTotal;
    }

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
