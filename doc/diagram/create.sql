CREATE TABLE User (
 username VARCHAR(20) NOT NULL PRIMARY KEY,
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

CREATE TABLE Book (
 bookid INT NOT NULL PRIMARY KEY,
 title VARCHAR(50),
 author VARCHAR(80),
 summary VARCHAR(1000),
 language VARCHAR(15),
 filepath VARCHAR(50) NOT NULL,
 downloads INT,
 views INT,
 username VARCHAR(20),

 FOREIGN KEY (username) REFERENCES User (username)
);

CREATE TABLE ChangeRequest (
 username VARCHAR(20) NOT NULL,
 bookid INT NOT NULL,
 changedate DATE,
 page INT,

 PRIMARY KEY (username,bookid),
 FOREIGN KEY (username) REFERENCES User (username),
 FOREIGN KEY (bookid) REFERENCES Book(bookid)
);

CREATE TABLE Contribution (
 username VARCHAR(20) NOT NULL,
 bookid INT NOT NULL,
 comment VARCHAR(1000),
 rating SMALLINT,

 PRIMARY KEY (username,bookid),
 FOREIGN KEY (username) REFERENCES User (username),
 FOREIGN KEY (bookid) REFERENCES Book(bookid)
);

CREATE TABLE Report (
 username VARCHAR(20) NOT NULL,
 bookid INT NOT NULL,
 comment VARCHAR(1000),
 reportdate DATE,
 status SMALLINT,
 
 PRIMARY KEY (username,bookid),
 FOREIGN KEY (username) REFERENCES User (username),
 FOREIGN KEY (bookid) REFERENCES Book(bookid)
);


