CREATE TABLE USER (
  ID int not null primary key,
  EMAIL varchar(255) not null,
  PASSWORD varchar(255),
  CREATED_AT timestamp not null,
  STATUS_CODE char(3) not null
);

CREATE TABLE OFFERSET (
  ID int not null primary key,
  USER_ID int not null,
  NAME varchar(45)
);

CREATE TABLE OFFER (
  ID int not null primary key,
  OFFERSET_ID int not null,
  TARGET_CLASS varchar(45) not null,
  CONTENT_CLASS varchar(45) not null
);

CREATE TABLE LOGIN (
  ID int not null primary key,
  USER_ID int not null,
  LOGGED_IN timestamp not null
);

CREATE TABLE STATUS (
  CODE CHAR(3) not null primary key,
  NAME varchar(45) not null
);

CREATE TABLE OFFER_LOG (
  ID int not null primary key,
  OFFERSET_ID int not null,
  OFFER_ID int not null,
  TIMESTAMP timestamp not null
);
