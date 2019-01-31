<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include.inc.jsp"%>
<div class="right-main">
    <div class="table-height30 marginTop20">
        <a href="javascript:;" class="width80 fakeBtn-hover-crimson floatLeft addNewRole">新建角色</a>
        <div class="clear"></div>
    </div>
    <ul class="ul-table-tHeader marginTop20 paddingLR20">
        <li class="width250">角色名称</li>
        <li class="width250">创建时间</li>
        <li class="width250">账号数量</li>
        <li class="width160">操作</li>
        <div class="clear"></div>
    </ul>
    <div class="dataLoading"><img src="${mvcPath}/img/cLoading.gif"></div>
    <c:forEach items="${reList}" var="item">
	    <ul class="ul-table-tBody tableWithoutLine50 paddingLR20">
	        <li class="width250">${item.roleName}</li>
	        <li class="width250">${item.createTimeFmt}</li>
	        <li class="width250">${item.count}</li>
	        <li class="width160" rid="${item.roleId}" rname="${item.roleName}">
	            <a class="operation disInlineBlock update">修改</a>
	            <a class="operation disInlineBlock delete">删除</a>
	        </li>
	        <div class="clear"></div>
	    </ul>
    </c:forEach>
    <div class="tcdPageCode" count="${page.totalResult}" page="${page.currentPage}" form="/view/roleMan"></div>
    <div class="clear"></div>
</div>
<div class="bgShow"></div>
<div class="floatLayer width790 addNewRoleSel">
    <div class="header">新建角色</div>
    <div class="main height400" style="overflow: auto;">
        <ul class="ul-info table-height30">
            <li class="width80">角色名称</li>
            <li>
                <label id="roleParms" class="input">
                	<input type="text" id="roleId" value="" style="display: none;" />
                    <input type="text" id="roleName" ctype="required" class="labelInput width300">
                </label>
            </li>
            <div class="clear"></div>
        </ul>
        <ul class="ul-info table-height30">
            <li class="width80">权限控制</li>
            <li>
            <c:forEach items="${menues}" var="pMenu">
            	<div>
            	<div class="fontWB"><input type="checkbox" class="marginRight10 pmenu" mid="${pMenu.menuId}"/> ${pMenu.menuName}</div>
				<c:if test="${pMenu.subMenues.size() > 0}">
					<ul>
					<c:forEach items="${pMenu.subMenues}" var="item">
						<li class="marginRight30" style="margin-left: 20px;">
                            <label>
                                <input type="checkbox" class="marginRight10 submenu" mid="${item.menuId}" pid="${item.menuPid}"/> ${item.menuName}
                            </label>
                        </li>
					</c:forEach>
					<div class="clear"></div>
					</ul>
				</c:if>
				</div>
			</c:forEach>
            </li>
            <div class="clear"></div>
        </ul>
    </div>
    <div class="footer">
        <input type="button" value="确定" class="floatLayerSure btn-hover-crimson width80 marginRight10">
        <input type="button" value="取消" class="floatLayerCancel btn-hover-crimson width80">
    </div>
</div>

<script type="text/javascript">
    $(".floatLayer").hide();
    
    $(".addNewRole").bind("click",function(){
    	$("#roleId,#roleName").val("");
    	$(".addNewRoleSel input[type=checkbox]").removeAttr("checked");
    	$(".addNewRoleSel,.bgShow").fadeIn();
    });
    
    $(".floatLayerCancel").bind("click",function(){
    	$(".addNewRoleSel,.bgShow").fadeOut();
    })
    
    $(".pmenu").change(function(){
    	var _this = $(this);
    	if(_this.attr("checked") == null){
    		_this.parent().parent().find(".submenu").removeAttr("checked");
    	}else{
    		_this.parent().parent().find(".submenu").attr("checked","checked");
    	}
    });
    
    $(".submenu").change(function(){
    	var _this = $(this),
    		_pid = _this.attr("pid"),
    		_isClear = true;
    	if(_this.attr("checked") == null){
    		$(".submenu[pid="+_pid+"]").each(function(){
    			if($(this).attr("checked") != null){
    				_isClear = false;
    				return false;
    			}
    		});
   			if(_isClear) $(".pmenu[mid="+_pid+"]").removeAttr("checked");
    	}else{
    		$(".pmenu[mid="+_pid+"]").attr("checked","checked");
    	}
    });
    
    $(".floatLayerSure").bind("click",function(){
    	if($("#roleParms").params() == null) return false;
    	var params = $.extend({menuIds:""},$("#roleParms").params());
    	$(".pmenu,.submenu").each(function(){
    		var _this = $(this),
    			_mid = _this.attr("mid");
    		if(_this.attr("checked") != null){
    			params.menuIds += _mid + ",";
    		}
    	});
    	if(params.menuIds == ""){
    		$.hd.tips($("#roleName"), "您尚未为该角色选择任何菜单!");
    		return false;
    	}
    	params.menuIds = params.menuIds.substring(0,params.menuIds.length-1);
    	
    	$.ajax({
			url 	 : "${mvcPath}/view/roleMan/saveOrUpdate",
			type 	 : "post",
			dataType : "json",
			data 	 : params,
			success  : function(data, textStatus, XMLHttpResponse) {
				if(data.result){
					$(".addNewRoleSel,.bgShow").fadeOut();
//					window.confirm($("#roleId").val().length ==0 ? "角色新增成功!" : "角色修改成功!");
					refreshPage();
				}else{
					switch (data.status) {
					case 1004:
						// 角色名被占用
						$.hd.tips($("#roleName"), data.errors);
						break;
					default:
						$.hd.alertMsg.error("服务器繁忙,稍后请重试!");
						break;
					}
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}
		});
    });
    
    $(".delete").click(function(){
    	var _this = $(this),
    		_rid = _this.parent().attr("rid");
    	$.hd.alertMsg.confirm("删除","确认删除该角色吗?",function(){
	    	$.ajax({
				url 	 : "${mvcPath}/view/roleMan/delete",
				type 	 : "post",
				dataType : "json",
				data 	 : {roleId :_rid},
				success  : function(data, textStatus, XMLHttpResponse) {
					console.log(data);
					if(data.result){
						refreshPage();
					}else{
						$.hd.alertMsg.error(data.errors || data.data || "服务器繁忙,稍后请重试!");
					}
				}
			});
    	});
    });
    
    $(".update").click(function(){
    	var _this = $(this),
    		_rname = _this.parent().attr("rname"),
    		_rid = _this.parent().attr("rid");
    	$.ajax({
			url 	 : "${mvcPath}/view/roleMan/listMenues",
			type 	 : "post",
			dataType : "json",
			data 	 : {roleId :_rid},
			success  : function(data, textStatus, XMLHttpResponse) {
				if(data.result){
					$("#roleId").val(_rid);
					$("#roleName").val(_rname);
			    	$(".addNewRoleSel input[type=checkbox]").removeAttr("checked");
			    	$.each(data.data,function(i,item){
			    		$("input[type=checkbox][mid="+item.menuId+"]").attr("checked","checked");
			    	});
			    	$(".addNewRoleSel,.bgShow").fadeIn();
				}else{
					$.hd.alertMsg.error("服务器繁忙,稍后请重试!");
				}
			}
		});
    });
</script>