<%--
 - Author: Stephen Abson
 -
 - Summary of external referral authorization.
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.health.msgs.health">
	<div class="authorizationInfo">
		<p>
			<span class="fieldLabel"><fmt:message key="medicalFacilityLabel"/></span>
			<span class="fieldValue"><c:out value="${authorization.request.medicalFacility.name}"/></span>
			<c:if test="${not empty authorization.request.providerAssignment}">
				<span class="fieldLabel"><fmt:message key="providerLabel"/></span>
				<span class="fieldValue"><c:out value="${authorization.request.providerAssignment.provider.name.lastName}, ${authorization.request.providerAssignment.provider.name.firstName} ${authorization.request.providerAssignment.title.name}"/></span>
			</c:if>
			<span class="fieldLabel"><fmt:message key="externalReferralReasonLabel"/></span>
			<span class="fieldValue"><c:out value="${authorization.request.reason.name}"/></span>
		</p>
		<p>
			<c:if test="${not empty authorization.request.reasonNotes}">
				<span class="fieldLabel"><fmt:message key="externalReferralReasonNotesLabel"/></span>
				<span class="fieldValue"><c:out value="${authorization.request.reasonNotes}"/></span>
			</c:if>
		</p>
		<p>
			<span class="fieldLabel"><fmt:message key="externalReferralDecisionDateLabel"/></span>
			<span class="fieldValue"><fmt:formatDate pattern="MM/dd/yyyy" value="${authorization.decisionDate}"/></span>
			<span class="fieldLabel"><fmt:message key="externalReferralAuthorizedByLabel"/></span>
			<span class="fieldValue"><c:out value="${authorization.authorizedBy.name.lastName}, ${authorization.authorizedBy.name.firstName}"/></span>
			<c:if test="${'PANEL_REVIEW_REQUIRED' eq authorization.status.name}">
				<span class="fieldLabel"><fmt:message key="externalReferralMedicalPanelReviewDateLabel"/></span>
				<span class="fieldValue"><fmt:formatDate pattern="MM/dd/yyyy" value="${authorization.panelReviewDecision.reviewDate}"/></span>
			</c:if>
		</p>
		<c:if test="${not empty authorization.request.referredByProviderAssignment or not empty authorization.request.referredDate}">
		<p>
			<c:if test="${not empty authorization.request.referredDate}">
			<span class="fieldLabel"><fmt:message key="externalReferralReferredDateLabel"/></span>
			<span class="fieldValue"><fmt:formatDate pattern="MM/dd/yyyy" value="${authorization.request.referredDate}"/></span>
			</c:if>
			<c:if test="${not empty authorization.request.referredByProviderAssignment}">
			<span class="fieldLabel"><fmt:message key="referredByProviderAssignmentLabel"/></span>
			<span class="fieldValue"><c:out value="${authorization.request.referredByProviderAssignment.provider.name.lastName}, ${authorization.request.referredByProviderAssignment.provider.name.firstName}"/></span>
			</c:if>
		</p>
		</c:if>
	</div>
</fmt:bundle>