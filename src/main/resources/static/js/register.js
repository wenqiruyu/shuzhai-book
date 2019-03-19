// 相应的输入框获取焦点显示提示
$(function () {
    // 进行判空处理 并进行提交
    $("#form_submit").click(function () {
        var flag = true;
        var name = $("#name").val();
        var pwd = $("#password").val();
        var number = $("#number").val();
        var verify = $("#verify").val();
        if (name == "" || name == null) {
            $("#name").css("border-color", "#FF3917");
            flag = false;
        }
        if (pwd == "" || pwd == null) {
            $("#password").css("border-color", "#FF3917");
            flag = false;
        }
        if (number == "" || number == null) {
            $("#number").css("border-color", "#FF3917");
            flag = false;
        }
        if (verify == "" || verify == null) {
            $("#verify").css("border-color", "#FF3917");
            flag = false;
        }
        if ($("#name").css('border-color') == "rgb(255, 57, 23)" || $("#password").css('border-color') == "rgb(255, 57, 23)" ||
            $("#number").css('border-color') == "rgb(255, 57, 23)" || $("#verify").css('border-color') == "rgb(255, 57, 23)") {
            flag = false;
        }
        if (flag == true) {
            $.ajax({
                url: "/userRegister",
                async: true,
                type: "POST",
                dataType: "json",
                data: {"name": name, "password": pwd, "number": number, "verify": verify},
                success: function (data) {
                    if (data.status == 0) {
                        $("#show_msg").html("· " + data.msg);
                    } else {
                        $("#show_msg").html("· " + data.msg);
                        window.location.href = "/user/login";
                    }
                    $("#show_msg").css("color", "red");
                },
            })
            return false;
        }
        return flag;
    })

    // 用户名验证
    $("#name").focus(function () {
        $("#remind_name").css("display", "inline");
    })
    // 获取用户名输入的字符是否符合，符合隐藏提示
    $("#name").keyup(function () {
        var name = $("#name").val();
        // 汉字字母数字下划线减号3-15位
        var reg = /^[\u4E00-\u9FA5a-zA-Z0-9_-]{3,15}$/;
        if (reg.test(name) == true) {
            // 验证用户名是否可用
            // 合适让输入框变绿 并包括用户名可用未重复
            $("#remind_name").css("display", "none");
            verifyName(name);
        } else {
            // 不合适让输入框变红
            $("#name").css("border-color", "#FF3917");
            $("#remind_name").css("display", "inline");
        }
    })

    function verifyName(name) {
        $.ajax({
            url: "/verifyName",
            async: true,
            type: "get",
            dataType: "json",
            data: {username: name},
            success: function (data) {
                if (data.status == 0) {
                    $("#name").css("border-color", "#FF3917");
                    $("#verify_name").css("display", "inline");
                    return;
                }
                $("#name").css("border-color", "gray");
                $("#verify_name").css("display", "none");
            }
        })
    }

    // 密码验证
    $("#password").focus(function () {
        $("#remind_pwd").css("display", "inline");
    })
    $("#password").keyup(function () {
        var pwd = $("#password").val();
        var reg = /^[a-zA-Z0-9]{6,15}$/;
        if (reg.test(pwd) == true) {
            // 合适让输入框变绿
            $("#password").css("border-color", "gray");
            $("#remind_pwd").css("display", "none");
        } else {
            // 不合适让输入框变红
            $("#password").css("border-color", "#FF3917");
            $("#remind_pwd").css("display", "inline");
        }
    })

    // 手机号验证
    $("#number").focus(function () {
        $("#remind_num").css("display", "inline");
    })
    $("#number").keyup(function () {
        var number = $("#number").val();
        var reg = /^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\d{8}$/;
        if (reg.test(number) == true) {
            // 合适让输入框变绿
            $("#number").css("border-color", "gray");
            $("#remind_num").css("display", "none");
            // 手机号合适，使获取验证码按钮可用状态
            $("#form_verify_button").attr("disabled", false);
        } else {
            // 不合适让输入框变红
            $("#number").css("border-color", "#FF3917");
            $("#remind_num").css("display", "inline");
            // 手机号不合适，使获取验证码按钮不可用状态
            $("#form_verify_button").attr("disabled", true);
        }
    })

    // 获取验证码，使用ajax验证是否发送成功  不考虑使用ajax嵌套，执行过程中会锁住页面
    $("#form_verify_button").click(function () {
        var num = $("#number").val();
        $.ajax({
            url: "/sendSms",
            async: true,
            type: "GET",
            dataType: "json",
            data: {number: num},
            success: function (data) {
                alert(data)
                alert(data.status)
                // 验证手机号是否注册过
                if (data.status == 8) {
                    // 手机号已经注册过
                    $("#show_msg").html("· " + data.msg + ",前往" + "<a href='/user/login'>登录</a>");
                    return;
                } else {
                    // 发送验证短信
                    if (data.status == 1) {
                        // 短信发送成功
                        $("#show_msg").html("");
                        countdownHandler();
                        $("#remind_verify").css("display", "inline");
                        return;
                    } else {
                        $("#show_msg").html("· " + data.msg);
                        $("#show_msg").css("color", "red");
                        return;
                    }
                }

            }
        })
    })
    $("#verify").keyup(function () {
        $("#verify").css("border-color", "gray");
    })

    //短信验证码倒计时
    var countdownHandler = function () {
        var button = $("#form_verify_button");
        var number = 60;
        var countdown = function () {
            if (number == 0) {
                button.attr("disabled", false);
                button.html("发送验证码");
                number = 60;
                return;
            } else {
                button.attr("disabled", true);
                button.html(number + "秒重新发送");
                number--;
            }
            setTimeout(countdown, 1000);
        }
        setTimeout(countdown, 1000);
    }
})