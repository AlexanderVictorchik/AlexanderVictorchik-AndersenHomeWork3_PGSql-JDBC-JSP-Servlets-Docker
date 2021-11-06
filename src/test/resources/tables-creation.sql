BEGIN;
CREATE TABLE IF NOT EXISTS vendor1 (id SERIAL PRIMARY KEY,
                                    name VARCHAR(70),
    site VARCHAR(70));

CREATE TABLE IF NOT EXISTS phone1 (phone_id SERIAL PRIMARY KEY,
                                   price INTEGER,
                                   model VARCHAR(70),
    vendor1_id INTEGER REFERENCES vendor1(id));
COMMIT;

