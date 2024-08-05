<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.algonquin.cst8288.fwrptomc.model.Food" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Food Item - Food Waste Reduction Platform</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1>Update Food Item</h1>
    <form method="post" action="<%= request.getContextPath() %>/inventory?actionUser=update">
        <!-- Hidden field for Food ID -->
        <input type="hidden" id="fid" name="fid" value="${food.fid}">

        <div class="form-group">
            <label for="fname">Item Name</label>
            <input type="text" class="form-control" id="fname" name="fname" value="${food.fname}" required>
        </div>
        <div class="form-group">
            <label for="expiration">Expiration Date</label>
            <input type="date" class="form-control" id="expiration" name="expiration" value="${food.expiration}" required>
        </div>
        <div class="form-group">
            <label for="price">Price</label>
            <input type="number" step="0.01" class="form-control" id="price" name="price" value="${food.price}" required>
        </div>
        <div class="form-group">
            <label for="inventory">Quantity</label>
            <input type="number" class="form-control" id="inventory" name="inventory" value="${food.inventory}" required>
        </div>
        <div class="form-group">
            <label for="discount">Discount</label>
            <input type="number" step="0.01" class="form-control" id="discount" name="discount" value="${food.discount}">
        </div>
        <div class="form-group">
            <label for="ftid">Food Type ID</label>
            <input type="number" class="form-control" id="ftid" name="ftid" value="${food.ftid}" required>
        </div>
        <div class="form-group">
            <label for="is_donate">Is Donate</label>
            <select class="form-control" id="is_donate" name="is_donate" required>
                <option value="0" <c:if test="${food.is_donate == 0}">selected</c:if>>No</option>
                <option value="1" <c:if test="${food.is_donate == 1}">selected</c:if>>Yes</option>
            </select>
        </div>
        <div class="form-group">
            <label for="store_id">Store ID</label>
            <input type="number" class="form-control" id="store_id" name="store_id" value="${food.store_id}" required>
        </div>
        <button type="submit" class="btn btn-primary">Update Item</button>
        <a href="<%= request.getContextPath() %>/inventory" class="btn btn-secondary">Cancel</a>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

