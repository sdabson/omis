<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.questionnaire.msgs.questionnaire">
<table id="questionnaireSectionSummariesTable" class="listTable">
	<thead>
		<tr>
			<th class="operations"></th>
			<th><fmt:message key="titleLabel"/></th>
			<th><fmt:message key="assessorLabel"/></th>
			<th><fmt:message key="dateLabel"/></th>
			<th><fmt:message key="statusLabel"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="administeredQuestionnaireListTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>