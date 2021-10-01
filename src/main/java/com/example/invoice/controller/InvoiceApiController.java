package com.example.invoice.controller;

import com.example.invoice.dao.CustomerDao;
import com.example.invoice.dao.InvoiceTypeDao;
import com.example.invoice.dto.CreateInvoiceRequestDto;
import com.example.invoice.entity.Customer;
import com.example.invoice.entity.InvoiceType;
import com.example.invoice.exception.CustomerNotFoundException;
import com.example.invoice.exception.InvoiceTypeNotFoundException;
import com.example.invoice.service.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/invoice")
public class InvoiceApiController {

    @Autowired
    CustomerDao customerDao;

    @Autowired
    InvoiceTypeDao invoiceTypeDao;

    @Autowired
    InvoiceService invoiceService;

    @PostMapping("/")
    public void createInvoice(@RequestBody @Valid CreateInvoiceRequestDto request) throws InvoiceTypeNotFoundException, CustomerNotFoundException {
        Customer customer = customerDao.findByCode(request.getCustomerCode())
                .orElseThrow(() -> new CustomerNotFoundException("Customer code '" + request.getCustomerCode() + "' not found"));
        log.info("Customer {}", customer.getName());

        InvoiceType invoiceType = invoiceTypeDao.findByCode(request.getInvoiceTypeCode())
                .orElseThrow(() -> new InvoiceTypeNotFoundException("Invoice type '"+ request.getInvoiceTypeCode() +"' not found"));
        log.info("Invoice Type {}", invoiceType.getName());

        invoiceService.createInvoice(customer, invoiceType, request.getDescription(), request.getAmount());
        log.info("Invoice Created");
    }

}
