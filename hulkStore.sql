CREATE TABLE product
(
	productId INT NOT NULL,
	productName VARCHAR(100) NOT NULL ,
	productType VARCHAR(100) NOT NULL,
	productLabel VARCHAR(100) NOT NULL,
	productArchive BOOLEAN NOT NULL,
	PRIMARY KEY (productId)
);

CREATE TABLE history
(
	productId INT NOT NULL,
	historyQuantity INT NOT NULL ,
	historyActivityDate TIMESTAMP NOT NULL,
	historyPrice INT NOT NULL,
	PRIMARY KEY (productId, historyActivityDate),
	CONSTRAINT fk_productId FOREIGN KEY (productId) REFERENCES product (productId)
);

INSERT into product(productId, productName, productType, productLabel, productArchive)
VALUES(123, 'Hulk Rojo', 'Muneco', 'Marvel', true);

INSERT into history(productId, historyQuantity, historyActivityDate, historyPrice)
VALUES(123, 200, TO_DATE('17/12/2015', 'DD/MM/YYYY'), 42);
