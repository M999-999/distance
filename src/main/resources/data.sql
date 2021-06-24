-- INSERT INTO TBL_EMPLOYEES (first_name, last_name, email) VALUES
-- ('Lokesh', 'Gupta', 'abc@gmail.com'),
-- ('Deja', 'Vu', 'xyz@email.com'),
-- ('Caption', 'America', 'cap@marvel.com');

insert into cities2 values (1, 'A');
insert into cities2 values (2, 'B');
insert into cities2 values (3, 'C');
insert into cities2 values (4, 'E');
insert into cities2 values (5, 'F');
insert into cities2 values (6, 'I');

-- A - B
insert into cities_distance2 values (1, 2, 3);
-- A - E
insert into cities_distance2 values (1, 4, 3);
-- B - C
insert into cities_distance2 values (2, 3, 4);
-- B - E
insert into cities_distance2 values (2, 4, 3);
-- F - C
insert into cities_distance2 values (5, 3, 3);
-- F - E
insert into cities_distance2 values (5, 4, 3);
-- F - I
insert into cities_distance2 values (5, 6, 3);
