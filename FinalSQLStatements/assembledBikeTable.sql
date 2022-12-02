CREATE TABLE AssembledBike(
assembledBikeId int NOT NULL,
wheelId int NOT NULL,
handlebarId int NOT NULL,
frameId int NOT NULL,
PRIMARY KEY (assembledBikeId),
CONSTRAINT FK_AssembledBike_Product FOREIGN KEY (assembledBikeId) REFERENCES Product (productId),
CONSTRAINT FK_AssembledBike_Wheel FOREIGN KEY (wheelId) REFERENCES Wheel (wheelId),
CONSTRAINT FK_AssembledBike_Handlebars FOREIGN KEY (handlebarId) REFERENCES Handlebar (handlebarId),
CONSTRAINT FK_AssembledBike_FrameSet FOREIGN KEY (frameId) REFERENCES FrameSet (frameId)
);