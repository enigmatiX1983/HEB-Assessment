package com.heb.assessment.controller;

import javax.ws.rs.core.MediaType;

import com.heb.assessment.exception.CartException;
import com.heb.assessment.exception.ExceptionBody.CartExceptionBody;
import com.heb.assessment.model.complex.ItemsAndCoupons;
import com.heb.assessment.model.item.ItemsList;
import com.heb.assessment.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ControllerAdvice
@RequestMapping("/rc")
public class ReceiptController  {
    private final ReceiptService receiptService;

    @Autowired
    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "featureOne", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> featureOneReceipt(@RequestBody ItemsAndCoupons itemsAndCoupons) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(this.receiptService.calculateFeatureOneReceipt(itemsAndCoupons));
    }

    @RequestMapping(method = RequestMethod.POST, value = "featureTwo", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> featureTwoReceipt(@RequestBody ItemsAndCoupons itemsAndCoupons) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(this.receiptService.calculateFeatureTwoReceipt(itemsAndCoupons));
    }

    @RequestMapping(method = RequestMethod.POST, value = "featureThree", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> featureThreeReceipt(@RequestBody ItemsAndCoupons itemsAndCoupons) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(this.receiptService.calculateFeatureThreeReceipt(itemsAndCoupons));
    }

    @RequestMapping(method = RequestMethod.POST, value = "featureFour", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> featureFourReceipt(@RequestBody ItemsAndCoupons itemsAndCoupons) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(this.receiptService.calculateFeatureFourReceipt(itemsAndCoupons));
    }

    @ExceptionHandler(CartException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<CartExceptionBody> handleExceptions(CartException e){
        return ResponseEntity.status(HttpStatus.OK).body(e.getCartExceptionBody());
    }
}
