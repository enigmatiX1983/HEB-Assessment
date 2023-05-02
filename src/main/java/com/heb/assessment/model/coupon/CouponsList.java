package com.heb.assessment.model.coupon;

import java.util.List;

public class CouponsList {
    private List<Coupon> coupons;

    public CouponsList() {
    }

    public CouponsList(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }
}
