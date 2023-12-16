$(function(){
    getAll();
});

function regTask() {
    const task = {
        name : $("#name").val(),
        description : $("#description").val()
    };
    const url = "/save";
    $.post(url, task, function(result){
        getAll();
    });
    $("#name").val("");
    $("#description").val("");
};

function getAll() {
    $.get( "/getAll", function( data ) {
        formatData(data);
    });
};

function formatData(tasks){
    var ut = "<table class='table table-striped'>" +
        "<tr>" +
        "<th>Navn</th><th>Adresse</th>" +
        "</tr>";
    for(let i in kunder ){
        ut+="<tr><td>"+kunder[i].navn+"</td><td>"+kunder[i].adresse+"</td></tr>"
    }
    $("#kundene").html(ut);
}

function deleteTasks() {
    $.get( "/deleteAll", function( data ) {
        getAll();
    });
};