package com.heb.assessment.model.coupon;

public class Coupon {
    String couponName;
    long appliedSku;
    float discountPrice;

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public long getAppliedSku() {
        return appliedSku;
    }

    public void setAppliedSku(long appliedSku) {
        this.appliedSku = appliedSku;
    }

    public float getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(float discountPrice) {
        this.discountPrice = discountPrice;
    }
}
