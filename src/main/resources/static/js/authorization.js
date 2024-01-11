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
                    alert('Регистрация прошла успешно');
                },
                error: function(response) {
                    $('#error-message').text(response.responseText).show();
                }
            });
        } else {
            $('#error-message').text('Все поля обязательны к заполнению').show();
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
                }
            });
        } else {
            $('#error-message').text('Все поля обязательны к заполнению').show();
        }
    });
});