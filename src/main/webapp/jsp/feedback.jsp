<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="com.algonquin.cst8288.fwrptomc.model.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Feedback - Food Waste Reduction Platform</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%= request.getContextPath()%>/css/style.css" rel="stylesheet">
    </head>
    <body>
        <c:import url="jspf/topbar.jsp" />
        <div class="main-content">
            <c:import url="jspf/sidebar.jsp" />
            <div class="content container">
                <h1>Feedback</h1>
                <form id="feedbackForm" method="post" action="<%= request.getContextPath()%>/feedback">
                    <input type="hidden" name="action" value="submit">
                    <div class="form-group">
                        <label for="foodItem">Food Item</label>
                        <select class="form-control" id="foodItem" name="foodItem" required>
                            <c:forEach var="item" items="${items}">
                                <option value="${item.fid}">${item.fname}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="rating">Rating</label>
                        <select class="form-control" id="rating" name="rating" required>
                            <option value="5">5 - Excellent</option>
                            <option value="4">4 - Good</option>
                            <option value="3">3 - Average</option>
                            <option value="2">2 - Poor</option>
                            <option value="1">1 - Very Poor</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="comments">Comments</label>
                        <textarea class="form-control" id="comments" name="comments" rows="3" required></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Submit Feedback</button>
                </form>
                <h2 class="mt-5">Submitted Feedback</h2>
                <ul class="list-group" id="feedbackList">
                    <!-- Feedback items will be dynamically added here -->
                </ul>
            </div>
        </div>



        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

        <script>
            $(document).ready(function () {
                $('#feedbackForm').on('submit', function (e) {
                    e.preventDefault();
                    const foodItem = $('#foodItem').val();
                    const rating = $('#rating').val();
                    const comments = $('#comments').val();


                    $.post('<%= request.getContextPath()%>/feedback', {
                        action: 'submit',
                        foodItem: foodItem,
                        rating: rating,
                        comments: comments
                    }, function (response) {
                        // Handle response if needed
                        if (response.status === 'success') {
                            alert('Feedback submitted successfully!');
                            let foodName = response.foodName;
                            let feedbackItem = '<li class="list-group-item">' +
                                    '<h5>' + foodItem + '-' + foodName + ' - Rating: ' + rating + '</h5>' +
                                    '<p>' + comments + '</p>' +
                                    '</li>';
                            $('#feedbackList').append(feedbackItem);
                            $('#feedbackForm')[0].reset();
                        }
                    });
                });
            });
        </script>
    </body>
</html>