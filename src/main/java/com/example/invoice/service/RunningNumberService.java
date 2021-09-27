package com.example.invoice.service;

import com.example.invoice.dao.RunningNumberDao;
import com.example.invoice.entity.RunningNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RunningNumberService {

    @Autowired
    private RunningNumberDao runningNumberDao;

    @Transactional
    public Long getNumber(String prefix) {
        RunningNumber rn = runningNumberDao.findByPrefix(prefix);

        if (rn == null) {
            rn = new RunningNumber();
            rn.setPrefix(prefix);
        }

        rn.setLastNumber(rn.getLastNumber() + 1);
        runningNumberDao.save(rn);
        return rn.getLastNumber();
    }



}
