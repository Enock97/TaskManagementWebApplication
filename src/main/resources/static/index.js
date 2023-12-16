$(document).ready(function() {
    // Attach a click event handler to the login button using jQuery
    $('#loginButton').click(function() {
        // Retrieve the values of the username and password fields
        const username = $('#username').val();
        const password = $('#password').val();

        // Perform your validation here (e.g., check username and password)
        if (username === 'admin' && password === 'admin') {
            // Redirect to the target HTML page if credentials are correct
            window.location.href = 'task-main-page/task-main-page.html';
        } else {
            // Display an error message or perform other actions for incorrect credentials
            $('#error').text('Incorrect username or password. Please try again.');
        }
    });
});