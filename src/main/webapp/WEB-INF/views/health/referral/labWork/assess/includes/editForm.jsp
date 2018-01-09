<%--
 - Author: Joel Norris
 - Version: 0.1.0 (July 16, 2014)
 - Since: OMIS 3.0
 --%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.health.msgs.health">
	<form:form commandName="assessLabWorkReferralForm" class="editForm">
		<fieldset>
			<legend><fmt:message key="assessLabWorkReferralLegendLabel"/></legend>
			<span class="fieldGroup">
				<form:label path="notes" class="fieldLabel">
					<fmt:message key="labWorkReferralAssessmentNotesLabel"/></form:label>
				<form:textarea path="notes"/>
				<form:errors path="notes" cssClass="error"/>
			</span>
		</fieldset>
		<fieldset>
			<legend><fmt:message key="followupLabel"/></legend>
			<c:if test="${not empty labWorkReferral.actionRequest}">
				<input type="hidden" id="followUpAlreadyScheduled" name="followUpAlreadyScheduled" value="true"/>
				<p class="warningMessage"><fmt:message key="followUpAlreadyScheduledLabel"/></p>
			</c:if>
			<jsp:include page="/WEB-INF/views/health/referral/includes/followUpReferralFieldsetContent.jsp"/>
		</fieldset>
		<fieldset>
			<legend><fmt:message key="labWorkAssessmentItemsLegendLabel"/></legend>
			<c:set var="labWorkAssessmentItems" value="${assessLabWorkReferralForm.labWorkAssessmentItems}" scope="request"/>
			<jsp:include page="/WEB-INF/views/health/referral/labWork/assess/includes/labWorkAssessmentItemContent.jsp"/>
		</fieldset>
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
		</p>
	</form:form>
</fmt:bundle>