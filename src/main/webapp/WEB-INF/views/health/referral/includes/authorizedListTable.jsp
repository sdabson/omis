<%-- Author: Stephen Abson --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.health.msgs.health">
<table id="authorizedReferralsListTable" class="listTable">
	<thead>
		<tr>
			<th class="operations"></th>
			<th><fmt:message key="offenderLabel"/></th>
			<th><fmt:message key="providerNameLabel"/></th>
			<th><fmt:message key="externalReferralReasonLabel"/></th>
			<th class="notes"><fmt:message key="externalReferralReasonNotesLabel"/></th>
			<th><fmt:message key="medicalFacilityLabel"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="authorizedListTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>