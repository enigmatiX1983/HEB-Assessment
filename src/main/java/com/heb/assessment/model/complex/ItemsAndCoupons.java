package com.heb.assessment.model.complex;

import com.heb.assessment.model.coupon.Coupon;
import com.heb.assessment.model.item.CartItem;

import java.util.List;

public class ItemsAndCoupons {
    private List<CartItem> cartItems;
    private List<Coupon> coupons;

    public ItemsAndCoupons(List<CartItem> items, List<Coupon> coupons) {
        this.cartItems = items;
        this.coupons = coupons;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }
}
