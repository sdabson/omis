<%--
 - Author: Stephen Abson
 - Author: Yidong Li
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.health.msgs.health">
<ul>
	<sec:authorize access="hasRole('ADMIN') or hasRole('HEALTH_ADMIN') or hasRole('LAB_WORK_EDIT')">
		<c:if test="${referralType.name eq 'ALL' or referralType.name eq 'LAB' or empty referralType}">
			<li><a href="${pageContext.request.contextPath}/health/labWork/resolve/resolve.html?facility=${facility.id}&amp;offender=${offender.id}&amp;sampleTaken=false&amp;filterByStartDate=<fmt:formatDate value='${filterByStartDate}' pattern='MM/dd/yyyy'/>&amp;filterByEndDate=<fmt:formatDate value='${filterByEndDate}' pattern='MM/dd/yyyy'/>"><fmt:message key="listLabWorkLabel"/></a></li>
		</c:if>
	</sec:authorize>
</ul>
</fmt:bundle>