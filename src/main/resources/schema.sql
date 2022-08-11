CREATE SCHEMA IF NOT EXISTS tournaments;

---- ENUM TYPE data_source
DO '
BEGIN
    CREATE TYPE tournaments.data_source AS ENUM (''DATA_SOURCE_1'', ''DATA_SOURCE_2'');
EXCEPTION
    WHEN duplicate_object THEN null;
END ';

-- ALTER TYPE tournaments.data_source ADD VALUE 'DATA_SOURCE_3';

---- TABLE tournament
CREATE TABLE IF NOT EXISTS tournaments.golf_tournament
(
    id  UUID    NOT NULL ,
    external_id VARCHAR(255) NOT NULL ,
    tournament_start_date TIMESTAMP NOT NULL ,
    tournament_end_date TIMESTAMP NOT NULL ,
    golf_course_name VARCHAR(255) NOT NULL ,
    host_country VARCHAR(255) NOT NULL ,
    number_of_rounds INT NOT NULL ,
    tournament_data_source tournaments.data_source NOT NULL,
    additional_data VARCHAR(255) NOT NULL ,

    CONSTRAINT pk_tournament_id PRIMARY KEY (id)
);
