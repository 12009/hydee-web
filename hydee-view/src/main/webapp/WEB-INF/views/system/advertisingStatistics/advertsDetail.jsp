<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include.inc.jsp"%>

<div class="right-main">
    <div class="table-height30 marginTop20" id="searchParams">
        <a onclick="loadingPage('/view/showAdverts')" class=" width80 fakeBtn-hover-crimson floatLeft" >返回列表</a>
        <input id="id" name="id" value="${page.id}" type="hidden"/>
        <input id="advertType" name="advertType" value="${page.advertType}" type="hidden"/>
    </div>
    <ul class="ul-table-tHeader marginTop20 paddingLR20">
        <li class="width60">ID</li>
        <c:choose>
            <c:when test="${page.advertType == 0}">
                <li class="width300">广告投放省市</li>
            </c:when>
            <c:otherwise>
                <li class="width300">广告投放连锁</li>
            </c:otherwise>
        </c:choose>
        <li class="width100">昨日展现次数</li>
        <li class="width100">总展现次数</li>
        <div class="clear"></div>
    </ul>
    <c:forEach items="${reList}" var="item">
        <ul class="ul-table-tBody table-height60 withPadding20 ">
            <li class="width60">1</li>
            <c:choose>
                <c:when test="${page.advertType == 0}">
                    <li class="width300">${item.region} ${item.city}</li>
                </c:when>
                <c:otherwise>
                    <li class="width300">${item.companyName}</li>
                </c:otherwise>
            </c:choose>
            <li class="width100">${item.ystDisCnt}</li>
            <li class="width100">${item.totalDisCnt}</li>
            <div class="clear"></div>
        </ul>
    </c:forEach>
    <div class="tcdPageCode" count="${page.totalResult}" page="${page.currentPage}" size="${page.showCount}" form="/view/showAdverts/advertsDetail" filter="#searchParams"></div>
    <div class="clear"></div>
</div>

