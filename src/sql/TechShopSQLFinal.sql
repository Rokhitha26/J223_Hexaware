create database techshopfinal;
use techshopfinal;
create table customers(
customer_ID int primary key  auto_increment,
customerFirstName varchar(255),
customerLastName varchar(255),
email varchar(40) unique,
address varchar(100)
);
create table products(
productId int primary key auto_increment,
productName varchar(50),
productDes varchar(100),
productPrice int);
alter table products modify column productPrice decimal(10,2);
create table orders(
orderID int primary key auto_increment,
customer_ID int,
orderDate date,
totalAmount int,
foreign key(customer_ID) references customers(customer_ID)
on delete cascade
);

create table orderDetails(
orderDetailID int primary key auto_increment,
orderID int,
productID int,
quantity int,
foreign key(orderID) references orders(orderID)
on delete cascade);

create table inventory(
inventoryID int primary key auto_increment,
productID int,
quantityInStock int,
lastStockUpdate int,
foreign key (productID) references products(productID)
on delete cascade);

INSERT INTO customers (customerFirstName, customerLastName, email, address) VALUES
('John', 'Doe', 'john.doe@gmail.com', '123 Maple Street'),
('Alice', 'Smith', 'alice.smith@yahoo.com', '456 Oak Avenue'),
('Bob', 'Johnson', 'bob.johnson@hotmail.com', '789 Pine Road'),
('Eva', 'Williams', 'eva.williams@gmail.com', '321 Birch Lane'),
('Mike', 'Brown', 'mike.brown@outlook.com', '654 Cedar Street');

INSERT INTO products (productName, productDes, productPrice) VALUES
('Smartphone', '6.5-inch display, 128GB storage', 25000),
('Laptop', '15-inch, 8GB RAM, 512GB SSD', 55000),
('Wireless Earbuds', 'Bluetooth 5.2, Noise Cancellation', 3500),
('Smartwatch', 'Heart Rate Monitor, GPS, Waterproof', 7000),
('Tablet', '10-inch display, 64GB storage', 18000);

alter table inventory modify column lastStockUpdate date;
INSERT INTO inventory (productID, quantityInStock, lastStockUpdate) VALUES
(1, 50, '2024-03-01'),
(2, 30, '2024-03-05'),
(3, 100, '2024-03-02'),
(4, 60, '2024-03-04'),
(5, 40, '2024-03-03');
alter table customers add column password varchar(4);
desc customers;
CREATE TABLE payments (
    paymentID INT AUTO_INCREMENT PRIMARY KEY,
    orderID INT,
    paymentMethod VARCHAR(50),
    amountPaid DECIMAL(10, 2),
    paymentDate DATE,
    FOREIGN KEY (orderID) REFERENCES orders(orderID)
        ON DELETE CASCADE
);
