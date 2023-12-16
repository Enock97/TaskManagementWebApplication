$(function(){
    getAll();
});

/*
function createTask() {
    const task = {
        Name : $("#Name").val(),
        Description : $("#Description").val()
    };
    const url = "/save";
    $.post(url, task, function(result){
        getAll();
    });
    $("#Name").val("");
    $("#Description").val("");
};
*/

function getAll() {
    $.get( "/getAll", function( data ) {
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
               "<td><a href='../edit-task/edit-task.html'><button class='btn btn-success'>Edit</button></a></td>" +
               "<td><button class='btn btn-danger' onclick='deleteTask("+tasks[i].id+")'>Delete</button></td></tr>";
    }
    $("#tasks").html(out);
}

function deleteTask(taskId) {
    $.get("/delete/" + taskId, function(data) {
        getAll(); // Refresh the task list after deleting
    });
}