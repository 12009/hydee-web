<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include.inc.jsp"%>
<div class="right-top">
    <div class="floatLeft right-top-left">您好，<span class="font-color-crimson">${sessionUser.userName}</span>，欢迎使用药店加厂商活动管理系统！（客服1：15308478752  客服2：18975801415 用户QQ群：342562936）</div>
    <a class="exitIcon floatRight right-top-right" href="<c:url value='/login/logout'/>"><span></span>退出</a>
    <a class="floatRight ModifiedPwd" style="padding-right: 10px"><span></span>修改密码</a>
    <div class="clear"></div>
</div>
<div class="bgShow"></div>
<div class="clear"></div>
<div class="shadowWhite displayNone increaseBonusPwd"><!--修改密码-->
    <div class="contentWrapper">
        <div class="content"  style="margin-left: 20px;margin-top: 10px">
            <div class="detailTitle">修改密码</div>
            <div class="div-line"></div>
            <ul class="ul-info table-height30 marginTop20">
                <li class="width80">当前密码</li>
                <li class="width250"><input type="password" class="width250 userOldPasswordEx" ctype="required" placeholder="请输入当前密码"></li>
                <div class="clear"></div>
            </ul>
            <ul class="ul-info table-height30">
                <li class="width80">新密码</li>
                <li class="width250"><input type="password" class="width250 userPasswordEx" ctype="password" placeholder="请输入新密码"></li>
                <div class="clear"></div>
            </ul>
            <P style="color:#0077dd; font-size:10px;">提示：密码长度6-20位,请输入数字、字母或标点符号的组合，且不能与旧密码相同</P>
            <ul class="ul-info table-height30 marginTop5">
                <li class="width80">确认密码</li>
                <li class="width250"><input type="password" class="width250 passwordRepaet" ctype="required" placeholder="请输入确认密码"></li>
                <div class="clear"></div>
            </ul>
        </div>
        <div class="btn-wraper width360">
            <input type="button" value="确定" class="btn-hover-crimson marginRight10 btnForSurePwd">
            <input type="button" value="取消" class="btn-line-crimson btnCancelPwd">
        </div>
    </div>
</div>

<script type="text/javascript">

    $(".btnCancelPwd").bind("click",function(){
        $(".userOldPasswordEx").val('');
        $(".userPasswordEx").val('');
        $(".passwordRepaet").val('');
        $(".increaseBonusPwd,.bgShow").fadeOut();
    });
    $(".ModifiedPwd").bind("click",function(){
        $(".increaseBonusPwd,.bgShow").fadeIn();
    });

    // 重置密码-服务企请求
    $(".increaseBonusPwd .btnForSurePwd").click(function(){
        var _this = $(this),
                _oldPassword = $(".userOldPasswordEx").val(),
                _password = $(".userPasswordEx").val(),
                _passwordRepeat = $(".passwordRepaet").val(),
                _params = $(".increaseBonusPwd").params();
        console.log(_params);
        if( _params != null ){
            // 判断密码是否一致
            console.log(_password + " : " + _passwordRepeat);
            if(_password != _passwordRepeat){
                $(".passwordRepaet").val("");
                $.hd.tips($(".userPasswordEx"), "两次密码输入不一致,请重新输入!");
                return false;
            }
            // 请求服务
            $.ajax({
                url 	 : "${mvcPath}/view/accountNumMan/changePassWord",
                type 	 : "post",
                dataType : "json",
                data 	 : {userOldPassWord:_oldPassword,userNewPassWord :_password},
                success  : function(data, textStatus, XMLHttpResponse) {
                    if(data.result){
                        $(".increaseBonusPwd,.bgShow").fadeOut();
                        $.hd.alertMsg.info("修改成功,需重新登录","修改",true,function(){
                            window.location.href = "${mvcPath}/login/logout";
                        });
                    }else{
                        $.hd.alertMsg.error(data.errors || "服务器繁忙,稍后请重试!","错误",true,true);
//                        $.hd.tips($(".userPasswordEx"),data.errors || "服务器繁忙,稍后请重试!");
                    }
                }
            });
        }
    });

</script>