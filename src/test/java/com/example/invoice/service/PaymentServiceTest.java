package com.example.invoice.service;

import com.example.invoice.dao.VirtualAccountConfigurationDao;
import com.example.invoice.entity.VirtualAccountConfiguration;
import com.example.invoice.exception.PaymentExceedInvoiceAmountException;
import com.example.invoice.exception.VirtualAccountAlreadyPaidException;
import com.example.invoice.exception.VirtualAccountNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class PaymentServiceTest {

    @Autowired
    PaymentService paymentService;

    @Autowired
    VirtualAccountConfigurationDao vaConfigDao;

    @Test
    void testCreatePayment() throws VirtualAccountAlreadyPaidException, PaymentExceedInvoiceAmountException, VirtualAccountNotFoundException {
        VirtualAccountConfiguration vaConfig = vaConfigDao.findById("va-bni").get();

        paymentService.pay(
                vaConfig.getPaymentProvider(),
                vaConfig.getCompanyPrefix(),
                "23509899929919",
                new BigDecimal(123000.98),
                "Test Make a Payment"
        );
    }

}
