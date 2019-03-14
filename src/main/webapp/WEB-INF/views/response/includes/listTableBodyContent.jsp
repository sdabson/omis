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
 - Table body content of responses.
 -
 - Author: Josh Divine
 - Version: 0.1.0 (Mar 6, 2019)
 - Since: OMIS 3.0
 --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.response.msgs.response">
<c:forEach var="response" items="${responseSummaries}" varStatus="status">
	<tr>
		<td>
			<a class="actionMenuItem" id="rowActionMenuLink${status.index}" href="${pageContext.request.contextPath}/response/responsesActionMenu.html?response=${response.responseId}"></a>
		</td>
		<td><c:out value="${response.gridName}"/></td>
		<td><fmt:message key="responseCategoryLabel.${response.category.name}"/></td>
		<td><c:out value="${response.levelName}"/></td>
		<td><c:out value="${response.responseDescription}"/></td>
		<td>
			<c:choose>
				<c:when test="${response.valid}"><fmt:message key="yesLabel" bundle="${commonBundle}"/></c:when>
				<c:otherwise><fmt:message key="noLabel" bundle="${commonBundle}"/></c:otherwise>
			</c:choose>
		</td>
	</tr>
</c:forEach>
</fmt:bundle>