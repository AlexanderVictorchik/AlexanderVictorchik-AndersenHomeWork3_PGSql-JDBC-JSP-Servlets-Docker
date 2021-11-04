CREATE TABLE public.phone
(
    phone_id SERIAL,
    price integer,
    model character varying(50) COLLATE pg_catalog."default",
    vendor_id integer,
    CONSTRAINT phone_pkey PRIMARY KEY (phone_id),
    CONSTRAINT phone_vendor_id_fkey FOREIGN KEY (vendor_id)
        REFERENCES public.vendor (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

CREATE TABLE public.vendor
(
    id SERIAL,
    name character varying(50) COLLATE pg_catalog."default",
    site character varying(50) COLLATE pg_catalog."default",
    CONSTRAINT vendor_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;