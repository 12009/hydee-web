<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include.inc.jsp" %>
<div class="right-main">
    <div class="right-main-top1">
        <div class="table-height30 marginTop20" id="operation">
            <a onclick="loadingPage('/view/trainingExercises/singleItemAddition')" class=" width80 fakeBtn-hover-crimson floatLeft"
               style="margin-right:10px;">单题添加</a>
            <span style="color:#c6244b;float:left;">批量导入</span>
            <span style="float:left; margin-top:12px;"><img src="${mvcPath}/img/jiantou.png"></span>
            <a onclick="downloadXlsx()" class=" width100 fakeBtn-hover-crimson floatLeft" style="margin-right:10px;">先下载模板</a>
            <a class="a-upload width100 fakeBtn-hover-crimson floatLeft" style="margin-right:10px;position: relative;"><input class="width100" style="height: 30px !important;" type="file" name="upload"/>后上传excel
            </a>
            <label style="float:left; margin-right:10px;" id="searchParams">
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
	        <input type="button" onclick="searchExercises()" class="width80 btn-hover-crimson nextPart" value="查询"> </span>
            <input type="button" onclick="deleteExercisesAll()" class="width80 btn-hover-crimson nextPart" value="批量删除" id="btnCheckAll">
            <!--<a href="#" class=" width80 fakeBtn-hover-crimson floatLeft" style="margin-right:10px;" >批量删除</a> -->
            <span style="float:right;"><a style="color:#c6244b;" onclick="loadingPage('/view/trainingExercises')">返回习题列表</a></span>
        </div>
        <div class="clear"></div>
    </div>
    <ul class="ul-table-tHeader marginTop30 paddingLR20">
        <li class="width60"><input type="checkbox" name="checkBoxAll" class="checkBoxAll"></li>
        <li class="width60">ID</li>
        <li class="width300">题目分类</li>
        <li class="width200">题目</li>
        <li class="width120">创建人</li>
        <li class="width180">创建时间</li>
        <li class="width90 textAlignR">操作</li>
        <div class="clear"></div>
    </ul>
    <c:forEach items="${reList}" var="item">
        <ul class="ul-table-tBody tableWithoutLine100">
            <li class="width60"><input type="checkbox" name="checkExercisesId" class="checkExercisesId" value="${item.exercisesId}"></li>
            <li class="width60">${item.exercisesId}</li>
            <li class="width300">${item.name}</li>
            <li class="width200" title="${item.content}">${item.content}</li>
            <li class="width120">${item.realName}</li>
            <li class="width180" title="${item.createTimeFmt}">${item.createTimeFmt}</li>
            <li class="width90 textAlignR" rid="${item.exercisesId}">
                <a class="operation overView">查看</a>
                <a onclick="editQuestion(${item.exercisesId})" class="operation">修改</a>
                <a class="operation deleteExercises">删除</a>
            </li>
            <div class="clear"></div>
        </ul>
    </c:forEach>

    <div class="tcdPageCode" count="${page.totalResult}" page="${page.currentPage}" size="${page.showCount}"
         form="/view/trainingExercises/exercisesQuestionBank" filter="#searchParams"></div>
    <div class="clear"></div>
</div>
<!--遮罩层-->
<div class="shadowWhite displayNone trainingDetail"><!--查看详情-->
    <div class="contentWrapper">
        <div class="content">
            <input type="hidden" id="detailExerciseId"/>
            <div class="detailTitle">培训习题详情</div>
            <div class="div-ui-divide marginRight10"><span class="span-ui-dot"></span><span>分类</span></div>
            <ul class="ul-info heightAuto">
                <li class="width352 marginRight20">
                    <div class="table-height30" id="detailClassType"></div>
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


<div class="bgShow"></div>
<div class="clear"></div>

