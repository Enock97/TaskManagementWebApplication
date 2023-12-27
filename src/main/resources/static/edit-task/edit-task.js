function getQueryParam(param) {
    var search = window.location.search.substring(1);
    var params = new URLSearchParams(search);
    return params.get(param);
}

$(document).ready(function() {
    var taskId = getQueryParam('id');
    if (taskId) {
        loadTaskData(taskId);
    }
});

function loadTaskData(taskId) {
    // AJAX call to get the task data
    $.get("/getSingleTask/" + taskId, function(task) {
        $('#Name').val(task.name);
        $('#Description').val(task.description);
        // Store the task ID in a hidden field or use a global variable
        $('#taskId').val(taskId);
    }).fail(function() {
        alert('Error: Could not load task');
    });
}

function updateTask() {
    const taskId = $('#taskId').val();
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

    // If all checks pass, proceed with AJAX
    $.ajax({
        url: "/updateTask/" + taskId,
        type: "PUT",
        contentType: "application/json",
        data: JSON.stringify({ name, description }),
        success: function (response) {
            window.location.href = '../task-main-page/task-main-page.html';
        },
        error: function (xhr, status, error) {
            $("#feil").html("Error: " + error);
        }
    });
}




