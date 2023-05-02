package com.heb.assessment.controller;

import javax.ws.rs.core.MediaType;

import com.heb.assessment.model.item.ItemsList;
import com.heb.assessment.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rc")
public class ReceiptController {
    private final ReceiptService receiptService;

    @Autowired
    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "calculateReceipt", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> getTest(@RequestBody ItemsList cartJson) {
        return ResponseEntity.status(HttpStatus.OK).body(this.receiptService.calculateReceipt(cartJson));
    }
}
