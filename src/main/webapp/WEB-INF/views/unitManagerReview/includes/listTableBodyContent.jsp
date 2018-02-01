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
 - Table body content of unit manager reviews.
 -
 - Author: Josh Divine
 - Version: 0.1.0 (Jan 29, 2018)
 - Since: OMIS 3.0
 --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.unitmanagerreview.msgs.unitManagerReview">
<c:forEach var="unitManagerReview" items="${unitManagerReviewSummaries}" varStatus="status">
	<tr>
		<td>
			<a class="actionMenuItem" id="rowActionMenuLink${status.index}" href="${pageContext.request.contextPath}/unitManagerReview/unitManagerReviewsActionMenu.html?unitManagerReview=${unitManagerReview.id}"></a>
		</td>
		<td><fmt:formatDate value="${unitManagerReview.date}" pattern="MM/dd/yyyy"/></td>
		<td>
			<c:out value="${unitManagerReview.staffMemberLastName}, ${unitManagerReview.staffMemberFirstName}"/>
			<c:if test="${not empty unitManagerReview.staffMemberMiddleName}">
				<c:out value=" ${unitManagerReview.staffMemberMiddleName}"/>
			</c:if>
			<c:if test="${not empty unitManagerReview.staffTitle}">
				<c:out value=" ${unitManagerReview.staffTitle}"/>
			</c:if>
		</td>
	</tr>
</c:forEach>
</fmt:bundle>