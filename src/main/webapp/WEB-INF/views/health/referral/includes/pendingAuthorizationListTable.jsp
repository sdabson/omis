<%--
 - Table to list referrals requiring authorization but not yet authorized. 
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.health.msgs.health" var="healthBundle"/>
<table id="pendingAuthorizationListTable" class="listTable">
	<thead>
		<tr>
			<th class="operations"></th>
			<th><fmt:message key="offenderLabel" bundle="${healthBundle}"/></th>
			<th><fmt:message key="providerNameLabel" bundle="${healthBundle}"/></th>
			<th><fmt:message key="externalReferralReasonLabel" bundle="${healthBundle}"/></th>
			<th class="notes"><fmt:message key="externalReferralReasonNotesLabel" bundle="${healthBundle}"/></th>
			<th><fmt:message key="medicalFacilityLabel" bundle="${healthBundle}"/></th>
			<th><fmt:message key="referredByProviderAssignmentLabel" bundle="${healthBundle}"/></th>
			<th><fmt:message key="externalReferralReferredDateLabel" bundle="${healthBundle}"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="pendingAuthorizationListTableBodyContent.jsp"/>
	</tbody>
</table>