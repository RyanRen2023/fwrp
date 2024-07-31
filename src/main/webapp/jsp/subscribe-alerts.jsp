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
        <title>Subscribe to Alerts - Food Waste Reduction Platform</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%= request.getContextPath()%>/css/style.css" rel="stylesheet">
    </head>

    <body>
        <c:import url="jspf/topbar.jsp" />
        <div class="main-content">
            <c:import url="jspf/sidebar.jsp" />
            <div class="content container">
                <h1>Subscribe to Alerts</h1>
                <form id="subscribeForm">
                    <div class="form-group">
                        <label for="alertType">Alert Type</label>
                        <select class="form-control" id="alertType" required>
                            <option value="surplus">Surplus Food Alert</option>
                            <option value="discount">Discounted Food Alert</option>
                            <option value="expiry">Expiring Soon Alert</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="email">Email Address</label>
                        <input type="email" class="form-control" id="email" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Subscribe</button>
                </form>
                <h2 class="mt-5">Subscribed Alerts</h2>
                <ul class="list-group" id="subscribedAlerts">
                    <!-- Subscribed alerts will be dynamically added here -->
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

                $('#subscribeForm').on('submit', function (e) {
                    e.preventDefault();
                    const alertType = $('#alertType').val();
                    const email = $('#email').val();
                    const alertItem = `<li class="list-group-item">${alertType} - ${email}</li>`;
                    $('#subscribedAlerts').append(alertItem);
                    $('#subscribeForm')[0].reset();
                });
            });
        </script>

        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>

</html>