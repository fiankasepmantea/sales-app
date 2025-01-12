PGDMP     $            
         }            salesdb    14.1    14.1                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    50284    salesdb    DATABASE     g   CREATE DATABASE salesdb WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'English_Indonesia.1252';
    DROP DATABASE salesdb;
                postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                postgres    false            	           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                   postgres    false    3            �            1259    50389    products    TABLE     �  CREATE TABLE public.products (
    id uuid NOT NULL,
    name character varying(255) NOT NULL,
    price numeric(10,2) NOT NULL,
    stock integer NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_by character varying(255),
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_by character varying(255),
    deleted_at timestamp without time zone,
    deleted_by character varying(255)
);
    DROP TABLE public.products;
       public         heap    postgres    false    3            �            1259    50398    transactions    TABLE     
  CREATE TABLE public.transactions (
    id uuid NOT NULL,
    product_id uuid,
    quantity integer NOT NULL,
    total numeric(10,2) NOT NULL,
    transaction_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_by character varying(255),
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_by character varying(255),
    deleted_at timestamp without time zone,
    deleted_by character varying(255)
);
     DROP TABLE public.transactions;
       public         heap    postgres    false    3            �            1259    50376    users    TABLE     �  CREATE TABLE public.users (
    id uuid NOT NULL,
    username character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    role character varying(50) NOT NULL,
    is_activated boolean DEFAULT false,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_by character varying(255),
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_by character varying(255),
    deleted_at timestamp without time zone,
    deleted_by character varying(255),
    CONSTRAINT users_role_check CHECK (((role)::text = ANY ((ARRAY['ADMIN'::character varying, 'KASIR'::character varying])::text[])))
);
    DROP TABLE public.users;
       public         heap    postgres    false    3                      0    50389    products 
   TABLE DATA           �   COPY public.products (id, name, price, stock, created_at, created_by, updated_at, updated_by, deleted_at, deleted_by) FROM stdin;
    public          postgres    false    210   �                 0    50398    transactions 
   TABLE DATA           �   COPY public.transactions (id, product_id, quantity, total, transaction_date, created_at, created_by, updated_at, updated_by, deleted_at, deleted_by) FROM stdin;
    public          postgres    false    211   �                  0    50376    users 
   TABLE DATA           �   COPY public.users (id, username, password, role, is_activated, created_at, created_by, updated_at, updated_by, deleted_at, deleted_by) FROM stdin;
    public          postgres    false    209          q           2606    50397    products products_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.products DROP CONSTRAINT products_pkey;
       public            postgres    false    210            s           2606    50407    transactions transactions_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.transactions
    ADD CONSTRAINT transactions_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.transactions DROP CONSTRAINT transactions_pkey;
       public            postgres    false    211            m           2606    50386    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    209            o           2606    50388    users users_username_key 
   CONSTRAINT     W   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_key UNIQUE (username);
 B   ALTER TABLE ONLY public.users DROP CONSTRAINT users_username_key;
       public            postgres    false    209            t           2606    50408 )   transactions transactions_product_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.transactions
    ADD CONSTRAINT transactions_product_id_fkey FOREIGN KEY (product_id) REFERENCES public.products(id) ON DELETE CASCADE;
 S   ALTER TABLE ONLY public.transactions DROP CONSTRAINT transactions_product_id_fkey;
       public          postgres    false    211    3185    210                  x������ � �            x������ � �             x������ � �     