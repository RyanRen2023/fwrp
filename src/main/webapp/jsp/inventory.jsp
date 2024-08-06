<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="com.algonquin.cst8288.fwrptomc.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.algonquin.cst8288.fwrptomc.model.Food" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Manage Inventory - Food Waste Reduction Platform</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%= request.getContextPath()%>/css/style.css" rel="stylesheet">
    </head>
    <body>
        <c:import url="jspf/topbar.jsp" />
        <div class="main-content">
            <c:import url="jspf/sidebar.jsp" />
            <div class="content container">
                <h1>Manage Inventory</h1>
                <!-- Button trigger modal -->
                <a href="#" class="btn btn-primary mb-3" data-toggle="modal" data-target="#addItemModal">Add Item</a>
                <form action="${pageContext.request.contextPath}/inventory">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" name="expiration" value="check" id="flexCheckChecked" ${requestScope.expiration != null? 'checked' : ''} onchange="this.form.submit()">
                        <label class="form-check-label" for="flexCheckChecked">
                            Is Expiration After week
                        </label>
                    </div>
                </form>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Item Name</th>
                            <th>Quantity</th>
                            <th>Expiration Date</th>
                            <th>Price</th>
                            <th>Discount</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody id="inventory-table">
                        <c:forEach var="food" items="${foodList}">
                            <tr>
                                <td>${food.fname}</td>
                                <td>${food.inventory}</td>
                                <td>${food.expiration}</td>
                                <td>${food.price}</td>
                                <td>${food.discount}</td>
                                <td>
                                    <a href="#" class="btn btn-secondary" data-toggle="modal" data-target="#editItemModal${food.fid}">Edit</a>
                                    <div class="modal fade" id="editItemModal${food.fid}" tabindex="-1" role="dialog" aria-labelledby="addItemModalLabel" aria-hidden="true">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="editItemModal${food.fid}">Edit Food Item</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <form method="post" action="${pageContext.request.contextPath}/inventory?actionUser=update">
                                                    <div class="modal-body">
                                                        <input type="hidden" name="fid" value="${food.fid}">
                                                        <div class="form-group">
                                                            <label for="fname">Item Name</label>
                                                            <input type="text" class="form-control" id="fname" name="fname" value="${food.fname}" required>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="expiration">Expirations Date</label>
                                                            <input type="date" class="form-control" id="expiration" name="expiration" value="${food.expiration.toString()}" required>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="price">Price</label>
                                                            <input type="number" step="0.01" class="form-control" id="price" name="price" value="${food.price.intValue()}" required>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="inventory">Quantity</label>
                                                            <input type="number" class="form-control" id="inventory" name="inventory" value="${food.inventory}" required>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="discount">Discount</label>
                                                            <input type="number" step="0.01" class="form-control" id="discount" value="${food.discount}" name="discount">
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="ftid">Food Type ID</label>
                                                            <input type="number" class="form-control" id="ftid" value="${food.ftid}" name="ftid" required>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="is_donate">Is Donate</label>
                                                            <select class="form-control" id="is_donate" name="is_donate" required>
                                                                <option value="0" ${food.isDonate == 0? 'selected' : ''}>No</option>
                                                                <option value="1" ${food.isDonate == 1? 'selected' : ''}>Yes</option>
                                                            </select>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="store_id">Store ID</label>
                                                            <input type="number" class="form-control" id="store_id" value="${food.storeId}" name="store_id" required>
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                        <button type="submit" class="btn btn-primary">Edit Item</button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>

                                    <form method="post" action="${pageContext.request.contextPath}/inventory?actionUser=delete">
                                        <input type="hidden" name="actionUser" value="delete">
                                        <input type="hidden" name="fid" value="${food.fid}">
                                        <button type="submit" class="btn btn-secondary">Delete</button>
                                    </form>
                                </td>   
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- Add Item Modal -->
        <div class="modal fade" id="addItemModal" tabindex="-1" role="dialog" aria-labelledby="addItemModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addItemModalLabel">Add New Food Item</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form method="post" action="${pageContext.request.contextPath}/inventory?actionUser=add">
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="fname">Item Name</label>
                                <input type="text" class="form-control" id="fname" name="fname" required>
                            </div>
                            <div class="form-group">
                                <label for="expiration">Expirations Date</label>
                                <input type="date" class="form-control" id="expiration" name="expiration" required>
                            </div>
                            <div class="form-group">
                                <label for="price">Price</label>
                                <input type="number" step="0.01" class="form-control" id="price" name="price" required>
                            </div>
                            <div class="form-group">
                                <label for="inventory">Quantity</label>
                                <input type="number" class="form-control" id="inventory" name="inventory" required>
                            </div>
                            <div class="form-group">
                                <label for="discount">Discount</label>
                                <input type="number" step="0.01" class="form-control" id="discount" name="discount">
                            </div>
                            <div class="form-group">
                                <label for="ftid">Food Type ID</label>
                                <input type="number" class="form-control" id="ftid" name="ftid" required>
                            </div>
                            <div class="form-group">
                                <label for="is_donate">Is Donate</label>
                                <select class="form-control" id="is_donate" name="is_donate" required>
                                    <option value="0">No</option>
                                    <option value="1">Yes</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="store_id">Store ID</label>
                                <input type="number" class="form-control" id="store_id" name="store_id" required>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Add Item</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="<%= request.getContextPath()%>/js/inventory.js"></script>
    </body>
</html>
