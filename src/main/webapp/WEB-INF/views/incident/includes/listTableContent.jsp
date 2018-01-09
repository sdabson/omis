<%--
 - Author: Ryan Johns
 - Author: Yidong Li
 - Version: 0.1.0 (Apr 5, 2014)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<c:forEach var="incidentReportSummary" items="${incidentReportSummaries}">
	<tr>
		<td>
			<sec:authorize access="hasRole('INCIDENT_VIEW') or hasRole('INCIDENT_EDIT') or hasRole('ADMIN')">
				<a class="viewEditLink" href="${pageContext.request.contextPath}/incident/report/edit.html?incidentReport=${incidentReportSummary.id}&&offender=${offender.id}" 
				title="<fmt:message key='viewEditLink' bundle='${commonBundle}'/>"><span class="linkLabel"><fmt:message key="viewEditLink" bundle="${commonBundle}"/></span></a>
 			</sec:authorize> 
			<sec:authorize access="hasRole('INCIDENT_EDIT') or hasRole('ADMIN')">
				<a class="removeLink" href="${pageContext.request.contextPath}/incident/report/remove.html?incidentReport=${incidentReportSummary.id}" 
				title="<fmt:message key='removeLink' bundle='${commonBundle}'/>"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			</sec:authorize>		
		</td>
		<td><c:out value="${incidentReportSummary.reportNumber}"/></td>
		<td><fmt:formatDate value="${incidentReportSummary.incidentDate}" pattern="MM/dd/yyyy"/></td>
		<td><c:out value="${incidentReportSummary.reportTitle}"/></td>
		<td><c:out value="${incidentReportSummary.locationName}"/></td>
		<td>
			<c:forEach var="involvedParty" items="${summaryMap[incidentReportSummary]}">
				<c:out value="${summaryMap[incidentReportSummary].firstName}"/><c:out value="${summaryMap[incidentReportSummary].lastName}"/><c:out value="${${summaryMap[incidentReportSummary].offenderNumber}"/><br>
			</c:forEach>
		</td>
		<td><c:out value="${incidentReportSummary.reporterFirstName}"/><c:out value="${incidentReportSummary.reporterLastName}"/></td><c:out value="${incidentReportSummary.reportTitle}"/>
	</tr>
</c:forEach>