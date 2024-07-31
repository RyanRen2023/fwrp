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
        <title>Purchase/Claim Food - Food Waste Reduction Platform</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%= request.getContextPath()%>/css/style.css" rel="stylesheet">
    </head>

    <body>
        <c:import url="jspf/topbar.jsp" />
        <div class="main-content">
            <c:import url="jspf/sidebar.jsp" />
            <div class="content container">
                <h1>Purchase/Claim Food</h1>
                <div class="row">
                    <div class="col-md-4">
                        <div class="card mb-4">
                            <div class="card-body">
                                <h5 class="card-title">Apple</h5>
                                <p class="card-text">Quantity: 50</p>
                                <p class="card-text">Expiration Date: 2023-12-01</p>
                                <button class="btn btn-primary">Purchase</button>
                                <button class="btn btn-success">Claim</button>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card mb-4">
                            <div class="card-body">
                                <h5 class="card-title">Banana</h5>
                                <p class="card-text">Quantity: 30</p>
                                <p class="card-text">Expiration Date: 2023-11-15</p>
                                <button class="btn btn-primary">Purchase</button>
                                <button class="btn btn-success">Claim</button>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card mb-4">
                            <div class="card-body">
                                <h5 class="card-title">Carrots</h5>
                                <p class="card-text">Quantity: 20</p>
                                <p class="card-text">Expiration Date: 2023-10-20</p>
                                <button class="btn btn-primary">Purchase</button>
                                <button class="btn btn-success">Claim</button>
                            </div>
                        </div>
                    </div>
                    <!-- Add more food items as needed -->
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