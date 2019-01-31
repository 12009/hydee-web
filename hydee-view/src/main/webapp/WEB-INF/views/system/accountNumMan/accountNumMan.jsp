<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include.inc.jsp"%>
<div class="right-main">
	<div class="table-height30 marginTop20">
		<c:if test="${sessionUser.isAdmin}">
			<a href="javascript:;" class="width80 fakeBtn-hover-crimson floatLeft addaccNum">新建账号</a>
		</c:if>
        <div class="floatRight">
            <label class="positionRelative width250">
                <input type="text" class="labelInput width250 verticalMJ" maxlength="14" autocomplete="off">
                <input type="button" class="btn-hover-blue searchBtn" value="搜索">
            </label>
        </div>
        <div class="clear"></div>
    </div>
    <ul class="ul-table-tHeader marginTop20 paddingLR20">
        <li class="width100">账号</li>
        <li class="width100">角色</li>
        <li class="width80">使用人</li>
        <li class="width200">创建时间</li>
        <li class="width200">最后登录时间</li>
        <li class="width80">状态</li>
        <li class="width140 textAlignC">操作</li>
        <div class="clear"></div>
    </ul>
    <c:forEach items="${reList}" var="item">
    <ul class="ul-table-tBody tableWithoutLine80 paddingLR20">
        <li class="width100">${item.userName}</li>
        <li class="width100">${item.roleName}</li>
        <li class="width80">${item.owner}</li>
        <li class="width200">${item.createTimeFmt}</li>
        <li class="width200">${item.lastLoginTimeFmt == null ? '尚未登录' : item.lastLoginTimeFmt}</li>
        <li class="width90">
			<c:choose>
				<c:when test="${item.status==1}">已启用</c:when>
				<c:when test="${item.status==2}">已锁定</c:when>
				<c:when test="${item.status==3}">待审核</c:when>
				<c:when test="${item.status==4}">审核不通过</c:when>
			</c:choose>
		</li>
        <li class="width140 textAlignC" uid="${item.id}">
            <a href="javascript:;" class="operation disInlineBlock btn_update">修改</a>
			<c:choose>
				<c:when test="${item.status==1}">
					<a class="operation disInlineBlock btn_update_status" status="2">禁用</a>
					<a class="operation disInlineBlock btn_resetPwd">重置密码</a> 
				</c:when>
				<c:when test="${item.status==2}">
					<a class="operation disInlineBlock btn_update_status" status="1">启用</a>
				</c:when>
			</c:choose>
			<%--<a class="operation disInlineBlock btn_resetPwd">重置密码</a>--%>
        </li>
        <div class="clear"></div>
    </ul>
    </c:forEach>
    <div class="tcdPageCode" count="${page.totalResult}" page="${page.currentPage}" form="/view/roleMan"></div>
</div>

<div class="bgShow"></div>
<div class="floatLayer width400 addaccNumSel">
    <div class="header">新建账号</div>
    <div id="userParms" class="main">
    	<input id="id" type="hidden">
		<input id="customerId" type="hidden" value="${sessionUser.customerId}"/>
        <ul class="ul-info table-height30">
            <li class="widthAuto textAlignR marginRight10">登陆账号&nbsp;&nbsp;&nbsp;&nbsp;${sessionUser.userName}_</li>
            <li>
                <label class="input">
                    <input id="userName" type="text" class="labelInput width120" prefix="${sessionUser.userName}_" ctype="username">
                </label>
            </li>
            <div class="clear"></div>
        </ul>
        <ul class="ul-info table-height30" id="pwd">
            <li class="width60 textAlignR marginRight10" ctype="password">密码</li>
            <li>
                <label class="input">
                    <input id="userPassword" type="password" class="labelInput width200">
                </label>
            </li>
            <div class="clear"></div>
        </ul>
        <ul class="ul-info table-height30">
            <li class="width60 textAlignR marginRight10" ctype="required">使用人</li>
            <li>
                <label class="input">
                    <input id="owner" type="text" class="labelInput width200">
                </label>
            </li>
            <div class="clear"></div>
        </ul>
        <ul class="ul-info table-height30">
            <li  class="width60 textAlignR marginRight10" ctype="required">手机号码</li>
            <li>
                <label class="input">
                    <input id="userPhone" type="text" class="labelInput width200" ctype="mobile">
                </label>
            </li>
            <div class="clear"></div>
        </ul>
        <ul class="ul-info table-height30">
            <li class="width60 textAlignR marginRight10">所属角色</li>
            <li>
                <label class="input">
                    <select id="roleId" class="width200 xiala" ctype="required" errtips="您尚未创建任何角色">
						<c:forEach items="${roleList}" var="item" varStatus="s">
							<option value="${item.roleId}">${item.roleName}</option>
						</c:forEach>
					</select>
                </label>
            </li>
            <div class="clear"></div>
        </ul>
    </div>
    <div class="footer">
        <input type="button" value="确定" class="floatLayerSure btn-hover-crimson width80 marginRight10">
        <input type="button" value="取消" class="floatLayerCancel btn-hover-crimson width80">
    </div>
</div>

