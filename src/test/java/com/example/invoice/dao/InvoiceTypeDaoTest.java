package com.example.invoice.dao;

import com.example.invoice.entity.InvoiceType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql(scripts = {"/sql/delete-all-data.sql", "/sql/insert-inactive-invoice-type.sql"})
public class InvoiceTypeDaoTest {

    @Autowired InvoiceTypeDao invoiceTypeDao;

    @Test
    public void testInsertInvoiceType() throws Exception {
        InvoiceType it = new InvoiceType();
        it.setCode("IT-001");
        it.setName("Invoice Type Test");

        Assertions.assertNull(it.getId());
        invoiceTypeDao.save(it);
        Assertions.assertNotNull(it.getId());

        System.out.println("***** Create Invoice Type *****");
        System.out.println("ID : " + it.getId());
        System.out.println("Created : " + it.getCreated());
        System.out.println("Updated : " + it.getUpdated());

        Assertions.assertEquals(it.getCreated(), it.getUpdated());

        Thread.sleep(1000);
        it.setName("IT-001-UPDATE");
        it = invoiceTypeDao.save(it);

        Assertions.assertNotEquals(it.getCreated(), it.getUpdated());

        System.out.println("***** Update Invoice Type *****");
        System.out.println("ID : " + it.getId());
        System.out.println("Created : " + it.getCreated());
        System.out.println("Updated : " + it.getUpdated());
    }

    @Test
    public void testQuerySoftDelete() {
        Long numberOfRecords = invoiceTypeDao.count();
        System.out.println("Number of record : " + numberOfRecords);
        Assertions.assertEquals(1, numberOfRecords);
    }

    @Test
    public void testSoftDelete() {
        InvoiceType it = invoiceTypeDao.findById("test-002").get();
        invoiceTypeDao.delete(it);

        Long numberOfRecords = invoiceTypeDao.count();
        System.out.println("***** Soft Delete *****");
        System.out.println("Number of record : " + numberOfRecords);
        Assertions.assertEquals(0, numberOfRecords);
    }
}
