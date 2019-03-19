$(function(){
    $("#submit").click(function(){
        var flag = true;
        var name = $("#name").val();
        var password = $("#password").val();
        if(name == "" || name == null){
            $("#name").css("border-color","#FF3917");
            flag = false;
        }
        if(password == "" || password == null){
            $("#password").css("border-color","#FF3917");
            flag = false;
        }
        if(flag){
            // 将用户名密码提交
            $.ajax({
                url:"/userLogin",
                async:true,
                type:"POST",
                dataType:"json",
                data:{"name":name,"password":password},
                success:function (data) {
                    if(data.status == 1){
                        // 登录成功,跳转首页
                        window.location.href = "/index";
                    }else{
                        // 登录失败，进行提示
                        $(".form_msg").html("· " + data.msg);
                    }
                }
            })
            return false;
        }
        return flag;
    })

    $("#name").blur(function(){
        var name = $("#name").val();
        if(name != ""){
            $("#name").css("border-color","gray");
        }
    })
    $("#password").blur(function(){
        var password = $("#password").val();
        if(password != ""){
            $("#password").css("border-color","gray");
        }
    })
})