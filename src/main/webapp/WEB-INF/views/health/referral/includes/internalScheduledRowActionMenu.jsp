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
		<li><a href="${pageContext.request.contextPath}/health/referral/internal/assess.html?internalReferral=${scheduled.id}" class="healthAssessLink" title="<fmt:message key='assessLabel'/>"><span class="visibleLinkLabel"><fmt:message key="assessLabel"/></span></a></li>
		<li><a href="${pageContext.request.contextPath}/health/referral/internal/reschedule.html?internalReferral=${scheduled.id}&facility=${facility.id}" class="calendarLink" title="<fmt:message key='recheduleReferralLink'/>"><span class="visibleLinkLabel"><fmt:message key="recheduleReferralLink"/></span></a></li>
		<li><a href="${pageContext.request.contextPath}/health/referral/internal/editSchedule.html?internalReferral=${scheduled.id}" class="viewEditLink" title="<fmt:message key='editReferralScheduleLink'/>"><span class="visibleLinkLabel"><fmt:message key="editReferralScheduleLink"/></span></a></li>
		<li><a href="${pageContext.request.contextPath}/health/referral/internal/cancel.html?internalReferral=${scheduled.id}" title="<fmt:message key='cancelLabel' bundle='${commonBundle}'/>"class="cancelLink"><span class="visibleLinkLabel"><fmt:message key="cancelLabel" bundle="${commonBundle}"/></span></a></li>
	</ul>
</fmt:bundle>