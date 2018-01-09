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
		<li><a href="${pageContext.request.contextPath}/health/labWork/edit.html?labWork=${scheduled.id}&facility=${facility.id}" class="viewEditLink" title="<fmt:message key='editReferralScheduleLink'/>"><span class="visibleLinkLabel"><fmt:message key="editReferralScheduleLink"/></span></a></li>
		<li><a href="${pageContext.request.contextPath}/health/labWork/remove.html?labWork=${scheduled.id}&facility=${facility.id}" title="<fmt:message key='removeLink' bundle='${commonBundle}'/>"class="removeLink"><span class="visibleLinkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a></li>
	</ul>
</fmt:bundle>