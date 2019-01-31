<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include.inc.jsp"%>
<!DOCTYPE html>
<html lang="ch">
<head>
    <title>药企平台</title>
    <%@ include file="./static.jsp"%>
</head>
<body>
<div id="hd_content" style="overflow-y: auto; overflow-x: hidden; width: 100%;">
<div id="wrapper" class="lineBody">
    <div id="left-wrapper" class="floatLeft" style="min-height: 600px;">
        <div class="left-top">
			<input value="${org.orgImg}" id="image" type="hidden"/>
            <img src="${mvcPath}/image/DefaultIcon.png" id="orgImage">
            <p>${sessionUser.isSysAdmin ? '超级管理员' :sessionUser.company}</p>
        </div>
        <!-- 加载角色菜单 -->
        <%@ include file="./menu.jsp"%>
    </div>
    <div id="right-wrapper" class="floatLeft">
        <%@ include file="./head.jsp"%>
        <iframe id="mainFrame" frameborder="0"></iframe>
        <div id="right-content">
        </div>
    </div>
</div>
<div class="clear"></div>
</div>
</body>
<script type="text/javascript">
	var currUrl = "",
		hdContentDom = $("#hd_content"),
		wrapperDom = $("#wrapper");
		iframeDom = $("#mainFrame"),
		menuDom = $("#left-wrapper"),
		contenteDom = $("#right-content"),
		fixedHeight = $(".right-top:eq(0)").height() + 10,
		iframeUrlFilters = new RegExp("/activity/list|/activity/index","g"),
		timer = null;
	var option = ['A','B','C','D','E','F'];
	var optionXM = ['C','D','E','F'];

	function hideText(){
		$('.optionA').hide();
		$('.optionB').hide();
		$('#optionA').parent().hide();
		$('#optionB').parent().hide();
		$('#checkA').html("正确");
		$('#checkB').html("错误");
		for(var i = 0 ; i < optionXM.length ; i++){
			$('.option'+optionXM[i]).hide();
			$('#'+optionXM[i]).parent().hide();

		}
	}
	function showText(){
		for(var i = 0 ; i < option.length ; i++){
			$('.option'+option[i]).show();
			$('#option'+option[i]).show();
			$('#option'+option[i]).parent().show();
			$('#'+option[i]).parent().show();
			$('#'+option[i]).show();
		}
		$('#checkA').html("A");
		$('#checkB').html("B");
	}

	function clearTest(){
		for(var i = 0 ; i < option.length ; i++){
			$('#'+option[i]).val("");
		}
	}

	/**
	 * 菜单点击加载页面...
	 */
    $('#nav a').each(function (index,el){
        $(this).removeAttr('href');
        $(this).click(function(){
            var _href = $(this).attr('url');
            $('#nav a').removeClass('active');
            $(this).addClass('active');
            loadingPage(_href);
        });
    });

	onerror = function(msg,url,l){
		console.error(url+"(第"+l+"行)发生错误："+msg);
//		window.location.reload();
	}

	/**
	 * 加载首菜单页面
	 */
	$(function(){
		var _image = $('#image').val();
		if(_image != ''){
			$('#orgImage').attr('src',_image);
		}
		$("ul#nav li").each(function(){
			var _this = $(this),
				_a = _this.find("a:first"),
				_url = _a.attr("url");
			if(_url != null && _url != ""){
				var show = $(".C-op[forId='" + _this.attr("forId") + "']");
			    if (show) {
			        $(".C-op").not(show).hide();
			        show.toggle();
			    }
				_a.click();
				return false;
			}
		});
	});
	
	/**
	 * 初始化分页设置
	 */
	function initPageNation(){
		$(".tcdPageCode").pagenation();
	}

	function initListUi(){
		$(".ul-table-tBody li").each(function(){
			var _this = $(this),_html = _this.html();
			if( _html.trim() == "" ){
				_this.html("--");
			}
		});
		$(".ul-table-tR li").each(function(){
			var _this = $(this),_html = _this.html();
			if( _html.trim() == "" ){
				_this.html("--");
			}
		});
	}
	/**
	 * 重置个模块高度
	 */
	function initContentHeight(type){
		if(type == 0){
			var _height = Math.max(menuDom.height(), contenteDom.height() + fixedHeight);
			hdContentDom.height(_height);
		}else{
			hdContentDom.height(Math.max(menuDom.height(), iframeDom.height()) + fixedHeight);
			iframeDom.height(Math.max(menuDom.height(), $(window).height()) - fixedHeight);
		}
	}
	
	/**
	 * 加载页面
	 */
	function loadingPage(href,params){
		if(timer) clearInterval(timer);
		if(href != null && href != ""){
			currUrl = href;
			if(href.match(iframeUrlFilters) == null){
				contenteDom.show();
				iframeDom.hide();
				$(window).scrollTop(0);
	        	$.ajax({
	                url:"${mvcPath}"+href,
	                data:$.extend({viewUrl:href},params),
					type: "post",
					success: function (data,textStatus,request){
						if(data.result != null && data.result == false){
							if(data.status == 100){
								// 用户登录超时或登录失效时跳转
								window.location.href = "${mvcPath}/login/goLogin";
							}else{
								$.hd.alertMsg.error(data.errors || data.data || "服务器繁忙,稍后请重试!");
							}
						}else{
		                    $("#right-content").html(data);
		                    initContentHeight(0);
		                    initPageNation();
							initListUi();
						}
	                },
	                error: function (error,type,msg){
	                	switch(error.status)
	                	{
	                	case 404:
	                		break;
	                	case 500:
	                		break;
	                	default:
	                		break;
	                	}
	                }
	            });	
			}else{
				contenteDom.hide();
				initContentHeight(1);
				iframeDom.css("display","block");
				iframeDom.attr("src","${ydjPath}" + href + "?ydjToken=${sessionUser.ydjToken}");
			}
        }
	}
	/**
	 * 刷新页面
	 */
	function refreshPage(params){
		if(currUrl == null || currUrl.length == 0) return false;
		loadingPage(currUrl, params);
	}
</script>
</html>
