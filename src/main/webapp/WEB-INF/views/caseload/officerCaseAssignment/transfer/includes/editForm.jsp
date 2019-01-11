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
 - Version: 0.1.1 (Aug 13, 2018)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<sec:authorize var="editOfficerCaseAssignment" access="hasRole('OFFICER_CASE_ASSIGNMENT_EDIT') or hasRole('ADMIN')"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.caseload.msgs.officerCaseAssignment">
<form:form commandName="officerCaseAssignmentTransferForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="transferCaseloadDetailsTitle"/></legend>
		<span class ="fieldGroup">
			<form:label path="userAccountFrom" class="fieldLabel">
				<fmt:message key="fromLabel"/></form:label>
			<input id="userFrom" />
			<form:input path="userAccountFrom" type="hidden"/>			
			<a id="currentUserAccountFromLink" class="currentUserAccountLink"></a>
			<a id="clearUserFromLink" class="clearLink"></a>
			<span id="userAccountFromCurrentLabel">
				<c:if test="${not empty officerCaseAssignmentTransferForm.userAccountFrom}">
					<c:out value="${officerCaseAssignmentTransferForm.userAccountFrom.user.name.lastName}, ${officerCaseAssignmentTransferForm.userAccountFrom.user.name.firstName} (${officerCaseAssignmentTransferForm.userAccountFrom.username})"/>
				</c:if>
			</span>
			<form:errors path="userAccountFrom" cssClass="error"/>
		</span>
		<span class ="fieldGroup">
			<form:label path="userAccountTo" class="fieldLabel">
				<fmt:message key="toLabel"/></form:label>
			<input id="userTo" />
			<form:input path="userAccountTo" type="hidden"/>			
			<a id="currentUserAccountToLink" class="currentUserAccountLink"></a>
			<a id="clearUserToLink" class="clearLink"></a>
			<span id="userAccountToCurrentLabel">
				<c:if test="${not empty officerCaseAssignmentTransferForm.userAccountTo}">
					<c:out value="${officerCaseAssignmentTransferForm.userAccountTo.user.name.lastName}, ${officerCaseAssignmentTransferForm.userAccountTo.user.name.firstName} (${officerCaseAssignmentTransferForm.userAccountTo.username})"/>
				</c:if>
			</span>
			<form:errors path="userAccountTo" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="date" class="fieldLabel">
				<fmt:message key="transferDateLabel"/>
			</form:label>
			<form:input path="date" class="date"/>
			<form:errors path="date" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="swapCaseloads" class="fieldLabel">
				<fmt:message key="swapCaseloadsLabel"/>
			</form:label>
			<form:checkbox path="swapCaseloads"/>
			<form:errors path="swapCaseloads" cssClass="error"/>
		</span>
	</fieldset>
	<c:if test="${editOfficerCaseAssignment}">
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
		</p>
	</c:if>
</form:form>
</fmt:bundle>