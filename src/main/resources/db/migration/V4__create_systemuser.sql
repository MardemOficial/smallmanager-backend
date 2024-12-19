CREATE TABLE IF NOT EXISTS SystemUser (
    id uuid unique not null,
    name varchar (150),
    password varchar(255),
    primary key(id)
);