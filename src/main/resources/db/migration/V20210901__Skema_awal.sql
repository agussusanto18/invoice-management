CREATE TABLE bank (
    id character varying(36) NOT NULL,
    created timestamp without time zone,
    created_by character varying(255),
    status_record character varying(255) NOT NULL,
    updated timestamp without time zone,
    updated_by character varying(255),
    code character varying(100) NOT NULL,
    name character varying(100) NOT NULL,
    central_bank_code character varying(5) NOT NULL
);

ALTER TABLE ONLY bank ADD CONSTRAINT bank_pkey PRIMARY KEY (id);

CREATE TABLE bank_account (
    id character varying(36) NOT NULL,
    created timestamp without time zone,
    created_by character varying(255),
    status_record character varying(255) NOT NULL,
    updated timestamp without time zone,
    updated_by character varying(255),
    account_name character varying(100) NOT NULL,
    account_number character varying(100) NOT NULL,
    branch_name character varying(100) NOT NULL,
    id_bank character varying(255) NOT NULL
);

ALTER TABLE ONLY bank_account ADD CONSTRAINT bank_account_pkey PRIMARY KEY (id);
ALTER TABLE ONLY bank_account ADD CONSTRAINT fk4eape8iri4w1j2edy9jyykqhh FOREIGN KEY (id_bank) REFERENCES bank(id);

CREATE TABLE activity_log (
    id character varying(255) NOT NULL,
    feature character varying(255) NOT NULL,
    activity_time timestamp without time zone NOT NULL,
    message character varying(255) NOT NULL
);

ALTER TABLE ONLY activity_log
    ADD CONSTRAINT activity_log_pkey PRIMARY KEY (id);

CREATE TABLE running_number (
    id character varying(36) NOT NULL,
    prefix character varying(100) NOT NULL,
    last_number bigint NOT NULL
);

ALTER TABLE ONLY running_number
    ADD CONSTRAINT running_number_pkey PRIMARY KEY (id);

CREATE TABLE customer (
    id character varying(36) NOT NULL,
    created timestamp without time zone,
    created_by character varying(255),
    status_record character varying(255) NOT NULL,
    updated timestamp without time zone,
    updated_by character varying(255),
    code character varying(100) NOT NULL,
    name character varying(255) NOT NULL,
    email character varying(100) NOT NULL,
    mobile_phone character varying(30) NOT NULL
);

ALTER TABLE ONLY customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id);

CREATE TABLE invoice_type (
    id character varying(36) NOT NULL,
    created timestamp without time zone,
    created_by character varying(255),
    status_record character varying(255) NOT NULL,
    updated timestamp without time zone,
    updated_by character varying(255),
    code character varying(100) NOT NULL,
    name character varying(100) NOT NULL,
    payment_type character varying(100) NOT NULL
);

ALTER TABLE ONLY invoice_type
    ADD CONSTRAINT invoice_type_pkey PRIMARY KEY (id);

CREATE TABLE invoice (
    id character varying(36) NOT NULL,
    created timestamp without time zone,
    created_by character varying(255),
    status_record character varying(255) NOT NULL,
    updated timestamp without time zone,
    updated_by character varying(255),
    amount numeric(19,2) NOT NULL,
    description character varying(255) NOT NULL,
    due_date date NOT NULL,
    invoice_number character varying(100) NOT NULL,
    paid boolean NOT NULL,
    payment_status character varying(255) NOT NULL,
    total_payment numeric(19,2) NOT NULL,
    id_invoice_type character varying(255) NOT NULL,
    id_customer character varying(255) NOT NULL,
    CONSTRAINT invoice_amount_check CHECK ((amount >= (0)::numeric)),
    CONSTRAINT invoice_total_payment_check CHECK ((total_payment >= (0)::numeric))
);

ALTER TABLE ONLY invoice
    ADD CONSTRAINT invoice_pkey PRIMARY KEY (id);
ALTER TABLE ONLY invoice
    ADD CONSTRAINT invoice_unique_number UNIQUE (invoice_number);
ALTER TABLE ONLY invoice
    ADD CONSTRAINT fkco4sbxv9cj2oevm6cdpq76ffb FOREIGN KEY (id_invoice_type) REFERENCES invoice_type(id);
ALTER TABLE ONLY invoice
    ADD CONSTRAINT fk_invoice_cutomer FOREIGN KEY (id_customer) REFERENCES customer(id);


CREATE TABLE payment_provider (
    id character varying(36) NOT NULL,
    code character varying(100) NOT NULL,
    name character varying(100) NOT NULL,
    created timestamp without time zone,
    created_by character varying(255),
    status_record character varying(255) NOT NULL,
    updated timestamp without time zone,
    updated_by character varying(255),
    logo character varying(255)
);

ALTER TABLE ONLY payment_provider
    ADD CONSTRAINT payment_provider_pkey PRIMARY KEY (id);
ALTER TABLE ONLY payment_provider
    ADD CONSTRAINT payment_provider_unique_code UNIQUE (code);

