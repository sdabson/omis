<%-- Author: Stephen Abson --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.health.msgs.health">
	<table class="listTable">
		<thead>
			<tr>
				<th><fmt:message key="offenderLabel"/></th>
				<th><fmt:message key="medicalFacilityLabel"/></th>
				<th><fmt:message key="externalProviderLabel"/></th>
				<th><fmt:message key="externalReferralReasonLabel"/></th>
				<th><fmt:message key="externalReferralReferralByProviderLabel"/></th>
				<th><fmt:message key="externalReferralReferredDateLabel"/></th>
				<th><fmt:message key="externalReferralAuthorizationDecisionDateLabel"/></th>
				<th><fmt:message key="externalReferralAuthorizedByProviderLabel"/></th>
				<th><fmt:message key="externalReferralAuthorizationStatusLabel"/></th>
				<th><fmt:message key="externalReferralMedicalPanelReviewDecisionDateLabel"/></th>
				<th><fmt:message key="externalReferralMedicalPanelReviewDecisionStatusLabel"/></th>
			</tr>
		</thead>
		<tbody>
			<jsp:include page="listTableBodyContent.jsp"/>
		</tbody>
	</table>
</fmt:bundle>