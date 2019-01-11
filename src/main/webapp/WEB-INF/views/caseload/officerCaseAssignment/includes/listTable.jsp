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
 - Author: Annie Wahl
 - Version: 0.1.3 (Jan 8, 2019)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.caseload.msgs.officerCaseAssignment">
<table id="${rowPrefix}officerCaseAssignmentListTable" class="listTable">
	<thead>
		<c:if test="${not empty rowPrefix}">
			<tr>
				<th colspan="6"><fmt:message key="${rowPrefix}OfficerCaseAssignmentLabel"/></th>
			</tr>
		</c:if>
		<tr>			
			<th class="operations"/>
			<th><fmt:message key="startDateLabel" bundle="${commonBundle}"/></th>
 			<th><fmt:message key="endDateLabel" bundle="${commonBundle}"/></th>
 			<c:choose>
 				<c:when test="${empty offender and empty officerCaseAssignmentId}">
 					<th><fmt:message key="offenderLabel"/></th>
 				</c:when>
 				<c:otherwise>
 					<th><fmt:message key="officerLabel"/></th>
 				</c:otherwise>
 			</c:choose>
 			<c:if test="${officerCaseAssignmentSearchForm.allowInterstateCompact}">
 				<th><fmt:message key="interstateCompactLabel"/></th>
 			</c:if>
			<th><fmt:message key="supervisionTypeLabel"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>