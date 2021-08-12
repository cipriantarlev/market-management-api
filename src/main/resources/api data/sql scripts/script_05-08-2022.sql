CREATE TABLE IF NOT EXISTS public.users
(
    id integer NOT NULL DEFAULT nextval('users_id_seq'::regclass),
    username character varying(100) COLLATE pg_catalog."default" NOT NULL,
    password character varying(20) COLLATE pg_catalog."default" NOT NULL,
    email character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT "unique email" UNIQUE (email),
    CONSTRAINT "unique username" UNIQUE (username)
);

--------------------------------------------------

CREATE TABLE IF NOT EXISTS public.roles
(
    id smallint NOT NULL,
    role character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT roles_pkey PRIMARY KEY (id),
    CONSTRAINT "unique role" UNIQUE (role)
);

--------------------------------------------------

CREATE TABLE IF NOT EXISTS public."user_roles"
(
    user_id smallint NOT NULL,
    role_id smallint NOT NULL,
    CONSTRAINT "user-roles_pkey" PRIMARY KEY (user_id, role_id),
    CONSTRAINT "role_id to public.roles id" FOREIGN KEY (role_id)
        REFERENCES public.roles (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT "user_id to public.users id" FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

-----------------------------------------------------------
CREATE TABLE IF NOT EXISTS public.my_organizations
(
    id smallserial NOT NULL,
    name character varying(150) NOT NULL,
    bank character varying(200) NOT NULL,
    fiscal_code numeric(20) NOT NULL,
    bank_account numeric(50) NOT NULL,
    vat_code numeric(50) NOT NULL,
    city character varying(150) NOT NULL,
    phone_number character varying(100),
    email character varying(150),
    note character varying(500),
    PRIMARY KEY (id),
    CONSTRAINT "unique" UNIQUE (name, fiscal_code, bank_account, vat_code)
);

------------------------------------------------------------
CREATE TABLE IF NOT EXISTS public.regions
(
    id smallserial NOT NULL,
    region_name character varying(100) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT region UNIQUE (region_name)
)

------------------------------------------------------
CREATE TABLE IF NOT EXISTS public.vendors
(
    id serial NOT NULL,
    name character varying(200) NOT NULL,
    bank character varying(250) NOT NULL,
    fiscal_code character varying(100) NOT NULL,
    bank_account character varying(100) NOT NULL,
    currency character varying(10) NOT NULL,
    vat_code character varying(50) NOT NULL,
    city character varying(100) NOT NULL,
    region_id integer NOT NULL,
    phone_number character varying(50) NOT NULL,
    postal_code character varying(10) NOT NULL,
    business_address character varying(250) NOT NULL,
    vendor_type character varying(100) NOT NULL,
    vendor_legal_type character varying(150) NOT NULL,
    note character varying(250),
    CONSTRAINT id PRIMARY KEY (id),
    UNIQUE (name, fiscal_code, bank_account, vat_code),
    CONSTRAINT vendor_region FOREIGN KEY (region_id)
        REFERENCES public.regions (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

------------------------------------------------------------
CREATE TABLE IF NOT EXISTS public.categories
(
    id smallserial NOT NULL,
    name character varying(150) NOT NULL,
    CONSTRAINT category_id PRIMARY KEY (id),
    CONSTRAINT name UNIQUE (name)
);

-------------------------------------------------------------

CREATE TABLE IF NOT EXISTS public.subcategories
(
    id smallserial NOT NULL,
    name character varying(150) NOT NULL,
    category_id integer NOT NULL,
    CONSTRAINT subcategory_id PRIMARY KEY (id),
    CONSTRAINT "category_id - id public.categories" FOREIGN KEY (category_id)
        REFERENCES public.categories (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

----------------------------------------------------------------

CREATE TABLE IF NOT EXISTS public.vat
(
    id smallserial NOT NULL,
    value integer NOT NULL,
    name character varying(100) NOT NULL,
    CONSTRAINT id PRIMARY KEY (id),
    CONSTRAINT "value-name" UNIQUE (value, name)
);

----------------------------------------------------------------

CREATE TABLE IF NOT EXISTS public.plu
(
    id integer NOT NULL,
    value integer NOT NULL,
    CONSTRAINT plu_pkey PRIMARY KEY (id),
    CONSTRAINT value UNIQUE (value)
);

----------------------------------------------------------------

CREATE TABLE IF NOT EXISTS public.measuring_units
(
    id smallint NOT NULL,
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT measuring_units_pkey PRIMARY KEY (id),
    CONSTRAINT measuring_units_name_key UNIQUE (name)
);

----------------------------------------------------------------

CREATE TABLE IF NOT EXISTS public.barcodes
(
    id bigint NOT NULL DEFAULT nextval('barcodes_id_seq'::regclass),
    value character varying(50) COLLATE pg_catalog."default" NOT NULL,
    product_id bigint,
    CONSTRAINT barcodes_pkey PRIMARY KEY (id),
    CONSTRAINT barcodes_value_key UNIQUE (value),
    CONSTRAINT barcodes_product_id_fkey FOREIGN KEY (product_id)
        REFERENCES public.products (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
        NOT VALID
);

-----------------------------------------------------------------

CREATE TABLE IF NOT EXISTS public.products_code
(
    id bigint NOT NULL DEFAULT,
    value character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT products_code_pkey PRIMARY KEY (id),
    CONSTRAINT products_code_value_key UNIQUE (value)
);

------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS public.products
(
    id bigint NOT NULL DEFAULT nextval('products_id_seq'::regclass),
    name_rom character varying(300) COLLATE pg_catalog."default" NOT NULL,
    name_rus character varying(300) COLLATE pg_catalog."default" NOT NULL,
    category_id integer NOT NULL,
    subcategory_id integer NOT NULL,
    discount_price numeric(7,2),
    retail_price numeric(7,2),
    trade_margin numeric(4,2),
    measuring_unit_id integer NOT NULL,
    vat_id integer NOT NULL,
    plu_id bigint,
    product_code_id bigint NOT NULL,
    stock numeric(8,4),
    CONSTRAINT products_pkey PRIMARY KEY (id),
    CONSTRAINT products_name_rom_name_rus_key UNIQUE (name_rom, name_rus),
    CONSTRAINT products_category_id_fkey FOREIGN KEY (category_id)
        REFERENCES public.categories (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT products_measuring_unit_id_fkey FOREIGN KEY (measuring_unit_id)
        REFERENCES public.measuring_units (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT products_plu_id_fkey FOREIGN KEY (plu_id)
        REFERENCES public.plu (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID,
    CONSTRAINT products_product_code_id_fkey FOREIGN KEY (product_code_id)
        REFERENCES public.products_code (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID,
    CONSTRAINT products_subcategory_id_fkey FOREIGN KEY (subcategory_id)
        REFERENCES public.subcategories (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT products_vat_id_fkey FOREIGN KEY (vat_id)
        REFERENCES public.vat (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS public.document_types
(
    id integer NOT NULL,
    name character varying(250) NOT NULL,
    CONSTRAINT document_types_pkey PRIMARY KEY (id),
    CONSTRAINT document_types_name_key UNIQUE (name)
);

----------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS public.invoices
(
    id bigint NOT NULL DEFAULT nextval('invoices_id_seq'::regclass),
    document_type_id integer NOT NULL,
    my_organization_id integer NOT NULL,
    vendor_id integer NOT NULL,
    date_created date NOT NULL,
    invoice_number character varying(50) COLLATE pg_catalog."default" NOT NULL,
    invoice_date date NOT NULL,
    note character varying(250) COLLATE pg_catalog."default",
    total_discount_price numeric(8,2),
    total_retail_price numeric(8,2),
    total_trade_margin numeric(8,2),
    trade_margin numeric(4,2),
    vat_sum numeric(8,2),
    CONSTRAINT invoices_pkey PRIMARY KEY (id),
    CONSTRAINT invoices_document_type_id_fkey FOREIGN KEY (document_type_id)
        REFERENCES public.document_types (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT invoices_my_organization_id_fkey FOREIGN KEY (my_organization_id)
        REFERENCES public.my_organizations (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT invoices_vendor_id_fkey FOREIGN KEY (vendor_id)
        REFERENCES public.vendors (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS public.invoice_products
(
    id bigserial NOT NULL,
    invoice_id bigint NOT NULL,
    product_id bigint NOT NULL,
    quantity numeric(10, 4) NOT NULL DEFAULT 0.0000,
    vat_sum numeric(4, 2) NOT NULL DEFAULT 0.00,
    total_discount_price numeric(8, 2) NOT NULL DEFAULT 0.00,
    total_retail_price numeric(8, 2) NOT NULL DEFAULT 0.00,
    PRIMARY KEY (id),
    FOREIGN KEY (invoice_id)
        REFERENCES public.invoices (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    FOREIGN KEY (product_id)
        REFERENCES public.products (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

-----------------------------------------------------------------------------
