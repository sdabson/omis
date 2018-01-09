<%--
 - Displays resolved referrals table, regardless of type.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.health.msgs.health">
<table id="resovledReferralsListTable" class="listTable">
	<thead>
		<tr>
			<th class="operations"></th>
			<th><fmt:message key="offenderLabel"/></th>
			<th class="appointmentDate"><fmt:message key="scheduleDateLabel"/></th>
			<th><fmt:message key="referralReasonLabel"/></th>
			<th><fmt:message key="referralReasonNotesLabel"/></th>
			<th><fmt:message key="providerNameLabel"/></th>
			<th class="notes"><fmt:message key="schedulingNotesLabel"/></th>
			<th><fmt:message key="resolutionLabel"/></th>
			<th class="notes"><fmt:message key="notesLabel"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="resolvedListTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>