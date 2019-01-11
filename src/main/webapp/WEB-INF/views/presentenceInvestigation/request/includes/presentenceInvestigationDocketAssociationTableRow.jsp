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
  - Table row to edit presentence investigation delays.
  -
  - Author: Josh Divine
  - Version: 0.1.0 (Oct 31, 2018)
  - Since: OMIS 3.0
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.presentenceInvestigationRequest">
	<tr id="presentenceInvestigationDocketAssociationItemRow${presentenceInvestigationDocketAssociationItemIndex}" class="presentenceInvestigationDocketAssociationItemRow">
		<td>
			<a class="removeLink" id="removePresentenceInvestigationDocketAssociationLink${presentenceInvestigationDocketAssociationItemIndex}" href="${pageContext.request.contextPath}/presentenceInvestigationRequest/removePresentenceInvestigationDocketAssociation.html?presentenceInvestigationRequest=${presentenceInvestigationRequest.id}"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="presentenceInvestigationDocketAssociationId${presentenceInvestigationDocketAssociationItemIndex}" name="presentenceInvestigationDocketAssociationItems[${presentenceInvestigationDocketAssociationItemIndex}].presentenceInvestigationDocketAssociation" value="${presentenceInvestigationDocketAssociationItem.presentenceInvestigationDocketAssociation.id}"/>
			<form:errors path="presentenceInvestigationDocketAssociationItems[${presentenceInvestigationDocketAssociationItemIndex}].presentenceInvestigationDocketAssociation" cssClass="error"/>
			<input type="hidden" id="presentenceInvestigationDocketAssociationOperation${presentenceInvestigationDocketAssociationItemIndex}" name="presentenceInvestigationDocketAssociationItems[${presentenceInvestigationDocketAssociationItemIndex}].itemOperation" value="${presentenceInvestigationDocketAssociationItem.itemOperation}"/>
			<form:errors path="presentenceInvestigationDocketAssociationItems[${presentenceInvestigationDocketAssociationItemIndex}].itemOperation" cssClass="error"/>
		</td>
		<td>
			<input type="hidden" name="presentenceInvestigationDocketAssociationItems[${presentenceInvestigationDocketAssociationItemIndex}].useExisting" value="${presentenceInvestigationDocketAssociationItem.useExisting}"/>
			<c:choose>
				<c:when test="${presentenceInvestigationDocketAssociationItem.useExisting}">
					<select name="presentenceInvestigationDocketAssociationItems[${presentenceInvestigationDocketAssociationItemIndex}].existingDocket" id="presentenceInvestigationDocketAssociationItemsExistingDocket${presentenceInvestigationDocketAssociationItemIndex}" ${presentenceInvestigationDocketAssociationItem.existingDocket != null && presentenceInvestigationDocketAssociationItem.presentenceInvestigationDocketAssociation != null ? 'disabled="disabled"' : ''}>
						<jsp:include page="/WEB-INF/views/includes/nullOption.jsp"/>
						<c:forEach items="${dockets}" var="docket">
							<option value="${docket.id}" ${docket == presentenceInvestigationDocketAssociationItems[presentenceInvestigationDocketAssociationItemIndex].existingDocket ? 'selected="selected"' : ''}>
								<c:out value="${docket.value}"/>
							</option>
						</c:forEach>
					</select>
					<c:if test="${presentenceInvestigationDocketAssociationItem.existingDocket != null && presentenceInvestigationDocketAssociationItem.presentenceInvestigationDocketAssociation != null}">
						<input type="hidden" name="presentenceInvestigationDocketAssociationItems[${presentenceInvestigationDocketAssociationItemIndex}].existingDocket" value="${presentenceInvestigationDocketAssociationItem.existingDocket.id}"/>
					</c:if>
					<form:errors path="presentenceInvestigationDocketAssociationItems[${presentenceInvestigationDocketAssociationItemIndex}].existingDocket" cssClass="error"/>
				</c:when>
				<c:otherwise>
					<input type="text" name="presentenceInvestigationDocketAssociationItems[${presentenceInvestigationDocketAssociationItemIndex}].docketValue" id="presentenceInvestigationDocketAssociationItemDocketValue${presentenceInvestigationDocketAssociationItemIndex}" value="${presentenceInvestigationDocketAssociationItem.docketValue}"/>
					<form:errors path="presentenceInvestigationDocketAssociationItems[${presentenceInvestigationDocketAssociationItemIndex}].docketValue" cssClass="error"/>
				</c:otherwise>
			</c:choose>
		</td>
		<td>
			<select name="presentenceInvestigationDocketAssociationItems[${presentenceInvestigationDocketAssociationItemIndex}].court" id="presentenceInvestigationDocketAssociationItemsCourt${presentenceInvestigationDocketAssociationItemIndex}" ${presentenceInvestigationDocketAssociationItem.useExisting ? 'disabled="disabled"' : ''}>
				<c:set var="courts" value="${courts}" scope="request"/>
				<c:set var="court" value="${presentenceInvestigationDocketAssociationItems[presentenceInvestigationDocketAssociationItemIndex].court}" scope="request"/>
				<jsp:include page="courtOptions.jsp"/>
			</select>
			<c:if test="${presentenceInvestigationDocketAssociationItem.useExisting}">
				<input type="hidden" name="presentenceInvestigationDocketAssociationItems[${presentenceInvestigationDocketAssociationItemIndex}].court" id="presentenceInvestigationDocketAssociationItems[${presentenceInvestigationDocketAssociationItemIndex}].court" value="${presentenceInvestigationDocketAssociationItem.court.id}"/>
			</c:if>
			<form:errors path="presentenceInvestigationDocketAssociationItems[${presentenceInvestigationDocketAssociationItemIndex}].court" cssClass="error"/>
		</td>
	</tr>
</fmt:bundle> 