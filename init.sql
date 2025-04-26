USE animalshelterdb;


CREATE TABLE IF NOT EXISTS animals (
	id INT NOT NULL AUTO_INCREMENT,
    species VARCHAR(50) NOT NULL,
	name VARCHAR(50) NOT NULL,
    gender VARCHAR(1) NOT NULL,
    spayed VARCHAR(3) NOT NULL,
    breed VARCHAR(50) NOT NULL,
    color VARCHAR(50) NOT NULL,
    birthday DATE NOT NULL,
    vaccine_status VARCHAR(50),
    identification VARCHAR(100),
    adoption_fee DECIMAL(5, 2) CHECK (adoption_fee < 300),
    PRIMARY KEY(id)

);




