<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.hearing.msgs.hearing">
<table id="infractionTable" class="editTable">
	<thead>
		<tr>
			<th class="operations" />
			<th><fmt:message key="eventDetailsLabel"/></th>
			<th><fmt:message key="violationLabel"/></th>
		</tr>
	</thead>
	<tbody id="infractionTableBody">
		<jsp:include page="infractionItemTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>