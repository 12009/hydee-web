<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include.inc.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>忘记密码</title>
    <link href="${mvcPath}/css/login.css" rel="stylesheet" type="text/css">
    <script src="<c:url value='/js/jquery-1.7.2.min.js'/>"></script>
    <script src="<c:url value='/js/jquery.tips.js'/>"></script>
    <script src="<c:url value='/js/jquery.ui.widget.js'/>"></script>
    <script src="<c:url value='/js/jquery.hydee.js'/>"></script>
</head>
<body>
<div class="header">
    <div class="header-left">
        <a href="${mvcPath}/">
            <img src="${mvcPath}/image/logo.png">
        </a>
        <span>找回密码</span>
    </div>
    <div class="header-right">
        <p>客服热线： 15308478752</p>
    </div>
    <div class="clear"></div>
</div>
<div class="main" style="padding-top: 30px;">
    <div class="passwod-main">
        <ul>
            <li class="progress-item activ">
                <span class="first-radius"></span>
                <span class="progress-txt">1. 填写资料</span>
                <span class="progress-next"></span>
            </li>
            <li class="progress-item">
                <span class="progress-txt">2. 验证邮箱</span>
                <span class="progress-next"></span>
            </li>
            <li class="progress-item">
                <span class="progress-txt">3. 设置新密码</span>
                <span class="last-radius"></span>
            </li>
        </ul>
        <div class="clear"></div>
    </div>
    <div class="passwod">
        <div class="nc-login">
            <form class="red">
                <div class="login-name">
                    <p>忘记密码？</p>
                </div>
                <div class="login-main">
                    <p>
                        <input id="username" type="text" placeholder="用户名" class="login-input">
                    </p>
                    <p>
                        <input id="email" type="text" placeholder="注册邮箱" class="login-input">
                    </p>
                    <P></P>
                    <input id="nextStep" type="button" value="下一步" class="login-button">
                    <p>
                        <a href="${mvcPath}/login/goLogin" class="span-right">返回登录</a>
                    </p>
                </div>
            </form>
        </div>
    </div>
    <div class="clear"></div>
</div>
<script type="text/javascript">
	$("#nextStep").bind("click",function(){
		// 校验用户名
		if($("#username").val().length == 0){
			Tips($("#username"), "请输入需要找回密码的用户名!");
			return false;
		}
		// 校验邮箱
		if($("#email").val().match( /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/) == null){
			Tips($("#email"), "请输入正确的邮箱地址!");
			return false;
		}
		// 校验用户名和密码是否合法,并且发送邮件
		$(".login-main").loading(true);
		$.ajax({
			url 	 : "${mvcPath}/login/resetPassword/1st",
			type 	 : "post",
			dataType : "json",
			data 	 : {userName:$("#username").val(),email:$("#email").val()},
			success  : function(data, textStatus, XMLHttpResponse) {
				$(".login-main").loading(false);
				if(data.result){
					window.location.href = "${mvcPath}/login/forgetPwd/2nd/"+data.data;
				}else{
					switch (data.status) {
					case 500:
                        $.hd.alertMsg.error("服务器繁忙,稍后请重试!");
						break;
					default:
						Tips($("#email"), data.errors || data.data);
						break;
					}
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}
		});
	});
	
	// 气泡提示
	function Tips(node, message){
		$('.jq_tips_box').remove();
		currTips = node.tips({
			side	:1,
            msg		:message,
            bg 		:"#FF5080",
            time 	:5
		});
	}
</script>
</body>
</html>