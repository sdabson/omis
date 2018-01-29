<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle" />
<fmt:bundle basename="omis.boardhearing.msgs.boardHearing">
	<ul>
		<sec:authorize access="hasRole('PAROLE_ELIGIBILITY_LIST') or hasRole('ADMIN')">
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/paroleEligibility/list.html?offender=${offender.id}"><span class="visibleLinkLabel"><fmt:message key="listParoleEligibilitiesLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>