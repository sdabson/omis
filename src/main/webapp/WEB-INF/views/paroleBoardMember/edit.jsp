<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Josh Divine
 - Version: 0.1.0 (Nov 9, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.paroleboardmember.msgs.paroleBoardMember">
<head>
	<title>
		<c:choose>
			<c:when test="${not empty paroleBoardMember}">
				<fmt:message key="editParoleBoardMemberTitle"/>
			</c:when>
			<c:otherwise>
				<fmt:message key="createParoleBoardMemberTitle"/>
			</c:otherwise>
		</c:choose>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/jQueryResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/serverConfigResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/messageResolverResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/SessionConfig.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/paroleBoardMember/scripts/JQuery/jquery.omis.paroleBoardMember.js?VERSION=1"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/paroleBoardMember/scripts/paroleBoardMember.js?VERSION=1"></script>
</head>

<body>
	<h1>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/paroleBoardMember/paroleBoardMemberActionMenu.html"></a>
		<c:choose>
			<c:when test="${not empty paroleBoardMember}">
				<fmt:message key="editParoleBoardMemberTitle"/>
			</c:when>
			<c:otherwise>
				<fmt:message key="createParoleBoardMemberTitle"/>
			</c:otherwise>
		</c:choose>
	</h1>
	<jsp:include page="includes/editForm.jsp"/>
</body>
</fmt:bundle>
</html>