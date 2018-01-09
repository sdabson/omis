<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.workrestriction.msgs.workRestriction">
<table id="workRestrictionSummariesTable" class="listTable">
	<thead>
		<tr>
			<th class="operations"></th>
			<th><fmt:message key="categoryLabel"/></th>
			<th><fmt:message key="authorizedByLabel"/></th>
			<th><fmt:message key="authorizationDateLabel" /></th>
			<th><fmt:message key="notesLabel" /></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>