<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.questionnaire.msgs.questionnaire">
<table id="questionnaireSectionTable" class="listTable">
	<thead>
		<tr>
			<th class="operations"></th>
			<th><fmt:message key="numberLabel"/></th>
			<th><fmt:message key="titleLabel"/></th>
			<th><fmt:message key="typeLabel"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="questionnaireSectionListTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>