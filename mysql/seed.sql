INSERT INTO customer (first_name, last_name, address, contact_number, email, is_returning_customer)
VALUES
('John', 'Doe', '123 Main St', '555-1234', 'john.doe@example.com', TRUE),
('Jane', 'Smith', '456 Oak St', '555-5678', 'jane.smith@example.com', FALSE),
('Alice', 'Johnson', '789 Pine St', '555-9012', 'alice.johnson@example.com', TRUE),
('Bob', 'Williams', '101 Maple St', '555-3456', 'bob.williams@example.com', FALSE),
('Eve', 'Brown', '202 Elm St', '555-7890', 'eve.brown@example.com', TRUE),
('Charlie', 'Davis', '303 Birch St', '555-2345', 'charlie.davis@example.com', FALSE),
('Grace', 'Miller', '404 Cedar St', '555-6789', 'grace.miller@example.com', TRUE),
('David', 'Wilson', '505 Spruce St', '555-1230', 'david.wilson@example.com', FALSE),
('Sophia', 'Moore', '606 Willow St', '555-4567', 'sophia.moore@example.com', TRUE),
('Liam', 'Taylor', '707 Aspen St', '555-8901', 'liam.taylor@example.com', FALSE);

INSERT INTO vehicle (make, model, year, vin, price, cost_price, sold_status)
VALUES
('Toyota', 'Camry', 2022, '1HGCM82633A123456', 25000.00, 20000.00, TRUE),
('Honda', 'Civic', 2021, '2HGCM82633A654321', 22000.00, 18000.00, TRUE),
('Ford', 'F-150', 2023, '3HGCM82633A789012', 35000.00, 28000.00, FALSE),
('Chevrolet', 'Malibu', 2020, '4HGCM82633A890123', 23000.00, 19000.00, TRUE),
('Nissan', 'Altima', 2022, '5HGCM82633A345678', 24000.00, 19500.00, TRUE),
('BMW', 'X5', 2023, '6HGCM82633A567890', 60000.00, 50000.00, FALSE),
('Tesla', 'Model 3', 2021, '7HGCM82633A123789', 45000.00, 40000.00, TRUE),
('Hyundai', 'Elantra', 2020, '8HGCM82633A654987', 20000.00, 16000.00, TRUE),
('Kia', 'Sorento', 2023, '9HGCM82633A345123', 32000.00, 27000.00, FALSE),
('Mazda', 'CX-5', 2022, '0HGCM82633A567234', 28000.00, 23000.00, TRUE);

INSERT INTO salesperson (first_name, last_name, contact_number)
VALUES
('Emily', 'Clark', '555-1111'),
('James', 'Adams', '555-2222'),
('Olivia', 'Scott', '555-3333'),
('Michael', 'Harris', '555-4444'),
('Emma', 'Thompson', '555-5555'),
('William', 'Evans', '555-6666'),
('Sophia', 'Turner', '555-7777'),
('Lucas', 'Martin', '555-8888'),
('Mia', 'White', '555-9999'),
('Noah', 'King', '555-0000');

INSERT INTO sales (customer_id, vehicle_id, sold_price, sale_date, salesperson_id)
VALUES
(1, 1, 25000.00, '2024-01-15', 1),
(2, 2, 22000.00, '2024-01-20', 2),
(3, 4, 23000.00, '2024-02-10', 3),
(5, 5, 24000.00, '2024-03-05', 4),
(7, 7, 45000.00, '2024-03-15', 5),
(9, 8, 20000.00, '2024-04-01', 1),
(4, 10, 28000.00, '2024-04-15', 2),
(6, 3, 35000.00, '2024-05-10', 3),
(8, 6, 60000.00, '2024-05-20', 4),
(10, 9, 32000.00, '2024-06-01', 5);

INSERT INTO ServicePackage (PackageName, CarAge, LaborCost, Description)
VALUES
('1-Year Service', 1, 100.00, 'Oil change, tire rotation, basic inspection'),
('2-Year Service', 2, 200.00, 'Oil change, brake check, detailed inspection'),
('3-Year Service', 3, 300.00, 'Full service, transmission check, diagnostics'),
('4-Year Service', 4, 400.00, 'Advanced service, suspension check, full diagnostics'),
('5-Year Service', 5, 500.00, 'Comprehensive service, engine tune-up, full repairs');

INSERT INTO ServiceAppointment (CustomerID, VehicleID, ScheduledTime, PackageID, EstimatedTime, AppointmentStatus)
VALUES
(1, 1, '2024-07-01 09:00:00', 1, 120, 'scheduled'),
(2, 2, '2024-07-02 10:00:00', 2, 150, 'completed'),
(3, 4, '2024-07-03 11:00:00', 3, 180, 'scheduled'),
(5, 5, '2024-07-04 08:30:00', 1, 90, 'completed'),
(7, 7, '2024-07-05 14:00:00', 2, 120, 'scheduled'),
(9, 8, '2024-07-06 15:30:00', 3, 180, 'scheduled'),
(4, 10, '2024-07-07 09:00:00', 1, 120, 'completed'),
(6, 3, '2024-07-08 10:00:00', 2, 150, 'scheduled'),
(8, 6, '2024-07-09 11:30:00', 3, 180, 'completed'),
(10, 9, '2024-07-10 08:00:00', 1, 90, 'scheduled');

INSERT INTO Parts (PartName, Price, StockQuantity)
VALUES
('Oil Filter', 15.00, 100),
('Brake Pads', 45.00, 50),
('Air Filter', 20.00, 75),
('Spark Plugs', 25.00, 80),
('Tires', 100.00, 20),
('Battery', 120.00, 15),
('Headlight Bulb', 10.00, 40),
('Wiper Blades', 8.00, 60),
('Alternator', 150.00, 10),
('Timing Belt', 200.00, 5);

INSERT INTO ServiceDetail (AppointmentID, ArrivalTime, PickUpTime, ServicePerformed, PartsUsed, LaborHours, TotalCost)
VALUES
(1, '2024-07-01 09:00:00', '2024-07-01 11:00:00', 'Basic Inspection', 'Oil Filter', 2.0, 120.00),
(2, '2024-07-02 10:00:00', '2024-07-02 12:30:00', 'Brake Check', 'Brake Pads', 2.5, 250.00),
(3, '2024-07-03 11:00:00', NULL, 'Diagnostics', 'None', 3.0, 300.00),
(4, '2024-07-04 08:30:00', '2024-07-04 10:00:00', 'Oil Change', 'Oil Filter', 1.5, 150.00),
(5, '2024-07-05 14:00:00', NULL, 'Tire Rotation', 'None', 2.0, 200.00),
(6, '2024-07-06 15:30:00', '2024-07-06 18:00:00', 'Full Diagnostics', 'Spark Plugs', 3.5, 350.00),
(7, '2024-07-07 09:00:00', '2024-07-07 11:30:00', 'Suspension Check', 'Tires', 3.0, 400.00),
(8, '2024-07-08 10:00:00', '2024-07-08 12:30:00', 'Engine Tuning', 'Timing Belt', 2.5, 500.00),
(9, '2024-07-09 11:30:00', '2024-07-09 14:00:00', 'Battery Replacement', 'Battery', 1.5, 200.00),
(10, '2024-07-10 08:00:00', '2024-07-10 09:30:00', 'Wiper Replacement', 'Wiper Blades', 1.0, 50.00);


