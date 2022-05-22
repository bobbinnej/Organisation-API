#  Organisation-API
### By Jackline Njoroge
## Description
This is a bare-bones example of a News application providing a REST
API to an organisatio-API News Portal.

The entire application is contained within the app.java file.

## Setup and Installation
* Fork this repo from https://github.com/bobbinnej/Organisation-API
* Clone this repo from https://github.com/bobbinnej/Organisation-API.git
* Open terminal
* Create Organisation-API directory then cd Organisation-API
*  On any terminal type  the command git clone and paste the url  https://github.com/bobbinnej/Organisation-API.git and then press enter
### Install

    gradle install

### Run the app

    gradle run
## Setup Requirements for Database
* We will use h2 database that stores its data in memory
  SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS users(
id int  PRIMARY KEY auto_increment,
name varchar,
department_id int,
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
department_id int,
user_id varchar,
type varchar,
title varchar

);
## Technology used
- Java
- SparkJava
- Gradle
- JUnit 4
- Git
- IntelliJ IDEA community Edition
- Postgres
- Sql2o


## Working of the API
Models present

- Department
- DepartmentNews
- News
- Users

DepartmentNews uses the concept of objects within object by inheriting from News.

API routes
- GET "/users"
- GET "/users/:id"
- GET "/departments"

- GET "/departments/:id"
- GET "/departments/:id/users"
- GET "/departments/:id/news"
- GET "/news"
- GET "/news/general"
- GET "/news/department"

- POST "/Users/new"
- POST "/Departments/new"
- POST "/News/new"
- POST "/DepartmentNews/new"

## Development

To fix a bug or enhance an existing module, follow these steps:
- Fork the repo
- Create a new branch (git checkout -b improve-feature)
- Make the appropriate changes in the files
- Add changes to reflect the changes made
- Commit your changes (git commit -am 'Improve feature')
- Push to the branch (git push origin improve-feature)
- Create a Pull Request


## Known Bugs

If you find a bug or would like to request a new function, reach out to me via bobbinnej@gmail.com

 
