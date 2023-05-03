package com.heb.assessment.service;

import com.heb.assessment.constants.Constants;
import com.heb.assessment.exception.CartException;
import com.heb.assessment.exception.ExceptionBody.ErrorTypeAndMessage;
import com.heb.assessment.model.complex.ItemsAndCoupons;
import com.heb.assessment.model.coupon.Coupon;
import com.heb.assessment.model.item.CartItem;
import com.heb.assessment.model.item.ItemsList;
import com.heb.assessment.model.item.ReceiptItem;
import com.heb.assessment.model.receipt.ReceiptTotals;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.DecimalFormat;
import java.util.*;

@Service
public class ReceiptService implements Constants {
    private static final DecimalFormat df = new DecimalFormat("0.00");

    private void throwEmptyCartException() throws CartException {
        List<ErrorTypeAndMessage> errorTypeAndMessageList = new ArrayList<ErrorTypeAndMessage>();
        errorTypeAndMessageList.add(new ErrorTypeAndMessage(EMPTY_CART_ERROR_CD, EMPTY_CART_MESSAGE));

        throw new CartException(errorTypeAndMessageList);
    }

    /**
     * <p>Method that calculates cart subtotal by accumulating the sum of all prices</p>
     *
     * @param items
     * @return
     */
    private float calculateSubtotal(List<CartItem> items) {
        float subtotal = 0.0F;

        for (CartItem item : items) {
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
    private float calculateTaxableSubtotal(List<CartItem> items) {
        float taxableSubtotal = 0.0F;

        for (CartItem item : items) {
            if (item.getIsTaxable()) {
                taxableSubtotal += item.getPrice();
            }
        }

        return taxableSubtotal;
    }

    /**
     * <p>Method that calculates the tax, multiplying by 0.0825</p>
     *
     * @param subtotal
     * @return
     */
    private float calculateTax(Float subtotal) {
        return subtotal * 0.0825F;
    }

    /**
     * <p>
     * Method that calculates the total discount, using the couponsListMap as a SKU lookup
     * If there are no coupons, it will return the original list, and a customer-receipt item list
     * </p>
     *
     * @param couponsListMap
     * @param cartItemList
     * @return
     */
    private ItemsList calculateDiscountTotal(Map<Long, Coupon> couponsListMap, List<CartItem> cartItemList) throws CartException {
        ItemsList itemsList = new ItemsList();
        List<CartItem> tempCartItemsList = new ArrayList<>();
        List<ReceiptItem> tempReceiptItemsList = new ArrayList<>();
        List<ErrorTypeAndMessage> errorTypeAndMessageList = new ArrayList<ErrorTypeAndMessage>();

        //Here we loop through each cart item, and check if there is an applicable coupon
        //If so, we calculate the discounted price
        for (CartItem cartItem: cartItemList) {
            Coupon coupon = null;

            if (couponsListMap != null) {
                coupon = couponsListMap.get(cartItem.getSku());
            }

            //We allow this to accumulate so that each error'd price is caught and displayed
            //Skip adding to the lists, it's already borked anyway!
            if (coupon != null) {
                if (cartItem.getPrice() - coupon.getDiscountPrice() < 0.0F) {
                    errorTypeAndMessageList.add(
                        new ErrorTypeAndMessage(
                            FINAL_PRICE_LESS_THAN_ZERO_ERROR_CD,
                            cartItem.getItemName() + FINAL_PRICE_LESS_THAN_ZERO_MESSAGE
                        )
                    );

                    continue;
                }
            }

            //List for the cart items as received from the API
            tempCartItemsList.add(
                new CartItem(
                    cartItem.getItemName(),
                    cartItem.getSku(),
                    cartItem.getIsTaxable(),
                    cartItem.getOwnBrand(),
                    (coupon != null) ?
                          Float.parseFloat(df.format(cartItem.getPrice() - coupon.getDiscountPrice()))
                        : Float.parseFloat(df.format(cartItem.getPrice()))
                )
            );

            //List for receipt items, to display to the user
            //Doesn't have information irrelevant to the consumer, such as SKU or Taxable or "Own brand"
            //We use it for the final receipt (to make it like an actual receipt at the store)
            tempReceiptItemsList.add(
                new ReceiptItem(
                    cartItem.getItemName(),
                    cartItem.getPrice(),
                    (coupon != null) ?
                          Float.parseFloat(df.format(coupon.getDiscountPrice()))
                        : null,
                    (coupon != null) ?
                          Float.parseFloat(df.format(cartItem.getPrice() - coupon.getDiscountPrice()))
                        : null
                )
            );
        }

        //If there are any final pricing errors after discount (< 0.00), throw the exception
        if (!CollectionUtils.isEmpty(errorTypeAndMessageList)) {
            throw new CartException(errorTypeAndMessageList);
        }

        if (!CollectionUtils.isEmpty(tempCartItemsList)) {
            itemsList.setCartItems(tempCartItemsList);
        }
        if (!CollectionUtils.isEmpty(tempReceiptItemsList)) {
            itemsList.setReceiptItems(tempReceiptItemsList);
        }

        return itemsList;
    }

    /**
     * <p>Feature 1, Calculate the grand total of a given shopping cart.</p>
     *
     * @param itemsAndCoupons
     * @return
     * @throws CartException
     */
    public ReceiptTotals calculateFeatureOneReceipt(ItemsAndCoupons itemsAndCoupons) throws CartException {
        if (CollectionUtils.isEmpty(itemsAndCoupons.getItems())) {
            throwEmptyCartException();
        }

        ItemsList itemsList = calculateDiscountTotal(null, itemsAndCoupons.getItems());

        return new ReceiptTotals(
            itemsList.getReceiptItems(),
            Float.parseFloat(df.format(calculateSubtotal(itemsList.getCartItems())))
        );
    }

    /**
     * <p>Feature 2, Calculate the subtotal and tax total of a given shopping cart.</p>
     *
     * @param itemsAndCoupons
     * @return
     * @throws CartException
     */
    public ReceiptTotals calculateFeatureTwoReceipt(ItemsAndCoupons itemsAndCoupons) throws CartException {
        if (CollectionUtils.isEmpty(itemsAndCoupons.getItems())) {
            throwEmptyCartException();
        }

        float subtotal = Float.parseFloat(df.format(calculateSubtotal(itemsAndCoupons.getItems())));
        float taxTotal = Float.parseFloat(df.format(calculateTax(subtotal)));
        float grandTotal = Float.parseFloat(df.format(subtotal + taxTotal));

        ItemsList itemsList = calculateDiscountTotal(null, itemsAndCoupons.getItems());

        return new ReceiptTotals(
            itemsList.getReceiptItems(),
            subtotal,
            taxTotal,
            grandTotal
        );
    }

    /**
     * <p>Feature 3, Not all items are taxable.</p>
     * <p>If "isTaxable" is true then calculate tax for that item, if false then skip the tax calculation.</p>
     *
     * @param itemsAndCoupons
     * @return
     * @throws CartException
     */
    public ReceiptTotals calculateFeatureThreeReceipt(ItemsAndCoupons itemsAndCoupons) throws CartException {
        if (CollectionUtils.isEmpty(itemsAndCoupons.getItems())) {
            throwEmptyCartException();
        }

        float subtotal = Float.parseFloat(df.format(calculateSubtotal(itemsAndCoupons.getItems())));
        float taxableSubtotal = Float.parseFloat(df.format(calculateTaxableSubtotal(itemsAndCoupons.getItems())));
        float taxTotal = Float.parseFloat(df.format(calculateTax(taxableSubtotal)));
        float grandTotal = Float.parseFloat(df.format(subtotal + taxTotal));

        ItemsList itemsList = calculateDiscountTotal(null, itemsAndCoupons.getItems());

        return new ReceiptTotals(
            itemsList.getReceiptItems(),
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
    public ReceiptTotals calculateFeatureFourReceipt(ItemsAndCoupons itemsAndCoupons) throws CartException {
        if (CollectionUtils.isEmpty(itemsAndCoupons.getItems())) {
            throwEmptyCartException();
        }

        //Create a map of SKUs for easy/fast O(1) lookup
        Map<Long, Coupon> couponsListMap  = new HashMap<>();

        for (Coupon coupon : itemsAndCoupons.getCoupons()) {
            couponsListMap.put(coupon.getAppliedSku(), coupon);
        }

        //Create new cart as per instructions with new discounted prices
        ItemsList discountedItemsList = calculateDiscountTotal(couponsListMap, itemsAndCoupons.getItems());

        float subtotalBeforeDiscounts = Float.parseFloat(df.format(calculateSubtotal(itemsAndCoupons.getItems())));
        float subtotalAfterDiscounts = Float.parseFloat(df.format(calculateSubtotal(discountedItemsList.getCartItems())));
        float discountTotal = Float.parseFloat(df.format(subtotalBeforeDiscounts - subtotalAfterDiscounts));
        float taxableSubtotalAfterDiscounts = Float.parseFloat(df.format(calculateTaxableSubtotal(discountedItemsList.getCartItems())));
        float taxTotal = Float.parseFloat(df.format(calculateTax(taxableSubtotalAfterDiscounts)));
        float grandTotal = Float.parseFloat(df.format(subtotalAfterDiscounts + taxTotal));

        return new ReceiptTotals(
            discountedItemsList.getReceiptItems(),
            subtotalBeforeDiscounts,
            discountTotal,
            subtotalAfterDiscounts,
            taxableSubtotalAfterDiscounts,
            taxTotal,
            grandTotal
        );
    }
}
