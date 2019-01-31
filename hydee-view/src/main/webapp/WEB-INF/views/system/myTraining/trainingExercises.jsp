<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include.inc.jsp" %>
<div class="right-main">
    <div class="right-main-top1">
        <div class="table-height30 marginTop20">
            <a onclick="loadingPage('/view/trainingExercises/addRegularExercise')" class=" width120 fakeBtn-hover-crimson floatLeft"
               style="margin-right:10px;">添加常规习题</a>
            <a class=" width120 fakeBtn-hover-crimson floatLeft addRandomExercises" style="margin-right:10px;">添加随机习题</a>
            <a onclick="loadingPage('/view/trainingExercises/exercisesClassType')" class=" width80 fakeBtn-hover-crimson floatLeft"
               style="margin-right:10px;">习题分类</a>
            <a onclick="loadingPage('/view/trainingExercises/exercisesQuestionBank')" class=" width80 fakeBtn-hover-crimson floatLeft"
               style="margin-right:10px;">习题题库</a>
            <label style="float:left; margin-right:10px;" id="searchParams">
                <input type="text" id="title" name="title" value="${page.title}" placeholder="按标题搜索">
                <select class="classTypeId" name="classTypeId">
                    <option value="">全部</option>
                    <c:forEach items="${exercisesTypes}" var="types">
                        <c:if test="${types.classTypeId == page.classTypeId}">
                            <option value="${types.classTypeId}" selected="selected">${types.name}</option>
                        </c:if>
                        <c:if test="${types.classTypeId != page.classTypeId}">
                            <option value="${types.classTypeId}">${types.name}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </label>
            <input type="button" onclick="searchBase()" class="width80 btn-hover-crimson nextPart" value="查询">
        </div>
        <div class="clear"></div>
    </div>
    <ul class="ul-table-tHeader marginTop30 paddingLR20">
        <li class="width60">ID</li>
        <li class="width120">标题</li>
        <li class="width120">类型</li>
        <li class="width120">分类</li>
        <li class="width120">创建时间</li>
        <li class="width80">数量</li>
        <li class="width80">答题人次</li>
        <li class="width120">关联培训课件</li>
        <li class="width80">状态</li>
        <li class="width90 textAlignR">操作</li>
        <div class="clear"></div>
    </ul>
    <c:forEach items="${reList}" var="item">
        <ul class="ul-table-tBody tableWithoutLine100">
            <li class="width60">${item.baseId}</li>
            <li class="width120" title="${item.title}">${item.title}</li>
            <li class="width120">
                <c:choose>
                    <c:when test = "${item.type == 1}">随机习题</c:when>
                    <c:when test = "${item.type == 2}">常规习题</c:when>
                </c:choose>
            </li>
            <li class="width120">${item.name}</li>
            <li class="width120">${item.createTimeFmt}</li>
            <li class="width80">${item.count}</li>
            <li class="width80">0</li>
            <li class="width120">${item.classCount}</li>
            <li class="width80">
                <c:choose>
                    <c:when test = "${item.status == 0}">草稿</c:when>
                    <c:when test = "${item.status == 1}">进行中</c:when>
                </c:choose>
            </li>
            <li class="width90 textAlignR" rid="${item.baseId}" data="${item.type}" status="${item.status}">
                <c:if test="${item.status == 0}">
                    <a class="operation fabuExercises">发布</a>
                    <a href="#" class="operation updateBase">修改</a>
                    <a class="operation deleteBase">删除</a>
                </c:if>
                <c:if test="${item.status == 1}">
                    <a class="operation deleteBase">删除</a>
                </c:if>
                <c:if test = "${item.type == 2}">
                    <a href="#" class="operation overView">查看</a>
                </c:if>

            </li>
            <div class="clear"></div>
        </ul>
    </c:forEach>
    <div class="tcdPageCode" count="${page.totalResult}" page="${page.currentPage}" size="${page.showCount}" form="/view/trainingExercises" filter="#searchParams"></div>
    <div class="clear"></div>
