CREATE TABLE IF NOT EXISTS public.users
(
    id integer NOT NULL DEFAULT nextval('users_id_seq'::regclass),
    username character varying(100) COLLATE pg_catalog."default" NOT NULL,
    password character varying(20) COLLATE pg_catalog."default" NOT NULL,
    email character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT "unique email" UNIQUE (email),
    CONSTRAINT "unique username" UNIQUE (username)
)

--------------------------------------------------

CREATE TABLE IF NOT EXISTS public.roles
(
    id smallint NOT NULL,
    role character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT roles_pkey PRIMARY KEY (id),
    CONSTRAINT "unique role" UNIQUE (role)
)

--------------------------------------------------

CREATE TABLE IF NOT EXISTS public."user-roles"
(
    user_id smallint NOT NULL,
    role_id smallint NOT NULL,
    CONSTRAINT "user-roles_pkey" PRIMARY KEY (user_id, role_id),
    CONSTRAINT "role_id to public.roles id" FOREIGN KEY (user_id)
        REFERENCES public.roles (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT "user_id to public.users id" FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)