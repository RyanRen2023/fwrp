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
    <title>Search - Food Waste Reduction Platform</title>
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
            <h1>Search</h1>
            <form id="searchForm">
                <div class="form-group">
                    <label for="searchQuery">Search Query</label>
                    <input type="text" class="form-control" id="searchQuery" required>
                </div>
                <button type="submit" class="btn btn-primary">Search</button>
            </form>
            <h2 class="mt-5">Search Results</h2>
            <ul class="list-group" id="searchResults">
                <!-- Search results will be dynamically added here -->
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

            $('#searchForm').on('submit', function (e) {
                e.preventDefault();
                const query = $('#searchQuery').val();
                const results = [
                    // Sample search results
                    { title: 'Apple Surplus', description: '50 Apples available for claiming.', link: '#' },
                    { title: 'Banana Discount', description: '30 Bananas available at a discounted price.', link: '#' },
                    { title: 'Carrots Surplus', description: '20 Carrots available for claiming.', link: '#' }
                ];
                displayResults(results);
            });

            function displayResults(results) {
                const resultsList = $('#searchResults');
                resultsList.empty();
                results.forEach(result => {
                    const resultItem = `<li class="list-group-item">
                                            <h5>${result.title}</h5>
                                            <p>${result.description}</p>
                                            <a href="${result.link}" class="btn btn-primary">View</a>
                                        </li>`;
                    resultsList.append(resultItem);
                });
            }
        });
    </script>

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

</html>