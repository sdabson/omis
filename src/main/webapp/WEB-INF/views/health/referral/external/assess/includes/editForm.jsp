<%--
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.health.msgs.health" var="healthBundle"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<form:form commandName="assessExternalReferralForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="assessExternalReferralLabel" bundle="${healthBundle}"/></legend>
		<span class="fieldGroup">
			<form:label path="time" class="fieldLabel">
				<fmt:message key="externalReferralTimeLabel" bundle="${healthBundle}"/></form:label>
			<form:input path="time" class="time"/>
			<form:errors path="time" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="notes" class="fieldLabel">
				<fmt:message key="externalReferralAssessmentNotesLabel" bundle="${healthBundle}"/></form:label>
			<form:textarea path="notes" class="time"/>
			<form:errors path="notes" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="reportedDate" class="fieldLabel">
				<fmt:message key="externalReferralReportedDateLabel" bundle="${healthBundle}"/></form:label>
			<form:input path="reportedDate" class="date"/>
			<form:errors path="reportedDate" cssClass="error"/>
		</span>
	</fieldset>
	<sec:authorize access="hasRole('APP_DEV')">
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