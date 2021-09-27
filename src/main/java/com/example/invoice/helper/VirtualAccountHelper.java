package com.example.invoice.helper;

import com.example.invoice.dao.VirtualAccountDao;
import com.example.invoice.entity.Invoice;
import com.example.invoice.entity.PaymentProvider;
import com.example.invoice.entity.VirtualAccount;
import com.example.invoice.exception.PaymentExceedInvoiceAmountException;
import com.example.invoice.exception.VirtualAccountAlreadyPaidException;
import com.example.invoice.exception.VirtualAccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class VirtualAccountHelper {

    @Autowired
    private VirtualAccountDao virtualAccountDao;

    public VirtualAccount checkVaIsExist(PaymentProvider provider, String companyId, String accountNumber) throws VirtualAccountNotFoundException {
        Optional<VirtualAccount> opVa = virtualAccountDao.findByPaymentProviderAndCompanyIdAndAccountNumber(
                provider, companyId, accountNumber
        );

        if (opVa.isEmpty()) {
            throw new VirtualAccountNotFoundException( "VA ["+ companyId +"/"+ accountNumber +"-"+ provider.getCode()+"] not found" );
        }

        return opVa.get();
    }

    public void checkVaAlreadyPaid(VirtualAccount va) throws VirtualAccountAlreadyPaidException {
        if (va.getInvoice().getPaid()) {
            throw new VirtualAccountAlreadyPaidException( "VA ["+ va.getCompanyId() +"/"+ va.getAccountNumber() +"-"+ va.getPaymentProvider().getCode() +"] already paid" );
        }
    }

    public void checkIsPaymentAmountExceedInvoice(BigDecimal amount, VirtualAccount va) throws PaymentExceedInvoiceAmountException {
        if (va.getInvoice().getAmount().compareTo(amount) == -1) {
            throw new PaymentExceedInvoiceAmountException("Jumlah bayar melebihi jumlah tagihan !");
        }
    }


}
