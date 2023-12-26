function createTask() {
    const task = {
        name: $("#Name").val(),
        description: $("#Description").val()
    };

    const url = "/postTask";
    $.ajax({
        url: url,
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

