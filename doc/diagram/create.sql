CREATE TABLE User (
 username VARCHAR(20) NOT NULL,
 password VARCHAR(30),
 firstName VARCHAR(20),
 lastName VARCHAR(30),
 email VARCHAR(80),
 role SMALLINT,
 fid INT,
 gid INT,
 pwtoken VARCHAR(30),
 emailtoken VARCHAR(30)
);

ALTER TABLE User ADD CONSTRAINT PK_User PRIMARY KEY (username);


CREATE TABLE Book (
 filepath VARCHAR(50) NOT NULL,
 title VARCHAR(50),
 author VARCHAR(80),
 summary VARCHAR(1000),
 language VARCHAR(15),
 downloads INT,
 views INT,
 username VARCHAR(20)
);

ALTER TABLE Book ADD CONSTRAINT PK_Book PRIMARY KEY (filepath);


CREATE TABLE Change (
 changedate DATE,
 page INT
);


CREATE TABLE Contribution (
 comment VARCHAR(1000),
 rating SMALLINT
);


CREATE TABLE Report (
 comment VARCHAR(1000),
 reportdate DATE,
 status SMALLINT
);


ALTER TABLE Book ADD CONSTRAINT FK_Book_0 FOREIGN KEY (username) REFERENCES User (username);


