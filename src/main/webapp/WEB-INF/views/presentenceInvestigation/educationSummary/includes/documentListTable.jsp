<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.education.msgs.educationDocument">
<table id="educationDocumentSummariesTable" class="listTable">
	<thead>
		<tr>
			<th class="operations"></th>
			<th><fmt:message key="titleLabel"/></th>
			<th><fmt:message key="categoryLabel"/></th>
			<th><fmt:message key="dateLabel"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="documentListTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>