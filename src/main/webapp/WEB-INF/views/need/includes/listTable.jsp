<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.need.msgs.need">
<table id="casePlanObjectivesTable" class="listTable">
	<thead>
		<tr>
			<th class="operations"/>
			<th><fmt:message key="objectiveLabel"/></th>
			<th><fmt:message key="sourceLabel"/></th>
			<th class="date"><fmt:message key="dateLabel"/></th>
			<th><fmt:message key="criminogenicDomainLabel"/></th>
		</tr>
	</thead>
	<tbody id="casePlanObjectivesTableBody">
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>