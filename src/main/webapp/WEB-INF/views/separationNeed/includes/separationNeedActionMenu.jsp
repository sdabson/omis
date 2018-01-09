<!-- 
 - Author: Joel Norris
 - Version: 0.1.0 (May 18, 2015)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.separationneed.msgs.separationNeed">
	<ul>
		<sec:authorize access="hasRole('SEPARATION_NEED_MODULE') or hasRole('ADMIN')">
			<li><a class="listLink" href="${pageContext.request.contextPath}/separationNeed/list.html?offender=${offender.id}"><span class="visibleLinkLabel"><fmt:message key="listSeparationNeedsLink"/></span></a></li>
		</sec:authorize>
		<li></li>
		<li></li>
	</ul>
</fmt:bundle>