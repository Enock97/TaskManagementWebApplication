$(function(){
    getAll();
});

function getAll() {
    $.get( "/getAllTasks", function( data ) {
        formatData(data);
    });
};

function formatData(tasks){
    var out = "<table class='table table-striped'>" +
        "<tr>" +
        "<th style='font-weight: bold;'>Task</th><th>Edit</th><th>Delete</th>" +
        "</tr>";
    for (let i in tasks) {
        out += "<tr><td>"+tasks[i].name+"<br>"+tasks[i].description+"</td>" +
               "<td><a href='edit-task.html?id="+tasks[i].id+"'><button class='btn btn-success'>Edit</button></a></td>" +
               "<td><button class='btn btn-danger' onclick='deleteTask("+tasks[i].id+")'>Delete</button></td></tr>";
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

