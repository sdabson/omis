<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.visitation.msgs.visitation">
<table id="visitLogSummaries" class="listTable">
	<thead>
		<tr>
			<th class="operations"></th>
			<th><fmt:message key="visitStatus"/>
			<th><fmt:message key="visitLastNameLabel"/></th>
			<th><fmt:message key="visitFirstNameLabel"/></th>
			<th class="date"><fmt:message key="visitDateLabel"/></th>
			<th><fmt:message key="visitBadgeNumberLabel"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="logListTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>