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
                <form id="subscribeForm" action="<%= request.getContextPath()%>/subscribe-alerts" method="post">
                    <div class="form-group">
                        <label for="alertType">Alert Type</label>
                        <select class="form-control" id="alertType" name="alertType" required>
                            <option value="surplus">Surplus Food Alert</option>
                            <option value="discount">Discounted Food Alert</option>
                            <option value="expiry">Expiring Soon Alert</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="email">Email Address</label>
                        <input type="email" class="form-control" id="email" name="email" required>
                    </div>
                    <button id="subscribeButton" type="submit" class="btn btn-primary">Subscribe</button>
                </form>
                <h2 class="mt-5">Subscribed Alerts</h2>
                <ul class="list-group" id="subscribedAlerts">
                    <c:forEach var="subscribe" items="${subscriptions}">
                        <li class="list-group-item" data-sid="${subscribe.sid}">
                            ${subscribe.alertType} - ${subscribe.email}
                            <button class="btn btn-danger btn-sm float-right delete-subscription">Delete</button>
                            <button class="btn btn-secondary btn-sm float-right edit-subscription mr-2" data-sid="${subscribe.sid}" data-atype="${subscribe.alertType}" data-aemail="${subscribe.email}">Edit</button>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>

        <!-- Edit Modal -->
        <div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editModalLabel">Edit Subscription</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="editForm">
                            <input type="hidden" id="editSid">
                            <div class="form-group">
                                <label for="editAlertType">Alert Type</label>
                                <select class="form-control" id="editAlertType" name="alertType" required>
                                    <option value="surplus">Surplus Food Alert</option>
                                    <option value="discount">Discounted Food Alert</option>
                                    <option value="expiry">Expiring Soon Alert</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="editEmail">Email Address</label>
                                <input type="email" class="form-control" id="editEmail" name="email" required>
                            </div>
                            <button type="submit" class="btn btn-primary">Save changes</button>
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
                $('#subscribeForm').on('submit', function (e) {
                    e.preventDefault();
                    const alertType = $('#alertType').val();
                    const email = $('#email').val();

                    $.ajax({
                        type: 'POST',
                        url: '<%= request.getContextPath()%>/subscribe-alerts',
                        data: {
                            alertType: alertType,
                            email: email
                        },
                        success: function (res) {

                            var alertItem = '<li class="list-group-item" data-sid="' + res.sid + '">' +
                                    res.alertType + ' - ' + res.email +
                                    '<button class="btn btn-danger btn-sm float-right delete-subscription">Delete</button>' +
                                    '<button class="btn btn-secondary btn-sm float-right edit-subscription mr-2" data-sid="' + res.sid + '" data-atype="' + res.alertType + '" data-aemail="' + res.email + '">Edit</button>' +
                                    '</li>';
                            $('#subscribedAlerts').append(alertItem);
                            $('#subscribeForm')[0].reset();
                        },
                        error: function (error) {
                            alert('Failed to add the subscription'+ error);
                        }
                    });
                });

                $('#subscribedAlerts').on('click', '.edit-subscription', function () {
                    const $listItem = $(this).closest('li');
                    const sid = $listItem.data('sid');
                    const alertType = $(this).data('atype');
                    const email = $(this).data('aemail');

                    $('#editSid').val(sid);
                    $('#editAlertType').val(alertType);
                    $('#editEmail').val(email);

                    $('#editModal').modal('show');
                });

                $('#editForm').on('submit', function (e) {
                    e.preventDefault();
                    const sid = $('#editSid').val();
                    const alertType = $('#editAlertType').val();
                    const email = $('#editEmail').val();

                    $.ajax({
                        url: '<%= request.getContextPath()%>/subscribe-alerts',
                        type: 'PUT',
                        data: {
                            sid: sid,
                            alertType: alertType,
                            email: email
                        },
                        success: function (res) {
                            var sid = res.sid;
                            var alertType = res.alertType;
                            var email = res.email;
                            var $listItem = $('#subscribedAlerts li[data-sid="' + sid + '"]');
                            if ($listItem.length) {
                                $listItem.html(
                                        alertType + ' - ' + email +
                                        '<button class="btn btn-danger btn-sm float-right delete-subscription">Delete</button>' +
                                        '<button class="btn btn-secondary btn-sm float-right edit-subscription mr-2" data-sid="' + sid + '" data-atype="' + alertType + '" data-aemail="' + email + '">Edit</button>' + ""
                                        );
                                $('#editModal').modal('hide');
                            } else {
                                console.error('List item not found');
                            }
                        },
                        error: function (e) {
                            alert('Failed to edit the subscription');
                        }
                    });
                });

                $('#subscribedAlerts').on('click', '.delete-subscription', function () {
                    const $listItem = $(this).closest('li');
                    const sid = $listItem.data('sid');

                    $.ajax({
                        url: '<%= request.getContextPath()%>/subscribe-alerts',
                        type: 'DELETE',
                        data: {sid: sid},
                        success: function () {
                            $listItem.remove();
                        },
                        error: function () {
                            alert('Failed to delete the subscription');
                        }
                    });
                });
            });
        </script>
    </body>

</html>