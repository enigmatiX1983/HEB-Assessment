package com.heb.assessment.service;

import com.heb.assessment.constants.Constants;
import com.heb.assessment.exception.CartException;
import com.heb.assessment.model.complex.ItemsAndCoupons;
import com.heb.assessment.model.coupon.Coupon;
import com.heb.assessment.model.coupon.CouponsList;
import com.heb.assessment.model.item.Item;
import com.heb.assessment.model.item.ItemsList;
import com.heb.assessment.model.receipt.FeatureFourReceipt;
import com.heb.assessment.model.receipt.FeatureOneReceipt;
import com.heb.assessment.model.receipt.FeatureThreeReceipt;
import com.heb.assessment.model.receipt.FeatureTwoReceipt;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.DecimalFormat;
import java.util.*;

@Service
public class ReceiptService implements Constants {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    /**
     * <p>Method that calculates cart subtotal by accumulating the sum of all prices</p>
     *
     * @param items
     * @return
     */
    private float calculateSubtotal(List<Item> items) {
        float subtotal = 0.0F;

        for (Item item : items) {
            subtotal += item.getPrice();
        }

        return subtotal;
    }

    /**
     * <p>Method that calculates the taxable subtotal by only summing those that are 'isTaxable'</p>
     *
     * @param items
     * @return
     */
    private float calculateTaxableSubtotal(List<Item> items) {
        float taxableSubtotal = 0.0F;

        for (Item item : items) {
            if (item.getIsTaxable()) {
                taxableSubtotal += item.getPrice();
            }
        }

        return taxableSubtotal;
    }

    /**
     * <p>Method that calculates the tax, multiplying by 0.0825</p>
     * @param subtotal
     * @return
     */
    private Float calculateTax(Float subtotal) {
        return subtotal * 0.0825F;
    }

    /**
     * <p>Method that calculates the total discount</p>
     *
     * @param couponsList
     * @param cartItemMap
     * @return
     */
    private ItemsList calculateDiscountTotal(CouponsList couponsList, Map<Long, Item> cartItemMap) {
        float couponsTotal = 0.0F;
        List<Item> tempItemsList = new ArrayList<Item>();
        Map<Long, Item> discountedCartItemMap = new HashMap<Long, Item>();
        ItemsList itemsList = new ItemsList();

        //Find the discounted items, and calculate the discount
        for (Coupon coupon : couponsList.getCoupons()) {
            Item item = cartItemMap.get(coupon.getAppliedSku());

            if (item != null) {
                item.setPrice(item.getPrice() - coupon.getDiscountPrice());

                //Replace the old item with the newly discounted version
                discountedCartItemMap.put(coupon.getAppliedSku(), item);
            }
        }

        //Build new items list from map
        for (Item item: discountedCartItemMap.values()) {
            tempItemsList.add(item);
        }

        if (!CollectionUtils.isEmpty(tempItemsList)) {
            itemsList.setItems(tempItemsList);
        }

        return itemsList;
    }

    /**
     *<p>Feature 1, Calculate the grand total of a given shopping cart.</p>
     *
     * @param itemsList
     * @return
     * @throws CartException
     */
    public FeatureOneReceipt calculateFeatureOneReceipt(ItemsList itemsList) throws CartException {
        if (CollectionUtils.isEmpty(itemsList.getItems())) {
            throw new CartException(EMPTY_CART_ERROR_CD, EMPTY_CART_MESSAGE);
        }

        return new FeatureOneReceipt(Float.parseFloat(df.format(calculateSubtotal(itemsList.getItems()))));
    }

    /**
     *<p>Feature 2, Calculate the subtotal and tax total of a given shopping cart.</p>
     *
     * @param itemsList
     * @return
     * @throws CartException
     */
    public FeatureTwoReceipt calculateFeatureTwoReceipt(ItemsList itemsList) throws CartException {
        if (CollectionUtils.isEmpty(itemsList.getItems())) {
            throw new CartException(EMPTY_CART_ERROR_CD, EMPTY_CART_MESSAGE);
        }

        Float subtotal = Float.parseFloat(df.format(calculateSubtotal(itemsList.getItems())));
        Float taxTotal = Float.parseFloat(df.format(calculateTax(subtotal)));
        Float grandTotal = Float.parseFloat(df.format(subtotal + taxTotal));

        return new FeatureTwoReceipt(
            subtotal,
            taxTotal,
            grandTotal
        );
    }

    /**
     * <p>Feature 3, Not all items are taxable.</p>
     * <p>If "isTaxable" is true then calculate tax for that item, if false then skip the tax calculation.</p>
     * @param itemsList
     * @return
     * @throws CartException
     */
    public FeatureThreeReceipt calculateFeatureThreeReceipt(ItemsList itemsList) throws CartException {
        if (CollectionUtils.isEmpty(itemsList.getItems())) {
            throw new CartException(EMPTY_CART_ERROR_CD, EMPTY_CART_MESSAGE);
        }

        float subtotal = Float.parseFloat(df.format(calculateSubtotal(itemsList.getItems())));
        float taxableSubtotal = Float.parseFloat(df.format(calculateTaxableSubtotal(itemsList.getItems())));
        float taxTotal = Float.parseFloat(df.format(calculateTax(taxableSubtotal)));
        float grandTotal = Float.parseFloat(df.format(subtotal + taxTotal));

        return new FeatureThreeReceipt(
            subtotal,
            taxableSubtotal,
            taxTotal,
            grandTotal
        );
    }

    /**
     *<p>
     *Feature 4
     *Coupons discount an item's price before tax is calculated.
     *Coupons are automatically applied to a shopping cart if the item is present.
     *Use the coupons.json as your list to reference.
     *</p>
     *
     * @param itemsAndCoupons
     * @return
     * @throws CartException
     */
    public FeatureFourReceipt calculateFeatureFourReceipt(ItemsAndCoupons itemsAndCoupons) throws CartException {
        ItemsList itemsList = new ItemsList(itemsAndCoupons.getItems());
        CouponsList couponsList = new CouponsList(itemsAndCoupons.getCoupons());

        if (CollectionUtils.isEmpty(itemsList.getItems())) {
            throw new CartException(EMPTY_CART_ERROR_CD, EMPTY_CART_MESSAGE);
        }

        //Create a map of SKUs
        Map<Long, Item> cartItemMap  = new HashMap<Long, Item>();

        for (Item item : itemsList.getItems()) {
            cartItemMap.put(item.getSku(), item);
        }

        //Create new cart as per instructions with new discounted prices
        ItemsList discountedItemsList = calculateDiscountTotal(couponsList, cartItemMap);

        float subtotalBeforeDiscounts = Float.parseFloat(df.format(calculateSubtotal(itemsList.getItems())));
        float subtotalAfterDiscounts = Float.parseFloat(df.format(calculateSubtotal(discountedItemsList.getItems())));
        float discountTotal = Float.parseFloat(df.format(subtotalBeforeDiscounts - subtotalAfterDiscounts));
        float taxableSubtotalAfterDiscounts = Float.parseFloat(df.format(calculateTaxableSubtotal(discountedItemsList.getItems())));
        float taxTotal = Float.parseFloat(df.format(calculateTax(taxableSubtotalAfterDiscounts)));
        float grandTotal = Float.parseFloat(df.format(subtotalAfterDiscounts + taxTotal));

        return new FeatureFourReceipt(
            subtotalBeforeDiscounts,
            subtotalAfterDiscounts,
            discountTotal,
            taxableSubtotalAfterDiscounts,
            taxTotal,
            grandTotal
        );

    }
}
