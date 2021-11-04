CREATE TABLE vendor (id SERIAL PRIMARY KEY, vendor_name VARCHAR(50),  vendor_site VARCHAR(50));

CREATE TABLE phone (phone_id SERIAL PRIMARY KEY, price INTEGER, model VARCHAR(50), vendor_id INTEGER REFERENCES vendor(id));