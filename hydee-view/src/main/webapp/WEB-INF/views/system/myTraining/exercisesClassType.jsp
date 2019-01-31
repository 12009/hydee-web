<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include.inc.jsp" %>
<div class="right-main">
    <div class="table-height30 marginTop20">
        <a href="javascript:;" class="width80 fakeBtn-hover-crimson floatLeft addExercises">添加分类</a><span
            style=" float:right;"><a style="color:#c6244b;" onclick="loadingPage('/view/trainingExercises')">返回习题列表</a></span>
        <div class="clear"></div>
    </div>
    <ul class="ul-table-tHeader marginTop20 paddingLR20">
        <li class="width250">分类名称</li>
        <li class="width150">添加人</li>
        <li class="width250">添加时间</li>
        <li class="width160">操作</li>
        <div class="clear"></div>
    </ul>
    <c:forEach items="${reList}" var="item">
        <ul class="ul-table-tBody tableWithoutLine50 paddingLR20">
            <li class="width250" title="${item.name}">${item.name}</li>
            <li class="width150">${item.realName}</li>
            <li class="width250">${item.createTimeFmt}</li>
            <li class="width160" rid="${item.classTypeId}">
                <a href="javascript:;" onclick="update(${item.classTypeId})" class="operation disInlineBlock updateExercises">修改</a>
                <a class="operation disInlineBlock deleteExercises">删除</a>
            </li>
            <div class="clear"></div>
        </ul>
    </c:forEach>
    <div class="tcdPageCode" count="${page.totalResult}" page="${page.currentPage}" size="${page.showCount}" form="/view/trainingExercises/exercisesClassType" filter="#searchParams"></div>
    <div class="clear"></div>
</div>

<div class="shadowWhite displayNone increaseBonus"><!--添加分类-->
    <div class="contentWrapper">
        <div class="content">
            <input type="hidden" value="" id="classTypeId"/>
            <div class="detailTitle">添加分类</div>
            <div class="div-line"></div>
            <ul class="ul-info table-height30" style="margin-top:60px;">
                <li class="width80">分类名称</li>
                <li class="width180"><input type="text" id="exercisesType" class="width250"></li>
                <div class="clear"></div>
            </ul>

        </div>
        <div class="btn-wraper width360">
            <input type="button" value="确定" onclick="addOrUpdate()" class="btn-hover-crimson marginRight10 saveExercises">
            <input type="button" value="取消" class="btn-line-crimson btnCancel">
        </div>
    </div>
</div>
<div class="bgShow"></div>
<div class="clear"></div>

<script type="text/javascript">

    $(function(){
        $('.btnCancel').bind("click",function(){
            $(".increaseBonus,.bgShow").fadeOut();
        });

        $('.addExercises').bind("click",function(){
            $('.detailTitle').html("添加分类");
            $('#exercisesType').val('');
            $(".increaseBonus,.bgShow").fadeIn();
        });

        $(".deleteExercises").bind("click",function(){
            var _rid = $(this).parent().attr("rid");
            $.hd.alertMsg.confirm("删除","确认删除这个类型吗?",function(){
                $.ajax({
                    url 	 : "${mvcPath}/view/trainingExercises/deleteExercisesType",
                    type 	 : "post",
                    dataType : "json",
                    data 	 : {classTypeId:_rid},
                    success  : function(data, textStatus, XMLHttpResponse) {
                        console.log(data);
                        if(data.result){
                            $.hd.alertMsg.info("删除成功！");
                            loadingPage('/view/trainingExercises/exercisesClassType');
                        }else{
                            if(data.status == 1040){
                                $.hd.alertMsg.error(data.errors);
                            }else{
                                $.hd.alertMsg.error("服务器繁忙,稍后请重试!");
                            }
                        }

                    },
                    error : function(XMLHttpRequest, textStatus, errorThrown) {
                        console.log(textStatus);
                    }
                });
            });
        });

    });

    function update(classTypeId){
        $.ajax({
            url 	 : "${mvcPath}/view/trainingExercises/updateExercises",
            type 	 : "post",
            dataType : "json",
            data 	 : {classTypeId:classTypeId},
            success  : function(data, textStatus, XMLHttpResponse) {
                console.log(data);
                console.log(data.data.name);
                $('#classTypeId').val(data.data.classTypeId);
                $('#exercisesType').val(data.data.name);
                $('.detailTitle').html("修改分类");
                $(".increaseBonus,.bgShow").fadeIn();
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                console.log(textStatus);
            }
        });
    }


    function addOrUpdate(){
        var _exercisesType = $('#exercisesType').val();
        console.log($('#classTypeId').val());
        $.ajax({
            url 	 : "${mvcPath}/view/trainingExercises/saveOrUpdateExercisesType",
            type 	 : "post",
            dataType : "json",
            data 	 : {name:_exercisesType,classTypeId:$('#classTypeId').val()},
            success  : function(data, textStatus, XMLHttpResponse) {
                if(data.result){
                    loadingPage('/view/trainingExercises/exercisesClassType');
                }else{
                    switch (data.status) {
                        case 1035:
                            // 名字被占用
                            $.hd.tips($("#exercisesType"), data.errors);
                            break;
                        default:
                            $.hd.alertMsg.error("服务器繁忙,稍后请重试!");
                            break;
                    }
                }
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                console.log(textStatus);
            }
        });
    }
</script>