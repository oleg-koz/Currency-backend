--liquibase formatted sql

--changeset oleg:create-currency_ticker-table logicalFilePath:/db/changelog/001-schema.sql
create table currency_ticker (
    valiutos_kodas varchar(255) not null unique,
    pavadinimas varchar(255) not null unique,
    santykis varchar(255) not null,
    data varchar(255) not null,
    PRIMARY KEY(valiutos_kodas, data)
);
--rollback drop table currency_ticker;