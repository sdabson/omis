<%--
 - Author: Ryan Johns
 - Author: Stephen Abson
 - Version: 0.1.0 (May 12, 2014)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.health.msgs.health">
<ul>
	<sec:authorize access="hasRole('ADMIN') or hasRole('HEALTH_MODULE') or hasRole('HEALTH_ADMIN')">
		<li><a href="${pageContext.request.contextPath}/health/request/list.html?facility=${facility.id}&amp;resolvedOnly=true&amp;referralType=${referralType.name}"><fmt:message key="resolvedRequestsLink"/></a></li>
	</sec:authorize>
	<sec:authorize access="hasRole('ADMIN') or hasRole('HEALTH_MODULE') or hasRole('HEALTH_ADMIN')">
		<li><a href="${pageContext.request.contextPath}/health/request/create.html?facility=${facility.id}&amp;defaultOffender=${offender.id}&amp;referralType=${referralType.name}"><fmt:message key="requestReferralLink"/></a></li>
	</sec:authorize>
</ul>
</fmt:bundle>