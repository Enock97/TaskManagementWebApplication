$(function(){
    getAll();
});

function getAll() {
    $.get( "/getAllTasks", function( data ) {
        formatData(data);
    });
};

function formatData(tasks){
    var out = "<table class='table table-bordered table-hover table-light'>" +
        "<tr>" +
        "<th style='font-weight: bold;'>Task</th><th>Edit Tasks</th><th>Complete Tasks</th>" +
        "</tr>";
    for (let i in tasks) {
        out += "<tr><td>"+tasks[i].name+"<br>"+tasks[i].description+"</td>" +
               "<td><a href='../edit-task/edit-task.html?id="+tasks[i].id+"'><button class='btn btn-secondary'>Edit</button></a></td>" +
               "<td><button class='btn btn-success' onclick='deleteTask("+tasks[i].id+")'>Complete</button></td></tr>";
    }
    $("#tasks").html(out);
}

function deleteTask(taskId) {
    $.ajax({
        url: "/deleteTask/" + taskId,
        type: "DELETE",
        success: function (response) {
            getAll();
        },
        error: function (xhr, status, error) {
            console.error("Error deleting task: " + error);
        }
    });
}

