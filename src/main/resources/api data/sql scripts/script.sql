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
    id serial NOT NULL,
    value integer NOT NULL,
    CONSTRAINT id PRIMARY KEY (id),
    CONSTRAINT value UNIQUE (value)
);

----------------------------------------------------------------

CREATE TABLE IF NOT EXISTS public.measuring_units
(
    id smallserial NOT NULL,
    name character varying(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (name)
);

----------------------------------------------------------------

CREATE TABLE IF NOT EXISTS public.barcodes
(
    id bigserial NOT NULL,
    value character varying(50) NOT NULL,
    product_id bigint,
    PRIMARY KEY (id),
    FOREIGN KEY (product_id)
        REFERENCES public.products (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

-----------------------------------------------------------------