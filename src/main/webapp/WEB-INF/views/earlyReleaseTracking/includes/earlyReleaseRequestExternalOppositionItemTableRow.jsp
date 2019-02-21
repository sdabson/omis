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
	<tr  id="earlyReleaseRequestExternalOppositionItemRow${earlyReleaseRequestExternalOppositionItemIndex}">
		<td>
			<a class="removeLink" id="removeEarlyReleaseRequestExternalOppositionLink${earlyReleaseRequestExternalOppositionItemIndex}" href="${pageContext.request.contextPath}/earlyReleaseTracking/removeEarlyReleaseRequestExternalOpposition.html"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="earlyReleaseRequestExternalOppositionId${earlyReleaseRequestExternalOppositionItemIndex}" name="earlyReleaseRequestExternalOppositionItems[${earlyReleaseRequestExternalOppositionItemIndex}].earlyReleaseRequestExternalOpposition" value="${earlyReleaseRequestExternalOppositionItem.earlyReleaseRequestExternalOpposition.id}"/>
			<form:errors path="earlyReleaseRequestExternalOppositionItems[${earlyReleaseRequestExternalOppositionItemIndex}].earlyReleaseRequestExternalOpposition" cssClass="error"/>
			<input type="hidden" id="earlyReleaseRequestExternalOppositionOperation${earlyReleaseRequestExternalOppositionItemIndex}" name="earlyReleaseRequestExternalOppositionItems[${earlyReleaseRequestExternalOppositionItemIndex}].itemOperation" value="${earlyReleaseRequestExternalOppositionItem.itemOperation}"/>
			<form:errors path="earlyReleaseRequestExternalOppositionItems[${earlyReleaseRequestExternalOppositionItemIndex}].itemOperation" cssClass="error"/>
		</td>
		<td>
			<fmt:formatDate var="earlyReleaseRequestExternalOppositionItemDate" value="${earlyReleaseRequestExternalOppositionItem.date}" pattern="MM/dd/yyyy"/>
			<input id="earlyReleaseRequestExternalOppositionItems[${earlyReleaseRequestExternalOppositionItemIndex}].date" name="earlyReleaseRequestExternalOppositionItems[${earlyReleaseRequestExternalOppositionItemIndex}].date" value="${earlyReleaseRequestExternalOppositionItemDate}" class="date"/>
			<form:errors path="earlyReleaseRequestExternalOppositionItems[${earlyReleaseRequestExternalOppositionItemIndex}].date" cssClass="error"/>
		</td>
		<td>
			<select id="earlyReleaseRequestExternalOppositionItems[${earlyReleaseRequestExternalOppositionItemIndex}].party" name="earlyReleaseRequestExternalOppositionItems[${earlyReleaseRequestExternalOppositionItemIndex}].party" >
				<jsp:include page="../../includes/nullOption.jsp"/>
				<c:forEach items="${externalOppositionPartyCategories}" var="party">
					<option value="${party.id}" ${party == earlyReleaseRequestExternalOppositionItem.party ? 'selected="selected"' : ''}>
						<c:out value="${party.name}"/>
					</option>
				</c:forEach>
			</select>
			<form:errors path="earlyReleaseRequestExternalOppositionItems[${earlyReleaseRequestExternalOppositionItemIndex}].party" cssClass="error"/>
		</td>
		<td>
			<textarea style="width: 95%" rows="4" id="earlyReleaseRequestExternalOppositionItems[${earlyReleaseRequestExternalOppositionItemIndex}].narrative" name="earlyReleaseRequestExternalOppositionItems[${earlyReleaseRequestExternalOppositionItemIndex}].narrative"><c:out value="${earlyReleaseRequestExternalOppositionItem.narrative}" /></textarea>
			<form:errors path="earlyReleaseRequestExternalOppositionItems[${earlyReleaseRequestExternalOppositionItemIndex}].narrative" cssClass="error"/>
		</td>
	</tr>
</fmt:bundle>