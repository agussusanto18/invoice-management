package com.example.invoice.dao;

import com.example.invoice.entity.InvoiceType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InvoiceTypeDaoTest {

    @Autowired InvoiceTypeDao invoiceTypeDao;

    @Test
    public void testInsertInvoiceType() {
        InvoiceType it = new InvoiceType();
        it.setCode("IT-001");
        it.setName("Invoice Type Test");

        Assertions.assertNull(it.getId());
        invoiceTypeDao.save(it);
        Assertions.assertNotNull(it.getId());

        System.out.println("ID : " + it.getId());
        System.out.println("Created : " + it.getCreated());
        System.out.println("Updated : " + it.getUpdated());


    }

}
