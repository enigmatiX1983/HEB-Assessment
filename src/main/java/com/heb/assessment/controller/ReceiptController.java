package com.heb.assessment.controller;

import javax.ws.rs.core.MediaType;

import com.heb.assessment.constants.Constants;
import com.heb.assessment.exception.CartException;
import com.heb.assessment.exception.ExceptionBody.CartExceptionBody;
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

    @RequestMapping(method = RequestMethod.POST, value = "calculateReceipt", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> getTest(@RequestBody ItemsList cartJson) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(this.receiptService.calculateReceipt(cartJson));
    }

    @ExceptionHandler(CartException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<CartExceptionBody> handleExceptions(CartException e){
        return ResponseEntity.status(HttpStatus.OK).body(e.getCartExceptionBody());
    }
}
