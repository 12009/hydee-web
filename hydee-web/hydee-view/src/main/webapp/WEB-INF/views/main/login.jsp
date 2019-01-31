<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include.inc.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/login.css'/>" />
    <title>药企后台系统</title>
    <script src="<c:url value='/js/jquery-1.7.2.min.js'/>"></script>
    <script src="<c:url value='/js/jquery.tips.js'/>"></script>
    <script src="<c:url value='/js/jquery.ui.widget.js'/>"></script>
    <script src="<c:url value='/js/jquery.hydee.js'/>"></script>
</head>
<body>
<div class="header">
    <div class="header-left">
        <a href="#">
            <img src="<c:url value='/image/logo.png'/>">
        </a>
        <span>登录</span>
    </div>
    <div class="header-right">
        <p>客服热线： 15308478752</p>
    </div>
    <div class="clear"></div>
</div>
<div class="main">
    <div class="pic-left">
        <img src="<c:url value='/image/3.jpg'/>">
    </div>
    <div class="login">
        <div class="nc-login">
            <form class="red">
                <div class="login-name">
                    <p>药企后台系统</p>
                </div>
                <div class="login-main">
                    <p>
                        <input type="text" name="userName" id="loginName" placeholder="用户名" class="login-input">
                    </p>
                    <p>
                        <input type="password" name="userPassWord" id="userPassWord" placeholder="密码" class="login-input">
                    </p>
                    <%--<p>--%>
                        <%--<input type="checkbox" class="login-checkbox"><span>下次自动登录</span>--%>
                    <%--</p>--%>
                    <p></p>
                    <input type="button" onclick="login()" value="登&nbsp;&nbsp;录" class="login-button">
                    <p>
                        <a href="${mvcPath}/login/forgetPwd/1st/none">忘记密码？</a>
                        <a href="${mvcPath}/login/goRegist" class="span-right">注册账号</a>
                    </p>
                </div>
            </form>
        </div>
    </div>
    <div class="clear"></div>
</div>
</body>
<script type="text/javascript">
	//监听回车事件
	document.onkeyup = function(event){
		if(event.keyCode == 13){
			login();
		}
	}
	// 登录
    function login(){
        if(check()){
            var loginName=$("#loginName").val();
            var userPassWord=$("#userPassWord").val();
            $(".login-main").loading(true);
            $.ajax({
                type: "POST",
                url: '${mvcPath}/login/toLogin',
                data: {"userName": loginName,"userPassword":userPassWord},
                success: function (data) {
                    if("success" == data.result){
                        var _users = data.users;
                        var _zllnKey = data.zllnKey;
                        var params = {};
                        params.userId = _users.id;
                        params.userName = _users.userName;
                        params.ydjToken = _users.ydjToken;
                        params.customerId = _users.customerId;
                        params.customerName = _users.company;
                        params.zlln_key = _zllnKey;
                        $.ajax({
                            type: "POST",
                            url: '${ydjPath}/service/login',
                            data: params,
                            success: function (data) {
                                if(data.success){
                                    window.location.href="${mvcPath}/";
                                }else{
                                	$(".login-main").loading(false);
                                    $("#loginName").tips({
                                        side : 1,
                                        msg : data.msg,
                                        bg : '#FF5080',
                                        time : 15
                                    });
                                    $("#loginName").focus();
                                }
                            },
                            error: function(){
                            	window.location.href="${mvcPath}/";
                            }
                        });
                    }else if("erro" == data.result){
                    	$(".login-main").loading(false);
                        $("#loginName").tips({
                            side : 1,
                            msg : data.loginMsg,
                            bg : '#FF5080',
                            time : 15
                        });
                        $("#loginName").focus();
                    }
                }
            });
        }
    }

    /*
     * 判断文本框
     * */
    function check(){
        if ($("#loginName").val() == "") {

            $("#loginName").tips({
                side : 2,
                msg : '用户名不得为空',
                bg : '#AE81FF',
                time : 3
            });

            $("#loginName").focus();
            return false;
        } else {
            $("#loginName").val(jQuery.trim($('#loginName').val()));
        }

        if ($("#userPassWord").val() == "") {

            $("#userPassWord").tips({
                side : 2,
                msg : '密码不得为空',
                bg : '#AE81FF',
                time : 3
            });

            $("#userPassWord").focus();
            return false;
        }
        return true;
    }
</script>
</html>
