<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include.inc.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>药企后台系统</title>
    <link href="${mvcPath}/css/login.css" rel="stylesheet" type="text/css">
    <script src="<c:url value='/js/jquery-1.7.2.min.js'/>"></script>
    <script src="<c:url value='/js/jquery.tips.js'/>"></script>
    <script src="<c:url value='/js/jquery.ui.widget.js'/>"></script>
    <script src="<c:url value='/js/jquery.hydee.js'/>"></script>
</head>
<body>
<div class="header">
    <div class="header-left">
        <a href="#">
            <img src="${mvcPath}/image/logo.png">
        </a>
        <span>注册账号</span>
    </div>
    <div class="header-right">
        <p>客服热线： 15308478752</p>
    </div>
    <div class="clear"></div>
</div>
<div class="main">
    <div class="pic-left">
        <img src="${mvcPath}/image/3.jpg">
    </div>
    <div class="login">
        <div class="nc-login">
            <form id="register" class="red">
                <div class="login-name">
                    <p>注册账号</p>
                </div>
                <div class="login-main">
                    <p>
                        <input type="text" id="company" placeholder="公司名称" ctype="required" class="login-input" errtips="请输入公司名称">
                    </p>
                    <p>
                        <input type="text" id="email" placeholder="注册邮箱" ctype="email" class="login-input">
                    </p>
					<p>
						<input type="text" id="userPhone" placeholder="手机号码" ctype="mobile" class="login-input">
					</p>
                    <p>
                        <input type="text" id="userName" placeholder="用户名（英文或数字，至少五位）" ctype="username" class="login-input">
                    </p>
                    <p>
                        <input type="password" id="userPassword" placeholder="密码（6-12位）" ctype="password" class="login-input">
                    </p>
                    <p>
                        <input type="password" id="passwordSure" placeholder="确认密码" ctype="required" class="login-input">
                    </p>
                    <p>
                        <input type="checkbox" id="agree" ctype="required" errtips="您尚未同意海典软件用户注册协议!" class="login-checkbox" errtips="请同意本协议"><span>同意海典软件拟定的相关协议</span>
                    </p>
                    <input type="button" value="注&nbsp;&nbsp;册" class="login-button" onclick="register()">
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
	// 监听回车事件
	document.onkeyup = function(event){
		if(event.keyCode == 13){
			register();
		}
	}
	// 注册
	function register(){
		var _isEmpty = false,
			_params = $(".login-main").params();
		if(_params != null){
			// 校验两次密码是否一致
			if($("#passwordSure").val() != $("#userPassword").val()){
				$.hd.tips($("#passwordSure"), "两次密码输入不一致,请重新输入!");
				return false;
			}
			// 请求注册
			$(".login-main").loading(true);
			$.ajax({
				url 	 : "${mvcPath}/login/doReigist",
				type 	 : "post",
				dataType : "json",
				data 	 : _params,
				success  : function(data, textStatus, XMLHttpResponse) {
					$(".login-main").loading(false);
					if(data.result){
						if(window.confirm(data.data)){
							window.location.href = "${mvcPath}/";
						}
					}else{
						switch (data.status) {
						case 1001:
							// 用户名已被注册
							$.hd.tips($("#userName"), data.errors);
							break;
						case 1005:
							// 机构名已被注册
							$.hd.tips($("#company"), data.errors);
							break;
						default:
                            $.hd.alertMsg.error("服务器繁忙,稍后请重试!");
							break;
						}
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
				}
			});
		}
	}
</script>
</body>
</html>