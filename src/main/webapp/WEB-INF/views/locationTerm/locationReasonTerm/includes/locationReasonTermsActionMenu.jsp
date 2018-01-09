<%--
  - Action menu for location reason terms screen.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.locationterm.msgs.profile" var="profileBundle"/>
<fmt:bundle basename="omis.locationterm.msgs.locationReasonTerm">
<ul>
	<c:if test="${not empty offender}">
		<sec:authorize access="hasRole('LOCATION_REASON_TERM_CREATE') or hasRole('ADMIN')">
			<li><a class="createLink" href="${pageContext.request.contextPath}/locationTerm/locationReasonTerm/create.html?offender=${offender.id}"><span class="visibleLinkLabel"><fmt:message key="createLocationReasonTermTitle"/></span></a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('LOCATION_TERM_PROFILE_VIEW') or hasRole('ADMIN')">
			<li><a class="locationTermProfileLink" href="${pageContext.request.contextPath}/locationTerm/profile.html?offender=${offender.id}"><span class="visibleLinkLabel"><fmt:message key="locationTermProfileHeader" bundle="${profileBundle}"/></span></a></li>
		</sec:authorize>
	</c:if>
	<c:if test="${not empty locationReasonTerm}">
		<sec:authorize access="hasRole('LOCATION_REASON_TERM_VIEW') or hasRole('ADMIN')">
			<li><a class="viewEditLink" href="${pageContext.request.contextPath}/locationTerm/locationReasonTerm/edit.html?locationReasonTerm=${locationReasonTerm.id}" title="<fmt:message key='viewEditLocationReasonTermLink'/>"><span class="visibleLinkLabel"><fmt:message key="viewEditLocationReasonTermLink"/></span></a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('LOCATION_REASON_TERM_REMOVE') or hasRole('ADMIN')">
			<li><a class="removeLink" href="${pageContext.request.contextPath}/locationTerm/locationReasonTerm/remove.html?locationReasonTerm=${locationReasonTerm.id}" title="<fmt:message key='removeLocationReasonTermLink'/>"><span class="visibleLinkLabel"><fmt:message key="removeLocationReasonTermLink"/></span></a></li>
		</sec:authorize>
	</c:if>
</ul>
</fmt:bundle>