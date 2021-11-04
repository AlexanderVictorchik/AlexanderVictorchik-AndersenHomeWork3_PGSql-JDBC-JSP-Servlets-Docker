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

# private static final String INSERT_VENDORS_SQL =
#             "insert into vendor (name, site) values ( ?, ?) returning id";
#     private static final String INSERT_PHONES_SQL =
#             "insert into phone (price,model,vendor_id) values ( ?, ?, ?)";
#
#     private static final String SELECT_PHONE_BY_ID =
#             "select phone_id,price,model,name,site from phone " +
#                     "join vendor ON vendor.id=phone.vendor_id where phone.phone_id=?";
#
#     private static final String SELECT_ALL_PHONES =
#             "select phone_id,price,model,name,site from phone join vendor on vendor.id=phone.vendor_id";
#
#     private static final String DELETE_PHONES_SQL = "delete from phone where phone_id = ?;";
#
#     private static final String UPDATE_VENDORS_SQL =
#             "update vendor set name = ? ,site = ? where id= ?";
#     private static final String UPDATE_PHONES_SQL =
#             "update phone set price = ?, model = ? where phone_id = ? returning vendor_id";