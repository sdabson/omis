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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.earlyreleasetracking.msgs.earlyReleaseRequest">
<c:forEach var="summary" items="${earlyReleaseRequestSummaries}">
	<tr>
		<td><a class="actionMenuItem rowActionMenuItem" href="${pageContext.request.contextPath}/earlyReleaseTracking/earlyReleaseRequestsRowActionMenu.html?earlyReleaseRequest=${summary.earlyReleaseRequestId}"></a></td>
		<td>
			<fmt:message key="offenderNameLabel">
				<fmt:param value="${summary.offenderLastName}" />
				<fmt:param value="${summary.offenderFirstName}" />
				<fmt:param value="${summary.offenderNumber}" />
			</fmt:message>
		</td>
		<td>
			<fmt:formatDate value="${summary.eligibilityDate}" pattern="MM/dd/yyyy" />
		</td>
		<td>
			<c:out value="${summary.docketValue}" />
		</td>
		<td>
			<fmt:formatDate value="${summary.requestDate}" pattern="MM/dd/yyyy" />
		</td>
		<td>
			<c:out value="${summary.earlyReleaseRequestStatusCategoryName}" />
		</td>
		<td>
			<c:out value="${summary.monthsOnProbation}" />
		</td>
		<td>
			<c:out value="${summary.monthsInResidence}" />
		</td>
		<td>
			<c:out value="${summary.monthsEmployed}" />
		</td>
	</tr>
</c:forEach>
</fmt:bundle>