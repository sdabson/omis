<%--
 - Author: Yidong Li
 - Version: 0.1.0 (Sept 7, 2015)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.incident.msgs.incident">
	<table id="incidentStatementSummaries" class="listTable">
		<thead>
			<tr>
				<th class="operations"></th>
				<th><fmt:message key="incidentStatementNumberLabel"/></th>
				<th><fmt:message key="incidentDateTimeLabel"/></th>
				<th><fmt:message key="statusLabel"/></th>
				<th><fmt:message key="titleLabel"/></th>
				<th><fmt:message key="locationLabel"/></th>
				<th><fmt:message key="involvementLabel"/></th>
				<th><fmt:message key="reportingStaffLabel"/></th>
			</tr>
		</thead>
		<tbody>
			<jsp:include page="searchResultsContent.jsp"/>
		</tbody>
	</table>
</fmt:bundle>

