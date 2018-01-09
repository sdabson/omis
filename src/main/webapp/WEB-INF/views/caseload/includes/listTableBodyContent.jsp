<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.caseload.msgs.caseload">
<c:forEach var="caseWorkSummary" items="${caseWorkSummaries}" varStatus="status">
<c:set var="caseWorkSummary" value="${caseWorkSummary}" scope="request"/>
<tr id="caseWorkerCaseRow${status.index}">
<td>
	<a class="actionMenuItem rowActionMenuLinks"
				id="summaryActionMenuLink${status.index}"
				href="${pageContext.request.contextPath}/caseload/caseloadRowActionMenu.html?workerAssignment=${caseWorkSummary.caseWorkerAssignmentId}"></a>
</td>
		<td>
			<c:out value="${caseWorkSummary.caseWorkerFirstName} ${caseWorkSummary.caseWorkerLastName}"/>	
		</td>
		<td>
			<c:out value="${caseWorkSummary.caseworkCategory}"/>
		</td>
		<td>			
			<fmt:formatDate var="startDate" value="${caseWorkSummary.startDate}" pattern="MM/dd/yyyy"/>
			<fmt:formatDate var="endDate" value="${caseWorkSummary.endDate}" pattern="MM/dd/yyyy"/>
			<c:choose>
			<c:when test="${not empty caseWorkSummary.endDate}">
			<fmt:message key="startEndDate">
				<fmt:param value="${startDate}"/>
				<fmt:param value="${endDate}"/>
			</fmt:message>
			</c:when>
			<c:otherwise>
			<fmt:message key="startEndDate">
				<fmt:param value="${startDate}"/>
				<fmt:param value=""/>
			</fmt:message>
			</c:otherwise>
			</c:choose>
		</td>
		<td>
			<c:out value="${caseWorkSummary.caseloadName}"/>
		</td>
</tr>
</c:forEach>	
</fmt:bundle>