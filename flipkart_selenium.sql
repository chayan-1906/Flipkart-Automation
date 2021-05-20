CREATE TABLE Smartphone (
	pk_model_id INT NOT NULL AUTO_INCREMENT,
	model_name VARCHAR(255),
	rating FLOAT(24),
	rating_count INT,
	price FLOAT(24),
	ram VARCHAR(255),
	front_camera_mp VARCHAR(255),
	rear_camera_mp VARCHAR(255),
	capacity VARCHAR(255),
	available BOOLEAN,
	paths VARCHAR(1000),
	PRIMARY KEY(pk_model_id)
);

SELECT * FROM Smartphone;

DELETE FROM Smartphone;

DROP TABLE Smartphone;