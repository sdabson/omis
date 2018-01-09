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
		<c:choose>
			<c:when test="${'EXTERNAL_MEDICAL' eq pending.category.name}">
				<c:choose>
					<c:when test="${empty authorizationRequest and empty authorization}">
						<sec:authorize access="hasRole('ADMIN') or hasRole('HEALTH_ADMIN') or hasRole('EXTERNAL_REFERRAL_AUTHORIZATION_REQUEST_CREATE')">
							<li><a href="${pageContext.request.contextPath}/health/referral/external/authorization/request/createFromRequest.html?healthRequest=${pending.id}" class="authorizeLink" title="<fmt:message key='requestExternalReferralAuthorizationLink'/>"><span class="visibleLinkLabel"><fmt:message key="requestExternalReferralAuthorizationLink"/></span></a></li>
						</sec:authorize>
					</c:when>
					<c:when test="${not empty authorizationRequest and empty authorization}">
						<sec:authorize access="hasRole('ADMIN') or hasRole('HEALTH_ADMIN') or hasRole('EXTERNAL_REFERRAL_AUTHORIZATION_REQUEST_EDIT')">
							<li><a href="${pageContext.request.contextPath}/health/referral/external/authorization/request/edit.html?request=${authorizationRequest.id}" class="authorizeLink" title="<fmt:message key='editExternalReferralAuthorizationRequestLink'/>"><span class="visibleLinkLabel"><fmt:message key="editExternalReferralAuthorizationRequestLink"/></span></a></li>
						</sec:authorize>
					</c:when>
					<c:when test="${not empty authorizationRequest and not empty authorization and not authorized}">
						<sec:authorize access="hasRole('ADMIN') or hasRole('HEALTH_ADMIN') or hasRole('EXTERNAL_REFERRAL_AUTHORIZATION_REQUEST_EDIT')">
							<li><a href="${pageContext.request.contextPath}/health/referral/external/authorization/request/edit.html?request=${authorizationRequest.id}" class="authorizeLink" title="<fmt:message key='editExternalReferralAuthorizationRequestLink'/>"><span class="visibleLinkLabel"><fmt:message key="editExternalReferralAuthorizationRequestLink"/></span></a></li>
						</sec:authorize>
					</c:when>
					<c:when test="${not empty authorizationRequest and not empty authorization and authorized}">
						<sec:authorize access="hasRole('ADMIN') or hasRole('HEALTH_ADMIN') or hasRole('AUTHORIZED_EXTERNAL_REFERRAL_CREATE')">
							<li><a href="${pageContext.request.contextPath}/health/referral/external/scheduleAuthorized.html?authorization=${authorization.id}" class="calendarLink" title="<fmt:message key='scheduleLabel'/>"><span class="visibleLinkLabel"><fmt:message key="scheduleLabel"/></span></a></li>
						</sec:authorize>
					</c:when>
				</c:choose>
			</c:when>
			<c:when test="${'INTERNAL_MEDICAL' eq pending.category.name}">
				<li><a href="${pageContext.request.contextPath}/health/referral/internal/scheduleFromRequest.html?request=${pending.id}" class="calendarLink" title="<fmt:message key='scheduleInternalReferralLink'/>"><span class="visibleLinkLabel"><fmt:message key="scheduleInternalReferralLink"/></span></a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${pageContext.request.contextPath}/health/request/schedule.html?request=${pending.id}" class="calendarLink" title="<fmt:message key='scheduleLabel'/>"><span class="visibleLinkLabel"><fmt:message key="scheduleLabel"/></span></a></li>	
			</c:otherwise>
		</c:choose>
		<li><a href="${pageContext.request.contextPath}/health/request/edit.html?request=${pending.id}" class="viewEditLink" title="<fmt:message key='viewEditLink' bundle='${commonBundle}'/>"><span class="visibleLinkLabel"><fmt:message key="viewEditLink" bundle="${commonBundle}"/></span></a></li>
		<li><a href="${pageContext.request.contextPath}/health/request/cancel.html?request=${pending.id}" class="cancelLink cancelRequestLink" title="<fmt:message key='cancelLabel' bundle='${commonBundle}'/>"><span class="visibleLinkLabel"><fmt:message key="cancelLabel" bundle="${commonBundle}"/></span></a></li>
		<sec:authorize access="hasRole('APP_DEV')">
			<c:if test="${pending.labsRequired}">
				<li><a href="${pageContext.request.contextPath}/health/labWork/requirement/request/edit.html?healthRequest=${pending.id}" class="labLink" title="<fmt:message key='requestLabWorkRequirementLink'/>"><span class="visibleLinkLabel"><fmt:message key="requestLabWorkRequirementLink"/></span></a></li>
			</c:if>
		</sec:authorize>
	</ul>
</fmt:bundle>