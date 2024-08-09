<%-- 
    Document   : navbar
    Created on : Jul 28, 2024, 10:47:01 p.m.
    Author     : renxihai
--%>

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
<c:choose>
    <c:when test="${loggedInUser.userType == 'consumer'}">
        <nav class="sidebar">
            <a class="navbar-brand" href="<%= request.getContextPath()%>/consumer-dashboard">Dashboard</a>
            <a class="nav-link" href="<%= request.getContextPath()%>/purchase-claim">Purchase Surplus Food</a>
            <a class="nav-link" href="<%= request.getContextPath()%>/notifications">Notifications</a>
            <a class="nav-link" href="<%= request.getContextPath()%>/subscribe-alerts">Subscribe to Alerts</a>
            <a class="nav-link" href="<%= request.getContextPath()%>/search">Search</a>
            <a class="nav-link" href="<%= request.getContextPath()%>/feedback">Rate and Review Food</a>
        </nav>

    </c:when>
    <c:when test="${loggedInUser.userType == 'organization'}">
        <nav class="sidebar">
            <a class="navbar-brand" href="<%= request.getContextPath()%>/organization-dashboard">Food Waste Reduction</a>
            <a class="nav-link" href="<%= request.getContextPath()%>/purchase-claim">Claim Food</a>
            <a class="nav-link" href="<%= request.getContextPath()%>/notifications">Notifications</a>
            <a class="nav-link" href="<%= request.getContextPath()%>/subscribe-alerts">Subscribe to Alerts</a>
            <a class="nav-link" href="<%= request.getContextPath()%>/search">Search</a>
            <a class="nav-link" href="<%= request.getContextPath()%>/feedback">Feedback</a>

        </nav>
    </c:when>
    <c:when test="${loggedInUser.userType == 'retailer'}">
        <nav class="sidebar">
            <a class="navbar-brand" href="<%= request.getContextPath()%>/retailer-dashboard">Food Waste Reduction</a>
            <a class="nav-link" href="<%= request.getContextPath()%>/inventory">Manage Inventory</a>
            <a class="nav-link" href="<%= request.getContextPath()%>/surplus-food">Identify Surplus Food</a>
            <a class="nav-link" href="<%= request.getContextPath()%>/notifications">Notifications</a>
            <a class="nav-link" href="<%= request.getContextPath()%>/subscribe-alerts">Subscribe to Alerts</a>
            <a class="nav-link" href="<%= request.getContextPath()%>/search">Search</a>
            <a class="nav-link" href="<%= request.getContextPath()%>/feedback">Feedback</a>
        </nav>
    </c:when>
</c:choose>

