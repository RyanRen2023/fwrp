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
    <title>Feedback - Food Waste Reduction Platform</title>
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
            <h1>Feedback</h1>
            <form id="feedbackForm">
                <div class="form-group">
                    <label for="foodItem">Food Item</label>
                    <input type="text" class="form-control" id="foodItem" required>
                </div>
                <div class="form-group">
                    <label for="rating">Rating</label>
                    <select class="form-control" id="rating" required>
                        <option value="5">5 - Excellent</option>
                        <option value="4">4 - Good</option>
                        <option value="3">3 - Average</option>
                        <option value="2">2 - Poor</option>
                        <option value="1">1 - Very Poor</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="comments">Comments</label>
                    <textarea class="form-control" id="comments" rows="3" required></textarea>
                </div>
                <button type="submit" class="btn btn-primary">Submit Feedback</button>
            </form>
            <h2 class="mt-5">Submitted Feedback</h2>
            <ul class="list-group" id="feedbackList">
                <!-- Feedback items will be dynamically added here -->
            </ul>
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

            $('#feedbackForm').on('submit', function (e) {
                e.preventDefault();
                const foodItem = $('#foodItem').val();
                const rating = $('#rating').val();
                const comments = $('#comments').val();
                const feedbackItem = `
                    <li class="list-group-item">
                        <h5>${foodItem} - Rating: ${rating}</h5>
                        <p>${comments}</p>
                    </li>`;
                $('#feedbackList').append(feedbackItem);
                $('#feedbackForm')[0].reset();
            });
        });
    </script>

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

</html>