drop database if exists dealership;
create database dealership;
USE dealership;
drop table if exists customer;
drop table if exists vehicle;
drop table if exists sales;
drop table if exists service_appointment;
drop table if exists service_package;
drop table if exists service_detail;
drop table if exists parts;


CREATE TABLE customer (
    customer_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    address VARCHAR(100),
    contact_number VARCHAR(15),
    email VARCHAR(50)
);

CREATE TABLE vehicle (
    vehicle_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    make VARCHAR(30) NOT NULL,
    model VARCHAR(30) NOT NULL,
    year INT NOT NULL,
    vin VARCHAR(17) UNIQUE NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    cost_price DECIMAL(10, 2), 
    is_sold BOOLEAN DEFAULT FALSE 
);

CREATE TABLE salesperson (
    salesperson_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    contact_number VARCHAR(15)
);

CREATE TABLE sales (
    sale_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    customer_id BIGINT,
    vehicle_id BIGINT,
    sold_price DECIMAL(10, 2) NOT NULL,
    sale_date DATE NOT NULL,
    salesperson_id BIGINT,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
    FOREIGN KEY (vehicle_id) REFERENCES vehicle(vehicle_id),
    FOREIGN KEY (salesperson_id) REFERENCES salesperson(salesperson_id)
);

CREATE TABLE service_package (
    service_package_id INT PRIMARY KEY AUTO_INCREMENT,
    package_type VARCHAR(50) NOT NULL,
    estimated_hours INT NOT NULL,
    estimated_cost DECIMAL(10, 2),
    description TEXT
);

CREATE TABLE service_appointment (
    appointment_id INT PRIMARY KEY AUTO_INCREMENT, 
    customer_id BIGINT,
    vehicle_id BIGINT,
    scheduled_date DATETIME NOT NULL,
    service_package_id INT,
    time INT,
    appointment_status VARCHAR(20) DEFAULT 'scheduled',
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
    FOREIGN KEY (vehicle_id) REFERENCES vehicle(vehicle_id),
    FOREIGN KEY (service_package_id) REFERENCES service_package(service_package_id)
);

CREATE TABLE service_detail (
    service_id INT PRIMARY KEY AUTO_INCREMENT,
    appointment_id INT,
    arrival_time DATETIME NOT NULL,
    pick_up_time DATETIME,  
    service_performed TEXT,
    labor_hours DECIMAL(4, 2),  
    total_cost DECIMAL(10, 2),
    FOREIGN KEY (appointment_id) REFERENCES service_appointment(appointment_id)
);

CREATE TABLE parts (
    PartID INT PRIMARY KEY AUTO_INCREMENT,
    part_name VARCHAR(50) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    stock_quantity INT NOT NULL,
    cost DECIMAL(10, 2) NOT NULL
);