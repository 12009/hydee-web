<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include.inc.jsp"%>
<div class="right-main paddingLR40">
    <div class="div-ui-divide marginRight10 marginTop20"><span class="span-ui-dot"></span><span>任务基本信息</span></div>
    <form id="addTask" class="red">
        <div class="marginRight10">
            <div class="task-main">
                <ul class="ul-info table-height30">
                    <li class="width80 textAlignR marginRight20">任务标题</li>
                    <li>
                        <label class="input">
                            <input type="hidden" id="taskId" value="${companyTrainTask.taskId}"/>
                            <input type="hidden" id="taskCommissionType" value='${companyTrainTask.commissionType}'/>
                            <input type="hidden" id="taskAndClassId" value='${companyTrainClassTask.classId}'/>
                            <input type="hidden" id="taskCustomer" value='${taskCustomerList}'/>
                            <input type="hidden" id="taskThumbnail" value='${taskThumbnailList}'/>
                            <input type="hidden" id="companyUrl" value='${companyUrl}'/>
                            <input type="hidden" id="type" value='${type}'/>
                            <input type="hidden" id="availableBalance" value='${availableBalance}'/>
                            <label class="inputLabel" for="title" id="titleNameLabel">10字以内</label>
                            <input type="text" id="title" value="${companyTrainTask.title}" ctype="required" class="labelInput width200" maxlength="10" placeholder="10字以内" autocomplete="off">
                        </label>
                    </li>
                    <div class="clear"></div>
                </ul>
                <ul class="ul-info ">
                    <li class="width80 textAlignR marginRight20">缩略图</li>
                    <li class="littleFileShow" id="fileShow">
                        <div class="thumbnail fileUpload"  value="" defualt="${mvcPath}/img/upload.png" ></div>
                    </li>
                    <div class="clear"></div>
                    <%--<li><a class="font-color-blue">点击上传</a></li>--%>
                </ul>
                <ul class="ul-info heightAuto vertical-align-top">
                    <li class="width80 textAlignR marginRight20">指定连锁</li>
                    <li>  <a id="addShop" class="font-color-blue">点击添加</a></li>
                    <li>
                        <ul class="ul-multipleShop-Upload max-height" id="addShopMain">
                            <!--<li dataid="3" title="测试连锁三"><span class="shopName">测试连锁三</span><span class="shopDelete">×</span></li>-->
                            <!--<li dataid="3" title="测试连锁三"><span class="shopName">测试连锁三</span><span class="shopDelete">×</span></li>-->
                            <!--<li dataid="3" title="测试连锁三"><span class="shopName">测试连锁三</span><span class="shopDelete">×</span></li>-->
                            <div id="all" class="all displayNone">查看全部 ></div>
                            <div class="clear"></div>
                        </ul>
                    </li>
                    <div class="clear"></div>
                </ul>
                <ul class="ul-info heightAuto vertical-align-top">
                    <li class="width80 textAlignR marginRight20">开始时间</li>
                    <li>
                        <label class="input">
                            <input type="text" value="${companyTrainTask.startTimeFmt}" class="labelInput width200" ctype="date" tabindex="0" id="startTime">
                        </label>
                    </li>
                    <div class="clear"></div>
                </ul>
                <ul class="ul-info heightAuto vertical-align-top">
                    <li class="width80 textAlignR marginRight20">结束时间</li>
                    <li>
                        <label class="input">
                            <input type="text" value="${companyTrainTask.endTimeFmt}" class="labelInput width200" ctype="date" tabindex="0" id="endTime">
                        </label>
                    </li>
                    <div class="clear"></div>
                </ul>
                <ul class="ul-info table-height30">
                    <li class="width80 textAlignR marginRight20">赏金总额</li>
                    <li class="width170">
                        <label class="input">
                            <label class="inputLabel" for="moneyReward" id="sumMLabel"></label>
                            <input type="text" value="${companyTrainTask.moneyReward}" id="moneyReward" ctype="^\d{3,}.{0,1}\d{0,2}$" class="labelInput width120 " autocomplete="off">
                        </label>
                        <span class="marginLeft10">元</span>
                    </li>
                    <li>&nbsp;（最低100元）</li>
                    <div class="clear"></div>
                </ul>
                <ul class="ul-info table-height30">
                    <li class="width80 textAlignR marginRight20">佣金</li>

                    <li style="margin-right:15px;">
                        <label>
                            <input type="radio" name="brokerage"  checked="checked" value="1"/>
                        </label>
                        每领一次红包，连锁企业获得培训基金
                    </li>
                    <li class="width110">
                        <label class="input">
                            <input type="text" id="trainFund" value="${companyTrainTask.trainFund}" class="labelInput width50 " autocomplete="off" placeholder="0.5">
                        </label>
                        <span class="marginLeft10">元，</span>
                    </li>
                    <li style="margin-right:5px;">平台服务费</li>
                    <li class="width110">
                        <label class="input" id="serveCharge">
                            ${serveCharge}元
                        </label>
                    </li>
                    <div class="clear"></div>
                </ul>
                <ul class="ul-info table-height30">
                    <li class="width80 null marginRight20"></li>
                    <li style="margin-right:5px;">
                        <input type="radio" name="brokerage" value="2"/>
                        用户领取红包，按红包金额百分比抽取佣金，连锁企业获得培训基金
                    </li>
                    <li class="width110">
                        <label class="input">
                            <label class="inputLabel" for="trainFundPercent" id="sumMLabel2"></label>
                            <input type="text" id="trainFundPercent" value="${companyTrainTask.trainFund}" class="labelInput width50 " autocomplete="off" placeholder="1">
                        </label>
                        <span class="marginLeft10">%，</span>
                    </li>
                    <li style="margin-right:5px;">平台服务费</li>
                    <li class="width110">
                        <label class="input" id="serveChargePercent">
                            ${serveChargePercent}%
                        </label>
                    </li>
                    <div class="clear"></div>
                </ul>
                <ul class="ul-info table-height30">
                    <li class="width80 null marginRight20"></li>
                    <li class="font-color-c6244b"> 注：参与考试人员根据答案准确率领取红包金额</li>
                    <div class="clear"></div>
                </ul>

                <ul class="ul-info table-height30">
                    <li class="width80 textAlignR marginRight20">分档奖励</li>
                    <li class="width170">
                        <label class="input">
                            <label class="inputLabel" for="awardLow" id="0nePLabel">确率1%-59%</label>
                            <input type="text" id="awardLow" value="${companyTrainTask.awardLow}" ctype="double" class="labelInput width120" placeholder="正确率1%-59%" autocomplete="off">
                        </label>
                        <span class="marginLeft10">元</span>
                    </li>
                    <div class="clear"></div>
                </ul>

                <ul class="ul-info table-height30">
                    <li class="width80 null marginRight20"></li>
                    <li class="width170">
                        <label class="input">
                            <label class="inputLabel" for="awardMiddle" id="SixtyPLabel">正确率60-99%</label>
                            <input type="text" id="awardMiddle" value="${companyTrainTask.awardMiddle}" ctype="double" class="labelInput width120" placeholder="正确率60-99%" autocomplete="off">
                        </label>
                        <span class="marginLeft10">元</span>
                    </li>
                    <div class="clear"></div>
                </ul>

                <ul class="ul-info table-height30">
                    <li class="width80 null marginRight20"></li>
                    <li class="width170">
                        <label class="input">
                            <label class="inputLabel" for="awardHigh" id="allPLabel">正确率100%</label>
                            <input type="text" id="awardHigh" value="${companyTrainTask.awardHigh}" ctype="double" class="labelInput width120" placeholder="正确率100%" autocomplete="off">
                        </label>
                        <span class="marginLeft10">元</span>
                    </li>
                    <div class="clear"></div>
                </ul>

            </div>
        </div>
    </form>


    <div class="div-ui-divide marginRight10"><span class="span-ui-dot"></span><span>培训课件</span></div>
    <div class="marginTop20 marginBottom20">
        <span class="table-height30  floatLeft marginTop-1">当前选择的是:</span>
        <span class="table-height30  selectedTraining"></span>
        <div class="floatRight marginRight10">
            <label class="positionRelative width250">
                <input type="text" class="labelInput width250" id="searchTxt" autocomplete="off" placeholder="按标题搜索">
                <input type="button" onclick="searchClass()" class="btn-hover-blue searchBtn" id="searchBtn" value="搜索">
            </label>
        </div>
        <div class="clear"></div>
    </div>
    <div id="classDiv">
        <ul class="ul-info heightAuto trainingOption" id="classUl">
            <li></li>
            <c:forEach items="${companyTrainClassList}" var="trainBase">
                <li class="width290 marginRight10 marginBottom10" title="${trainBase.title}">
                    <label>
                        <input type="radio" name="question" class="question" data="${trainBase.title}" value="${trainBase.classId}" value="radio">${trainBase.title}
                    </label>
                </li>
            </c:forEach>
            <div class="clear"></div>
        </ul>
    </div>

    <div class="btn-noPosition marginTop20 ">
        <input type="button" onclick="saveTask()" class="btnForSure btn-hover-crimson marginRight10 width80 btn" value="保存">
        <a onclick="loadingPage('/view/trainTask')"><input type="button" class="btnCancel btn-line-crimson width80" value="取消"></a>
    </div>
