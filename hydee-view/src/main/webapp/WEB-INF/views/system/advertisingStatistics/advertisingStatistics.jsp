<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include.inc.jsp"%>
<style type="text/css">
    #compSelectedDetailDialog .deptNode { width: 100%; height: 300px; margin-bottom: 5px;overflow: auto}
    #compSelectedDetailDialog .clear{ width:1px; height:0; clear:both;}
    #compSelectedDetailDialog ul li {
        margin-left: 10px; margin-bottom: 5px; height: 20px; line-height: 20px; float: left; display: block;
        width: 100px; overflow: hidden;-moz-user-select: none; -khtml-user-select: none; user-select: none;
    }
    #compSelectedDetailDialog i { color: #B9D3EE; }
</style>
<div class="right-main">
    <c:if test="${reList.size() > 0 }">
        <ul class="ul-table-tHeader marginTop20 paddingLR20">
            <li class="width100">广告名称</li>
            <li class="width90">广告图片</li>
            <li class="width90">播放开始时间</li>
            <li class="width90">播放结束时间</li>
            <li class="width110">生效时间</li>
            <li class="width110">失效时间</li>
            <li class="width90">投放省市</li>
            <li class="width100">投放连锁数量</li>
            <li class="width80">状态</li>
            <li class="width80">总展现次数</li>
            <li class="width60">统计详情</li>
            <div class="clear"></div>
        </ul>
        <c:forEach items="${reList}" var="item">
            <ul class="ul-table-tBody tableWithoutLine100 ">
                <li class="width100">${item.advertName}</li>
                <li class="width90">
                    <div class="mskeLayBg"></div>
                    <div class="mskelayBox">
                        <div class="mske_html">
                        </div>
                        <img class="mskeClaose" src="${mvcPath}/img/mke_close.png"/>
                    </div>
                    <div class="msKeimgBox">
                        <ul>
                            <li>
                                <img src="${item.imgUrl}" width="50" height="50"/>
                              <span class="hidden" style="display: none">
                              <img src="${item.imgUrl}" width="650" height="288"/></span>
                            </li>
                        </ul>
                    </div>
                </li>
                <li class="width90">${item.playStartTime}</li>
                <li class="width90">${item.playEndTime}</li>
                <li class="width110">${item.startTime}</li>
                <li class="width110">${item.endTime}</li>
                <c:choose>
                    <c:when test="${item.advertType == 0}">
                        <li class="width90">${item.region == '' ? '全国' : item.region} ${item.city == '' ? '全市' : item.city}</li>
                    </c:when>
                    <c:otherwise>
                        <li class="width90">--</li>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${item.advertType == 0}">
                        <li class="width100">--</li>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${item.customerList.size() > 0}">
                                <li class="width100"><a onclick='advertCompDetials(${item.customerListJson})'>${item.customerList.size()}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li class="width100">0</li>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
                <li class="width80">${item.adStatus}</li>
                <li class="width80">${item.visitCount}</li>
                <li class="width60">
                    <a onclick="detailAdverts(${item.id},${item.advertType})" style="color:#0077dd;">查看</a>
                </li>
                <div class="clear"></div>
            </ul>
        </c:forEach>
        <div class="tcdPageCode" count="${page.totalResult}" page="${page.currentPage}" size="${page.showCount}" form="/view/showAdverts" filter="#searchParams"></div>
        <div class="clear"></div>
    </c:if>
    <c:if test="${reList.size() <= 0}">
        <img height="60%" width="100%" src="${mvcPath}/img/attract_investment.jpg">
    </c:if>
</div>
<div class="lsmd displayNone" id="compSelectedDetailDialog">
    <div class="close"></div>
    <b>连锁名单：</b>
    <ul class="deptNode"></ul>
</div>
<div class="clear"></div>
<div class="bgShow"></div>
<script type="text/javascript">
    function advertCompDetials(comps){
        $('.deptNode').empty();
        if(comps == null) return;
        console.log(comps);
        var _html = '';
        $.each(comps,function(index,value){
            _html += "<li><i class='fa fa-id-card fa-la group'></i> "+value.customerName+"</li>";
        });
        $('.deptNode').append(_html);
        $('.lsmd,.bgShow').fadeIn();
    }
    $(".close").bind("click",function() {
        $('.lsmd,.bgShow').fadeOut();
    });

    function detailAdverts(id,type){
        var _param = {};
        _param.id = id;
        _param.advertType = type;
        loadingPage("/view/showAdverts/advertsDetail",_param);
    }
</script>
