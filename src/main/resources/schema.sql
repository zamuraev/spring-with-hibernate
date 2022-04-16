CREATE TABLE IF NOT EXISTS person (
    id SERIAL,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    birth_date TIMESTAMP
);


INSERT INTO PERSON(id, name, location, birth_date) VALUES (10001, 'Ranga', 'Hyderabad', timestamp());
