<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include.inc.jsp"%>
<ul id="nav">
	<c:choose>
		<c:when test="${sessionUser.isSysAdmin}">
			<!-- 加载超管菜单(隐藏不需要展示的菜单) -->
			<c:forEach items="${menues}" var="pMenu">
			<c:if test="${pMenu.isShow == 1}">
				<li class="P-op" data-id="${pMenu.menuId}"><a url="${pMenu.menuUrl}" class="borderTop">${pMenu.menuName}<span class="nav-a-whiteDot"></span></a></li>
				<c:if test="${pMenu.subMenues.size() > 0}">
					<li class='C-op' forId='${pMenu.menuId}'>
					<c:forEach items="${pMenu.subMenues}" var="item">
						<c:if test="${item.isShow == 1}">
						<a url="${item.menuUrl}">>${item.menuName}<span class="nav-a-whiteDot"></span></a>
						</c:if>
					</c:forEach>
					</li>
				</c:if>
			</c:if>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<!-- 加载普通用户菜单 -->
			<c:forEach items="${menues}" var="pMenu">
			<li class="P-op" data-id="${pMenu.menuId}"><a url="${pMenu.menuUrl}" class="borderTop">${pMenu.menuName}<span class="nav-a-whiteDot"></span></a></li>
			<c:if test="${pMenu.subMenues.size() > 0}">
				<li class='C-op' forId='${pMenu.menuId}'>
				<c:forEach items="${pMenu.subMenues}" var="item">
					<a url="${item.menuUrl}">>${item.menuName}<span class="nav-a-whiteDot"></span></a>
				</c:forEach>
				</li>
			</c:if>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</ul>