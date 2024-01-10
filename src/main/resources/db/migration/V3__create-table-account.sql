CREATE TABLE account (
    id TEXT PRIMARY KEY NOT NULL,
    owner TEXT NOT NULL,
    account_number INT UNIQUE NOT NULL,
    balance DOUBLE PRECISION NOT NULL,
    FOREIGN KEY (owner) REFERENCES users(id)
);
