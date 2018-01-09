<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.questionnaire.msgs.questionnaire">
<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/questionnaire/style/questionnaire.css"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderListResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerListResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/questionnaire/scripts/questions.js?VERSION=1"> </script>
	<title>
		<fmt:message key="questionListHeader"/>
	</title>
</head>
 <body>
 	<jsp:include page="includes/questionnaireTypeDetailsHeader.jsp"/>
 	<h1>
 		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/questionnaire/questionsActionMenu.html?questionnaireSection=${questionnaireSection.id}"></a>
		<fmt:message key="questionListHeader"/>
	</h1>
	<jsp:include page="includes/questionListTable.jsp"/>
</body>
</fmt:bundle>
</html>