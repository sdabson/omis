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
	<tr id="earlyReleaseRequestNoteAssociationItemRow${earlyReleaseRequestNoteAssociationItemIndex}" class="earlyReleaseRequestNoteAssociationItemRow">
		<td>
			<a class="removeLink" id="removeEarlyReleaseRequestNoteAssociationLink${earlyReleaseRequestNoteAssociationItemIndex}" href="${pageContext.request.contextPath}/assessment/rating/removeEarlyReleaseRequestNoteAssociation.html?assessment=${assessment.id}"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="earlyReleaseRequestNoteAssociationId${earlyReleaseRequestNoteAssociationItemIndex}" name="earlyReleaseRequestNoteAssociationItems[${earlyReleaseRequestNoteAssociationItemIndex}].earlyReleaseRequestNoteAssociation" value="${earlyReleaseRequestNoteAssociationItem.earlyReleaseRequestNoteAssociation.id}"/>
			<form:errors path="earlyReleaseRequestNoteAssociationItems[${earlyReleaseRequestNoteAssociationItemIndex}].earlyReleaseRequestNoteAssociation" cssClass="error"/>
			<input type="hidden" id="earlyReleaseRequestNoteAssociationOperation${earlyReleaseRequestNoteAssociationItemIndex}" name="earlyReleaseRequestNoteAssociationItems[${earlyReleaseRequestNoteAssociationItemIndex}].itemOperation" value="${earlyReleaseRequestNoteAssociationItem.itemOperation}"/>
			<form:errors path="earlyReleaseRequestNoteAssociationItems[${earlyReleaseRequestNoteAssociationItemIndex}].itemOperation" cssClass="error"/>
		</td>
		<td>
			<fmt:formatDate var="earlyReleaseRequestNoteAssociationDate" value="${earlyReleaseRequestNoteAssociationItem.date}" pattern="MM/dd/yyyy"/>
			<input type="text" class="date" name="earlyReleaseRequestNoteAssociationItems[${earlyReleaseRequestNoteAssociationItemIndex}].date" id="earlyReleaseRequestNoteAssociationItemDate${earlyReleaseRequestNoteAssociationItemIndex}" value="${earlyReleaseRequestNoteAssociationDate}"/>
			<form:errors path="earlyReleaseRequestNoteAssociationItems[${earlyReleaseRequestNoteAssociationItemIndex}].date" cssClass="error"/>
		</td>
		<td>
			<textarea rows="4" name="earlyReleaseRequestNoteAssociationItems[${earlyReleaseRequestNoteAssociationItemIndex}].description" id="earlyReleaseRequestNoteAssociationItems[${earlyReleaseRequestNoteAssociationItemIndex}].description" style="width: 95%"><c:out value="${earlyReleaseRequestNoteAssociationItem.description}"/></textarea>
			<form:errors path="earlyReleaseRequestNoteAssociationItems[${earlyReleaseRequestNoteAssociationItemIndex}].description" cssClass="error"/>
		</td> 
		<td>
			<c:if test="${not empty earlyReleaseRequestNoteAssociationItem.earlyReleaseRequestNoteAssociation.updateSignature}">
				<label>
					<fmt:message key="lastUpdatedUserName">
						<fmt:param value="${earlyReleaseRequestNoteAssociationItem.earlyReleaseRequestNoteAssociation.updateSignature.userAccount.user.name.lastName}"/>
						<fmt:param value="${earlyReleaseRequestNoteAssociationItem.earlyReleaseRequestNoteAssociation.updateSignature.userAccount.user.name.firstName}"/>
					</fmt:message>
				</label>
			</c:if>
		</td>
	</tr>
</fmt:bundle> 