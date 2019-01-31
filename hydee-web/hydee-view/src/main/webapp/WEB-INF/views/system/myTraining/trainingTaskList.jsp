<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include.inc.jsp"%>
<div class="right-main">
    <div class="table-height30 marginTop20">
        <c:if test="${!sessionUser.isSysAdmin}">
            <a onclick="loadingPage('/view/trainTask/addTrainTask')" class=" width80 fakeBtn-hover-crimson floatLeft" >新建任务</a>
        </c:if>
        <div class="floatRight">
            <label class="positionRelative width250" id="searchParams">
                <input type="text" id="title" class="labelInput width250"  autocomplete="off" placeholder="按标题搜索">
                <input type="button" onclick="searchTask()" class="btn-hover-blue searchBtn" value="搜索">
            </label>
        </div>
        <div class="clear"></div>
    </div>
    <ul class="ul-table-tHeader marginTop20 paddingLR20">
        <li class="width140">标题</li>
        <li class="width120">创建时间</li>
        <!--<li class="width60">习题</li>-->
        <li class="width80">指定连锁</li>
        <li class="width80">参与连锁</li>
        <li class="width80">参加人数</li>
        <li class="width80">点赞</li>
        <li class="width80">评论</li>
        <li class="width90">状态</li>
        <li class="width80">悬赏金额</li>
        <!--<li class="width100">备注</li>-->
        <li class="width80 textAlignR">操作</li>
        <div class="clear"></div>
    </ul>

    <c:forEach items="${reList}" var="item">
        <ul class="ul-table-tBody tableWithoutLine100 ">
            <li class="width140" >${item.title}</li>
            <li class="width120" >${item.createTimeFmt}</li>
            <li class="width80">${item.customerCount}</li>
            <li class="width80">${item.joinCustomerCount}</li>
            <li class="width80">${item.userCount}</li>
            <li class="width80">${item.likeNum}</li>
            <li class="width80">${item.commentCount}</li>
            <li class="width90">
                <c:choose>
                    <c:when test = "${item.status == 1}">草稿</c:when>
                    <c:when test = "${item.status == 2}">即将开始</c:when>
                    <c:when test = "${item.status == 3}">进行中</c:when>
                    <c:when test = "${item.status == 4}">已结束</c:when>
                </c:choose>
            </li>
            <li class="width80">${item.moneyReward}</li>
            <li class="width80 textAlignR" rid="${item.taskId}" data="${item.status}">
                <c:choose>
                    <c:when test = "${sessionUser.isSysAdmin}">
                        <a class="operation overView">查看</a>
                    </c:when>
                    <c:otherwise>
                        <c:if test = "${item.status == 1}">
                            <a class="operation taskFabu">发布</a>
                            <a onclick="editTask(${item.taskId},1)" class="operation">修改</a>
                            <a class="operation deleteTask">删除</a>
                        </c:if>
                        <c:if test = "${item.status == 2}">
                            <%--<a onclick="editTask(${item.taskId},1)" class="operation">修改</a>--%>
                            <a class="operation deleteTask">删除</a>
                        </c:if>
                        <c:if test = "${item.status == 3}">
                            <a class="operation addMoney">增加奖金</a>
                        </c:if>
                        <c:if test = "${item.status == 4}">
                            <a class="operation " onclick="editTask(${item.taskId},3)" >重新发布</a>
                        </c:if>
                        <a class="operation overView">查看</a>
                    </c:otherwise>
                </c:choose>


            </li>
            <div class="clear"></div>
        </ul>
    </c:forEach>
    <div class="tcdPageCode" count="${page.totalResult}" page="${page.currentPage}" size="${page.showCount}" form="/view/trainTask" filter="#searchParams"></div>
    <div class="clear"></div>
