<?xml version="1.0" encoding="UTF-8"?>
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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<sec:authorize var="editBoardHearing" access="hasRole('BOARD_HEARING_DECISION_EDIT') or hasRole('ADMIN') or hasRole('BOARD_HEARING_DECISION_CREATE')"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.boardhearingdecision.msgs.boardHearingDecision">
<form:form commandName="boardHearingDecisionForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="boardMemberDecisionsTitle"/></legend>
		<span class="fieldGroup">
			<c:set var="boardMemberDecisionItems" value="${boardHearingDecisionForm.boardMemberDecisionItems}" scope="request"/>
			<jsp:include page="boardMemberDecisionTable.jsp"/>
		</span>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="boardHearingDecisionTitle"/></legend>
		<span class="fieldGroup">
			<label class="fieldLabel">
				<fmt:message key="decisionLabel"/>
			</label>
			<span id="decsionCategory">
				<c:if test="${not empty boardHearingDecisionForm.category}">
					<fmt:message key="decisonCategoryLabel.${boardHearingDecisionForm.category.decision}"/>
				</c:if>
			</span>
		</span>
		<span class="fieldGroup">
			<form:label path="category" class="fieldLabel">
				<fmt:message key="boardHearingDecisionCategoryLabel"/>
			</form:label>
			<form:select path="category">
				<c:set var="hearingDecisionCategory" value="${boardHearingDecisionForm.category}" scope="request"/>
				<jsp:include page="/WEB-INF/views/boardHearingDecision/includes/boardHearingDecisionCategoryOptions.jsp"/>
			</form:select>
			<form:errors path="category" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="rulingDetails" class="fieldLabel">
				<fmt:message key="rulingDetailsLabel"/>
			</form:label>
			<form:textarea path="rulingDetails"/>
			<form:errors path="rulingDetails" cssClass="error"/>
		</span>
	</fieldset>
	<fieldset>
		<legend>
			<fmt:message key="notesTitle" />
		</legend>
		<span class="fieldGroup">
			<c:set var="hearingDecisionNoteItems" value="${boardHearingDecisionForm.hearingDecisionNoteItems}" scope="request"/>
			<jsp:include page="hearingDecisionNoteTable.jsp"/>
		</span>
	</fieldset>
	<c:if test="${not empty boardHearingDecision}">
		<c:set var="updatable" value="${boardHearingDecision}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<c:if test="${editBoardHearing}">
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
		</p>
	</c:if>
</form:form>
</fmt:bundle>