<div class="shadowWhite displayNone increaseBonus">
	<div class="contentWrapper">
	<div class="content">
	<div class="detailTitle">修改密码</div>
	<div class="div-line" style="margin-bottom:50px;"></div>
		<input id="updatePwdId" type="text" style="display: none;">
		<ul class="ul-info table-height30">
		<li class="width80">新密码</li>
		<li class="width180"><input id="userPasswordEx" type="password" class="width250" ctype="password"></li>
		<div class="clear"></div>
		</ul>
		<ul class="ul-info table-height30">
		<li class="width80">密码确认</li>
		<li class="width180"><input id="passwordRepaet" type="password" class="width250" ctype="required"></li>
		<div class="clear"></div>
		</ul>
	</div>
	<div class="btn-wraper width360">
	<input type="button" value="确定" class="btn-hover-crimson marginRight10 btnForSure">
	<input type="button" value="取消" class="btn-line-crimson btnCancel">
	</div>
	</div>
</div>

<script type="text/javascript">
	$(".addaccNum").bind("click",function(){
		$("#userParms input[type=text]").val("");
		$("#userParms input[type=password]").val("");
		$("#pwd").show();
		$("#userPassword").attr("ctype","password");
		$(".addaccNumSel .header:eq(0)").html("新建账号");
		$(".addaccNumSel,.bgShow").fadeIn();
	});
	
	$(".floatLayerCancel,.btnCancel").bind("click",function(){
		$(".addaccNumSel,.increaseBonus,.bgShow").fadeOut();
	});
	
	$(".floatLayerSure").click(function(){
    	var params = $("#userParms").params();
    	if(params == null) return false;
    	$.ajax({
			url 	 : "${mvcPath}/view/accountNumMan/saveOrUpdate",
			type 	 : "post",
			dataType : "json",
			data 	 : params,
			success  : function(data, textStatus, XMLHttpResponse) {
				if(data.result){
					$(".addAccountSel,.bgShow").fadeOut();
//			    	if(window.confirm($("#id").val().length ==0 ? "账号新增成功!" : "账号修改成功!")){
						refreshPage();
//					};
				}else{
					switch (data.status) {
					case 1001:
						// 用户名被占用
						$.hd.tips($("#userName"), data.errors);
						break;
					default:
						$.hd.alertMsg.error(data.errors || "服务器繁忙,稍后请重试!");
						break;
					}
				}
			}
		});
    });
	
	$(".btn_update").click(function(){
    	var _this = $(this),
    		_uid = _this.parent().attr("uid");
    	$.ajax({
			url 	 : "${mvcPath}/view/accountNumMan/detail",
			type 	 : "post",
			dataType : "json",
			data 	 : {userId :_uid},
			success  : function(data, textStatus, XMLHttpResponse) {
				if(data.result){
					var _user = data.data;
					for(var key in _user){  
						if(key == 'userName'){
							var _val = _user[key],
								_prefix = $("#"+key).attr("prefix") || "";
							$("#"+key).val(_val.substr(_val.indexOf(_prefix)+_prefix.length));
						}else{
							$("#"+key).val(_user[key]);
						}
		            } 
					$("#userPassword").val("");
					$("#pwd").hide();
					$("#userPassword").removeAttr("ctype");
					$(".addaccNumSel .header:eq(0)").html("修改角色");
			    	$(".addaccNumSel,.bgShow").fadeIn();
				}else{
					$.hd.alertMsg.error(data.errors || "服务器繁忙,稍后请重试!");
				}
			}
		});
    });
	
	$(".btn_update_status").click(function(){
    	var _this = $(this),
    		_uid = _this.parent().attr("uid"),
    		_status = _this.attr("status");
    	$.ajax({
			url 	 : "${mvcPath}/view/accountNumMan/updateStatus",
			type 	 : "post",
			dataType : "json",
			data 	 : {userId :_uid,status :_status},
			success  : function(data, textStatus, XMLHttpResponse) {
				if(data.result){
//			    	if(window.confirm("操作成功")){
						refreshPage();
//					};
				}else{
					$.hd.alertMsg.error(data.errors || "服务器繁忙,稍后请重试!");
				}
			}
		});
    });
	
	// 重置密码
    $(".btn_resetPwd").click(function(){
    	$("#updatePwdId").val($(this).parent().attr("uid"));
    	$(".increaseBonus,.bgShow").fadeIn();
    });
 	// 重置密码-服务企请求
 	$(".increaseBonus .btnForSure").click(function(){
    	var _this = $(this),
    		_uid = $("#updatePwdId").val(),
    		_password = $("#userPasswordEx").val(),
    		_passwordRepeat = $("#passwordRepaet").val(),
    		_params = $(".increaseBonus").params();
    	if( _params != null ){
	    	// 判断密码是否一致
	    	console.log(_password + " : " + _passwordRepeat);
	    	if(_password != _passwordRepeat){
	    		$("#passwordRepaet").val("");
	    		$.hd.tips($("#userPasswordEx"), "两次密码输入不一致,请重新输入!");
	    		return false;
	    	}
	    	// 请求服务
	    	$.ajax({
				url 	 : "${mvcPath}/view/accountNumMan/resetPassword",
				type 	 : "post",
				dataType : "json",
				data 	 : {userId :_uid,userPassword :_password},
				success  : function(data, textStatus, XMLHttpResponse) {
					if(data.result){
				    	$(".increaseBonus,.bgShow").fadeOut();
				    	$.hd.alertMsg.info("重置成功");
					}else{
						$.hd.alertMsg.error(data.errors || "服务器繁忙,稍后请重试!");
					}
				}
			});
    	}
 	});
</script>