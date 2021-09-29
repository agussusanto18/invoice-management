package com.example.invoice.service;

import com.example.invoice.dao.InvoiceDao;
import com.example.invoice.dao.VirtualAccountDao;
import com.example.invoice.entity.*;
import com.example.invoice.exception.PaymentExceedInvoiceAmountException;
import com.example.invoice.exception.VirtualAccountAlreadyPaidException;
import com.example.invoice.exception.VirtualAccountNotFoundException;
import com.example.invoice.helper.VirtualAccountHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;


@Service
@Transactional(rollbackOn = {
        VirtualAccountAlreadyPaidException.class,
        VirtualAccountNotFoundException.class,
        PaymentExceedInvoiceAmountException.class
})
public class PaymentService {

    @Autowired
    private AuditLogService auditLogService;

    @Autowired
    private VirtualAccountDao virtualAccountDao;

    @Autowired
    private VirtualAccountHelper virtualAccountHelper;

    @Autowired
    private InvoiceDao invoiceDao;

    public void pay(PaymentProvider provider, String companyId, String accountNumber, BigDecimal amount, String reference) throws VirtualAccountNotFoundException, VirtualAccountAlreadyPaidException, PaymentExceedInvoiceAmountException {
        // auditLogService.log("Start payment VA " + accountNumber);

        // 1. Mengecek apakah VA ada
        VirtualAccount va = virtualAccountHelper.checkVaIsExist(provider, companyId, accountNumber);
        // 2. Mengecek apakah VA sudah lunas
        virtualAccountHelper.checkVaAlreadyPaid(va);
        // 3. Mengecek apakah amount pembayaran > nilai tagihan
        virtualAccountHelper.checkIsPaymentAmountExceedInvoice(amount, va);
        // 4. Update status VA menjadi lunas
        va.setStatusRecord(StatusRecord.INACTIVE);
        // 5. Update status invoice menjadi lunas
        Invoice invoice = va.getInvoice();
        invoice.setTotalPayment(invoice.getTotalPayment().add(amount));
        invoice.setPaymentStatus(PaymentStatus.FULL);
        invoiceDao.save(invoice);
    }


}
