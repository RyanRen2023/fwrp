<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.algonquin.cst8288.fwrptomc.model.OrganizationDashboardStatistics" %>
<%@ page import="com.algonquin.cst8288.fwrptomc.model.User" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    HttpSession currentSession = request.getSession(false);
    User loggedInUser = (User) currentSession.getAttribute("loggedInUser");

    if (loggedInUser == null || !"organization".equals(loggedInUser.getUserType())) {
        response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
        return;
    }

    OrganizationDashboardStatistics statistics = (OrganizationDashboardStatistics) request.getAttribute("statistics");
%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Organization Dashboard - Food Waste Reduction Platform</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%= request.getContextPath()%>/css/style.css" rel="stylesheet">
        <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script> <!-- Include Font Awesome for icons -->
    </head>
    <body>
        <c:import url="jspf/topbar.jsp" />
        <div class="main-content">
            <c:import url="jspf/sidebar.jsp" />

            <div class="content container">
                <h1 class="my-4">Organization Dashboard</h1>
                <div class="profile-summary dashboard-card">
                    <div class="dashboard-card-header">
                        <i class="icon fas fa-user"></i>Profile Summary
                    </div>
                    <div class="dashboard-card-body">
                        <p>Name: ${loggedInUser.name}</p>
                        <p>Email: ${loggedInUser.email}</p>
                    </div>
                </div>
                <div class="order-statistics">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="card dashboard-card stat-card">
                                <div class="card-body">
                                    <h2>${statistics.totalClaims}</h2>
                                    <p>Total Claims</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="card dashboard-card stat-card">
                                <div class="card-body">
                                    <h2>${statistics.mostClaimedFoodItem}</h2>
                                    <p>Most Claimed Food Item</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="https://kit.fontawesome.com/a076d05399.js"></script> <!-- Include Font Awesome for icons -->
    </body>
</html>