CREATE TABLE Customer(
cId int NOT NULL,
cForename varchar(30),
cSurname varchar (30),
houseNo int NOT NULL,
postcode varchar(10) NOT NULL,
PRIMARY KEY (cId),
CONSTRAINT FK_Customer_Address FOREIGN KEY (houseNo, postcode) REFERENCES Address(houseNo, postcode)
);