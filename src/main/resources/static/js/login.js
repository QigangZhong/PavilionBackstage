layui.use('form', function(){
    var form = layui.form;

    //监听提交
    form.on('submit(formDemo)', function(data){
        var userName=data.field.username;
        var password=data.field.password;
        $.post("/user/login",{userName:userName,password:password},function (result) {
            if(0!=result.code){
                layer.msg(result.msg);
                return false;
            }
            window.location.reload();
        });
        return false;
    });
});