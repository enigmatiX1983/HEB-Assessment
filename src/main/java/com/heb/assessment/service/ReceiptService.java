package com.heb.assessment.service;

import com.heb.assessment.constants.Constants;
import com.heb.assessment.exception.CartException;
import com.heb.assessment.model.item.Item;
import com.heb.assessment.model.item.ItemsList;
import com.heb.assessment.model.receipt.Receipt;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class ReceiptService implements Constants {
    //Feature 1
    public Receipt calculateReceipt(ItemsList cartJson) throws CartException  {
        if (CollectionUtils.isEmpty(cartJson.getItems())) {
            throw new CartException(EMPTY_CART_ERROR_CD, EMPTY_CART_MESSAGE);
        }

        Receipt receipt = new Receipt();
        float grandTotal = 0.0F;

        for (Item item : cartJson.getItems()) {
            grandTotal += item.getPrice();
        }

        receipt.setGrandTotal(grandTotal);

        return receipt;
    }
}
