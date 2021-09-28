package com.example.invoice.dao;

import com.example.invoice.entity.ActivityLog;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ActivityLogDao extends PagingAndSortingRepository<ActivityLog, String> {

}
