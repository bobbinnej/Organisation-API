--This line tells h2 database that the mode i am using is for postgresql--
SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS users(
 id int  PRIMARY KEY auto_increment,
  name varchar,
  department_id varchar,
  staff_position varchar,
  role varchar
);

CREATE TABLE IF NOT EXISTS departments(
 id int  PRIMARY KEY auto_increment,
 name varchar,
 description varchar,
 dpt_size int

);

CREATE TABLE IF NOT EXISTS news(
 id int  PRIMARY KEY auto_increment,
 content varchar,
 department_id varchar,
 user_id varchar,
 type varchar,
 postdate Timestamp

);
