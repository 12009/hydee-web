<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include.inc.jsp" %>
<!DOCTYPE html>
<html lang="en">


<body>

<div class="right-main accountRecharge">
    <p class="font-color-crimson">充值目前支持线下充值，请直接银行转账至海典财务账户</p>
    <ul>
        <li><span class="qian">账户名&nbsp;&nbsp;&nbsp;&nbsp;</span><span>上海海典软件股份有限公司</span></li>
        <li><span class="qian">所属银行</span><span>上海浦东发展银行金桥支行</span></li>
        <li><span class="qian">银行账户</span><span>988 401 547 400 064 58</span></li>
    </ul>
    <p class="font-color-crimson">转账后请输入以下转账信息提交给管理员审核确认，管理员确认后直接充值至厂家培训账户余额</p>
    <ul>
        <li><span class="qian">账户名&nbsp;&nbsp;&nbsp;&nbsp;</span><input style="width: 300px;" type="text"
                                                                        id="accountName" placeholder="输入银行账户名称"></li>
        <li><span class="qian">银行卡号</span><input style="width: 300px;" type="text" id="bankCard" placeholder="输入银行卡号">
        </li>
        <li><span class="qian">所属银行</span><input style="width: 300px;" type="text" id="bankName"
                                                 placeholder="输入银行名称即可，如：中国银行"></li>
        <li><span class="qian">充值金额</span><input style="width: 300px;" type="text" id="amount"
                                                 placeholder="请输入数字，至少100"></li>
        <li><span class="qian">联系电话</span><input style="width: 300px;" type="text" id="phone" placeholder="请输入联系电话">
        </li>
        <li><span style="float:left;margin-right: 25px">上传转账凭证</span>
            <div style="width: 300px;height: 200px;" id="transferProof" class="fileUpload" value=""
                 defualt="${mvcPath}/img/pingzheng.jpg"></div>
        </li>
        <div class="clear"></div>
    </ul>
    <p>
    <input type="button" class="width80 btn-hover-crimson nextPart marginTop20 marginLeft150 btn" value="提交">
    </p>

</div>
<div class="clear"></div>

<script type="text/javascript">


    function addRecharge() {

        var accountName = $("#accountName").val();
        var bankCard = $("#bankCard").val();
        var bankName = $("#bankName").val();
        var amount = $("#amount").val();
        var phone = $("#phone").val();
        var _transferProof = $('#transferProof').val();
        console.log(_transferProof);
        if (accountName == null || accountName == "") {
            $.hd.tips($("#accountName"), "账户名称不能为空!");
            return;
        } else if (bankCard == null || bankCard == "" || (bankCard.length != 16 && bankCard.length != 19)) {
            $.hd.tips($("#bankCard"), "银行卡号不正确!");
            return;
        } else if (bankName == null || bankName == "") {
            $.hd.tips($("#bankName"), "银行名称不能为空!");
            return;
        } else if (amount == null || amount == "" || isNaN(amount)) {
            $.hd.tips($("#amount"), "充值金额不正确!");
            return;
        } else if (amount < 100) {
            $.hd.tips($("#amount"), "充值金额不能少于100!");
            return;
        } else if (phone == null || phone == "" || isNaN(phone)) {
            $.hd.tips($("#phone"), "手机号不正确!");
            return;
        } else if (_transferProof == null || _transferProof == "") {
            $.hd.tips($(".fileUpload"), "请上传转账凭证!");
            return;
        }


        $.ajax({
            url: "${mvcPath}/view/accountRecharge/addRecharge",
            type: "post",
            dataType: "json",
            data: {
                accountName: accountName,
                bankCard: bankCard,
                bankName: bankName,
                amount: amount,
                phone: phone,
                transferProof: _transferProof
            },
            success: function (data, textStatus, XMLHttpResponse) {
                if (data.result) {
                    $.hd.alertMsg.info("提交成功，请耐心等待管理员审核...");
                    refreshPage();
                } else {
                    $.hd.alertMsg.error(data.data || "服务器繁忙,稍后请重试!");
                }
            }
        });


    }

    $(document).ready(function ($) {

        $(".btn").click(function (event) {
            addRecharge();
        });

        $(".hint-in3").click(function (event) {
            $(".hint").css({"display": "none"});
            $(".box").css({"display": "none"});
        });
        $(".fileUpload").fileUpload();
    });
</script>
</body>
</html>