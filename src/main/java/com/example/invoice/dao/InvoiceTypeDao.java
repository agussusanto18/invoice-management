package com.example.invoice.dao;

import com.example.invoice.entity.InvoiceType;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface InvoiceTypeDao extends PagingAndSortingRepository<InvoiceType, String> {

    Optional<InvoiceType> findByCode(String invoiceTypeCode);

}
