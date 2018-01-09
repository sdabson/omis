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
 - Supervision profile screen.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:setBundle basename="omis.supervision.msgs.placementTerm" var="placementTermBundle"/>
<fmt:setBundle basename="omis.supervision.msgs.supervisoryOrganizationTerm" var="supervisoryOrganizationTermBundle"/>
<fmt:setBundle basename="omis.supervision.msgs.correctionalStatusTerm" var="correctionalStatusTermBundle"/>
<fmt:bundle basename="omis.supervision.msgs.profile">
<head>
	<title><fmt:message key="supervisionProfileHeader"/></title>
	<jsp:include page="/WEB-INF/views/common/includes/headerMetas.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerGeneralResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderHeaderResources.jsp"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/supervision/style/links.css"/>
</head>
<body>
	<h1><fmt:message key="supervisionProfileHeader"/></h1>
	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	<ul class="taskLinks">
		<li class="header"><h2><fmt:message key="supervisionTasksHeader"/></h2></li>
		<sec:authorize access="hasRole('PLACEMENT_TERM_LIST') or hasRole('ADMIN')">
			<li><a class="listLink" href="${pageContext.request.contextPath}/supervision/placementTerm/list.html?offender=${offenderSummary.id}"><fmt:message key="listPlacementTermsLink" bundle="${placementTermBundle}"/></a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('CORRECTIONAL_STATUS_TERM_LIST') or hasRole('ADMIN')">
			<li><a class="listLink" href="${pageContext.request.contextPath}/supervision/correctionalStatusTerm/list.html?offender=${offenderSummary.id}"><fmt:message key="listCorrectionalStatusTermsLink" bundle="${correctionalStatusTermBundle}"/></a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('SUPERVISORY_ORGANIZATION_TERM_LIST') or hasRole('ADMIN')">
			<li><a class="listLink" href="${pageContext.request.contextPath}/supervision/supervisoryOrganizationTerm/list.html?offender=${offenderSummary.id}"><fmt:message key="listSupervisoryOrganizationTermsLink" bundle="${supervisoryOrganizationTermBundle}"/></a></li>
		</sec:authorize>
	</ul>
</body>
</fmt:bundle>
</html>