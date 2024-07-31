$(document).ready(function () {
    $('#role').on('change', function () {
        var role = $(this).val();
        $('#retailerFields').hide();
        $('#charitableOrgFields').hide();
        if (role === 'Retailer') {
            $('#retailerFields').show();
        } else if (role === 'CharitableOrganization') {
            $('#charitableOrgFields').show();
        }
    });

    $('#registerForm').on('submit', function (e) {
        e.preventDefault();
        const username = $('#username').val();
        const email = $('#email').val();
        const password = $('#password').val();
        const confirmPassword = $('#confirmPassword').val();
        const role = $('#role').val();


        if (password !== confirmPassword) {
            $('#registerMessage').html('<div class="alert alert-danger">Passwords do not match.</div>');
            return;
        }

        const data = {
            username: username,
            email: email,
            password: password,
            role: role,
            confirmPassword: confirmPassword
        };

        $.ajax({
            url: contextPath + '/sign-up',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (response) {
                if (response.status === "success") {
                    $('#registerMessage').html('<div class="alert alert-success">Registration successful!</div>');
                    setTimeout(function () {
                        window.location.href = contextPath + '/jsp/login.jsp';
                    }, 2000);
                } else {
                    $('#registerMessage').html('<div class="alert alert-danger">' + response.message + '</div>');
                }
            },
            error: function (xhr, status, error) {
                $('#registerMessage').html('<div class="alert alert-danger">Registration failed: ' + xhr.responseText + '</div>');
            }
        });
    });
});