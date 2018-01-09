<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.warrant.msgs.warrant">
<table id="warrantSummariesTable" class="listTable">
	<thead>
		<tr>
			<th class="operations"></th>
			<th><fmt:message key="warrantTypeLabel"/></th>
			<th><fmt:message key="dateLabel"/></th>
			<th><fmt:message key="clearedCanceledDateLabel"/></th>
			<th><fmt:message key="clearedMethodLabel"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>