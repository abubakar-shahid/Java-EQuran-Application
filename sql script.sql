create database mapleview;
use mapleview;

Create Table User
(
	userId int NOT NULL auto_increment,
    name varchar(100),
    email varchar(100),
    password varchar(100),
    userType int,
	CONSTRAINT PK_USER PRIMARY KEY (userId)
);

-- ------------------------
-- Sign up Procedure
-- ------------------------
DELIMITER $$
CREATE PROCEDURE SignUp
(
	IN u_name varchar(100),
    IN u_email varchar(100),
    IN u_password varchar(100),
    IN u_userType int,
    OUT uid varchar(30)
)
BEGIN
    IF (select count(*) from User where u_email = email) > 0 THEN
		set uid = 'duplicate';
	ELSE
		insert into User (name, email, password, userType) values(u_name, u_email, u_password, u_userType);
        select userId INTO uid from User where email = u_email;
    END IF;
END $$
DELIMITER ;

call SignUp("Ali", "2@2.com", "2", 1, @result);

-- insert into User (name, email, password, userType) values("Usman", "1@1.com", "1", 1);

select * from user;
Create Table Images
(
	id int NOT NULL auto_increment,
    imageurl varchar(100),
    uploadedBy int,
	CONSTRAINT PK_IMAGEID PRIMARY KEY (id),
    FOREIGN KEY (uploadedBy) REFERENCES User(userId)
);
