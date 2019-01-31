<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/include.inc.jsp"%>
<div class="right-main">
    <div class="right-main-top" id="searchParams">
        <input type="text" value="${page.pName}" id="pName" placeholder="连锁企业/姓名查询"> 
        <label class="input">
        <input type="text" value="${page.pStartTime}" class="labelInput width200" tabindex="0" id="pStartTime" placeholder="开始时间" >
        </label>——
        <label class="input">
        <input type="text" value="${page.pEndTime}" class="labelInput width200" tabindex="0"  id="pEndTime" placeholder="结束时间" >
        </label>
		<select class="xiala" id="status">  
		  <option value="-1" <c:if test="${page.status==-1}">selected = "selected"</c:if>>全部充值记录</option>  
		  <option value="0" <c:if test="${page.status==0}">selected = "selected"</c:if>>待确认</option>  
		  <option value="1" <c:if test="${page.status==1}">selected = "selected"</c:if>>充值成功</option>  
		  <option value="2" <c:if test="${page.status==2}">selected = "selected"</c:if>>充值失败</option>
		</select>  
		<input type="button" class="width80 btn-hover-crimson nextPart" onclick="search()" value="查询"> 
    </div>
    <div class="right-main-bottom">
    <table width="100%" cellpadding="0" cellspacing="0">
	  <tr id="tr_1">
	    <td>流水号</td>
	    <td>充值时间</td>
	    <td>连锁企业</td>
	    <td>姓名</td>
	    <td>联系电话</td>
	    <td>充值金额</td>
	    <td>转账银行</td>
	    <td>银行卡号</td>
	  	<td>凭证</td>
	    <td>状态</td>
	    <td>操作</td>
	  </tr>
	  <c:forEach items="${companyAccountRechargeList}" var="item">
	 
	  	<tr >
	    <td id="serialNumber" >${item.serialNumber }</td>
	    <td>
	    	<fmt:formatDate value="${item.createTime}" var="createTime" type="both" pattern="yyyy-MM-dd hh:mm:ss"/>
	    	<ul><li>${createTime }</li></ul>
	    </td>
	    <td> 
	    	<ul>
	    		<c:set value="${ fn:split(item.orgName, '-') }" var="str1" />
				 
				<c:forEach items="${ str1 }" var="s">
					<li>${ s }</li> 
				</c:forEach>
 
	    	</ul>
	    </td>
	    <td>${item.accountName }</td>
	    <td>${item.phone }</td>
	    <td>￥${item.amount }</td>
	    <td>
	    	<ul>
	    		<c:set value="${ fn:split(item.bankName, '-') }" var="str1" />
				 
				<c:forEach items="${ str1 }" var="s">
					<li>${ s }</li> 
				</c:forEach>
 
	    	</ul>
	    </td>
	    <td>
		    <ul>
		    <c:choose>
		    	<c:when test="${fn:length(item.bankCard) >= 16}">  
			        <li>${fn:substring(item.bankCard, 0, 10)}</li><li>${fn:substring(item.bankCard, 10, fn:length(item.bankCard))}</li>
			    </c:when>  
			    <c:otherwise>  
			      <li>${item.bankCard}</li>
			    </c:otherwise>  
		    </c:choose>
		    </ul>
	    </td>
		<td>
			<%--<img width="50px" height="50px" src="${item.transferProof}">--%>
			<div class="mskeLayBg"></div>
			<div class="mskelayBox">
				<div class="mske_html">
				</div>
				<img class="mskeClaose" src="${mvcPath}/img/mke_close.png"/>
			</div>
			<div class="msKeimgBox">
				<ul>
					<li>
						<img src="${item.transferProof}" width="50" height="50"/>
						  <span class="hidden" style="display: none">
						  <img src="${item.transferProof}" width="650" height="288"/></span>
					</li>
				</ul>
			</div>
		</td>
	    <td> 
			<c:choose>
		    	<c:when test="${item.status == 0}">
		    		待确认
			    </c:when>  
			    <c:when test="${item.status == 1}">  
			       	充值成功
			    </c:when>  
			    <c:when test="${item.status == 2}">  
			       	充值失败
			    </c:when>  
			    <c:otherwise>  
			       	未知状态
			    </c:otherwise>  
		    </c:choose> 
		</td>
	    <td >
	    	<c:choose>
		    	<c:when test="${item.status == 0}">
		    		<p><a class="operation addBonus" onclick="updateStatus(${item.id},1)">确认已转账</a></p><p><a class="operation release" rid="${item.id}">审核不通过</a></p>
			    </c:when>  
			    <c:when test="${item.status == 1}">  
			       -
			    </c:when>  
			    <c:when test="${item.status == 2}">  
			       -
			    </c:when>  
			    <c:otherwise>  
			       -
			    </c:otherwise>  
		    </c:choose> 
		    
	    	
	    </td>
	  	</tr>
	  
	  </c:forEach>
	   
	</table>
    </div>
    
    
   <div class="tcdPageCode" count="${page.totalResult}" page="${page.currentPage}" size="${page.showCount}" form="/view/companyAccountRecharge" filter="#searchParams"></div>


	<div class="shadowWhite displayNone redistribution"><!--审核不通过-->
		<div class="contentWrapper">
			<div class="content">
				<input type="hidden" id="redistRechargeId" value=""/>
				<div class="detailTitle">审核不通过</div>
				<div class="div-line"></div>
				<ul class="ul-info table-height30">

					<li><textarea class="wenbenyu" id="wenbenyuRecharge" placeholder="请输入审核不通过的原因..."></textarea>
						<div class="clear"></div>
				</ul>
			</div>
			<div class="btn-wraper width360">
				<input type="button" value="确定" class="btn-hover-crimson marginRight10 btnRechargeSure">
				<input type="button" value="取消" class="btn-line-crimson btnRechargeCancel">
			</div>
		</div>
	</div>
	<div class="clear"></div>
	<div class="bgShow"></div>
