<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.questionnaire.msgs.questionnaire">
<table id="questionnaireSectionTable" class="listTable">
	<thead>
		<tr>
			<th class="operations"></th>
			<th><fmt:message key="questionNumberLabel"/></th>
			<th><fmt:message key="questionTextLabel"/></th>
			<th><fmt:message key="questionTypeLabel"/></th>
			<th><fmt:message key="answersLabel"/></th>
			<th><fmt:message key="cardinalityLabel"/></th>
			<th><fmt:message key="helpTextLabel"/></th>
			<th><fmt:message key="requiredLabel"/></th>
			<th><fmt:message key="validLabel"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="questionListTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>