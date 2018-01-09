<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.questionnaire.msgs.questionnaire">
<table id="questionnaireTypeTable" class="listTable">
	<thead>
		<tr>
			<th class="operations"></th>
			<th><fmt:message key="categoryLabel"/></th>
			<th><fmt:message key="questionnaireTypeLabel"/></th>
			<th><fmt:message key="cycleLabel"/></th>
			<th><fmt:message key="startDateLabel"/></th>
			<th><fmt:message key="endDateLabel"/></th>
			<th><fmt:message key="validLabel"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="questionnaireTypeListTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>