<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/include.inc.jsp"%>

<div id="right-wrapper" class="floatLeft">

	<div class="right-main">
		<input id="tabType" type="hidden" value="${tabType}"/>
		<ul class="ul-fundsWhere">
			<%--<li class="selected myActivities" dataId="myActivities">我的活动<span--%>
				<%--class="activeLine"></span>--%>
			<%--</li>--%>
			<li dataId="myTraining" class="selected myTraining">我的培训<span class="activeLine"></span>
			</li>
		</ul>

		<div id="myTraining" class="ul-selShow">
			<div class="right-main-top" style="margin-top:-20px;" id="searchParams">
				<input type="hidden" name="tabType" value="myTraining">
				<input type="text" id="title" value="${page.title}" placeholder="通过培训名称查询">
				<label class="input">
					<input type="text" class="labelInput width200" tabindex="0" id="pStartTime" placeholder="开始时间" value="${page.pStartTime}">
				</label>——
				<label class="input">
				<input type="text" class="labelInput width200" tabindex="0" id="pEndTime" placeholder="结束时间" value="${page.pEndTime}">
				</label>
				<select class="xiala" id="dictId">
					<option value="-1" <c:if test="${page.dictId==-1}">selected = "selected"</c:if>>选择类型</option>
					<option value="1" <c:if test="${page.dictId==1}">selected = "selected"</c:if>>连锁企业培训基金</option>
					<option value="2" <c:if test="${page.dictId==2}">selected = "selected"</c:if>>平台服务费</option>
					<option value="3" <c:if test="${page.dictId==3}">selected = "selected"</c:if>>培训奖金</option>
					<option value="4" <c:if test="${page.dictId==4}">selected = "selected"</c:if>>提取到可用余额</option>
				</select>
				<input type="button" class="width80 btn-hover-crimson nextPart" value="查询" onclick="search()">
				<input type="button" class="width160 btn-hover-crimson nextPart" value="导出已培训人员明细" onclick="accountWhereExport()">
			</div>
			<ul class="ul-table-tHeader withPadding20">
				<li class="width200">培训名称</li>
				<c:if test="${sessionUser.isSysAdmin}">
					<li class="width120">公司名</li>
				</c:if>
				<li class="width250">最近操作时间</li>
				<li class="width150">类型</li>
				<li class="width100">金额</li>
				<li class="width150 ">操作</li>
				<div class="clear"></div>
			</ul>
			<div class="dataLoading">
				<img src="${mvcPath}/img/cLoading.gif">
			</div>
			<c:forEach items="${companyAccountWhereList}" var="item">
				<fmt:formatDate value="${item.createTime}" var="createTime" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
				<c:if test="${sessionUser.isXmCustomer && item.dictId != 1}">
					<%-- Make a bitch test requirements. Small honey chain role temporarily does not display the chain get much money--%>
					<ul class="ul-table-tBody tableWithoutLine80">
						<li class="width200">${item.title }</li>
						<c:if test="${sessionUser.isSysAdmin}">
							<li class="width120">${item.customerName }</li>
						</c:if>
						<li class="width250">${createTime }</li>
						<li class="width150">${item.dictName }</li>
						<li class="width100">
							<c:choose>
								<c:when test="${item.dictId == 3}">
									${item.redMoneyCount }
								</c:when>
								<c:when test="${item.dictId == 2}">
									${item.serveChargeCount }
								</c:when>
								<c:when test="${item.dictId == 4}">
									${item.redMoneyCount }
								</c:when>
								<c:when test="${item.dictId == 5}">
									${item.fundDivided }
								</c:when>
								<c:otherwise>
									未知
								</c:otherwise>
							</c:choose>

						</li>
						<li class="width150">
							<c:choose>
								<c:when test="${item.dictId == 4}">

								</c:when>
								<c:otherwise>
									<a class="operation " style="margin-top:20px;" href="javascript:detail('${item.title }',${item.dictId },${item.taskId});">查看明细</a>
								</c:otherwise>
							</c:choose>
						</li>
						<div class="clear"></div>
					</ul>
				</c:if>
				<c:if test="${!sessionUser.isXmCustomer}">
					<ul class="ul-table-tBody tableWithoutLine80">
						<li class="width200">${item.title }</li>
						<c:if test="${sessionUser.isSysAdmin}">
							<li class="width120">${item.customerName }</li>
						</c:if>
						<li class="width250">${createTime }</li>
						<li class="width150">${item.dictName }</li>
						<li class="width100">
							<c:choose>
								<c:when test="${item.dictId == 3}">
									${item.redMoneyCount }
								</c:when>
								<c:when test="${item.dictId == 2}">
									${item.serveChargeCount }
								</c:when>
								<c:when test="${item.dictId == 1}">
									${item.trainFundCount }
								</c:when>
								<c:when test="${item.dictId == 4}">
									${item.redMoneyCount }
								</c:when>
								<c:when test="${item.dictId == 5}">
									${item.fundDivided }
								</c:when>
								<c:otherwise>
									未知
								</c:otherwise>
							</c:choose>

						</li>
						<li class="width150">
							<c:choose>
								<c:when test="${item.dictId == 4}">

								</c:when>
								<c:otherwise>
									<a class="operation " href="javascript:detail('${item.title }',${item.dictId },${item.taskId});">查看明细</a>
								</c:otherwise>
							</c:choose>
						</li>
						<div class="clear"></div>
					</ul>
				</c:if>
			</c:forEach>

			<div class="tcdPageCode" count="${page.totalResult}" page="${page.currentPage}" size="${page.showCount}" form="/view/fundsWhereabouts" filter="#searchParams"></div>
   			<div class="clear"></div>
		</div>
	</div>
