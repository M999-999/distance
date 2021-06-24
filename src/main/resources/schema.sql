-- DROP TABLE IF EXISTS TBL_EMPLOYEES;
--
-- CREATE TABLE TBL_EMPLOYEES (
--                                id INT AUTO_INCREMENT  PRIMARY KEY,
--                                first_name VARCHAR(250) NOT NULL,
--                                last_name VARCHAR(250) NOT NULL,
--                                email VARCHAR(250) DEFAULT NULL
-- );

drop table cities2 if exists;
drop table cities_distance2 if exists;
create table cities2 (id bigint not null, mame varchar(255), primary key (id));
create table cities_distance2 (source_city_id bigint not null, target_city_id bigint not null, distance int, primary key (source_city_id, target_city_id));
alter table cities_distance2 add constraint FKjrfhfx39jy7oq1iyoxnc37ukv foreign key (source_city_id) references cities2;
alter table cities_distance2 add constraint FK46f2ebfvlgth1yx0xuaxugav6 foreign key (target_city_id) references cities2;