<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.questionnaire.msgs.questionnaire">
<head>
	<title>
		<fmt:message key="editQuestionTitle" />
	</title>
	
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/questionnaire/scripts/includes/jquery.omis.question.js?VERSION=1"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/questionnaire/scripts/question.js?VERSION=1"></script>
	<script type="text/javascript">
		var currentAllowedAnswerItemIndex = ${allowedAnswerItemIndex};
	</script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/questionnaire/style/questionnaire.css"/>
</head>
<body>
	<jsp:include page="includes/questionnaireTypeDetailsHeader.jsp"/>
	<h1>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/questionnaire/questionActionMenu.html?questionnaireSection=${allowedQuestion.questionnaireSection.id}"></a>
		<fmt:message key="editQuestionTitle" />
	</h1>
	<jsp:include page="includes/questionEditForm.jsp"/>
</body>
</fmt:bundle>
</html>