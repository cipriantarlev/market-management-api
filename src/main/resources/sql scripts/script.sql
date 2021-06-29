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