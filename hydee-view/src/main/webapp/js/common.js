function getVerifyCode(){var a=new Date().getTime().toString(16);var b=encodePassword(a);return a+b}function encodePassword(b){var h="";var d="";if(""!=$.trim(b)){for(var a=1;a<=b.length;a++){var g=b.substr(a-1,1);var f=g.charCodeAt();var e=(100+f)*23+"";h=h+e}var j="";for(var c=0;c<h.length;c++){j+=h.charAt(h.length-c-1)}h=j;for(var a=1;a<=h.length;a++){var g=h.substr(a-1,1);d=d+String.fromCharCode(32+(parseFloat(g)*5)+a)}}d=d.replace(/'/g,"''");return d};
$(function(){
	function stopProp(e){//阻止冒泡方法
	    if(e.stopPropagation) e.stopPropagation();else e.cancelBubble = true;
	}

	var winH=$(window).height();
	var rightWinH=$("#right-wrapper").height();
	$("#left-wrapper").css("min-height",winH);
	$("#right-wrapper").css("min-height",winH);
	if(rightWinH>winH) $("#left-wrapper").css("height",rightWinH);

	var imgUploadUrl="http://ydjia.hydee.cn:8090/hwimg/store/img";//全局变量（图片的上传地址）

	//登陆相关start
	var inputPlace = document.createElement('input');
	if ("placeholder" in inputPlace) {
	    $(".inputLabel").addClass("nonei");
	} else {
	    $(".inputLabel").show();
	    $(".input").each(function () {
	        var inputDom = $(this).find("input");
	        var labelDom = $(this).find("label");
	        var val = inputDom.val();
	        if (!val.match(/^\s*$/)) {
	            labelDom.hide();
	        }
	        $(this).find("input").focus(function () {
	            labelDom.hide();
	        })
	        $(this).find("input").blur(function () {
	            var value = $(this).val();
	            if (value.match(/^\s*$/)) {
	                labelDom.show();
	            }
	        })
	    })

	    $(".inputLabel").each(function () {
	        var val = $("#" + $(this).attr("for")).val();
	        if (val != undefined && !val.match(/^\s*$/)) {
	            $(this).hide();
	        }
	    })

	    $("body").delegate(".labelInput", "keydown", function () {
	        $("#" + $(this).attr("id") + "Label").hide();
	        if ($(this).attr("id").indexOf("T") >= 0) {
	            $("#" + $(this).attr("id").split("T")[0] + "Label").hide();
	        }
	    })

	    $("body").delegate(".labelInput", "keyup", function () {
	        if ($(this).val().match(/^\s*$/)) {
	            $("#" + $(this).attr("id") + "Label").show();
	            if ($(this).attr("id").indexOf("T") >= 0) {
	                $("#" + $(this).attr("id").split("T")[0] + "Label").show();
	            }
	        } else {
	            $("#" + $(this).attr("id") + "Label").hide();
	        }
	    })
	}
	//登陆相关stop

	//自适应更改页面缩放
	var winWidth = $(window).width();
	var w_winWidth = winWidth / 1349;
	if (w_winWidth < 1) {
	    if (!IsPC()) {
	        $("meta[name='viewport']").attr("content", "width=device-width, initial-scale=" + w_winWidth + ",minimum-scale=" + w_winWidth + ", user-scalable=yes");
	    }
	}

	function IsPC() {//判断是否是移动端
	    var userAgentInfo = navigator.userAgent;
	    var Agents = new Array("Android", "iPhone", "SymbianOS", "Windows Phone", "iPad", "iPod");
	    var flag = true;
	    for (var v = 0; v < Agents.length; v++) {
	        if (userAgentInfo.indexOf(Agents[v]) > 0) {
	            flag = false;
	            break;
	        }
	    }
	    return flag;
	}

	//左侧导航start
	$("body").delegate("#nav li", "click", function () {
	    var thisA = $(this);
	    if (thisA.hasClass("C-op")) return;
	    var dataId = thisA.attr("data-id");
	    var show = $(".C-op[forId='" + dataId + "']");
	    if (show) {
	        $(".C-op").not(show).hide();
	        show.toggle();
	    }
	})

	$("body").delegate(".CManger .cM .cM-edit", "click", function () {
	    var index = $(this).parent().index();
	    $("#cMName").val("").val($(".CManger .cM:eq(" + index + ") span:eq(1)").text());
	    $("#cMJob").val("").val($(".CManger .cM:eq(" + index + ") span:eq(2)").text());
	    $("#cMTel").val("").val($(".CManger .cM:eq(" + index + ") span:eq(3)").text());
	    $("#cMEmail").val("").val($(".CManger .cM:eq(" + index + ") span:eq(4)").text());
	    $(".CManger").hide();
	    $(".editCManger").show();
	})

	$("body").delegate(".editImgimgDelete", "click", function (e) {//上传图片的删除
	    stopProp(e);
	    var that = $(this);
	    if (confirm("确认删除图片？")) {
	        var inputDom = that.parent().parent().parent().find(".fileUpload:last");
	        if (that.parent().parent().parent().find(".uploadImg").length <= inputDom.attr("limit")) {
	            inputDom.parent().show();
	        }
	        that.parent().parent().remove();
	    }
	})

	$("body").delegate(".ul-fundsWhere li", "click", function (e) {//上传图片的删除
	    stopProp(e);
	    var thisLi = $(this);
	    thisLi.parent().find("li").removeClass("selected");
	    thisLi.addClass("selected");
	    var dataId = thisLi.attr("dataId");
	    $(".ul-selShow").addClass("displayNone");
	    $("#"+dataId).removeClass("displayNone");
	})

	$("body").delegate(".payment", "click", function (e) {//账户信息-账户充值-支付方式的选择
	    stopProp(e);
	    var thisA = $(this);
	    var id = thisA.attr("id");
	    switch (id) {
	        case "aliPay":
	            if (thisA.hasClass("Selected")) {
	                thisA.removeClass("Selected");
	            } else {
	                thisA.addClass("Selected");
	                $("#onlineBank").removeClass("Selected");
	                $("#Banks").addClass("displayNone");
	            }
	            break;
	        case "onlineBank":
	            if (thisA.hasClass("Selected")) {
	                thisA.removeClass("Selected");
	                $("#Banks").find("a").removeClass("Selected");
	                $("#Banks").addClass("displayNone");
	            } else {
	                $("#aliPay").removeClass("Selected");
	                thisA.addClass("Selected");
	                $("#Banks").removeClass("displayNone");
	            }
	            break;
	        default :
	            break;
	    }
	})

	$("body").delegate("#Banks a", "click", function (e) {//账户信息-账户充值-支付方式的选择
	    stopProp(e);
	    var thisA = $(this);
	    if (thisA.hasClass("Selected")) {
	        thisA.removeClass("Selected");
	    } else {
	        $("#Banks").find("a").removeClass("Selected");
	        thisA.addClass("Selected");
	    }
	})

	$("body").delegate(".all","click",function(e){//培训中
	    stopProp(e);
	    var thisDiv=$(this);
	    var thisP=thisDiv.parent();
	    if(thisP.hasClass("max-height")){
	        thisP.removeClass("max-height");
	        thisDiv.text("点击收起 ︿");
	    }else{
	        thisP.addClass("max-height");
	        thisDiv.text("查看全部 >");
	    }
	})

	var bgJudge=false;
	$(".bgShow").click(function (e) {
	    stopProp(e);
	    if(bgJudge){
	        $(".shadowWhite").addClass("displayNone");
	        $(".bgShow").hide();
	    }
	});

	$("body").delegate(".trainingOption li","click",function(e) {//新建任务，选择培训课件
	    stopProp(e);
	    $(".selectedTraining").text($(this).attr("title"));
	    //$(".trainingDetail").removeClass("displayNone");
		$(".trainingDetail").fadeIn();
	    $(".bgShow").show();
	    bgJudge=true;
	})

	//活动说明查看
	$("body").delegate(".viewActivity","click",function(e){
	    stopProp(e);
	    $(".trainingDetail").removeClass("displayNone");
	    $(".bgShow").show();
	    bgJudge=true;
	})

	//查看
	$("body").delegate(".overView","click",function(e){
	    stopProp(e);
	    $(".trainingDetail").removeClass("displayNone");
	    $(".bgShow").show();
	    bgJudge=true;
	})

	//删除
	//$("body").delegate(".deleteTraining","click",function(e){
	//    stopProp(e);
	//	$.hd.alertMsg.confirm("删除","确认删除这个培训吗?",function(){
	//		$(this).parent().parent().remove();
	//	});
	//    //if (confirm("确认删除这个培训吗？")) {
	//    //    $(this).parent().parent().remove();
	//    //}
	//})

	//增加奖金
	$("body").delegate(".addBonus","click",function(e){
	    stopProp(e);
	    $(".increaseBonus").removeClass("displayNone");
	    $(".bgShow").show();
	    bgJudge=true;
	})

	$("body").delegate(".increaseBonus .btnForSure","click",function(e){
	    stopProp(e);
	    $(".increaseBonus").addClass("displayNone");
	    $(".bgShow").hide();
	})

	$("body").delegate(".increaseBonus .btnCancel","click",function(e){
	    stopProp(e);
	    $(".increaseBonus").addClass("displayNone");
	    $(".bgShow").hide();
	})

	//重新发布
	$("body").delegate(".release","click",function(e){
	    stopProp(e);
	    $(".redistribution").removeClass("displayNone");
	    $(".bgShow").show();
	    bgJudge=true;
	})

	$("body").delegate(".redistribution .btnForSure","click",function(e){
	    stopProp(e);
	    $(".redistribution").addClass("displayNone");
	    $(".bgShow").hide();
	})

	$("body").delegate(".redistribution .btnCancel","click",function(e){
	    stopProp(e);
	    $(".redistribution").addClass("displayNone");
	    $(".bgShow").hide();
	})

	//关闭弹出层的叉号
	$("body").delegate(".shadowClose","click",function(e){
	    stopProp(e);
	    $(this).parent().parent().addClass("displayNone");
	    $(".bgShow").hide();
	});

	$("body").delegate(".mskeClaose","click",function(e){
		stopProp(e);
		$(".bgShow,.mskelayBox").hide()
	});

	$("body").delegate(".msKeimgBox li","click",function(e){
		stopProp(e);
		$(".mske_html").html($(this).find(".hidden").html());
		$(".mskelayBox,.bgShow").fadeIn(300);
	});

	$("body").delegate(".mskeTogBtn","click",function(e){
		stopProp(e);
		$(".msKeimgBox").toggleClass("msKeimgBox2");
		$(this).toggleClass("mskeTogBtn2");
	});

	//增加题目
	var addQ;
	var oldDom;
	//$("body").delegate(".addQuestion","click",function(e){
	//    stopProp(e);
	//    addQ="addQuestion";
	//    oldDom=$(".questionList");
	//    if(oldDom.find("li").length<10 ){
	//        $(".addQuestionShadow").removeClass("displayNone");
	//        $(".bgShow").show();
	//        bgJudge=true;
	//    }else{
	//        if(confirm("最多只能添加10个题目")){
	//            obj.parent().remove();
	//        }
	//    }
	//})
    //
	//var  xuanxiang = ['A','B','C','D','E','F'];
	//$("body").delegate(".li-question a","click",function(e) {//新建培训课件，增加题目
	//    stopProp(e);
	//    addQ="a";
	//    var thisLi=$(this).parent();
	//    oldDom=thisLi;
	//    var shadow=$(".addQuestionShadow");
	//    shadow.removeClass("displayNone");
	//    $(".bgShow").show();
	//    bgJudge=true;
	//    shadow.find("textarea").val(thisLi.attr("title"));
	//	shadow.find("input[type=radio]"+"#"+thisLi.attr("tabType")).attr("checked","checked");
	//	for(var i=0;i<shadow.find("input[type='text']").length;i++){
	//		var choose = shadow.find("input[type=text]").eq(i).val(thisLi.attr("tab"+xuanxiang[i]));
	//		if('' == choose) break;
	//	}
	//	var rightTab=thisLi.attr("rightanswer");
	//	if(thisLi.attr("tabtype")=="singleTab"){
	//		shadow.find("input[type=checkbox]"+"#"+thisLi.attr("rightanswer")).attr("checked","checked");
	//	}else{
	//		for(var i=0; i<rightTab.length; i++){
	//			shadow.find("input[type=checkbox]").each(function(e){
	//				if($(this).attr("id")==rightTab[i]){
	//					$(this).attr("checked","checked");
	//				}
	//			})
	//		}
	//	}
	//    shadow.find("input[type=radio]"+"#"+thisLi.attr("")).attr("checked","checked");
	//})

	function checkQuestionParams(){
		var _questionTitle = $("#questionTitle");
		var _questionA = $("#questionA");
		var _questionB = $("#questionB");
		var _questionC = $("#questionC");
		var _questionD = $("#questionD");
		var _checkBox = $('.answerR input[type = "checkbox"]').is(':checked');
		console.log(_checkBox);
		if(_questionTitle.val() == ""){
			$.hd.tips(_questionTitle,"题干不能为空！");
			return false;
		}
		if(_questionA.val() == ""){
			$.hd.tips(_questionA,"选项A不能为空！");
			return false;
		}
		if(_questionB.val() == ""){
			$.hd.tips(_questionB,"选项B不能为空！");
			return false;
		}
		if(_questionC.val() == ""){
			$.hd.tips(_questionC,"选项C不能为空！");
			return false;
		}
		if(_questionD.val() == ""){
			$.hd.tips(_questionD,"选项D不能为空！");
			return false;
		}
		if(!_checkBox){
			$.hd.tips($('.answerR input[type = "checkbox"][id = "A"]'),"请选择答案！");
			return false;
		}

		return true;
	}

	//$("body").delegate(".addQuestionShadow .btnForSure","click",function(e){
	//	//if(checkQuestionParams()){
	//	var _checkBox = $('.answerR input[type = "checkbox"]').is(':checked');
	//	if(!_checkBox){
	//		$.hd.tips($('.answerR input[type = "checkbox"][id = "A"]'),"请选择答案！");
	//		return ;
	//	}
	//		stopProp(e);
	//		var thisGp=$(this).parent().parent();
	//		var title=thisGp.find("textarea").val();
	//		var tabType=thisGp.find("input[type='radio']:checked").attr("id");
	//		var tabA=thisGp.find("input[type='text']").eq(0).val();
	//		var tabB=thisGp.find("input[type='text']").eq(1).val();
	//		var tabC=thisGp.find("input[type='text']").eq(2).val();
	//		var tabD=thisGp.find("input[type='text']").eq(3).val();
	//		var rightTab="";
	//		thisGp.find("input[type='checkbox']:checked").each(function(e){
	//			rightTab+=$(this).attr("id");
	//		})
	//		var newDom = '<li  class="li-question" style="float:left; width:360px; height:220px;"  title='+title+' tabType='+tabType+' rightAnswer='+rightTab+' ';
	//		for(var j=0;j<thisGp.find("input[type='text']").length;j++){
	//			var choose = thisGp.find("input[type='text']").eq(j).val();
	//			if('' == choose) break;
	//			newDom += 'tab'+xuanxiang[j]+'="'+ choose+'"';
	//		}
	//		newDom += '><a href="javascript:;" class="questionDetail"><span>'+ title;
	//		newDom += '';
	//		newDom += '</span></a><span style="margin-left:10px;" class="font-color-blue" onclick="deleteParent($(this))">删除</span>';
	//		console.log(thisGp.find("input[type='text']").length);
	//		for(var i=0;i<thisGp.find("input[type='text']").length;i++){
	//			var choose = thisGp.find("input[type='text']").eq(i).val();
	//			if('' == choose) break;
	//			newDom += '<br><span tab'+ xuanxiang[i] +' = '+ choose +'>选项'+ xuanxiang[i] +'：'+ choose +'</span>';
	//		}
	//		//var newDom="<li class='li-question' title='"+title+"' tabType='"+tabType+"' tabA='"+tabA+"' tabB='"+tabB+"' tabC='"+tabC+"' tabD='"+tabD+"' rightAnswer='"+rightTab+"'><a href='javascript:;' class='questionDetail'>"+title+"</a><span class='questionDelete' onclick='deleteParent($(this))'>删除</span></li>";
	//		//var newDom=" <li class='li-question' title='"+thisGp.find("textarea").val()+"' tabType='"+thisGp.find("input[type='radio'] :checked").attr("id")+"' tabA='"+thisGp.find("input[type='text']").eq(0).val()+"' tabB='"+thisGp.find("input[type='text']").eq(1).val()+"' tabC='"+thisGp.find("input[type='text']").eq(2).val()+"' tabD='"+thisGp.find("input[type='text']").eq(3).val()+"' rightAnswer='"+thisGp.find("input[type='checkbox']:checked").each().attr("id")+"'><a href='javascript:;' class='questionDetail'>"+thisGp.find("textarea").val()+"</a><span class='questionDelete' onclick='deleteParent($(this))'>删除</span></li>";
	//		switch(addQ){
	//			case "addQuestion":oldDom.append(newDom);
	//				break;
	//			case "a":oldDom.replaceWith(newDom);
	//				break;
	//			default :
	//				break;
	//		}
	//		thisGp.find("textarea").val("");
	//		thisGp.find("input[type='radio']").eq(0).click();
	//		thisGp.find("input[type='text']").val("");
	//		thisGp.find("input[type='checkBox']").removeAttr("checked");
	//		$(".addQuestionShadow").addClass("displayNone");
	//		$(".bgShow").hide();
	//	//}
	//})
	//
	//$("body").delegate(".addQuestionShadow .btnCancel","click",function(e){
	//    stopProp(e);
	//    var thisGp=$(this).parent().parent();
	//    thisGp.find("textarea").val("");
	//    thisGp.find("input[type='radio']").eq(0).click();
	//    thisGp.find("input[type='text']").val("");
	//    thisGp.find("input[type='checkBox']").removeAttr("checked");
	//    $(".addQuestionShadow").addClass("displayNone");
	//    $(".bgShow").hide();
	//})

	function forRadio(){
	    $(this).find("input[type='radio']").click();
	}

	//充值
	$(".rechargeButton").click(function(){
	    $(".recharge,.bgShow").fadeIn();
	})

	$(".rechargeFun .rechargeCancel").click(function(){
	    $(".recharge,.bgShow").fadeOut();
	})


	function getTimeStyle(time){
	    var time0=time.split("/")[0];
	    var time1=time.split("/")[1];
	    var time2=time.split("/")[2];
	    if(time1<10) time1="0"+time1;
	    if(time2<10) time2="0"+time2;
	    return time0+"."+time1+"."+time2;
	}




	// 截获ajax请求
	$(document).ajaxStart(function(){
		$("#hd_content").loading(true,{gifImg: "img/cLoading.gif"});
	}).ajaxStop(function(){
		$("#hd_content").loading(false,{gifImg: "img/cLoading.gif"});
	}).ajaxSend(function(evt, request, settings){
	});



});
/**
 * 本地临时缓存工具类(关闭浏览器后,自动清空)
 * 支持对象存储和读取
 */
var Cache = {
	storage :window.sessionStorage,
	save :function(key, value){
		this.storage[key] = (typeof value == "object" ? JSON.stringify(value) : value);
	},
	load :function(key){
		var value = this.storage.getItem(key);
		try {
			return JSON.parse(value);
		} catch (e) {
			return value;
		}
	},
	remove :function(key){
		this.storage.removeItem(key)
	},
	clear :function(){
		this.storage.clear();
	}
};
/**
 * 失去焦点后，判断答案是否可以选择
 * @param that
 * @param id
 */
function blurText(that,id){
	if($(that).val().trim() == ''){
		if($('#'+id).is(':checked')){
			$('#'+id).attr("checked", false);
		}
		$('#'+id).attr("disabled", true);
	}else{
		$('#'+id).attr("disabled", false);

	}

}

function forbidCheckBox(){
	$('.answerR input[type="checkbox"]').each(function(){
		$(this).attr('disabled',true);
	});
}

function forbidText(){
	$('.optionTxt').each(function(){
		var text = $(this).val();
		if(text.trim() == ""){
			var id = $(this).attr('rid');
			$('#'+id).attr("disabled", true);
		}
	});
}