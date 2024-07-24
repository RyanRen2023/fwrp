<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="com.algonquin.cst8288.fwrps.model.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Food Waste Reduction Platform</title>
    <link href="<%= request.getContextPath() %>/css/style.css" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="topbar">
        <div class="system-info">
            <h2>Food Waste Reduction Platform</h2>
        </div>
        <div class="user-info">
            <span>Welcome, <c:out value="${sessionScope.loggedInUser.name}"/></span>
            <form action="<%= request.getContextPath() %>/logout" method="post" style="display:inline;">
                <button type="submit" class="btn btn-danger btn-sm">Logout</button>
            </form>
        </div>
    </div>
    <div class="main-content">
        <nav class="sidebar">
            <a class="navbar-brand" href="#">Food Waste Reduction</a>
            <a class="nav-link" href="dashboard.jsp">Dashboard</a>
            <a class="nav-link" href="inventory.jsp">Manage Inventory</a>
            <a class="nav-link" href="surplus-food.jsp">Surplus Food</a>
            <a class="nav-link" href="purchase-claim.jsp">Purchase/Claim Food</a>
            <a class="nav-link" href="notifications.jsp">Notifications</a>
            <a class="nav-link" href="subscribe-alerts.jsp">Subscribe to Alerts</a>
            <a class="nav-link" href="search.jsp">Search</a>
            <a class="nav-link" href="feedback.jsp">Feedback</a>
        </nav>
        <div class="content">
            <h1>Welcome to the Dashboard</h1>
            <p>This is your main area to manage the system.</p>
            <div id="content">
                <%
                    HttpSession currentSession = request.getSession(false);
                    if (currentSession != null) {
                        User loggedInUser = (User) currentSession.getAttribute("loggedInUser");
                        if (loggedInUser != null) {
                            String userRole = loggedInUser.getUserType();

                            if ("retailer".equals(userRole)) {
                                out.print("<p>As a retailer, you can manage inventory, list surplus food, and more.</p>");
                            } else if ("consumer".equals(userRole)) {
                                out.print("<p>As a consumer, you can purchase discounted food, rate food, and more.</p>");
                            } else if ("charity".equals(userRole)) {
                                out.print("<p>As a charity organization, you can claim food donations and more.</p>");
                            }
                        } else {
                            response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
                        }
                    } else {
                        response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
                    }
                %>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>