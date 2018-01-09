<%--
 - Author: Josh Divine
 - Version: 0.1.0 (Mar 21, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.health.msgs.health">
	<ul>
		<li><a href="${pageContext.request.contextPath}/health/referral/external/scheduleAuthorized.html?authorization=${authorized.id}" class="calendarLink" title="<fmt:message key='scheduleLabel'/>"><span class="visibleLinkLabel"><fmt:message key="scheduleLabel"/></span></a></li>
	</ul>
</fmt:bundle>