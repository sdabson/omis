<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.workassignment.msgs.summary">
<c:if test="${not empty workAssignmentSummary}">
<div class="offenderHeaderDetailsSection">
	<div class="headerCell">
		<span class="offenderHeaderFieldLabel"><fmt:message key="workAssignmentLabel"/></span>
		<a href="${pageContext.request.contextPath}/workAssignment/list.html?offender=${offenderSummary.id}">
			<span class="offenderHeaderFieldValue"><c:out value="${workAssignmentSummary.categoryName}" /></span>
		</a>
	</div>
	<div class="headerCell">
		<span class="offenderHeaderFieldLabel"><fmt:message key="workAssignmentFenceRestrictionLabel"/></span>
		<a href="${pageContext.request.contextPath}/workAssignment/list.html?offender=${offenderSummary.id}">
			<span class="offenderHeaderFieldValue"><c:out value="${workAssignmentSummary.fenceRestrictionName}" /></span>
		</a>
	</div>
	<div class="headerCell">
		<span class="offenderHeaderFieldLabel"><fmt:message key="workAssignmentDateLabel"/></span>
		<a href="${pageContext.request.contextPath}/workAssignment/list.html?offender=${offenderSummary.id}">
			<span class="offenderHeaderFieldValue"><fmt:formatDate value="${workAssignmentSummary.assignedDate}" pattern="MM/dd/yyyy"/></span>
		</a>
	</div>
	<c:if test="${not empty workAssignmentSummary.terminationDate}">
		<div class="headerCell">
			<span class="offenderHeaderFieldLabel"><fmt:message key="workAssignmentTerminationDateLabel"/></span>
			<a href="${pageContext.request.contextPath}/workAssignment/list.html?offender=${offenderSummary.id}">
				<span class="offenderHeaderFieldValue"><fmt:formatDate value="${workAssignmentSummary.terminationDate}" pattern="MM/dd/yyyy"/></span>
			</a>
		</div>
	</c:if>
</div>
</c:if>
</fmt:bundle>