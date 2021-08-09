CREATE TABLE IF NOT EXISTS country (
                                       id BIGINT AUTO_INCREMENT  PRIMARY KEY,
                                       name VARCHAR(250) NOT NULL UNIQUE,
                                       created datetime  NOT NULL,
                                       last_modified datetime NULL
    );

CREATE TABLE IF NOT EXISTS city (
                                    id BIGINT AUTO_INCREMENT  PRIMARY KEY,
                                    name VARCHAR(250) NOT NULL UNIQUE,
    country_id BIGINT NOT NULL ,
    created datetime  NOT NULL,
    last_modified datetime NULL,
    FOREIGN KEY (country_id) REFERENCES country(id));


CREATE TABLE IF NOT EXISTS patient (
                                       id BIGINT AUTO_INCREMENT  PRIMARY KEY,
                                       first_name VARCHAR(250) NOT NULL,
    last_name VARCHAR(250) NOT NULL,
    gender VARCHAR(250) NOT NULL,
    city_id BIGINT NOT NULL ,
    country_id BIGINT NOT NULL ,
    age INT NOT NULL,
    diabetes_state VARCHAR(250) NULL,
    created datetime  NOT NULL,
    last_modified datetime NULL,
    FOREIGN KEY (city_id) REFERENCES country(id),
    FOREIGN KEY (country_id) REFERENCES country(id));

INSERT INTO country (id, name, created)
VALUES (1, 'France', '2021-08-09 15:01:34'),
       (2, 'Algeria', '2021-08-09 15:01:34'),
       (3, 'Germany', '2021-08-09 15:01:34');

INSERT INTO city (id, name, country_id, created)
VALUES (1, 'Paris', 1, '2021-08-09 15:01:34'),
       (2, 'Alger', 2, '2021-08-09 15:01:34'),
       (3, 'Brussels', 3, '2021-08-09 15:01:34');

INSERT INTO patient (id, first_name, last_name, gender, city_id, country_id, age, created)
VALUES (1, 'Jean', 'Dupont', 'MALE', 1, 1, 46, '2021-08-09 15:01:34'),
       (2, 'Amina', 'Taleb', 'FEMALE', 2, 2, 12, '2021-08-09 15:01:34'),
       (3, 'Marc', 'Vanvo', 'MALE', 3, 3, 19, '2021-08-09 15:01:34');