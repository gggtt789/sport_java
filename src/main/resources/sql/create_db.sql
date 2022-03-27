DROP DATABASE IF EXISTS sport;
DROP ROLE IF EXISTS sport_admin;

CREATE DATABASE sport
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'ru_RU.UTF-8'
    LC_CTYPE = 'ru_RU.UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE ROLE sport_admin WITH LOGIN PASSWORD '123456';
GRANT CONNECT ON DATABASE sport TO sport_admin;