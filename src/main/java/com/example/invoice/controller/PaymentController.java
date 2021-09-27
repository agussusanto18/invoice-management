package com.example.invoice.controller;

import com.example.invoice.exception.PaymentExceedInvoiceAmountException;
import com.example.invoice.exception.VirtualAccountAlreadyPaidException;
import com.example.invoice.exception.VirtualAccountNotFoundException;
import com.example.invoice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void pay() throws VirtualAccountAlreadyPaidException, VirtualAccountNotFoundException, PaymentExceedInvoiceAmountException {
        paymentService.pay(null, null, null, null, null);
    }

}
