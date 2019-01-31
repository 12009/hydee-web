<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include.inc.jsp"%>
<div class="right-main">
    <div class="table-height30 marginTop20">
        <c:if test="${!sessionUser.isSysAdmin}">
            <a  onclick="loadingPage('/view/trainClass/addTrainClass')" class=" width80 fakeBtn-hover-crimson floatLeft" >新建课件</a>
        </c:if>
        <div class="floatRight">
            <label class="positionRelative width250" id="searchParams">
                <input type="text" id="title" class="labelInput width250" autocomplete="off">
                <input type="button" class="btn-hover-blue searchBtn" onclick="search()" value="搜索">
            </label>
        </div>
        <div class="clear"></div>
    </div>
    <ul class="ul-table-tHeader marginTop30 paddingLR20">
        <li class="width60">ID</li>
        <c:if test = "${sessionUser.isSysAdmin}">
            <li class="width80">所属厂商</li>
        </c:if>
        <li class="width120">标题</li>
        <li class="width120">创建时间</li>
        <li class="width70">习题</li>
        <li class="width70">阅读量</li>
        <li class="width120">关联培训任务</li>
        <li class="width80">状态</li>
        <li class="width100">备注</li>
        <li class="width90 textAlignR">操作</li>
        <div class="clear"></div>
    </ul>
    <div class="dataLoading"></div>
    <c:forEach items="${reList}" var="item">
        <ul class="ul-table-tBody tableWithoutLine100">
            <li class="width60">${item.classId}</li>
            <c:if test = "${sessionUser.isSysAdmin}">
                <li class="width80">${item.customerName}</li>
            </c:if>
            <li class="width120" >${item.title}</li>
            <li class="width120" >${item.createTimeFmt}</li>
            <li class="width70">${item.exercisesCount}</li>
            <li class="width70">${item.readNum}</li>
            <li class="width120">${item.taskCount}</li>
            <li class="width80">
                <c:choose>
                    <c:when test = "${item.status == 1}">草稿</c:when>
                    <c:when test = "${item.status == 2}">待审核</c:when>
                    <c:when test = "${item.status == 3}">进行中</c:when>
                    <c:when test = "${item.status == 4}">审核不通过</c:when>
                    <c:when test = "${item.status == 5}">已结束</c:when>
                </c:choose>
            </li>
            <li class="width100">${item.remark == null ? '--' : item.remark}</li>
            <li class="width90 textAlignR" rid="${item.classId}" data="${item.status}">
                <c:choose>
                    <c:when test = "${sessionUser.isSysAdmin}">
                        <c:if test = "${item.status != 4 || item.status != 5}">
                            <a class="operation overView">查看</a>
                        </c:if>
                        <c:if test = "${item.status == 2}">
                            <a onclick = "checkPass(${item.classId})" class="operation">审核通过</a>
                            <a class="operation release">审核不通过</a>
                        </c:if>
                    </c:when>
                    <c:otherwise>
                        <c:if test = "${item.status == 1}">
                            <a class="operation fabu">发布</a>
                            <a onclick="editClass(${item.classId},1)" class="operation">修改</a>
                            <a class="operation overView">查看</a>
                        </c:if>
                        <c:if test = "${item.status == 2}">
                            <a class="operation overView">查看</a>
                        </c:if>
                        <c:if test = "${item.status == 3}">
                            <a class="operation overView">查看</a>
                        </c:if>
                        <c:if test="${item.status == 4}">
                            <a onclick="editClass(${item.classId},2)" class="operation">重新发布</a>
                        </c:if>
                        <c:if test="${item.status != 5}">
                            <a class="operation deleteTraining">删除</a>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </li>
            <div class="clear"></div>
        </ul>
    </c:forEach>
    <div class="tcdPageCode" count="${page.totalResult}" page="${page.currentPage}" size="${page.showCount}" form="/view/trainClass" filter="#searchParams"></div>
    <div class="clear"></div>
