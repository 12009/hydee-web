<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/include.inc.jsp"%>
 
 
    <div id="right-wrapper" class="floatLeft">
        
        <div class="right-main">
            <input id="tabType" type="hidden" value="${tabType}"/>
            <ul class="ul-fundsWhere">
                <%--<li class="selected" dataId="myActivities">我的活动<span class="activeLine"></span></li>--%>
                <li dataId="myTraining" class="selected">我的培训<span class="activeLine"></span></li>
            </ul>
            <div id="myTraining" class="ul-selShow">
                <ul class="ul-info table-height30 marginBottom20">
                    <li class="width150 textAlignC marginRight30">
                        <div class="selecteType0 dropList" id="searchParams">
                            <div class="dropList-top"></div>
                           <!--  <div class="dropList-con width150" id="title">培训名称</div> -->
                            <input type="hidden" name="tabType" value="myTraining">
                            <input type="text" id="title" value="${page.title}" placeholder="培训名称">
                        </div>
                    </li>
                    <li><input type="button" class="width80 btn-hover-crimson nextPart"  value="筛选" onclick="search()" ></li>
                    <div class="clear"></div>
                </ul>
                <ul class="ul-table-tHeader withPadding20">
                    <li class="width250">培训名称</li>
                    <c:if test="${sessionUser.isSysAdmin}">
                        <li class="width120">公司</li>
                    </c:if>
                    <li class="width180">状态</li>
                    <li class="width250">冻结金额</li>
                    <li class="width130">操作</li>
                    <div class="clear"></div>
                </ul>
                <div class="dataLoading"><img src="${mvcPath}/img/cLoading.gif">……数据加载中,请稍等……</div>
                
                <c:forEach items="${companyTrainTaskList}" var="item">
                
                	<ul class="ul-table-tBody table-height60 withPadding20">
                    <li class="width250" title="${item.title }">${item.title }</li>
                    <c:if test="${sessionUser.isSysAdmin}">
                        <li class="width120" title="${item.customerName }">${item.customerName }</li>
                    </c:if>
                    <li class="width180">
						<c:choose>  
					    	<c:when test="${item.status == 1}">
					    		草稿
						    </c:when>  
						    <c:when test="${item.status == 2}">  
						       	即将开始
						    </c:when>  
						    <c:when test="${item.status == 3}">  
						       	进行中
						    </c:when>  
						    <c:when test="${item.status == 4}">  
						       	已结束
						    </c:when>  
						    <c:otherwise>  
						       	未知
						    </c:otherwise>  
				    	</c:choose> 
					</li>
                    <li class="width250">${item.residueMoney }</li>
                    <li class="width130">
                    	<c:choose>  
					    	<c:when test="${item.status == 1}">
					    		不能提取
						    </c:when>  
						    <c:when test="${item.status == 2}">  
						       	不能提取
						    </c:when>  
						    <c:when test="${item.status == 3}">  
						       	不能提取
						    </c:when>  
						    <c:when test="${item.status == 4}">  
						       	<a href="javascript:extract(${item.taskId });" class="font-color-blue">提取到可用余额</a>
						    </c:when>  
						    <c:otherwise>  
						       	未知
						    </c:otherwise>  
				    	</c:choose> 
                    	
                    </li>
                    <div class="clear"></div>
                	</ul>
                
                </c:forEach>
                <div class="tcdPageCode" count="${page.totalResult}" page="${page.currentPage}" size="${page.showCount}" form="/view/frozenBalance" filter="#searchParams"></div>
   				<div class="clear"></div>
   				
            </div>
        </div>
    </div>
    <div class="clear"></div>
 

<script>//这里调用插件只是测试，可以放在单独的js文件里面,js位置在common后面
   var dataList = [];
   <c:forEach items="${companyTrainTaskList}" var="item">
	   var map={
	    id:'${item.taskId }',
	    label:'${item.title }'
		};
   	dataList.push(map);
   </c:forEach>
   
    $(".selecteType0").dropList({
        dropListz:dataList,
        key:"id",
        name:"label",
        inputName:"ceshi",
        width:"150px",
        defaultStr:"选择类型",//选择"不填"要展示的内容
        input:false
    });

	 
    $(".selecteType1").dropList({
        dropListz:[{"id":"0","label":"不填"},{"id":"1","label":"测试类型10"},{"id":"2","label":"测试类型11"}],
        key:"id",
        name:"label",
        inputName:"ceshi",
        width:"150px",
        defaultStr:"选择类型",//选择"不填"要展示的内容
        input:false
    });

    var _tabType = $('#tabType').val();
    $(document).ready(function() {
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
    
    
    function search(){
        var _title = $("#title").val();
        var _params={};
      	_params.title=_title;
        _params.tabType="myTraining";
        loadingPage("/view/frozenBalance",_params);
    }
    
    function extract(taskId){
        if(confirm('是否提交到余额?'))
		{
		    $.ajax({
				url 	 : "${mvcPath}/view/frozenBalance/extract",
				type 	 : "post",
				dataType : "json",
				data 	 : {
				taskId :taskId
				},
				success  : function(data, textStatus, XMLHttpResponse) {
							if(data.result){
							 	 $.hd.alertMsg.error('操作成功');
							 	 refreshPage();
							}else{
								$.hd.alertMsg.error(data.data || "服务器繁忙,稍后请重试!");
								
							}
				}
		   });
		}
//		else
//		{
//		}
    }
    
    
    
</script>
 