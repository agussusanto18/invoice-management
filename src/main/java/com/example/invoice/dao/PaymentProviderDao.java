package com.example.invoice.dao;

import com.example.invoice.entity.PaymentProvider;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PaymentProviderDao extends PagingAndSortingRepository<PaymentProvider, String> {

}
