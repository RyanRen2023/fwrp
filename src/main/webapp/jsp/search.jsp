<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Advanced Search - Food Waste Reduction Platform</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%= request.getContextPath()%>/css/style.css" rel="stylesheet">
    </head>

    <body>
        <c:import url="jspf/topbar.jsp" />
        <div class="main-content">

            <c:import url="jspf/sidebar.jsp" />

            <div class="content container p-4">
                <h1>Search</h1>
                <form id="searchForm" action="search" method="post" class="mb-4">
                    <input type="hidden" name="action" value="search">
                    <div class="form-group">
                        <label for="searchQuery">Search Query</label>
                        <input type="text" class="form-control" id="searchQuery" name="searchQuery" required>
                    </div>
                    <div class="filters mb-4">
                        <div class="filter-group">
                            <label for="food-type">Food Type</label>
                            <select id="food-type" class="form-control" name="foodType">
                                <option value="">Select Type</option>
                                <option value="vegetables">Vegetables</option>
                                <option value="fruits">Fruits</option>
                                <option value="meat">Meat</option>
                                <option value="dairy">Dairy</option>
                            </select>
                        </div>
                        <div class="filter-group">
                            <label for="price-range">Price Range</label>
                            <select id="price-range" class="form-control" name="priceRange">
                                <option value="">Select Range</option>
                                <option value="5">Below $5</option>
                                <option value="5-10">$5 - $10</option>
                                <option value="10">Above $10</option>
                            </select>
                        </div>
                        <div class="filter-group">
                            <label for="expiration">Expiration</label>
                            <select id="expiration" class="form-control" name="expiration">
                                <option value="">Select Expiration</option>
                                <option value="1">1 day</option>
                                <option value="3">3 days</option>
                                <option value="7">1 week</option>
                            </select>
                        </div>
                        <div class="filter-group">
                            <label for="supplier">Supplier</label>
                            <input type="text" id="supplier" class="form-control" name="supplier" placeholder="Enter supplier name">
                        </div>
                        <div class="filter-group">
                            <label for="location">Location</label>
                            <input type="text" id="location" class="form-control" name="location" placeholder="Enter location">
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary">Search</button>
                </form>
                <h2 class="mt-5">Search Results</h2>
                <div class="results" id="searchResults">
                    <c:if test="${empty searchPage}">
                        <c:choose>
                            <c:when test="${not empty searchResults}">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>Food Name</th>
                                            <th>Expiration Date</th>
                                            <th>Price</th>
                                            <th>Inventory</th>
                                            <th>Discount</th>
                                            <th>Donate</th>
                                            <th>Food Type</th>
                                            <th>Store Name</th>
                                            <th>City</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="result" items="${searchResults}">
                                            <tr>
                                                <td>${result.fname}</td>
                                                <td>${result.expiration}</td>
                                                <td>${result.price}</td>
                                                <td>${result.inventory}</td>
                                                <td>${result.discount}</td>
                                                <td>${result.isDonate}</td>
                                                <td>${result.foodType}</td>
                                                <td>${result.storeName}</td>
                                                <td>${result.city}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>
                            <c:otherwise>
                                <div class="alert alert-warning" role="alert">
                                    No result
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <!--    <script src="<%= request.getContextPath()%>/js/search.js"></script>-->
    </body>

</html>