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
 - Author: Josh Divine
 - Version: 0.1.3 (Nov 28, 2018)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.caseload.msgs.officerCaseAssignment">
<head>
	<title>
		<c:choose>
			<c:when test="${not empty offender}">
				<fmt:message key="offenderCaseAssignmentsTitle"/>
				<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
			</c:when>
			<c:otherwise>
				<fmt:message key="officerCaseAssignmentsTitle"/>
			</c:otherwise>
		</c:choose>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderListResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/jQueryResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/serverConfigResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/messageResolverResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/caseload/officerCaseAssignment/style/officerCaseAssignment.css?VERSION=1"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/caseload/officerCaseAssignment/scripts/JQuery/jquery.omis.officerCaseAssignments.js?VERSION=2"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/caseload/officerCaseAssignment/scripts/officerCaseAssignments.js?VERSION=1"></script>
</head>
<body>
	<c:if test="${not empty offender}"><jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/></c:if>
	<h1>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/caseload/officerCaseAssignment/officerCaseAssignmentsActionMenu.html?offender=${offender.id}&userAccount=${userAccount.id}"></a>
		<c:choose>
			<c:when test="${not empty offender}">
				<fmt:message key="offenderCaseAssignmentsTitle"/>
			</c:when>
			<c:otherwise>
				<fmt:message key="officerCaseAssignmentsTitle"/>
			</c:otherwise>
		</c:choose>
	</h1>
	<c:if test="${not empty userAccount}"><jsp:include page="includes/officerCaseAssignmentSearchForm.jsp"/></c:if>
	<jsp:include page="includes/listTable.jsp"/>
</body>
</fmt:bundle>
</html>