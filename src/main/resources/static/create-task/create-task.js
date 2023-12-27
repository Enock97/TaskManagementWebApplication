function createTask() {
    const name = $("#Name").val();
    const description = $("#Description").val();

    // Regex for name and description validation
    const nameRegex = /[0-9a-zA-ZøæåØÆÅ:_@#\/%&,(). -]{2,30}/;
    const descriptionRegex = /[0-9a-zA-ZøæåØÆÅ:_@#!\/%&,(). -]{10,1000}/;

    let hasError = false;

    // Clear previous errors
    $("#feil1").html('');
    $("#feil2").html('');
    $("#feil3").html('');

    // Check if name or description is empty
    if (!name || !description) {
        $("#feil3").html("Error in input - missing name or description");
        hasError = true;
    }

    // Check if name doesn't match the regex
    if (!nameRegex.test(name)) {
        $("#feil1").html("Error: Invalid name format");
        hasError = true;
    }

    // Check if description doesn't match the regex
    if (!descriptionRegex.test(description)) {
        $("#feil2").html("Error: Invalid description format");
        hasError = true;
    }

    // If there is any error, stop the function execution
    if (hasError) {
        return;
    }

    // Prepare task data for POST request
    const task = {
        name: name,
        description: description
        // Add other task properties if necessary
    };

    // If all checks pass, proceed with AJAX POST
    $.ajax({
        url: "/postTask",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(task),
        success: function (response) {
            window.location.href = '../task-main-page/task-main-page.html';
        },
        error: function (xhr, status, error) {
            $("#feil").html("Error: " + error);
        }
    });
}


