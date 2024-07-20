
-- Create User table
CREATE TABLE User (
    UserID INT PRIMARY KEY AUTO_INCREMENT,
    Username VARCHAR(50) NOT NULL,
    Email VARCHAR(100) NOT NULL UNIQUE,
    Password VARCHAR(100) NOT NULL,
    Role ENUM('Retailer', 'CharitableOrganization', 'Consumer') NOT NULL,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Retailer table
CREATE TABLE Retailer (
    RetailerID INT PRIMARY KEY,
    RetailerName VARCHAR(100) NOT NULL,
    Address VARCHAR(255),
    FOREIGN KEY (RetailerID) REFERENCES User(UserID)
);

-- Create Charitable Organization table
CREATE TABLE CharitableOrganization (
    CharitableOrganizationID INT PRIMARY KEY,
    OrganizationName VARCHAR(100) NOT NULL,
    Address VARCHAR(255),
    FOREIGN KEY (CharitableOrganizationID) REFERENCES User(UserID)
);

-- Create Food Item table
CREATE TABLE FoodItem (
    FoodItemID INT PRIMARY KEY AUTO_INCREMENT,
    RetailerID INT NOT NULL,
    Name VARCHAR(100) NOT NULL,
    Category VARCHAR(50),
    Quantity INT NOT NULL,
    ExpirationDate DATE NOT NULL,
    Surplus BOOLEAN DEFAULT FALSE,
    ListedFor ENUM('Donation', 'Sale'),
    Price DECIMAL(10, 2),
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (RetailerID) REFERENCES Retailer(RetailerID)
);

-- Create Order table
CREATE TABLE `Order` (
    OrderID INT PRIMARY KEY AUTO_INCREMENT,
    ConsumerID INT NOT NULL,
    OrderDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    TotalAmount DECIMAL(10, 2),
    FOREIGN KEY (ConsumerID) REFERENCES User(UserID)
);

-- Create Order Detail table
CREATE TABLE OrderDetail (
    OrderDetailID INT PRIMARY KEY AUTO_INCREMENT,
    OrderID INT NOT NULL,
    FoodItemID INT NOT NULL,
    Quantity INT NOT NULL,
    UnitPrice DECIMAL(10, 2),
    FOREIGN KEY (OrderID) REFERENCES `Order`(OrderID),
    FOREIGN KEY (FoodItemID) REFERENCES FoodItem(FoodItemID)
);

-- Create Subscription table
CREATE TABLE Subscription (
    SubscriptionID INT PRIMARY KEY AUTO_INCREMENT,
    UserID INT NOT NULL,
    Location VARCHAR(100),
    CommunicationMethod ENUM('Email', 'Phone'),
    FoodPreferences VARCHAR(255),
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (UserID) REFERENCES User(UserID)
);

-- Create Rating and Feedback table
CREATE TABLE RatingAndFeedback (
    RatingID INT PRIMARY KEY AUTO_INCREMENT,
    UserID INT NOT NULL,
    FoodItemID INT NOT NULL,
    Rating INT CHECK (Rating >= 1 AND Rating <= 5),
    Review TEXT,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (UserID) REFERENCES User(UserID),
    FOREIGN KEY (FoodItemID) REFERENCES FoodItem(FoodItemID)
);