DROP DATABASE IF EXISTS pizzastore_db;
CREATE DATABASE pizzastore_db;
USE pizzastore_db;

DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS orders;

CREATE TABLE customer(
    cid INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(15),
    email VARCHAR(50),
    password VARCHAR(30),
    mobile CHAR(10)
);


CREATE TABLE menu(
    mid INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30),
    description TEXT,
    price DOUBLE
);

CREATE TABLE orders(
    oid INT PRIMARY KEY AUTO_INCREMENT,
    cid INT,
    mid INT,
    FOREIGN KEY (cid) REFERENCES customer(cid),
    FOREIGN KEY (mid) REFERENCES menu(mid)
);

INSERT INTO menu(name,description,price) VALUES("Margherita","Classic delight with 100% real mozzarella cheese",109);
INSERT INTO menu(name,description,price) VALUES("Farmhouse","Delightful combination of onion, capsicum, tomato & grilled mushroom",459);
INSERT INTO menu(name,description,price) VALUES("Peppy Paneer","Flavorful trio of juicy paneer, crisp capsicum with spicy red paprika",259);
INSERT INTO menu(name,description,price) VALUES("Mexican Green Wave","Mexican herbs sprinkled on onion, capsicum, tomato & jalapeno",259);
INSERT INTO menu(name,description,price) VALUES("Veggie Paradise","The awesome foursome! Golden corn, black olives, capsicum, red paprika",689);
INSERT INTO menu(name,description,price) VALUES("Paneer Makhani","Paneer, Onion & Capsicum with Desi Makhani Sauce",129);


CREATE TABLE admin(
    aid INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(15),
    email VARCHAR(50),
    password VARCHAR(30),
    mobile CHAR(10)
);

INSERT INTO admin (name,email,password,mobile) VALUES ("Shrish","shrish20@gmail.com","shrish",9096817947);





