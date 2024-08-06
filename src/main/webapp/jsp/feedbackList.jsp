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
    if (loggedInUser == null || !loggedInUser.getUserType().equals("retailer")) {
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
        <title>View Feedback - Food Waste Reduction Platform</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%= request.getContextPath()%>/css/style.css" rel="stylesheet">
    </head>
    <body>
        <c:import url="jspf/topbar.jsp" />
        <div class="main-content">
            <c:import url="jspf/sidebar.jsp" />

            <div class="content container">
                <h1>Submitted Feedback</h1>
                <ul class="list-group" id="feedbackList">
                    <c:choose>
                        <c:when test="${not empty feedbackList}">
                            <c:forEach var="feedback" items="${feedbackList}">
                                <li class="list-group-item">
                                    <h5>${feedback.fname} - Rating: ${feedback.rating}</h5>
                                    <p>${feedback.review}</p>
                                    <small>Submitted by: ${feedback.userType}</small>
                                    <small>Created at: ${feedback.createdAt}</small>
                                </li>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <li class="list-group-item">
                                There is no feedback right now!
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>