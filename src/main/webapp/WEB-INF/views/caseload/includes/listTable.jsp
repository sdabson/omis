<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.caseload.msgs.caseload">
<table id="caseloadListTable" class="listTable">
	<thead>
		<tr>			
			<th class="operations"/>
			<th><fmt:message key="caseWorkerLabel"/></th>
 			<th><fmt:message key="caseloadCategoryLabel"/></th>
 			<th><fmt:message key="caseloadLabel"/></th>
			<th ><fmt:message key="startEndDatesLabel"/></th>
		</tr>
	</thead>
	<tbody>	
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>