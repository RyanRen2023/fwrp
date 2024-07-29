<%-- 
    Document   : navbar
    Created on : Jul 28, 2024, 10:47:01 p.m.
    Author     : renxihai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="sidebar">
    <a class="navbar-brand" href="<%= request.getContextPath()%>/dashboard">Food Waste Reduction</a>
    <a class="nav-link" href="<%= request.getContextPath()%>/dashboard">Dashboard</a>
    <a class="nav-link" href="<%= request.getContextPath()%>/inventory">Manage Inventory</a>
    <a class="nav-link" href="<%= request.getContextPath()%>/surplus-food">Surplus Food</a>
    <a class="nav-link" href="<%= request.getContextPath()%>/purchase-claim">Purchase/Claim Food</a>
    <a class="nav-link" href="<%= request.getContextPath()%>/notifications">Notifications</a>
    <a class="nav-link" href="<%= request.getContextPath()%>/subscribe-alerts">Subscribe to Alerts</a>
    <a class="nav-link" href="<%= request.getContextPath()%>/search">Search</a>
    <a class="nav-link" href="<%= request.getContextPath()%>/feedback">Feedback</a>
</nav>
