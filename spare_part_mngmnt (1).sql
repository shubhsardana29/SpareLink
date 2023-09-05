-- Create the database
CREATE DATABASE IF NOT EXISTS spare_partmanagement;

-- Use the database
USE spare_partmanagement;

-- Role table
CREATE TABLE Role (
    RoleID INT PRIMARY KEY,
    RoleName VARCHAR(50) NOT NULL
);

-- User table
CREATE TABLE User (
    UserID INT PRIMARY KEY AUTO_INCREMENT,
    Username VARCHAR(50) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    RoleID INT NOT NULL,
    FOREIGN KEY (RoleID) REFERENCES Role(RoleID)
);

-- Permission table
CREATE TABLE Permission (
    PermissionID INT PRIMARY KEY,
    PermissionName VARCHAR(50) NOT NULL
);

-- RolePermission table
CREATE TABLE RolePermission (
    RoleID INT,
    PermissionID INT,
    PRIMARY KEY (RoleID, PermissionID),
    FOREIGN KEY (RoleID) REFERENCES Role(RoleID),
    FOREIGN KEY (PermissionID) REFERENCES Permission(PermissionID)
);

-- Product table
CREATE TABLE Product (
    ProductID INT PRIMARY KEY AUTO_INCREMENT,
    SKU VARCHAR(50) NOT NULL
);

-- SparePart table
CREATE TABLE SparePart (
    SparePartID INT PRIMARY KEY AUTO_INCREMENT,
    SKU VARCHAR(50) NOT NULL
);

-- ProductSparePart table
CREATE TABLE ProductSparePart (
    ProductID INT,
    SparePartID INT,
    PRIMARY KEY (ProductID, SparePartID),
    FOREIGN KEY (ProductID) REFERENCES Product(ProductID),
    FOREIGN KEY (SparePartID) REFERENCES SparePart(SparePartID)
);

-- Warehouse table
CREATE TABLE Warehouse (
    WarehouseID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(50) NOT NULL,
    Zone VARCHAR(50) NOT NULL
);

-- ServiceCenter table
CREATE TABLE ServiceCenter (
    ServiceCenterID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(50) NOT NULL,
    Location VARCHAR(50) NOT NULL,
    WarehouseID INT,
    FOREIGN KEY (WarehouseID) REFERENCES Warehouse(WarehouseID)
);

-- RepairOrder table
CREATE TABLE RepairOrder (
    RepairOrderID INT PRIMARY KEY AUTO_INCREMENT,
    ServiceCenterID INT,
    FOREIGN KEY (ServiceCenterID) REFERENCES ServiceCenter(ServiceCenterID)
);

-- JobSheet table
CREATE TABLE JobSheet (
    JobSheetID INT PRIMARY KEY AUTO_INCREMENT,
    RepairOrderID INT,
    CreatedDate DATE,
    FOREIGN KEY (RepairOrderID) REFERENCES RepairOrder(RepairOrderID)
);

-- PartRequest table
CREATE TABLE PartRequest (
    PartRequestID INT PRIMARY KEY AUTO_INCREMENT,
    RepairOrderID INT,
    SparePartID INT,
    RequestedQuantity INT,
    Status VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (RepairOrderID) REFERENCES RepairOrder(RepairOrderID),
    FOREIGN KEY (SparePartID) REFERENCES SparePart(SparePartID)
);

-- DispatchPlan table
CREATE TABLE DispatchPlan (
    DispatchPlanID INT PRIMARY KEY AUTO_INCREMENT,
    WarehouseID INT,
    DispatchDate DATE,
    Status VARCHAR(50),
    FOREIGN KEY (WarehouseID) REFERENCES Warehouse(WarehouseID)
);

-- DispatchPlanDetail table
CREATE TABLE DispatchPlanDetail (
    DispatchPlanDetailID INT PRIMARY KEY AUTO_INCREMENT,
    DispatchPlanID INT,
    SparePartID INT,
    Quantity INT,
    FOREIGN KEY (DispatchPlanID) REFERENCES DispatchPlan(DispatchPlanID),
    FOREIGN KEY (SparePartID) REFERENCES SparePart(SparePartID)
);

-- CustomerSupport table
CREATE TABLE CustomerSupport (
    CustomerSupportID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(50)
);

-- CustomerQuery table
CREATE TABLE CustomerQuery (
    CustomerQueryID INT PRIMARY KEY AUTO_INCREMENT,
    RepairOrderID INT,
    CustomerSupportID INT,
    QueryText TEXT,
    Response TEXT,
    FOREIGN KEY (RepairOrderID) REFERENCES RepairOrder(RepairOrderID),
    FOREIGN KEY (CustomerSupportID) REFERENCES CustomerSupport(CustomerSupportID)
);

