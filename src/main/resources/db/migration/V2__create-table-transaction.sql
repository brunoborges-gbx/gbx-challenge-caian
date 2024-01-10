CREATE TABLE transactions (
    id TEXT PRIMARY KEY NOT NULL,
    origin TEXT NOT NULL,
    destiny TEXT NOT NULL,
    value DOUBLE PRECISION NOT NULL,
    date DATE NOT NULL,
    FOREIGN KEY (origin) REFERENCES users(id),
    FOREIGN KEY (destiny) REFERENCES users(id)
);
