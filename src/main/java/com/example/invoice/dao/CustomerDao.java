package com.example.invoice.dao;

import com.example.invoice.entity.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CustomerDao extends PagingAndSortingRepository<Customer, String> {

    Optional<Customer> findByCode(String customerCode);

}
