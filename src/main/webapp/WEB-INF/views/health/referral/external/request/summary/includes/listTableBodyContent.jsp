<%-- Author: Stephen Abson --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.health.msgs.health">
	<c:forEach var="summary" items="${summaries}">
		<tr>
			<td><c:out value="${summary.offenderLastName}, ${summary.offenderFirstName} ${summary.offenderMiddleName} #${summary.offenderNumber}"/></td>
			<td><c:out value="${summary.medicalFacilityName}"/></td>
			<td><c:if test="${summary.primaryProviderExists}"><c:out value="${summary.primaryProviderLastName}, ${summary.primaryProviderFirstName} ${summary.primaryProviderTitleAbbreviation}"/></c:if></td>
			<td><c:out value="${summary.reasonName}"/></td>
			<td><c:if test="${summary.referredByProviderExists}"><c:out value="${summary.referredByProviderLastName}, ${summary.referredByProviderFirstName} ${summary.referredByProviderTitleAbbreviation}"/></c:if></td>
			<td><fmt:formatDate value="${summary.referredDate}" pattern="MM/dd/yyyy"/></td>
			<td><c:if test="${summary.authorized}"><fmt:formatDate value="${summary.decisionDate}" pattern="MM/dd/yyyy"/></c:if></td>
			<td><c:if test="${summary.authorized}"><c:out value="${summary.authorizedByLastName}, ${summary.authorizedByFirstName} ${summary.authorizedByStaffTitleName}"/></c:if></td>
			<td><c:if test="${summary.authorized}"><fmt:message key="externalReferralAuthorizationStatusLabel.${summary.authorizationStatus.name}"/></c:if></td>
			<td><c:if test="${summary.panelReviewRequired}"><fmt:formatDate value="${summary.panelReviewDecisionDate}" pattern="MM/dd/yyyy"/></c:if></td>
			<td><c:if test="${summary.panelReviewRequired}"><c:if test="${not empty summary.panelReviewDecisionStatus}"><fmt:message key="medicalPanelReviewDecisionStatusLabel.${summary.panelReviewDecisionStatus.name}"/></c:if></c:if></td>
		</tr>
	</c:forEach>
</fmt:bundle>