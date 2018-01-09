<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.health.msgs.health">
<head>
	<title>
		<c:choose>
			<c:when test="${operation.name eq 'SCHEDULE'}">
				<fmt:message key="scheduleAuthorizedExternalReferralTitle"/>
			</c:when>
			<c:when test="${operation.name eq 'EDIT'}">
				<fmt:message key="editScheduledAuthorizedExternalReferralTitle"/>
			</c:when>
			<c:when test="${operation.name eq 'RESCHEDULE'}">
				<fmt:message key="rescheduleAuthorizedExternalReferralTitle"/>
			</c:when>
			<c:otherwise>
				<fmt:message key="scheduleAuthorizedExternalReferralTitle"/>
			</c:otherwise>
		</c:choose>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/messageResolverResources.jsp"/>	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/health/style/health.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/jquery/ui/jquery.ptTimeSelect.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/3rdparty/JQuery/ui/jquery.ptTimeSelect.js"> </script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/health/scripts/scheduleAuthorizedExternalReferral.js"> </script>
</head>
<body>
	<h1>
		<c:choose>
			<c:when test="${operation.name eq 'SCHEDULE'}">
				<fmt:message key="scheduleAuthorizedExternalReferralTitle"/>
			</c:when>
			<c:when test="${operation.name eq 'EDIT'}">
				<fmt:message key="editScheduledAuthorizedExternalReferralTitle"/>
			</c:when>
			<c:when test="${operation.name eq 'RESCHEDULE'}">
				<fmt:message key="rescheduleAuthorizedExternalReferralTitle"/>
			</c:when>
			<c:otherwise>
				<fmt:message key="scheduleAuthorizedExternalReferralTitle"/>
			</c:otherwise>
		</c:choose>
	</h1>
	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	<ul class="toolbar">
		<sec:authorize access="hasRole('ADMIN') or hasRole('HEALTH_CENTER')">
		<li>
			<a id="referralCenterLink" class="healthLink" href="${pageContext.request.contextPath}/health/referral/referralCenter.html?referralType=ALL&amp;facility=${facility.id}">
				<fmt:message key="title">
					<fmt:param value="${facility.name}"/>
				</fmt:message>
			</a>
		</li>
		</sec:authorize>
	</ul>
	<jsp:include page="/WEB-INF/views/health/referral/external/authorization/includes/summary.jsp"/>
	<jsp:include page="includes/editForm.jsp"/>
</body>
</fmt:bundle>
</html>