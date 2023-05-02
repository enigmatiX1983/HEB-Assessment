package com.heb.assessment.service;

import com.heb.assessment.constants.Constants;
import com.heb.assessment.exception.CartException;
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

import java.util.*;

@Service
public class ReceiptService implements Constants {

    /**
     * <p>Method that calculates cart subtotal by accumulating the sum of all prices</p>
     *
     * @param cartJson
     * @return
     */
    private float calculateSubtotal(ItemsList cartJson) {
        float subtotal = 0.0F;

        for (Item item : cartJson.getItems()) {
            subtotal += item.getPrice();
        }

        return subtotal;
    }

    /**
     * <p>Method that calculates the taxable subtotal by only summing those that are 'isTaxable'</p>
     *
     * @param cartJson
     * @return
     */
    private float calculateTaxableSubtotal(ItemsList cartJson) {
        float taxableSubtotal = 0.0F;

        for (Item item : cartJson.getItems()) {
            if (item.isTaxable()) {
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
    private float calculateTax(float subtotal) {
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
        ItemsList itemsList = new ItemsList();

        for (Coupon coupon : couponsList.getCoupons()) {
            Item item = cartItemMap.get(coupon.getAppliedSku());
            item.setPrice(item.getPrice() - coupon.getDiscountPrice());
            tempItemsList.add(item);

            //cartItemMap
            //    .get(coupon.getAppliedSku())
            //    .setPrice(cartItemMap.get(coupon.getAppliedSku()).getPrice() - coupon.getDiscountPrice());
        }

        if (!CollectionUtils.isEmpty(tempItemsList)) {
            itemsList.setItems(tempItemsList);
        }

        return itemsList;
    }

    /**
     *<p>Feature 1, Calculate the grand total of a given shopping cart.</p>
     *
     * @param cartJson
     * @return
     * @throws CartException
     */
    public FeatureOneReceipt calculateFeatureOneReceipt(ItemsList cartJson) throws CartException {
        if (CollectionUtils.isEmpty(cartJson.getItems())) {
            throw new CartException(EMPTY_CART_ERROR_CD, EMPTY_CART_MESSAGE);
        }

        return new FeatureOneReceipt(calculateSubtotal(cartJson));
    }

    /**
     *<p>Feature 2, Calculate the subtotal and tax total of a given shopping cart.</p>
     *
     * @param cartJson
     * @return
     * @throws CartException
     */
    public FeatureTwoReceipt calculateFeatureTwoReceipt(ItemsList cartJson) throws CartException {
        if (CollectionUtils.isEmpty(cartJson.getItems())) {
            throw new CartException(EMPTY_CART_ERROR_CD, EMPTY_CART_MESSAGE);
        }

        float subtotal = calculateSubtotal(cartJson);
        float taxTotal = calculateTax(subtotal);
        float grandTotal = subtotal + taxTotal;

        return new FeatureTwoReceipt(
            subtotal,
            taxTotal,
            grandTotal
        );
    }

    /**
     * <p>Feature 3, Not all items are taxable.</p>
     * <p>If "isTaxable" is true then calculate tax for that item, if false then skip the tax calculation.</p>
     * @param cartJson
     * @return
     * @throws CartException
     */
    public FeatureThreeReceipt calculateFeatureThreeReceipt(ItemsList cartJson) throws CartException {
        if (CollectionUtils.isEmpty(cartJson.getItems())) {
            throw new CartException(EMPTY_CART_ERROR_CD, EMPTY_CART_MESSAGE);
        }

        float subtotal = calculateSubtotal(cartJson);
        float taxableSubtotal = calculateTaxableSubtotal(cartJson);
        float taxTotal = calculateTax(taxableSubtotal);
        float grandTotal = subtotal + taxTotal;

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
     * @param cartJson
     * @param couponsList
     * @return
     * @throws CartException
     */
    public FeatureFourReceipt calculateFeatureFourReceipt(ItemsList cartJson, CouponsList couponsList) throws CartException {
        if (CollectionUtils.isEmpty(cartJson.getItems())) {
            throw new CartException(EMPTY_CART_ERROR_CD, EMPTY_CART_MESSAGE);
        }

        //Create a map of SKUs
        Map<Long, Item> cartItemMap  = new HashMap<Long, Item>();

        for (Item item : cartJson.getItems()) {
            cartItemMap.put(item.getSku(), item);
        }

        //Create new cart as per instructions with new discounted prices
        ItemsList discountedItemsList = calculateDiscountTotal(couponsList, cartItemMap);

        float subtotalBeforeDiscounts = calculateSubtotal(cartJson);
        float subtotalAfterDiscounts = calculateSubtotal(discountedItemsList);
        float discountTotal = subtotalBeforeDiscounts - subtotalAfterDiscounts;
        float taxableSubtotalAfterDiscounts = calculateTaxableSubtotal(discountedItemsList);
        float taxTotal = calculateTax(taxableSubtotalAfterDiscounts);
        float grandTotal = subtotalAfterDiscounts + taxTotal;

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
