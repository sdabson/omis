<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.disciplinaryCode.msgs.disciplinaryCode">
<table id="supervisoryOrganizationDisciplinaryCodeSummariesTable" class="listTable">
	<thead>
		<tr>
			<th class="operations"></th>
			<th><fmt:message key="codeLabel"/></th>
			<th><fmt:message key="descriptionLabel" /></th>
			<th><fmt:message key="extendedDescriptionLabel" /></th>
			<th><fmt:message key="startDateLabel" /></th>
			<th><fmt:message key="endDateLabel" /></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>