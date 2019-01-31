<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/include.inc.jsp"%>
<div class="right-main paddingV60">
    <div class="marginTop40 table-height30">
        <div class="line"></div>
        <div class="title"><span>公司信息</span></div>
    </div>
    <!--------------------------------------编辑状态，start---------------------------------------------->
    <div id="compParams" class="info-edit-withBg positionRelative editCompanyInfo withoutInfo">
        <div class="lbl-logo-wrapper"><!------上传公司logo------>
            <label id="orgImg" class="lbl-file lblfile-square-max fileUpload" value="${org.orgImg}" defualt="${mvcPath}/img/upload_logo.png"></label>
        </div>
        <ul class="ul-info table-height30">
            <li class="width80"><span class="font-color-red">＊</span>公司名称</li>
            <li>
                <label class="input">
                    <label class="inputLabel"></label>
                    <input type="hidden" id="customerId" value="${org.customerId}">
                    <input type="text" ctype="required" class="labelInput width300" id="orgName" tabindex="0" value="${org.orgName}">
                </label>
            </li>
            <div class="clear"></div>
        </ul>
        <ul class="ul-info table-height30">
            <li class="width80"><span class="font-color-red">＊</span>公司地址</li>
            <li>
                <label class="input">
                    <label class="inputLabel"></label>
                    <input type="text" ctype="required" maxlength="70" x class="labelInput width300" id="address" tabindex="1" value="${org.address}">
                </label>
            </li>
            <div class="clear"></div>
        </ul>
        <ul class="ul-info table-height30">
            <li class="width80"><span class="font-color-red">＊</span>公司电话</li>
            <li>
                <label class="input">
                    <label class="inputLabel"></label>
                    <input type="text" ctype="mobile" class="labelInput width300" id="phone" tabindex="2" value="${org.phone}">
                </label>
            </li>
            <div class="clear"></div>
        </ul>
        <ul class="ul-info table-height30">
            <li class="width80"><span class="font-color-red">＊</span>公司网址</li>
            <li>
                <label class="input">
                    <label class="inputLabel" ></label>
                    <input type="text" ctype="url" class="labelInput width300" id="orgUrl" tabindex="3" value="${org.orgUrl}">
                </label>
            </li>
            <div class="clear"></div>
        </ul>
        <ul class="ul-info table-height30 heightAuto marginBottom20">
            <li class="width80"><span class="font-color-red">＊</span>公司三证</li>
            <li class="littleFileShow">
                <label id="paperOne" class="lbl-file lblfile-square-max fileUpload" value="${org.orgPaperOne}" defualt="${mvcPath}/img/wanshuizhengming.jpg"></label>
            </li>
            <li class="littleFileShow">
                <label id="paperTwo" class="lbl-file lblfile-square-max fileUpload" value="${org.orgPaperTwo}" defualt="${mvcPath}/img/yingyezhizhao.jpg"></label>
            </li>
            <li class="littleFileShow">
                <label id="paperThree" class="lbl-file lblfile-square-max fileUpload" value="${org.orgPaperThree}" defualt="${mvcPath}/img/yizheng.jpg"></label>
            </li>
            <div class="clear"></div>
        </ul>
        <div class="edit-btn">
            <input type="button" class="btn-hover-crimson cInfo-save" value="保存" tabindex="4">
            <input type="button" class="btn-line-666 cInfo-cancel" value="取消" tabindex="5">
        </div>
    </div>
    <!--------------------------------------编辑状态，end---------------------------------------------->
    <!--------------------------------------普通状态，start---------------------------------------------->
    <div class="info-edit-noBg positionRelative companyInfo">
        <div class="cInfo-edit"></div>
        <ul class="ul-info table-height100">
            <li class="marginRight20">
            	<img class="img100_cc" src="${org.orgImg == null ? 'none' : org.orgImg}" onerror="$(this).attr('src','${mvcPath}/img/jiazaishibai.jpg')">
            </li>
            <li class="cInfo">
                <span class="boldTitle">${org.orgName}</span>
                <span>${org.address == null ? "暂未填写企业地址" : org.address}</span>
                <span>${org.phone == null ? "暂未补充企业电话" :org.phone}</span>
                <c:choose>
                    <c:when test="${org.orgUrl != null}"><a href="http://${org.orgUrl}" target="_blank">${org.orgUrl}</a></c:when>
                    <c:otherwise><span>暂未填写</span></c:otherwise>
                </c:choose>

            </li>
            <div class="clear"></div>
        </ul>
        <ul class="ul-info heightAuto vertical-align-top marginTop40">
            <li class="width100 ">公司三证：</li>
            <li class="marginRight10">
                <img class="img150_cc" src="${org.orgPaperOne == null ? 'none' : org.orgPaperOne}" onerror="$(this).attr('src','${mvcPath}/img/wanshuizhengming.jpg')">
            </li>
            <li class="marginRight10">
                <img class="img150_cc" src="${org.orgPaperTwo == null ? 'none' : org.orgPaperTwo}" onerror="$(this).attr('src','${mvcPath}/img/yingyezhizhao.jpg')">
            </li>
            <li>
                <img class="img150_cc" src="${org.orgPaperThree == null ? 'none' : org.orgPaperThree}" onerror="$(this).attr('src','${mvcPath}/img/yizheng.jpg')">
            </li>
            <div class="clear"></div>
        </ul>
    </div>

    <!--------------------------------------普通状态，end---------------------------------------------->
    <div class="marginTop40 table-height30">
        <div class="line"></div>
        <div class="title"><span>平台管理员</span></div>
    </div>
  <!--------------------------------------编辑状态，start---------------------------------------------->
    <div class="info-edit-withBg editCManger withoutInfo">
    	<input id="id" type="hidden" value="${adminUser.id}">
        <ul class="ul-info table-height30">
            <li class="width80"><span class="font-color-red">＊</span>姓名</li>
            <li>
                <label class="input">
                    <label class="inputLabel" ></label>
                    <input id="realName" ctype="required" type="text" class="labelInput width300" tabindex="6" value="${adminUser.realName}">
                </label>
            </li>
            <div class="clear"></div>
        </ul>
      
        <ul class="ul-info table-height30">
            <li class="width80"><span class="font-color-red">＊</span>电话</li>
            <li>
                <label class="input">
                    <label class="inputLabel"></label>
                    <input id="userPhone" ctype="mobile" type="tel" class="labelInput width300" tabindex="8" value="${adminUser.userPhone}" >
                </label>
            </li>
            <div class="clear"></div>
        </ul>
        <ul class="ul-info table-height30">
            <li class="width80"><span class="font-color-red">＊</span>邮箱</li>
            <li>
                <label class="input">
                    <label class="inputLabel" ></label>
                    <input id="email" ctype="email" type="email" class="labelInput width300" tabindex="9" value="${adminUser.email}" >
                </label>
            </li>
            <div class="clear"></div>
        </ul>
        <div class="edit-btn">
            <input type="button" class="btn-hover-crimson CManger-save" value="保存">
            <input type="button" class="btn-line-666 CManger-cancel" value="取消">
        </div>
    </div>
    <!--------------------------------------编辑状态，end---------------------------------------------->
    <!--------------------------------------普通状态，start---------------------------------------------->
    <div class="info-edit-noBg CManger withInfo">
        <ul class="ul-info heightAuto ">
            <li class="cM">
                <c:if test="${sessionUser.isAdmin}"><span class="cM-edit"></span></c:if>
                <span class="boldTitle">${adminUser.realName}</span>
                <span></span>
                <span>${adminUser.userPhone}</span>
                <span>${adminUser.email}</span>
            </li>
            <div class="clear"></div>
        </ul>
    </div>
    <!--------------------------------------普通状态，end---------------------------------------------->
