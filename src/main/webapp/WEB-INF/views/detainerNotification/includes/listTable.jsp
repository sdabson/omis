<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.detainernotification.msgs.detainerNotification">
<table id="detainerNotificationSummariesTable" class="listTable">
	<thead>
		<tr>
			<th class="operations"></th>
			<th><fmt:message key="dateWarrantReceivedLabel"/></th>
			<th><fmt:message key="notificationDateLabel"/></th>
			<th><fmt:message key="detainerNotificationType"/></th>
			<th><fmt:message key="requestingAgencyLabel"/></th>
			<th><fmt:message key="courtCaseNumberLabel"/></th>
			<th><fmt:message key="warrantNumberLabel"/></th>
			<th><fmt:message key="cancellationDateLabel"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>