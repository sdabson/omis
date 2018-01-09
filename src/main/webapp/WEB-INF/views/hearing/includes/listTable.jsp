<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.hearing.msgs.hearing">
<table id="hearingSummariesTable" class="listTable">
	<thead>
		<tr>
			<th class="operations"></th>
			<th><fmt:message key="hearingDateLabel"/></th>
			<th><fmt:message key="hearingLocationLabel"/></th>
			<th><fmt:message key="hearingTypeLabel"/></th>
			<th><fmt:message key="hearingStatusLabel"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>