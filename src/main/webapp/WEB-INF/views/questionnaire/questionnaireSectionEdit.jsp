<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.questionnaire.msgs.questionnaire">
<head>
	<title>
		<c:if test="${empty questionnaireSection}">
			<fmt:message key="createQuestionnaireSectionTitle" />
		</c:if>
		<c:if test="${not empty questionnaireSection}">
			<fmt:message key="editTitle" />&nbsp;<c:out value="${questionnaireSection.title}"/>
		</c:if>
	</title>
	<title><fmt:message key="createQuestionnaireTitle" />&nbsp;<c:out value="${questionnaireType.title}"/></title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/questionnaire/style/questionnaire.css"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/questionnaire/scripts/questionnaireSection.js?VERSION=1"></script>
</head>
<body>
	<jsp:include page="includes/questionnaireTypeDetailsHeader.jsp"/>
	<h1>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/questionnaire/questionnaireSectionActionMenu.html?questionnaireType=${questionnaireType.id}"></a>
		<c:if test="${empty questionnaireSection}">
			<fmt:message key="createQuestionnaireSectionTitle" />
		</c:if>
		<c:if test="${not empty questionnaireSection}">
			<fmt:message key="editTitle" />&nbsp;<c:out value="${questionnaireSection.title}"/>
		</c:if>
	</h1>
	<jsp:include page="includes/questionnaireSectionEditForm.jsp"/>
</body>
</fmt:bundle>
</html>