</div>



<script>//这里调用插件只是测试，可以放在单独的js文件里面,js位置在common后面

	var _tabType = $('#tabType').val();
	$(document).ready(function() {
		console.log(_tabType);
		console.log('' != _tabType);
		if('' != _tabType){
			$('.ul-fundsWhere').find('li').removeClass('selected');
			$('.'+_tabType).addClass("selected");
			$(".ul-selShow").addClass("displayNone");
			$("#"+_tabType).removeClass("displayNone");

			$('.ul-fundsWhere li').bind("click",function(){
				var thisLi = $(this);
				thisLi.parent().find("li").removeClass("selected");
				thisLi.addClass("selected");
				var dataId = thisLi.attr("dataId");
				$(".ul-selShow").addClass("displayNone");
				$("#"+dataId).removeClass("displayNone");
			});
		}


	});


    $(".inputcolor").colorSelect({
        colorArray:["#5fcad9","#5ed3a0","#C876C2","#C697C3","#A6A3CE","#6469A9","#E6DED1","#ECA29F","#FA4A54","#76C08D","#E3A335","#B47941"],//传入选择颜色的数组
        conName:".leftViewBg",//相关联的改变背景色的class或者id
        inputHiddenId:"inputColor"//隐藏input的id
    })

    $("#startTime0,#stopTime0").datetimepicker({
        lang:'ch',
        timepicker:false,
        format:'Y.m.d H:i:s',
        formatDate:'Y.m.d',
        scrollMonth:false,
        scrollTime:false,
        scrollInput:false,
    });

    $("#pStartTime,#pEndTime").datetimepicker({
        lang:'ch',
        timepicker:false,
        format:'Y.m.d H:i:s',
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

        var _title = $("#title").val();
        var _pStartTime = $("#pStartTime").val();
        var _pEndTime = $("#pEndTime").val();
        var _dictId = $("#dictId").val();

        var _params={};

      	_params.title=_title;
        _params.pStartTime=_pStartTime;
        _params.pEndTime=_pEndTime;
        _params.dictId=_dictId;
		_params.tabType="myTraining";
		console.log(_params);
        loadingPage("/view/fundsWhereabouts",_params);
    }

	function accountWhereExport(){

        var _title = encodeURI(encodeURI($("#title").val()));
        var _pStartTime = $("#pStartTime").val();
        var _pEndTime = $("#pEndTime").val();
        var _dictId = $("#dictId").val();
		var data = "title="+_title+"&pStartTime="+_pStartTime+"&pEndTime="+_pEndTime+"&dictId=3";
		window.open("${mvcPath}/view/fundsWhereabouts/accountWhereExportEx?"+data);
    }

    function detail(_title,_dictId,_taskId){
        var _pStartTime = $("#pStartTime").val();
        var _pEndTime = $("#pEndTime").val();

        var _params = {};

      	_params.taskId = _taskId;
        _params.pStartTime = _pStartTime;
        _params.pEndTime = _pEndTime;
        _params.dictId = _dictId;
        loadingPage("/view/fundsWhereaboutsDetail",_params);
    }



</script>
