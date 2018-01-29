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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<sec:authorize var="editHearingParticipant" access="hasRole('HEARING_PARTICIPANT_EDIT') or hasRole('ADMIN') or hasRole('HEARING_PARTICIPANT_CREATE')"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.hearingparticipant.msgs.hearingParticipant">
<form:form commandName="hearingParticipantForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="hearingParticipantTitle"/></legend>
		<span class="fieldGroup">
			<form:label path="category" class="fieldLabel">
				<fmt:message key="participantCategoryLabel"/>
			</form:label>
			<form:select path="category">
				<jsp:include page="../../includes/nullOption.jsp"/>
				<c:forEach items="${categories}" var="cat">
					<option value="${cat}" ${cat == hearingParticipantForm.category ? 'selected="selected"' : ''}>
						<fmt:message key="${cat}CategoryLabel"/>
					</option>
				</c:forEach>
			</form:select>
			<form:errors path="category" cssClass="error"/>
			<br/><form:errors path="person" cssClass="error"/>
		</span>
		<form:hidden path="person"/>
		<input type=hidden id="offender" value="${offender.id}"/>
		<c:set var="hidden" value="${hearingParticipantForm.category != 'STAFF' ? 'hidden' : '' }" />
		<span id="searchStaffFields" class="${hidden}">
			<span class="fieldGroup">
				<form:label path="person" class="fieldLabel">
					<fmt:message key="nameLabel"/>
				</form:label>
				<span id="staffFields">
					<input id="staffInput"/>
					<a id="currentStaff" class="currentUserAccountLink "></a>
					<a id="clearStaff" class="clearLink"></a>
					<span id="staffDisplay">
						<c:choose>
							<c:when test="${not empty hearingParticipantForm.person }">
								<c:set var="person" value="${hearingParticipantForm.person}" scope="request"/>
								<c:out value="${person.name.lastName}"/>, <c:out value="${person.name.firstName}"/>
							</c:when>
						</c:choose>
					</span>
				</span>
			</span>
		</span>
		<c:set var="hidden" value="${hearingParticipantForm.category != 'FAMILY' ? 'hidden' : '' }" />
		<span id="searchFamilyFields" class="${hidden}">
			<span class="fieldGroup">
				<form:label path="person" class="fieldLabel">
					<fmt:message key="nameLabel"/>
				</form:label>
				<span id="familyFields">
					<input id="familyInput"/>
					<a id="clearFamily" class="clearLink"></a>
					<span id="familyDisplay">
						<c:choose>
							<c:when test="${not empty hearingParticipantForm.person }">
								<c:set var="person" value="${hearingParticipantForm.person}" scope="request"/>
								<c:out value="${person.name.lastName}"/>, <c:out value="${person.name.firstName}"/>
							</c:when>
						</c:choose>
					</span>
				</span>
			</span>
		</span>
		<c:set var="hidden" value="${hearingParticipantForm.category != 'VICTIM' ? 'hidden' : '' }" />
		<span id="searchVictimFields" class="${hidden}">
			<span class="fieldGroup">
				<form:label path="person" class="fieldLabel">
					<fmt:message key="nameLabel"/>
				</form:label>
				<span id="victimFields">
					<input id="victimInput"/>
					<a id="clearVictim" class="clearLink"></a>
					<span id="victimDisplay">
						<c:choose>
							<c:when test="${not empty hearingParticipantForm.person }">
								<c:set var="person" value="${hearingParticipantForm.person}" scope="request"/>
								<c:out value="${person.name.lastName}"/>, <c:out value="${person.name.firstName}"/>
							</c:when>
						</c:choose>
					</span>
				</span>
			</span>
		</span>
		<span class="fieldGroup">
			<form:label path="boardApproved" class="fieldLabel">
				<fmt:message key="boardApprovedLabel"/>
			</form:label>
			<form:checkbox path="boardApproved" />
			<form:errors path="boardApproved" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="facilityApproved" class="fieldLabel">
				<fmt:message key="facilityApprovedLabel"/>
			</form:label>
			<form:checkbox path="facilityApproved" />
			<form:errors path="facilityApproved" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="witnessed" class="fieldLabel">
				<fmt:message key="witnessedLabel"/>
			</form:label>
			<form:checkbox path="witnessed" />
			<form:errors path="witnessed" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="intent" class="fieldLabel">
				<fmt:message key="intentLabel"/>
			</form:label>
			<form:select path="intent">
				<jsp:include page="../../includes/nullOption.jsp"/>
				<c:forEach items="${intentCategories}" var="cat">
					<option value="${cat.id}" ${cat == hearingParticipantForm.intent ? 'selected="selected"' : ''}>
						<c:out value="${cat.name}"/>
					</option>
				</c:forEach>
			</form:select>
			<form:errors path="intent" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="comments" class="fieldLabel">
				<fmt:message key="commentsLabel"/>
			</form:label>
			<form:textarea path="comments"/>
			<form:errors path="comments" cssClass="error"/>
		</span>
	</fieldset>
	<fieldset>
		<legend>
			<fmt:message key="notesLabel" />
		</legend>
		<span class="fieldGroup">
			<c:set var="hearingParticipantNoteItems" value="${hearingParticipantForm.hearingParticipantNoteItems}" scope="request"/>
			<jsp:include page="hearingParticipantNoteTable.jsp"/>
		</span>
	</fieldset>
	<c:if test="${not empty hearingParticipant}">
		<c:set var="updatable" value="${hearingParticipant}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<c:if test="${editHearingParticipant}">
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
		</p>
	</c:if>
</form:form>
</fmt:bundle>