package com.example.invoice.service;

import com.example.invoice.dao.CustomerDao;
import com.example.invoice.dao.InvoiceTypeDao;
import com.example.invoice.dao.VirtualAccountDao;
import com.example.invoice.entity.Customer;
import com.example.invoice.entity.Invoice;
import com.example.invoice.entity.InvoiceType;
import com.example.invoice.entity.VirtualAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;

@SpringBootTest
public class InvoiceServiceTest {

    @Autowired private InvoiceService invoiceService;
    @Autowired private CustomerDao customerDao;
    @Autowired private InvoiceTypeDao invoiceTypeDao;
    @Autowired private VirtualAccountDao virtualAccountDao;

    @Test
    @Sql(scripts = {
            "classpath:/sql/delete-all-data.sql",
            "classpath:/sql/insert-sample-data-invoice.sql"
    })
    public void createInvoice() {
        Customer customer = customerDao.findById("c001").get();
        InvoiceType register = invoiceTypeDao.findById("registrasi").get();
        BigDecimal amount = new BigDecimal(123000.98);
        String description = "Tagihan Registrasi";

        Invoice invoice = invoiceService.createInvoice(customer, register, description, amount);

        Assertions.assertNotNull(invoice);
        Assertions.assertNotNull(invoice.getInvoiceNumber());

        System.out.println("Invoice Number: " + invoice.getInvoiceNumber());

        Iterable<VirtualAccount> vaList = virtualAccountDao.findByInvoice(invoice);

        int countVa = 0;
        for (VirtualAccount va : vaList) {
            countVa++;
            System.out.println("No VA : " + va.getAccountNumber());
        }
        Assertions.assertEquals(2, countVa);
    }

}