</div>
<!--遮罩层-->
<div class="shadowWhite displayNone trainingDetail"><!--查看详情-->
    <div class="contentWrapper">
        <div class="content">
            <div class="detailTitle">培训课件详情</div>
            <div class="div-ui-divide marginRight10"><span class="span-ui-dot"></span><span>课件内容</span></div>
            <ul class="ul-info heightAuto">
                <li class="marginRight20">
                    <div class="table-height30 taskDetailClass">这里存放文本呢信息，标题一类的</div>
                    <div class="taskDetailContent heightAuto" ></div>
                </li>
                <div class="clear"></div>
            </ul>
            <div class="div-ui-divide marginRight10"><span class="span-ui-dot"></span><span>配套练习</span></div>
            <div id="taskQuestions"></div>

        </div>
        <div class="shadowClose"></div>
    </div>
</div>

<div class='chooseProduce'>
    <div class='cProduceHeader'>
        <div class='cProduceHeaderLeft floatLeft'>指定连锁</div>
        <div class='cProduceHeaderRight floatRight'><input type='text' id="searchText" class='inputSearch width200'>
            <input type='button' id='searchCustomer' onclick='searchCustomer()' value='搜索' class='btn-hover-crimson'>
        </div>
        <div class='clear'></div>
    </div>
    <div class='cProduceMain'>
        <ul></ul>
    </div>
    <div class='cProduceSelectedShow'>
        <ul>
            <div class='clear'></div>
        </ul>
    </div>
    <div class='cProduceFooter'>
        <div class='pager floatLeft'>
            <a onclick="onclickPrep()">上一页</a><span class='font-color-crimson'  id="nowPage"> 1 </span>
            <a onclick="onclickNext()">下一页</a></div>
        <div class='goToPage floatLeft'>共<span id="count"></span>条&nbsp;&nbsp;共<span id="pageCount"></span>页&nbsp;&nbsp;
            <span id="pageSize"></span>条/页&nbsp;&nbsp;跳转至&nbsp;&nbsp;
            <input type='text' id="skipText" class='width50' />&nbsp;&nbsp;页&nbsp;&nbsp;
            <input type='button' onclick="skipPage()" class='btn-hover-crimson btn-gray' value='跳转' />
    </div>
        <div class='cProduceFooterFun floatRight'>
            <input type='button' value='确定' class='cProduceSure btn-hover-crimson width60 marginRight10'>
            <input type='button' value='取消' class='cProduceCancel btn-hover-crimson width60'>
        </div>
        <div class='clear'></div>
    </div>
