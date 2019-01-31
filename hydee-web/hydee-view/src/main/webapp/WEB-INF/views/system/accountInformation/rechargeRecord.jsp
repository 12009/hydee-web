<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/include.inc.jsp"%>
<div class="right-main">
     <div class="right-main-top" id="searchParams">
        <input type="text" value="${page.serialNumber}" id="serialNumber" placeholder="流水号"> 
        <label class="input">
        <input type="text" value="${page.pStartTime}" class="labelInput width200" tabindex="0" id="pStartTime" placeholder="开始时间" >
        </label>——
        <label class="input">
        <input type="text" value="${page.pEndTime}" class="labelInput width200" tabindex="0"  id="pEndTime" placeholder="结束时间" >
        </label>
		<select class="xiala" id="status">  
		  <option value="-1" <c:if test="${page.status==-1}">selected = "selected"</c:if>>全部充值记录</option>  
		  <option value="0" <c:if test="${page.status==0}">selected = "selected"</c:if>>待审核</option>  
		  <option value="1" <c:if test="${page.status==1}">selected = "selected"</c:if>>充值成功</option>  
		  <option value="2" <c:if test="${page.status==2}">selected = "selected"</c:if>>充值失败</option>
		</select>  
		<input type="button" class="width80 btn-hover-crimson nextPart" onclick="search()" value="查询"> 
    </div> 
    <div class="right-main-bottom">
    	
    	<ul class="ul-table-tHeader marginTop40 withPadding20">
		    <li class="width180">时间</li>
            <c:if test="${sessionUser.isSysAdmin}">
                <li class="width120">公司</li>
            </c:if>
		    <li class="width100">充值金额</li>
		    <li class="width180">流水号</li>
		    <li class="width100">支付方式</li>
		    <li class="width100">状态</li>
            <li class="width200">原因</li>
		    <div class="clear"></div>
		</ul>
		<div class="dataLoading"><img src="${mvcPath}/img/cLoading.gif"></div>
		<c:forEach items="${companyAccountRechargeList}" var="item"> 
			<ul class="ul-table-tBody table-height60 withPadding20">
				<fmt:formatDate value="${item.createTime}" var="createTime" type="both" pattern="yyyy-MM-dd hh:mm:ss"/>
			    <li class="width180">${createTime }</li>
                <c:if test="${sessionUser.isSysAdmin}">
                    <li class="width120">${item.orgName }</li>
                </c:if>
			    <li class="width100">${item.amount }</li>
			    <li class="width180" title="${item.serialNumber }" id="serialNumber2">${item.serialNumber }</li>
			    <li class="width100">${item.dictName }</li>
			    <li class="width100">
				    <c:choose> 
				    	<c:when test="${item.status == 0}">
				    		待审核
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
		    	</li>
                <li class="width200" >${item.remark }</li>
			    <div class="clear"></div>
			</ul>
	 	</c:forEach>
		 
		 <div class="tcdPageCode" count="${page.totalResult}" page="${page.currentPage}" size="${page.showCount}" form="/view/rechargeRecord" filter="#searchParams"></div>
   		<div class="clear"></div>
	</div>
    
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
        format:'Y-m-d H:i:s',
        formatDate:'Y-m-d',
        scrollMonth:false,
        scrollTime:false,
        scrollInput:false,
    });

    $("#startTime,#stopTime").datetimepicker({
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
     
        var _serialNumber = $("#serialNumber").val();
        var _pStartTime = $("#pStartTime").val();
        var _pEndTime = $("#pEndTime").val();
        var _status = $("#status").val();
        
        var _params={};
        
      	_params.serialNumber=_serialNumber;
        _params.pStartTime=_pStartTime;
        _params.pEndTime=_pEndTime;
        _params.status=_status;
        console.log(_params);
        loadingPage("/view/rechargeRecord",_params);
    }
      
    
    
    

</script>
    
    
    