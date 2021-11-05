#!/bin/bash
set -e
export PGPASSWORD=$POSTGRES_PASSWORD;
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
  CREATE USER $APP_DB_USER WITH PASSWORD '$APP_DB_PASS';
  CREATE DATABASE $APP_DB_NAME;
  GRANT ALL PRIVILEGES ON DATABASE $APP_DB_NAME TO $APP_DB_USER;
  \connect $APP_DB_NAME $APP_DB_USER
  BEGIN;
    CREATE TABLE vendor
    (id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    site VARCHAR(50));
   CREATE TABLE phone
   (phone_id SERIAL PRIMARY KEY,
    price INTEGER,
    model VARCHAR(50),
    vendor_id INTEGER REFERENCES vendor(id));
       
      INSERT INTO vendor (name,site)
      VALUES ('LuxuryBrand1','luxurybrand1.com'),
      ('NormalBrand1','normalbrand1.com'),
      ('AffordableBrand1','affordablebrand1.com');
      INSERT INTO phone (price,model,vendor_id)
      VALUES (2000,'LuxuryModel1',1),
      (600,'NormalModel1',2),
      (200,'AffordableModel1',3);
  COMMIT;
EOSQL