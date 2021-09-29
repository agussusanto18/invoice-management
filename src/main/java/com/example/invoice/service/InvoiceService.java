package com.example.invoice.service;

import com.example.invoice.dao.InvoiceDao;
import com.example.invoice.dao.InvoiceTypeDao;
import com.example.invoice.dao.VirtualAccountDao;
import com.example.invoice.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@Transactional
public class InvoiceService {

    @Autowired private InvoiceDao invoiceDao;
    @Autowired private InvoiceTypeDao invoiceTypeDao;
    @Autowired private VirtualAccountDao virtualAccountDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceService.class);

    public Invoice createInvoice(Customer customer, InvoiceType invoiceType, String description, BigDecimal amount) {
        InvoiceType it = invoiceTypeDao.findById(invoiceType.getId())
                .orElseThrow(() -> new IllegalStateException("Invoice type " +invoiceType.getId() + "is not found"));

        Invoice invoice = new Invoice();
        invoice.setCustomer(customer);
        invoice.setInvoiceType(it);
        invoice.setDescription(description);
        invoice.setAmount(amount);
        invoice.setDueDate(LocalDateTime.now().plusMonths(1));

        invoiceDao.save(invoice);

        LOGGER.info("[INVOICE] - [CREATED] : {}|{}|{}",
                invoice.getInvoiceType().getCode(),
                invoice.getInvoiceNumber(),
                invoice.getCustomer().getCode());

        for (VirtualAccountConfiguration vaConfig : it.getVirtualAccountConfigurations()) {
            VirtualAccount va = new VirtualAccount();
        }

        return invoice;
    }

}
