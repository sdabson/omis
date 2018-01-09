<%--
  - Placement home action menu.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.placement.msgs.placement" var="placementBundle"/>
<fmt:setBundle basename="omis.program.msgs.program" var="programBundle"/>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<ul>
	<li>
		<c:choose>
			<c:when test="${not empty fromCorrectionalStatus}">
				<fmt:message key="correctionalStatusMessage" bundle="${placementBundle}">
					<fmt:param value="${fromCorrectionalStatus.name}"/>
				</fmt:message>
			</c:when>
			<c:otherwise>
				<fmt:message key="newCommitmentCorrectionalStatusMessage" bundle="${placementBundle}"/>
			</c:otherwise>
		</c:choose>
	</li>
	<sec:authorize access="hasRole('PLACEMENT_TERM_EDIT') or hasRole('ADMIN')">
	<c:if test="${correctionalStatusChangeAllowed}">
		<c:forEach var="allowedCorrectionalStatusChangeSummary" items="${allowedCorrectionalStatusChangeSummaries}">
			<c:choose>
				<c:when test="${allowedCorrectionalStatusChangeSummary.fromCorrectionalStatusExists}">
					<c:set var="fromCorrectionalStatusName" value="${allowedCorrectionalStatusChangeSummary.fromCorrectionalStatusName}"/>
				</c:when>
				<c:otherwise>
					<fmt:message var="fromCorrectionalStatusName" key="startCorrectionalStatusLabel" bundle="${placementBundle}"/>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${allowedCorrectionalStatusChangeSummary.toCorrectionalStatusExists}">
					<c:set var="toCorrectionalStatusName" value="${allowedCorrectionalStatusChangeSummary.toCorrectionalStatusName}"/>
				</c:when>
				<c:otherwise>
					<fmt:message var="toCorrectionalStatusName" key="endCorrectionalStatusLabel" bundle="${placementBundle}"/>
				</c:otherwise>
			</c:choose>
			<fmt:message var="actionMessage" key="correctionalStatusChangeActionMessage" bundle="${placementBundle}">
				<fmt:param><c:out value="${fromCorrectionalStatusName}"/></fmt:param>
				<fmt:param><c:out value="${toCorrectionalStatusName}"/></fmt:param>
			</fmt:message>
			<li>
				<a href="${pageContext.request.contextPath}/placement/changeCorrectionalStatus.html?offender=${offender.id}&amp;fromCorrectionalStatus=${fromCorrectionalStatus.id}&amp;toCorrectionalStatus=${allowedCorrectionalStatusChangeSummary.toCorrectionalStatusId}&amp;action=${allowedCorrectionalStatusChangeSummary.actionId}" title="${actionMessage}"><c:out value="${allowedCorrectionalStatusChangeSummary.actionName}"/></a>
			</li>
		</c:forEach>
	</c:if>
	</sec:authorize>
	<sec:authorize access="hasRole('LOCATION_TERM_EDIT') or hasRole('ADMIN')">
	  <c:if test="${fn:length(locationTermChangeActionSummaries) gt 0}">
	    <li>
	      <fmt:message key="changePhysicalLocationMessage" bundle="${placementBundle}"/>
	    </li>
	    <c:forEach var="locationTermChangeActionSummary" items="${locationTermChangeActionSummaries}">
	      <li>
	        <a href="${pageContext.request.contextPath}/placement/changeLocation.html?offender=${offender.id}&amp;correctionalStatus=${fromCorrectionalStatus.id}&amp;action=${locationTermChangeActionSummary.id}" title="<c:out value='${locationTermChangeActionSummary.name}'/>"><c:out value="${locationTermChangeActionSummary.name}"/></a>
	      </li>
	    </c:forEach>
	  </c:if>
	</sec:authorize>
	<sec:authorize access="hasRole('SUPERVISORY_ORGANIZATION_TERM_EDIT') or hasRole('ADMIN')">
	  <c:if test="${fn:length(placementTermChangeActionsForSupervisoryOrganizationChange) gt 0}">
	  	<li>
	  		<fmt:message key="changeSupervisoryOrganizationMessage" bundle="${placementBundle}"/>
	  	</li>
	  	<c:forEach var="placementTermChangeActionForSupervisoryOrganizationChange" items="${placementTermChangeActionsForSupervisoryOrganizationChange}">
	  		<li>
	  			<a href="${pageContext.request.contextPath}/placement/changeSupervisoryOrganization.html?offender=${offender.id}&amp;action=${placementTermChangeActionForSupervisoryOrganizationChange.id}&amp;correctionalStatus=${fromCorrectionalStatus.id}&amp;fromSupervisoryOrganization=${fromSupervisoryOrganization.id}"><c:out value="${placementTermChangeActionForSupervisoryOrganizationChange.name}"/></a>
	  		</li>
	  	</c:forEach>
	  </c:if>
	</sec:authorize>
	<sec:authorize access="hasRole('PROGRAM_PLACEMENT_EDIT') or hasRole('ADMIN')">
		<c:if test="${hasActivePlacementTerm}">
			<li><fmt:message key="programPlacementHeader" bundle="${programBundle}"/></li>
			<li><a href="${pageContext.request.contextPath}/placement/changeProgram.html?offender=${offender.id}"><fmt:message key="changeProgramPlacementLink" bundle="${programBundle}"/></a></li>
		</c:if>
	</sec:authorize>
</ul>