DROP TABLE IF EXISTS status ;
CREATE TABLE status (
  code CHAR(3) NOT NULL,
  name VARCHAR(45) NOT NULL,
  PRIMARY KEY (code),
  UNIQUE INDEX code_UNIQUE (code ASC),
  UNIQUE INDEX name_UNIQUE (name ASC));

DROP TABLE IF EXISTS user;
CREATE TABLE user (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  email VARCHAR(100) NOT NULL,
  password VARCHAR(255) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX id_UNIQUE (id ASC),
  UNIQUE INDEX email_UNIQUE (email ASC));

DROP TABLE IF EXISTS login;
CREATE TABLE login (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  user_id INT UNSIGNED NOT NULL,
  logged_in TIMESTAMP NOT NULL);

DROP TABLE IF EXISTS offerset;
CREATE TABLE offerset (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  user_id INT UNSIGNED NOT NULL,
  name VARCHAR(45) NOT NULL DEFAULT 'unnamed',
  status_code VARCHAR(45) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX offerset_id_UNIQUE (id ASC));

DROP TABLE IF EXISTS offer;
CREATE TABLE offer (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  offerset_id INT UNSIGNED NOT NULL,
  target_class VARCHAR(45) NOT NULL,
  content_class VARCHAR(45) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX offer_id_UNIQUE (id ASC));

DROP TABLE IF EXISTS offer_log;
CREATE TABLE offer_log (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  offerset_id INT NOT NULL,
  offer_id INT NOT NULL,
  timestamp TIMESTAMP NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX offer_log_id_UNIQUE (id ASC));

# insert test data
insert into user (id, email , password, created_at  ) values ( 1, 'test1@hoge.com', 'test', current_date ) ;
insert into user (id, email , password, created_at  ) values ( 2, 'test2@hoge.com', 'test', current_date ) ;
insert into user (id, email , password, created_at  ) values ( 3, 'test3@hoge.com', 'test', current_date ) ;
insert into user (id, email , password, created_at  ) values ( 4, 'test4@hoge.com', 'test', current_date ) ;
insert into user (id, email , password, created_at  ) values ( 5, 'test5@hoge.com', 'test', current_date ) ;

insert into login ( id , user_id , logged_in ) values ( 1, 1, current_timestamp );
insert into login ( id , user_id , logged_in ) values ( 2, 2, current_timestamp );


insert into offerset ( id , user_id ,name , status_code ) values (1, 1,'test1-1','FREE_PLAN');
insert into offerset ( id , user_id ,name , status_code ) values (11, 1,'test1-2','FREE_PLAN');
insert into offerset ( id , user_id ,name , status_code ) values (111, 1,'test1-3','PREMIUM');
insert into offerset ( id , user_id ,name , status_code ) values (1111, 2,'test2-1','CAMPAIGN');

insert into offer ( id, offerset_id , target_class , content_class ) values ( 1, 1, 'test_target1-1', 'test_content1-1') ;
insert into offer ( id, offerset_id , target_class , content_class ) values ( 2, 1, 'test_target1-2', 'test_content1-2') ;
insert into offer ( id, offerset_id , target_class , content_class ) values ( 3, 11, 'test_target2-1', 'test_content2-1') ;
insert into offer ( id, offerset_id , target_class , content_class ) values ( 4, 111, 'test_target3-1', 'test_content3-1') ;
insert into offer ( id, offerset_id , target_class , content_class ) values ( 5, 1111, 'test_target4-1', 'test_content4-1') ;
