package com.example.invoice.dao;

import com.example.invoice.entity.VirtualAccountConfiguration;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VirtualAccountConfigurationDao extends PagingAndSortingRepository<VirtualAccountConfiguration, String> {

}
