<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/include.inc.jsp"%>

<div id="right-wrapper" class="floatLeft">
         
   <div class="right-main">
		<div class="activityMoreDetail"><span class="location"><a href="javascript:returnPage();">资金去向</a>><a class="font-color-crimson">企业培训基金明细</a></span></div>
        <div class="right-main-top" id="searchParams">
        	<input type="hidden" id="title" value="${page.title }"/>
        	<input type="hidden" id="dictId" value="${page.dictId }"/>
        	<input type="hidden" id="taskId" value="${page.taskId }"/>
	        <input type="text" id="customerName" placeholder="通过连锁企业查询" value="${page.customerName }">
	        <label class="input">
	        <input type="text" class="labelInput width200" tabindex="0" id="pStartTime" placeholder="开始时间" value="${page.pStartTime }">
	        </label>——<label class="input">
	        <input type="text" class="labelInput width200" tabindex="0" id="pEndTime" placeholder="结束时间" value="${page.pEndTime }">
	        </label> 
			<input type="button" class="width80 btn-hover-crimson nextPart" value="查询" onclick="search()"> 
    	</div> 
	    <div class="right-main-bottom">
	    <ul class="ul-table-tHeader withPadding20">
	    <li class="width200">流水号</li>
	    <li class="width150">连锁企业</li>
	    <li class="width80">用户名</li>
	    <%--<li class="width120">联系电话</li>--%>
	    <li class="width120">红包分成时间</li>
	    <li class="width110">红包金额（元）</li>
	    <li class="width130">平台服务费（元）</li>
	    <li>企业获得培训基金（元）</li>
	    </ul>
	     
	     
	    <c:forEach items="${companyAccountWhereList}" var="item"> 
			<fmt:formatDate value="${item.createTime}" var="createTime" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>

            <ul class="ul-table-tBody tableWithoutLine80" style="height:60px; padding-top:10px;padding-left: 0px;">
		    <li class="width200" title="${item.serialNumber}">${item.serialNumber}</li>
		    <li class="width150">${item.customerName}</li>
		    <li class="width80">${item.userName}</li>
		    <%--<li class="width120" title="${item.phone}">${item.phone}</li>--%>
		    <li class="width120">
		     
			    <c:set value="${ fn:split(createTime, ' ') }" var="str1" />
					 
				<c:forEach items="${ str1 }" var="s">
					<p>${ s }</p> 
				</c:forEach>
		    </li>
            <li class="width110">${item.redMoney}</li>
            <li class="width130">${item.serveCharge}</li>
            <li class="width110">${item.trainFund}</li>
		   
		    </ul>
			
		</c:forEach>
	     
	     	
		    
	    </div>  
	
		<div>
		    <div class="pageSum marginTop30">
		        <input value="返回" class="btn-line-crimson floatLeft width80" onclick="returnPage()" type="button">
		        <div class="tcdPageCode" count="${page.totalResult}" page="${page.currentPage}" size="${page.showCount}" form="/view/fundsWhereaboutsDetail" filter="#searchParams"></div>
	   			
		    </div> 
	    
	    </div>
        
    </div>
    <br>
	<div class="clear"></div>      
</div>
    

 
<script>//这里调用插件只是测试，可以放在单独的js文件里面,js位置在common后面
    $(".inputcolor").colorSelect({
        colorArray:["#5fcad9","#5ed3a0","#C876C2","#C697C3","#A6A3CE","#6469A9","#E6DED1","#ECA29F","#FA4A54","#76C08D","#E3A335","#B47941"],//传入选择颜色的数组
        conName:".leftViewBg",//相关联的改变背景色的class或者id
        inputHiddenId:"inputColor"//隐藏input的id
    })

    $("#pStartTime,#pEndTime").datetimepicker({
        lang:'ch',
        timepicker:true,
        format:'Y-m-d H:i:s',
        formatDate:'Y-m-d',
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
     
        var _taskId = $("#taskId").val();
        var _dictId = $("#dictId").val();
        var _customerName = $("#customerName").val();
        var _pStartTime = $("#pStartTime").val();
        var _pEndTime = $("#pEndTime").val();
        
        
        var _params = {};
        
      	_params.taskId = _taskId;
        _params.pStartTime = _pStartTime;
        _params.pEndTime = _pEndTime;
        _params.dictId = _dictId;
        _params.customerName = _customerName;
        
        loadingPage("/view/fundsWhereaboutsDetail",_params);
    }
    
    
    function returnPage(){
    	var _params={};
    	loadingPage("/view/fundsWhereabouts",_params);
    }
    
    
</script>
</body>
</html>