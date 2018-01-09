<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.education.msgs.education">
<table id="educationSummariesTable" class="listTable">
	<thead>
		<tr>
			<th class="operations"></th>
			<th><fmt:message key="attendedStartDateLabel"/></th>
			<th><fmt:message key="attendedEndDateLabel"/></th>
			<th><fmt:message key="instituteCategoryLabel"/></th>
			<th><fmt:message key="instituteNameLabel"/></th>
			<th><fmt:message key="graduatedLabel"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>