</div>

<script type="text/javascript">
	$(".fileUpload").fileUpload();

	$(".CManger-cancel").click(function(){
	    $(".editCManger").hide();
		$(".CManger").show();
	});
	
	$(".CManger-save").click(function(){
		var params = $(".editCManger").params();
		if(params == null) return false;
		$.ajax({
			url 	 : "${mvcPath}/view/companyInformation/updateAdmin",
			type 	 : "post",
			dataType : "json",
			data 	 : params,
			success  : function(data, textStatus, XMLHttpResponse) {
				if(data.result){
					refreshPage();
				}else{
                    $.hd.alertMsg.error(data.errors || "服务器繁忙,稍后请重试!");
				}
			}
		});
	});
	
	$(".cInfo-cancel").click(function(){
	    $(".editCompanyInfo").hide();
		$(".companyInfo").show();
	});
	
	$(".companyInfo .cInfo-edit").click(function (){
	    $(".companyInfo").hide();
	    $(".editCompanyInfo").show();
	});
	
	$(".cInfo-save").click(function(){
		var params = $("#compParams").params();
		if(params == null) return false;
		$.extend(params,{
			orgImg			:$("#orgImg").attr("value"),
			orgPaperOne 	:$("#paperOne").attr("value"),
			orgPaperTwo 	:$("#paperTwo").attr("value"),
			orgPaperThree	:$("#paperThree").attr("value")
		});
		$.ajax({
			url 	 : "${mvcPath}/view/companyInformation/updateCompInfo",
			type 	 : "post",
			dataType : "json",
			data 	 : params,
			success  : function(data, textStatus, XMLHttpResponse) {
				if(data.result){
					refreshPage();
				}else{
                    $.hd.alertMsg.error(data.errors || "服务器繁忙,稍后请重试!");
				}
			}
		});
	});
</script>