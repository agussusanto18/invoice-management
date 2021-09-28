-- invoice type
insert into invoice_type (id, code, name, payment_type, status_record, created, created_by)
values ('registrasi', 'REG-001', 'Biaya Pendaftaran', 'CLOSED', 'ACTIVE', current_timestamp, 'user-test');

insert into invoice_type (id, code, name, payment_type, status_record, created, created_by)
values ('donasi', 'DON-001', 'Sumbangan Sukarela', 'OPEN', 'ACTIVE', current_timestamp, 'user-test');

insert into invoice_type (id, code, name, payment_type, status_record, created, created_by)
values ('uang-muka', 'DP-001', 'Down Payment', 'INSTALLMENT', 'ACTIVE', current_timestamp, 'user-test');


-- customer
insert into customer (id, code, name, email, mobile_phone, status_record, created, created_by, updated, updated_by)
values ('c001', 'CUST-001', 'Customer 001', 'c001@mail.com', '09899929919', 'ACTIVE', '2021-09-08T11:28:38.426199', 'test-user', '2021-09-08T11:48:38.426199', 'test-user');

insert into customer (id, code, name, email, mobile_phone, status_record, created, created_by, updated, updated_by)
values ('c002', 'CUST-002', 'Customer 002', 'c002@mail.com', '09899929919', 'ACTIVE', '2021-09-08T11:28:38.426199', 'test-user', '2021-09-08T11:48:38.426199', 'test-user');

insert into customer (id, code, name, email, mobile_phone, status_record, created, created_by, updated, updated_by)
values ('c003', 'CUST-003', 'Customer 003', 'c003@mail.com', '09899929919', 'ACTIVE', '2021-09-08T11:28:38.426199', 'test-user', '2021-09-08T11:48:38.426199', 'test-user');

insert into customer (id, code, name, email, mobile_phone, status_record, created, created_by, updated, updated_by)
values ('c004', 'CUST-004', 'Customer 004', 'c004@mail.com', '09899929919', 'ACTIVE', '2021-09-08T11:28:38.426199', 'test-user', '2021-09-08T11:48:38.426199', 'test-user');
