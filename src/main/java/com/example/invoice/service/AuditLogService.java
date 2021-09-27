package com.example.invoice.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuditLogService {

        @Transactional(propagation = Propagation.REQUIRES_NEW)
        public void log(String log) {
            // Suspen transaction yang sedang berjalan
            // Start transaction baru (tx2)
            // Yang ada disini akan dijalankan oleh transaction baru (tx2)
            // Commit/rollback tx2
            // Resume tx2
        }

}
