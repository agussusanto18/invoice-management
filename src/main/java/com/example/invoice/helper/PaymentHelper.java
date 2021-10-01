package com.example.invoice.helper;

import com.example.invoice.dao.InvoiceDao;
import com.example.invoice.entity.Invoice;
import com.example.invoice.entity.PaymentStatus;
import com.example.invoice.entity.VirtualAccount;
import com.example.invoice.exception.PaymentExceedInvoiceAmountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PaymentHelper {

    @Autowired
    InvoiceDao invoiceDao;

    public void checkIsPaymentAmountExceedInvoice(BigDecimal amount, VirtualAccount va) throws PaymentExceedInvoiceAmountException {
        if (va.getInvoice().getAmount().compareTo(amount) == -1) {
            throw new PaymentExceedInvoiceAmountException("Jumlah bayar melebihi jumlah tagihan !");
        }
    }

    public void updateInvoicePaymentStatus(VirtualAccount va, BigDecimal amount) {
        Invoice invoice = va.getInvoice();
        invoice.setTotalPayment(invoice.getTotalPayment().add(amount));
        invoice.setPaid(true);
        invoice.setPaymentStatus(PaymentStatus.FULL);
        invoiceDao.save(invoice);
    }

}
