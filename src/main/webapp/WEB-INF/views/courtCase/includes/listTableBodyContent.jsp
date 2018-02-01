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
 - Author: Stephen Abson
 - Author: Joel Norris
 - Author: Ryan Johns
 - Author: Josh Divine
 - Version: 0.1.3 (Jan 31, 2018)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.courtcase.msgs.courtCase">
<c:forEach var="courtCaseSummary" items="${courtCaseSummaries}" varStatus="status">
	<tr>
		<td>
			<a id="rowActionMenuLink${status.index}" class="actionMenuItem" href="${pageContext.request.contextPath}/courtCase/courtCaseListItemActionMenu.html?courtCase=${courtCaseSummary.courtCaseId}&amp;convictionCount=${courtCaseSummary.convictions}"></a>
		</td>
		<td>
			<c:out value="${courtCaseSummary.docket}"/>
		</td>
		<td>
			<c:out value="${courtCaseSummary.courtName}"/>
		</td>
		<td>
			<c:if test="${courtCaseSummary.judge}">
				<c:out value="${courtCaseSummary.judgeLastName}"/>,
				<c:out value="${courtCaseSummary.judgeFirstName}"/>
			</c:if>
		</td>
		<td>
			<c:choose>
				<c:when test="${courtCaseSummary.youthTransfer}">
					<fmt:message key="yesLabel" bundle="${commonBundle}"/>
				</c:when>
				<c:otherwise>
					<fmt:message key="noLabel" bundle="${commonBundle}"/>
				</c:otherwise>
			</c:choose>
		</td>
		<td>
			<fmt:formatDate value="${courtCaseSummary.pronouncementDate}" pattern="MM/dd/yyyy"/>
		</td>
		<td>
			<c:choose>
				<c:when test="${courtCaseSummary.dismissed}">
					<fmt:message key="yesLabel" bundle="${commonBundle}"/>
				</c:when>
				<c:otherwise>
					<fmt:message key="noLabel" bundle="${commonBundle}"/>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</c:forEach>
</fmt:bundle>