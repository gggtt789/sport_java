DROP DATABASE IF EXISTS sport_test;
DROP ROLE IF EXISTS sport_admin_test;

CREATE DATABASE sport_test
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'ru_RU.UTF-8'
    LC_CTYPE = 'ru_RU.UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE ROLE sport_admin_test WITH LOGIN PASSWORD '1';
GRANT CONNECT ON DATABASE sport_test TO sport_admin_test;
