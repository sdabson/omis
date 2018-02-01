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
  - List table body content.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.docket.msgs.docket" var="docketBundle"/>
<c:forEach var="courtCaseSummary" items="${courtCaseSummaries}">
	<tr>
		<td><a href="${pageContext.request.contextPath}/offenseTerm/offenseTermsActionMenu.html?courtCase=${courtCaseSummary.courtCaseId}" class="actionMenuItem"></a></td>
		<td>
			<fmt:message key="docketText" bundle="${docketBundle}">
				<fmt:param>${courtCaseSummary.docket}</fmt:param>
				<fmt:param>${courtCaseSummary.courtName}</fmt:param>
			</fmt:message>
		</td>
		<td><c:if test="${courtCaseSummary.judge}"><c:out value="${courtCaseSummary.judgeLastName}"/>, <c:out value="${courtCaseSummary.judgeFirstName}"/></c:if></td>
		<td>
			<c:choose>
				<c:when test="${courtCaseSummary.convictionOverturned}">
					<fmt:message key="yesLabel" bundle="${commonBundle}"/>
				</c:when>
				<c:otherwise>
					<fmt:message key="noLabel" bundle="${commonBundle}"/>
				</c:otherwise>
			</c:choose>
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