<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (${date})
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.offender.msgs.alternativeOffenderName">
<head>
	<title>
		<fmt:message key="alternativeOffenderNamesTitle"/>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderListResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/jQueryResources.jsp"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/offender/scripts/JQuery/jquery.omis.alternativeOffenderNames.js"> </script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/offender/scripts/alternativeOffenderNames.js"> </script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	<h1>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/offender/name/alternative/alternativeNamesActionMenu.html?offender=${offender.id}"></a>
		<fmt:message key="alternativeOffenderNamesTitle"/>
	</h1>
	<jsp:include page="includes/listTable.jsp"/>
</body>
</fmt:bundle>
</html>