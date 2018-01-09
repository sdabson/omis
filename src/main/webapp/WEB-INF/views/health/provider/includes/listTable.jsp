<%--
 - Author: Ryan Johns
 - Version: 0.1.0 (Apr 4, 2014)
 - Since: OMIS 3.0
 --%>
 <?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="common"/>
<fmt:setBundle basename="omis.health.msgs.health" var="provider"/>
<table id="providerListTable" class="listTable">
	<thead>
		<tr>
		<th class="operations"></th>
		<th><fmt:message key="nameLabel" bundle="${provider}"/></th>
		<th><fmt:message key="providerAssignmentTitleLabel" bundle="${provider}"/></th>
		<th><fmt:message key="providerAssignmentAbbreviationLabel" bundle="${provider}"/></th>
		<th><fmt:message key="providerAssignmentCategoryLabel" bundle="${provider}"/></th>
		<th><fmt:message key="startDateLabel" bundle="${common}"/></th>
		<th><fmt:message key="endDateLabel" bundle="${common}"/></th>
		<th><fmt:message key="internalExternalContractedLabel" bundle="${provider}"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>
