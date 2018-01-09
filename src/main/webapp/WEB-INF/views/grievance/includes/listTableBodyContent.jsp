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
 - List table body content for grievances.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:forEach var="grievanceSummary" items="${grievanceSummaries}">
	<tr>
		<td>
			<a class="actionMenuItem" href="${pageContext.request.contextPath}/grievance/grievancesActionMenu.html?grievance=${grievanceSummary.id}"></a>
		</td>
		<td>
			<c:out value="${grievanceSummary.grievanceNumber}"/>
		</td>
		<c:if test="${empty offender}">
		<td>
			<c:set var="offenderSummary" value="${grievanceSummary}" scope="request"/>
			<jsp:include page="/WEB-INF/views/offender/includes/offenderSummary.jsp"/>
		</td>
		</c:if>
		<td>
			<fmt:formatDate value="${grievanceSummary.openedDate}" pattern="MM/dd/yyyy"/>
		</td>
		<td>
			<c:out value="${grievanceSummary.subjectName}"/>
		</td>
		<td>
			<c:out value="${grievanceSummary.statusName}"/>
		</td>
		<td>
			<c:out value="${grievanceSummary.description}"/>
		</td>
	</tr>
</c:forEach>