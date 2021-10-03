package com.example.invoice.controller;

import com.example.invoice.dto.CreateInvoiceRequestDto;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {
        "classpath:/sql/delete-all-data.sql",
        "classpath:/sql/insert-sample-data-invoice.sql"
})
public class InvoiceApiControllerTest {
    @LocalServerPort
    private Integer serverPort;

    @BeforeEach
    public void setup() {
        baseURI = "http://localhost";
        port = serverPort;
        System.out.println("Post : " + serverPort);
    }

    @Test
    void testCreateInvoice() {
        CreateInvoiceRequestDto invoiceRequestDto = new CreateInvoiceRequestDto();
        invoiceRequestDto.setCustomerCode("CUST-001");
        invoiceRequestDto.setInvoiceTypeCode("REG-001");
        invoiceRequestDto.setAmount(new BigDecimal(123000.98));
        invoiceRequestDto.setDescription("Pembayaran Barang 1");

        with().body(invoiceRequestDto).contentType(ContentType.JSON)
                .when().post("/api/invoice/")
                .then().statusCode(200);
    }
}
