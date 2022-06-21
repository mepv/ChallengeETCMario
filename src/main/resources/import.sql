CREATE TABLE users (
        id SERIAL PRIMARY KEY,
	username VARCHAR(250) NOT NULL,
	password VARCHAR(250) NOT NULL
);

CREATE TABLE vehicles (
	id SERIAL PRIMARY KEY,
	make VARCHAR(250) NOT NULL,
	model VARCHAR(250) NOT NULL,
	color VARCHAR(250) NOT NULL,
	year INT NOT NULL,
	created_at TIMESTAMP,
	uuid uuid
);

INSERT INTO vehicles (id, make, model, color, year, created_at, uuid) VALUES
    (1, 'Ferrari', '458 Italia', 'Red', 2009, current_timestamp, 'd2b81c06-347d-42cd-bb2c-4f04721ff60c'),
    (2, 'Mercedes', 'SMG', 'Grey', 2000, current_timestamp, '39c2b246-32fe-42bc-ab44-600370fb94a9'),
    (3, 'Chevrolet', 'Corvette', 'Blue', 2003, current_timestamp, 'e96ac666-a553-4991-b08a-188e59125655'),
    (4, 'Renault', 'Alpine', 'Black', 2011, current_timestamp, '236e0fe2-51f7-454a-b85c-7f222b7ed55f');

ALTER SEQUENCE vehicles_id_seq RESTART WITH 5;
