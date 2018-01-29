<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.residence.msgs.residence">
<table id="nonResidenceTermItemsTable" class="editTable">
	<thead>
		<tr>
			<th class="date"><fmt:message key="startDateTableHeaderLabel"/></th>
			<th class="date"><fmt:message key="endDateTableHeaderLabel"/></th>
			<th><fmt:message key="addressTablerHeaderLabel"/></th>
			<th><fmt:message key="locationTableHeaderLabel"/></th>
			<th><fmt:message key="residenceStatusTableHeaderLabel"/></th>
		</tr>
	</thead>
	<tbody id="nonResidenceTermItemsTableBody">
		<jsp:include page="nonResidenceTermItemsTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>