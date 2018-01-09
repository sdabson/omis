<%--
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.health.msgs.health">
		<div class="requestInfo">
			<p>
				<span class="fieldLabel"><fmt:message key="requestDateLabel"/></span>
				<span class="fieldValue"><fmt:formatDate value="${healthRequest.date}" pattern="MM/dd/yyyy"/></span>
			</p>
			<p>
				<span class="fieldLabel"><fmt:message key="requestNotesLabel"/></span>
				<span class="fieldValue"><c:out value="${healthRequest.notes}"/></span>
			</p>
			<c:if test="${not empty originalReferralSummary}">
				<p>
					<span class="fieldLabel"><fmt:message key="originalReferralSchedulingNotesLabel"/></span>
					<span class="fieldValue"><c:out value="${originalReferralSummary.schedulingNotes}"/></span>
				</p>
				<p>
					<span class="fieldLabel"><fmt:message key="originalReferralAssessmentNotesLabel"/></span>
					<span class="fieldValue"><c:out value="${originalReferralSummary.assessmentNotes}"/></span>
				</p>
				<p>
					<span class="fieldLabel"><fmt:message key="originalAppointmentDateLabel"/></span>
					<span class="fieldValue"><fmt:formatDate value="${originalReferralSummary.appointmentDate}" pattern="MM/dd/yyyy"/></span>
					<span class="fieldLabel"><fmt:message key="originalReferralTypeLabel"/></span>
					<span class="fieldValue"><fmt:message key="referralTypeLabel.${originalReferralSummary.type.name}"/></span>
				</p>
				<p>
					<span class="fieldLabel"><fmt:message key="originalReferralLocationLabel"/></span>
					<span class="fieldValue"><c:out value="${originalReferralSummary.facilityName}"/></span>
					<c:if test="${originalReferralSummary.primaryProviderExists}">
						<span class="fieldLabel"><fmt:message key="originalAppointmentProviderLabel"/></span>
						<span class="fieldValue"><c:out value="${originalReferralSummary.primaryProviderLastName}"/>, <c:out value="${originalReferralSummary.primaryProviderFirstName}"/> <c:out value="${originalReferralSummary.primaryProviderTitleAbbreviation}"/></span>
					</c:if>
				</p>
				<p>
					<span class="fieldLabel"><fmt:message key="originalReferralReasonLabel"/></span>
					<span class="fieldValue"><c:out value="${originalReferralSummary.reasonName}"/></span>
					<c:if test="${not empty originalReferralSummary.providerLevelName}">
						<span class="fieldLabel"><fmt:message key="originalReferralProviderLevelLabel"/></span>
						<span class="fieldValue"><c:out value="${originalReferralSummary.providerLevelName}"/></span>
					</c:if>
				</p>
				<c:if test="${not empty originalReferralSummary.reasonNotes}">
					<p>
						<span class="fieldLabel"><fmt:message key="originalReferralReasonNotesLabel"/></span>
						<span class="fieldValue"><c:out value="${originalReferralSummary.reasonNotes}"/></span>
					</p>
				</c:if>
				<c:if test="${not empty originalReferralSummary.reportedDate}">
				<p>
					<span class="fieldLabel"><fmt:message key="originalReferralReportedDateLabel"/></span>
					<span class="fieldValue"><fmt:formatDate value="${originalReferralSummary.reportedDate}" pattern="MM/dd/yyyy"/></span>
				</p>
				</c:if>
			</c:if>
		</div>
</fmt:bundle>