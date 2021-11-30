PGDMP                     
    y            market-management    10.17    10.17 �    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            �           1262    24576    market-management    DATABASE     �   CREATE DATABASE "market-management" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United Kingdom.1252' LC_CTYPE = 'English_United Kingdom.1252';
 #   DROP DATABASE "market-management";
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            �           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    3                        3079    12924    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            �           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    73768    barcodes    TABLE     z   CREATE TABLE public.barcodes (
    id bigint NOT NULL,
    value character varying(50) NOT NULL,
    product_id bigint
);
    DROP TABLE public.barcodes;
       public         postgres    false    3            �            1259    73766    barcodes_id_seq    SEQUENCE     x   CREATE SEQUENCE public.barcodes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.barcodes_id_seq;
       public       postgres    false    219    3            �           0    0    barcodes_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.barcodes_id_seq OWNED BY public.barcodes.id;
            public       postgres    false    218            �            1259    49193 
   categories    TABLE     g   CREATE TABLE public.categories (
    id smallint NOT NULL,
    name character varying(150) NOT NULL
);
    DROP TABLE public.categories;
       public         postgres    false    3            �            1259    49191    categories_id_seq    SEQUENCE     �   CREATE SEQUENCE public.categories_id_seq
    AS smallint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.categories_id_seq;
       public       postgres    false    3    207            �           0    0    categories_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.categories_id_seq OWNED BY public.categories.id;
            public       postgres    false    206            �            1259    106533    document_types    TABLE     j   CREATE TABLE public.document_types (
    id integer NOT NULL,
    name character varying(250) NOT NULL
);
 "   DROP TABLE public.document_types;
       public         postgres    false    3            �            1259    106531    document_types_id_seq    SEQUENCE     �   CREATE SEQUENCE public.document_types_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.document_types_id_seq;
       public       postgres    false    223    3            �           0    0    document_types_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.document_types_id_seq OWNED BY public.document_types.id;
            public       postgres    false    222            �            1259    139301    invoice_products    TABLE     ]  CREATE TABLE public.invoice_products (
    id bigint NOT NULL,
    invoice_id bigint NOT NULL,
    product_id bigint NOT NULL,
    quantity numeric(10,4) DEFAULT 0.0000 NOT NULL,
    vat_sum numeric(8,2) DEFAULT 0.00 NOT NULL,
    total_discount_price numeric(8,2) DEFAULT 0.00 NOT NULL,
    total_retail_price numeric(8,2) DEFAULT 0.00 NOT NULL
);
 $   DROP TABLE public.invoice_products;
       public         postgres    false    3            �            1259    139299    invoice_products_id_seq    SEQUENCE     �   CREATE SEQUENCE public.invoice_products_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.invoice_products_id_seq;
       public       postgres    false    227    3            �           0    0    invoice_products_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.invoice_products_id_seq OWNED BY public.invoice_products.id;
            public       postgres    false    226            �            1259    114725    invoices    TABLE     (  CREATE TABLE public.invoices (
    id bigint NOT NULL,
    document_type_id integer NOT NULL,
    my_organization_id integer NOT NULL,
    vendor_id integer NOT NULL,
    date_created date NOT NULL,
    invoice_number character varying(50) NOT NULL,
    invoice_date date NOT NULL,
    note character varying(250),
    total_discount_price numeric(8,2) DEFAULT 0.00,
    total_retail_price numeric(8,2) DEFAULT 0.00,
    total_trade_margin numeric(8,2) DEFAULT 0.00,
    trade_margin numeric(4,2) DEFAULT 0.00,
    vat_sum numeric(8,2) DEFAULT 0.00
);
    DROP TABLE public.invoices;
       public         postgres    false    3            �            1259    114723    invoices_id_seq    SEQUENCE     x   CREATE SEQUENCE public.invoices_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.invoices_id_seq;
       public       postgres    false    3    225            �           0    0    invoices_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.invoices_id_seq OWNED BY public.invoices.id;
            public       postgres    false    224            �            1259    65590    measuring_units    TABLE     k   CREATE TABLE public.measuring_units (
    id smallint NOT NULL,
    name character varying(50) NOT NULL
);
 #   DROP TABLE public.measuring_units;
       public         postgres    false    3            �            1259    65588    measuring_units_id_seq    SEQUENCE     �   CREATE SEQUENCE public.measuring_units_id_seq
    AS smallint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.measuring_units_id_seq;
       public       postgres    false    3    215            �           0    0    measuring_units_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.measuring_units_id_seq OWNED BY public.measuring_units.id;
            public       postgres    false    214            �            1259    32816    my_organizations    TABLE     �  CREATE TABLE public.my_organizations (
    id smallint NOT NULL,
    name character varying(150) NOT NULL,
    bank character varying(200) NOT NULL,
    fiscal_code character varying(20) NOT NULL,
    bank_account character varying(50) NOT NULL,
    vat_code character varying(50) NOT NULL,
    city character varying(150) NOT NULL,
    phone_number character varying(100),
    email character varying(150),
    note character varying(500)
);
 $   DROP TABLE public.my_organizations;
       public         postgres    false    3            �            1259    32814    my_organizations_id_seq    SEQUENCE     �   CREATE SEQUENCE public.my_organizations_id_seq
    AS smallint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.my_organizations_id_seq;
       public       postgres    false    201    3            �           0    0    my_organizations_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.my_organizations_id_seq OWNED BY public.my_organizations.id;
            public       postgres    false    200            �            1259    65580    plu    TABLE     Q   CREATE TABLE public.plu (
    id integer NOT NULL,
    value integer NOT NULL
);
    DROP TABLE public.plu;
       public         postgres    false    3            �            1259    65578 
   plu_id_seq    SEQUENCE     �   CREATE SEQUENCE public.plu_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE public.plu_id_seq;
       public       postgres    false    3    213            �           0    0 
   plu_id_seq    SEQUENCE OWNED BY     9   ALTER SEQUENCE public.plu_id_seq OWNED BY public.plu.id;
            public       postgres    false    212            �            1259    73760    products    TABLE       CREATE TABLE public.products (
    id bigint NOT NULL,
    name_rom character varying(300) NOT NULL,
    name_rus character varying(300) NOT NULL,
    category_id integer NOT NULL,
    subcategory_id integer NOT NULL,
    discount_price numeric(7,2) DEFAULT 0.00,
    retail_price numeric(7,2) DEFAULT 0.00,
    trade_margin numeric(5,2) DEFAULT 0.00,
    measuring_unit_id integer NOT NULL,
    vat_id integer NOT NULL,
    plu_id bigint,
    product_code_id bigint NOT NULL,
    stock numeric(8,4) DEFAULT 0.0000
);
    DROP TABLE public.products;
       public         postgres    false    3            �            1259    81982    products_code    TABLE     h   CREATE TABLE public.products_code (
    id bigint NOT NULL,
    value character varying(50) NOT NULL
);
 !   DROP TABLE public.products_code;
       public         postgres    false    3            �            1259    81980    products_code_id_seq    SEQUENCE     }   CREATE SEQUENCE public.products_code_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.products_code_id_seq;
       public       postgres    false    3    221            �           0    0    products_code_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.products_code_id_seq OWNED BY public.products_code.id;
            public       postgres    false    220            �            1259    73758    products_id_seq    SEQUENCE     x   CREATE SEQUENCE public.products_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.products_id_seq;
       public       postgres    false    217    3            �           0    0    products_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.products_id_seq OWNED BY public.products.id;
            public       postgres    false    216            �            1259    40992    regions    TABLE     k   CREATE TABLE public.regions (
    id smallint NOT NULL,
    region_name character varying(100) NOT NULL
);
    DROP TABLE public.regions;
       public         postgres    false    3            �            1259    40990    regions_id_seq    SEQUENCE     �   CREATE SEQUENCE public.regions_id_seq
    AS smallint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.regions_id_seq;
       public       postgres    false    203    3            �           0    0    regions_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.regions_id_seq OWNED BY public.regions.id;
            public       postgres    false    202            �            1259    24585    roles    TABLE     a   CREATE TABLE public.roles (
    id smallint NOT NULL,
    role character varying(50) NOT NULL
);
    DROP TABLE public.roles;
       public         postgres    false    3            �            1259    49203    subcategories    TABLE     �   CREATE TABLE public.subcategories (
    id smallint NOT NULL,
    name character varying(150) NOT NULL,
    category_id integer NOT NULL
);
 !   DROP TABLE public.subcategories;
       public         postgres    false    3            �            1259    49201    subcategories_id_seq    SEQUENCE     �   CREATE SEQUENCE public.subcategories_id_seq
    AS smallint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.subcategories_id_seq;
       public       postgres    false    209    3            �           0    0    subcategories_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.subcategories_id_seq OWNED BY public.subcategories.id;
            public       postgres    false    208            �            1259    24579    users    TABLE     �   CREATE TABLE public.users (
    id integer NOT NULL,
    username character varying(100) NOT NULL,
    password character varying(100) NOT NULL,
    email character varying(100) NOT NULL
);
    DROP TABLE public.users;
       public         postgres    false    3            �            1259    24577    users_id_seq    SEQUENCE     �   CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public       postgres    false    197    3            �           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
            public       postgres    false    196            �            1259    32793    users_roles    TABLE     b   CREATE TABLE public.users_roles (
    user_id smallint NOT NULL,
    role_id smallint NOT NULL
);
    DROP TABLE public.users_roles;
       public         postgres    false    3            �            1259    57376    vat    TABLE     |   CREATE TABLE public.vat (
    id smallint NOT NULL,
    value integer NOT NULL,
    name character varying(100) NOT NULL
);
    DROP TABLE public.vat;
       public         postgres    false    3            �            1259    57374 
   vat_id_seq    SEQUENCE     �   CREATE SEQUENCE public.vat_id_seq
    AS smallint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE public.vat_id_seq;
       public       postgres    false    3    211            �           0    0 
   vat_id_seq    SEQUENCE OWNED BY     9   ALTER SEQUENCE public.vat_id_seq OWNED BY public.vat.id;
            public       postgres    false    210            �            1259    41015    vendors    TABLE     �  CREATE TABLE public.vendors (
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
    id integer DEFAULT 26 NOT NULL
);
    DROP TABLE public.vendors;
       public         postgres    false    3            �            1259    41042    vendors_id_seq    SEQUENCE     �   CREATE SEQUENCE public.vendors_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.vendors_id_seq;
       public       postgres    false    3    204            �           0    0    vendors_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.vendors_id_seq OWNED BY public.vendors.id;
            public       postgres    false    205            �
           2604    73771    barcodes id    DEFAULT     j   ALTER TABLE ONLY public.barcodes ALTER COLUMN id SET DEFAULT nextval('public.barcodes_id_seq'::regclass);
 :   ALTER TABLE public.barcodes ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    218    219    219            �
           2604    49196    categories id    DEFAULT     n   ALTER TABLE ONLY public.categories ALTER COLUMN id SET DEFAULT nextval('public.categories_id_seq'::regclass);
 <   ALTER TABLE public.categories ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    207    206    207            �
           2604    106536    document_types id    DEFAULT     v   ALTER TABLE ONLY public.document_types ALTER COLUMN id SET DEFAULT nextval('public.document_types_id_seq'::regclass);
 @   ALTER TABLE public.document_types ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    223    222    223            �
           2604    139304    invoice_products id    DEFAULT     z   ALTER TABLE ONLY public.invoice_products ALTER COLUMN id SET DEFAULT nextval('public.invoice_products_id_seq'::regclass);
 B   ALTER TABLE public.invoice_products ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    226    227    227            �
           2604    114728    invoices id    DEFAULT     j   ALTER TABLE ONLY public.invoices ALTER COLUMN id SET DEFAULT nextval('public.invoices_id_seq'::regclass);
 :   ALTER TABLE public.invoices ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    224    225    225            �
           2604    65593    measuring_units id    DEFAULT     x   ALTER TABLE ONLY public.measuring_units ALTER COLUMN id SET DEFAULT nextval('public.measuring_units_id_seq'::regclass);
 A   ALTER TABLE public.measuring_units ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    215    214    215            �
           2604    32819    my_organizations id    DEFAULT     z   ALTER TABLE ONLY public.my_organizations ALTER COLUMN id SET DEFAULT nextval('public.my_organizations_id_seq'::regclass);
 B   ALTER TABLE public.my_organizations ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    200    201    201            �
           2604    65583    plu id    DEFAULT     `   ALTER TABLE ONLY public.plu ALTER COLUMN id SET DEFAULT nextval('public.plu_id_seq'::regclass);
 5   ALTER TABLE public.plu ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    213    212    213            �
           2604    73763    products id    DEFAULT     j   ALTER TABLE ONLY public.products ALTER COLUMN id SET DEFAULT nextval('public.products_id_seq'::regclass);
 :   ALTER TABLE public.products ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    216    217    217            �
           2604    81985    products_code id    DEFAULT     t   ALTER TABLE ONLY public.products_code ALTER COLUMN id SET DEFAULT nextval('public.products_code_id_seq'::regclass);
 ?   ALTER TABLE public.products_code ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    221    220    221            �
           2604    40995 
   regions id    DEFAULT     h   ALTER TABLE ONLY public.regions ALTER COLUMN id SET DEFAULT nextval('public.regions_id_seq'::regclass);
 9   ALTER TABLE public.regions ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    202    203    203            �
           2604    49206    subcategories id    DEFAULT     t   ALTER TABLE ONLY public.subcategories ALTER COLUMN id SET DEFAULT nextval('public.subcategories_id_seq'::regclass);
 ?   ALTER TABLE public.subcategories ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    209    208    209            �
           2604    24582    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    197    196    197            �
           2604    57379    vat id    DEFAULT     `   ALTER TABLE ONLY public.vat ALTER COLUMN id SET DEFAULT nextval('public.vat_id_seq'::regclass);
 5   ALTER TABLE public.vat ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    211    210    211            �          0    73768    barcodes 
   TABLE DATA               9   COPY public.barcodes (id, value, product_id) FROM stdin;
    public       postgres    false    219   �       �          0    49193 
   categories 
   TABLE DATA               .   COPY public.categories (id, name) FROM stdin;
    public       postgres    false    207   z�       �          0    106533    document_types 
   TABLE DATA               2   COPY public.document_types (id, name) FROM stdin;
    public       postgres    false    223   �       �          0    139301    invoice_products 
   TABLE DATA               �   COPY public.invoice_products (id, invoice_id, product_id, quantity, vat_sum, total_discount_price, total_retail_price) FROM stdin;
    public       postgres    false    227   9�       �          0    114725    invoices 
   TABLE DATA               �   COPY public.invoices (id, document_type_id, my_organization_id, vendor_id, date_created, invoice_number, invoice_date, note, total_discount_price, total_retail_price, total_trade_margin, trade_margin, vat_sum) FROM stdin;
    public       postgres    false    225   ��       �          0    65590    measuring_units 
   TABLE DATA               3   COPY public.measuring_units (id, name) FROM stdin;
    public       postgres    false    215   �       �          0    32816    my_organizations 
   TABLE DATA               �   COPY public.my_organizations (id, name, bank, fiscal_code, bank_account, vat_code, city, phone_number, email, note) FROM stdin;
    public       postgres    false    201   �       �          0    65580    plu 
   TABLE DATA               (   COPY public.plu (id, value) FROM stdin;
    public       postgres    false    213   ��       �          0    73760    products 
   TABLE DATA               �   COPY public.products (id, name_rom, name_rus, category_id, subcategory_id, discount_price, retail_price, trade_margin, measuring_unit_id, vat_id, plu_id, product_code_id, stock) FROM stdin;
    public       postgres    false    217   �       �          0    81982    products_code 
   TABLE DATA               2   COPY public.products_code (id, value) FROM stdin;
    public       postgres    false    221   �       �          0    40992    regions 
   TABLE DATA               2   COPY public.regions (id, region_name) FROM stdin;
    public       postgres    false    203   �       �          0    24585    roles 
   TABLE DATA               )   COPY public.roles (id, role) FROM stdin;
    public       postgres    false    198   �       �          0    49203    subcategories 
   TABLE DATA               >   COPY public.subcategories (id, name, category_id) FROM stdin;
    public       postgres    false    209   �       �          0    24579    users 
   TABLE DATA               >   COPY public.users (id, username, password, email) FROM stdin;
    public       postgres    false    197   ˺       �          0    32793    users_roles 
   TABLE DATA               7   COPY public.users_roles (user_id, role_id) FROM stdin;
    public       postgres    false    199   |�       �          0    57376    vat 
   TABLE DATA               .   COPY public.vat (id, value, name) FROM stdin;
    public       postgres    false    211   ��       �          0    41015    vendors 
   TABLE DATA               �   COPY public.vendors (name, bank, fiscal_code, bank_account, currency, vat_code, city, region_id, phone_number, postal_code, business_address, vendor_type, vendor_legal_type, note, id) FROM stdin;
    public       postgres    false    204   �       �           0    0    barcodes_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.barcodes_id_seq', 116, true);
            public       postgres    false    218            �           0    0    categories_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.categories_id_seq', 22, true);
            public       postgres    false    206            �           0    0    document_types_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.document_types_id_seq', 6, true);
            public       postgres    false    222            �           0    0    invoice_products_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.invoice_products_id_seq', 42, true);
            public       postgres    false    226            �           0    0    invoices_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.invoices_id_seq', 19, true);
            public       postgres    false    224            �           0    0    measuring_units_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.measuring_units_id_seq', 120, true);
            public       postgres    false    214            �           0    0    my_organizations_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.my_organizations_id_seq', 10, true);
            public       postgres    false    200            �           0    0 
   plu_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.plu_id_seq', 36, true);
            public       postgres    false    212            �           0    0    products_code_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.products_code_id_seq', 105, true);
            public       postgres    false    220            �           0    0    products_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.products_id_seq', 53, true);
            public       postgres    false    216            �           0    0    regions_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.regions_id_seq', 1, false);
            public       postgres    false    202                        0    0    subcategories_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.subcategories_id_seq', 22, true);
            public       postgres    false    208                       0    0    users_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.users_id_seq', 31, true);
            public       postgres    false    196                       0    0 
   vat_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.vat_id_seq', 10, true);
            public       postgres    false    210                       0    0    vendors_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.vendors_id_seq', 3, true);
            public       postgres    false    205            (           2606    73773    barcodes barcodes_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.barcodes
    ADD CONSTRAINT barcodes_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.barcodes DROP CONSTRAINT barcodes_pkey;
       public         postgres    false    219            *           2606    98342    barcodes barcodes_value_key 
   CONSTRAINT     W   ALTER TABLE ONLY public.barcodes
    ADD CONSTRAINT barcodes_value_key UNIQUE (value);
 E   ALTER TABLE ONLY public.barcodes DROP CONSTRAINT barcodes_value_key;
       public         postgres    false    219                       2606    49198    categories category_id 
   CONSTRAINT     T   ALTER TABLE ONLY public.categories
    ADD CONSTRAINT category_id PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.categories DROP CONSTRAINT category_id;
       public         postgres    false    207            0           2606    106540 &   document_types document_types_name_key 
   CONSTRAINT     a   ALTER TABLE ONLY public.document_types
    ADD CONSTRAINT document_types_name_key UNIQUE (name);
 P   ALTER TABLE ONLY public.document_types DROP CONSTRAINT document_types_name_key;
       public         postgres    false    223            2           2606    106538 "   document_types document_types_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.document_types
    ADD CONSTRAINT document_types_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.document_types DROP CONSTRAINT document_types_pkey;
       public         postgres    false    223                       2606    57381    vat id 
   CONSTRAINT     D   ALTER TABLE ONLY public.vat
    ADD CONSTRAINT id PRIMARY KEY (id);
 0   ALTER TABLE ONLY public.vat DROP CONSTRAINT id;
       public         postgres    false    211            6           2606    139310 &   invoice_products invoice_products_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.invoice_products
    ADD CONSTRAINT invoice_products_pkey PRIMARY KEY (id);
 P   ALTER TABLE ONLY public.invoice_products DROP CONSTRAINT invoice_products_pkey;
       public         postgres    false    227            4           2606    114730    invoices invoices_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.invoices
    ADD CONSTRAINT invoices_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.invoices DROP CONSTRAINT invoices_pkey;
       public         postgres    false    225                       2606    65597 (   measuring_units measuring_units_name_key 
   CONSTRAINT     c   ALTER TABLE ONLY public.measuring_units
    ADD CONSTRAINT measuring_units_name_key UNIQUE (name);
 R   ALTER TABLE ONLY public.measuring_units DROP CONSTRAINT measuring_units_name_key;
       public         postgres    false    215                        2606    65595 $   measuring_units measuring_units_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.measuring_units
    ADD CONSTRAINT measuring_units_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.measuring_units DROP CONSTRAINT measuring_units_pkey;
       public         postgres    false    215            �
           2606    295153 2   my_organizations my_organizations_bank_account_key 
   CONSTRAINT     u   ALTER TABLE ONLY public.my_organizations
    ADD CONSTRAINT my_organizations_bank_account_key UNIQUE (bank_account);
 \   ALTER TABLE ONLY public.my_organizations DROP CONSTRAINT my_organizations_bank_account_key;
       public         postgres    false    201            �
           2606    295151 1   my_organizations my_organizations_fiscal_code_key 
   CONSTRAINT     s   ALTER TABLE ONLY public.my_organizations
    ADD CONSTRAINT my_organizations_fiscal_code_key UNIQUE (fiscal_code);
 [   ALTER TABLE ONLY public.my_organizations DROP CONSTRAINT my_organizations_fiscal_code_key;
       public         postgres    false    201            �
           2606    295149 *   my_organizations my_organizations_name_key 
   CONSTRAINT     e   ALTER TABLE ONLY public.my_organizations
    ADD CONSTRAINT my_organizations_name_key UNIQUE (name);
 T   ALTER TABLE ONLY public.my_organizations DROP CONSTRAINT my_organizations_name_key;
       public         postgres    false    201            �
           2606    32824 &   my_organizations my_organizations_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.my_organizations
    ADD CONSTRAINT my_organizations_pkey PRIMARY KEY (id);
 P   ALTER TABLE ONLY public.my_organizations DROP CONSTRAINT my_organizations_pkey;
       public         postgres    false    201            �
           2606    295155 .   my_organizations my_organizations_vat_code_key 
   CONSTRAINT     m   ALTER TABLE ONLY public.my_organizations
    ADD CONSTRAINT my_organizations_vat_code_key UNIQUE (vat_code);
 X   ALTER TABLE ONLY public.my_organizations DROP CONSTRAINT my_organizations_vat_code_key;
       public         postgres    false    201                       2606    49200    categories name 
   CONSTRAINT     J   ALTER TABLE ONLY public.categories
    ADD CONSTRAINT name UNIQUE (name);
 9   ALTER TABLE ONLY public.categories DROP CONSTRAINT name;
       public         postgres    false    207                       2606    65585    plu plu_pkey 
   CONSTRAINT     J   ALTER TABLE ONLY public.plu
    ADD CONSTRAINT plu_pkey PRIMARY KEY (id);
 6   ALTER TABLE ONLY public.plu DROP CONSTRAINT plu_pkey;
       public         postgres    false    213            ,           2606    81987     products_code products_code_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.products_code
    ADD CONSTRAINT products_code_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.products_code DROP CONSTRAINT products_code_pkey;
       public         postgres    false    221            .           2606    81989 %   products_code products_code_value_key 
   CONSTRAINT     a   ALTER TABLE ONLY public.products_code
    ADD CONSTRAINT products_code_value_key UNIQUE (value);
 O   ALTER TABLE ONLY public.products_code DROP CONSTRAINT products_code_value_key;
       public         postgres    false    221            "           2606    295145    products products_name_rom_key 
   CONSTRAINT     ]   ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_name_rom_key UNIQUE (name_rom);
 H   ALTER TABLE ONLY public.products DROP CONSTRAINT products_name_rom_key;
       public         postgres    false    217            $           2606    295147    products products_name_rus_key 
   CONSTRAINT     ]   ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_name_rus_key UNIQUE (name_rus);
 H   ALTER TABLE ONLY public.products DROP CONSTRAINT products_name_rus_key;
       public         postgres    false    217            &           2606    73765    products products_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.products DROP CONSTRAINT products_pkey;
       public         postgres    false    217            �
           2606    40999    regions region 
   CONSTRAINT     P   ALTER TABLE ONLY public.regions
    ADD CONSTRAINT region UNIQUE (region_name);
 8   ALTER TABLE ONLY public.regions DROP CONSTRAINT region;
       public         postgres    false    203                        2606    40997    regions regions_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.regions
    ADD CONSTRAINT regions_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.regions DROP CONSTRAINT regions_pkey;
       public         postgres    false    203            �
           2606    24589    roles roles_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.roles DROP CONSTRAINT roles_pkey;
       public         postgres    false    198                       2606    295143 $   subcategories subcategories_name_key 
   CONSTRAINT     _   ALTER TABLE ONLY public.subcategories
    ADD CONSTRAINT subcategories_name_key UNIQUE (name);
 N   ALTER TABLE ONLY public.subcategories DROP CONSTRAINT subcategories_name_key;
       public         postgres    false    209                       2606    49208    subcategories subcategory_id 
   CONSTRAINT     Z   ALTER TABLE ONLY public.subcategories
    ADD CONSTRAINT subcategory_id PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.subcategories DROP CONSTRAINT subcategory_id;
       public         postgres    false    209            �
           2606    24600    roles unique role 
   CONSTRAINT     N   ALTER TABLE ONLY public.roles
    ADD CONSTRAINT "unique role" UNIQUE (role);
 =   ALTER TABLE ONLY public.roles DROP CONSTRAINT "unique role";
       public         postgres    false    198            �
           2606    32797    users_roles users-roles_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT "users-roles_pkey" PRIMARY KEY (user_id, role_id);
 H   ALTER TABLE ONLY public.users_roles DROP CONSTRAINT "users-roles_pkey";
       public         postgres    false    199    199            �
           2606    24584    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public         postgres    false    197            �
           2606    180260    users users_username_key 
   CONSTRAINT     W   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_key UNIQUE (username);
 B   ALTER TABLE ONLY public.users DROP CONSTRAINT users_username_key;
       public         postgres    false    197                       2606    65587 	   plu value 
   CONSTRAINT     E   ALTER TABLE ONLY public.plu
    ADD CONSTRAINT value UNIQUE (value);
 3   ALTER TABLE ONLY public.plu DROP CONSTRAINT value;
       public         postgres    false    213                       2606    295141    vat vat_name_key 
   CONSTRAINT     K   ALTER TABLE ONLY public.vat
    ADD CONSTRAINT vat_name_key UNIQUE (name);
 :   ALTER TABLE ONLY public.vat DROP CONSTRAINT vat_name_key;
       public         postgres    false    211                       2606    295139    vat vat_value_key 
   CONSTRAINT     M   ALTER TABLE ONLY public.vat
    ADD CONSTRAINT vat_value_key UNIQUE (value);
 ;   ALTER TABLE ONLY public.vat DROP CONSTRAINT vat_value_key;
       public         postgres    false    211                       2606    295135     vendors vendors_bank_account_key 
   CONSTRAINT     c   ALTER TABLE ONLY public.vendors
    ADD CONSTRAINT vendors_bank_account_key UNIQUE (bank_account);
 J   ALTER TABLE ONLY public.vendors DROP CONSTRAINT vendors_bank_account_key;
       public         postgres    false    204                       2606    295133    vendors vendors_fiscal_code_key 
   CONSTRAINT     a   ALTER TABLE ONLY public.vendors
    ADD CONSTRAINT vendors_fiscal_code_key UNIQUE (fiscal_code);
 I   ALTER TABLE ONLY public.vendors DROP CONSTRAINT vendors_fiscal_code_key;
       public         postgres    false    204                       2606    295131    vendors vendors_name_key 
   CONSTRAINT     S   ALTER TABLE ONLY public.vendors
    ADD CONSTRAINT vendors_name_key UNIQUE (name);
 B   ALTER TABLE ONLY public.vendors DROP CONSTRAINT vendors_name_key;
       public         postgres    false    204                       2606    41053    vendors vendors_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.vendors
    ADD CONSTRAINT vendors_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.vendors DROP CONSTRAINT vendors_pkey;
       public         postgres    false    204            
           2606    295137    vendors vendors_vat_code_key 
   CONSTRAINT     [   ALTER TABLE ONLY public.vendors
    ADD CONSTRAINT vendors_vat_code_key UNIQUE (vat_code);
 F   ALTER TABLE ONLY public.vendors DROP CONSTRAINT vendors_vat_code_key;
       public         postgres    false    204            ?           2606    98334 !   barcodes barcodes_product_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.barcodes
    ADD CONSTRAINT barcodes_product_id_fkey FOREIGN KEY (product_id) REFERENCES public.products(id) ON UPDATE RESTRICT ON DELETE RESTRICT NOT VALID;
 K   ALTER TABLE ONLY public.barcodes DROP CONSTRAINT barcodes_product_id_fkey;
       public       postgres    false    2854    217    219            8           2606    49209 0   subcategories category_id - id public.categories    FK CONSTRAINT     �   ALTER TABLE ONLY public.subcategories
    ADD CONSTRAINT "category_id - id public.categories" FOREIGN KEY (category_id) REFERENCES public.categories(id);
 \   ALTER TABLE ONLY public.subcategories DROP CONSTRAINT "category_id - id public.categories";
       public       postgres    false    207    2828    209            C           2606    139311 1   invoice_products invoice_products_invoice_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.invoice_products
    ADD CONSTRAINT invoice_products_invoice_id_fkey FOREIGN KEY (invoice_id) REFERENCES public.invoices(id);
 [   ALTER TABLE ONLY public.invoice_products DROP CONSTRAINT invoice_products_invoice_id_fkey;
       public       postgres    false    227    225    2868            D           2606    139316 1   invoice_products invoice_products_product_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.invoice_products
    ADD CONSTRAINT invoice_products_product_id_fkey FOREIGN KEY (product_id) REFERENCES public.products(id);
 [   ALTER TABLE ONLY public.invoice_products DROP CONSTRAINT invoice_products_product_id_fkey;
       public       postgres    false    217    2854    227            B           2606    122915 '   invoices invoices_document_type_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.invoices
    ADD CONSTRAINT invoices_document_type_id_fkey FOREIGN KEY (document_type_id) REFERENCES public.document_types(id) NOT VALID;
 Q   ALTER TABLE ONLY public.invoices DROP CONSTRAINT invoices_document_type_id_fkey;
       public       postgres    false    225    223    2866            @           2606    114736 )   invoices invoices_my_organization_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.invoices
    ADD CONSTRAINT invoices_my_organization_id_fkey FOREIGN KEY (my_organization_id) REFERENCES public.my_organizations(id);
 S   ALTER TABLE ONLY public.invoices DROP CONSTRAINT invoices_my_organization_id_fkey;
       public       postgres    false    225    2810    201            A           2606    114741     invoices invoices_vendor_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.invoices
    ADD CONSTRAINT invoices_vendor_id_fkey FOREIGN KEY (vendor_id) REFERENCES public.vendors(id);
 J   ALTER TABLE ONLY public.invoices DROP CONSTRAINT invoices_vendor_id_fkey;
       public       postgres    false    225    2824    204            9           2606    81955 "   products products_category_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_category_id_fkey FOREIGN KEY (category_id) REFERENCES public.categories(id) NOT VALID;
 L   ALTER TABLE ONLY public.products DROP CONSTRAINT products_category_id_fkey;
       public       postgres    false    2828    207    217            ;           2606    81965 (   products products_measuring_unit_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_measuring_unit_id_fkey FOREIGN KEY (measuring_unit_id) REFERENCES public.measuring_units(id) NOT VALID;
 R   ALTER TABLE ONLY public.products DROP CONSTRAINT products_measuring_unit_id_fkey;
       public       postgres    false    217    2848    215            >           2606    90152    products products_plu_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_plu_id_fkey FOREIGN KEY (plu_id) REFERENCES public.plu(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;
 G   ALTER TABLE ONLY public.products DROP CONSTRAINT products_plu_id_fkey;
       public       postgres    false    213    2842    217            =           2606    90142 &   products products_product_code_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_product_code_id_fkey FOREIGN KEY (product_code_id) REFERENCES public.products_code(id) ON DELETE CASCADE NOT VALID;
 P   ALTER TABLE ONLY public.products DROP CONSTRAINT products_product_code_id_fkey;
       public       postgres    false    217    2860    221            :           2606    81960 %   products products_subcategory_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_subcategory_id_fkey FOREIGN KEY (subcategory_id) REFERENCES public.subcategories(id) NOT VALID;
 O   ALTER TABLE ONLY public.products DROP CONSTRAINT products_subcategory_id_fkey;
       public       postgres    false    2834    217    209            <           2606    81970    products products_vat_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_vat_id_fkey FOREIGN KEY (vat_id) REFERENCES public.vat(id) NOT VALID;
 G   ALTER TABLE ONLY public.products DROP CONSTRAINT products_vat_id_fkey;
       public       postgres    false    2836    211    217            7           2606    41026    vendors vendor_region    FK CONSTRAINT     x   ALTER TABLE ONLY public.vendors
    ADD CONSTRAINT vendor_region FOREIGN KEY (region_id) REFERENCES public.regions(id);
 ?   ALTER TABLE ONLY public.vendors DROP CONSTRAINT vendor_region;
       public       postgres    false    203    204    2816            �   S   x�U���@Ck<L�7L��#�RDq�'D㦧\�m9T6�3�'�P[���(Z�K���>O��U��P��KC�/ ��      �   z   x�=�;�0D�S��	��5��];+�X�����O��0=7��b������+'iE'�ɢ����Ֆmz;������w�Oi7���$H/T��Q�+E	1������c��	��&+      �   %   x�3���K��MU��+��LN�2��/-A����� �C      �   O   x�-���@cQ��^��9+��ݑ6��j,d��<v<�+h�H�������ڦA����hu��Q�u��z��>���      �   J   x�Mȱ�0D���K��;�L��	ؿ��/��X/Q�8������}�g/:��j.τ���tѶ���z�      �      x�3��N�2�L*M����� >      �   �   x�%�1�  ��|�L8�N�*�V�i΀���1�7���6{�a�gO��(�A��K
6m$�Ϝ|��v_��C�;*���L(ecZ�e�S)�1��_(�i�O���5�@)P��h�+��-�����xec��o}*(      �      x�36�4�26�4�26�4����� �      �   &  x�e�=N1���S����'-@��4V��%+�ֻe��������&�a�F�ޅ,��<o<O��HW�Usr�Kc�&��E	��M{�>`3l�݀ 	"�)))OA2ʘW9Lǐ(|�X�2��3rV�j���|�/�$�C
9���e8��
X�V�����K���%�|���p��)���7�$��7=�dmf�[y�lp�Q7>�g�x����x��'�d0�U]jk���#���bDGGp9�5pj���z�;�
��)���a��{��7��]���2*"�;�[��4I�oN�      �   X   x�Eϱ�@Cј_�ZXl�^�_�����K��7��f$�&=�E�Q�Wd��	�h��t��N��.42F�2_�_f�̽{U*t      �   R  x�U�MS�0����Q/�i�����3
3P=yY�Hw&l����w[�\����O֌>Z����lVS"��!��o�E0�#60��"�C�0���Z�̮��+��n�F
,GO�([��3�b[��DxP��4<���փ� /�������)���M�U�*�=%Of�,J�>79�y��X5�1�u7��v�iHL�+b׀Q�z��gsuX�OL^|��'sUx%.O1uxC�}N�]8"�*�$�P�S*�*��i�U��D�����!��k��=V�o�w.ʠ���+侫�T��j�M�~���P	�l��kա�-�ݲ�*NVuzE->yWw��7 ����      �   !   x�3���q�v�2��]|=��b���� t��      �   �   x�UO��0��_�/@u�d�HHL,Q�@$�P��~Ҩ�'Y���g� Q�L�pR̋0���ju�V�Z�C5�2*jpS�m����j�p[�eh��z�\����pK�Hw�quN��)�ۤ�y��;��!�9�)�M��>!��QFYm��8��PDX      �   �   x�=���   �3<�g�~��f�4����u!D�i����d�|P��|�5'�-�5�QנՅ�(���c�1D,j{���`�<�[u�$G;_;�L�[��7���5���Oq��/�t^�6{F����J�)��J��H��y��z�HPL*����� ��:U      �      x�34�4�2c#�=... �?      �   7   x�3�42 "U.#NNU.cNSNSU.N��̒����0�UM�=... �-�      �     x����n�@���O�h�����\�������6v�M;8vK��)��i@���.�B��D����f�9��ͺ[Vݲ���8�6��P_�'�G��Q��'Z�I�!�x��H(Bqx7��pB"�fD3���Ji�I׆"��U��������oʫ �r>��S��|�s�W����?��<�ϛ�h��8���*��8*��˺�ݍx�B��zQ���Y\N8�J+�H
�H�J$����M�oY?����ի����a� Ɛa��+GJXi�[� $�t~\�n����&^����$�'��ƂE+�g�8e��� ��F�;�m�!�뛦��D@�����A�zC���(݂�C@�"�[ĝ!�ʖ������<�D i���9�l$����k�"�A' �ȠvǠ��rӽ}]�E��ge@��ڀf/[K�:yxˠ��I����Ӻ�W��C%:V��MW
̞�U�L��h2V1˰�i�@d-z����˱P�-�_�4.�y	��Tt�_�f~��Qy
ȓ��-8����B�SЬ�ЉT�# ���ɿr�ʞ5m��N��Aōo-	4�?�?���d�tO�;;N��s;��-ì!{ܴס-�uW�u�bS3ڠ|r��������?ǌ��cZ�>	0�`�w`�Xh~�o�fq>��U�F?���yů�y�x���VR#�K�>���`K����)�C9P��f����uQ[��X=�B�E� 4�+k����p�m�����3���z&�R����n۳�CٛY�}���     