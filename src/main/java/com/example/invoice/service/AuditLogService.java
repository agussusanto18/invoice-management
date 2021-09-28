package com.example.invoice.service;

import com.example.invoice.dao.ActivityLogDao;
import com.example.invoice.entity.ActivityLog;
import com.example.invoice.entity.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuditLogService {

        @Autowired
        ActivityLogDao activityLogDao;

        @Transactional(propagation = Propagation.REQUIRES_NEW)
        public void log(String log) {
                ActivityLog activityLog = new ActivityLog();
                activityLog.setFeature(Feature.PAYMENT);
                activityLog.setMessage(log);
                activityLogDao.save(activityLog);
        }

}
