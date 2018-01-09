<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.presentenceInvestigationRequest">
	<ul>
		<sec:authorize access="hasRole('PRESENTENCE_INVESTIGATION_REQUEST_LIST') or hasRole('ADMIN')">
			<c:if test="${not empty assignedUser}">
				<li>
					<a class="listLink" href="${pageContext.request.contextPath}/presentenceInvestigation/request/list.html?assignedUser=${assignedUser.id}"><span class="visibleLinkLabel"><fmt:message key="listPresentenceInvestigationRequestsByUserLink"/></span></a>
				</li>
			</c:if>
			<c:if test="${not empty offender}">
					<li>
						<a class="listLink" href="${pageContext.request.contextPath}/presentenceInvestigation/request/list.html?offender=${offender.id}"><span class="visibleLinkLabel"><fmt:message key="listPresentenceInvestigationRequestsLink"/></span></a>
					</li>
				</c:if>
			<c:if test="${not empty presentenceInvestigationRequest}">
				<li>
					<a class="homeLink" href="${pageContext.request.contextPath}/presentenceInvestigation/home.html?presentenceInvestigationRequest=${presentenceInvestigationRequest.id}"><span class="visibleLinkLabel"><fmt:message key="presentenceInvestigationHomeLink"/></span></a>
				</li>
			</c:if>
		</sec:authorize>
	</ul>
</fmt:bundle>