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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.earlyreleasetracking.msgs.earlyReleaseRequest">
	<tr id="earlyReleaseRequestInternalApprovalItemRow${earlyReleaseRequestInternalApprovalItemIndex}">
		<td>
			<a class="removeLink" id="removeEarlyReleaseRequestInternalApprovalLink${earlyReleaseRequestInternalApprovalItemIndex}" href="${pageContext.request.contextPath}/earlyReleaseTracking/removeEarlyReleaseRequestInternalApproval.html"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="earlyReleaseRequestInternalApprovalId${earlyReleaseRequestInternalApprovalItemIndex}" name="earlyReleaseRequestInternalApprovalItems[${earlyReleaseRequestInternalApprovalItemIndex}].earlyReleaseRequestInternalApproval" value="${earlyReleaseRequestInternalApprovalItem.earlyReleaseRequestInternalApproval.id}"/>
			<form:errors path="earlyReleaseRequestInternalApprovalItems[${earlyReleaseRequestInternalApprovalItemIndex}].earlyReleaseRequestInternalApproval" cssClass="error"/>
			<input type="hidden" id="earlyReleaseRequestInternalApprovalOperation${earlyReleaseRequestInternalApprovalItemIndex}" name="earlyReleaseRequestInternalApprovalItems[${earlyReleaseRequestInternalApprovalItemIndex}].itemOperation" value="${earlyReleaseRequestInternalApprovalItem.itemOperation}"/>
			<form:errors path="earlyReleaseRequestInternalApprovalItems[${earlyReleaseRequestInternalApprovalItemIndex}].itemOperation" cssClass="error"/>
		</td>
		<td>
			<fmt:formatDate var="earlyReleaseRequestInternalApprovalItemDate" value="${earlyReleaseRequestInternalApprovalItem.date}" pattern="MM/dd/yyyy"/>
			<input id="earlyReleaseRequestInternalApprovalItems[${earlyReleaseRequestInternalApprovalItemIndex}].date" name="earlyReleaseRequestInternalApprovalItems[${earlyReleaseRequestInternalApprovalItemIndex}].date" value="${earlyReleaseRequestInternalApprovalItemDate}" class="date"/>
			<form:errors path="earlyReleaseRequestInternalApprovalItems[${earlyReleaseRequestInternalApprovalItemIndex}].date" cssClass="error"/>
		</td>
		<td>
			<select id="earlyReleaseRequestInternalApprovalItems[${earlyReleaseRequestInternalApprovalItemIndex}].decision" name="earlyReleaseRequestInternalApprovalItems[${earlyReleaseRequestInternalApprovalItemIndex}].decision" >
				<jsp:include page="../../includes/nullOption.jsp"/>
				<c:forEach items="${internalApprovalDecisionCategories}" var="decision">
					<option value="${decision}" ${decision == earlyReleaseRequestInternalApprovalItem.decision ? 'selected="selected"' : ''}>
						<c:out value="${decision}"/>
					</option>
				</c:forEach>
			</select>
			<form:errors path="earlyReleaseRequestInternalApprovalItems[${earlyReleaseRequestInternalApprovalItemIndex}].decision" cssClass="error"/>
		</td>
		<td>
			<input id="earlyReleaseRequestInternalApprovalItems[${earlyReleaseRequestInternalApprovalItemIndex}].name" name="earlyReleaseRequestInternalApprovalItems[${earlyReleaseRequestInternalApprovalItemIndex}].name" value="${earlyReleaseRequestInternalApprovalItem.name}" type="text"/>
			<form:errors path="earlyReleaseRequestInternalApprovalItems[${earlyReleaseRequestInternalApprovalItemIndex}].name" cssClass="error"/>
		</td>
		<td>
			<textarea style="width: 95%" rows="4" id="earlyReleaseRequestInternalApprovalItems[${earlyReleaseRequestInternalApprovalItemIndex}].narrative" name="earlyReleaseRequestInternalApprovalItems[${earlyReleaseRequestInternalApprovalItemIndex}].narrative"><c:out value="${earlyReleaseRequestInternalApprovalItem.narrative}" /></textarea>
			<form:errors path="earlyReleaseRequestInternalApprovalItems[${earlyReleaseRequestInternalApprovalItemIndex}].narrative" cssClass="error"/>
		</td>
	</tr>
</fmt:bundle>