</div>
<!--遮罩层-->
<div class="shadowWhite displayNone trainingDetail"><!--查看详情-->
    <div class="contentWrapper">
        <div class="content">
            <input type="hidden" id="showTaskId" value=""/>
            <input type="hidden" id="showStatus" value=""/>
            <div class="detailTitle">培训任务详情</div>
            <div class="div-ui-divide marginRight10"><span class="span-ui-dot"></span><span>基本信息</span></div>
            <div class="marginRight10">
                <ul class="ul-info table-height30">
                    <li class="width80 textAlignR marginRight20" >标题</li>
                    <li class="width170" id="taskTitle"></li>
                    <div class="clear"></div>
                </ul>
                <ul class="ul-info heightAuto vertical-align-top">
                    <li class="width80 textAlignR marginRight20">缩略图</li>
                    <li>
                        <ul class="ul-thumbnail" style="padding-left:10px;padding-top: 15px" id="taskThumbnail">

                        </ul>
                    </li>
                    <div class="clear"></div>
                </ul>
                <ul class="ul-info heightAuto vertical-align-top">
                    <li class="width80 textAlignR marginRight20">指定连锁</li>
                    <li>
                        <ul class="ul-multipleShop max-height" style="padding-left:10px;padding-top: 15px" id="taskCustomer">
                        </ul>
                    </li>
                    <div class="clear"></div>
                </ul>
                <ul class="ul-info table-height30">
                    <li class="width80 textAlignR marginRight20">开始时间</li>
                    <li class="width170" id="taskStartTime"></li>
                    <div class="clear"></div>
                </ul>
                <ul class="ul-info table-height30">
                    <li class="width80 textAlignR marginRight20">结束时间</li>
                    <li class="width170" id="taskEndTime"></li>
                    <div class="clear"></div>
                </ul>

                <ul class="ul-info table-height30">
                    <li class="width80 textAlignR marginRight20">赏金总额</li>
                    <li class="width170" id="taskMoneyReward"></li>
                    <div class="clear"></div>
                </ul>
                <ul class="ul-info table-height30">
                    <li class="width80 textAlignR marginRight20">佣金</li>
                    <li id="taskMoney"></li>
                    <div class="clear"></div>
                </ul>
                <ul class="ul-info table-height30">
                    <li class="width80 textAlignR marginRight20">分档奖励/人</li>
                    <li class="width170" ><span id="taskAwardLow">1</span>元（正确率1%-59%）</li>
                    <li class="width170" ><span id="taskAwardMiddle">3</span>元（正确率60-99%）</li>
                    <li class="width170" ><span id="taskAwardHigh">5</span>元（正确率100%）</li>
                    <div class="clear"></div>
                </ul>
            </div>
            <div class="div-ui-divide marginRight10"><span class="span-ui-dot"></span><span>课件内容</span></div>
            <ul class="ul-info table-height30">
                <li class="width80 textAlignR marginRight20">课件标题</li>
                <li class="width420" title="螺旋藻的作用到底是什么呢？" id="classTitle">螺旋藻的作用到底是什么呢？</li>
                <div class="clear"></div>
            </ul>
            <ul class="ul-info heightAuto vertical-align-top">
                <li class="width80 textAlignR marginRight20">课件内容</li>
                <li class="heightAuto" id="classDetailTitle">
                    <%--<img class="img352_144_cc">--%>
                </li>
                <div class="clear"></div>
            </ul>
            <div class="div-ui-divide marginRight10"><span class="span-ui-dot"></span><span>配套练习题</span></div>
            <div id="questions"></div>
        </div>
        <a   class="shadowEdit" style="display: block"></a>
        <div class="shadowClose" id="shadowCloseDetail"></div>
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
        </div>
        <div class="btn-wraper width360">
            <input type="button" value="确定" class="btn-hover-crimson marginRight10 btnForSure">
            <input type="button" value="取消" class="btn-line-crimson btnCancel">
        </div>
    </div>
</div>
<div class="shadowWhite displayNone insertBonus"><!--增肌奖金-->
    <div class="contentWrapper">
        <div class="content">
            <input type="hidden" id="bonusTaskId" value=""/>
            <div class="detailTitle">增加奖金</div>
            <div class="div-line"></div>
            <ul class="ul-info table-height30">
                <li class="width80">赏金余额</li>
                <li class="width120" id="showMoneyReward">53元</li>
                <div class="clear"></div>
            </ul>
            <ul class="ul-info table-height30">
                <li class="width80">增加奖金</li>
                <li class="width180"><input type="text" id="addMoneyTxt" class="width100"><span class="marginLeft10">元</span></li>
                <div class="clear"></div>
            </ul>
            <ul class="ul-info table-height30">
                <li class="width180 marginLeft80 font-color-666" >最多可增加奖金<span id="limitAddMoney"></span>元</li>
                <div class="clear"></div>
            </ul>
            <ul class="ul-info table-height30 font-color-crimson">
                <li class="width80">可用余额</li>
                <li class="width120" id="usableMoney"></li>
                <div class="clear"></div>
            </ul>
        </div>
        <div class="btn-wraper width360">
            <input type="button" value="确定" class="btn-hover-crimson marginRight10 saveBonus">
            <input type="button" value="取消" class="btn-line-crimson cancelBonus">
        </div>
    </div>
