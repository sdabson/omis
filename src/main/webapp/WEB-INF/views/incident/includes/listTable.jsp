<%--
 - Author: Yidong Li
 - Version: 0.1.0 (Sept 7, 2015)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.incident.msgs.incident">
<table id="incidentReportSummaries" class="listTable">
	<thead>
		<tr>
		<th class="operations"/>
		<th><fmt:message key="incidentReportIdLabel"/></th>
		<th><fmt:message key="incidentDateTimeLabel"/></th>
		<th><fmt:message key="titltLabel"/></th>
		<th><fmt:message key="locationLabel"/></th>
		<th><fmt:message key="involvementLabel"/></th>
		<th><fmt:message key="reporterLabel"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="listTableContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>