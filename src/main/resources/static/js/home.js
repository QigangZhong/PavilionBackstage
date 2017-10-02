var element;
var layer;

$(function () {
    layui.use('element', function () {
        element = layui.element;

    });
    layui.use('layer', function () {
        layer=layui.layer;
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
});

function createTab(iframeId, srcUrl, title) {
    if($("#"+iframeId).length>0){
        return false;
    }

    var tabId=iframeId+"Tab";

    //新增一个Tab项
    element.tabAdd('tabHead', {
        title: title
        ,content: '<iframe id="'+iframeId+'" src="'+srcUrl+'"></iframe>'
        ,id: tabId
    });

    setIframeHeight(iframeId);

    element.init();
    //切换到指定Tab项
    element.tabChange('tabHead', tabId);
}

function setIframeHeight(id){
    var iframe = document.getElementById(id);
    if (iframe.attachEvent) {
        iframe.attachEvent("onload", function() {
            $("#"+id).height(iframe.contentWindow.document.documentElement.scrollHeight);
        });
    } else {
        iframe.onload = function() {
            $("#"+id).height(iframe.contentDocument.body.scrollHeight);
        };
    }
}
