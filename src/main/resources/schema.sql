CREATE SCHEMA IF NOT EXISTS tournaments;

---- ENUM TYPE data_source
CREATE TYPE tournaments.data_source AS ENUM ('DATA_SOURCE_1', 'DATA_SOURCE_2');

---- TABLE tournament
CREATE TABLE tournaments.tournament
(
    id  UUID    NOT NULL ,
    external_id VARCHAR(255) NOT NULL ,
    tournament_start_date TIMESTAMP NOT NULL ,
    tournament_end_date TIMESTAMP NOT NULL ,
    golf_course_name VARCHAR(255) NOT NULL ,
    host_country VARCHAR(255) NOT NULL ,
    number_of_rounds INT NOT NULL ,
    tournament_data_source tournaments.data_source NOT NULL,

    CONSTRAINT pk_tournament_id PRIMARY KEY (id)
);
