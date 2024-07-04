# Project Structure Instruction

```code
FoodWasteReductionPlatform/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── algonquin/
│   │   │           └── cst8288/
│   │   │               └── fwrps/
│   │   │                   ├── model/
│   │   │                   │   ├── User.java
│   │   │                   │   ├── Retailer.java
│   │   │                   │   ├── Consumer.java
│   │   │                   │   ├── Charity.java
│   │   │                   │   └── FoodItem.java
│   │   │                   ├── controller/
│   │   │                   │   ├── UserServlet.java
│   │   │                   │   ├── RetailerServlet.java
│   │   │                   │   ├── ConsumerServlet.java
│   │   │                   │   └── CharityServlet.java
│   │   │                   ├── service/
│   │   │                   │   ├── UserService.java
│   │   │                   │   ├── RetailerService.java
│   │   │                   │   ├── ConsumerService.java
│   │   │                   │   └── CharityService.java
│   │   │                   ├── dao/
│   │   │                   │   ├── UserDao.java
│   │   │                   │   ├── RetailerDao.java
│   │   │                   │   ├── ConsumerDao.java
│   │   │                   │   ├── CharityDao.java
│   │   │                   │   └── FoodItemDao.java
│   │   │                   ├── resource/
│   │   │                   │   ├── UserResource.java
│   │   │                   │   ├── RetailerResource.java
│   │   │                   │   ├── ConsumerResource.java
│   │   │                   │   └── CharityResource.java
│   │   │                   ├── utils/
│   │   │                   │   ├── EmailUtil.java
│   │   │                   │   └── DatabaseUtil.java
│   │   │                   └── ApplicationConfig.java
│   └── webapp/
│       ├── WEB-INF/
│       │   ├── web.xml
│       │   └── views/
│       │       ├── register.jsp
│       │       ├── login.jsp
│       │       ├── retailer/
│       │       │   ├── inventory.jsp
│       │       │   └── surplus.jsp
│       │       ├── consumer/
│       │       │   └── purchase.jsp
│       │       ├── charity/
│       │       │   └── claim.jsp
│       │       └── layout/
│       │           ├── header.jsp
│       │           ├── footer.jsp
│       │           └── navbar.jsp
│       ├── css/
│       ├── js/
│       ├── images/
│       └── index.jsp
├── docs/
│   ├── diagrams/
│   │   ├── usecase_diagram.puml
│   │   ├── deployment_diagram.puml
│   │   ├── usecase_diagram.png
│   │   └── deployment_diagram.png
│   ├── guidlines/
│   │   └── development_guidelines.md
│   └── README.md
├── pom.xml
└── README.md
```
## Directory Explanation

- **src/**: The source code directory of the project.
  - **main/**: Contains the main application code.
    - **java/**: Java source code directory.
      - **com/algonquin/cst8288/fwrps/**: Base package name containing various components.
        - **model/**: Contains all entity classes (e.g., `User`, `Retailer`, `Consumer`, `Charity`, `FoodItem`).
        - **controller/**: Contains all Servlet classes that handle HTTP requests and responses.
        - **service/**: Contains business logic classes (e.g., `UserService`, `RetailerService`, `ConsumerService`, `CharityService`).
        - **dao/**: Contains data access object classes (e.g., `UserDao`, `RetailerDao`, `ConsumerDao`, `CharityDao`, `FoodItemDao`).
        - **resource/**: Contains RESTful API resource classes (e.g., `UserResource`, `RetailerResource`, `ConsumerResource`, `CharityResource`).
        - **utils/**: Contains utility classes (e.g., `EmailUtil`, `DatabaseUtil`).
        - **ApplicationConfig.java**: Configures JAX-RS application path.
    - **webapp/**: Web application content directory.
      - **WEB-INF/**: Contains `web.xml` file and views (JSP files).
        - **views/**: Contains all view files (JSP).
          - **register.jsp**: User registration page.
          - **login.jsp**: User login page.
          - **retailer/**: Retailer-related pages (e.g., `inventory.jsp`, `surplus.jsp`).
          - **consumer/**: Consumer-related pages (e.g., `purchase.jsp`).
          - **charity/**: Charity-related pages (e.g., `claim.jsp`).
          - **layout/**: Common layout files (e.g., `header.jsp`, `footer.jsp`, `navbar.jsp`).
      - **css/**: Contains stylesheet files.
      - **js/**: Contains JavaScript files.
      - **images/**: Contains image files.
      - **index.jsp**: Application home page.

- **docs/**: Project documentation directory.
  - **diagrams/**: Stores all design diagrams (e.g., use case diagrams, deployment diagrams).
  - **guildlines/** Stores all instruction documents.
- **pom.xml**: Maven project configuration file, defines project dependencies and build configurations.

- **README.md**: Project README file, contains basic project information and usage instructions.

## Coding Standards

- **Naming Conventions**: Use meaningful variable names and class names. Follow camelCase naming convention.
- **Code Formatting**: Use consistent code formatting, including indentation and line spacing.
- **Comments**: Add comments where necessary to explain the logic and purpose of the code.
- **Exception Handling**: Handle exceptions appropriately to avoid program crashes.

## Version Control

- **Repository Management**: Use GitHub for version control.
- **Branching Strategy**: Create a new branch for each feature/fix, branch off from the main branch, and merge back into the main branch.
- **Commit Messages**: Commit messages should be concise and descriptive of the changes made.
- **Pull Requests**: All changes should go through a pull request for code review and merging.

## Testing

- **Unit Testing**: Write unit tests using JUnit to test individual components.
- **Integration Testing**: Ensure proper interaction between different components.
- **Test Coverage**: Aim to increase test coverage to ensure critical paths are tested.

---

## References

_Add any references, such as books, articles, documentation, etc._

## Acronyms/Abbreviations

- **MVC**: Model-View-Controller
- **DAO**: Data Access Object
- **JSP**: JavaServer Pages
- **JAX-RS**: Java API for RESTful Web Services
- **ERD**: Entity-Relationship Diagram

## List of Figures

_Should include clickable links for all figures used in the entire report._

```markdown
[Use Case Diagram](docs/diagrams/usecase_diagram.png)
[Deployment Diagram](docs/diagrams/deployment_diagram.png)