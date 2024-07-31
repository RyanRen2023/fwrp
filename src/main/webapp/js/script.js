document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('logoutForm').addEventListener('submit', function (e) {
        e.preventDefault(); // Prevent the default form submission

        // Show confirmation dialog
        var userConfirmed = confirm('Are you sure you want to logout?');

        if (userConfirmed) {
            // If user clicks "OK", submit the form
            e.target.submit();
        } else {
            // If user clicks "Cancel", do nothing
            alert('Logout canceled');
        }
    });
});