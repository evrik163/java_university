$(document).ready(function() {

    $('#register-btn').click(function() {
        var username = $('#username').val().trim();
        var password = $('#password').val().trim();

        if(username && password) {
            $.ajax({
                url: '/auth/register',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({ username: username, password: password }),
                success: function(response) {
                    $('#success-message').text('Регистрация прошла успешно').show();
                    $('#username').val('');
                    $('#password').val('');
                    $('#error-message').hide()
                },
                error: function(response) {
                    $('#error-message').text(response.responseText).show();
                    $('#success-message').hide();
                }
            });
        } else {
            $('#error-message').text('Все поля обязательны к заполнению').show();
            $('#success-message').hide();
        }
    });

    $('#login-btn').click(function() {
        var username = $('#username').val().trim();
        var password = $('#password').val().trim();

        if(username && password) {
            $.ajax({
                url: '/auth/login',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({ username: username, password: password }),
                success: function(response) {
                    window.location.href = '/posts';
                },
                error: function(response) {
                    $('#error-message').text('Неверные учетные данные').show();
                    $('#success-message').hide();
                }
            });
        } else {
            $('#error-message').text('Все поля обязательны к заполнению').show();
            $('#success-message').hide();
        }
    });
});