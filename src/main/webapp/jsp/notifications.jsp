<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="com.algonquin.cst8288.fwrptomc.model.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    // Retrieve the current session
    HttpSession currentSession = request.getSession(false);
    User loggedInUser = null;
    if (currentSession != null) {
        loggedInUser = (User) currentSession.getAttribute("loggedInUser");
    }

    // Redirect to login page if the user is not logged in
    if (loggedInUser == null) {
        response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
        return; // Stop further execution of the JSP
    }

    // Set the loggedInUser as a request attribute for JSTL use
    request.setAttribute("loggedInUser", loggedInUser);
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Notifications - Food Waste Reduction Platform</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/css/style.css" rel="stylesheet">
</head>

<body>
    <div class="topbar">
        <div class="system-info">
            <h2>Food Waste Reduction Platform</h2>
        </div>
        <div class="user-info">
            <span>Welcome, ${loggedInUser.name}</span>
            <form action="${pageContext.request.contextPath}/logout" method="post" style="display:inline;">
                <button type="submit" class="btn btn-danger btn-sm">Logout</button>
            </form>
        </div>
    </div>
    <div class="main-content">
        <nav class="sidebar">
            <a class="navbar-brand" href="#">Food Waste Reduction</a>
            <a class="nav-link" href="${pageContext.request.contextPath}/dashboard">Dashboard</a>
            <a class="nav-link" href="${pageContext.request.contextPath}/inventory">Manage Inventory</a>
            <a class="nav-link" href="${pageContext.request.contextPath}/surplus-food">Surplus Food</a>
            <a class="nav-link" href="${pageContext.request.contextPath}/purchase-claim">Purchase/Claim Food</a>
            <a class="nav-link" href="${pageContext.request.contextPath}/notifications">Notifications</a>
            <a class="nav-link" href="${pageContext.request.contextPath}/subscribe-alerts">Subscribe to Alerts</a>
            <a class="nav-link" href="${pageContext.request.contextPath}/search">Search</a>
            <a class="nav-link" href="${pageContext.request.contextPath}/feedback">Feedback</a>
        </nav>
        <div class="content container">
            <h1>Notifications</h1>
            <div class="list-group">
                <a href="#" class="list-group-item list-group-item-action">
                    <div class="d-flex w-100 justify-content-between">
                        <h5 class="mb-1">Surplus Food Alert: Apples</h5>
                        <small>3 days ago</small>
                    </div>
                    <p class="mb-1">50 Apples available for claiming.</p>
                    <small>Expires on: 2023-12-01</small>
                </a>
                <a href="#" class="list-group-item list-group-item-action">
                    <div class="d-flex w-100 justify-content-between">
                        <h5 class="mb-1">Discounted Sale: Bananas</h5>
                        <small>5 days ago</small>
                    </div>
                    <p class="mb-1">30 Bananas available at a discounted price.</p>
                    <small>Expires on: 2023-11-15</small>
                </a>
                <a href="#" class="list-group-item list-group-item-action">
                    <div class="d-flex w-100 justify-content-between">
                        <h5 class="mb-1">Surplus Food Alert: Carrots</h5>
                        <small>1 week ago</small>
                    </div>
                    <p class="mb-1">20 Carrots available for claiming.</p>
                    <small>Expires on: 2023-10-20</small>
                </a>
                <!-- Add more notifications as needed -->
            </div>
        </div>
    </div>

    <script>
        function logout() {
            // Implement logout functionality
            alert('Logging out...');
        }

        $(document).ready(function () {
            $('.nav-link').on('click', function (e) {
                e.preventDefault();
                const target = $(this).data('target');
                $('.content').load(target);
            });
        });
    </script>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

</html>