</div>
<!--遮罩层-->
<div class="shadowWhite displayNone trainingDetail" style="width: 1000px;height: 800px"><!--查看详情-->
    <div class="contentWrapper">
        <div class="content detailContent">
            <input type="hidden" id="showClassId" value=""/>
            <input type="hidden" id="showStatus" value=""/>
            <div class="detailTitle">培训课件详情</div>
            <div class="div-ui-divide marginRight10"><span class="span-ui-dot"></span><span>课件内容</span></div>
            <ul class="ul-info heightAuto">
                <li class="marginRight20">
                    <div class="table-height30 classDetailTitle">这里存放文本呢信息，标题一类的</div>
                    <div class="classDetailContent heightAuto" >

                        <%--<ul class="roundabout" id="myroundabout">--%>
                            <%--<!-- <li><img src="img/slide1.jpg"></li>--%>
                            <%--<li><img src="img/slide2.jpg"></li>--%>
                            <%--<li><img src="img/slide3.jpg"></li>--%>
                            <%--<li><img src="img/slide4.jpg"></li>--%>
                            <%--<li><img src="img/slide5.jpg"></li>  -->--%>
                        <%--</ul>--%>
                    </div>
                </li>
                <div class="clear"></div>
            </ul>
            <div class="div-ui-divide marginRight10"><span class="span-ui-dot"></span><span>配套练习</span></div>
            <div id="questions"></div>
        </div>
        <a class="shadowEdit" style="display: block"></a><!--这个编辑按钮只有在这个培训任务非进行中，非结束状态才出现-->
        <div class="shadowClose"></div>
    </div>
</div>

<div class="shadowWhite displayNone fabu_1"><!--发布-->
    <div class="contentWrapper">
        <div class="content">
            <div class="detailTitle">发布</div>
            <div class="div-line"></div>
            <ul class="ul-info table-height30">
                <li style="margin-top:80px; margin-left:70px; font-size:16px;">发布后将不允许修改，请确认？</li>
            </ul>
            <input type="hidden" id="classId" value=""/>
        </div>
        <div class="btn-wraper width360">
            <input type="button" value="确定" class="btn-hover-crimson marginRight10 btnFabuForSure">
            <input type="button" value="取消" class="btn-line-crimson btnFabuCancel">
        </div>
    </div>
</div>

<div class="shadowWhite displayNone redistribution"><!--审核不通过-->
    <div class="contentWrapper">
        <div class="content">
            <div class="detailTitle">审核不通过</div>
            <div class="div-line"></div>
            <ul class="ul-info table-height30">

                <li><textarea class="wenbenyu" placeholder="请输入审核不通过的原因..."></textarea>
                    <div class="clear"></div>
            </ul>
            <input type="hidden" id="redistClassId" value=""/>
        </div>
        <div class="btn-wraper width360">
            <input type="button" value="确定" class="btn-hover-crimson marginRight10 btnRedist">
            <input type="button" value="取消" class="btn-line-crimson btnRedistCancel">
        </div>
    </div>
</div>
</div>
<div class="bgShow"></div>
<div class="clear"></div>

