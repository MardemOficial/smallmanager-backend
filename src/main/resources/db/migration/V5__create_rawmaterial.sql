CREATE TABLE IF NOT EXISTS rawmaterial (
    id UUID DEFAULT gen_random_uuid() NOT NULL,
    code VARCHAR(10) NOT NULL,
    description VARCHAR (150) NOT NULL,
    price MONEY,
    PRIMARY KEY(id)
);