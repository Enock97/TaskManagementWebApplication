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
    const task = {
        name: $("#Name").val(),
        description: $("#Description").val()
    };

    $.ajax({
        url: "/updateTask/" + taskId,
        type: "PUT",
        contentType: "application/json",
        data: JSON.stringify(task),
        success: function (response) {
            window.location.href = 'task-main-page.html';
        },
        error: function (xhr, status, error) {
            $("#feil").html("Error: " + error);
        }
    });
}



