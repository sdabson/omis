<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.presentenceinvestigation.msgs.presentenceInvestigationHome" var="psiBundle" />
<fmt:bundle basename="omis.presentenceinvestigation.msgs.otherPertinentInformationSectionSummary">
	<ul>
		<sec:authorize access="hasRole('PRESENTENCE_INVESTIGATION_REQUEST_VIEW') or hasRole('ADMIN')">
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/presentenceInvestigation/home.html?presentenceInvestigationRequest=${presentenceInvestigationRequest.id}">
					<span class="visibleLinkLabel"><fmt:message key="presentenceInvestigationHomeLink" bundle="${psiBundle}" /></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>