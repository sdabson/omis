<%--
 - Displays scheduled referrals table, regardless of type.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.health.msgs.health">
<table id="scheduledReferralsListTable" class="listTable">
	<thead>
		<tr>
			<th class="evenMoreOperations"></th>
			<c:if test="${empty referralType or 'ALL' eq referralType}">
			<th><fmt:message key="referralTypeLabel"/></th>
			</c:if>
			<th><fmt:message key="offenderLabel"/></th>
			<th class="appointmentDate"><fmt:message key="scheduleDateLabel"/></th>
			<th><fmt:message key="providerNameLabel"/></th>
			<c:if test="${referralType eq 'EXTERNAL_MEDICAL'}">
			<th><fmt:message key="medicalFacilityLabel"/></th>
			</c:if>
			<th><fmt:message key="reasonLabel"/></th>
			<th class="notes"><fmt:message key="reasonNotesLabel"/></th>
			<th><fmt:message key="locationDesignatorLabel"/></th>
			<th><fmt:message key="offenderUnitLabel"/></th>
			<th class="notes"><fmt:message key="notesLabel"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="scheduledListTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>