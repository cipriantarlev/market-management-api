DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id INT PRIMARY KEY auto_increment,
  username VARCHAR(100) NOT NULL,
  password VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL
);

DROP TABLE IF EXISTS roles;

CREATE TABLE roles (
  id INT PRIMARY KEY auto_increment,
  role VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS users_roles;

CREATE TABLE users_roles (
  user_id INT,
  role_id INT
);

DROP TABLE IF EXISTS barcodes;

CREATE TABLE barcodes (
  id INT PRIMARY KEY auto_increment,
  value VARCHAR(50) NOT NULL,
  product_id INT DEFAULT NULL
);

DROP TABLE IF EXISTS entities_history;

CREATE TABLE entities_history
(
    id uuid PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    action VARCHAR(30) NOT NULL,
    entity_name VARCHAR(100) NOT NULL,
    new_entity CLOB NOT NULL,
    old_entity CLOB,
    created timestamp NOT NULL
);

DROP TABLE IF EXISTS categories;

CREATE TABLE categories
(
    id INT PRIMARY KEY auto_increment,
    name VARCHAR(150) NOT NULL
);

DROP TABLE IF EXISTS subcategories;

CREATE TABLE subcategories
(
    id INT PRIMARY KEY auto_increment,
    name VARCHAR(150) NOT NULL,
    category_id INT NOT NULL
);

DROP TABLE IF EXISTS document_types;

CREATE TABLE document_types
(
    id INT PRIMARY KEY auto_increment,
    name VARCHAR(250) NOT NULL
);

DROP TABLE IF EXISTS invoices;

CREATE TABLE IF NOT EXISTS invoices
(
    id INT PRIMARY KEY auto_increment,
    document_type_id INT NOT NULL,
    my_organization_id INT NOT NULL,
    vendor_id INT NOT NULL,
    date_created date NOT NULL,
    invoice_number VARCHAR(50) NOT NULL,
    invoice_date DATE NOT NULL,
    note VARCHAR(250),
    total_discount_price numeric(8,2) DEFAULT 0.00,
    total_retail_price numeric(8,2) DEFAULT 0.00,
    total_trade_margin numeric(8,2) DEFAULT 0.00,
    trade_margin numeric(4,2) DEFAULT 0.00,
    vat_sum numeric(8,2) DEFAULT 0.00,
    is_approved boolean NOT NULL DEFAULT false
);

DROP TABLE IF EXISTS my_organizations;

CREATE TABLE IF NOT EXISTS my_organizations
(
    id INT PRIMARY KEY auto_increment,
    name VARCHAR(150) NOT NULL,
    bank VARCHAR(200) NOT NULL,
    fiscal_code VARCHAR(20) NOT NULL,
    bank_account VARCHAR(50) NOT NULL,
    vat_code VARCHAR(50) NOT NULL,
    city VARCHAR(150) NOT NULL,
    phone_number VARCHAR(100),
    email VARCHAR(150),
    note VARCHAR(500) 
);

DROP TABLE IF EXISTS vendors;

CREATE TABLE IF NOT EXISTS vendors
(
    id INT PRIMARY KEY auto_increment,
    name VARCHAR(200) NOT NULL,
    bank VARCHAR(250) NOT NULL,
    fiscal_code VARCHAR(100) NOT NULL,
    bank_account VARCHAR(100) NOT NULL,
    currency VARCHAR(10) NOT NULL,
    vat_code VARCHAR(50) NOT NULL,
    city VARCHAR(100) NOT NULL,
    region_id integer NOT NULL,
    phone_number VARCHAR(50) NOT NULL,
    postal_code VARCHAR(10) NOT NULL,
    business_address VARCHAR(250) NOT NULL,
    vendor_type VARCHAR(100) NOT NULL,
    vendor_legal_type VARCHAR(150) NOT NULL,
    note VARCHAR(250)
);

DROP TABLE IF EXISTS regions;

CREATE TABLE IF NOT EXISTS regions
(
    id INT PRIMARY KEY auto_increment,
    region_name VARCHAR(100) NOT NULL
);

DROP TABLE IF EXISTS invoice_products;

CREATE TABLE IF NOT EXISTS invoice_products
(
    id INT PRIMARY KEY auto_increment,
    invoice_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity numeric(10,4) NOT NULL DEFAULT 0.0000,
    vat_sum numeric(8,2) NOT NULL DEFAULT 0.00,
    total_discount_price numeric(8,2) NOT NULL DEFAULT 0.00,
    total_retail_price numeric(8,2) NOT NULL DEFAULT 0.00
);

DROP TABLE IF EXISTS products;

CREATE TABLE IF NOT EXISTS products
(
    id INT PRIMARY KEY auto_increment,
    name_rom VARCHAR(300) NOT NULL,
    name_rus VARCHAR(300) NOT NULL,
    category_id INT NOT NULL,
    subcategory_id INT NOT NULL,
    discount_price numeric(7,2) DEFAULT 0.00,
    retail_price numeric(7,2) DEFAULT 0.00,
    trade_margin numeric(5,2) DEFAULT 0.00,
    measuring_unit_id INT NOT NULL,
    vat_id INT NOT NULL,
    plu_id INT,
    product_code_id INT NOT NULL,
    stock numeric(8,4) DEFAULT 0.0000,
    default_vendor_id INT
);

DROP TABLE IF EXISTS vat;

CREATE TABLE IF NOT EXISTS vat
(
    id INT PRIMARY KEY auto_increment,
    value INT NOT NULL,
    name VARCHAR(100) NOT NULL
);

DROP TABLE IF EXISTS plu;

CREATE TABLE IF NOT EXISTS plu
(
    id INT PRIMARY KEY auto_increment,
    value INT NOT NULL
);

DROP TABLE IF EXISTS measuring_units;

CREATE TABLE IF NOT EXISTS measuring_units
(
    id INT PRIMARY KEY auto_increment,
    name VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS products_code;

CREATE TABLE IF NOT EXISTS products_code
(
    id INT PRIMARY KEY auto_increment,
    value VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS vendors_products;

CREATE TABLE IF NOT EXISTS vendors_products
(
    vendor_id INT NOT NULL,
    product_id INT NOT NULL
);

DROP TABLE IF EXISTS product_history;

CREATE TABLE IF NOT EXISTS product_history
(
    id UUID NOT NULL,
    username VARCHAR(50) NOT NULL,
    action VARCHAR(20) NOT NULL,
    product JSON NOT NULL,
    product_id INT NOT NULL,
    discount_price numeric(7,2),
    retail_price numeric(7,2),
    created timestamp NOT NULL
);