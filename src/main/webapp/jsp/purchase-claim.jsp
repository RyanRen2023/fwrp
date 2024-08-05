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
                <c:choose>
                    <c:when test="${loggedInUser.userType == 'consumer'}">
                        <h1>Purchase Food</h1>
                        <button class="btn btn-info mb-4" id="viewPurchaseBtn">View All Purchases</button>

                    </c:when>
                    <c:when test="${loggedInUser.userType == 'organization'}">
                        <h1>Claim Food</h1>
                        <button class="btn btn-info mb-4" id="viewClaimsBtn">View All Claims</button>
                    </c:when>
                </c:choose>

                <div class="row">
                    <c:forEach var="food" items="${foodList}">
                        <div class="col-md-4">
                            <div class="card mb-4">
                                <div class="card-body">
                                    <h5 class="card-title">${food.fname}</h5>
                                    <p id="quantity-${food.fid}" class="card-text">Quantity: ${food.inventory}</p>
                                    <p class="card-text">Expiration Date: ${food.expiration}</p>
                                    <c:choose>
                                        <c:when test="${loggedInUser.userType == 'consumer'}">
                                            <button class="btn btn-primary purchase-btn" data-fid="${food.fid}" data-inventory="${food.inventory}">Purchase</button>
                                        </c:when>
                                        <c:when test="${loggedInUser.userType == 'organization'}">
                                            <button class="btn btn-success claim-btn" data-fid="${food.fid}" data-inventory="${food.inventory}">Claim</button>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

        <!-- Claim Quantity Modal -->
        <div class="modal fade" id="claimModal" tabindex="-1" aria-labelledby="claimModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-custom">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="claimModalLabel">Claim Quantity</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="claimForm">
                            <div class="form-group">
                                <label for="quantity">Quantity</label>
                                <input type="number" class="form-control" id="quantity" name="quantity" min="1" max="1" required>
                            </div>
                            <input type="hidden" id="foodId" name="foodId">
                            <button type="submit" class="btn btn-success">Claim</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- View Claims Modal -->
        <div class="modal fade" id="viewClaimsModal" tabindex="-1" aria-labelledby="viewClaimsModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="viewClaimsModalLabel">All Claims</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <ul id="claimsList" class="list-group">
                            <!-- Claims will be loaded here dynamically -->
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <!-- Purchase Quantity Modal -->
        <div class="modal fade" id="purchaseModal" tabindex="-1" aria-labelledby="purchaseModal" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="purchaseModalLabel">Purchase Quantity</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="purchaseForm">
                            <div class="form-group">
                                <label for="quantity">Quantity</label>
                                <input type="number" class="form-control" id="purchase-quantity" name="purchase-quantity" min="1" max="1" required>
                            </div>
                            <input type="hidden" id="purchase-foodId" name="purchase-foodId">
                            <button type="submit" class="btn btn-success">Purchase</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- View Purchase Modal -->
        <div class="modal fade" id="viewPurchaseModal" tabindex="-1" aria-labelledby="viewClaimsModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="viewClaimsModalLabel">All Purchases</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <ul id="purchaseList" class="list-group">
                            <!-- Claims will be loaded here dynamically -->
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script>
            $(document).ready(function () {
                

                //purchase

                $('.purchase-btn').on('click', function () {
                    const fid = $(this).data('fid');
                    const inventory = $(this).data('inventory');
                    $('#purchase-foodId').val(fid);
                    $('#purchase-quantity').attr('max', inventory);
                    $('#purchase-quantity').val('');
                    $('#purchaseModal').modal('show');
                });

                $('#purchaseForm').on('submit', function (e) {
                    e.preventDefault();
                    const formData = $(this).serialize();
                    const fid = $('#purchase-foodId').val();
                    const purchaseQuantity = $('#purchase-quantity').val();
                    $.post('<%= request.getContextPath()%>/purchase-claim', formData + '&action=purchase', function (response) {
                        const currentQuantity = parseInt($('#quantity-' + fid).text().split(': ')[1]);
                        const newQuantity = currentQuantity - purchaseQuantity;
                        $('#quantity-' + fid).text('Quantity: ' + newQuantity);
                        alert('Purchase successful!');
                        $('#purchaseModal').modal('hide');
                    });
                });

                $('#viewPurchaseBtn').on('click', function () {
                    $.get('<%= request.getContextPath()%>/purchase-claim', {action: 'viewPurchase'}, function (data) {
                        const purchaseList = $('#purchaseList');
                        purchaseList.empty();
                        data.forEach(function (orders) {
                            const purchaseDate = orders.purchaseDate;
                            purchaseList.append('<li class="list-group-item">Food ID: ' + orders.foodId + ', Quantity: ' + orders.num + ', Money ' + orders.money + ', Date: ' + purchaseDate + '</li>');
                        });
                        $('#viewPurchaseModal').modal('show');
                    });
                });

                // claim



                $('.claim-btn').on('click', function () {
                    const fid = $(this).data('fid');
                    const inventory = $(this).data('inventory');
                    $('#foodId').val(fid);
                    $('#quantity').attr('max', inventory);
                    $('#quantity').val('');
                    $('#claimModal').modal('show');
                });




                // claim logic
                $('#claimForm').on('submit', function (e) {
                    e.preventDefault();
                    const formData = $(this).serialize();
                    const fid = $('#foodId').val();
                    const claimedQuantity = $('#quantity').val();
                    $.post('<%= request.getContextPath()%>/purchase-claim', formData + '&action=claim', function (response) {
                        const currentQuantity = parseInt($('#quantity-' + fid).text().split(': ')[1]);
                        const newQuantity = currentQuantity - claimedQuantity;
                        $('#quantity-' + fid).text('Quantity: ' + newQuantity);
                        alert('Claim successful!');
                        $('#claimModal').modal('hide');
                    });
                });

                $('#viewClaimsBtn').on('click', function () {
                    $.get('<%= request.getContextPath()%>/purchase-claim', {action: 'viewClaims'}, function (data) {
                        const claimsList = $('#claimsList');
                        claimsList.empty();
                        data.forEach(function (claim) {
                            const claimDate = claim.claimDate.date + ' ' +
                                    claim.claimDate.time.hour + ':' +
                                    claim.claimDate.time.minute + ':' +
                                    claim.claimDate.time.second;
                            claimsList.append('<li class="list-group-item">Food ID: ' + claim.foodId + ', Quantity: ' + claim.quantity + ', Date: ' + claimDate + '</li>');
                        });
                        $('#viewClaimsModal').modal('show');
                    });
                });


            });
        </script>
    </body>

</html>