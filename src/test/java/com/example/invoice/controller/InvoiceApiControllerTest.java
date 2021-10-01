package com.example.invoice.controller;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {
        "classpath:/sql/delete-all-data.sql",
        "classpath:/sql/insert-sample-data-invoice.sql"
})
public class InvoiceApiControllerTest {

    @LocalServerPort
    private Integer serverPort;

}
