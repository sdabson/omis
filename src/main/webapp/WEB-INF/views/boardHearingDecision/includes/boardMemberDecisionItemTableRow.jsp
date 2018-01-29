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
 - Author: Josh Divine
 - Version: 0.1.0 (Jan 23, 2018)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.boardhearingdecision.msgs.boardHearingDecision">
	<tr id="boardMemberDecisionItemRow${boardMemberDecisionItemIndex}" class="boardMemberDecisionItemRow">
		<td>
			<input type="hidden" id="boardMemberDecisionId${boardMemberDecisionItemIndex}" name="boardMemberDecisionItems[${boardMemberDecisionItemIndex}].boardMemberDecision" value="${boardMemberDecisionItem.boardMemberDecision.id}"/>
			<input type="hidden" id="boardHearingParticipantId${boardMemberDecisionItemIndex}" name="boardMemberDecisionItems[${boardMemberDecisionItemIndex}].boardHearingParticipant" value="${boardMemberDecisionItem.boardHearingParticipant.id}"/>
			<c:set var="memberName" value="${boardMemberDecisionItem.boardHearingParticipant.boardMember.staffAssignment.staffMember.name}"/>
			<span><c:out value="${memberName.lastName}, ${memberName.firstName} ${memberName.middleName}"/></span>
		</td>
		<td>
			<select id="boardMemberDecisionItems${boardMemberDecisionItemIndex}Category" name="boardMemberDecisionItems[${boardMemberDecisionItemIndex}].category">
				<jsp:include page="/WEB-INF/views/includes/nullOption.jsp"/>
				<c:forEach items="${decisionCategories}" var="desicionCategory">
					<option value="${desicionCategory}" ${desicionCategory == boardMemberDecisionItem.category ? 'selected="selected"' : ''}>
						<fmt:message key="decisonCategoryLabel.${desicionCategory}"/>
					</option>
				</c:forEach>
			</select>
			<form:errors path="boardMemberDecisionItems[${boardMemberDecisionItemIndex}].category" cssClass="error"/>
		</td>
		<td>
			<c:set var="decisionReason" value="${boardMemberDecisionItem.decisionReason}" scope="request"/>
			<c:choose>
				<c:when test="${not empty boardMemberDecisionItem.category && boardMemberDecisionItem.category eq 'DENY'}">
					<c:set var="hearingDecisionReasons" value="${denyHearingDecisionReasons}" scope="request"/>
				</c:when>
				<c:when test="${not empty boardMemberDecisionItem.category && boardMemberDecisionItem.category eq 'GRANT'}">
					<c:set var="hearingDecisionReasons" value="${grantHearingDecisionReasons}" scope="request"/>
				</c:when>
				<c:otherwise>
					<c:remove var="hearingDecisionReasons"/>
				</c:otherwise>
			</c:choose>
			<select id="boardMemberDecisionItems${boardMemberDecisionItemIndex}DecisionReason" name="boardMemberDecisionItems[${boardMemberDecisionItemIndex}].decisionReason">
				<jsp:include page="/WEB-INF/views/boardHearingDecision/includes/hearingDecisionReasonOptions.jsp"/>
			</select>
			<form:errors path="boardMemberDecisionItems[${boardMemberDecisionItemIndex}].decisionReason" cssClass="error"/>
		</td> 
		<td>
			<textarea rows="4" name="boardMemberDecisionItems[${boardMemberDecisionItemIndex}].comments" id="boardMemberDecisionItems[${boardMemberDecisionItemIndex}].comments" style="width: 95%"><c:out value="${boardMemberDecisionItem.comments}"/></textarea>
			<form:errors path="boardMemberDecisionItems[${boardMemberDecisionItemIndex}].comments" cssClass="error"/>
		</td>
	</tr>
</fmt:bundle> 