</div>
<!--遮罩层-->
<div class="shadowWhite displayNone trainingDetail"><!--查看详情-->
    <div class="contentWrapper">
        <div class="content">
            <input type="hidden" id="detailBaseId"/>
            <div class="detailTitle">培训习题详情</div>
            <div class="div-ui-divide marginRight10"><span class="span-ui-dot"></span><span>习题名称</span></div>
            <ul class="ul-info heightAuto">
                <li class="width352 marginRight20">
                    <div class="table-height30" id="detailTitle">习题名称习题名称习题</div>
                </li>
                <div class="clear"></div>
            </ul>
            <div class="div-ui-divide marginRight10"><span class="span-ui-dot"></span><span>习题类别</span></div>
            <ul class="ul-info heightAuto">
                <li class="width352 marginRight20">
                    <div class="table-height30" id="detailClassType">类别一</div>
                </li>
                <div class="clear"></div>
            </ul>
            <div class="div-ui-divide marginRight10"><span class="span-ui-dot"></span><span>习题</span></div>
            <div id="questions"></div>
        </div>
        <a class="shadowEdit"></a><!--这个编辑按钮只有在这个培训任务非进行中，非结束状态才出现-->
        <div class="shadowClose"></div>
    </div>
</div>

<div class="shadowWhite displayNone increaseBonus"><!--题库添加随机习题-->
    <div class="contentWrapper">
        <div class="content">
            <input type="hidden" id="baseId"/>
            <div class="detailTitle">题库添加随机习题</div>
            <div class="div-line"></div>
            <ul class="ul-info table-height30" style="margin-top:30px;">
                <li class="width80">习题名称</li>
                <li class="width180"><input type="text" id="randomTitle" class="width200"></li>
                <div class="clear"></div>
            </ul>
            <ul class="ul-info table-height30">
                <li class="width80">习题分类</li>
                <li class="width180">
                    <select id="classTypeId" style="width:100px; height:30px;">
                        <c:forEach items="${exercisesTypes}" var="types">
                            <option value="${types.classTypeId}">${types.name}</option>
                        </c:forEach>
                    </select>
                </li>
                <div class="clear"></div>
            </ul>

            <ul class="ul-info table-height30">
                <li class="width80">创建题数</li>
                <li class="width110"><input type="text" id="randomCount" class="width100"></li>
                <li>(最多支持${limitCount}道)</li>
                <div class="clear"></div>
            </ul>
        </div>
        <div class="btn-wraper width360">
            <input type="button" value="确定" onclick="addRandom()" class="btn-hover-crimson marginRight10 btnSureRandom">
            <input type="button" value="取消" class="btn-line-crimson btnCancel">
        </div>
    </div>
</div>
<div class="clear"></div>
<div class="bgShow"></div>

