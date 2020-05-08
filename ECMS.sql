CREATE TABLE Program (
  ID SERIAL PRIMARY KEY,
  ProgramTitle VARCHAR(50) NOT NULL
);

-- todo: (Program_ID + Occupation + Person) skal være en combined key (altså noget som er unique).
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
