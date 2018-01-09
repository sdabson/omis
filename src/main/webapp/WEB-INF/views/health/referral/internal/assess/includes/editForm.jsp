<%--
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.health.msgs.health" var="healthBundle"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<form:form commandName="assessInternalReferralForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="assessInternalReferralLabel" bundle="${healthBundle}"/></legend>
		<span class="fieldGroup">
			<label for="providerLevel" class="fieldLabel">
		  		<fmt:message key="providerLevelLabel" bundle="${healthBundle}"/></label>
			<input name="providerLevel" type="text" readonly="readonly" value="<c:out value='${internalReferral.providerLevel.name}'/>" class="small"/>
		</span>
		<span class="fieldGroup">
			<label for="providerAssignment" class="fieldLabel">
		  		<fmt:message key="providerAssignmentLabel" bundle="${healthBundle}"/></label>
			<input name="providerAssignment" type="text" readonly="readonly" value="<c:if test='${not empty providerAssignment}'><c:out value='${providerAssignment.provider.name.lastName}'/>, <c:out value='${providerAssignment.provider.name.firstName}'/> <c:out value='${providerAssignment.title.abbreviation}'/></c:if>" class="small"/>
		</span>
		<span class="fieldGroup">
			<label for="reason" class="fieldLabel">
		  		<fmt:message key="internalReferralReasonLabel" bundle="${healthBundle}"/></label>
			<input name="reason" type="text" readonly="readonly" value="<c:out value='${internalReferral.reason.name}'/>" class="small"/>
		</span>
		<span class="fieldGroup">
			<label for="locationDesignator" class="fieldLabel">
		  		<fmt:message key="locationDesignatorLabel" bundle="${healthBundle}"/></label>
			<input name="locationDesignator" type="text" readonly="readonly" value="<c:if test='${not empty internalReferral.locationDesignator}'><fmt:message key='referralLocationDesignatorLabel.${internalReferral.locationDesignator.name}' bundle='${healthBundle}'/></c:if>" class="small"/>
		</span>
		<span class="fieldGroup">
			<label for="appointmentDate" class="fieldLabel">
		  		<fmt:message key="appointmentDateLabel" bundle="${healthBundle}"/></label>
			<input name="appointmentDate" type="text" class="date" readonly="readonly" value="<fmt:formatDate value='${internalReferral.offenderAppointmentAssociation.appointment.date}' pattern='MM/dd/yyyy'/>"/>
		</span>
		<span class="fieldGroup">
			<label for="internalReferralSchedulingNotes" class="fieldLabel">
		  		<fmt:message key="internalReferralSchedulingNotesLabel" bundle="${healthBundle}"/></label>
			<textarea name="internalReferralSchedulingNotes" readonly="readonly"><c:out value="${internalReferral.schedulingNotes}"/></textarea>
		</span>
		<c:if test="${not empty internalReferral.statusReason}">
			<span class="fieldGroup">
				<label for="internalReferralStatusReason" class="fieldLabel">
		  			<fmt:message key="internalReferralStatusReasonLabel" bundle="${healthBundle}"/></label>
				<input name="internalReferralStatusReason" type="text" readonly="readonly" value="<c:out value='${internalReferral.statusReason.name}'/>" class="medium"/>
			</span>
		</c:if>
		<span class="fieldGroup">
			<form:label path="time" class="fieldLabel">
				<fmt:message key="internalReferralTimeLabel" bundle="${healthBundle}"/></form:label>
			<form:input path="time" class="time"/>
			<form:errors path="time" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="notes" class="fieldLabel">
				<fmt:message key="internalReferralAssessmentNotesLabel" bundle="${healthBundle}"/></form:label>
			<form:textarea path="notes" class="time"/>
			<form:errors path="notes" cssClass="error"/>
		</span>
	</fieldset>
	<sec:authorize access="hasRole('DISABLED')">
	<c:if test="${not empty assessInternalReferralForm.labWork}">
	<fieldset>
		<legend><fmt:message key="requiredLabsLegendLabel" bundle="${healthBundle}"/></legend>
		<c:set var="labWorkItems" value="${assessInternalReferralForm.labWork}" scope="request"/>	
		<jsp:include page="/WEB-INF/views/health/labWork/includes/labWorkContent.jsp"/>
	</fieldset>
	</c:if>
	</sec:authorize>
	<fieldset>
		<legend><fmt:message key="followupLabel" bundle="${healthBundle}"/></legend>
		<c:if test="${not empty internalReferral.actionRequest}">
			<input type="hidden" id="followUpAlreadyScheduled" name="followUpAlreadyScheduled" value="true"/>
			<p class="warningMessage"><fmt:message key="followUpAlreadyScheduledLabel" bundle="${healthBundle}"/></p>
		</c:if>
		<jsp:include page="/WEB-INF/views/health/referral/includes/followUpReferralFieldsetContent.jsp"/>
	</fieldset>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
	</p>
</form:form>