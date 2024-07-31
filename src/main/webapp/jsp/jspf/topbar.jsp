<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
<div class="topbar">
    <div class="system-info">
        <h2>Food Waste Reduction Platform</h2>
    </div>
    <div class="user-info">
        <span>Welcome, ${loggedInUser.name}</span>
        <form id="logoutForm" action="${pageContext.request.contextPath}/logout" method="post" style="display:inline;">
            <button type="submit" class="btn btn-danger btn-sm">Logout</button>
        </form>
    </div>

</div>
<script src="<%= request.getContextPath()%>/js/script.js"></script>

