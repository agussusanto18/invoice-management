package com.example.invoice.service;

import com.example.invoice.entity.Invoice;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service @Transactional
public class InvoiceService {

    public Invoice createInvoice() {
        Invoice invoice = new Invoice();
        return invoice;
    }

}
