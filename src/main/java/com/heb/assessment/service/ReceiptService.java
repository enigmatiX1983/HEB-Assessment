package com.heb.assessment.service;

import com.heb.assessment.model.item.Item;
import com.heb.assessment.model.item.ItemsList;
import com.heb.assessment.model.receipt.Receipt;
import org.springframework.stereotype.Service;

@Service
public class ReceiptService {
    //Feature 1
    public Receipt calculateReceipt(ItemsList cartJson) {
        if (cartJson == null) {

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
