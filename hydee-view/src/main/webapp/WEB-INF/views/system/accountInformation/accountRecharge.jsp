<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include.inc.jsp" %>
<div class="right-main">
    <c:if test="${!sessionUser.isSysAdmin}">
        <div class="table-height30 marginTop20">
            <div style=" float:left;margin-right:30px;"><a class="width80 fakeBtn-hover-crimson floatLeft" style="margin-right:10px;">账户余额</a>
                <label>${availableBalance}元</label></div>
            <a onclick="loadingPage('/view/accountRecharge/duigongdakuan');" class="width80 fakeBtn-hover-crimson floatLeft addNewRole" style="margin-right:10px;">对公打款</a>
            <a href="javascript:;" class="width80 fakeBtn-hover-crimson floatLeft addNewRole" style="margin-right:10px;" id="ClickMe">微信支付</a>

            <!--充值金额结束 -->
            <div class="czje displayNone">
                <div class="close"></div>
                <form action="" method="get">
                    充值金额：<input type="text" id="rechargeMoney"/>元<br /><br />
                    <label><input name="money" type="radio" value="1000"/>1000元 </label>
                    <label><input name="money" type="radio" value="2000"/>2000元 </label>
                    <label><input name="money" type="radio" value="10000"/>10000元 </label>
                    <label><input name="money" type="radio" value="20000"/>20000元 </label>
                    <label><input name="money" type="radio" value="50000"/>50000元 </label>
                </form><br />
                <a onclick="wxPay()" class="width80 fakeBtn-hover-crimson floatLeft" style="margin-right:10px;">立即充值</a><span style="color:#c6244b;">（不低于1000元）</span>
            </div>
            <div class="clear"></div>
        </div>
    </c:if>
    <div class="right-main-top" id="searchParams">
        <input type="text" value="${page.serialNumber}" id="serialNumber" placeholder="流水号">
        <label class="input">
            <input type="text" value="${page.pStartTime}" class="labelInput width200" tabindex="0" id="pStartTime" placeholder="开始时间" >
        </label>——
        <label class="input">
            <input type="text" value="${page.pEndTime}" class="labelInput width200" tabindex="0"  id="pEndTime" placeholder="结束时间" >
        </label>
        <select class="xiala" id="status" name="status">
            <option value="-1" <c:if test="${page.status==-1}">selected = "selected"</c:if>>全部充值记录</option>
            <option value="0" <c:if test="${page.status==0}">selected = "selected"</c:if>>待审核</option>
            <option value="1" <c:if test="${page.status==1}">selected = "selected"</c:if>>充值成功</option>
            <option value="2" <c:if test="${page.status==2}">selected = "selected"</c:if>>充值失败</option>
        </select>
        <select class="payment" style="height:30px;border-radius:4px;" id="payment" name="dictId">
            <option value="">全部</option>
            <c:forEach items="${companyDicts}" var="types">
                <c:if test="${types.dictId == page.dictId}">
                    <option value="${types.dictId}" selected="selected">${types.dictName}</option>
                </c:if>
                <c:if test="${types.dictId != page.dictId}">
                    <option value="${types.dictId}">${types.dictName}</option>
                </c:if>
            </c:forEach>
        </select>
        <input type="button" class="width80 btn-hover-crimson nextPart" onclick="search()" value="查询">
    </div>
    <%--<div class="right-main-bottom">--%>
        <ul class="ul-table-tHeader  marginTop30 paddingLR20">
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
        <c:forEach items="${companyAccountRechargeList}" var="item">
            <ul class="ul-table-tBody tableWithoutLine80">
                <fmt:formatDate value="${item.createTime}" var="createTime" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
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
                        <c:when test="${item.status == 3}">
                            已退款
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

        <div class="tcdPageCode" count="${page.totalResult}" page="${page.currentPage}" size="${page.showCount}" form="/view/accountRecharge" filter="#searchParams"></div>
        <div class="clear"></div>
    <%--</div>--%>

</div>
<div class="bgShow" />

<script>
    $(function(){
        $("#pStartTime,#pEndTime").datetimepicker({
            lang:'ch',
            timepicker:false,
            format:'Y-m-d H:i:s',
            formatDate:'Y-m-d',
            scrollMonth:false,
            scrollTime:false,
            scrollInput:false,
        });
        $('input:radio[name="money"]').change( function(){
            $('#rechargeMoney').val($(this).val());
        });

        $("#ClickMe").bind("click",function(){
            $(".czje,.bgShow").fadeIn();
        });

        $(".bgShow").bind("click",function(){
            $(".czje,.bgShow").fadeOut();
        });
        $(".close").bind("click",function(){
            $(".czje,.bgShow").fadeOut();
        });
    });

    function wxPay(){
        var _rechargeMoney = $('#rechargeMoney').val();
        var _reg = /^(([0-9]+)|([0-9]+\.[0-9]{1,2}))$/;
        if(_rechargeMoney.match(_reg) == null){
            $.hd.tips($("#rechargeMoney"),"请输入正确格式（可小数点后2位）");
            return ;
        }
        if(_rechargeMoney < 1000){
            $.hd.tips($("#rechargeMoney"),"不能低于1000元");
            return ;
        }
        var _money = _rechargeMoney * 100
        var _params = {};
        _params.totalFee = _money;
        loadingPage("/view/accountRecharge/wxOcode",_params)
        $(".czje,.bgShow").fadeOut();
    }

    function search(){
        var _serialNumber = $("#serialNumber").val();
        var _pStartTime = $("#pStartTime").val();
        var _pEndTime = $("#pEndTime").val();
        var _status = $("#status").val();
        var _payment = $("#payment").val();
        var _params={};
        _params.serialNumber = _serialNumber;
        _params.pStartTime = _pStartTime;
        _params.pEndTime = _pEndTime;
        _params.status = _status;
        _params.dictId = _payment;
        loadingPage("/view/accountRecharge",_params);
    }

</script>