drop table T_USER if exists;

-- Firstname, Lastname, Role, Password
CREATE TABLE T_USER (
    id bigint identity primary key,
    first_name varchar(255),
    last_name varchar(255),
    role varchar(255),
    password varchar(255)
)

-- create table T_USER (ID bigint identity primary key, NUMBER varchar(9),
--                        NAME varchar(50) not null, BALANCE decimal(8,2), unique(NUMBER));
                        
-- ALTER TABLE T_USER ALTER COLUMN BALANCE SET DEFAULT 0.0;
