<%--
 - Author: Ryan Johns
 - Version: 0.1.0 (Jan 02, 2014)
 - Since: OMIS 3.0
 --%>
<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.caseload.msgs.caseload">
<c:forEach var="worker" items="${workers}">
	<tr>
		<td><a href="#">
		load
		</a>
		</td>
		<td>
			<c:out value="${worker.workerLastName}, ${worker.workerFirstName}"/>
		</td>
		<td title="<c:out value="${worker.caseCapacityDescription}"/>">
			<c:out value="${worker.caseCapacityName}"/>
		</td>
		<td>
			<fmt:formatDate value="${worker.dateRange.startDate}"/>, <fmt:formatDate value="${worker.dateRange.endDate}"/>
		</td>
	</tr>
</c:forEach>
</fmt:bundle>