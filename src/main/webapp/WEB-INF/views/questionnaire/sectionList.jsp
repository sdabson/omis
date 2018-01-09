<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.questionnaire.msgs.questionnaire">
<head>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderListResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerListResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/questionnaire/scripts/questionnaireSections.js?VERSION=1"> </script>
	<title>
		<c:out value="${administeredQuestionnaireSummary.questionnaireTitle}"/>
	</title>
</head>
 <body>
 	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
 	<h1>
 		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/questionnaire/administeredQuestionnaireSectionsActionMenu.html?offender=${offender.id}&administeredQuestionnaire=${administeredQuestionnaire.id}"></a>
		<c:out value="${administeredQuestionnaireSummary.questionnaireTitle}"/>
	</h1>
	<jsp:include page="includes/sectionListTable.jsp"/>
</body>
</fmt:bundle>
</html>