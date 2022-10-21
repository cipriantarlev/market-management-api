INSERT INTO users(id, username, password, email) VALUES
(1, 'ciprian', '$2a$10$aTlt8tUImvW0Rts4/gxWCesILeLmq0ZRoI9tC.8eYfKsaV.DEv8oy', 'ciprian@gmail.com');

INSERT INTO roles(id, role) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

INSERT INTO users_roles(user_id, role_id) VALUES
(1, 1);

INSERT INTO barcodes( id, value, product_id) VALUES
(1, '2100002', 2),
(2, '220000285994', 3),
(3, '484000384004', 1);

INSERT INTO categories(id, name) VALUES
(1, 'Chimie'),
(2, 'Bacanie'),
(3, 'Alcool');

INSERT INTO subcategories(id, name, category_id) VALUES
(1, 'Detergent de rufe', 2),
(2, 'Detergent de vase', 3),
(3, 'Vin', 1);

INSERT INTO document_types(id, name) VALUES
(1, 'Income Invoice'),
(2, 'Outcome Invoice'),
(3, 'Other Invoice');

INSERT INTO my_organizations(id, name, bank, fiscal_code, bank_account, vat_code, city, phone_number, email, note, is_default)
VALUES
(1, 'II Ciprian Tarlev', 'BC Moldova-AgroindddBank fil. Basarabeasca', '2115684415156333',
'1225622456333', '2146878333', 'Chisinau', '022-21-24-0643', 'test_test@email.com', null, false),
(2, 'SRL Ciprian Tarlev', 'BC Moldova-AgroindBank fil. Basarabeasca', '2115334415156333',
'1225622456393', '2146878393', 'Chisinau', '022-21-24-0432', 'test_test@emailr.com', null, true);

INSERT INTO regions(id, region_name) VALUES
(1, 'Chisinau'),
(2, 'Cahul'),
(3, 'Ialoveni');

INSERT INTO vendors(id, name, bank, fiscal_code, bank_account, currency, vat_code, city, region_id, phone_number,
postal_code, business_address, vendor_type, vendor_legal_type, note) VALUES
(1, 'SRL Customagic', 'BC VictoriaBank SRL', '1002602004366', '10026342054287', 'MDL', '32341545', 'Chisinau',
 1, '022-21-55-77', 'MD-2000', 'Strada Alexei Şciusev 111, Chișinău 2004, Republica Moldova', 'Supplier',
 'Legal entity', null),
(2, 'SRL Customagica', 'BC VictoriaBank SRL', '1002602404366', '1002602054287', 'MDL', '32121545', 'Chisinau',
 1, '022-21-55-77', 'MD-2000', 'Strada Alexei Şciusev 111, Chișinău 2004, Republica Moldova', 'Supplier',
 'Legal entity', null),
(3, 'SRL Customagaica', 'BC VictoriaBank SRL', '1002643004366', '10026342054287', 'MDL', '321341545', 'Chisinau',
 1, '022-21-55-77', 'MD-2000', 'Strada Alexei Şciusev 111, Chișinău 2004, Republica Moldova', 'Supplier',
 'Legal entity', null);

 INSERT INTO invoices(id, document_type_id, my_organization_id, vendor_id, date_created, invoice_number, invoice_date,
 note, total_discount_price, total_retail_price, total_trade_margin, trade_margin, vat_sum, is_approved)
 VALUES
 (1, 1, 1, 1, '2021-12-29', 'F3243255', '2021-12-29', null, '65.96', '65.96', '65.96', '65.96', '65.96', false),
 (2, 1, 1, 1, '2021-12-29', 'F3243255', '2021-12-29', null, '65.96', '65.96', '65.96', '65.96', '65.96', false),
 (3, 2, 1, 1, '2021-12-29', 'F3243255', '2021-12-29', null, '65.96', '65.96', '65.96', '65.96', '65.96', true);

 INSERT INTO products_code(id, value) VALUES
 (1, 'MD00000000'),
 (2, 'MD00000001'),
 (3, 'MD00000002');

 INSERT INTO measuring_units(id, name) VALUES
 (1, 'kg'),
 (2, 'buc');

 INSERT INTO vat(id, value, name) VALUES
 (1, 20, '20%'),
 (2, 8, '8%'),
 (3, 5, '5%');

 INSERT INTO products(id, name_rom, name_rus, category_id, subcategory_id, discount_price, retail_price,
 trade_margin, measuring_unit_id, vat_id, plu_id, product_code_id, stock, default_vendor_id, is_checked,
 is_retail_price_changed, old_retail_price) VALUES
 (1, 'Naturalis 1L', 'Naturalis 1Л', 1, 3, '22.44', '22.44', '22.44', 2, 1, null, 1, 8, 1, false, false, '22.44'),
 (2, 'Tide Manual 1Kg', 'Tide Manual 1КГ', 2, 1, '22.44', '22.44', '22.44', 1, 2, null, 2, '7.540', 1, true, false, '22.44'),
 (3, 'Paste Barilla 400gr', 'Paste Barilla 400gr', 3, 2, '22.44', '22.44', '22.44', 2, 3, null, 3, 5, 1, true, false, '22.44');

 INSERT INTO invoice_products(id, invoice_id, product_id, quantity, vat_sum, total_discount_price, total_retail_price)
 VALUES
 (1, 1, 1, 3, '2.5', '21.98', '31.98'),
 (2, 1, 2, '3.550', '2.5', '21.98', '31.98'),
 (3, 1, 3, 5, '2.5', '21.98', '31.98');

 INSERT INTO plu(id, value) VALUES
 (1,1),
 (2,2),
 (3,3);

 INSERT INTO product_history(id, username, action, product, product_id, discount_price, retail_price, created) VALUES
 ('0734a16b-0746-4a96-b55c-a2ea0e42c844', 'ciprian', 'update', JSON '{"id": 1}', 1, 12, 14, '2021-12-30 19:00:25.4324'),
 ('0b85d7b3-3ab0-48f7-8a50-1d542ccde4f6', 'ciprian', 'update', JSON '{"id": 1}', 1, 44, 66, '2021-12-30 19:00:55.3242'),
 ('1e623c30-a4d4-4f3b-b969-6e7a19286cf4', 'ciprian', 'update', JSON '{"id": 1}', 1, 33, 44, '2021-12-30 19:01:44.2342');

INSERT INTO price_changing_acts(
id, date_created, my_organization_id, old_prices, new_prices, prices_difference, note, is_approved, is_sent, invoice_id) VALUES
('5fd843b1-f782-4134-bf94-112b3790ec7f', '2022-07-06', 1, '65.96', '65.96', '65.96', null, false, false, null),
('5fd843b1-f782-4134-bf94-112b3790ecf4', '2022-07-06', 1, '65.96', '65.96', '65.96', null, false, false, 1),
('5fd843b1-f782-4134-bf94-112b3790ec3f', '2022-07-06', 1, '65.96', '65.96', '65.96', null, false, false, null);

INSERT INTO public.price_changing_act_products(id, price_changing_act_id, product_id, old_price, price_difference) VALUES
('5fd843b1-f782-4de4-bf94-112b3790ec7f', '5fd843b1-f782-4134-bf94-112b3790ec7f', 2, '65.96', '65.96'),
('5fd843b1-f782-4de4-bf94-11454790ec7f', '5fd843b1-f782-4134-bf94-112b3790ec7f', 2, '65.96', '65.96'),
('5fd843b1-f782-4de4-bf94-112b4390ec7f', '5fd843b1-f782-4134-bf94-112b3790ec7f', 2, '65.96', '65.96');