<%--
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.health.msgs.health">
<ul>
	<c:if test="${referralType.name eq 'ALL' or referralType.name eq 'EXTERNAL_MEDICAL' or empty referralType}">
		<li><a href="${pageContext.request.contextPath}/health/referral/external/authorization/create.html?facility=${facility.id}&defaultOffender=${offender.id}" id="authorizeExternalReferralLink"><fmt:message key="authorizeExternalReferralLink"/></a></li>
	</c:if>
</ul>
</fmt:bundle>