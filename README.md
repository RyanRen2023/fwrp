

# High-Level Design

## Version History
| Version # | Name of the Author | Date       |
|-----------|---------------------|------------|
| 01        | Angela Martins      | 2024-07-21 |
| 01        | Sam Doiron          | 2024-07-21 |
| 01        | Xihai Ren           | 2024-07-21 |
| 01        | Uyen Minh           | 2024-07-21 |

## High-Level Design
**Date:** 2024-07-21  
**Version #01**

---

## 2. Introduction
The Food Waste Reduction platform is a waste management platform. With collaboration from restaurants, consumers, food suppliers, and charitable organizations, this platform promotes sustainability throughout the supply chain as well as efforts to reduce global hunger. This document explores the functionality of the application and provides insight into its layout.

## 3. Targeted Audience
The intended audience for this document are realtors and customers who may be interested in investing in the platform.

## 4. Scope
**In Scope:**

*Functional Requirements:*
- User registration and authentication, including account creation, login, and logout functionality.
- Retailer features, including inventory management, identification and flagging of surplus food, and listing surplus food for donation or sale at a discounted price.
- Charitable organization features, including claiming food listed for donation by retailers and updating inventory information.
- Consumer features, including purchasing discounted food (simulated transactions) and updating inventory information.
- Surplus food alerts, including user subscriptions to surplus food notifications and automatic notifications of surplus food availability.
- Advanced search, including food search based on various criteria and the application of multiple filters.
- Food rating and feedback system, including rating and reviewing purchased or claimed food, and submitting, viewing, and responding to feedback.

*Non-Functional Requirements:*
- System security, including user data protection and encryption, transmission encryption, user authentication and authorization, and data privacy.

**Out of Scope:**
- Real transaction processing, the platform’s purchasing functionality is limited to simulated transactions and does not include real payment and financial transaction processing.
- Complex UI design, the platform does not include complex user interface designs, only simple and practical basic interfaces.
- Other functionalities not listed in the scope, excluding any additional features not explicitly listed in the in-scope section.

## 5. Application Architecture
### High-level architecture
<img width="417" alt="image" src="https://github.com/user-attachments/assets/2431867e-502f-4c52-bbef-356b23edb165">

**Presentation Layer (JSP + HTML + CSS + JavaScript):**
- **User Interface (UI):** Provides the user interaction interface, implemented using JSP, HTML, CSS, and JavaScript technology.
- **Authentication Module:** Manages user registration, login, and logout functionality, implemented using JSP and Servlets.

**Business Logic Layer (Servlet + Java):**
- **Retailer Module:** Manages inventory, identifies and flags surplus food, and lists surplus food for donation or sale at a discounted price.
- **Charitable Organization Module:** Allows charitable organizations to claim surplus food and update inventory.
- **Consumer Module:** Allows consumers to purchase discounted food and update inventory.
- **Notification Module:** Manages user subscriptions to surplus food alerts and sends automatic notifications.
- **Search Module:** Provides advanced search functionality with filtering options.
- **Feedback Module:** Manages food ratings and feedback submission, viewing, and responding.

**Data Persistence Layer (DAO + RDBMS):**
- **Data Access Objects (DAO):** Provides an abstraction layer for database operations, ensuring efficient and secure interactions with the database.
- **Database:** Uses a relational database management system (such as MySQL) to store user data, inventory data, surplus food listings, transaction records, notifications, and feedback.

## 6. Business Architecture
### Use Case diagram
<img width="282" alt="image" src="https://github.com/user-attachments/assets/590750e2-77f2-46ce-8f0c-0122753d0258">


**Description:**
- **Retailer:** Register, Login, Manage Inventory, Identify Surplus Food, List Surplus Food Item, Receive Notifications, Subscribe to Alerts.
- **Consumer:** Register, Login, Purchase Surplus Food, Receive Notifications, Rate and Review Food, Search Food, Subscribe to Alerts.
- **Charity Organization:** Register, Login, Claim Food, Receive Notifications, Subscribe to Alerts.

## 7. Detailed Design

## 8. Database Structures
The database architecture includes tables for storing information about food items, orders, stores, subscriptions, users, and food types. The tables that will be used in this project are:

**Table: Food**


**Table: food_type**


**Table: orders**


**Table: store**


**Table: subscribe**


**Table: user**


### Entity Relationship Diagram – Physical Data Model


## 9. Security Architecture
### Enforcing Password Policies
**Description:** Ensure that users create secure passwords to access the platform.
**Details:**
- Minimum length (e.g., 8 characters)
- At least one uppercase letter
- At least one lowercase letter
- At least one digit
- At least one special character
- Create a function to validate passwords against the defined policy.
- Provide immediate feedback to users when they are creating their passwords.

### User Authentication
**Description:** Use a password-based method for authentication.
**Detailed:**
- **User Registration:** The user registers by providing their username and password. The system stores this information in the database.
- **Login Request:** The user attempts to log in by entering their username and password. The system uses the Authentication method to get the appropriate authentication method based on the information stored in the database.
- **Password-Based Authentication:** The authenticate method checks the username and password against the stored values in the database. If the credentials match, the user is authenticated.

## 10. Deployment Architecture
- **Client-Side:** Users interact with the platform through a web browser. These clients send requests to the server-side components.
- **Server-Side:** The application server processes requests, and interacts with databases.
- **Data Storage:** The primary database handles structured data.

## 11. Testing Model
For this project we will use Junit as our testing application, and to verify the functionality, reliability, and performance, we will perform the following tests:
- Test to ensure that the attributes from each entity can be created, modified, and deleted correctly.
- Verify the accuracy of analytics calculations (e.g., total food saved, and donations made).
- Test complete workflows within the application including simulation of user actions (e.g., adding food items, making donations) and analysis of expected outcomes.
- Testing interactions between different components (e.g., user interface, database).
- Test scenarios at the limits of acceptable input values and perform a behavior validation when unexpected inputs are provided.

## 12. References
1. J. Gomez, “What is an application architecture diagram?,” Koombea, Feb. 05, 2024. [Application Architecture Diagram](https://www.koombea.com/blog/application-architecture-diagram/#:~:text=Application%20architecture%20diagrams%20offer%20a,engineering%20and%20cloud%2Dnative%20projects)
2. Arthur C. Codex, “Java cryptography: Encrypting and decrypting data”, April 18, 2023. [Java Cryptography](https://reintech.io/blog/java-cryptography-encrypting-decrypting-data-tutorial)
3. tutorialspoint, “Java Cryptography - Encrypting Data”. [Java Cryptography](https://www.tutorialspoint.com/java_cryptography/java_cryptography_encrypting_data.htm)
4. V Developer, “How to Add Two-Factor Authentication (2FA) Using Java and Spark”, April 27, 2023. [2FA in Java](https://developer.vonage.com/en/blog/how-to-add-two-factor-authentication-using-java-and-spark)
5. Medium, “Implementing a Simple Load Balancer in Java”, Apr 11, 2024. [Load Balancer in Java](https://medium.com/@kowsalyathirupp

## 13. Develop Guidline

- [DevelopmentGuidelines](docs/guilines/DevelopmentGuidelines.md)

- [HowToDevelopAServlet](docs/guilines/HowToDevelopAServlet.md)

- [ProjectStructureInstruction](docs/guilines/ProjectStructureInstruction.md)