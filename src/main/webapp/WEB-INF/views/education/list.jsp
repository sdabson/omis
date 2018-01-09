<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.education.msgs.education">
<head>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderListResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerListResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/education/scripts/educations.js?VERSION=1"> </script>
	<title>
		<fmt:message key="educationListHeader"/>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
	</title>
</head>
 <body>
 	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
 	<h1>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/education/educationsActionMenu.html?offender=${offender.id}"></a>
		<fmt:message key="educationListHeader"/>
	</h1>
	<jsp:include page="includes/listTable.jsp"/>
	<h1 style="margin-top:20px;">
		<a class="actionMenuItem" id="documentActionMenuLink" href="${pageContext.request.contextPath}/education/educationDocumentsActionMenu.html?offender=${offender.id}"></a>
		<fmt:message key="documentsListHeader"/>
	</h1>
	<jsp:include page="includes/documentListTable.jsp"/>
	
</body>
</fmt:bundle>
</html>