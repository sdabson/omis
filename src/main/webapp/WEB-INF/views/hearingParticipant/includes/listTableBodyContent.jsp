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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.hearingparticipant.msgs.hearingParticipant">
<c:forEach var="summary" items="${hearingParticipantSummaries}">
<tr>
	<td><a class="actionMenuItem rowActionMenuItem" href="${pageContext.request.contextPath}/hearingParticipant/hearingParticipantsRowActionMenu.html?hearingParticipant=${summary.hearingParticipantId}"></a></td>
	<td>
		<c:out value="${summary.lastName}, ${summary.firstName } ${summary.middleName ne null ? summary.middleName : ''}" />
	</td>
	<td>
		<fmt:message key="${summary.category}CategoryLabel"/>
	</td>
	<td>
		<fmt:message key="${summary.boardApproved}ApprovedLabel"/>
	</td>
	<td>
		<fmt:message key="${summary.facilityApproved}ApprovedLabel"/>
	</td>
</tr>
</c:forEach>
</fmt:bundle>	