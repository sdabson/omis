<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.violationevent.msgs.violationEvent">
<table id="violationEventSummariesTable" class="listTable">
	<thead>
		<tr>
			<th class="operations"></th>
			<th><fmt:message key="eventDateLabel"/></th>
			<th><fmt:message key="eventCategoryLabel"/></th>
			<th><fmt:message key="eventJurisdictionLabel"/></th>
			<th><fmt:message key="violationsLabel"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>