# GBX Challenge

## Descrição
Este projeto é uma REST API desenvolvida em Java com o framework Spring Boot. Ele utiliza Flyway para migrações de banco de dados, e PostgreSQL como banco de dados. O projeto gerencia informações sobre usuários, contas e transferências.

## Configuração
1. Clone o repositório:
git clone https://github.com/CaianMarcinkowski/gbx-challenge
2. Configure o arquivo "src/main/resources/application.properties" com as informações de seu banco de dados

# Endpoints
* Liste usuários: GET /user
* Crie um novo usuário: POST /user
  * Exemplo de Json para o POST:     
   ```
    {
    "name": "Alcemar da Silva Silva",
    "cpf": "345.987.345-23",
    "date_of_birth": "1981-11-21"
    }
* Liste contas: GET /account
* Crie uma nova conta: POST /account
  * Exemplo de Json para o POST:
  ```
    {
    "owner": "43459dae-35e0-4de9-be51-dcb6dac20070",
    "account_number": 987654321,
    "balance": 5613.45
    }
Obs.: owner é uma foreign key de usuário, referenciada na tabela users, este valor deve ser o mesmo de uma chave primária da coluna id da tabela users, para mais detalhes consultar o arquivo "src/main/resources/db/migration/V1__create-tables.sql"
* Liste transferências: GET /transaction
* Crie uma nova transferência: POST /transaction
  * Exemplo de Json para o POST:
  ```
    {
    "origin": "da57bf34-c898-48c8-b3c4-2db0a775a1f0",
    "destiny": "bc87393b-08b8-47e3-9784-a25f3d62c022",
    "value": 100
    }
Obs.:Os valores de destiny e origin são foreign key referenciados da tabela account (id), para mais detalhes, consultar o arquivo "src/main/resources/db/migration/V1__create-tables.sql"

## Banco de dados

* Caso queira usar, eu exportei as tabelas do banco de dados, esta em "tables_db"

* Estrutura do banco de dados
    ```
    CREATE TABLE users (
    id TEXT PRIMARY KEY NOT NULL,
    name TEXT NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    date_of_birth DATE NOT NULL
    );

    CREATE TABLE accounts (
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
    FOREIGN KEY (origin) REFERENCES accounts(id),
    FOREIGN KEY (destiny) REFERENCES accounts(id)
    );

## Tecnologias Utilizadas
* Java 21
* Spring Boot
* Flyway
* PostgreSQL
