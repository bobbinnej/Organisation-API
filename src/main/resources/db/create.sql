--This line tells h2 database that the mode i am using is for postgresql--
SET MODE PostgreSQL;

CREATE IF NOT EXISTS staff(
  department_id int,
  id serial PRIMARY KEY,
  name varchar,
  office_position varchar,
  role varchar
);

CREATE TABLE IF NOT EXISTS departments(
 id serial PRIMARY KEY,
 name varchar,
 description varchar,
 dpt_size int

);

CREATE TABLE IF NOT EXISTS news(
 id serial PRIMARY KEY,
 content varchar,
 department_id varchar,
 user_id varchar,
 title varchar,
 postdate timestamp

);
