<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle" />
<fmt:bundle basename="omis.boardhearing.msgs.boardHearing">
	<ul>
		<sec:authorize access="hasRole('PAROLE_ELIGIBILITY_VIEW') or hasRole('ADMIN')">
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/paroleEligibility/edit.html?eligibility=${paroleEligibility.id}"><span class="visibleLinkLabel"><fmt:message key="viewParoleEligibilityLink"/></span></a>
			</li>
		</sec:authorize>
		<sec:authorize access="hasRole('HEARING_ANALYSIS_VIEW') or hasRole('ADMIN')">
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/hearingAnalysis/edit.html?eligibility=${paroleEligibility.id}"><span class="visibleLinkLabel"><fmt:message key="viewHearingAnalysisLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>