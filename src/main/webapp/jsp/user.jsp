<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
    <title>User List</title>
</head>
<body>
    <h1>User List</h1>
    <table border="1">
        <tr>
            <th>UserID</th>
            <th>Username</th>
            <th>Email</th>
            <th>Role</th>
        </tr>
        <c:forEach var="user" items="${userList}">
            <tr>
                <td>${user.getUserID()}</td>
                <td>${user.getUsername()}</td>
                <td>${user.getEmail()}</td>
                <td>${user.getRole()}</td>
            </tr>
        </c:forEach>
    </table>

    <h2>Create New User</h2>
    <form action="UserServlet" method="post">
        <input type="hidden" name="action" value="create">
        Username: <input type="text" name="username" required><br>
        Password: <input type="password" name="password" required><br>
        Email: <input type="email" name="email" required><br>
        Role:
        <select name="role" required>
            <option value="Retailer">Retailer</option>
            <option value="CharitableOrganization">Charitable Organization</option>
            <option value="Consumer">Consumer</option>
        </select><br>
        <input type="submit" value="Create User">
    </form>
</body>
</html>