package com.heb.assessment.model.complex;

import com.heb.assessment.model.coupon.Coupon;
import com.heb.assessment.model.item.CartItem;

import java.util.List;

public class ItemsAndCoupons {
    private List<CartItem> items;
    private List<Coupon> coupons;

    public ItemsAndCoupons() {
    }

    public ItemsAndCoupons(List<CartItem> items, List<Coupon> coupons) {
        this.items = items;
        this.coupons = coupons;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }
}
