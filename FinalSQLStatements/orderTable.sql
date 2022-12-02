CREATE TABLE Orders(
orderNo int NOT NULL AUTO_INCREMENT,
assembledBikeId int NOT NULL,
customerId int NOT NULL,
staffUsername varchar(30) NOT NULL,
orderDate date,
orderStatus enum ("Pending","Confirmed","Fulfilled"),
orderCost double,
PRIMARY KEY (orderNo),
CONSTRAINT FK_Order_Customer FOREIGN KEY (customerId) REFERENCES Customer (customerId),
CONSTRAINT FK_Order_Staff FOREIGN KEY (staffUsername) REFERENCES Staff (staffUsername),
CONSTRAINT FK_Order_AssembledBike FOREIGN KEY (assembledBikeId) REFERENCES AssembledBike (assembledBikeId)
);