<script type="text/javascript">

    $(function(){
        $('.btnCancel').bind("click",function(){
            $('#randomTitle').val('');
            $('#randomCount').val('');
            $(".increaseBonus,.bgShow").fadeOut();
        });
        $('.addRandomExercises').bind("click",function(){
            $(".increaseBonus,.bgShow").fadeIn();
        });

        $(".deleteBase").bind("click",function(){
            var _rid = $(this).parent().attr("rid");
            $.hd.alertMsg.confirm("删除","确认删除这个习题吗?",function(){
                $.ajax({
                    url 	 : "${mvcPath}/view/trainingExercises/deleteExercisesBase",
                    type 	 : "post",
                    dataType : "json",
                    data 	 : {baseId:_rid},
                    success  : function(data, textStatus, XMLHttpResponse) {
                        if(data.result){
                            $.hd.alertMsg.info("删除成功！");
                            loadingPage('/view/trainingExercises');
                        }else{
                            if(data.status == 1036){
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

        //发布
        $(".fabuExercises").bind("click",function(){
            var _rid = $(this).parent().attr("rid");
            $.hd.alertMsg.confirm("发布","发布后将不允许修改，请确认?",function(){
                $.ajax({
                    url 	 : "${mvcPath}/view/trainingExercises/issueBase",
                    type 	 : "post",
                    dataType : "json",
                    data 	 : {baseId:_rid,status:1},
                    success  : function(data, textStatus, XMLHttpResponse) {
                        if(data.result){
                            $.hd.alertMsg.info("发布成功！");
                            loadingPage('/view/trainingExercises');
                        }else{
                            $.hd.alertMsg.error("服务器繁忙,稍后请重试!");
                        }

                    },
                    error : function(XMLHttpRequest, textStatus, errorThrown) {
                        console.log(textStatus);
                        console.log(XMLHttpRequest);
                    }
                });
            });
        });

        //修改随机习题
        $(".updateBase").bind("click",function(){
            var _rid = $(this).parent().attr("rid");
            var _type = $(this).parent().attr("data");
            if(_type == 1){//随机习题
                $.ajax({
                    url 	 : "${mvcPath}/view/trainingExercises/updateRandomBase",
                    type 	 : "post",
                    dataType : "json",
                    data 	 : {baseId:_rid},
                    success  : function(data, textStatus, XMLHttpResponse) {
                        if(data.result){
                            $('#baseId').val(data.data.baseId);
                            $('#randomTitle').val(data.data.title);
                            $('#randomCount').val(data.data.count);
                            $('#classTypeId').find('option[value='+ data.data.classTypeId +']').attr('selected',true);
                            $(".increaseBonus,.bgShow").fadeIn();
                        }else{
                            $.hd.alertMsg.error("服务器繁忙,稍后请重试!");
                        }

                    },
                    error : function(XMLHttpRequest, textStatus, errorThrown) {
                        console.log(textStatus);
                        console.log(XMLHttpRequest);
                    }
                });
            }else if(_type == 2){//常规习题
                editBase(_rid);
            }

        });

        //查看时候点击修改
        $(".shadowEdit").bind("click",function(){
            editBase($("#detailBaseId").val());
        });

        //查看
        $(".overView").bind("click",function(){
            var _rid = $(this).parent().attr("rid");
            var _type = $(this).parent().attr("data");
            var _status = $(this).parent().attr("status");
            $("#detailBaseId").val(_rid);

            if(1 == _status || _type == 1 || ${sessionUser.isSysAdmin}){
                $(".shadowEdit").hide();
            }else{
                $(".shadowEdit").show();
            }
            $.ajax({
                url 	 : "${mvcPath}/view/trainingExercises/showDetailBase",
                type 	 : "post",
                dataType : "json",
                data 	 : {baseId:_rid},
                success  : function(data, textStatus, XMLHttpResponse) {
                    $("#questions").empty();
                    var _companyTrainBase = data.data.companyTrainBase;
                    var _trainExercisesList = data.data.trainExercisesList;
                    $("#detailTitle").html(_companyTrainBase.title);
                    $("#detailClassType").html(_companyTrainBase.name);
                    var questionHtml;
                    $.each(_trainExercisesList, function(index,value){
                        var _type = value.type;
                        if(_type == "singleTab"){
                            _type = "单选";
                        }else if(_type == 'judgeTab'){
                            _type = "判断";
                        }else{
                            _type = "多选";
                        }
                        var _index = index+1;
                        questionHtml = '<ul class="question">';
                        questionHtml += "<li>"+ _index + "、" + value.content + "(" + _type + "正确答案：" + value.answer +")</li>";
                        $.each(value.optionsList, function(i,v){
                            questionHtml += "<li>"+v.optionNo+"."+ v.content +"</li>";
                        });
                        questionHtml += '</ul>';
                        $("#questions").append(questionHtml);
                    });

                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                }
            });

        });

    });



    function editBase(_rid){
        var _param = {};
        _param.baseId = _rid;
        loadingPage("/view/trainingExercises/addRegularExercise",_param);
    }

    function searchBase(){
        var _param = {};
        _param.title = $('#title').val();
        _param.classTypeId = $('.classTypeId').val();
        loadingPage("/view/trainingExercises",_param);
    }

    function addRandom(){
        var _randomTitle = $('#randomTitle').val();
        var _classTypeId = $('#classTypeId').val();
        var _randomCount = $('#randomCount').val();
        var _baseId = $('#baseId').val();
        if(_randomCount > ${limitCount}){
            $.hd.tips($("#randomCount"), "最多"+ ${limitCount} +"道题目");
            return;
        }
        if(_randomTitle == ''){
            $.hd.tips($("#randomTitle"), "名称不能为空");
            return;
        }

        if(_randomCount == ''){
            $.hd.tips($("#randomCount"), "数量不能为空");
            return;
        }

        var _params = {};
        _params.baseId = _baseId;
        _params.title = _randomTitle;
        _params.classTypeId = _classTypeId;
        _params.count = _randomCount;
        _params.type = 1;//随机习题
        $.ajax({
            url 	 : "${mvcPath}/view/trainingExercises/saveOrUpdateBase",
            type 	 : "post",
            dataType : "json",
            data 	 : _params,
            success  : function(data, textStatus, XMLHttpResponse) {
                if(data.result){
                    loadingPage('/view/trainingExercises');
                }else{
                    if(data.status == 1038){
                        $(".increaseBonus").fadeOut();
                        $('#randomTitle').val('');
                        $('#randomCount').val('');
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
    }

</script>