<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include.inc.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>重设密码</title>
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
            <li class="progress-item">
                <span class="first-radius"></span>
                <span class="progress-txt">1. 填写资料</span>
            </li>
            <li class="progress-item">
                <span class="progress-next01"></span>
                <span class="progress-txt">2. 验证邮箱</span>
            </li>
            <li class="progress-item activ">
                <span class="progress-next01"></span>
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
                    <p>重设密码</p>
                </div>
                <div class="login-main">
	                <input id="username" type="text" value="${username}" style="display:none;" />
                    <p>
                        <input id="userPassword" type="password" placeholder="输入新密码" class="login-input">
                    </p>
                    <p>
                        <input id="passwordSure" type="password" placeholder="确认新密码" class="login-input">
                    </p>
                    <P></P>
                    <input id="resetPwd" type="button" value="提&nbsp;&nbsp;交" class="login-button">
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
	$("#resetPwd").bind("click",function(){
		// 校验密码长度
		if($("#userPassword").val().match(/^[\S]{6,12}$/) == null){
			Tips($("#userPassword"), "密码必须为6至12位!");
			return false;
		}
		// 校验两次密码是否一致
		if($("#passwordSure").val() != $("#userPassword").val()){
			Tips($("#passwordSure"), "两次密码输入不一致,请重新输入!");
			return false;
		}
		// 校验用户验证码并跳转至修改密码页面
		$(".login-main").loading(true);
		$.ajax({
			url 	 : "${mvcPath}/login/resetPassword/3rd",
			type 	 : "post",
			dataType : "json",
			data 	 : {userName:$("#username").val(),userPassword:$("#userPassword").val()},
			success  : function(data, textStatus, XMLHttpResponse) {
				$(".login-main").loading(false);
				if(data.result){
					if(window.confirm("充值密码成功,请重新登录!")){
						window.location.href = "${mvcPath}/login/goLogin";
					}
				}else{
					switch (data.status) {
					case 500:
                        $.hd.alertMsg.error("服务器繁忙,稍后请重试!");
						break;
					default:
						Tips($("#userPassword"), data.errors);
						break;
					}
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}
		});
	});
	// 气泡提示
	function Tips(node, msg){
		$('.jq_tips_box').remove();
		currTips = node.tips({
			side	:1,
            msg		:msg,
            bg 		:"#FF5080",
            time 	:5
		});
	}
</script>
</body>
</html>