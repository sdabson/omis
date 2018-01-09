<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.military.msgs.military">
<table id="militaryServiceTermsTable" class="listTable">
	<thead>
		<tr>
			<th class="operations"/>
			<th class="date"><fmt:message key="startDateLabel"/></th>
			<th class="date"><fmt:message key="endDateLabel"/></th>
			<th><fmt:message key="branchLabel"/></th>
			<th><fmt:message key="dischargeTypeLabel"/></th>
		</tr>
	</thead>
	<tbody id="militaryServiceTermsTableBody">
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>