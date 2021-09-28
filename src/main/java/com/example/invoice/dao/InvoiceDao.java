package com.example.invoice.dao;

import com.example.invoice.entity.Invoice;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InvoiceDao extends PagingAndSortingRepository<Invoice, String> {

}
