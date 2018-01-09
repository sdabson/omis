<?xml version="1.0" encoding="UTF-8"?>
<%-- Author: Ryan Johns
 - Version: 0.1.0 (Jun 15, 2017)
 - Since: OMIS 3.0 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.caseload.msgs.caseload">
	<head>
		<c:choose>
		<c:when test="${empty caseload}">
			<fmt:message key="createCaseloadLabel" var="titleLabel"/>
		</c:when>
		<c:otherwise>
			<fmt:message key="editCaseloadLabel" var="titleLabel"/>
		</c:otherwise>
		</c:choose>
		
		<title>
			<c:out value="${titleLabel}"/>
		</title>
		<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/caseload/scripts/caseload.js?VERSION=1"></script>
	</head>
	<body>
		<h1>
			<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/caseload/caseloadActionMenu.html"></a>
			<c:out value="${titleLabel}"/>
		</h1>
		<jsp:include page="includes/editForm.jsp"/>
	</body>
</fmt:bundle>
</html>