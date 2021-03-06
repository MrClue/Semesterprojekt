-- lets make sure everyone has he same password
ALTER USER postgres WITH PASSWORD 'password';

-- now lets create the database used for this project
CREATE DATABASE ecms;

-- lets create the tables used by the application
CREATE TABLE Program (
  ID SERIAL PRIMARY KEY,
  ProgramTitle VARCHAR(50) NOT NULL
);

CREATE TABLE Credits (
    ID SERIAL PRIMARY KEY,
    Program_ID INTEGER NOT NULL REFERENCES Program(ID),
    Occupation VARCHAR(50) NOT NULL,
    Person VARCHAR(50) NOT NULL
);

CREATE TABLE Login (
  ID SERIAL PRIMARY KEY,
  Username VARCHAR(50) UNIQUE NOT NULL,
  Password VARCHAR(50) NOT NULL,
  Authlvl INTEGER NOT NULL
);
