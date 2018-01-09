<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Joel Norris
 - Version: 0.1.0 (July 16, 2014)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.health.msgs.health">
	<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
		<head>
			<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
			<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
			<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
			<jsp:include page="/WEB-INF/views/common/includes/eventRunnerResources.jsp"/>
			<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/offender/style/profile.css"/>
			<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/health/style/health.css"/>
			<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/health/style/labWork.css"/>
			<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/JQuery/jquery.omis.lookup.js" type="text/javascript"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/JQuery/jquery.omis.lookupDialog.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/resources/health/scripts/JQuery/jquery.omis.labWorkReferral.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/resources/health/scripts/scheduleLabWorkReferral.js"></script>
			<script type="text/javascript">
				/* <![CDATA[ */
					//Track current lab work sample index
					var labWorkSampleItemIndex= ${labWorkSampleItemIndex};
					//Current facility id
					var facilityId = ${facility.id};
				/* ]]> */
			</script>
			<title>
				<c:choose>
					<c:when test="${operation.name eq 'SCHEDULE'}">
						<fmt:message key="scheduleLabWorkReferralTitleLabel"/>
					</c:when>
					<c:when test="${operation.name eq 'EDIT'}">
						<fmt:message key="editLabWorkReferralTitleLabel"/>
					</c:when>
					<c:when test="${operation.name eq 'RESCHEDULE'}">
						<fmt:message key="rescheduleLabWorkReferralTitleLabel"/>
					</c:when>
					<c:otherwise>
						<fmt:message key="scheduleLabWorkReferralTitleLabel"/>
					</c:otherwise>
				</c:choose>		
			</title>
		</head>
		<body>
			<h1>
				<c:choose>
					<c:when test="${operation.name eq 'SCHEDULE'}">
						<fmt:message key="scheduleLabWorkReferralTitleLabel"/>
					</c:when>
					<c:when test="${operation.name eq 'EDIT'}">
						<fmt:message key="editLabWorkReferralTitleLabel"/>
					</c:when>
					<c:when test="${operation.name eq 'RESCHEDULE'}">
						<fmt:message key="rescheduleLabWorkReferralTitleLabel"/>
					</c:when>
					<c:otherwise>
						<fmt:message key="scheduleLabWorkReferralTitleLabel"/>
					</c:otherwise>
				</c:choose>		
			</h1>
			<c:if test="${not empty offenderSummary}">
				<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp" />
			</c:if>
		<ul class="toolbar">
			<sec:authorize access="hasRole('ADMIN') or hasRole('HEALTH_CENTER')">
			<fmt:formatDate value="${weekStartDate}" pattern="MM/dd/yyyy" var="weekStartDate"/>
			<li>
				<a id="referralCenterLink" class="healthLink" href="${pageContext.request.contextPath}/health/referral/referralCenter.html?referralType=ALL&amp;facility=${facility.id}&amp;filterByStartDate=${weekStartDate}">
					<fmt:message key="title">
						<fmt:param value="${facility.name}"/>
					</fmt:message>
				</a>
			</li>
			</sec:authorize>
		</ul>
		<jsp:include page="includes/editForm.jsp"/>
		</body>
	</html>
</fmt:bundle>