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
 - Table body content of prison terms.
 -
 - Author: Trevor Isles
 - Author: Josh Divine
 - Version: 0.1.1 (Feb 11, 2019)
 - Since: OMIS 3.0
 --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.prisonterm.msgs.prisonTerm">
<c:forEach var="prisonTerm" items="${prisonTerms}" varStatus="status">
	<tr class="${activeClass}">
		<td>
			<a class="actionMenuItem" id="rowActionMenuLink${status.index}" href="${pageContext.request.contextPath}/prisonTerm/prisonTermsActionMenu.html?prisonTerm=${prisonTerm.id}&offender=${offender.id}"></a>
		</td>
		<td><fmt:formatDate value="${prisonTerm.actionDate}" pattern="MM/dd/yyyy"/></td>
		<td><fmt:formatDate value="${prisonTerm.paroleEligibilityDate}" pattern="MM/dd/yyyy"/></td>
		<td><fmt:formatDate value="${prisonTerm.projectedDischargeDate}" pattern="MM/dd/yyyy"/></td>
		<td><c:choose>
				<c:when test="${prisonTerm.sentenceToFollow}">
					<fmt:message key="yesLabel" bundle="${commonBundle}"/>
				</c:when>
				<c:otherwise>
					<fmt:message key="noLabel" bundle="${commonBundle}"/>
				</c:otherwise>
			</c:choose>
		</td>
		<td><c:if test="${not empty prisonTerm.verificationUserUsername}">
			<c:out value="${prisonTerm.verificationUserLastName}, ${prisonTerm.verificationUserFirstName} (${prisonTerm.verificationUserUsername})"/></c:if></td>
		<td><fmt:formatDate value="${prisonTerm.verificationDate}" pattern="MM/dd/yyyy"/></td>
		<td><c:if test="${not empty prisonTerm.status}"><fmt:message key="prisonTermStatusLabel.${prisonTerm.status}"/></c:if></td>
	</tr>
</c:forEach>
</fmt:bundle>