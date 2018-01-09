<%--
 - Author: Ryan Johns
 - Version: 0.1.0 (Jan 02, 2014)
 - Since: OMIS 3.0
 --%>
<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.caseload.msgs.caseload">
<table id="caseLoad" class="listTable">
	<thead>
		<tr>
		<th><fmt:message key="caseLoadTitleLabel"/></th>
		<th><fmt:message key="caseLoadDescriptionLabel"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>