</div>
<div class="bgShow"></div>
<div class="clear"></div>
<script>
    var _classId;
    var _companyJson = [];
    var _companyJsonEmp = [];
    var _taskCommissionType = $('#taskCommissionType').val(),
        _taskAndClassId = $('#taskAndClassId').val(),
        _taskCustomer = $.parseJSON($('#taskCustomer').val()),
        _taskThumbnail = $.parseJSON($('#taskThumbnail').val()),
        _companyUrl = $('#companyUrl').val(),
        _type = $('#type').val(),
        _availableBalance = $('#availableBalance').val();
    var _pageSize = 29,_nowPage = 0,_count = 0,_totalPage,
        _pageCount;
    var _companyMap = new HashMap();
    var chooseId = [];

    $(function(){
        $.ajax({
            url 	 : _companyUrl,
            type 	 : "post",
            dataType : "JSON",
            success  : function(data, textStatus, XMLHttpResponse) {
                var _dataMoni = [];
                for(var i = 0 ; i < data.length; i++){
                    var _data = {};
                    _data.chainName = data[i].companyName;
                    _data.chainId = data[i].customerId;
                    _dataMoni.push(_data);
//                    if(i == 10) break;
                }
                _companyJson = _dataMoni;
                _companyJsonEmp = _dataMoni;

            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                console.log(textStatus);
            }
        });

        $(".shadowClose").bind("click",function(){
            $(".trainingDetail,.bgShow").fadeOut();
        });

        $('input[name = "brokerage"][value = "'+ _taskCommissionType +'"]').attr("checked","checked");
        if(1 == _taskCommissionType){
            $('#trainFundPercent').val('');
        }else{
            $('#trainFund').val('');
        }
        _classId = _taskAndClassId;
        console.log(_type);
        if("" != _type){
            $('input[name = "question"][value = "'+ _taskAndClassId +'"]').attr("checked","checked");
            $('.selectedTraining').html($('input[name="question"]:checked').attr('data'));
            var _customerHtml;
            var _addShopMain = $('#addShopMain');
            $.each(_taskCustomer,function(i,_value){
                _customerHtml = "<li dataId='"+_value.customerId+"' title='"+_value.customerName+"'>";
                _customerHtml += "<span class='shopName'>"+_value.customerName+"</span>";
                _customerHtml += '<span class="shopDelete" onclick="deleteParent($(this))">×</span></li>';
                $(_customerHtml).insertBefore(_addShopMain.find(".all"));
            });
            var _thumbnailHtml;
            $.each(_taskThumbnail,function(i,_value){
                _thumbnailHtml = '<div class="thumbnail fileUpload"  value="'+ _value.imgUrl +'" defualt="${mvcPath}/img/yizheng.jpg" limit="5"></div>';
                $(_thumbnailHtml).insertBefore($('#fileShow'));
            });
        }

        $("#startTime,#endTime").datetimepicker({
            lang:'ch',
            timepicker:true,
            format:'Y-m-d H:i:s',
            formatDate:'Y-m-d',
            scrollMonth:false,
            scrollTime:false,
            scrollInput:false,
        });

        $('.question').change(function(){
            _classId=$('input[name="question"]:checked').val();
            $.ajax({
                url 	 : "${mvcPath}/view/trainClass/showClassDetail",
                type 	 : "post",
                dataType : "json",
                data 	 : {classId:_classId},
                success  : function(data, textStatus, XMLHttpResponse) {
                    $("#taskQuestions").empty();
                    var _exercisesList = data.exercisesList;
                    var _companyTrainClass = data.companyTrainClass;
                    $(".taskDetailClass").html(_companyTrainClass.title);
                    var _content = _companyTrainClass.content;
                    console.log(_companyTrainClass.contentType);
                    if(1 == _companyTrainClass.contentType){
                        $.hd.pdfFile(_content,$(".taskDetailContent"));
                    }else{
                        $(".taskDetailContent").html(_companyTrainClass.content);
                    }

                    var questionHtml;
                    $.each(_exercisesList, function(index,value){
                        var _type = value.type == "singleTab" ? "单选" : "多选";
                        var _index = index+1;
                        questionHtml = '<ul class="question">';
                        questionHtml += "<li>"+ _index + "、" + value.content + "(" + _type + "正确答案:" + value.answer +")</li>";
                        questionHtml += "<li>A."+ value.optionA +"</li>";
                        questionHtml += "<li>B."+ value.optionB +"</li>";
                        questionHtml += "<li>C."+ value.optionC +"</li>";
                        questionHtml += "<li>D."+ value.optionD +"</li>";
                        $("#taskQuestions").append(questionHtml);
                    });
                    $(".selectedTraining").text(_companyTrainClass.title);
                    $(".trainingDetail,.bgShow").fadeIn();
                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    console.log(textStatus);
                }
            });
        });

        //图片上传
        $(".fileUpload").fileUpload();

        //提示红包个数
        $("#awardHigh").blur(function(){
            var _awardHigh = $(this).val();
            var _moneyReward = $('#moneyReward').val();
            if("" != _awardHigh && "" != _moneyReward){
                var _brokerage = $('input[name="brokerage"]:checked').val();
                var _trainFund,_serveCharge,_count,_sumCount;
                if(1 == _brokerage){
                    _trainFund = $("#trainFund").val();
                    _serveCharge = $("#serveCharge").html();
                    if("" == _trainFund || "" == _serveCharge) return ;
                    _sumCount = parseInt(_trainFund) + parseInt(_serveCharge) + parseInt(_awardHigh);
                }else if(2 == _brokerage){
                    var _fund,_charge;
                    _trainFund = $("#trainFundPercent").val();
                    _serveCharge = $("#serveChargePercent").html();
                    if("" == _trainFund && "" == _serveCharge) return ;
                    _fund = _trainFund * (_trainFund / 100);
                    _charge = _serveCharge * (_serveCharge / 100);
                    _sumCount = parseInt(_fund) + parseInt(_charge) + parseInt(_awardHigh);
                }
                _count = (_moneyReward % _sumCount) == 0 ? (_moneyReward / _sumCount) : parseInt(_moneyReward / _sumCount) + 1;
                $.hd.tips($(this),"最多红包数为"+ _count + "个");
            }
        });
    });


    $("#addShop").click(function(){
        $('.chooseProduce,.bgShow').fadeIn();
        $('#addShopMain ul').empty();
        addEvent();
        addProduceData(_companyJson);
    });

    function addEvent(){
        var appendId = 'addShopMain';
        $("body").unbind("click").delegate(".cProduceMain li","click",function(){
            var that = $(this);
            if(that.find("input[type=checkbox]").attr("checked")=="checked"){
                $(".cProduceSelectedShow ul").append("<li dataId='"+that.attr("dataId")+"' dataDes='"+that.find(".con").text()+"'>"+that.find(".name").text()+"<span class='deleteDom one'></span></li>");
            }else{
                $(".cProduceSelectedShow ul li").each(function(){
                    if($(this).attr("dataId")==that.attr("dataId")){
                        $(this).remove();
                    }
                })
            }
        });

        $("body").delegate(".cProduceSelectedShow .deleteDom","click",function(){
            var judgeId = $(this).parent().attr("dataId");
            if(confirm("确认删除？")){
                $(this).parent().remove();
                $(".cProduceMain li").each(function(){
                    if($(this).attr("dataId")==judgeId){
                        $(this).find("input[type=checkbox]").removeAttr("checked");
                    }
                })
            }
        });


        var judgeExit = false;
        if(true) judgeExit = true;
        var deleteFun = 'deleteParent';

        $("body").delegate(".cProduceSure","click",function(){
            if(appendId) {
                var appendDom = $("#" + appendId);
                $(".cProduceSelectedShow li").each(function () {
                    var that = $(this);
                    var thisId = that.attr("dataId");
                    var judgeNowD = true;
                    if (judgeExit) {
                        appendDom.find("li").each(function () {
                            if ($(this).attr("dataId") == thisId) {
                                judgeNowD = false;
                            }
                        })
                    }
                    if (judgeNowD) {
                        if (appendDom.find(".all").length > 0) {
                            $("<li dataId='" + thisId + "' title='" + that.text() + "'>" +
                            "<span class='shopName'>" + that.text() + "</span>" +
                            "<span class='shopDelete' onclick='" + deleteFun + "($(this))'>×</span></li>").insertBefore(appendDom.find(".all"));
                        } else {
                            $("<li dataId='" + thisId + "' title='" + that.text() + "'>" +
                            "<span class='shopName'>" + that.text() + "</span>" +
                            "<span class='shopDelete' onclick='" + deleteFun + "($(this))'>×</span></li>").insertBefore(appendDom.find(".clear"));
                        }
                    }
                });
            }
//            refresh();
            $(".chooseProduce,.bgShow").fadeOut();
        });

        $("body").delegate(".cProduceCancel","click",function(){
            refresh();
        });

//        $('.cProduceSelectedShow li').each(function(){
//            var that = $(this);
//            var thisId = that.attr("dataId");
//            if(chooseId.indexOf(thisId) < 0) chooseId.push(thisId);
//        });
//        $.each(chooseId,function(i,value){
//            console.log($('#'+value));
//            console.log($('#'+value+' input[name="customerId"][value='+ value +']').attr("checked"));
//            console.log($('#09914763-59A4-4534-B878-AEBF4F4B0C32 input[name="customerId"][value="09914763-59A4-4534-B878-AEBF4F4B0C32"]').attr("checked"));
//            $('#'+value+' input[name="customerId"][value='+value+']').prop("checked",true);
//        });
//        $('.cProduceCancel').click(function(){
//            refresh();
//        });
    }


    function refresh(){
        $(".chooseProduce,.bgShow").fadeOut();
//        $('.cProduceMain li input[type=checkbox]').removeAttr("checked");
//        $(".cProduceSelectedShow ul").empty();
    }

    function addProduceData(dArray,_page){
        _count = dArray.length;
        var _countSpan = $('#count');
        var _nowPageSpan = $('#nowPage');
        var _pageSizeSpan = $('#pageSize');
        var _pageCountSpan = $('#pageCount');
        if(null != _page){
            _nowPage = _page
        }
        console.log(_page);
        console.log(_nowPage);
        _totalPage = parseInt(_count/_pageSize);
        _pageCount = _count % _pageSize == 0 ? _totalPage : (_totalPage + 1);
        _countSpan.html(_count);
        _nowPageSpan.html(_nowPage+1);
        _pageSizeSpan.html(_pageSize);
        _pageCountSpan.html(_pageCount);
        var dataDom = $(".cProduceMain ul");
        dataDom.empty().append("<div class='clear'> </div>");
        for(var i = (_nowPage*_pageSize);i < dArray.length;i++){
            $("<li id='"+dArray[i].chainId+"' dataId='"+dArray[i].chainId+"'><label><input type='checkbox' value='"+dArray[i].chainId+"' name='customerId'><span class='name'>"+dArray[i].chainName+"</span></label></li>").insertBefore(dataDom.find(".clear"));
            if(i == _pageSize*(_nowPage + 1)){
                break ;
            }
        }
        $(".cProduceSelectedShow ul li").each(function(){
            var _dataId = $(this).attr("dataId");
            $('.cProduceMain li input[type=checkbox][value = "'+ _dataId +'"]').attr("checked","checked");

        });
    }

    /**
     * 点击跳转
     */
    function skipPage(){
        var _skipText = $('#skipText').val();
        console.log(_skipText);
        _nowPage = _skipText >= _pageCount ? _pageCount-1 : _skipText-1;
        if(_skipText == 0) _nowPage = 0;
        addProduceData(_companyJson);
    }

    /**
     * 点击下一页
     */
    function onclickNext(){
        if(_nowPage == _pageCount-1){
            return;
        }
        _nowPage++;
        addProduceData(_companyJson);
    }

    /**
     * 点击上一页
     */
    function onclickPrep(){
        if(_nowPage == 0){
            return;
        }
        _nowPage--;
        addProduceData(_companyJson);
    }

    function customerArrayToMap(){
        for(var i = 0;i < _companyJsonEmp.length;i++){
            var _chainId = _companyJsonEmp[i].chainId;
            var _chainName = _companyJsonEmp[i].chainName;
            _companyMap.put(_chainId,_chainName);
        }

    }

    //回车事件绑定
    $('#searchText').bind('keyup', function(event) {
        if (event.keyCode == "13") {
            //回车执行查询
            searchCustomer();
        }
    });

    /**
     * 指定连锁的搜索
     */
    function searchCustomer(){
        customerArrayToMap();
        var _div = $(".cProduceMain");
        var _searchCustomer = $("#searchText").val();
        if('' == _searchCustomer){
            _companyJson = _companyJsonEmp;
        }else{
            _companyJson = [];
            $.each(_companyMap.keySet(), function(i,value){
                var _customerName=_companyMap.get(value);
                var flag = _customerName.indexOf(_searchCustomer);
                if(flag >= 0){
                    var _data = {};
                    _data.chainName = _customerName;
                    _data.chainId = value;
                    _companyJson.push(_data);
                }
            });
        }
        addProduceData(_companyJson,0);
    }

    $("#searchTxt").keyup(function(){
        searchClass();
    });

    /**
     *模糊搜索课件
     */
    function searchClass(){
        var _classDiv = $("#classDiv");
        var _searchTxt = $("#searchTxt");

        if(_classDiv.css("display") == 'none'){
            _classDiv.css('display','block');
        }
        var _name = _searchTxt.val();
        var v = _name.replace(/^\s+|\s+$/g,"");
        var trs = _classDiv.find("li:gt(0)");
        console.log(trs);
        trs.filter("::visible").hide();
        if(v == ""){
            trs.filter(":hidden").show();
        }else{
            trs.hide().filter(":contains('"+_searchTxt.val()+"')").show();
        }
    }


    /**
     *保存任务
     */
    function saveTask(){
        var _brokerage = $('input[name="brokerage"]:checked').val();
        var _params = $(".task-main").params();
        console.log(JSON.stringify(_params));
        if(_params != null){
            var _thumbnails = [];
            var _customer = [];
            $('.thumbnail').each(function(index){
                var _jsonValue = {};
                var _thumbnail = $(this).attr('value');
                _jsonValue.thumbnail = _thumbnail;
                _thumbnails.push(_jsonValue);
            });

            $('#addShopMain li').each(function(index){
                var _jsonValue = {};
                var _customerId = $(this).attr('dataId');
                var _customerName = $(this).attr('title');
                _jsonValue.customerId = _customerId;
                _jsonValue.customerName = _customerName;
                _customer.push(_jsonValue);
            });
            _params.thumbnails = JSON.stringify(_thumbnails);
            _params.customer = JSON.stringify(_customer);
            _params.classId = _classId;
            _params.commissionType = _brokerage;
            var _reg = /^(([0-9]+)|([0-9]+\.[0-9]{1,2}))$/;
            if(2 == _brokerage){
                console.log($("#trainFundPercent").val().match(_reg));
                if($("#trainFundPercent").val().match(_reg) == null){
                    $.hd.tips($("#trainFundPercent"),"请输入正确格式（可小数点后2位）");
                    return false;
                }
//                if($("#serveChargePercent").val().match(_reg) == null){
//                    $.hd.tips($("#serveChargePercent"),"请输入正确格式（可小数点后2位）");
//                    return false;
//                }
                _params.trainFund = $("#trainFundPercent").val();
            }else if(1 == _brokerage){
                if($("#trainFund").val().match(_reg) == null){
                    $.hd.tips($("#trainFund"),"请输入正确格式（可小数点后2位）");
                    return false;
                }
//                if($("#serveCharge").val().match(_reg) == null){
//                    $.hd.tips($("#serveCharge"),"请输入正确格式（可小数点后2位）");
//                    return false;
//                }
            }

            if(Date.parse(_params.startTime) > Date.parse(_params.endTime)){
                $.hd.tips($("#startTime"),"结束时间必须大于开始时间");
                return false;
            }

            if(Date.parse(_params.startTime) < Date.parse(new Date())){
                $.hd.tips($("#startTime"),"开始时间必须大于当前时间");
                return false;
            }

            if(_customer.length <= 0){
                $.hd.tips($("#addShop"),"请指定连锁");
                return ;
            }
            if(null == _classId || '' == _classId){
                $.hd.tips($(".selectedTraining"),"培训课件不能为空");
                return ;
            }
            if(_type == 3){//重新发布界面
                _params.taskId = '';
            }

            console.log($('#availableBalance').val());

            if(Number($('#availableBalance').val()) < Number($('#moneyReward').val()) ){
                $.hd.tips($("#moneyReward"),"你的余额不足，请充值！");
                return ;
            }

            var _awardLow = $('#awardLow');
            var _awardMiddle = $('#awardMiddle');
            var _awardHigh = $('#awardHigh');
            if(Number(_awardLow.val()) > Number(_awardHigh.val())){
                $.hd.tips(_awardLow,"请根据答题正确率设置奖励！");
                return ;
            }
            if(Number(_awardMiddle.val()) > Number(_awardHigh.val())){
                $.hd.tips(_awardMiddle,"请根据答题正确率设置奖励！");
                return ;
            }

            $.ajax({
                url 	 : "${mvcPath}/view/trainTask/saveOrUpdateTask",
                type 	 : "post",
                dataType : "json",
                data 	 : _params,
                success  : function(data, textStatus, XMLHttpResponse) {
                    if(data.result){
                        $.hd.alertMsg.info("保存成功！");
                        loadingPage('/view/trainTask');
                    }else{
                        $.hd.alertMsg.error("服务器繁忙,稍后请重试!");
                    }
                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    console.log(textStatus);
                }
            });
        }
    }



    function deleteParent(obj){
    //        if(confirm("确认删除？")){
    //            obj.parent().remove();
    //        }
        $.hd.alertMsg.confirm("删除","确认删除吗?",function(){
            obj.parent().remove();
        });
    }
</script>