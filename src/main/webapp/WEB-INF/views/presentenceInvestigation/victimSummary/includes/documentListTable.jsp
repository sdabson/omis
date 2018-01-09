<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.victimSectionSummary">
<table id="victimDocumentSummariesTable" class="listTable">
	<thead>
		<tr>
			<th class="operations"></th>
			<th><fmt:message key="titleLabel"/></th>
			<th><fmt:message key="uploadDateLabel"/></th>
			<th><fmt:message key="createdByLabel"/></th>
		</tr>
	</thead>
	<tbody id="victimDocumentSummariesTableBody">
		<jsp:include page="documentListTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>