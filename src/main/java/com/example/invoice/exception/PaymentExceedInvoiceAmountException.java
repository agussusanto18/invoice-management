package com.example.invoice.exception;

public class PaymentExceedInvoiceAmountException extends Exception{

    public PaymentExceedInvoiceAmountException() {

    }

    public PaymentExceedInvoiceAmountException(String message) {
        super(message);
    }
}
