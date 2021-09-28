package com.example.invoice.service;

import com.example.invoice.entity.Customer;
import com.example.invoice.entity.Invoice;
import com.example.invoice.entity.InvoiceType;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service @Transactional
public class InvoiceService {

    public Invoice createInvoice(Customer customer, InvoiceType invoiceType, String description, BigDecimal amount) {
        Invoice invoice = new Invoice();
        return invoice;
    }

}
