package com.heb.assessment.model.receipt;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.heb.assessment.model.item.CartItem;
import com.heb.assessment.model.item.ReceiptItem;

import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReceiptTotals {
    private List<ReceiptItem> receiptItemList;
    private Float subtotals;
    private Float subtotalBeforeDiscounts;
    private Float discountTotal;
    private Float subtotalAfterDiscounts;
    private Float taxableSubtotal;
    private Float taxableSubtotalAfterDiscounts;
    private Float taxTotal;
    private Float grandTotal;

    public ReceiptTotals() {
    }

    //Constructor for feature 1
    public ReceiptTotals(
            List<ReceiptItem> itemsList,
            float grandTotal
    ) {
        this.receiptItemList = itemsList;
        this.grandTotal = grandTotal;
    }

    //Constructor for feature 2
    public ReceiptTotals(
            List<ReceiptItem> itemsList,
            float subtotals,
            float taxTotal,
            float grandTotal
    ) {
        this.receiptItemList = itemsList;
        this.subtotals = subtotals;
        this.taxTotal = taxTotal;
        this.grandTotal = grandTotal;
    }

    //Constructor for feature 3
    public ReceiptTotals(
            List<ReceiptItem> itemsList,
            float subtotals,
            float taxableSubtotal,
            float taxTotal,
            float grandTotal
    ) {
        this.receiptItemList = itemsList;
        this.subtotals = subtotals;
        this.taxableSubtotal = taxableSubtotal;
        this.taxTotal = taxTotal;
        this.grandTotal = grandTotal;
    }

    //Constructor for feature 4
    public ReceiptTotals(
        List<ReceiptItem> itemsList,
        float subtotalBeforeDiscounts,
        float discountTotal,
        float subtotalAfterDiscounts,
        float taxableSubtotalAfterDiscounts,
        float taxTotal,
        float grandTotal
    ) {
        this.receiptItemList = itemsList;
        this.subtotalBeforeDiscounts = subtotalBeforeDiscounts;
        this.discountTotal = discountTotal;
        this.subtotalAfterDiscounts = subtotalAfterDiscounts;
        this.taxableSubtotalAfterDiscounts = taxableSubtotalAfterDiscounts;
        this.taxTotal = taxTotal;
        this.grandTotal = grandTotal;
    }

    public List<ReceiptItem> getReceiptItemList() {
        return this.receiptItemList;
    }

    public void setReceiptItemList(List<ReceiptItem> receiptItemList) {
        this.receiptItemList = receiptItemList;
    }

    public Float getSubtotals() {
        return this.subtotals;
    }

    public void setSubtotals(Float subtotals) {
        this.subtotals = subtotals;
    }

    public Float getSubtotalBeforeDiscounts() {
        return this.subtotalBeforeDiscounts;
    }

    public void setSubtotalBeforeDiscounts(Float subtotalBeforeDiscounts) {
        this.subtotalBeforeDiscounts = subtotalBeforeDiscounts;
    }

    public Float getDiscountTotal() {
        return this.discountTotal;
    }

    public void setDiscountTotal(Float discountTotal) {
        this.discountTotal = discountTotal;
    }

    public Float getSubtotalAfterDiscounts() {
        return this.subtotalAfterDiscounts;
    }

    public void setSubtotalAfterDiscounts(Float subtotalAfterDiscounts) {
        this.subtotalAfterDiscounts = subtotalAfterDiscounts;
    }

    public Float getTaxableSubtotal() {
        return this.taxableSubtotal;
    }

    public void setTaxableSubtotal(Float taxableSubtotal) {
        this.taxableSubtotal = taxableSubtotal;
    }

    public Float getTaxableSubtotalAfterDiscounts() {
        return this.taxableSubtotalAfterDiscounts;
    }

    public void setTaxableSubtotalAfterDiscounts(Float taxableSubtotalAfterDiscounts) {
        this.taxableSubtotalAfterDiscounts = taxableSubtotalAfterDiscounts;
    }

    public Float getTaxTotal() {
        return this.taxTotal;
    }

    public void setTaxTotal(Float taxTotal) {
        this.taxTotal = taxTotal;
    }

    public Float getGrandTotal() {
        return this.grandTotal;
    }

    public void setGrandTotal(Float grandTotal) {
        this.grandTotal = grandTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReceiptTotals)) return false;
        ReceiptTotals that = (ReceiptTotals) o;
        return Objects.equals(receiptItemList, that.receiptItemList) &&
            Objects.equals(subtotals, that.subtotals) &&
            Objects.equals(subtotalBeforeDiscounts, that.subtotalBeforeDiscounts) &&
            Objects.equals(discountTotal, that.discountTotal) &&
            Objects.equals(subtotalAfterDiscounts, that.subtotalAfterDiscounts) &&
            Objects.equals(taxableSubtotal, that.taxableSubtotal) &&
            Objects.equals(taxableSubtotalAfterDiscounts, that.taxableSubtotalAfterDiscounts) &&
            Objects.equals(taxTotal, that.taxTotal) &&
            Objects.equals(grandTotal, that.grandTotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(receiptItemList, subtotals, subtotalBeforeDiscounts, discountTotal, subtotalAfterDiscounts, taxableSubtotal, taxableSubtotalAfterDiscounts, taxTotal, grandTotal);
    }
}
