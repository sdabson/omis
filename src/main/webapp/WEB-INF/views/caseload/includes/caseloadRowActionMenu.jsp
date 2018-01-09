<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.caseload.msgs.caseload">
	<ul>	
	<%-- <c:if test="${not empty caseWorkSummaries}">
		<li>
			<a class="viewLink" href="${pageContext.request.contextPath}/caseload/listCaseloadCaseAssignments.html?caseload=${caseload.id}"><fmt:message key="viewCaseAssignmentsLabel"/></a>		
		</li>
	</c:if>
	<c:if test="${not empty offenderSummaries}">
		<li>
			<a class="viewLink" href="${pageContext.request.contextPath}/caseload/listCaseWorkersCaseloadAssignment.html?workerAssignment=${workerAssignment.id}"><fmt:message key="viewOffenderCaseAssignmentsLabel"/></a>		
		</li>
		
		<li>
			<a class="viewEditLink" href="${pageContext.request.contextPath}/caseload/temporaryTransferOffenders.html?workerAssignment=${workerAssignment.id}"><fmt:message key="temporaryTransferOffendersLabel"/></a>			
		</li>	
	</c:if>	
		<li>
			<a class="viewEditLink" href="${pageContext.request.contextPath}/caseload/reassignOfficer.html?workerAssignment=${workerAssignment.id}"><fmt:message key="reassignOfficerLabel"/></a>			
		</li>	 --%>
	</ul>
</fmt:bundle>