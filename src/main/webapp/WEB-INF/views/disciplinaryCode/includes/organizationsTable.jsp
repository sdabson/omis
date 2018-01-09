<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.disciplinaryCode.msgs.disciplinaryCode">
<table id="supervisoryOrganizationDisciplinarySummariesTable" class="listTable">
	<thead>
		<tr>
			<th class="operations"></th>
			<th><fmt:message key="supervisoryOrganizationLabel"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="organizationsTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>