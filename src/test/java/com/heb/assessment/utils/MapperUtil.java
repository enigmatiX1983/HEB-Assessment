package com.heb.assessment.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heb.assessment.model.complex.ItemsAndCoupons;
import com.heb.assessment.model.item.ItemsList;
import com.heb.assessment.model.receipt.ReceiptTotals;

public class MapperUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static ItemsAndCoupons deserializeItemsAndCoupons(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, ItemsAndCoupons.class);
    }

    public static ItemsList deserializeItemsList(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, ItemsList.class);
    }

    public static ReceiptTotals deserializeReceiptTotalsList(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, ReceiptTotals.class);
    }
}
