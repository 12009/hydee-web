<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include.inc.jsp"%>
<div class="right-main">
    <ul class="accountDetail_wp">
        <li>
            <div class="accountDetailLeft">
                <div class="name">可用余额</div>
                <div class="money marginTop20">${availableBalance}</div>
                <div class="reChargeFun">
                    <input type="button" onclick="loadingPage('/view/accountRecharge');" class="btn-hover-crimson" value="充值">
                    <a onclick="loadingPage('/view/rechargeRecord');" class="count checkChargeLog">查看充值记录></a>
                </div>
            </div>
            <div class="accountDetailRight">
                <div class="name">正在进行</div>
                <%--<div class="activeDetail">--%>
                    <%--<span class="activeDetailAni"><i class="fontNormal"></i><em class="fontNormal">活动</em></span>--%>
                    <%--<span class="money">2</span>--%>
                    <%--<a href="../2myActivities/activityList.html" class="checkActiveDetail">查看</a>--%>
                    <%--<a href="../2myActivities/activityAdd.html" class="count">创建新活动></a>--%>
                <%--</div>--%>
                <div class="activeDetail train">
                    <span class="activeDetailAni"><i class="fontNormal"></i><em class="fontNormal">培训</em></span>
                    <span class="money">${doingAccount}</span>
                    <a onclick="loadingPage('/view/trainTask');" class="checkActiveDetail">查看</a>
                    <a onclick="loadingPage('/view/trainTask/addTrainTask');" class="count">创建新培训></a>
                </div>
            </div>
            <div class="clear"> </div>
        </li>
        <li>
            <div class="accountDetailLeft">
                <div class="name">冻结金额</div>
                <div class="money marginTop20">${taskFrozen}</div>
                <div class="marginTop18">
                    <span class="count">培训：${taskFrozen}</span>
                    <%--<span class="count">活动：9300.5</span>--%>
                </div>
                <a onclick="loadingPage('/view/frozenBalance');" class="marginTop24 count disInlineBlock">提取已结束培训和活动的冻结余额></a>
            </div>
            <div class="accountDetailRight">
                <div class="name">累计费用</div>
                <div class="money marginTop20">${taskCharge}</div>
                <div class="marginTop18">
                    <span class="count">培训：${taskCharge}</span>
                    <%--<span class="count">活动：9300.5</span>--%>
                </div>
                <a onclick="loadingPage('/view/fundsWhereabouts');" class="marginTop24 count disInlineBlock">查看资金去向></a>
            </div>
            <div class="clear"> </div>
        </li>
        <%--<li>--%>
            <%--<div class="accountDetailLeft">--%>
                <%--<div class="name">昨日活动交易额</div>--%>
                <%--<div class="money marginTop20">12000.0</div>--%>
            <%--</div>--%>
            <%--<div class="accountDetailRight">--%>
                <%--<div class="name">累计活动交易额</div>--%>
                <%--<div class="money marginTop20">685628.6</div>--%>
            <%--</div>--%>
            <%--<div class="clear"> </div>--%>
            <%--<div class="dataShow" id="dataShowPrice">--%>
                <%--<ul dataShow="当日交易额："></ul>--%>
                <%--<div class="floatDaTa">--%>
                    <%--<div class="floatDataMain">--%>
                        <%--<p class="date">0000-00-00 星期一</p>--%>
                        <%--<p class="marginTop5 dataSum">当日交易额：0.0</p>--%>
                        <%--<span class="arrow arrowF"></span>--%>
                        <%--<span class="arrow arrowL"></span>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</li>--%>
        <li>
            <div class="accountDetailLeft">
                <div class="name">昨日培训人数</div>
                <div class="money marginTop20" id="yesterdayUserAccount"></div>
            </div>
            <div class="accountDetailRight">
                <div class="name">累计培训人数</div>
                <div class="money marginTop20">${taskUserAccount}</div>
            </div>
            <div class="clear"> </div>
            <div class="dataShow" id="dataShowCount">
                <ul dataShow="当日培训人数："></ul>
                <div class="floatDaTa">
                    <div class="floatDataMain">
                        <p class="date">0000-00-00 星期一</p>
                        <p class="marginTop5 dataSum">当日交易额：0.0</p>
                        <span class="arrow arrowF"></span>
                        <span class="arrow arrowL"></span>
                    </div>
                </div>
            </div>
        </li>
    </ul>
</div>
<script>//这里调用插件只是测试，可以放在单独的js文件里面,js位置在jquery_common后面
//$("#dataShowPrice").histogramDataShow({
//    "dataList":["","","110","234","210","134","110","634","110","234","110","234","180","234","110","934","110","234","1010","234","110","934","110","834","110","","","","",""]
//})

$(function(){
    $.ajax({
        url 	 : "${mvcPath}/view/accountOverviewDetail/showMontTaskCount",
        type 	 : "post",
        dataType : "json",
        success  : function(data, textStatus, XMLHttpResponse) {
            if(data.result){
                var _monthTaskDate = data.data.monthTaskDate;
                var _taskData = [];
                for(var i=0;i<_monthTaskDate.length;i++){
                    _taskData.push(Number(_monthTaskDate[i].dayTaskCount));
                }
                $('#yesterdayUserAccount').html(Number(_monthTaskDate[0].dayTaskCount));
                $("#dataShowCount").histogramDataShow({
                    "dataList":_taskData
                });
            }
        },
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            console.log(textStatus);
        }
    });
});

</script>