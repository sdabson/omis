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
 - Author: Ryan Johns
 - Author: Trevor Isles
 - Version: 0.1.6 (Dec 6, 2018)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<sec:authorize var="editOfficerCaseAssignment" access="hasRole('OFFICER_CASE_ASSIGNMENT_EDIT') or hasRole('ADMIN') or hasRole('OFFICER_CASE_ASSIGNMENT_CREATE')"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.caseload.msgs.officerCaseAssignment">
<form:form commandName="officerCaseAssignmentForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="assignmentDetailsTitle"/></legend>
		<span class ="fieldGroup">
			<form:label path="officer" class="fieldLabel">
				<fmt:message key="assignedToLabel"/></form:label>
			<input id="assignedTo" ${not empty userAccount or not empty officerCaseAssignmentForm.officer ? 'type="hidden"' : ''}/>
			<form:input path="officer" type="hidden"/>			
			<a id="currentUserAccountLink" class="currentUserAccountLink" ${not empty userAccount or not empty officerCaseAssignmentForm.officer ? 'style="display: none"' : ''}></a>
			<a id="clearUserLink" class="clearLink" ${not empty userAccount or not empty officerCaseAssignmentForm.officer ? 'style="display: none"' : ''}></a>
			<span id="userAccountCurrentLabel">
			<c:if test="${not empty officerCaseAssignmentForm.officer}">
				<c:out value="${officerCaseAssignmentForm.officer.user.name.lastName}, ${officerCaseAssignmentForm.officer.user.name.firstName} (${officerCaseAssignmentForm.officer.username})"/>
			</c:if>
			</span>
		<form:errors path="officer" cssClass="error"/>
		</span>
		<span class ="fieldGroup">
			<form:label path="selectedOffender" class="fieldLabel">
				<fmt:message key="offenderLabel"/></form:label>
			<input id="offenderSearch" ${not empty offender or not empty officerCaseAssignmentForm.selectedOffender ? 'type="hidden"' : ''}/>
			<form:input path="selectedOffender" type="hidden"/>			
			<a id="clearOffenderLink" class="clearLink" ${not empty offender or not empty officerCaseAssignmentForm.selectedOffender ? 'style="display: none"' : ''}></a>
			<span id="offenderCurrentLabel">
			<c:if test="${not empty officerCaseAssignmentForm.selectedOffender}">
				<c:out value="${officerCaseAssignmentForm.selectedOffender.name.lastName}, ${officerCaseAssignmentForm.selectedOffender.name.firstName} #${officerCaseAssignmentForm.selectedOffender.offenderNumber}"/>
			</c:if>
			</span>
		<form:errors path="selectedOffender" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="supervisionLevelCategory" class="fieldLabel">
				<fmt:message key="supervisionTypeLabel"/>
			</form:label>
			<form:select path="supervisionLevelCategory">
				<jsp:include page="/WEB-INF/views/includes/nullOption.jsp"/>
				<c:forEach items="${supervisionLevels}" var="supervisionLevel">
					<option value="${supervisionLevel.id}" ${supervisionLevel == officerCaseAssignmentForm.supervisionLevelCategory ? 'selected="selected"' : ''}>
						<c:out value="${supervisionLevel.description}"/>
					</option>
				</c:forEach>
			</form:select>
			<form:errors path="supervisionLevelCategory" cssClass="error"/>
		</span>
		<form:hidden path="allowInterstateCompact"/>
		<c:if test="${officerCaseAssignmentForm.allowInterstateCompact}">
			<c:set var="displayClass" value="${officerCaseAssignmentForm.interstateCaseload == null || !officerCaseAssignmentForm.interstateCaseload ? 'hidden' : ''}" scope="request"/>
			<span class="fieldGroup">
				<form:label path="interstateCaseload" class="fieldLabel">
					<fmt:message key="interstateCaseloadLabel"/>
				</form:label>
				<input type="checkbox" name="interstateCaseload" id="interstateCaseload" ${officerCaseAssignmentForm.interstateCaseload ? 'checked="checked"' : ''} />
				<form:errors path="interstateCaseload" cssClass="error"/>
			</span>
		</c:if>
		<span class="fieldGroup">
			<label for="officeFilter" class="fieldLabel">
				<fmt:message key="officeFilterLabel"/>
			</label>
			<c:forEach items="${officeFilters}" var="officeFilter">
				<form:radiobutton path="officeFilter" id="${officeFilter}" value="${officeFilter}"/>
				<label for="${officeFilter}" class="checkboxLabel">
					<fmt:message key="officeFilterLabel.${officeFilter}"/>
				</label>
			</c:forEach>
		</span>
		<span class="fieldGroup">
			<form:label path="location" class="fieldLabel">
				<fmt:message key="regionOfficeLabel"/>
			</form:label>
			<c:set value="${officerCaseAssignmentForm.location}" var="officeLocation" scope="request"/>
			<form:select path="location">
				<jsp:include page="officeOptions.jsp"/>
			</form:select>
			<form:errors path="location" cssClass="error"/>
		</span>
		<c:if test="${officerCaseAssignmentForm.allowInterstateCompact}">
			<div id="interstateCompactFields" class="${displayClass}">
				<span class="fieldGroup">
					<form:label path="interstateCompactStatus" class="fieldLabel">
						<fmt:message key="interstateCompactStatusLabel"/>
					</form:label>
					<form:select path="interstateCompactStatus">
						<jsp:include page="/WEB-INF/views/includes/nullOption.jsp"/>
						<c:forEach items="${interstateCompactStatuses}" var="interstateCompactStatus">
							<option value="${interstateCompactStatus.id}" ${interstateCompactStatus == officerCaseAssignmentForm.interstateCompactStatus ? 'selected="selected"' : ''}>
								<c:out value="${interstateCompactStatus.correctionalStatus.name}"/>
							</option>
						</c:forEach>
					</form:select>
					<form:errors path="interstateCompactStatus" cssClass="error"/>
				</span>
				<span class="fieldGroup">
					<form:label path="interstateCompactType" class="fieldLabel">
						<fmt:message key="interstateCompactTypeLabel"/>
					</form:label>
					<form:select path="interstateCompactType">
						<jsp:include page="/WEB-INF/views/includes/nullOption.jsp"/>
						<c:forEach items="${interstateCompactTypes}" var="interstateCompactType">
							<option value="${interstateCompactType.id}" ${interstateCompactType == officerCaseAssignmentForm.interstateCompactType ? 'selected="selected"' : ''}>
								<c:out value="${interstateCompactType.name}"/>
							</option>
						</c:forEach>
					</form:select>
					<form:errors path="interstateCompactType" cssClass="error"/>
				</span>
				<span class="fieldGroup">
					<form:label path="jurisdiction" class="fieldLabel">
						<fmt:message key="jurisdictionLabel"/>
					</form:label>
					<form:select path="jurisdiction">
						<jsp:include page="/WEB-INF/views/includes/nullOption.jsp"/>
						<c:forEach items="${jurisdictions}" var="jurisdiction">
							<option value="${jurisdiction.id}" ${jurisdiction == officerCaseAssignmentForm.jurisdiction ? 'selected="selected"' : ''}>
								<c:out value="${jurisdiction.name}"/>
							</option>
						</c:forEach>
					</form:select>
					<form:errors path="jurisdiction" cssClass="error"/>
				</span>
				<span class="fieldGroup">
					<form:label path="jurisdictionStateId" class="fieldLabel">
						<fmt:message key="jurisdictionStateIdLabel"/>
					</form:label>
					<form:input path="jurisdictionStateId"/>
					<form:errors path="jurisdictionStateId" cssClass="error"/>
				</span>
			</div>
		</c:if>
		<span class="fieldGroup">
			<form:label path="startDate" class="fieldLabel">
				<fmt:message key="startDateLabel" bundle="${commonBundle}"/></form:label>
			<form:input path="startDate" class="date"/>
			<form:errors path="startDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="startTime" class="fieldLabel">
				<fmt:message key="startTimeLabel" bundle="${commonBundle}"/></form:label>
			<form:input path="startTime" class="time"/>
			<form:errors path="startTime" cssClass="error"/>
		</span>
		<c:if test="${officerCaseAssignmentForm.allowInterstateCompact}">
			<div id="projectedEndDateField" class="${displayClass}">
				<span class="fieldGroup">
					<form:label path="projectedEndDate" class="fieldLabel">
						<fmt:message key="projectedEndDateLabel"/>
					</form:label>
					<form:input path="projectedEndDate" class="date"/>
					<form:errors path="projectedEndDate" cssClass="error"/>
				</span>
			</div>
		</c:if>
		<span class="fieldGroup">
			<form:label path="endDate" class="fieldLabel">
				<fmt:message key="endDateLabel" bundle="${commonBundle}"/></form:label>
			<form:input path="endDate" class="date"/>
			<form:errors path="endDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="endTime" class="fieldLabel">
				<fmt:message key="endTimeLabel" bundle="${commonBundle}"/></form:label>
			<form:input path="endTime" class="time"/>
			<form:errors path="endTime" cssClass="error"/>
		</span>
		<c:if test="${officerCaseAssignmentForm.allowInterstateCompact}">
			<div id="endReasonField" class="${displayClass}">
				<span class="fieldGroup">
					<form:label path="endReason" class="fieldLabel">
						<fmt:message key="endReasonLabel"/>
					</form:label>
					<form:select path="endReason">
						<jsp:include page="/WEB-INF/views/includes/nullOption.jsp"/>
						<c:forEach items="${endReasons}" var="endReason">
							<option value="${endReason.id}" ${endReason == officerCaseAssignmentForm.endReason ? 'selected="selected"' : ''}>
								<c:out value="${endReason.name}"/>
							</option>
						</c:forEach>
					</form:select>
					<form:errors path="endReason" cssClass="error"/>
				</span>
			</div>
		</c:if>
	</fieldset>
	<fieldset>
		<legend>
			<fmt:message key="notesTitle" />
		</legend>
		<span class="fieldGroup">
			<c:set var="officerCaseAssignmentNoteItems" value="${officerCaseAssignmentForm.officerCaseAssignmentNoteItems}" scope="request"/>
			<jsp:include page="officerCaseAssignmentNoteTable.jsp"/>
		</span>
	</fieldset>
	<c:if test="${not empty officerCaseAssignment}">
		<c:set var="updatable" value="${officerCaseAssignment}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<c:if test="${editOfficerCaseAssignment}">
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
		</p>
	</c:if>
</form:form>
</fmt:bundle>