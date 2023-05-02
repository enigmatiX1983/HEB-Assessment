package com.heb.assessment.controller;

import javax.ws.rs.core.MediaType;

import com.heb.assessment.exception.CartException;
import com.heb.assessment.exception.ExceptionBody.CartExceptionBody;
import com.heb.assessment.model.coupon.CouponsList;
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
    public ResponseEntity<?> featureOneReceipt(@RequestBody ItemsList cartJson) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(this.receiptService.calculateFeatureOneReceipt(cartJson));
    }

    @RequestMapping(method = RequestMethod.POST, value = "featureTwo", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> featureTwoReceipt(@RequestBody ItemsList cartJson) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(this.receiptService.calculateFeatureTwoReceipt(cartJson));
    }

    @RequestMapping(method = RequestMethod.POST, value = "featureThree", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> featureThreeReceipt(@RequestBody ItemsList cartJson) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(this.receiptService.calculateFeatureThreeReceipt(cartJson));
    }

    @RequestMapping(method = RequestMethod.POST, value = "featureFour", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> featureFourReceipt(@RequestBody ItemsList cartJson, @RequestBody CouponsList couponsList) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(this.receiptService.calculateFeatureFourReceipt(cartJson, couponsList));
    }

    @ExceptionHandler(CartException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<CartExceptionBody> handleExceptions(CartException e){
        return ResponseEntity.status(HttpStatus.OK).body(e.getCartExceptionBody());
    }
}