</div>
<div class="clear"></div>
<div class="bgShow"></div>
<script type="text/javascript">
    var _limit;
    function searchTask(){
        var _title = $("#title").val();
        console.log(_title);
        var _params={};
        _params.title=_title;
        loadingPage("/view/trainTask",_params);
    }

    function editTask(taskId,type){
        var _param = {};
        _param.taskId = taskId;
        _param.type = type;
        loadingPage("/view/trainTask/addTrainTask",_param);
    }

    $(".taskFabu").bind("click",function(){
        var _rid = $(this).parent().attr("rid");
        console.log(_rid);
        $.hd.alertMsg.confirm("发布","发布后将不允许修改，请确认?",function(){
            $.ajax({
                url 	 : "${mvcPath}/view/trainTask/issueTask",
                type 	 : "post",
                dataType : "json",
                data 	 : {taskId:_rid,status:2},
                success  : function(data, textStatus, XMLHttpResponse) {
                    if(data.result){
                        $.hd.alertMsg.info("发布成功！");
                        loadingPage('/view/trainTask');
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

    $(".shadowEdit").bind("click",function(){
        editTask($("#showTaskId").val(),1);
//        $(".redistribution,.bgShow").fadeOut();
    });

    //新增奖金
    $(".addMoney").bind("click",function() {
        var _rid = $(this).parent().attr("rid");
        $('#bonusTaskId').val(_rid);
        $.ajax({
            url 	 : "${mvcPath}/view/trainTask/showAddMoney",
            type 	 : "post",
            dataType : "json",
            data 	 : {taskId:_rid},
            success  : function(data, textStatus, XMLHttpResponse) {
                var _companyAccount = data.companyAccount;
                var _companyTrainTask = data.companyTrainTask;
                var _availableBalance = _companyAccount.availableBalance;
                var _moneyReward = _companyTrainTask.moneyReward;
                _limit = _availableBalance - _moneyReward;
                var _showMoneyReward = $('#showMoneyReward');
                var _limitAddMoney = $('#limitAddMoney');
                var _usableMoney = $('#usableMoney');
                _showMoneyReward.html(_moneyReward);
                _limitAddMoney.html(_limit);
                _usableMoney.html(_availableBalance);
                $('.insertBonus,.bgShow').fadeIn();

            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                console.log(textStatus);
            }
        });

    });

    //增加奖金
    $(".saveBonus").bind("click",function() {
        var _addMoneyTxt = $('#addMoneyTxt');
        var _taskId = $('#bonusTaskId').val();
        if(_addMoneyTxt.val() > _limit){
            $.hd.tips(_addMoneyTxt,"增加的奖金不能超过可用余额！");
            return ;
        }
        console.log("${mvcPath}/view/trainTask/addMoney");
        $.ajax({
            url 	 : "${mvcPath}/view/trainTask/addMoney",
            type 	 : "post",
            dataType : "json",
            data 	 : {taskId:_taskId,residueMoney:_addMoneyTxt.val()},
            success  : function(data, textStatus, XMLHttpResponse) {
                console.log(data);
                if(data.result){
                    $.hd.alertMsg.info("添加成功！");
                    $('.insertBonus,.bgShow').fadeOut();
                    loadingPage('/view/trainTask');
                }else{
                    $.hd.alertMsg.error("服务器繁忙,稍后请重试!");
                }
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                console.log(textStatus);
            }
        });

    });
    $(".cancelBonus").bind("click",function() {
        $('.insertBonus,.bgShow').fadeOut();
    });

    $("#shadowCloseDetail").bind("click",function(){
        $('.trainingDetail,.bgShow').fadeOut();
    });

    $(".overView").bind("click",function(){
        $('.shadowEdit').show();
        var _rid = $(this).parent().attr("rid");
        var _status = $(this).parent().attr("data");
        var _taskTitle = $("#taskTitle");
        var _taskThumbnail = $("#taskThumbnail");
        var _taskCustomer = $("#taskCustomer");
        var _taskStartTime = $("#taskStartTime");
        var _taskEndTime = $("#taskEndTime");
        var _taskMoneyReward = $("#taskMoneyReward");
        var _taskMoney = $("#taskMoney");
        var _taskAwardLow = $("#taskAwardLow");
        var _taskAwardMiddle = $("#taskAwardMiddle");
        var _taskAwardHigh = $("#taskAwardHigh");
        var _classTitle = $("#classTitle");
        var _classDetailTitle = $("#classDetailTitle");
        var _questions = $("#questions");
        $("#showTaskId").val(_rid);
        if(3 == _status || 4 == _status || 2 == _status || ${sessionUser.isSysAdmin}){
            $('.shadowEdit').hide();
        }
        $.ajax({
            url 	 : "${mvcPath}/view/trainTask/showTaskDetail",
            type 	 : "post",
            dataType : "json",
            data 	 : {taskId:_rid},
            success  : function(data, textStatus, XMLHttpResponse) {
                _questions.empty();
                _taskThumbnail.empty();
                _taskCustomer.empty();
                var _exercisesList = data.exercisesList;
                var _companyTrainClass = data.companyTrainClass;
                var _companyTrainTask = data.companyTrainTask;
                var _taskThumbnailList = data.taskThumbnailList;
                var _taskCustomerList = data.taskCustomerList;
                _taskTitle.html(_companyTrainTask.title);
                //缩略图
                var _thumbnailHtml = '';
                $.each(_taskThumbnailList, function(i,value){
                    _thumbnailHtml += '<li class=""> <img src='+value.imgUrl+'></li>';
                });
                console.log(_taskThumbnailList.length);
                if(_taskThumbnailList.length <= 0){
                    _thumbnailHtml += '<li class=""> <img src="<c:url value='/img/yiyaokaoshi.jpg'/>"></li>';
                }
                _thumbnailHtml += '<div class="clear"></div>';
                _taskThumbnail.append(_thumbnailHtml);

                //指定连锁
                var _customerHtml = '';
                $.each(_taskCustomerList, function(i,value){
                    _customerHtml += '<li title='+value.customerName+'>'+value.customerName+'</li>';
                });
//                _customerHtml += '<div id="all" class="all">查看全部 ></div>';
                _customerHtml += '<div class="clear"></div>';
                _taskCustomer.append(_customerHtml);

                _taskStartTime.html(_companyTrainTask.startTimeFmt);
                _taskEndTime.html(_companyTrainTask.endTimeFmt);
                _taskMoneyReward.html(_companyTrainTask.moneyReward);
                _taskAwardLow.html(_companyTrainTask.awardLow);
                _taskAwardMiddle.html(_companyTrainTask.awardMiddle);
                _taskAwardHigh.html(_companyTrainTask.awardHigh);

                var _commissionType = _companyTrainTask.commissionType;
                var _moneyTypeHtml;
                if(1 == _commissionType){
                    _moneyTypeHtml = '每领一次红包，连锁企业获得培训基金'+ _companyTrainTask.trainFund +'元，平台服务费'+ _companyTrainTask.serveCharge +'元';
                }else{
                    _moneyTypeHtml = '用户领取红包，按红包金额百分比抽取佣金，连锁企业获得培训基金'+ _companyTrainTask.trainFund +'%，平台服务费'+ _companyTrainTask.serveCharge +'%';
                }
                _taskMoney.html(_moneyTypeHtml);

                _classTitle.html(_companyTrainClass.title);
                var _content = _companyTrainClass.content;
                if(1 == _companyTrainClass.contentType){
                    $.hd.pdfFile(_content,_classDetailTitle);
                }else{
                    _classDetailTitle.html(_companyTrainClass.content);
                }
                var questionHtml;
                $.each(_exercisesList, function(index,value){
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

                $('.trainingDetail,.bgShow').fadeIn();

            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
            }
        });
    });

    //删除
    $(".deleteTask").bind("click",function(){
        var _rid = $(this).parent().attr("rid");
        $.hd.alertMsg.confirm("删除","确认删除这个任务吗?",function(){
            $.ajax({
                url 	 : "${mvcPath}/view/trainTask/deleteTask",
                type 	 : "post",
                dataType : "json",
                data 	 : {taskId:_rid},
                success  : function(data, textStatus, XMLHttpResponse) {
                    if(data.result){
                        $.hd.alertMsg.info("删除成功！");
                        loadingPage('/view/trainTask');
                    }else{
                        $.hd.alertMsg.error("服务器繁忙,稍后请重试!");
                    }

                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    console.log(textStatus);
                }
            });
        });
    });

</script>