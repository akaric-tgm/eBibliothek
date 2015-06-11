CREATE TABLE User (
 username VARCHAR(40) NOT NULL PRIMARY KEY,
 password VARCHAR(64),
 firstName VARCHAR(20),
 lastName VARCHAR(30),
 email VARCHAR(120),
 fid INT,
 gid INT,
 pwtoken VARCHAR(64),
 emailtoken VARCHAR(64),
 blocked BOOLEAN
);

CREATE TABLE User_Groups (
 groupname VARCHAR(20) NOT NULL,
 username VARCHAR(40) NOT NULL,
 
 PRIMARY KEY (username),
 FOREIGN KEY (username) REFERENCES User (username) ON UPDATE CASCADE ON DELETE CASCADE 
);

CREATE TABLE Book (
 bookid INT NOT NULL PRIMARY KEY,
 title VARCHAR(100),
 author VARCHAR(100),
 summary VARCHAR(1000),
 language VARCHAR(15),
 filepath VARCHAR(100) NOT NULL,
 downloads INT,
 views INT,
 pages INT,
 username VARCHAR(40),

 FOREIGN KEY (username) REFERENCES User (username) ON UPDATE CASCADE ON DELETE CASCADE 
);

CREATE TABLE ChangeRequest (
 username VARCHAR(40) NOT NULL,
 bookid INT NOT NULL,
 changedate DATE,
 page INT,

 PRIMARY KEY (username,bookid),
 FOREIGN KEY (username) REFERENCES User (username) ON UPDATE CASCADE ON DELETE CASCADE ,
 FOREIGN KEY (bookid) REFERENCES Book(bookid) ON UPDATE CASCADE ON DELETE CASCADE 
);

CREATE TABLE Contribution (
 username VARCHAR(40) NOT NULL,
 bookid INT NOT NULL,
 comment VARCHAR(1000),
 rating SMALLINT,

 PRIMARY KEY (username,bookid),
 FOREIGN KEY (username) REFERENCES User (username) ON UPDATE CASCADE ON DELETE CASCADE ,
 FOREIGN KEY (bookid) REFERENCES Book(bookid) ON UPDATE CASCADE ON DELETE CASCADE 
);

CREATE TABLE Report (
 username VARCHAR(40) NOT NULL,
 bookid INT NOT NULL,
 comment VARCHAR(1000),
 reportdate DATE,
 status SMALLINT,
 
 PRIMARY KEY (username,bookid),
 FOREIGN KEY (username) REFERENCES User (username) ON UPDATE CASCADE ON DELETE CASCADE ,
 FOREIGN KEY (bookid) REFERENCES Book(bookid) ON UPDATE CASCADE ON DELETE CASCADE 
);


INSERT INTO User VALUES('akaric','a44ee4c7b9d08be3d632c255f2c4029bc5aed773d11908af0f07754906eab455','Adin','Karic','akaric@student.tgm.ac.at',12345678,12345678,null,null,false);
INSERT INTO User VALUES('nhohenwarter','a44ee4c7b9d08be3d632c255f2c4029bc5aed773d11908af0f07754906eab455','Niklas','Hohenwarter','nhohenwarter@student.tgm.ac.at',12345671,12345671,null,null,false);
INSERT INTO User VALUES('sbrinnich','a44ee4c7b9d08be3d632c255f2c4029bc5aed773d11908af0f07754906eab455','Selina','Brinnich','sbrinnich@student.tgm.ac.at',12345672,12345672,'testy','testy',false);
INSERT INTO User VALUES('tperny','a44ee4c7b9d08be3d632c255f2c4029bc5aed773d11908af0f07754906eab455','Tobias','Perny','tperny@student.tgm.ac.at',12345673,12345678,null,null,false);
INSERT INTO User VALUES('mgoebel','a44ee4c7b9d08be3d632c255f2c4029bc5aed773d11908af0f07754906eab455','Melanie','Göbel','mgoebel@student.tgm.ac.at',12345674,12345678,null,null,false);

INSERT INTO User_Groups VALUES('User','akaric');
INSERT INTO User_Groups VALUES('Admin','sbrinnich');
INSERT INTO User_Groups VALUES('User','tperny');
INSERT INTO User_Groups VALUES('User','mgoebel');
INSERT INTO User_Groups VALUES('Admin','nhohenwarter');

INSERT INTO Book VALUES(1,'Harry Potter 1','Joanne K. Rowling','Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.','Deutsch','harry_potter_1_joanne_k._rowling_akaric.epub',12334,12354,200,'akaric');
INSERT INTO Book VALUES(2,'Harry Potter 2','Joanne K. Rowling','Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.','Deutsch','harry2.epub',12324,12334,500,'sbrinnich');
INSERT INTO Book VALUES(3,'Harry Potter 3','Joanne K. Rowling','Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.','Deutsch','harry3.epub',12344,12324,400,'nhohenwarter');
INSERT INTO Book VALUES(4,'Harry Potter 4','Joanne K. Rowling','Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.','Deutsch','harry4.epub',12364,12345,20,'tperny');
INSERT INTO Book VALUES(5,'Harry Potter 5','Joanne K. Rowling','Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.','Deutsch','harry5.epub',12354,12134,130,'akaric');

INSERT INTO ChangeRequest VALUES('akaric',1,'2015-01-01',3);
INSERT INTO ChangeRequest VALUES('nhohenwarter',3,'2015-01-01',12);
INSERT INTO ChangeRequest VALUES('sbrinnich',1,'2015-01-01',3);
INSERT INTO ChangeRequest VALUES('tperny',5,'2015-01-01',334);
INSERT INTO ChangeRequest VALUES('mgoebel',4,'2015-01-01',34);

INSERT INTO Contribution VALUES('akaric',1,"Des is ua supa!",5);
INSERT INTO Contribution VALUES('sbrinnich',1,"Des is voll ua supa!",5);
INSERT INTO Contribution VALUES('mgoebel',3,"Des war fad!",1);
INSERT INTO Contribution VALUES('tperny',2,"I kann ned lesn!",3);
INSERT INTO Contribution VALUES('nhohenwarter',4,"Hob i scho 3 Mal glesn!",5);

INSERT INTO Report VALUES('akaric',1,"Da is ein Fehler!",'2015-01-01',0);
INSERT INTO Report VALUES('sbrinnich',1,"Da is ein Fehler!",'2015-01-01',0);
INSERT INTO Report VALUES('mgoebel',2,"Da is ein Fehler!",'2015-01-01',1);
INSERT INTO Report VALUES('tperny',4,"Da is ein Fehler!",'2015-01-01',0);
INSERT INTO Report VALUES('nhohenwarter',5,"Da is ein Fehler!",'2015-01-01',0);
