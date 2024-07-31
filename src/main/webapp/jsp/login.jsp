<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login - Food Waste Reduction Platform</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%= request.getContextPath()%>/css/style.css" rel="stylesheet">
    </head>

    <body>
        <div class="container-fluid vh-100">
            <div class="row h-100">
                <!-- Left Side - System Information -->
                <div class="col-md-6 d-flex flex-column justify-content-center align-items-center bg-light text-center p-5">
                    <h1 class="mb-4">Food Waste Reduction Platform</h1>
                    <p class="lead">Our mission is to reduce food waste by connecting retailers, charitable organizations, and consumers. Join us in making a difference!</p>
                </div>
                <!-- Right Side - Login Form -->
                <div class="col-md-6 d-flex flex-column justify-content-center align-items-center">
                    <div class="card w-75">
                        <div class="card-header text-center">
                            <h2>Login</h2>
                        </div>
                        <div class="card-body">
                            <form id="loginForm" method="post" action="<%= request.getContextPath()%>/login">
                                <div class="form-group">
                                    <label for="email">Email address</label>
                                    <input type="email" class="form-control" id="email" name="email" required>
                                </div>
                                <div class="form-group">
                                    <label for="password">Password</label>
                                    <input type="password" class="form-control" id="password" name="password" required>
                                </div>
                                <button type="submit" class="btn btn-primary btn-block">Login</button>
                            </form>
                            <div id="loginMessage" class="mt-3">
                                <% if (request.getAttribute("errorMessage") != null) {%>
                                <div class="alert alert-danger"><%= request.getAttribute("errorMessage")%></div>
                                <% }%>
                            </div>
                        </div>
                        <div class="card-footer text-center">
                            <a href="sign-up.jsp">Don't have an account? Register here</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>

</html>