<script type="text/javascript">
    var _num;
    $(document).ready(function() {

//        $(".btnFabuCancel").bind("click",function(){
//            $(".fabu_1,.bgShow").fadeOut();
//        });
//        $(".btnFabuForSure").bind("click",function(){
//            $(".fabu_1,.bgShow").fadeOut();
//            var _classId = $("#classId").val();
//            var _param = {};
//            _param.status = 2;//发布将状态变为待审核
//            _param.classId = _classId;
//            updateClass(_param,"发布成功");
//        });

        $(".release").bind("click",function(){
            var _rid = $(this).parent().attr("rid");
            $("#redistClassId").val(_rid);
            $(".redistribution,.bgShow").fadeIn();
        });
        $(".btnRedistCancel").bind("click",function(){
            $(".redistribution,.bgShow").fadeOut();
        });
        $(".btnRedist").bind("click",function(){
            var _redistClassId = $("#redistClassId").val();
    //        if(_redistClassId == null ){
    //            $.hd.tips($("#redistClassId"), "");
    //        }
            $(".redistribution,.bgShow").fadeOut();
            var _remark=$(".wenbenyu").val();
            var _param={};
            _param.status = 4;//审核不通过将状态变为审核不通过
            _param.classId = _redistClassId;
            _param.remark=_remark;
            updateClass(_param,"操作成功");
        });

        $(".shadowEdit").bind("click",function(){
            editClass($("#showClassId").val(),1);
    //        $(".redistribution,.bgShow").fadeOut();
        });

        $(".overView").bind("click",function(){
            var _rid = $(this).parent().attr("rid");
            var _status = $(this).parent().attr("data");
            $("#showClassId").val(_rid);

            console.log(${sessionUser.isSysAdmin});
            if(3 == _status || 2 == _status || ${sessionUser.isSysAdmin}){
                $(".shadowEdit").hide();
            }else{
                $(".shadowEdit").show();
            }
            $.ajax({
                url 	 : "${mvcPath}/view/trainClass/showClassDetail",
                type 	 : "post",
                dataType : "json",
                data 	 : {classId:_rid},
                success  : function(data, textStatus, XMLHttpResponse) {
                    $("#questions").empty();
                    var _exercisesList = data.exercisesList;
                    var _companyTrainClass = data.companyTrainClass;
                    $(".classDetailContent").html('');
                    $(".classDetailTitle").html(_companyTrainClass.title);
                    var _content = _companyTrainClass.content;
                    if(1 == _companyTrainClass.contentType){
                        $.hd.pdfFile(_content,$('.classDetailContent'));
                    }else{
                        $(".classDetailContent").html(_companyTrainClass.content);
                    }
                    var questionHtml;
                    $.each(_exercisesList, function(index,value){
                        console.log(index);
                        console.log(value);
                        var _type = value.type == "singleTab" ? "单选" : "多选";
                        var _index = index+1;
                        questionHtml = '<ul class="question">';
                        questionHtml += "<li>"+ _index + "、" + value.content + "(" + _type + "正确答案：" + value.answer +")</li>";
                        questionHtml += "<li>A."+ value.optionA +"</li>";
                        questionHtml += "<li>B."+ value.optionB +"</li>";
                        questionHtml += "<li>C."+ value.optionC +"</li>";
                        questionHtml += "<li>D."+ value.optionD +"</li>";
                        $("#questions").append(questionHtml);
                    });

                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                }
            });

        });

        //删除
        $(".deleteTraining").bind("click",function(){
            var _rid = $(this).parent().attr("rid");
            $.hd.alertMsg.confirm("删除","确认删除这个培训吗?",function(){
                $.ajax({
                    url 	 : "${mvcPath}/view/trainClass/deleteClass",
                    type 	 : "post",
                    dataType : "json",
                    data 	 : {classId:_rid},
                    success  : function(data, textStatus, XMLHttpResponse) {
                        console.log(data);
                        if(data.result){
                            $.hd.alertMsg.info("删除成功！");
                            loadingPage('/view/trainClass');
                        }else{
                            if(data.status == 1017){
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

        $(".fabu").bind("click",function(){
            var _rid = $(this).parent().attr("rid");
            $("#classId").val(_rid);
            $.hd.alertMsg.confirm("发布","发布后将不允许修改，请确认？",function(){
                var _classId = $("#classId").val();
                var _param = {};
                _param.status = 2;//发布将状态变为待审核
                _param.classId = _classId;
                updateClass(_param,"发布成功");
            });
        });


    });

    /**
     *审核通过
     */
    function checkPass(_classId){
        var _param={};
        _param.status=3;//审核通过将状态变为进行中
        _param.classId=_classId;
        updateClass(_param,"审核成功");
    }

    /**
     * 修改状态
     * @param param
     */
    function updateClass(param,message){
        $.ajax({
            url 	 : "${mvcPath}/view/trainClass/updateClass",
            type 	 : "post",
            dataType : "json",
            data 	 : param,
            success  : function(data, textStatus, XMLHttpResponse) {
                if(data.result){
                    $.hd.alertMsg.info(message);
                    refreshPage();
                }else{
                    $.hd.alertMsg.error("服务器繁忙,稍后请重试!");
                }
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
            }
        });
    }



    function editClass(classId,sType){
        var _param = {};
        _param.classId = classId;
        _param.sType = sType;
        loadingPage("/view/trainClass/addTrainClass",_param);
    }

    function search(){
        var _title = $("#title").val();
        console.log(_title);
        var _params={};
        _params.title=_title;
        loadingPage("/view/trainClass",_params);
    }


</script>