DROP DATABASE IF EXISTS InventoryManagementSystem;
CREATE DATABASE IF NOT EXISTS InventoryManagementSystem;
SHOW DATABASES ;
USE InventoryManagementSystem;



DROP TABLE IF EXISTS Category;
CREATE TABLE IF NOT EXISTS Category(
   categoryId INT(10) NOT NULL AUTO_INCREMENT,
   categoryName VARCHAR(45) NOT NULL DEFAULT 'Unknown',
   description VARCHAR(15),
   date DATE,
   time VARCHAR(15),
   CONSTRAINT PRIMARY KEY (categoryId)
);
SHOW TABLES ;
DESCRIBE Category;



DROP TABLE IF EXISTS Brand;
CREATE TABLE IF NOT EXISTS Brand(
   brandId INT(10) NOT NULL AUTO_INCREMENT,
   brandCategoryId INT,
   brandName VARCHAR(45) NOT NULL DEFAULT 'Unknown',
   date DATE,
   time VARCHAR(15),
   CONSTRAINT PRIMARY KEY (brandId),
   CONSTRAINT FOREIGN KEY (brandCategoryId ) REFERENCES Category(categoryId) ON DELETE CASCADE ON UPDATE CASCADE
);
SHOW TABLES ;
DESCRIBE Brand;



DROP TABLE IF EXISTS Product;
CREATE TABLE IF NOT EXISTS Product(
   productId INT(10) NOT NULL AUTO_INCREMENT,
   serialNumber VARCHAR(15),
   brandId INT,
   productName VARCHAR(45) NOT NULL DEFAULT 'Unknown',
   qty INT,
   price DOUBLE,
   date DATE,
   time VARCHAR(15),
   CONSTRAINT PRIMARY KEY (productId),
   CONSTRAINT FOREIGN KEY (brandId) REFERENCES Brand(brandId) ON DELETE CASCADE ON UPDATE CASCADE
);
SHOW TABLES ;
DESCRIBE Product;



DROP TABLE IF EXISTS ;
CREATE TABLE IF NOT EXISTS Supplier(
   supplierNIC VARCHAR(15),
   supplierName VARCHAR(45) NOT NULL DEFAULT 'Unknown',
   email VARCHAR(15),
   description VARCHAR(15),
   CONSTRAINT PRIMARY KEY (supplierNIC)
);
SHOW TABLES ;
DESCRIBE Supplier;



DROP TABLE IF EXISTS Customer;
CREATE TABLE IF NOT EXISTS Customer(
   customerNIC VARCHAR(15),
   name VARCHAR(45) NOT NULL DEFAULT 'Unknown',
   mobileNumber VARCHAR(15),
   CONSTRAINT PRIMARY KEY (customerNIC)
);
SHOW TABLES ;
DESCRIBE Customer;



CREATE TABLE IF NOT EXISTS CustomerOrder(
   customerOrderId VARCHAR(15),
   customerNIC VARCHAR(15),
   date DATE,
   time VARCHAR(15),
   CONSTRAINT PRIMARY KEY (customerOrderId),
   CONSTRAINT FOREIGN KEY (customerNIC) REFERENCES Customer(customerNIC) ON DELETE CASCADE ON UPDATE CASCADE
);
SHOW TABLES ;
DESCRIBE CustomerOrder;



DROP TABLE IF EXISTS CustomerOrderDetail;
CREATE TABLE IF NOT EXISTS CustomerOrderDetail(
   customerOrderId VARCHAR(15),
   productId INT,
   unitPrice DOUBLE,
   qtyOnHand INT,
   total DOUBLE,
   date DATE,
   time VARCHAR(15),
   CONSTRAINT PRIMARY KEY (customerOrderId,productId)
);
SHOW TABLES ;
DESCRIBE CustomerOrderDetail;



DROP TABLE IF EXISTS SupplierOrder;
CREATE TABLE IF NOT EXISTS SupplierOrder(
   orderId VARCHAR(15),
   supplierNIC VARCHAR(15),
   orderDate DATE,
   orderTime VARCHAR(15),
   CONSTRAINT PRIMARY KEY (orderId),
   CONSTRAINT FOREIGN KEY (supplierNIC) REFERENCES Supplier(supplierNIC) ON DELETE CASCADE ON UPDATE CASCADE
);
SHOW TABLES ;
DESCRIBE SupplierOrder;



DROP TABLE IF EXISTS SupplierOrderDetail;
CREATE TABLE IF NOT EXISTS SupplierOrderDetail(
   orderId VARCHAR(15),
   productId  INT,
   unitPrice DOUBLE,
   total DOUBLE,
   qty INT,
   date DATE,
   time VARCHAR(15),
   CONSTRAINT PRIMARY KEY (orderId, productId)
);
SHOW TABLES ;
DESCRIBE SupplierOrderDetail;


