<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Screen to list user accounts.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.user.msgs.userAccount">
<head>
	<title>
		<c:choose>
			<c:when test="${not empty user}">
				<fmt:message key="userAccountsWithUserTitle">
				<fmt:param>
					<c:out value="${user.name.lastName}"/>, <c:out value="${user.name.firstName}"/>
				</fmt:param>
				</fmt:message>
			</c:when>
			<c:otherwise>
				<fmt:message key="userAccountsTitle"/>
			</c:otherwise>
		</c:choose>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerMetas.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerGeneralResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerListResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/linksResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/listResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/jQueryResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/serverConfigResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerToolbarResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/user/admin/style/userAdmin.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/user/admin/scripts/userAccounts.js"> </script>
</head>
<body>
	<h1>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/user/admin/userAccount/userAccountsActionMenu.html?user=${user.id}"></a>
		<c:choose>
			<c:when test="${not empty user}">
				<fmt:message key="userAccountsWithUserTitle">
				<fmt:param>
					<c:out value="${user.name.lastName}"/>, <c:out value="${user.name.firstName}"/>
				</fmt:param>
				</fmt:message>
			</c:when>
			<c:otherwise>
				<fmt:message key="userAccountsTitle"/>
			</c:otherwise>
		</c:choose>
	</h1>
	<jsp:include page="includes/listTable.jsp"/>
</body>
</fmt:bundle>
</html>