package com.example.invoice.dao;

import com.example.invoice.entity.RunningNumber;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.LockModeType;

public interface RunningNumberDao extends CrudRepository<RunningNumber, String> {

//    @Lock(LockModeType.PESSIMISTIC_READ)
    RunningNumber findByPrefix(String prefix);

}
