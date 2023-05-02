package com.heb.assessment.model.complex;

import com.heb.assessment.model.coupon.Coupon;
import com.heb.assessment.model.coupon.CouponsList;
import com.heb.assessment.model.item.Item;
import com.heb.assessment.model.item.ItemsList;

import java.util.List;

public class ItemsAndCoupons {
    private List<Item> items;
    private List<Coupon> coupons;

    public ItemsAndCoupons(List<Item> items, List<Coupon> coupons) {
        this.items = items;
        this.coupons = coupons;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }
}
