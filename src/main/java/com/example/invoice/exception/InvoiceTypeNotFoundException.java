package com.example.invoice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InvoiceTypeNotFoundException extends Exception{

    public InvoiceTypeNotFoundException() {

    }

    public InvoiceTypeNotFoundException(String message) {
        super(message);
    }

}
