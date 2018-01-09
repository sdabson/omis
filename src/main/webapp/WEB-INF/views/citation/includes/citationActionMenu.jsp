<!-- 
 - Author: Trevor Isles
 - Version: 0.1.0 (Aug 22, 2016)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.citation.msgs.citation">
	<ul>
		<sec:authorize access="hasRole('MISDEMEANOR_CITATION_LIST') or hasRole('ADMIN')">
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/citation/list.html?offender=${offender.id}">
				<span class="visibleLinkLabel"><fmt:message key="listCitationsLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>