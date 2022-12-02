CREATE TABLE Address(
houseNo varchar(8) NOT NULL,
streetName varchar(30),
cityName varchar(30),
postcode varchar(10) NOT NULL,
PRIMARY KEY (houseNo,postcode)
);