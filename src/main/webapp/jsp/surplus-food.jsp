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
        <title>Surplus Food - Food Waste Reduction Platform</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%= request.getContextPath()%>/css/style.css" rel="stylesheet">
    </head>

    <body>
        <c:import url="jspf/topbar.jsp" />
        <div class="main-content">
            <c:import url="jspf/sidebar.jsp" />
            <div class="content container">
                <h1>Surplus Food</h1>
                <div class="row" id="surplusFoodContainer">
                    <!-- Surplus food items will be loaded here dynamically -->
                </div>
            </div>
        </div>

        <!-- Identify and Donation Modal -->
        <div class="modal fade" id="claimModal" tabindex="-1" aria-labelledby="claimModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="claimModalLabel">Identify Surplus Food and Donation</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="claimForm">
                            <div class="form-group">
                                <div class="form-check">
                                    <input type="checkbox" class="form-check-input" id="isSurplus" name="isSurplus">
                                    <label class="form-check-label" for="isSurplus">Mark as Surplus</label>
                                </div>
                                <div class="form-check">
                                    <input type="checkbox" class="form-check-input" id="isDonate" name="isDonate">
                                    <label class="form-check-label" for="isDonate">Mark as Donate</label>
                                </div>
                            </div>
                            <input type="hidden" id="foodId" name="foodId">
                            <button type="submit" class="btn btn-success">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script>
            $(document).ready(function () {
                loadSurplusFood();

                function loadSurplusFood() {
                    $.getJSON('<%= request.getContextPath()%>/surplus-food?action=list', function (data) {
                        var surplusFoodContainer = $('#surplusFoodContainer');
                        surplusFoodContainer.empty();
                        $.each(data, function (index, food) {
                            var surplus = food.isSurplus == '0' ? 'No' : 'Yes';
                            var donate = food.isDonate == '0' ? 'No' : 'Yes';
                            var foodCard = '<div class="col-md-4">' +
                                    '<div class="card mb-4">' +
                                    '<div class="card-body">' +
                                    '<h5 class="card-title">' + food.fname + '</h5>' +
                                    '<p class="card-text">Quantity: ' + food.inventory + '</p>' +
                                    '<p class="card-text">Expiration Date: ' + food.expiration + '</p>' +
                                    '<p class="card-text">Identified: ' + surplus + '</p>' +
                                    '<p class="card-text">Declare Donated: ' + donate + '</p>' +
                                    '<button class="btn btn-primary identify-btn" data-fid="' + food.fid + '" data-issurplus="' + food.isSurplus + '" data-isdonate="' + food.isDonate + '">Identify</button>' +
                                    '</div>' +
                                    '</div>' +
                                    '</div>';
                            surplusFoodContainer.append(foodCard);
                        });

                        $('.identify-btn').on('click', function () {
                            var fid = $(this).data('fid');
                            var isDonate = $(this).data('isdonate');
                            var isSurplus = $(this).data('issurplus');
                            var donate = (isDonate > 0) ? true : false;
                            var surplus = (isSurplus > 0) ? true : false;
                            $('#foodId').val(fid);
                            $('#isDonate').prop('checked', donate);
                            $('#isSurplus').prop('checked', surplus);
                            $('#claimModal').modal('show');
                        });
                    });
                }

                $('#claimForm').on('submit', function (e) {
                    e.preventDefault();
                    var formData = $(this).serialize();
                    var fid = $('#foodId').val();
                    $.post('<%= request.getContextPath()%>/surplus-food?action=identify', formData, function (response) {
                        if (response.status === 'success') {
                            alert(response.message);
                            $('#claimModal').modal('hide');
                            loadSurplusFood(); // Reload the food list
                        } else {
                            alert('Identification failed: ' + response.message);
                        }
                    });
                });
            });
        </script>
    </body>

</html>