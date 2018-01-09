<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.educationSectionSummary">
<table id="educationSummariesTable" class="listTable">
	<thead>
		<tr>
			<th><fmt:message key="attendanceTermLabel"/></th>
			<th><fmt:message key="categoryLabel"/></th>
			<th><fmt:message key="nameLabel"/></th>
			<th><fmt:message key="graduatedLabel"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>