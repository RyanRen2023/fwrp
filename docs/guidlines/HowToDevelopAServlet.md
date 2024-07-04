
# How to Develop a Servlet using Jakarta EE, TomEE, and JDK 17

## Introduction

A servlet is a Java programming language class used to extend the capabilities of servers that host applications accessed by means of a request-response programming model. With Jakarta EE and TomEE, you can develop robust web applications using the latest Java standards.

## Steps to Develop a Servlet

### 1. Set Up Your Development Environment

Ensure you have the following installed:
- JDK 17
- An Integrated Development Environment (IDE) like Apache NetBeans
- Apache TomEE (or any other Jakarta EE compatible server)

### 2. Create a New Project

1. Open your IDE and create a new Maven project.
2. Add the necessary dependencies to your `pom.xml`.

Example `pom.xml`:
```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.algonquin.cst8288</groupId>
    <artifactId>fwrps</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>war</packaging>
    <name>fwrps-1.0-SNAPSHOT</name>
    
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <jakartaee>10.0.0</jakartaee>
    </properties>
    
    <dependencies>
        <dependency>
            <groupId>jakarta.platform</groupId>
            <artifactId>jakarta.jakartaee-api</artifactId>
            <version>${jakartaee}</version>
            <scope>provided</scope>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>17</source>
                    <target>17</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-war-plugin</artifactId>
                <version>3.3.2</version>
            </plugin>
        </plugins>
    </build>
</project>
```

### 3. Define Your Servlet

Create a new Java class that extends `HttpServlet`. Override the `doGet` or `doPost` methods depending on your needs.

Example:
```java
package com.example;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().println("<h1>Hello, World!</h1>");
    }
}
```

### 4. Configure Your Servlet

You need to configure your servlet in the `web.xml` file located in the `WEB-INF` directory, or use annotations as shown in the example above.

Example using `web.xml`:
```xml
<web-app xmlns="https://jakarta.ee/xml/ns/jakartaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="https://jakarta.ee/xml/ns/jakartaee
         https://jakarta.ee/xml/ns/jakartaee/web-app_5_0.xsd"
         version="5.0">

    <servlet>
        <servlet-name>HelloServlet</servlet-name>
        <servlet-class>com.example.HelloServlet</servlet-class>
    </servlet>

    <servlet-mapping>
        <servlet-name>HelloServlet</servlet-name>
        <url-pattern>/hello</url-pattern>
    </servlet-mapping>
</web-app>
```

### 5. Deploy Your Servlet

1. Right-click your project and select `Run As > Run on Server`.
2. Choose Apache TomEE and click `Finish`.

### 6. Access Your Servlet

Open a web browser and go to `http://localhost:8080/your-project-name/hello` to see your servlet in action.

## Conclusion

You've successfully developed a simple servlet using Jakarta EE, TomEE, and JDK 17. From here, you can expand its functionality by handling different HTTP methods, processing form data, and interacting with databases.

## References

- [Jakarta Servlet Documentation](https://jakarta.ee/specifications/servlet/5.0/)
- [Apache TomEE Documentation](http://tomee.apache.org/)
