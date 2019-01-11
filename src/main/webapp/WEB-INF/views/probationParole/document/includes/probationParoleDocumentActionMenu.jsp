<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.probationparole.msgs.probationParoleDocument">
	<ul>
		<sec:authorize access="hasRole('PROBATION_PAROLE_DOCUMENT_LIST') or hasRole('ADMIN')">
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/probationParole/document/list.html?offender=${offender.id}"><span class="visibleLinkLabel"><fmt:message key="viewProbationParoleDocumentsLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>