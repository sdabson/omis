<?xml version="1.0" encoding="UTF-8"?>
<%--
  - Placement home screen.
  -
  - Author: Stephen Abson
  - Author: Sheronda Vaughn
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<fmt:setBundle basename="omis.placement.msgs.placement" var="placementBundle"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<title>
		<fmt:message key="placementHomeTitle" bundle="${placementBundle}"/>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/serverConfigResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderListResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/common/style/panel.css"/>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/common/style/data.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/placement/scripts/home.js"> </script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	<div class="panel">
		<h2>
			<a class="actionMenuItem" id="placementActionMenu" href="${pageContext.request.contextPath}/placement/placementActionMenu.html?offender=${offender.id}&amp;effectiveDate=<fmt:formatDate value='${effectiveDate}' pattern='MM/dd/yyyy'/>&amp;effectiveTime=<fmt:formatDate value='${effectiveDate}' pattern='h:mm%20a'/>&amp;correctionalStatus=${placementSummary.correctionalStatusId}&amp;correctionalStatusChangeAllowed=${correctionalStatusChangeAllowed}&amp;supervisoryOrganization=${placementSummary.supervisoryOrganizationId}"></a>
			<fmt:message key="activePlacementHeader" bundle="${placementBundle}"/>
		</h2>
		<c:choose>
			<c:when test="${not empty placementSummary}">
				<table class="dataTable">
					<tbody>
						<tr>
							<td class="header"><fmt:message key="currentCorrectionalStatusLabel" bundle="${placementBundle}"/></td>
							<td><c:out value="${placementSummary.correctionalStatusName}"/></td>
							<td class="header"><fmt:message key="currentCorrectionalStatusStartDateLabel" bundle="${placementBundle}"/></td>
							<td class="datetime"><fmt:formatDate value="${placementSummary.correctionalStatusStartDate}" pattern="MM/dd/yyyy h:mm a"/></td>
							<td class="header"><fmt:message key="currentCorrectionalStatusEndDateLabel" bundle="${placementBundle}"/></td>
							<td class="datetime"><fmt:formatDate value="${placementSummary.correctionalStatusEndDate}" pattern="MM/dd/yyyy h:mm a"/></td>
							<td class="header"><fmt:message key="currentCorrectionalStatusDayCountLabel" bundle="${placementBundle}"/></td>
							<td>
								<c:set var="dayCount" value="${placementSummary.correctionalStatusDayCount}" scope="request"/>
								<jsp:include page="/WEB-INF/views/includes/dayCount.jsp"/>
							</td>
						</tr>
						<tr>
							<td class="header"><fmt:message key="currentSupervisoryOrganizationLabel" bundle="${placementBundle}"/></td>
							<td><c:out value="${placementSummary.supervisoryOrganizationName}"/></td>
							<td class="header"><fmt:message key="currentSupervisoryOrganizationStartDateLabel" bundle="${placementBundle}"/></td>
							<td class="datetime"><fmt:formatDate value="${placementSummary.supervisoryOrganizationStartDate}" pattern="MM/dd/yyyy h:mm a"/></td>
							<td class="header"><fmt:message key="currentSupervisoryOrganizationEndDateLabel" bundle="${placementBundle}"/></td>
							<td class="datetime"><fmt:formatDate value="${placementSummary.supervisoryOrganizationEndDate}" pattern="MM/dd/yyyy h:mm a"/></td>
							<td class="header"><fmt:message key="currentSupervisoryOrganizationDayCountLabel" bundle="${placementBundle}"/></td>
							<td>
								<c:set var="dayCount" value="${placementSummary.supervisoryOrganizationDayCount}" scope="request"/>
								<jsp:include page="/WEB-INF/views/includes/dayCount.jsp"/>
							</td>
						</tr>
						<c:choose>
						<c:when test="${placementSummary.location}">
						<tr>
							<td class="header"><fmt:message key="currentLocationLabel" bundle="${placementBundle}"/></td>
							<td><c:out value="${placementSummary.locationName}"/></td>
							<td class="header"><fmt:message key="currentLocationStartDateLabel" bundle="${placementBundle}"/></td>
							<td class="datetime"><fmt:formatDate value="${placementSummary.locationStartDate}" pattern="MM/dd/yyyy h:mm a"/></td>
							<td class="header"><fmt:message key="currentLocationReasonLabel" bundle="${placementBundle}"/></td>
							<td><c:out value="${placementSummary.locationReasonName}"/></td>
							<td class="header"><fmt:message key="currentLocationDayCountLabel" bundle="${placementBundle}"/></td>
							<td>
								<c:set var="dayCount" value="${placementSummary.locationDayCount}" scope="request"/>
								<jsp:include page="/WEB-INF/views/includes/dayCount.jsp"/>
							</td>
						</tr>
						</c:when>
						<c:when test="${placementSummary.address}">
						<tr>
							<td class="header"><fmt:message key="currentAddressLabel" bundle="${placementBundle}"/></td>
							<td>
								<c:set var="addressSummarizable" value="${placementSummary}" scope="request"/>
								<jsp:include page="/WEB-INF/views/address/includes/addressSummary.jsp"/>
							</td>	
						</tr>
						</c:when>
						</c:choose>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
				<div class="message"><fmt:message key="noCurrentPlacementMessage" bundle="${placementBundle}"/></div>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="panel">
		<h2>
			<a class="actionMenuItem hidden" id="supervisoryOrganizationsActionMenu" href="#"></a>
			<fmt:message key="placementListHeader" bundle="${placementBundle}"/>
		</h2>
		<table class="listTable">
			<thead>
				<tr>
					<th><fmt:message key="placementListHeader" bundle="${placementBundle}"/></th>
					<th class="datetime"><fmt:message key="startDateLabel" bundle="${commonBundle}"/></th>
					<th class="datetime"><fmt:message key="endDateLabel" bundle="${commonBundle}"/></th>
					<th><fmt:message key="placementStartReasonLabel" bundle="${placementBundle}"/></th>
					<th><fmt:message key="placementEndReasonLabel" bundle="${placementBundle}"/></th>
					<th class="date"><fmt:message key="placementDayCountLabel" bundle="${placementBundle}"/></th>
				</tr>
			</thead>
			<tbody id="supervisoryOrganizationTerms">
				<c:forEach var="supervisoryOrganizationTermSummary" items="${supervisoryOrganizationTermSummaries}">
				<tr id="supervisoryOrganizationTermSummary${supervisoryOrganizationTermSummary.id}TableRow" class="supervisoryOrganizationTerm">
					<td>
						<c:out value="${supervisoryOrganizationTermSummary.supervisoryOrganizationName}"/>
						<c:if test="${supervisoryOrganizationTermSummary.locationCount gt 0}">
						(<a id="supervisoryOrganizationTermSummary${supervisoryOrganizationTermSummary.id}LocationTermSummaryLink" class="locationTermsLink" href="${pageContext.request.contextPath}/placement/locationTermSummaries.html?supervisoryOrganizationTerm=${supervisoryOrganizationTermSummary.id}&amp;effectiveDate=<fmt:formatDate value='${effectiveDate}' pattern='MM/dd/yyyy'/>&amp;nocache=${effectiveDate.time}" title="<fmt:message key='locationChangesDuringSupervisionMessage' bundle='${placementBundle}'><fmt:param value="${supervisoryOrganizationTermSummary.locationCount}"/><fmt:param value="${supervisoryOrganizationTermSummary.supervisoryOrganizationName}"/></fmt:message>"><c:out value="${supervisoryOrganizationTermSummary.locationCount}"/></a>)						
						</c:if>
					</td>
					<td><fmt:formatDate value="${supervisoryOrganizationTermSummary.startDate}" pattern="MM/dd/yyyy h:mm a"/></td>
					<td><fmt:formatDate value="${supervisoryOrganizationTermSummary.endDate}" pattern="MM/dd/yyyy h:mm a"/></td>
					<td><c:out value="${supervisoryOrganizationTermSummary.startReasonName}"/></td>
					<td><c:out value="${supervisoryOrganizationTermSummary.endReasonName}"/></td>
					<td>
						<c:set var="dayCount" value="${supervisoryOrganizationTermSummary.dayCount}" scope="request"/>
						<jsp:include page="/WEB-INF/views/includes/dayCount.jsp"/>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="panel">
		<h2>
			<a class="actionMenuItem hidden" id="correctionalStatusesActionMenu" href="#"></a>
			<fmt:message key="correctionalStatusListHeader" bundle="${placementBundle}"/>
		</h2>
		<table class="listTable">
			<thead>
				<tr>
					<th><fmt:message key="correctionalStatusNameLabel" bundle="${placementBundle}"/></th>
					<th class="datetime"><fmt:message key="startDateLabel" bundle="${commonBundle}"/></th>
					<th class="datetime"><fmt:message key="endDateLabel" bundle="${commonBundle}"/></th>
					<th><fmt:message key="correctionalStatusStartReasonLabel" bundle="${placementBundle}"/></th>
					<th><fmt:message key="correctionalStatusEndReasonLabel" bundle="${placementBundle}"/></th>
					<th class="date"><fmt:message key="correctionalStatusDayCountLabel" bundle="${placementBundle}"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="correctionalStatusTermSummary" items="${correctionalStatusTermSummaries}">
				<tr>
					<td><c:out value="${correctionalStatusTermSummary.correctionalStatusName}"/></td>
					<td><fmt:formatDate value="${correctionalStatusTermSummary.startDate}" pattern="MM/dd/yyyy h:mm a"/></td>
					<td><fmt:formatDate value="${correctionalStatusTermSummary.endDate}" pattern="MM/dd/yyyy h:mm a"/></td>
					<td><c:out value="${correctionalStatusTermSummary.startReasonName}"/></td>
					<td><c:out value="${correctionalStatusTermSummary.endReasonName}"/></td>
					<td>
						<c:set var="dayCount" value="${correctionalStatusTermSummary.dayCount}" scope="request"/>
						<jsp:include page="/WEB-INF/views/includes/dayCount.jsp"/>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<jsp:include page="/WEB-INF/views/includes/effectiveAsOf.jsp"/>
</body>
</html>