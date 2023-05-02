package com.heb.assessment.model.receipt;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.heb.assessment.model.item.Item;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReceiptTotals {
    private List<Item> itemsList;
    private Float subtotals;
    private Float subtotalBeforeDiscounts;
    private Float discountTotal;
    private Float subtotalAfterDiscounts;
    private Float taxableSubtotal;
    private Float taxableSubtotalAfterDiscounts;
    private Float taxTotal;
    private Float grandTotal;

    //Constructor for feature 1
    public ReceiptTotals(
            List<Item> itemsList,
            float grandTotal
    ) {
        this.itemsList = itemsList;
        this.grandTotal = grandTotal;
    }

    //Constructor for feature 2
    public ReceiptTotals(
            List<Item> itemsList,
            float subtotals,
            float taxTotal,
            float grandTotal
    ) {
        this.itemsList = itemsList;
        this.subtotals = subtotals;
        this.taxTotal = taxTotal;
        this.grandTotal = grandTotal;
    }

    //Constructor for feature 3
    public ReceiptTotals(
            List<Item> itemsList,
            float subtotals,
            float taxableSubtotal,
            float taxTotal,
            float grandTotal
    ) {
        this.itemsList = itemsList;
        this.subtotals = subtotals;
        this.taxableSubtotal = taxableSubtotal;
        this.taxTotal = taxTotal;
        this.grandTotal = grandTotal;
    }

    //Constructor for feature 4
    public ReceiptTotals(
        List<Item> itemsList,
        float subtotalBeforeDiscounts,
        float discountTotal,
        float subtotalAfterDiscounts,
        float taxableSubtotalAfterDiscounts,
        float taxTotal,
        float grandTotal
    ) {
        this.itemsList = itemsList;
        this.subtotalBeforeDiscounts = subtotalBeforeDiscounts;
        this.discountTotal = discountTotal;
        this.subtotalAfterDiscounts = subtotalAfterDiscounts;
        this.taxableSubtotalAfterDiscounts = taxableSubtotalAfterDiscounts;
        this.taxTotal = taxTotal;
        this.grandTotal = grandTotal;
    }

    public List<Item> getItemsList() {
        return this.itemsList;
    }

    public void setItemsList(List<Item> itemsList) {
        this.itemsList = itemsList;
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
}
