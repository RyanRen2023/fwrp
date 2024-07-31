<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Register - Food Waste Reduction Platform</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%= request.getContextPath()%>/css/style.css" rel="stylesheet">
        <script>
        var contextPath = '<%= request.getContextPath()%>';
        </script>
    </head>

    <body>
        <div class="container-fluid vh-100">
            <div class="row h-100">
                <!-- Left Side - System Information -->
                <div class="col-md-6 d-flex flex-column justify-content-center align-items-center bg-light text-center p-5">
                    <h1 class="mb-4">Food Waste Reduction Platform</h1>
                    <p class="lead">Join us in our mission to reduce food waste by connecting retailers, charitable organizations, and consumers. Together, we can make a difference!</p>
                </div>
                <!-- Right Side - Registration Form -->
                <div class="col-md-6 d-flex flex-column justify-content-center align-items-center">
                    <div class="card w-75">
                        <div class="card-header text-center">
                            <h2>Register</h2>
                        </div>
                        <div class="card-body">
                            <form id="registerForm" method="post">
                                <div class="form-group">
                                    <label for="username">Username</label>
                                    <input type="text" class="form-control" id="username" required>
                                </div>
                                <div class="form-group">
                                    <label for="email">Email address</label>
                                    <input type="email" class="form-control" id="email" required>
                                </div>
                                <div class="form-group">
                                    <label for="password">Password</label>
                                    <input type="password" class="form-control" id="password" required>
                                </div>
                                <div class="form-group">
                                    <label for="confirmPassword">Confirm Password</label>
                                    <input type="password" class="form-control" id="confirmPassword" required>
                                </div>
                                <div class="form-group">
                                    <label for="role">Role</label>
                                    <select class="form-control" id="role" required>
                                        <option value="Retailer">Retailer</option>
                                        <option value="CharitableOrganization">Charitable Organization</option>
                                        <option value="Consumer">Consumer</option>
                                    </select>
                                </div>
                                <!-- Retailer Specific Fields -->
                                <div id="retailerFields" style="display: none;">
                                    <div class="form-group">
                                        <label for="retailerName">Retailer Name</label>
                                        <input type="text" class="form-control" id="retailerName">
                                    </div>
                                    <div class="form-group">
                                        <label for="retailerAddress">Address</label>
                                        <input type="text" class="form-control" id="retailerAddress">
                                    </div>
                                </div>
                                <!-- Charitable Organization Specific Fields -->
                                <div id="charitableOrgFields" style="display: none;">
                                    <div class="form-group">
                                        <label for="organizationName">Organization Name</label>
                                        <input type="text" class="form-control" id="organizationName">
                                    </div>
                                    <div class="form-group">
                                        <label for="organizationAddress">Address</label>
                                        <input type="text" class="form-control" id="organizationAddress">
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-primary btn-block">Register</button>
                            </form>
                            <div id="registerMessage" class="mt-3"></div>
                        </div>
                        <div class="card-footer text-center">
                            <a href="<%= request.getContextPath()%>/jsp/login.jsp">Already have an account? Login here</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="<%= request.getContextPath()%>/js/sign-up.js"></script>
    </body>

</html>