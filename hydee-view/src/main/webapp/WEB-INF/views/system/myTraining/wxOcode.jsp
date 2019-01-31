<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include.inc.jsp"%>

<div class="right-main paddingLR40">
    <input id="outTradeNo" type="hidden" value="${outTradeNo}"/>
    <div class="je">充值金额：<span class="font-color-crimson ">${totalFee / 100}</span>元，请使用微信扫描下面的二维码完成付款！
        <span style=" float:right;">
            <a style="color:#c6244b;" onclick="loadingPage('/view/accountRecharge');" class="width100 fakeBtn-hover-crimson">返回账户充值</a>
        </span>
    </div>
</div>
<div class="floatLeft ewm"><img id="wxQrCode" src="${mvcPath}/wxpay/wxQrCode?result=${qcodeResult}"></div>
<div class="floatRight sj"><img src="${mvcPath}/img/sys.jpg"></div>
<div class="clear"></div>

<script type="text/javascript">
    $(document).ready(function () {
        timer = setInterval(function(){
            ajaxstatus();
        }, 3000)
    });

    function ajaxstatus() {
        $.ajax({
            url: "${mvcPath}/wxpay/orderquery",
            type: "GET",
            dataType:"json",
            data: {outTradeNo : $("#outTradeNo").val()},
            global: false,
            success: function (data) {
                console.log(data);
                if(data.result) {
                    clearInterval(timer);
                    loadingPage('/view/accountRecharge');
                }else if(data.data == 'REFUND'){
                    clearInterval(timer);
                    loadingPage('/view/accountRecharge');
                    $.hd.alertMsg.info("订单出错，已退款！");
                }
            },
            error: function () {
                alert("请求订单状态出错");
            }
        });
    }
</script>
