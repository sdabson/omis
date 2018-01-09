<%--
 - Author: Ryan Johns
 - Author: Stephen Abson
 - Version: 0.1.0 (Apr 14, 2014)
 - Since: OMIS 3.0 
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.health.msgs.health">
<c:forEach var="pending" items="${pendingRequestSummaries}">
	<tr>
		<td>
			<c:choose>
				<c:when test="${'EXTERNAL_MEDICAL' eq pending.category.name}">
					<c:choose>
						<c:when test="${empty pending.authorizationRequestId and empty pending.authorizationId}">
							<sec:authorize access="hasRole('ADMIN') or hasRole('HEALTH_ADMIN') or hasRole('EXTERNAL_REFERRAL_AUTHORIZATION_REQUEST_CREATE')">
								<a href="${pageContext.request.contextPath}/health/referral/external/authorization/request/createFromRequest.html?healthRequest=${pending.id}" class="authorizeLink" title="<fmt:message key='requestExternalReferralAuthorizationLink'/>"><span class="linkLabel"><fmt:message key="requestExternalReferralAuthorizationLink"/></span></a>
							</sec:authorize>
						</c:when>
						<c:when test="${not empty pending.authorizationRequestId and empty pending.authorizationId}">
							<sec:authorize access="hasRole('ADMIN') or hasRole('HEALTH_ADMIN') or hasRole('EXTERNAL_REFERRAL_AUTHORIZATION_REQUEST_EDIT')">
								<a href="${pageContext.request.contextPath}/health/referral/external/authorization/request/edit.html?request=${pending.authorizationRequestId}" class="authorizeLink" title="<fmt:message key='editExternalReferralAuthorizationRequestLink'/>"><span class="linkLabel"><fmt:message key="editExternalReferralAuthorizationRequestLink"/></span></a>
							</sec:authorize>
						</c:when>
						<c:when test="${not empty pending.authorizationRequestId and not empty pending.authorizationId and not pending.authorized}">
							<sec:authorize access="hasRole('ADMIN') or hasRole('HEALTH_ADMIN') or hasRole('EXTERNAL_REFERRAL_AUTHORIZATION_REQUEST_EDIT')">
								<a href="${pageContext.request.contextPath}/health/referral/external/authorization/request/edit.html?request=${pending.authorizationRequestId}" class="authorizeLink" title="<fmt:message key='editExternalReferralAuthorizationRequestLink'/>"><span class="linkLabel"><fmt:message key="editExternalReferralAuthorizationRequestLink"/></span></a>
							</sec:authorize>
						</c:when>
						<c:when test="${not empty pending.authorizationRequestId and not empty pending.authorizationId and pending.authorized}">
							<sec:authorize access="hasRole('ADMIN') or hasRole('HEALTH_ADMIN') or hasRole('AUTHORIZED_EXTERNAL_REFERRAL_CREATE')">
								<a href="${pageContext.request.contextPath}/health/referral/external/scheduleAuthorized.html?authorization=${pending.authorizationId}" class="calendarLink" title="<fmt:message key='scheduleLabel'/>"><span class="linkLabel"><fmt:message key="scheduleLabel"/></span></a>
							</sec:authorize>
						</c:when>
					</c:choose>
				</c:when>
				<c:when test="${'INTERNAL_MEDICAL' eq pending.category.name}">
					<a href="${pageContext.request.contextPath}/health/referral/internal/scheduleFromRequest.html?request=${pending.id}" class="calendarLink" title="<fmt:message key='scheduleInternalReferralLink'/>"><span class="linkLabel"><fmt:message key="scheduleInternalReferralLink"/></span></a>
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath}/health/request/schedule.html?request=${pending.id}" class="calendarLink" title="<fmt:message key='scheduleLabel'/>"><span class="linkLabel"><fmt:message key="scheduleLabel"/></span></a>	
				</c:otherwise>
			</c:choose>
			<a href="${pageContext.request.contextPath}/health/request/edit.html?request=${pending.id}" class="viewEditLink" title="<fmt:message key='viewEditLink' bundle='${commonBundle}'/>"><span class="linkLabel"><fmt:message key="viewEditLink" bundle="${commonBundle}"/></span></a>
			<a href="${pageContext.request.contextPath}/health/request/cancel.html?request=${pending.id}" class="cancelLink cancelRequestLink" title="<fmt:message key='cancelLabel' bundle='${commonBundle}'/>"><span class="linkLabel"><fmt:message key="cancelLabel" bundle="${commonBundle}"/></span></a>
			<sec:authorize access="hasRole('APP_DEV')">
				<c:if test="${pending.labsRequired}">
					<a href="${pageContext.request.contextPath}/health/labWork/requirement/request/edit.html?healthRequest=${pending.id}" class="labLink" title="<fmt:message key='requestLabWorkRequirementLink'/>"><span class="linkLabel"><fmt:message key="requestLabWorkRequirementLink"/></span></a>
				</c:if>
			</sec:authorize>
		</td><td>
			<c:out value="${pending.offenderLastName}"/>, <c:out value="${pending.offenderFirstName}"/> (<c:out value="${pending.offenderNumber}"/>)
		</td><td>
			<c:if test="${pending.asap}"><fmt:message key="yesLabel" bundle="${commonBundle}"/></c:if>
		</td>
		<td>
			<fmt:formatDate value="${pending.date}" pattern="MM/dd/yyyy"/>
		</td><td>
			<fmt:message key="healthRequestCategoryLabel.${pending.category.name}"/>
		</td><td>
			<c:out value="${pending.unitName}"/>
		</td><td>
			<c:if test="${not empty pending.notes}">
				<a class="commentLink" title="<c:out value="${pending.notes}"/>"></a>
			</c:if>
		</td>
	</tr>
</c:forEach>
</fmt:bundle>