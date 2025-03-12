CREATE TABLE IF NOT EXISTS userss (
    id uuid DEFAULT gen_random_uuid() not null,
    name varchar (150),
    password varchar(255),
    primary key(id)
);