<%--
 - Author: Ryan Johns
 - Author: Yidong Li
 - Version: 0.1.0 (Mar 31, 2014)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.health.msgs.health">
<ul>
	<c:if test="${referralType.name eq 'ALL' or referralType.name eq 'INTERNAL_MEDICAL' or empty referralType}">
		<li><a href="${pageContext.request.contextPath}/health/referral/internal/schedule.html?facility=${facility.id}&defaultOffender=${offender.id}" id="scheduleInternalReferralLink"><fmt:message key="scheduleInternalReferralLink"/></a></li>
	</c:if>
	<sec:authorize access="hasRole('ADMIN') or hasRole('HEALTH_ADMIN') or hasRole('LAB_WORK_CREATE')">
	<c:if test="${referralType.name eq 'ALL' or referralType.name eq 'LAB' or empty referralType}">
		<li><a href="${pageContext.request.contextPath}/health/labWork/create.html?facility=${facility.id}&defaultOffender=${offender.id}" id="scheduleLabWorkReferralLink"><fmt:message key="scheduleLabWorks"/></a></li>
	</c:if>
	</sec:authorize>
	<sec:authorize access="hasRole('ADMIN') or hasRole('HEALTH_ADMIN') or hasRole('LAB_WORK_EDIT')">
	<c:if test="${referralType.name eq 'ALL' or referralType.name eq 'LAB' or empty referralType}">
		<li><a href="${pageContext.request.contextPath}/health/labWork/resolve/resolve.html?facility=${facility.id}&amp;offender=${offender.id}&amp;filterByStartDate=<fmt:formatDate value='${filterByStartDate}' pattern='MM/dd/yyyy'/>&amp;filterByEndDate=<fmt:formatDate value='${filterByEndDate}' pattern='MM/dd/yyyy'/>"><fmt:message key="listLabWorkLabel"/></a></li>
	</c:if>
	</sec:authorize>
</ul>
</fmt:bundle>