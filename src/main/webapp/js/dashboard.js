
$(document).ready(function () {
    // Fetch and display statistics based on user role
    $.get('/api/statistics', function (data) {
        var userRole = $('#loggedInUserRole').val();
        if (userRole === 'retailer') {
            $('#totalFoods').text(data.totalFoods);
            $('#surplusFoods').text(data.surplusFoods);
            $('#donatedFoods').text(data.donatedFoods);
            $('#pendingClaims').text(data.pendingClaims);

            // Inventory Chart
            var inventoryCtx = document.getElementById('inventoryChart').getContext('2d');
            var inventoryChart = new Chart(inventoryCtx, {
                type: 'bar',
                data: {
                    labels: data.inventory.labels,
                    datasets: [{
                        label: 'Inventory',
                        data: data.inventory.data,
                        backgroundColor: 'rgba(54, 162, 235, 0.2)',
                        borderColor: 'rgba(54, 162, 235, 1)',
                        borderWidth: 1
                    }]
                }
            });
        } else if (userRole === 'consumer') {
            $('#totalPurchases').text(data.totalPurchases);
            $('#favoriteFoods').text(data.favoriteFoods);
            $('#ratingsGiven').text(data.ratingsGiven);

            // Purchase Trend Chart
            var purchaseTrendCtx = document.getElementById('purchaseTrendChart').getContext('2d');
            var purchaseTrendChart = new Chart(purchaseTrendCtx, {
                type: 'line',
                data: {
                    labels: data.purchaseTrend.labels,
                    datasets: [{
                        label: 'Purchases',
                        data: data.purchaseTrend.data,
                        borderColor: 'rgba(75, 192, 192, 1)',
                        borderWidth: 1
                    }]
                }
            });
        } else if (userRole === 'charity') {
            $('#totalClaims').text(data.totalClaims);
            $('#successfulClaims').text(data.successfulClaims);
            $('#charityPendingClaims').text(data.pendingClaims);

            // Claims Trend Chart
            var claimsTrendCtx = document.getElementById('claimsTrendChart').getContext('2d');
            var claimsTrendChart = new Chart(claimsTrendCtx, {
                type: 'line',
                data: {
                    labels: data.claimsTrend.labels,
                    datasets: [{
                        label: 'Claims',
                        data: data.claimsTrend.data,
                        borderColor: 'rgba(255, 99, 132, 1)',
                        borderWidth: 1
                    }]
                }
            });
        }
    });
});