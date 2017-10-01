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
                content: data
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
    $("#profileBtn").click(function () {
        $.get("/user/profile",function (data) {
            layer.open({
                type: 1,
                title:"个人信息",
                content: data
            });
        });
    });

    $("#editPwdBtn").click(function () {
        $.get("/user/editPwd",function (data) {
            layer.open({
                type: 1,
                title:"修改密码",
                content: data
            });
        });
    });
    $("#findPwdBtn").click(function () {
        $.get("/user/findPwd",function (data) {
            layer.open({
                type: 1,
                title:"找回密码",
                content: data
            });
        });
    });
    $("#hideLeft").click(function () {
        $("#left").toggle();
    });
});