<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Grievance edit screen.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.grievance.msgs.grievance">
<head>
	<title>
		<c:choose>
			<c:when test="${not empty grievance}">
				<fmt:message key="editGrievanceTitle"><fmt:param value="${subject.name}"/></fmt:message>
				<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
			</c:when>
			<c:otherwise>
				<fmt:message key="createGrievanceTitle"><fmt:param value="${subject.name}"/></fmt:message>
				<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
			</c:otherwise>
		</c:choose>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/messageResolverResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/sessionConfigResources.jsp"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/grievance/scripts/grievance.js?VERSION=1"> </script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	<h1>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/grievance/grievanceActionMenu.html?offender=${offenderSummary.id}"></a>
		<c:choose>
			<c:when test="${not empty grievance}"><fmt:message key="editGrievanceTitle"><fmt:param value="${subject.name}"/></fmt:message></c:when>
			<c:otherwise><fmt:message key="createGrievanceTitle"><fmt:param value="${subject.name}"/></fmt:message></c:otherwise>
		</c:choose>
	</h1>
	<jsp:include page="includes/editForm.jsp"/>
</body>
</fmt:bundle>
</html>