<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.questionnaire.msgs.questionnaire">
<head>
	<title>
		<c:if test="${empty questionnaireType}">
			<fmt:message key="createQuestionnaireTypeTitle" />
		</c:if>
		<c:if test="${not empty questionnaireType}">
			<fmt:message key="editTitle" />&nbsp;<c:out value="${questionnaireType.title}"/>
		</c:if>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/questionnaire/scripts/questionnaireType.js?VERSION=1"></script>
</head>
<body>
	<h1>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/questionnaire/questionnaireTypeActionMenu.html"></a>
		<c:if test="${empty questionnaireType}">
			<fmt:message key="createQuestionnaireTypeTitle" />
		</c:if>
		<c:if test="${not empty questionnaireType}">
			<fmt:message key="editTitle" />&nbsp;<c:out value="${questionnaireType.title}"/>
		</c:if>
	</h1>
	<jsp:include page="includes/questionnaireTypeEditForm.jsp"/>
</body>
</fmt:bundle>
</html>