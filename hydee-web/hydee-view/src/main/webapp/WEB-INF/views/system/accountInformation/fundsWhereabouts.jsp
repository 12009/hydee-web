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
		<%--<div id="myActivities" class="ul-selShow">--%>
			<%--<ul class="ul-table-tHeader withPadding20">--%>
				<%--<li class="width180">活动名称</li>--%>
				<%--<li class="width180">活动时间</li>--%>
				<%--<li class="width100">状态</li>--%>
				<%--<li class="width120 textAlignR">广告费</li>--%>
				<%--<li class="width120 textAlignR">优惠券</li>--%>
				<%--<li class="width120 textAlignR">平台费</li>--%>
				<%--<li class="width120 textAlignR">总费用</li>--%>
				<%--<div class="clear"></div>--%>
			<%--</ul>--%>
			<%--<div class="dataLoading">--%>
				<%--<img src="${mvcPath}/img/cLoading.gif">--%>
			<%--</div>--%>
			<%--<ul class="ul-table-tBody table-height60 withPadding20">--%>
				<%--<li class="width180" title="六一儿童节大促活动">六一儿童节大促活动</li>--%>
				<%--<li class="width180">2016.05.30-2016.06.04</li>--%>
				<%--<li class="width100">正在进行</li>--%>
				<%--<li class="width120 textAlignR">2000.3</li>--%>
				<%--<li class="width120 textAlignR">168325.3</li>--%>
				<%--<li class="width120 textAlignR">8625.3</li>--%>
				<%--<li class="width120 textAlignR">186253.9</li>--%>
				<%--<div class="clear"></div>--%>
			<%--</ul>--%>
			<%--<ul class="ul-table-tBody table-height60 withPadding20">--%>
				<%--<li class="width180" title="六一儿童节大促活动">六一儿童节大促活动</li>--%>
				<%--<li class="width180">2016.05.30-2016.06.04</li>--%>
				<%--<li class="width100">正在进行</li>--%>
				<%--<li class="width120 textAlignR">2000.3</li>--%>
				<%--<li class="width120 textAlignR">168325.3</li>--%>
				<%--<li class="width120 textAlignR">8625.3</li>--%>
				<%--<li class="width120 textAlignR">186253.9</li>--%>
				<%--<div class="clear"></div>--%>
			<%--</ul>--%>
			<%--<div>--%>
				<%--<div class="pageSum">每页 10 条 共 20 页</div>--%>
				<%--<div class="pageDetail">--%>
					<%--<div class="pager">--%>
						<%--<a href="javascript:void (0);">上一页</a> <span--%>
							<%--class="font-color-crimson"> 1 </span> <a--%>
							<%--href="javascript:void (0);">下一页</a>--%>
					<%--</div>--%>
					<%--<div class="goToPage">--%>
						<%--共20页 跳转至 <input type="text" class="width50">页 <input--%>
							<%--type="button" class="btn-hover-crimson" value="确定">--%>
					<%--</div>--%>
				<%--</div>--%>
			<%--</div>--%>
		<%--</div>--%>
		
		
		
		
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
				</select> <input type="button" class="width80 btn-hover-crimson nextPart" value="查询" onclick="search()">
			</div>
			<ul class="ul-table-tHeader withPadding20">
				<li class="width200">培训名称</li>
				<c:if test="${sessionUser.isSysAdmin}">
					<li class="width120">公司名</li>
				</c:if>
				<li class="width250">时间</li>
				<li class="width150">类型</li>
				<li class="width100">金额</li>
				<li class="width150 ">操作</li>
				<div class="clear"></div>
			</ul>
			<div class="dataLoading">
				<img src="${mvcPath}/img/cLoading.gif">
			</div>
			<c:forEach items="${companyAccountWhereList}" var="item"> 
				<fmt:formatDate value="${item.createTime}" var="createTime" type="both" pattern="yyyy-MM-dd hh:mm:ss"/>
				<ul class="ul-table-tBody table-height60 withPadding20">
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
					<li class="width150"><a class="operation "
						style="margin-top:20px;" href="javascript:detail('${item.title }',${item.dictId });">查看明细</a>
					</li>
					<div class="clear"></div>
				</ul>
				
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
    
    function detail(_title,_dictId){
        var _pStartTime = $("#pStartTime").val();
        var _pEndTime = $("#pEndTime").val();

        var _params={};
        
      	_params.title=_title;
        _params.pStartTime=_pStartTime;
        _params.pEndTime=_pEndTime;
        _params.dictId=_dictId;
        loadingPage("/view/fundsWhereaboutsDetail",_params);
    }
    
    

</script>
