<?xml version="1.0" encoding="UTF-8"?>

<%--
 - OMIS - Offender Management Information System
 - Copyright (C) 2011 - 2017 State of Montana
 -
 - This program is free software: you can redistribute it and/or modify
 - it under the terms of the GNU General Public License as published by
 - the Free Software Foundation, either version 3 of the License, or
 - (at your option) any later version.
 -
 - This program is distributed in the hope that it will be useful,
 - but WITHOUT ANY WARRANTY; without even the implied warranty of
 - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 - GNU General Public License for more details.
 -
 - You should have received a copy of the GNU General Public License
 - along with this program.  If not, see <http://www.gnu.org/licenses/>.
 --%>

<%--
 - Listing screen for grievances.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:setBundle basename="omis.grievance.msgs.grievance" var="grievanceBundle"/>
<head>
	<title>
		<c:choose>
			<c:when test="${not empty offender}">
				<fmt:message key="listGrievancesByOffenderTitle" bundle="${grievanceBundle}"/>
				<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
			</c:when>
			<c:when test="${not empty location}">
				<fmt:message key="listGrievancesByLocationTitle" bundle="${grievanceBundle}">
					<fmt:param>${location}</fmt:param>
				</fmt:message>
			</c:when>
			<c:when test="${not empty grievanceSearchForm}">
				<fmt:message key="searchGrievancesTitle" bundle="${grievanceBundle}"/>
			</c:when>
			<c:otherwise>ERROR: Offender nor Location not Form Specified</c:otherwise>
		</c:choose>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderListResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/jQueryResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/serverConfigResources.jsp"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/grievance/scripts/grievances.js?VERSION=1"> </script>
</head>
<body>
	<c:if test="${not empty offenderSummary}">
		<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	</c:if>
	<h1>
		<a href="${pageContext.request.contextPath}/grievance/grievancesActionMenu.html?offender=${offender.id}&amp;locaiton=${location.id}" class="actionMenuItem" id="actionMenuLink"></a>
		<c:choose>
			<c:when test="${not empty offender}"><fmt:message key="listGrievancesByOffenderTitle" bundle="${grievanceBundle}"/></c:when>
			<c:when test="${not empty location}">
				<fmt:message key="listGrievancesByLocationTitle" bundle="${grievanceBundle}">
					<fmt:param>${location.name}</fmt:param>
				</fmt:message>
			</c:when>
			<c:when test="${not empty grievanceSearchForm}"><fmt:message key="searchGrievancesTitle" bundle="${grievanceBundle}"/></c:when>
			<c:otherwise>ERROR: Offender nor Location nor Form Specified</c:otherwise>
		</c:choose>
	</h1>
	<c:if test="${not empty grievanceSearchForm}">
		<jsp:include page="includes/searchForm.jsp" />
	</c:if>
	<jsp:include page="includes/listTable.jsp"/>
</body>
</html>