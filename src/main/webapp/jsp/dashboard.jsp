<%@ page language="java" contentType="text/html; charset=UTF-8" %>
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
        <title>Dashboard - Food Waste Reduction Platform</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%= request.getContextPath()%>/css/style.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    </head>
    <body>
        <c:import url="jspf/topbar.jsp" />
        <div class="main-content">
            <c:import url="jspf/sidebar.jsp" />

            <div class="content container">
                <h1>Welcome to the Dashboard</h1>
                <p>This is your main area to manage the system.</p>

                <c:choose>
                    <c:when test="${loggedInUser.userType == 'consumer'}">
                        <div class="row">
                            <div class="col-md-3">
                                <div class="card bg-primary mb-3">
                                    <div class="card-header card-header-custom">Total Purchases</div>
                                    <div class="card-body">
                                        <h5 class="card-title" id="totalPurchases">0</h5>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="card bg-success mb-3">
                                    <div class="card-header">Favorite Foods</div>
                                    <div class="card-body">
                                        <h5 class="card-title" id="favoriteFoods">0</h5>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="card bg-warning mb-3">
                                    <div class="card-header">Ratings Given</div>
                                    <div class="card-body">
                                        <h5 class="card-title" id="ratingsGiven">0</h5>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <canvas id="purchaseTrendChart"></canvas>
                        <p>As a consumer, you can purchase discounted food, rate food, and more.</p>

                    </c:when>
                    <c:when test="${loggedInUser.userType == 'organization'}">
                        <div class="row">
                            <div class="col-md-3">
                                <div class="card bg-primary mb-3">
                                    <div class="card-header">Total Claims</div>
                                    <div class="card-body">
                                        <h5 class="card-title" id="totalClaims">0</h5>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="card bg-success mb-3">
                                    <div class="card-header">Successful Claims</div>
                                    <div class="card-body">
                                        <h5 class="card-title" id="successfulClaims">0</h5>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="card bg-warning mb-3">
                                    <div class="card-header">Pending Claims</div>
                                    <div class="card-body">
                                        <h5 class="card-title" id="charityPendingClaims">0</h5>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <canvas id="claimsTrendChart"></canvas>
                        <p>As a charity organization, you can claim food donations and more.</p>
                    </c:when>

                    <c:when test="${loggedInUser.userType == 'retailer'}">
                        <div class="row">
                            <div class="col-md-3">
                                <div class="card bg-primary mb-3">
                                    <div class="card-header">Total Foods</div>
                                    <div class="card-body">
                                        <h5 class="card-title" id="totalFoods">0</h5>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="card bg-success mb-3">
                                    <div class="card-header">Surplus Foods</div>
                                    <div class="card-body">
                                        <h5 class="card-title" id="surplusFoods">0</h5>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="card bg-warning mb-3">
                                    <div class="card-header">Donated Foods</div>
                                    <div class="card-body">
                                        <h5 class="card-title" id="donatedFoods">0</h5>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="card bg-danger mb-3">
                                    <div class="card-header">Pending Claims</div>
                                    <div class="card-body">
                                        <h5 class="card-title" id="pendingClaims">0</h5>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <canvas id="inventoryChart"></canvas>
                        <p>As a retailer, you can manage inventory, list surplus food, and more.</p>
                    </c:when>
                </c:choose>


            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="<%= request.getContextPath()%>/js/dashboard.js"></script>
    </body>
</html>