CREATE TABLE virtual_account_configuration (
    id character varying(36) NOT NULL,
    created timestamp without time zone,
    created_by character varying(255),
    status_record character varying(255) NOT NULL,
    updated timestamp without time zone,
    updated_by character varying(255),
    account_number_length integer NOT NULL,
    code character varying(100) NOT NULL,
    company_prefix character varying(3) NOT NULL,
    name character varying(100) NOT NULL,
    transaction_fee_flat numeric(19,2) NOT NULL,
    transaction_fee_persentage double precision NOT NULL,
    id_bank_account character varying(255) NOT NULL,
    id_payment_provider character varying(255) NOT NULL,
    CONSTRAINT virtual_account_configuration_account_number_length_check CHECK ((account_number_length >= 10)),
    CONSTRAINT virtual_account_configuration_transaction_fee_flat_check CHECK ((transaction_fee_flat >= (0)::numeric)),
    CONSTRAINT virtual_account_configuration_transaction_fee_persentage_check CHECK ((transaction_fee_persentage >= (0)::double precision))
);

ALTER TABLE ONLY virtual_account_configuration
    ADD CONSTRAINT virtual_account_configuration_pkey PRIMARY KEY (id);

ALTER TABLE ONLY virtual_account_configuration
    ADD CONSTRAINT fkbfvwy58xo8wb2ne6uk1ci3mj2 FOREIGN KEY (id_bank_account) REFERENCES bank_account(id);

ALTER TABLE ONLY virtual_account_configuration
    ADD CONSTRAINT fkp5o8jrcun29y9po8cjrpu6lim FOREIGN KEY (id_payment_provider) REFERENCES payment_provider(id);

CREATE TABLE virtual_account (
    id character varying(36) NOT NULL,
    created timestamp without time zone,
    created_by character varying(255),
    status_record character varying(255) NOT NULL,
    updated timestamp without time zone,
    updated_by character varying(255),
    account_number character varying(255) NOT NULL,
    company_id character varying(255) NOT NULL,
    virtual_account_type character varying(255) NOT NULL,
    id_invoice character varying(255) NOT NULL,
    id_payment_provider character varying(255) NOT NULL,
    id_va_config character varying(255) NOT NULL
);

ALTER TABLE ONLY virtual_account
    ADD CONSTRAINT virtual_account_pkey PRIMARY KEY (id);
ALTER TABLE ONLY virtual_account
    ADD CONSTRAINT fkbbdwdxpgdisiikyyhf2xteblc FOREIGN KEY (id_invoice) REFERENCES invoice(id);
ALTER TABLE ONLY virtual_account
    ADD CONSTRAINT fkt3t7f64hvgk4xjblsovqqkpll FOREIGN KEY (id_payment_provider) REFERENCES payment_provider(id);
ALTER TABLE ONLY virtual_account
    ADD CONSTRAINT fk_invoice_va_config FOREIGN KEY (id_va_config) REFERENCES virtual_account_configuration(id);


CREATE TABLE payment (
    id character varying(36) NOT NULL,
    created timestamp without time zone,
    created_by character varying(255),
    status_record character varying(255) NOT NULL,
    updated timestamp without time zone,
    updated_by character varying(255),
    amount numeric(19,2) NOT NULL,
    provider_reference character varying(255) NOT NULL,
    transaction_time timestamp without time zone NOT NULL,
    id_virtual_account character varying(255) NOT NULL,
    CONSTRAINT payment_amount_check CHECK ((amount >= (1)::numeric))
);

ALTER TABLE ONLY payment
    ADD CONSTRAINT payment_pkey PRIMARY KEY (id);
ALTER TABLE ONLY payment
    ADD CONSTRAINT fkptriq88d7e8io9mhri8p10cq0 FOREIGN KEY (id_virtual_account) REFERENCES virtual_account(id);




CREATE TABLE invoice_type_provider (
    id_invoice_type character varying(36) NOT NULL,
    id_payment_provider character varying(36) NOT NULL
);

ALTER TABLE ONLY invoice_type_provider
    ADD CONSTRAINT invoice_type_provider_pkey PRIMARY KEY (id_invoice_type, id_payment_provider);
ALTER TABLE ONLY invoice_type_provider
    ADD CONSTRAINT fk_invoice_type_provider_type FOREIGN KEY (id_invoice_type) REFERENCES invoice_type(id);
ALTER TABLE ONLY invoice_type_provider
    ADD CONSTRAINT fk_invoice_type_provider_provider FOREIGN KEY (id_payment_provider) REFERENCES payment_provider(id);

CREATE TABLE invoice_type_configuration (
    id_invoice_type character varying(255) NOT NULL,
    id_virtual_account_configuration character varying(255) NOT NULL
);

ALTER TABLE ONLY invoice_type_configuration
    ADD CONSTRAINT invoice_type_configuration_pkey PRIMARY KEY (id_invoice_type, id_virtual_account_configuration);

ALTER TABLE ONLY invoice_type_configuration
    ADD CONSTRAINT fkextslpqopbximqfd69mbi46rq FOREIGN KEY (id_invoice_type) REFERENCES invoice_type(id);

ALTER TABLE ONLY invoice_type_configuration
    ADD CONSTRAINT fkpbx15u40wtbil9sbvu24eu2hp FOREIGN KEY (id_virtual_account_configuration) REFERENCES virtual_account_configuration(id);
