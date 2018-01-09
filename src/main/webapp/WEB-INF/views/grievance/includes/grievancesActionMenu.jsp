<%--
  - Action menu for screen to list grievances and grievance items on 
  - screen to list grievances.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle var="grievanceBundle" basename="omis.grievance.msgs.grievance"/>
<ul>
	<sec:authorize access="hasRole('ADMIN') or hasRole('GRIEVANCE_CREATE')">
	<c:forEach var="subject" items="${subjects}">
		<c:if test="${not empty offender}">
			<li>
				<a class="createLink" href="${pageContext.request.contextPath}/grievance/create.html?offender=${offender.id}&amp;subject=${subject.id}"><span class="visibleLinkLabel"><fmt:message key="createGrievanceForOffenderLink" bundle="${grievanceBundle}"><fmt:param>${subject.name}</fmt:param></fmt:message></span></a>
			</li>
		</c:if>
		<c:if test="${not empty location}">
			<li>
				<a class="createLink" href="${pageContext.request.contextPath}/grievance/create.html?location=${location.id}&amp;subject=${subject.id}"><span class="visibleLinkLabel"><fmt:message key="createGrievanceAtLocationLink" bundle="${grievanceBundle}"><fmt:param>${subject.name}</fmt:param></fmt:message></span></a>
			</li>
		</c:if>
	</c:forEach>
	</sec:authorize>
	<sec:authorize access="hasRole('GRIEVANCE_VIEW') or hasRole('ADMIN')">
		<c:if test="${not empty offender}">
		<li>
			<a href="${pageContext.request.contextPath}/grievance/grievanceListingReport.html?offender=${offender.id}&reportFormat=PDF" class="newTab reportLink"><fmt:message key="grievanceListingReportLinkLabel" bundle="${grievanceBundle}"/></a>
		</li>
		</c:if>
	</sec:authorize>
	<sec:authorize access="hasRole('ADMIN') or hasRole('GRIEVANCE_VIEW')">
		<c:if test="${not empty grievance}">
		<li>
			<a class="viewEditLink" href="${pageContext.request.contextPath}/grievance/edit.html?grievance=${grievance.id}"><span class="visibleLinkLabel"><fmt:message key="viewEditGrievanceLink" bundle="${grievanceBundle}"/></span></a>
		</li>
		</c:if>
	</sec:authorize>
	<sec:authorize access="hasRole('ADMIN') or hasRole('GRIEVANCE_REMOVE')">
		<c:if test="${not empty grievance}">
		<li>
			<a class="removeLink" href="${pageContext.request.contextPath}/grievance/remove.html?grievance=${grievance.id}"><span class="visibleLinkLabel"><fmt:message key="removeGrievanceLink" bundle="${grievanceBundle}"/></span></a>
		</li>
		</c:if>
	</sec:authorize>
	<sec:authorize access="hasRole('GRIEVANCE_VIEW') or hasRole('ADMIN')">
		<c:if test="${not empty grievance}">
		<li>
			<a href="${pageContext.request.contextPath}/grievance/grievanceDetailsReport.html?grievance=${grievance.id}&reportFormat=PDF" class="newTab reportLink"><fmt:message key="grievanceDetailsReportLinkLabel" bundle="${grievanceBundle}"/></a>
		</li>
		</c:if>
	</sec:authorize>
	<sec:authorize access="hasRole('GRIEVANCE_VIEW') or hasRole('ADMIN')">
		<c:if test="${not empty grievance}">
		<li>
			<a href="${pageContext.request.contextPath}/grievance/grievanceDetailHistoryReport.html?grievance=${grievance.id}&reportFormat=PDF" class="newTab reportLink"><fmt:message key="grievanceDetailHistoryReportLinkLabel" bundle="${grievanceBundle}"/></a>
		</li>
		</c:if>
	</sec:authorize>
</ul>