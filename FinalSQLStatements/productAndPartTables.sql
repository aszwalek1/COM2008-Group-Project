CREATE TABLE Product(
productId int NOT NULL AUTO_INCREMENT,
serialNo int NOT NULL,
brandName varchar(30) NOT NULL,
productName varchar(100),
unitCost double,
stock int,
PRIMARY KEY (productId),
UNIQUE (serialNo, brandName)
);

CREATE TABLE FrameSet(
frameId int NOT NULL,
gears int,
shocks bool,
size double,
PRIMARY KEY (frameId),
CONSTRAINT FK_FrameSet_Product FOREIGN KEY (frameId) REFERENCES Product (productId)
);

CREATE TABLE Handlebar(
handlebarId int NOT NULL,
handlebarStyle enum ("Straight","High","Dropped"),
PRIMARY KEY (handlebarId),
CONSTRAINT FK_Handlebars_Product FOREIGN KEY (handlebarId) REFERENCES Product (productId)
);

CREATE TABLE Wheel(
wheelId int NOT NULL,
style enum ("Road","Mountain","Hybrid"),
diameter double,
brakes enum ("Rim","Disk"),
PRIMARY KEY (wheelId),
CONSTRAINT FK_Wheel_Product FOREIGN KEY (wheelId) REFERENCES Product (productId)
);



