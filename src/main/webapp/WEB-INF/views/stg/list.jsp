<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.stg.msgs.stg">
<head>
	<title>
		<fmt:message key="securityThreatGroupsTitle"/>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderListResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/jQueryResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/serverConfigResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/messageResolverResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/stg/scripts/affiliations.js"> </script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	<h1>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/stg/affiliationsActionMenu.html?offender=${offenderSummary.id}"></a>
		<fmt:message key="stgAffiliationsTitle"/>
	</h1>
	<jsp:include page="affiliation/includes/listTable.jsp"/>
	<sec:authorize access="hasRole('STG_ACTIVITY_VIEW') or hasRole('ADMIN')">
		<h1>
			<a class="actionMenuItem" id="activityActionMenuLink" href="${pageContext.request.contextPath}/stg/activitiesActionMenu.html?offender=${offenderSummary.id}"></a>
			<fmt:message key="stgActivitiesTitle"/>
		</h1>
	
		<jsp:include page="activity/includes/listTable.jsp"/>
	</sec:authorize>
</body>
</fmt:bundle>
</html>