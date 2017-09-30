$(function () {
    $("#menu1").click(function () {
        $("#mainFrame").attr("src","/upload");
    });

    $("#menu2").click(function () {
        $("#mainFrame").attr("src","/upload/batch");
    });
    $("#loginbtn").click(function () {
        $.get("/user/login",function (data) {
            layer.open({
                type: 1,
                title:"请登录",
                content: data //注意，如果str是object，那么需要字符拼接。
            });
        });
    });
    $("#logoutbtn").click(function () {
        $.get("/user/logout",function (data) {
            if(data=="ok"){
                window.location.reload();
            }else{
                layer.error("退出失败");
            }
        });
    });
});