package com.example.invoice.service;

import com.example.invoice.dao.VirtualAccountDao;
import com.example.invoice.entity.PaymentProvider;
import com.example.invoice.entity.VirtualAccount;
import com.example.invoice.exception.VirtualAccountAlreadyPaidException;
import com.example.invoice.exception.VirtualAccountNotFoundException;
import com.example.invoice.helper.VirtualAccountHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;


@Service
@Transactional
public class PaymentService {

    @Autowired
    private VirtualAccountDao virtualAccountDao;

    public void pay(PaymentProvider provider, String companyId, String accountNumber, BigDecimal amount, String reference) throws VirtualAccountNotFoundException, VirtualAccountAlreadyPaidException {
        VirtualAccount va = VirtualAccountHelper.checkVaIsExist(virtualAccountDao, provider, companyId, accountNumber);
        checkVaIsPaid(provider, companyId, accountNumber, va);
    }

    private void checkVaIsPaid(PaymentProvider provider, String companyId, String accountNumber, VirtualAccount va) throws VirtualAccountAlreadyPaidException {
        if (va.getInvoice().getPaid()) {
            throw new VirtualAccountAlreadyPaidException( "VA ["+ companyId +"/"+ accountNumber +"-"+ provider.getCode()  +"] already paid" );
        }
    }



}
