layui.use('form', function(){
    var form = layui.form;

    //监听提交
    form.on('submit(formProfile)', function(data){
        var userName=data.field.username;
        var mobile=data.field.mobile;
        var email=data.field.email;
        var nickname=data.field.nickname;

        layer.prompt({
            formType: 1,
            value: '',
            title: '请输入密码'
        }, function(value, index, elem){
            var password=value;
            $.post("/user/updateProfile",{userName:userName,password:password,mobile:mobile,email:email,nickname:nickname},function (result) {
                if(0!=result.code){
                    layer.msg(result.msg);
                    return false;
                }
                layer.msg(result.msg);
            });
            layer.close(index);
        });

        return false;
    });
});