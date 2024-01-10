CREATE TABLE users (
    id TEXT PRIMARY KEY NOT NULL,
    name TEXT NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    date_of_birth DATE NOT NULL
);

CREATE TABLE account (
    id TEXT PRIMARY KEY NOT NULL,
    owner TEXT NOT NULL,
    account_number INT UNIQUE NOT NULL,
    balance DOUBLE PRECISION NOT NULL,
    FOREIGN KEY (owner) REFERENCES users(id)
);

CREATE TABLE transactions (
    id TEXT PRIMARY KEY NOT NULL,
    origin TEXT NOT NULL,
    destiny TEXT NOT NULL,
    value DOUBLE PRECISION NOT NULL,
    date DATE NOT NULL,
    FOREIGN KEY (origin) REFERENCES account(id),
    FOREIGN KEY (destiny) REFERENCES account(id)
);
