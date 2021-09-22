package com.example.invoice.helper;

import com.example.invoice.dao.VirtualAccountDao;
import com.example.invoice.entity.PaymentProvider;
import com.example.invoice.entity.VirtualAccount;
import com.example.invoice.exception.VirtualAccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

public class VirtualAccountHelper {

    public static VirtualAccount checkVaIsExist(VirtualAccountDao virtualAccountDao, PaymentProvider provider, String companyId, String accountNumber) throws VirtualAccountNotFoundException {
        Optional<VirtualAccount> opVa = virtualAccountDao.findByPaymentProviderAndCompanyIdAndAccountNumber(
                provider, companyId, accountNumber
        );

        if (opVa.isEmpty()) {
            throw new VirtualAccountNotFoundException( "VA ["+ companyId +"/"+ accountNumber +"-"+ provider.getCode()+"] not found" );
        }

        return opVa.get();
    }

}
