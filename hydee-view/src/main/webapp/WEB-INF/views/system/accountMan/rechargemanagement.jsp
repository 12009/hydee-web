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
	<ul class="ul-table-tHeader withPadding20">
		<li class="width120">流水号</li>
		<li class="width100">充值时间</li>
		<li class="width100">连锁企业</li>
		<li class="width80">姓名</li>
		<li class="width100">联系电话</li>
		<li class="width80">充值金额</li>
		<li class="width80">充值方式</li>
		<li class="width100">转账银行</li>
		<li class="width110">银行卡号</li>
		<li class="width80">凭证</li>
		<li class="width80">状态</li>
		<li class="width100">操作</li>
		<div class="clear"></div>
	</ul>
	<c:forEach items="${companyAccountRechargeList}" var="item">
		<ul class="ul-table-tBody" style="height: 80px;padding: 20px 5px 0px 10px">
			<li id="serialNumber" class="width120">
				<ul>
					<c:choose>
						<c:when test="${fn:length(item.serialNumber) >= 17}">
							<li>${fn:substring(item.serialNumber, 0, 8)}</li><li>${fn:substring(item.serialNumber, 8, fn:length(item.serialNumber))}</li>
						</c:when>
						<c:otherwise>
							<li>${item.serialNumber}</li>
						</c:otherwise>
					</c:choose>
				</ul>
			</li>
			<li class="width100">
				<fmt:formatDate value="${item.createTime}" var="createTime" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
				${createTime }
			</li>
			<li class="width100">
				<c:set value="${ fn:split(item.orgName, '-') }" var="str1" />
				<c:forEach items="${ str1 }" var="s">
					${ s }
				</c:forEach>
			</li>
			<li title="${item.accountName }" class="width80">${item.accountName }</li>
			<li class="width100">${item.phone }</li>
			<li class="width80">￥${item.amount }</li>
			<li class="width80">${item.dictName}</li>
			<li class="width100">
				<c:set value="${ fn:split(item.bankName, '-') }" var="str1" />
				<c:forEach items="${ str1 }" var="s">
					${ s }
				</c:forEach>
			</li>
			<li class="width110">
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
			</li>
			<li class="width80">
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
			</li>
			<li class="width80">
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
					<c:when test="${item.status == 3}">
						已退款
					</c:when>
					<c:otherwise>
						未知状态
					</c:otherwise>
				</c:choose>
			</li>
			<li class="width100">
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
			</li>
		</ul>
	</c:forEach>
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