<script type="text/javascript">

    $(function(){

        $('#operation').find('.a-upload input[type="file"]').bind("change",function(){
            var _file = $(this);
            var _filePath = $(this).val();
            if( _filePath.indexOf("xls")!= -1 ){
                // 上传人员名单
                var formData = new FormData();
                formData.append("attach", _file[0].files[0]);
                $.ajax({
                    url: "${mvcPath}/view/trainingExercises/importExercisesListExcel",
                    type: "POST",
                    data: formData,
                    /**
                     *必须false才会自动加上正确的Content-Type
                     */
                    contentType: false,
                    /**
                     * 必须false才会避开jQuery对 formdata 的默认处理
                     * XMLHttpRequest会对 formdata 进行正确的处理
                     */
                    processData: false,
                    success: function (rsp) {
                        if(rsp.result){
                            var _sessionId = rsp.data.sessionId;
                            if(rsp.data.error != ""){
                                $.hd.alertMsg.confirm("上传提示","文件校验失败，第["+rsp.data.error+"]行格式错误，错误行将无法保存",function(){
                                    saveExcel(_sessionId);
                                });
                            }else{
                                saveExcel(_sessionId);
                            }
                        }else{
                            if(rsp.status == 1041){
                                $.hd.alertMsg.error(rsp.errors);
                            }else{
                                $.hd.alertMsg.error("服务器繁忙,稍后请重试!");
                            }
                        }

                    }
                });
            }else{
                console.error("你所选择的文件不是正确的EXCEL文件格式!");
            }
        });

        $(".deleteExercises").bind("click",function(){
            var _rid = $(this).parent().attr("rid");
            $.hd.alertMsg.confirm("删除","确认删除这个题目吗?",function(){
                $.ajax({
                    url 	 : "${mvcPath}/view/trainingExercises/deleteExercises",
                    type 	 : "post",
                    dataType : "json",
                    data 	 : {exercisesId:_rid},
                    success  : function(data, textStatus, XMLHttpResponse) {
                        if(data.result){
                            $.hd.alertMsg.info("删除成功！");
                            loadingPage('/view/trainingExercises/exercisesQuestionBank');
                        }else{
                            if(data.status == 1037){
                                $.hd.alertMsg.error(data.errors,"删除提示");
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

        //查看
        $(".overView").bind("click",function(){
            var _rid = $(this).parent().attr("rid");
            $("#detailExerciseId").val(_rid);

            if(${sessionUser.isSysAdmin}){
                $(".shadowEdit").hide();
            }else{
                $(".shadowEdit").show();
            }
            $.ajax({
                url 	 : "${mvcPath}/view/trainingExercises/showDetailExercise",
                type 	 : "post",
                dataType : "json",
                data 	 : {exerciseId:_rid},
                success  : function(data, textStatus, XMLHttpResponse) {
                    $("#questions").empty();
                    var _trainExercises = data.data.trainExercises;
                    $("#detailClassType").html(_trainExercises.name);
                    var questionHtml;
                    var _type = _trainExercises.type;
                    var _chooseType
                    if(_type.indexOf('judgeTab') != -1){
                        _chooseType = '判断';
                    }else if(_type.indexOf('singleTab') != -1){
                        _chooseType = '单选';
                    }else{
                        _chooseType = '多选';
                    }
                    questionHtml = '<ul class="question">';
                    questionHtml += "<li>"+ _index + "、" + _trainExercises.content + "(" + _chooseType + "正确答案：";
                    if(_type.indexOf('judgeTab') != -1){
                        if(_trainExercises.answer.indexOf("A") != -1){
                            questionHtml += '正确)</li>';
                        }else if(_trainExercises.answer.indexOf("B") != -1){
                            questionHtml += '错误)</li>'
                        }
                    }else{
                        questionHtml += _trainExercises.answer +")</li>"
                        $.each(_trainExercises.optionsList, function(i,v){
                            questionHtml += "<li>"+v.optionNo+"."+ v.content +"</li>";
                        });
                    }

                    questionHtml += '</ul>';
                    $("#questions").append(questionHtml);

                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                }
            });

        });

        //全选
        $('.checkBoxAll').bind("click",function(){
            if ($(this).attr("checked")) {
                $(".checkExercisesId").attr("checked", true);
            } else {
                $(".checkExercisesId").attr("checked", false);
            }
        });

        //查看时候点击修改
        $(".shadowEdit").bind("click",function(){
            editQuestion($("#detailExerciseId").val());
        });

    });

    function editQuestion(_rid){
        var _param = {};
        _param.exercisesId = _rid;
        loadingPage("/view/trainingExercises/singleItemAddition",_param);
    }

    function searchExercises(){
        var _param = {};
        _param.classTypeId = $('.classTypeId').val();
        loadingPage("/view/trainingExercises/exercisesQuestionBank",_param);
    }

    function downloadXlsx(){
        window.open("${mvcPath}/download/exercisesUpload.xlsx");
    }

    function saveExcel(_sessionId){
        $.ajax({
            url 	 : "${mvcPath}/view/trainingExercises/saveExercisesListExcel",
            type 	 : "post",
            dataType : "json",
            data 	 : {sessionId:_sessionId},
            success  : function(data, textStatus, XMLHttpResponse) {
                if(data.result){
                    $.hd.alertMsg.info("上传成功！");
                    loadingPage('/view/trainingExercises/exercisesQuestionBank');
                }
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                console.log(textStatus);
            }
        });
    }

    function deleteExercisesAll(){
        var exercisesId = [];
        $("[name='checkExercisesId'][checked]").each(function(){
            exercisesId.push($(this).val());
        });
        $.hd.alertMsg.confirm("删除","确认删除这个题目吗?",function(){
            $.ajax({
                url 	 : "${mvcPath}/view/trainingExercises/deletExercisesList",
                type 	 : "post",
                dataType : "json",
                data 	 : {exercisesIds:exercisesId},
                success  : function(data, textStatus, XMLHttpResponse) {
                    if(data.result){
                        if(data.data != ""){
                            $.hd.alertMsg.info(data.data+"编号的题目已关联习题！不支持删除！");
                        }else{
                            $.hd.alertMsg.info("删除成功！");
                        }
                        loadingPage('/view/trainingExercises/exercisesQuestionBank');
                    }
                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    console.log(textStatus);
                }
            });
        });

    }

</script>