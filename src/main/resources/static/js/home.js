$(function () {
    $("#menu1").click(function () {
        $("#mainFrame").attr("src","/upload");
    });

    $("#menu2").click(function () {
        $("#mainFrame").attr("src","/upload/batch");
    });
});