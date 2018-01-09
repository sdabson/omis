<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.presentenceInvestigationHome">
	<ul>
		<sec:authorize access="hasRole('PRESENTENCE_INVESTIGATION_REQUEST_EDIT') or hasRole('ADMIN')">
			<li>
				<a class="viewEditLink" href="${pageContext.request.contextPath}/presentenceInvestigation/request/edit.html?presentenceInvestigationRequest=${presentenceInvestigationRequest.id}&onReturn=home">
					<span class="visibleLinkLabel"><fmt:message key="editRequestDetailsLink"/></span></a>
			</li>
		</sec:authorize>
		<sec:authorize access="hasRole('PRESENTENCE_INVESTIGATION_REQUEST_LIST') or hasRole('ADMIN')">
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/presentenceInvestigation/request/list.html?assignedUser=${assignedUser.id}"><span class="visibleLinkLabel"><fmt:message key="listPresentenceInvestigationRequestsByUserLink"/></span></a>
			</li>
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/presentenceInvestigation/request/list.html?offender=${offender.id}">
					<span class="visibleLinkLabel"><fmt:message key="listRequestsLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>