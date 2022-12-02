CREATE TABLE Staff(
staffUsername varchar(30) NOT NULL,
sPassword varchar(256),
PRIMARY KEY (staffUsername)
);

INSERT INTO Staff VALUES ("TEMP","GrenPaxSol7!"); 
Insert into team024.Staff values("Staff1", SHA2("password", 256));
Insert into team024.Staff values("Staff2", SHA2("PASSWORD", 256));