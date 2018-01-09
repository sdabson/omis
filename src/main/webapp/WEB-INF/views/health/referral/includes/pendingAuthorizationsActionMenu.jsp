<%--
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.health.msgs.health">
<ul>
	<sec:authorize access="hasRole('ADMIN') or hasRole('HEALTH_ADMIN') or hasRole('EXTERNAL_REFERRAL_AUTHORIZATION_REQUEST_VIEW')">
		<c:if test="${referralType.name eq 'ALL' or referralType.name eq 'EXTERNAL_MEDICAL' or empty referralType}">
			<li><a href="${pageContext.request.contextPath}/health/referral/external/request/summary/list.html?facility=${facility.id}&offender=${offender.id}" id="externalReferralRequestSummariesLink"><fmt:message key="externalReferralRequestSummariesLink"/></a></li>
		</c:if>
	</sec:authorize>
</ul>
</fmt:bundle>