</div>


<script>//这里调用插件只是测试，可以放在单独的js文件里面,js位置在common后面
    $(".inputcolor").colorSelect({
        colorArray:["#5fcad9","#5ed3a0","#C876C2","#C697C3","#A6A3CE","#6469A9","#E6DED1","#ECA29F","#FA4A54","#76C08D","#E3A335","#B47941"],//传入选择颜色的数组
        conName:".leftViewBg",//相关联的改变背景色的class或者id
        inputHiddenId:"inputColor"//隐藏input的id
    })

    $("#pStartTime,#pEndTime").datetimepicker({
        lang:'ch',
        timepicker:false,
        format:'Y-m-d',
        formatDate:'Y-m-d',
        scrollMonth:false,
        scrollTime:false,
        scrollInput:false,
    });

    $("#startTime,#stopTime").datetimepicker({
        lang:'ch',
        timepicker:false,
        format:'Y.m.d',
        formatDate:'Y.m.d',
        scrollMonth:false,
        scrollTime:false,
        scrollInput:false,
    });

    $("#addChainLayer").click(function(){//插件点击的时候直接调用，不放在异步后面；异步获取数据成功后调用方法addProduceData()，下面是模拟
        $(this).getProduce({
            goodsList:[],
            judgeLayer:"chain",
            appendId:"addActUl",
            appendFun:"deleteParent",
            judgeIdExist:true,
            endFun:""
        })

        //模拟异步成功获取到了数据
        var dataMoni=[{"chainName":"测试连锁一","chainId":"1"},{"chainName":"测试连锁二","chainId":"2"},{"chainName":"测试连锁三","chainId":"3"}];
        addProduceData(dataMoni,"chain");
    })

    $("#addGoodsLayer").click(function(){//插件点击的时候直接调用，不放在异步后面；异步获取数据成功后调用方法addProduceData()，下面是模拟
        $(this).getProduce({
            goodsList:[],
            judgeLayer:"product",
            appendId:"addActUlP",
            appendFun:"deleteParent",
            appendStyle:"2",
            judgeIdExist:true,
            endFun:""
        })

        //模拟异步成功获取到了数据
        var dataMoni=[{"productName":"测试商品一","product_standard":"我是规格1","productId":"1"},{"productName":"测试商品二","product_standard":"我是规格2","productId":"2"},{"productName":"测试商品三","product_standard":"我是规格3","productId":"3"}];
        addProduceData(dataMoni,"product");
    })
    
    function search(){
     
        var _pName = $("#pName").val();
        var _pStartTime = $("#pStartTime").val();
        var _pEndTime = $("#pEndTime").val();
        var _status = $("#status").val();
        
        var _params={};
        
      	_params.pName=_pName;
        _params.pStartTime=_pStartTime;
        _params.pEndTime=_pEndTime;
        _params.status=_status;
        
        loadingPage("/view/companyAccountRecharge",_params);
    }

	$(".release").bind("click",function(){
		var _rid = $(this).attr("rid");
		$("#redistRechargeId").val(_rid);
		$(".redistribution,.bgShow").fadeIn();
	});

	$(".btnRedistCancel").bind("click",function(){
		$(".redistribution,.bgShow").fadeOut();
	});
	$(".btnRechargeSure").bind("click",function(){
		var _redistRechargeId = $("#redistRechargeId").val();
		$(".redistribution,.bgShow").fadeOut();
		var _remark=$("#wenbenyuRecharge").val();
		var _param={};
		_param.status = 2;//审核不通过将状态变为审核不通过
		_param.id = _redistRechargeId;
		_param.remark=_remark;
		$.ajax({
			url 	 : "${mvcPath}/view/companyAccountRecharge/updateRecharge",
			type 	 : "post",
			dataType : "json",
			data 	 : _param,
			success  : function(data, textStatus, XMLHttpResponse) {
				if(data.result){
					$.hd.alertMsg.info("操作成功！");
					refreshPage();
				}else{
					$.hd.alertMsg.error("服务器繁忙,稍后请重试!");
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}
		});
	});
    
    function updateStatus(id,status){
     
     	$.hd.alertMsg.confirm("提示","确认修改此记录吗?",function(){
          $.ajax({
			url 	 : "${mvcPath}/view/companyAccountRecharge/updateStatus",
			type 	 : "post",
			dataType : "json",
			data 	 : {status :status,id :id},
			success  : function(data, textStatus, XMLHttpResponse) {
						if(data.result){
					    	refreshPage();
						}else{
							$.hd.alertMsg.error(data.data || "服务器繁忙,稍后请重试!");
					 
						}
					  }
			});
        });
        
        
        
		 
    }

jQuery(function(){
//选项卡滑动切换通用
	jQuery(function(){jQuery(".hoverTag .chgBtn").hover(function(){jQuery(this).parent().find(".chgBtn").removeClass("chgCutBtn");jQuery(this).addClass("chgCutBtn");var cutNum=jQuery(this).parent().find(".chgBtn").index(this);jQuery(this).parents(".hoverTag").find(".chgCon").hide();jQuery(this).parents(".hoverTag").find(".chgCon").eq(cutNum).show();})})

//选项卡点击切换通用
	jQuery(function(){jQuery(".clickTag .chgBtn").click(function(){jQuery(this).parent().find(".chgBtn").removeClass("chgCutBtn");jQuery(this).addClass("chgCutBtn");var cutNum=jQuery(this).parent().find(".chgBtn").index(this);jQuery(this).parents(".clickTag").find(".chgCon").hide();jQuery(this).parents(".clickTag").find(".chgCon").eq(cutNum).show();})})

//图库弹出层
	$(".mskeLayBg").height($(document).height());
	$(".mskeClaose").bind("click",function(){;$(".mskeLayBg,.mskelayBox").hide()});
	$(".msKeimgBox li").bind("click",function(){;$(".mske_html").html($(this).find(".hidden").html());$(".mskeLayBg").show();$(".mskelayBox").fadeIn(300)});
	$(".mskeTogBtn").bind("click",function(){;$(".msKeimgBox").toggleClass("msKeimgBox2");$(this).toggleClass("mskeTogBtn2")});

});
//屏蔽页面错误
jQuery(window).error(function(){
	return true;
});
jQuery("img").error(function(){
	$(this).hide();
});

</script>
</body>
</html>