-- WarehouseInventory table
CREATE TABLE WarehouseInventory (
    WarehouseID INT,
    SparePartID INT,
    AvailableQuantity INT,
    PRIMARY KEY (WarehouseID, SparePartID),
    FOREIGN KEY (WarehouseID) REFERENCES Warehouse(WarehouseID),
    FOREIGN KEY (SparePartID) REFERENCES SparePart(SparePartID)
);


-- Insert into Role table
INSERT INTO Role (RoleID, RoleName) VALUES
    (1, 'Service Center'),
    (2, 'Planning Team'),
    (3, 'Warehouse Team'),
    (4, 'Customer Support');
    
-- Insert into User table
INSERT INTO User (Username, Password, RoleID) VALUES
    ('user1', 'hashed_pw', 1),
    ('user2', 'hashed_pw', 2),
    ('user3', 'hashed_pw', 3);
    
-- Insert into Permission table
INSERT INTO Permission (PermissionID, PermissionName) VALUES
    (1, 'Manage Repair Orders'),
    (2, 'Create Dispatch Plans'),
    (3, 'Manage Parts'),
    (4, 'Address Customer Queries');
    
-- Insert into RolePermission table
INSERT INTO RolePermission (RoleID, PermissionID) VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4);
    
-- Insert into Product table
INSERT INTO Product (SKU) VALUES
    ('SKU001'),
    ('SKU002');
    
-- Insert into SparePart table
INSERT INTO SparePart (SKU) VALUES
    ('PART001'),
    ('PART002');
    
-- Insert into ProductSparePart table
INSERT INTO ProductSparePart (ProductID, SparePartID) VALUES
    (1, 1),
    (1, 2),
    (2, 2);
    
-- Insert into Warehouse table
INSERT INTO Warehouse (Name, Zone) VALUES
    ('North WH', 'North'),
    ('South WH', 'South'),
    ('East WH', 'East'),
    ('West WH', 'West');
    

-- Insert into ServiceCenter table
INSERT INTO ServiceCenter (Name, Location, WarehouseID) VALUES
    ('Center A', 'Location A', 1),
    ('Center B', 'Location B', 2),
    ('Center C', 'Location C', 3);
    
-- Insert into RepairOrder table
INSERT INTO RepairOrder (ServiceCenterID) VALUES
    (1),
    (2),
    (3);
    
-- Insert into JobSheet table
INSERT INTO JobSheet (RepairOrderID, CreatedDate) VALUES
    (1, '2023-08-01'),
    (2, '2023-08-02'),
    (3, '2023-08-03');
    
-- Insert into PartRequest table
INSERT INTO PartRequest (RepairOrderID, SparePartID, RequestedQuantity, Status) VALUES
    (1, 1, 2, 'Pending'),
    (2, 2, 3, 'Approved'),
    (3, 2, 1, 'Pending');

-- Insert into DispatchPlan table
INSERT INTO DispatchPlan (WarehouseID, DispatchDate, Status) VALUES
    (1, '2023-08-03', 'Planned'),
    (2, '2023-08-04', 'Ready'),
    (3, '2023-08-05', 'Planned');

-- Insert into DispatchPlanDetail table
INSERT INTO DispatchPlanDetail (DispatchPlanID, SparePartID, Quantity) VALUES
    (1, 1, 2),
    (1, 2, 1),
    (2, 2, 3),
    (3, 1, 1);
    
-- Insert into CustomerSupport table
INSERT INTO CustomerSupport (Name) VALUES
    ('Support A'),
    ('Support B');
    
-- Insert into CustomerQuery table
INSERT INTO CustomerQuery (RepairOrderID, CustomerSupportID, QueryText, Response) VALUES
    (1, 1, 'Query text', 'Response A'),
    (2, 2, 'Query text', 'Response B');

-- Example data for WarehouseInventory table
INSERT INTO WarehouseInventory (WarehouseID, SparePartID, AvailableQuantity) VALUES
    (1, 1, 100), -- Warehouse 1, Spare Part 1 has 100 available
    (1, 2, 50),  -- Warehouse 1, Spare Part 2 has 50 available
    (2, 1, 75)  -- Warehouse 2, Spare Part 1 has 75 available
    -- ...
    -- Add more rows for other warehouses and spare parts
;

    

